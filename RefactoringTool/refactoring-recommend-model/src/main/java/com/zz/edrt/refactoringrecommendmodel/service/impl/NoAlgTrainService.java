package com.zz.edrt.refactoringrecommendmodel.service.impl;

import com.zz.edrt.edrtcommon.Project;
import com.zz.edrt.refactoringrecommendmodel.feign.DataPreFeignService;
import com.zz.edrt.refactoringrecommendmodel.service.TrainService;
import com.zz.edrt.refactoringrecommendmodel.util.ShellUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class NoAlgTrainService implements TrainService {
    private List<Project> trainingSet;
    private String modelName;
    public NoAlgTrainService(List<Project> trainingSet, String modelName) {
        this.trainingSet = trainingSet;
        this.modelName = modelName;
    }
    @Autowired
    DataPreFeignService dataPreFeignService;
    @Override
    public boolean trainModel() throws IOException, InterruptedException {

        String dataPath;
        String tempPath = "......";
        //获取训练集中各项目的特征文件路径
        for (Project p : this.trainingSet){
            dataPath = dataPreFeignService.getFeatureFilePath(p);
            mergeFile(tempPath, dataPath);
        }


        //执行无参数的模型训练命令
        String command =  "python xxx.py tempPath.csv";//脚本还得把文件路径、状态、训练指标写到数据库中

        new ShellUtil().run(command, new File("....."));

        return false;
    }


}
