package com.zz.edrt.premodel.service;

import com.zz.edrt.edrtcommon.Project;
import com.zz.edrt.premodel.domain.Metric;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

public interface RefRecommendService {
    boolean getRecommendations(Project project, String model) throws IOException, InterruptedException;

    Resource loadFileAsResource(Project project, String model);

    List<Metric> getMetricsList();
}
