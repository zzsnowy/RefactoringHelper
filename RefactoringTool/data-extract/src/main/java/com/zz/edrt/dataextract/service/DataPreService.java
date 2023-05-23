package com.zz.edrt.dataextract.service;

import com.zz.edrt.eddetect.domain.SmellDetectRule;
import com.zz.edrt.edrtcommon.Project;

public interface DataPreService {
    boolean prepare(SmellDetectRule smellDetectRule) throws Exception;

    String getFeatureFilePath(Project project);
}
