package com.zz.edrt.dataextract.feature;

import com.zz.edrt.dataextract.util.CsvUtil;
import org.apache.commons.lang3.ArrayUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FeatureCombiner extends MetricsHandler{

    String type;
    public static final String CLASS = "class";
    public static final String EVOLUTION = "evolution";
    public static final String LOC = "loc";
    public static final String USAGE = "usage";

    public FeatureCombiner(String type) {
        this.type = type;
    }



    public void readAndHandleData(String pro) throws IOException{
        List<String[]> classMetricsLists = readLabelDependenciesFeaturesCsv(pro, CLASS);
        List<String[]> evolutionMetricsLists = readLabelDependenciesFeaturesCsv(pro, EVOLUTION);
        List<String[]> locMetricsLists = readLabelDependenciesFeaturesCsv(pro, LOC);
        List<String[]> usageMetricsLists = readLabelDependenciesFeaturesCsv(pro, USAGE);
        List<String[]> featuresLists = combineByDependency(pro, classMetricsLists, evolutionMetricsLists, locMetricsLists, usageMetricsLists);
        writeCsv(pro, type, featuresLists);
    }


    private List<String[]> combineByDependency(String pro, List<String[]> classMetricsLists, List<String[]> evolutionMetricsLists, List<String[]> locMetricsLists, List<String[]> usageMetricsLists) {

        List<String[]> res = new ArrayList<>();

        int size = classMetricsLists.size();

        if(evolutionMetricsLists.size() != size || locMetricsLists.size() != size || usageMetricsLists.size() != size){
            System.out.println("各级特征csv文件长度不同：" + pro);
        }

        for(int i = 0; i < size; i ++){

            String labelDependency = classMetricsLists.get(i)[0];

            if(!evolutionMetricsLists.get(i)[0].equals(labelDependency) || !locMetricsLists.get(i)[0].equals(labelDependency) ||
                    !usageMetricsLists.get(i)[0].equals(labelDependency)){
                System.out.println("各级特征无法对应：" + pro + " " + labelDependency);
            }

            String[] e = ArrayUtils.subarray(evolutionMetricsLists.get(i), evolutionMetricsLists.get(i).length - 4, evolutionMetricsLists.get(i).length);
            String[] l = ArrayUtils.subarray(locMetricsLists.get(i), locMetricsLists.get(i).length - 1, locMetricsLists.get(i).length);
            String[] u = ArrayUtils.subarray(usageMetricsLists.get(i), usageMetricsLists.get(i).length - 1, usageMetricsLists.get(i).length);
            res.add(ArrayUtils.addAll(ArrayUtils.addAll(classMetricsLists.get(i), e),
                    ArrayUtils.addAll(l, u)));

        }

        return res;
    }

    public List<String[]> readLabelDependenciesFeaturesCsv(String pro, String type) throws IOException {

        String path = "/Users/zzsnowy/edrtFolder/feature/" + type + "/" + pro + ".csv";

        return new CsvUtil().readCsv(path);
    }


}
