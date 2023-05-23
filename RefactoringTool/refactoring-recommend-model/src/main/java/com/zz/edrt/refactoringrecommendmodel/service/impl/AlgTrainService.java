package com.zz.edrt.refactoringrecommendmodel.service.impl;

import com.zz.edrt.edrtcommon.Project;
import com.zz.edrt.premodel.domain.Metric;
import com.zz.edrt.premodel.domain.Model;
import com.zz.edrt.refactoringrecommendmodel.domain.ModelTrain;
import com.zz.edrt.refactoringrecommendmodel.feign.DataPreFeignService;
import com.zz.edrt.refactoringrecommendmodel.service.TrainService;
import com.zz.edrt.refactoringrecommendmodel.util.ShellUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class AlgTrainService implements TrainService {

    private List<Project> trainingSet;
    private String modelName;
    private List<String> modelParams;
    public AlgTrainService(List<Project> trainingSet, String modelName, List<String> modelParams) {
        this.trainingSet = trainingSet;
        this.modelName = modelName;
        this.modelParams = modelParams;
    }
    @Autowired
    DataPreFeignService dataPreFeignService;

    @Override
    public boolean trainModel() throws IOException, InterruptedException {
        String dataPath;
        String tempPath = "tempPath.csv";
        //获取训练集中各项目的特征文件路径
        for (Project p : this.trainingSet){
            dataPath = dataPreFeignService.getFeatureFilePath(p);
            mergeFile(tempPath, dataPath);
        }
        //执行带参数的模型训练命令
        StringBuilder command = new StringBuilder("python train_ref.py tempPath.csv " + this.modelName);//脚本还得把文件路径、状态、训练指标写到数据库中
        for(String s: modelParams){
            command.append(" ").append(s);
        }
        String pyRefPath = "....../PycharmProjects/evolutionary-dependency-refactoring-prediction";
        new ShellUtil().run(command.toString(), new File(pyRefPath));
        return false;
    }


}
