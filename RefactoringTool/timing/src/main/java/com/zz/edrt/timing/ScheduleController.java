package com.zz.edrt.timing;

import org.quartz.SchedulerException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class ScheduleController extends ScheduleRequest {

    @PostMapping("/schedule")
    public String scheduleTask(@RequestBody ScheduleRequest request) throws SchedulerException {
        Scheduler scheduler = new Scheduler();
        scheduler.scheduleTask( request.getTask(), request.getCronExpression(), request.getProjectName());
        return "Scheduled task: " + request.getTask() + " for project: " + request.getProjectName() + " with cron expression: " + request.getCronExpression();
    }
}
