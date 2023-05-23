package com.zz.edrt.refactoringrecommendmodel.service;

import javax.annotation.Resource;

public interface ExportFileService {
    Resource exportModel(String modelName, String modelType);
}
