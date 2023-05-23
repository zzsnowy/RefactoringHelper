package com.zz.edrt.dataextract.service;

import com.zz.edrt.edrtcommon.Project;

public interface DataExtractService {
    public boolean detect(Project project);

    public String getFilePath(Project project);
}
