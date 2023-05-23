package com.zz.edrt.eddetect.service;

import com.zz.edrt.eddetect.domain.SmellDetectRule;

import java.io.IOException;

public interface SmellDetectService {
    public boolean detect(SmellDetectRule smellDetectRule) throws IOException;

    boolean detectByCommitId(SmellDetectRule smellDetectRule);

}
