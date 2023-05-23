package com.zz.edrt.dataextract.feature;


import org.apache.commons.lang3.ArrayUtils;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class ClassMetricsHandler extends MetricsHandler{

    String type;

    public static final String LEVEL = "class";

    public ClassMetricsHandler(String type) {
        this.type = type;
    }

    public void readAndHandleData(String pro) throws IOException{

        List<String[]> classMetricsLists = readLabelDependenciesMetricsCsv(pro, LEVEL);
        List<String[]> entityClassMetricsList = new ArrayList<>();

        String path = "/Users/zzsnowy/edrtFolder/dependency/" + pro + ".txt";

        File filename = new File(path);
        InputStreamReader reader = new InputStreamReader(
                new FileInputStream(filename));
        BufferedReader br = new BufferedReader(reader);

        String labelDependency = br.readLine();

        while (labelDependency != null) {

            String node1 = labelDependency.split("\t")[0];
            String node2 = labelDependency.split("\t")[1];

            String[] s1 = findClassMetricsByNode(pro, node1, classMetricsLists);
            String[] s2 = findClassMetricsByNode(pro, node2, classMetricsLists);

            if(s1 == null || s2 == null){
                System.out.println("类指标不存在：" + pro);
                labelDependency = br.readLine();
                continue;
            }
            String tmp = node1 + "+" + node2;

            String[] s = calEntityClassMetrics(tmp, s1, s2);

            entityClassMetricsList.add(s);

            labelDependency = br.readLine();
        }

        writeCsv(pro, type, entityClassMetricsList);
    }

    private static String[] findClassMetricsByNode(String pro, String node, List<String[]> classMetricslists) throws IOException {

        String className = node.split("/")[0];
        className = className.replace("__", "+");//im-server依赖的路径中存在__,原因是其文件名中有下划线，因此获取依赖时变成了两个_
        className = className.replace("_","/");
        className = className.replace("+","_");

        String fileName = "/Users/zzsnowy/edrtFolder/Project/" + pro + "/" + className;

        String[] classNameArray = fileName.split("/");
        String onlyClassName = classNameArray[classNameArray.length - 1].split("\\.")[0];

       // int num = 0;
        for (int i = 1; i < classMetricslists.size(); i++) {

            if(classMetricslists.get(i)[0].equals(fileName) && !classMetricslists.get(i)[1].contains("$")){
                String onlyClassNameTmp = classMetricslists.get(i)[1].split("\\.")[classMetricslists.get(i)[1].split("\\.").length - 1];
                if(onlyClassNameTmp.equals(onlyClassName)) {
                    return classMetricslists.get(i);
                    //num ++;
                }

            }

        }
/*
        if(num == 0){
            //System.out.println(pro + " 0 " + CommitUtil.getCoarseVer(pro, commitId) + " " + node + " " + fileName);
        }else if(num > 1){
            System.out.println(pro + " " + CommitUtil.getCoarseVer(pro, commitId) + " " + node + " " + fileName);
        }
*/
        return null;

    }

    private static String[] calEntityClassMetrics(String labelDependency, String[] s1, String[] s2) {

        String[] s = new String[49];
        int cnt = 0;

        for(int i = 3; i <= 51; i ++){

            if("NaN".equals(s1[i]) || "NaN".equals(s2[i])){
                s[cnt ++] = "NaN";
                continue;
            }

            double value = Math.abs(Double.parseDouble(s1[i]) - Double.parseDouble(s2[i]));
            s[cnt ++] = String.valueOf(value);
        }

        String[] d = new String[]{"\"" + labelDependency + "\""};
        s = ArrayUtils.addAll(d, s);

        return s;
    }

}
