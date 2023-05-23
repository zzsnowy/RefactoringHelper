package com.zz.edrt.refactoringrecommendmodel.service;

import com.zz.edrt.edrtcommon.Project;

import java.io.IOException;
import java.util.List;

public interface TrainService {

    public boolean trainModel() throws IOException, InterruptedException;

    default void mergeFile(String tempPath, String dataPath) {

    }


}
