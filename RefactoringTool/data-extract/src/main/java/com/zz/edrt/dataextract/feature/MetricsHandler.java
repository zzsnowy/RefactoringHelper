package com.zz.edrt.dataextract.feature;


import com.zz.edrt.dataextract.util.CsvUtil;

import java.io.*;
import java.util.List;


public class MetricsHandler {

    public void readAndHandleData(String pro) throws IOException{
    }

    public void writeCsv(String pro, String type, List<String[]> metricsList) throws IOException {

        String path = "/Users/zzsnowy/edrtFolder/feature/" + type + "/" + pro + ".csv";

        // 写入csv 制表符消失
        new CsvUtil().writeCsv(metricsList, "sheet0", path);


    }
    public static List<String[]> readLabelDependenciesMetricsCsv(String pro, String level) throws IOException {

        String path = "/Users/zzsnowy/edrtFolder/Metric/" + pro + "/" + level + ".csv";

        return new CsvUtil().readCsv(path);
    }


}
