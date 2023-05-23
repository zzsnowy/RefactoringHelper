package com.zz.edrt.refactoringrecommendmodel.domain;

import com.zz.edrt.edrtcommon.Project;
import com.zz.edrt.refactoringrecommendmodel.controller.ModelController;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class ModelTrain extends ModelController {
    public List<Project> getTrainingSet() {
        return trainingSet;
    }

    public void setTrainingSet(List<Project> trainingSet) {
        this.trainingSet = trainingSet;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public List<String> getModelParams() {
        return modelParams;
    }

    public void setModelParams(List<String> modelParams) {
        this.modelParams = modelParams;
    }

    private List<Project> trainingSet;
    private String modelName;
    private List<String> modelParams;
}
