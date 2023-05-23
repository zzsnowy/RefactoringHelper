package com.zz.edrt.refactoringrecommendmodel.controller;

import com.zz.edrt.edrtcommon.Project;
import com.zz.edrt.premodel.domain.Metric;
import com.zz.edrt.refactoringrecommendmodel.domain.ModelTrain;
import com.zz.edrt.refactoringrecommendmodel.service.EvaluateService;
import com.zz.edrt.refactoringrecommendmodel.service.ExportFileService;
import com.zz.edrt.refactoringrecommendmodel.service.PublishService;
import com.zz.edrt.refactoringrecommendmodel.service.TrainService;
import com.zz.edrt.refactoringrecommendmodel.service.impl.AlgTrainService;
import com.zz.edrt.refactoringrecommendmodel.service.impl.ExportFileServiceImpl;
import com.zz.edrt.refactoringrecommendmodel.service.impl.NoAlgTrainService;
import com.zz.edrt.refactoringrecommendmodel.service.impl.PublishServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

public class ModelController {
    @Autowired
    private TrainService trainService;
    @Autowired
    private EvaluateService evaluateService;
    @Autowired
    private ExportFileService exportService;
    @Autowired

    private PublishService publishService;

    @PostMapping("/train")
    public boolean handleTrain(@RequestBody ModelTrain modelTrain) throws IOException, InterruptedException {

        //策略模式 完成不同模型参数的校验

        if (modelTrain.getModelParams() != null) {
            trainService = new AlgTrainService(modelTrain.getTrainingSet(), modelTrain.getModelName(), modelTrain.getModelParams());
        } else {
            trainService = new NoAlgTrainService(modelTrain.getTrainingSet(), modelTrain.getModelName());
        }

        trainService.trainModel();
        return false;
    }


    @PostMapping("/eva/{modelName}")
    public Metric handleEvaluate(@PathVariable String modelName) {
        Metric metric = evaluateService.queryEvaluationMetrics(modelName);
        return metric;
    }



    @GetMapping("/exportFile/{modelName}")
    public ResponseEntity<Resource> exportFile(@PathVariable String modelName, @RequestParam("modelType") String modelType) {

        Resource resource = exportService.exportModel(modelName, modelType);
        String contentType = MediaType.APPLICATION_PDF_VALUE; // 文件类型
        String filename = "example.pdf"; // 文件名

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .body(resource);
    }

    @GetMapping("/publish/{modelName}")
    public boolean publish(@PathVariable String modelName) {
        return publishService.publish(modelName);
    }

}
