package com.zz.edrt.refactoringrecommendmodel.service;

import com.zz.edrt.refactoringrecommendmodel.domain.ModelTrain;

public interface ModelTrainService {
    ModelTrain select(String modelName);
}
