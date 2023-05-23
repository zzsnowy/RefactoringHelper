package com.zz.edrt.dataextract.service.impl;

import com.zz.edrt.dataextract.feature.DataMain;
import com.zz.edrt.dataextract.service.DataExtractService;
import com.zz.edrt.dataextract.util.ShellUtil;
import com.zz.edrt.edrtcommon.Project;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class DataExtractServiceImpl implements DataExtractService {

    private DataMain dataMain = new DataMain();
    @Override
    public boolean detect(Project project) {
        if(!genMetric(project)){
            //将数据库状态改为失败
            //...
            return false;
        }
        if(!genFeature(project)){
            //将数据库状态改为失败
            //...
            return false;
        }
        //将路径写入数据
        //...
        //记录更新时间
        //记录更新的commitId
        return true;
    }

    @Override
    public String getFilePath(Project project) {
        return null;
    }

    private boolean genFeature(Project project) {
        return dataMain.genFeature(project.getName());
    }

    private boolean genMetric(Project project) {
        try {

            String metaPath = "/Users/zzsnowy/edrtFolder/Metric/";
            String name = project.getName();
            File dir = new File("/Users/zzsnowy/edrtFolder");
            String command = "mkdir " + metaPath + name;
            new ShellUtil().run(command, dir);
            //生成指标数据
            command = "java -jar ck-0.7.1-SNAPSHOT-jar-with-dependencies.jar " +
                    "/Users/zzsnowy/edrtFolder/Project/" + name +
                    " true 0 True " +
                   metaPath + name + "/";
            new ShellUtil().run(command, dir);
            return true;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }


}


