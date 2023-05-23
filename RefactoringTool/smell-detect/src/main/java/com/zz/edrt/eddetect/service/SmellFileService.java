package com.zz.edrt.eddetect.service;

import com.zz.edrt.eddetect.domain.SmellDetectRule;

import javax.annotation.Resource;

public interface SmellFileService {
    Resource loadFileAsResource(SmellDetectRule smellDetectRule);

    String getFilePath(SmellDetectRule smellDetectRule);
}
