//package com.zz.edrt.premodel.service.impl;
//
//import jep.Jep;
//import org.apache.commons.io.IOUtils;
//import java.io.*;
//import java.util.Arrays;
//
//public class SklearnModel {
//
//    public static void main(String[] args) throws Exception {
//
//        // 初始化 Jep
//        try (Jep jep = new Jep()) {
//
//            // 添加 sklearn 和 joblib 模块路径到 Python 搜索路径
//            jep.eval("import sys");
//            jep.eval("sys.path.append('/path/to/sklearn')");
//            jep.eval("sys.path.append('/path/to/joblib')");
//
//            // 读取保存的模型文件
//            byte[] modelBytes = IOUtils.toByteArray(new FileInputStream("iris_clf.joblib"));
//
//            // 载入模型
//            jep.set("modelBytes", modelBytes);
//            jep.eval("import joblib");
//            jep.eval("clf = joblib.loads(modelBytes)");
//
//            // 使用模型进行预测
//            jep.eval("import numpy as np");
//            jep.eval("X = np.array([[5.1, 3.5, 1.4, 0.2], [4.9, 3.0, 1.4, 0.2], [6.0, 3.0, 4.8, 1.8]])");
//            jep.eval("y_pred = clf.predict(X)");
//            int[] yPred = jep.getValue("y_pred", int[].class);
//            System.out.println("Predicted targets: " + Arrays.toString(yPred));
//        }
//    }
//}
