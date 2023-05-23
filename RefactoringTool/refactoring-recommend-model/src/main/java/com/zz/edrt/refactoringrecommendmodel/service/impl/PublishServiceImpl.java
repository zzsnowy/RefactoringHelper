package com.zz.edrt.refactoringrecommendmodel.service.impl;

import com.zz.edrt.premodel.domain.Model;
import com.zz.edrt.refactoringrecommendmodel.domain.ModelTrain;
import com.zz.edrt.refactoringrecommendmodel.feign.ModelFeignService;
import com.zz.edrt.refactoringrecommendmodel.service.ExportFileService;
import com.zz.edrt.refactoringrecommendmodel.service.ModelTrainService;
import com.zz.edrt.refactoringrecommendmodel.service.PublishService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

public class PublishServiceImpl implements PublishService {

    @Autowired
    ModelFeignService modelFeignService;
    @Autowired
    ModelTrainService modelTrainService;
    @Override
    public boolean publish(String modelName) {
        //读取模型信息，包括名称、描述、头像、模型文件地址

        ModelTrain modelTrain = modelTrainService.select(modelName);
        Model model = new Model();//用modelTrain的内容组成model
        //调用重构推荐微服务新增模型的接口即可
        modelFeignService.addModel(model);
        return false;
    }
}
