package com.zz.edrt.eddetect.service.impl;

import com.zz.edrt.eddetect.domain.SmellDetectRule;
import com.zz.edrt.eddetect.service.SmellFileService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SmellFileServiceImpl implements SmellFileService {
    @Override
    public Resource loadFileAsResource(SmellDetectRule smellDetectRule) {
        return null;
    }

    @Override
    public String getFilePath(SmellDetectRule smellDetectRule) {
        return null;
    }
}
