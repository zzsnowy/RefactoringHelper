package com.zz.edrt.dataextract.feature;

import com.zz.edrt.dataextract.util.CsvUtil;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class MergeAllProFeatures {


    public void merge(String pro) throws IOException{

        List<String[]> res = new ArrayList<>();
        res.addAll(getDependencybyPro(pro));


        String outPath = "/Users/zzsnowy/edrtFolder/features.csv";
        new CsvUtil().writeCsv(res, "sheet0", outPath);
    }
    public List<String[]> getDependencybyPro(String pro) throws IOException {

        System.out.println("正在处理：" + pro);

        List<String[]> res = new ArrayList<>(readFeatureCsv(pro));

        return res;
    }


    private static List<String[]> readFeatureCsv(String pro) throws IOException {

        String path = "/Users/zzsnowy/edrtFolder/feature/features/" + pro + ".csv";

        return new CsvUtil().readCsv(path);
    }



}
