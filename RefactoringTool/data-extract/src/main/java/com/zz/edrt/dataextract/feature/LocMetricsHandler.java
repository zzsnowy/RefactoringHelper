package com.zz.edrt.dataextract.feature;

import org.apache.commons.lang3.ArrayUtils;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class LocMetricsHandler extends MetricsHandler{

    String type;

    public static final String LEVEL = "method";
    public LocMetricsHandler(String type) {
        this.type = type;
    }

    public void readAndHandleData(String pro) throws IOException{
        List<String[]> locMetricsLists = readLabelDependenciesMetricsCsv(pro, LEVEL);
        List<String[]> entityLocMetricsList = new ArrayList<>();

        String path = "/Users/zzsnowy/edrtFolder/dependency/" + pro + ".txt";

        File filename = new File(path);
        InputStreamReader reader = new InputStreamReader(
                new FileInputStream(filename));
        BufferedReader br = new BufferedReader(reader);

        String labelDependency = br.readLine();

        while (labelDependency != null) {

            String node1 = labelDependency.split("\t")[0];
            String node2 = labelDependency.split("\t")[1];

            String[] s1 = findLocMetricsByNode(pro, node1, locMetricsLists);
            String[] s2 = findLocMetricsByNode(pro, node2, locMetricsLists);
            String tmp = node1 + "+" + node2;
            if(s1 == null || s2 == null){

                System.out.println("实体Loc指标不存在：" + pro);
                String[] s = new String[]{"\"" + tmp + "\"", "NaN"};
                entityLocMetricsList.add(s);
                labelDependency = br.readLine();
                continue;
            }

            String[] s = calEntityLocMetrics(tmp, s1, s2);

            entityLocMetricsList.add(s);

            labelDependency = br.readLine();
        }
        writeCsv(pro, type, entityLocMetricsList);
    }


    private String[] findLocMetricsByNode(String pro, String node, List<String[]> locMetricslists) {

        String[] s = new String[1];
        if(node.contains("[FE]")){
            s[0] = "1";
            return s;
        }

        String[] data = node.split("/");
        String className = data[0];
        className = className.replace("__", "+");//im-server依赖的路径中存在__,原因是其文件名中有下划线，因此获取依赖时变成了两个_
        className = className.replace("_","/");
        className = className.replace("+","_");

        String fileName = "/Users/zzsnowy/edrtFolder/Project/" + pro + "/" + className;

        String methodName = data[data.length - 1].split("\\(")[0];

        for (int i = 1; i < locMetricslists.size(); i++) {

            if(locMetricslists.get(i)[0].equals(fileName)){
                String methodNameTmp = locMetricslists.get(i)[2].split("/")[0];

                if(methodNameTmp.contains("\"")){
                    methodNameTmp = methodNameTmp.substring(1);
                }

                if(methodNameTmp.equals(methodName)) {
                    s[0] = locMetricslists.get(i)[locMetricslists.get(i).length - 23];
                    return s;
                }

            }

        }
        //System.out.println(node + " " + fileName);
        return null;
    }

    private String[] calEntityLocMetrics(String labelDependency, String[] s1, String[] s2) {
        String[] s = new String[1];

        if("NaN".equals(s1[0]) || "NaN".equals(s2[0])){
            s[0] = "NaN";
        } else {
            double value = Math.abs(Double.parseDouble(s1[0]) - Double.parseDouble(s2[0]));
            s[0] = String.valueOf(value);
        }

        String[] d = new String[]{"\"" + labelDependency + "\""};
        return ArrayUtils.addAll(d, s);
    }

}
