package com.zz.edrt.timing;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import javax.sql.DataSource;
import java.util.Properties;

public class Scheduler implements Job {
    private Task task;

    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        String taskName = dataMap.getString("taskName");
        String projectName = dataMap.getString("projectName");
        this.task = TaskFactory.createTask(taskName);
        task.execute(projectName);
    }

    public void scheduleTask(String taskName, String cronExpression, String projectName) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(Scheduler.class)
                .withIdentity(taskName, projectName)
                .build();
        jobDetail.getJobDataMap().put("taskName", taskName);
        jobDetail.getJobDataMap().put("projectName", projectName);

        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(taskName, projectName)
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
                .build();


        // 配置JobStore的数据源
        ///DataSource dataSource = ...; // 数据库连接池或者其他数据源实例
        Properties props = new Properties();
        props.setProperty("org.quartz.jobStore.dataSource", "myDS");
        props.setProperty("org.quartz.dataSource.myDS.jndiURL", "java:comp/env/jdbc/myDS");
        props.setProperty("org.quartz.dataSource.myDS.driver", "com.mysql.jdbc.Driver");
        props.setProperty("org.quartz.dataSource.myDS.URL", "jdbc:mysql://localhost/quartz");
        props.setProperty("org.quartz.dataSource.myDS.user", "quartz");
        props.setProperty("org.quartz.dataSource.myDS.password", "quartz");
        SchedulerFactory schedulerFactory = new StdSchedulerFactory(props);
        org.quartz.Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();
    }
}
