package com.zz.edrt.dataextract.feature;

import org.apache.commons.lang3.ArrayUtils;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class UsageMetricsHandler extends MetricsHandler{
    String type;

    public static final String LEVEL_M = "method";
    public static final String LEVEL_F = "field";

    public UsageMetricsHandler(String type) {
        this.type = type;
    }

    public void readAndHandleData(String pro) throws IOException {

        List<String[]> usageMethodMetricsLists = readLabelDependenciesMetricsCsv(pro, LEVEL_M);
        List<String[]> usageFieldMetricsLists = readLabelDependenciesMetricsCsv(pro, LEVEL_F);
        List<String[]> entityUsageMetricsList = new ArrayList<>();

       String path = "/Users/zzsnowy/edrtFolder/dependency/" + pro + ".txt";

        File filename = new File(path);
        InputStreamReader reader = new InputStreamReader(
                new FileInputStream(filename));
        BufferedReader br = new BufferedReader(reader);

        String labelDependency = br.readLine();
        while (labelDependency != null) {

            String node1 = labelDependency.split("\t")[0];
            String node2 = labelDependency.split("\t")[1];

            String[] s1 = findUsageMetricsByNode(pro, node1, usageMethodMetricsLists, usageFieldMetricsLists);
            String[] s2 = findUsageMetricsByNode(pro, node2, usageMethodMetricsLists, usageFieldMetricsLists);
            String tmp = node1 + "+" + node2;
            if(s1 == null || s2 == null){
                //经测试，在现有项目中，只有可能是field未找到，即field使用次数为0
                String[] s = new String[]{"\"" + tmp + "\"", "0"};
                entityUsageMetricsList.add(s);
                labelDependency = br.readLine();
                continue;
            }

            String[] s = calEntityLocMetrics(tmp, s1, s2);

            entityUsageMetricsList.add(s);

            labelDependency = br.readLine();
        }
        writeCsv(pro, type, entityUsageMetricsList);
    }

    private String[] findUsageMetricsByNode(String pro, String node, List<String[]> usageMethodMetricslists, List<String[]> usageFieldMetricslists) throws IOException {

        // method 和 field 都有可能找不到对应指标
        if(node.matches("[\\s\\S]*\\[MT][\\s\\S]*")){
            return findMethodUsageMetricsByNode(pro, node, usageMethodMetricslists);
        } else if(node.matches("[\\s\\S]*\\[FE][\\s\\S]*")){
            return findFieldUsageMetricsByNode(pro, node, usageFieldMetricslists);
        } else{
            System.out.println("查找使用次数出错：" + node);
        }
        return null;
    }

    private String[] findMethodUsageMetricsByNode(String pro, String node, List<String[]> usageMethodMetricslists) throws IOException {

        String[] data = node.split("/");
        String className = data[0];
        className = className.replace("__", "+");//im-server依赖的路径中存在__,原因是其文件名中有下划线，因此获取依赖时变成了两个_
        className = className.replace("_","/");
        className = className.replace("+","_");

        String fileName = "/Users/zzsnowy/edrtFolder/Project/" + pro + "/" + className;

        String methodName = data[data.length - 1].split("\\(")[0];



        for (int i = 1; i < usageMethodMetricslists.size(); i++) {

            if(usageMethodMetricslists.get(i)[0].equals(fileName)){
                String methodNameTmp = usageMethodMetricslists.get(i)[2].split("/")[0];

                if(methodNameTmp.contains("\"")){
                    methodNameTmp = methodNameTmp.substring(1);
                }
                if(methodNameTmp.equals(methodName)) {
                    String[] methodMetrics = usageMethodMetricslists.get(i);
                    return new String[]{methodMetrics[methodMetrics.length - 17] + methodMetrics[methodMetrics.length - 18]
                    + methodMetrics[methodMetrics.length - 19]};
                }

            }

        }
        System.out.println("实体MethodUsage指标不存在：" + pro);
        return null;
    }

    private String[] findFieldUsageMetricsByNode(String pro, String node, List<String[]> usageFieldMetricslists) throws IOException {

        String[] data = node.split("/");
        String className = data[0];
        className = className.replace("__", "+");//im-server依赖的路径中存在__,原因是其文件名中有下划线，因此获取依赖时变成了两个_
        className = className.replace("_","/");
        className = className.replace("+","_");


        String fieldName = data[data.length - 1];

        int sum = 0;
        boolean flag = false;
        for (int i = 1; i < usageFieldMetricslists.size(); i++) {

            //if(usageFieldMetricslists.get(i)[0].equals(fileName)){
                String fieldNameTmp = usageFieldMetricslists.get(i)[usageFieldMetricslists.get(i).length - 2];
                if(fieldNameTmp.equals(fieldName)) {
                    flag = true;
                    sum += Integer.parseInt(usageFieldMetricslists.get(i)[usageFieldMetricslists.get(i).length - 1]);
                }

            //}

        }

        if(flag){
            return new String[]{String.valueOf(sum)};
        } else {
            //logger.info("commit:{}, {}实体FieldUsage指标不存在", CommitUtil.getCoarseVer(pro, commitId), node);
            //System.out.println(pro + " 0 " + CommitUtil.getCoarseVer(pro, commitId) + " " + node + " " + fileName);
            return null;
        }

    }

    private String[] calEntityLocMetrics(String labelDependency, String[] s1, String[] s2) {
        String[] s = new String[1];

        if("NaN".equals(s1[0]) || "NaN".equals(s2[0])){
            s[0] = "NaN";
        } else {
            //System.out.println(labelDependency + " " + s1[0] + " " + s2[0]);
            double value = Math.abs(Double.parseDouble(s1[0]) - Double.parseDouble(s2[0]));
            s[0] = String.valueOf(value);
        }

        String[] d = new String[]{"\"" + labelDependency + "\""};
        return ArrayUtils.addAll(d, s);
    }


}
