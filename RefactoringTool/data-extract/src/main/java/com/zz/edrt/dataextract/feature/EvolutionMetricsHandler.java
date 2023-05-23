package com.zz.edrt.dataextract.feature;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class EvolutionMetricsHandler extends MetricsHandler{

    String type;

    public EvolutionMetricsHandler(String type) {
        this.type = type;
    }

    public void readAndHandleData(String pro) throws IOException{

        List<String[]> evolutionMetricsLists = readEvolutionMetricslists(pro);

        List<String[]> entityEvolutionMetricsList = new ArrayList<>();

        String path = "/Users/zzsnowy/edrtFolder/dependency/" + pro + ".txt";

        File filename = new File(path);
        InputStreamReader reader = new InputStreamReader(
                new FileInputStream(filename));
        BufferedReader br = new BufferedReader(reader);

        String labelDependency = br.readLine();

        while (labelDependency != null) {
            String tmp = labelDependency.split("\t")[0] + "\t" + labelDependency.split("\t")[1];
            String[] s = findEvolutionMetricsByDependency(pro, tmp, evolutionMetricsLists);
            entityEvolutionMetricsList.add(s);
            labelDependency = br.readLine();
        }
        writeCsv(pro, type, entityEvolutionMetricsList);
    }

    private List<String[]> readEvolutionMetricslists(String pro) throws IOException {

        List<String[]> evolutionMetricslists = new ArrayList<>();

        String path = "/Users/zzsnowy/edrtFolder/dependency/" + pro + ".txt";


        File filename = new File(path);
        InputStreamReader reader = new InputStreamReader(
                new FileInputStream(filename));
        BufferedReader br = new BufferedReader(reader);

        String dependency = br.readLine();

        while (dependency != null) {

            evolutionMetricslists.add(dependency.split("\t"));
            dependency = br.readLine();
        }
        return evolutionMetricslists;

    }

    private static String[] findEvolutionMetricsByDependency(String pro, String labelDependency, List<String[]> evolutionMetricslists) throws IOException {

        String[] s = new String[5];

        String node1 = labelDependency.split("\t")[0];
        String node2 = labelDependency.split("\t")[1];

        s[0] = "\"" + node1 + "+" + node2 + "\"";

        int num = 0;

        boolean flag = false;
        for (int i = 0; i < evolutionMetricslists.size(); i++) {

            String[] data = evolutionMetricslists.get(i);
            if((data[0].equals(node1) && data[1].equals(node2)) ||
                    (data[1].equals(node1) && data[0].equals(node2))){
                if(!flag){
                    s[1] = data[2];
                    s[2] = data[3];
                    s[3] = data[3];
                    s[4] = data[5];
                    flag = true;
                } else {
                    s[3] = data[3];
                    if(!data[2].equals(s[1]) || !data[5].equals(s[4])){
                        System.out.println("错误：" + labelDependency);
                    }
                }
                num ++;
            }

        }

        if(num == 1){
            System.out.println(num + " " + labelDependency);
        }

        //2 3 5
        sortConfidence(s);
        return s;
    }

    private static void sortConfidence(String[] s) {
        double a = Double.parseDouble(s[2]);
        double b = Double.parseDouble(s[3]);
        if(a > b){
            s[2] = String.valueOf(b);
            s[3] = String.valueOf(a);
        }
    }

}

