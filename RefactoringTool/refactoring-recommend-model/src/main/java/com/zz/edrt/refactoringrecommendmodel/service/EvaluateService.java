package com.zz.edrt.refactoringrecommendmodel.service;

import com.zz.edrt.premodel.domain.Metric;

public interface EvaluateService {
    Metric queryEvaluationMetrics(String modelName);
}
