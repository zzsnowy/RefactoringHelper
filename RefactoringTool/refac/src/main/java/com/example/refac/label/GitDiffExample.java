package com.example.refac.label;

import java.io.*;
import java.util.*;

import com.example.refac.util.ShellUtil;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;
import org.eclipse.jgit.treewalk.TreeWalk;
import org.eclipse.jgit.treewalk.filter.PathFilter;

public class GitDiffExample {
    public static void main(String[] args) throws Exception {
        List<String> proList = new ArrayList<>();
        proList.add("litemall");
//        proList.add("zuul");
//        proList.add("mockito");
//        proList.add("apollo");
//        proList.add("mockserver");
//        proList.add("YalpStore");
//        proList.add("webmagic");
//        proList.add("disruptor");
//        proList.add("Hystrix");
        getNonRefOperation(proList);
    }
    private static void getNonRefOperation(List<String> proList) throws IOException {


        Set<String> set = new HashSet<>();

        for(String pro: proList){
            Map<String, String[]> map = new HashMap<>();
            String proPath = "/Users/zzsnowy/StudyDiary/MSA/graduationPro/experiment/projects/" + pro;
            File dir = new File(proPath);
            String tmpPath = "/Users/zzsnowy/Desktop/fighting/refac/shell/tmp.txt";
            String pathname = "commit/" + pro + "-coarse.txt";
            File filename = new File(pathname);
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(filename));
            BufferedReader br = new BufferedReader(reader);
            String commitId = br.readLine();
            while (commitId != null) {
                new ProcessBuilder("/Users/zzsnowy/Desktop/fighting/refac/shell/git_diff_zx.sh", commitId)
                        .directory(dir)
                        .redirectOutput(new File(tmpPath))
                        .start();
                File tmp = new File(tmpPath);
                BufferedReader tBr = new BufferedReader(new InputStreamReader(new FileInputStream(tmp)));
                String c = tBr.readLine();
                while (c != null){
                    if(map.containsKey(pro + " " + c)){
                        String[] tmpS = map.get(pro + " " + c);
                        tmpS[0] = String.valueOf(Integer.parseInt(tmpS[0]) + 1);
                        map.put(pro + " " + c, tmpS);

                        tmpS = map.get(pro + " " + c);
                        if(Integer.parseInt(tmpS[0]) == 50){
                            set.add(pro + " " + c + " " + tmpS[1] + " " + commitId);
                            map.remove(pro + " " + c);
                        }
                    } else{
                        map.put(pro + " " + c, new String[]{"1", commitId});
                    }
                    c = tBr.readLine();
                }
                commitId = br.readLine();
            }
        }
        System.out.println("dsdfsdf");
        serialSet("fifCsSet", set);
        for(String s: set){
            System.out.println(s);
        }

    }
    private static void serialSet(String name, Set<String> set) throws IOException {
        String path = "io" + File.separator  + name + ".txt";
        createFile(path);
        try (OutputStream op =  new FileOutputStream( path);
             ObjectOutputStream ops =  new  ObjectOutputStream(op);) {
            ops.writeObject(set);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static void createFile(String filePath) throws IOException {
        // 创建File对象
        File file = new File(filePath);

        // 检查文件或目录是否存在
        if (file.exists()) {
            // 如果存在，删除文件或目录
            if (file.delete()) {
                System.out.println(filePath + "已成功删除！");
            } else {
                System.out.println(filePath + "删除失败。");
            }
        } else {
            // 如果不存在，创建文件或目录
            if (file.createNewFile()) {
                System.out.println(filePath + "已成功创建！");
            } else {
                System.out.println(filePath + "创建失败。");
            }
        }
    }


}