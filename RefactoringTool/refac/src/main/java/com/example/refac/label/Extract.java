package com.example.refac.label;

import com.example.refac.domain.RefClassNode;
import com.example.refac.domain.RefMethodNode;
import com.example.refac.util.CommitUtil;
import com.example.refac.util.CsvUtil;
import com.example.refac.util.ShellUtil;
import gr.uom.java.xmi.diff.ExtractClassRefactoring;
import gr.uom.java.xmi.diff.ExtractOperationRefactoring;
import gr.uom.java.xmi.diff.MoveClassRefactoring;
import gr.uom.java.xmi.diff.MoveOperationRefactoring;
import lombok.SneakyThrows;
import org.eclipse.jgit.lib.Repository;
import org.refactoringminer.api.*;
import org.refactoringminer.rm1.GitHistoryRefactoringMinerImpl;
import org.refactoringminer.util.GitServiceImpl;

import java.io.*;
import java.util.*;

import static com.example.refac.util.CommitUtil.getLastCommitId;
import static org.refactoringminer.api.RefactoringType.*;

public class Extract {
//    static Map<String, String> urlMap = new HashMap<>();
    public static void main(String[] args) throws Exception {
        List<String> proList = new ArrayList<>();
        proList.add("litemall");
        proList.add("zuul");
        proList.add("mockito");
        proList.add("apollo");
        proList.add("mockserver");
        proList.add("YalpStore");
        proList.add("webmagic");
        proList.add("disruptor");
        proList.add("HikariCP");
        proList.add("Hystrix");
//        proList.add("APIJSON");


        minerRefOperation(proList);
        InputStream in =  new FileInputStream( "io" + File.separator + "classSetMm.txt" );
        ObjectInputStream os =  new  ObjectInputStream(in);
        Set<String> proIdSet = (Set<String>) os.readObject();
        for(String s: proIdSet){
            System.out.println(s);
        }
//        for(String s: proIdSet){
//            if(s.split(" ").length != 2){
//                continue;
//            }
//            System.out.println(s);
//            String pro = s.split(" ")[0];
//            String commitId = s.split(" ")[1];
//
//            File file = new File("/Users/zzsnowy/Desktop/fighting/metrics/" + pro + "_" + commitId + "_class.csv");
//            if (file.exists()) {
//                continue;
//            }
//
//            String metaPath = "/Users/zzsnowy/Desktop/fighting/metrics/";
//            String proPath = "/Users/zzsnowy/StudyDiary/MSA/graduationPro/experiment/projects/" + pro;
//            File dir = new File(proPath);
//            String command = "git checkout -f " + commitId;
//            new ShellUtil().run(command, dir);
//            //生成指标数据
//            command = "java -jar /Users/zzsnowy/Desktop/fighting/refac/ck-0.7.1-SNAPSHOT-jar-with-dependencies.jar " +
//                    proPath +
//                    " true 0 False " +
//                    metaPath + pro + "_" + commitId + "_";
//            new ShellUtil().run(command, dir);
//        }
//
//        genFeature("classSetMc");
//        genFeature("classSetEc");
//        genFeature("classSetMm");
//        genFeature("classSetEm");

    }

    private static void genFeature(String name) throws IOException, ClassNotFoundException {
        InputStream in =  new FileInputStream( "io" + File.separator + name + ".txt" );
        ObjectInputStream os =  new  ObjectInputStream(in);
        Set<String> set = (Set<String>) os.readObject();

        List<String[]> data = new ArrayList<>();
        for (String s: set){
            System.out.println(s);
            String pro = s.split(" ")[0];
            String commitId = s.split(" ")[1];
            String c = s.split(" ")[2];
            String csvPath = "/Users/zzsnowy/Desktop/fighting/metrics/" + pro + "_" + commitId + "_class.csv";
            List<String[]> cData = new CsvUtil().readCsv(csvPath);
            for(String[] sList : cData){
                String cName = sList[1].replaceAll("\\$", ".");
                if(cName.equals(c)){
                    String[] tmp = new String[52];
                    tmp[0] = pro;
                    tmp[1] = commitId;
                    tmp[2] = c;
                    for(int i = 3; i < 52; i ++){
                        tmp[i] = sList[i];
                    }
                    data.add(tmp);
                }
            }
        }

        new CsvUtil().writeCsv(data, "0", "/Users/zzsnowy/Desktop/fighting/" + name + ".csv");

    }

    private static void minerRefOperation(List<String> proList) throws Exception {
        Set<String> classSetMc = new HashSet<>();
        Set<String> classSetEc = new HashSet<>();
        Set<String> classSetMm = new HashSet<>();
        Set<String> classSetEm = new HashSet<>();
        Set<String> proIdSet = new HashSet<>();


        GitService gitService = new GitServiceImpl();
        GitHistoryRefactoringMiner miner = new GitHistoryRefactoringMinerImpl();

        for(String pro : proList){
            String proPath = "/Users/zzsnowy/StudyDiary/MSA/graduationPro/experiment/projects/" + pro;
            File dir = new File(proPath);
            String command = "git checkout -f master";
            new ShellUtil().run(command, dir);
            Repository repo = gitService.openRepository("/Users/zzsnowy/StudyDiary/MSA/graduationPro/experiment/projects/" + pro);
            try {
                miner.detectAll(repo, "master", new RefactoringHandler() {
                    @SneakyThrows
                    @Override
                    public void handle(String commitId, List<Refactoring> refactorings) {
                        for (Refactoring ref : refactorings) {
                            if (ref.getRefactoringType() == MOVE_CLASS) {

                                MoveClassRefactoring tmp = (MoveClassRefactoring) ref;

                                String c1 = tmp.getOriginalClassName();
                                String lastCommitId = getLastCommitId(pro, commitId);

                                classSetMc.add(pro + " " + lastCommitId + " " + c1);
                                proIdSet.add(pro + " " + lastCommitId);
                            }

                            if (ref.getRefactoringType() == EXTRACT_CLASS) {

                                ExtractClassRefactoring tmp = (ExtractClassRefactoring) ref;

                                String c1 = tmp.getOriginalClass().getName();
                                String lastCommitId = getLastCommitId(pro, commitId);

                                classSetEc.add(pro + " " + lastCommitId + " " + c1);
                                proIdSet.add(pro + " " + lastCommitId);
                            }

                            if (ref.getRefactoringType() == MOVE_OPERATION) {

                                MoveOperationRefactoring tmp = (MoveOperationRefactoring) ref;

                                String c1 = tmp.getOriginalOperation().getClassName();
                                String lastCommitId;
                                lastCommitId = getLastCommitId(pro, commitId);

                                classSetMm.add(pro + " " + lastCommitId + " " + c1);
                                proIdSet.add(pro + " " + lastCommitId);
                            }

                            if (ref.getRefactoringType() == EXTRACT_OPERATION) {

                                ExtractOperationRefactoring tmp = (ExtractOperationRefactoring) ref;

                                String c1 = tmp.getSourceOperationBeforeExtraction().getClassName();
                                String lastCommitId;
                                lastCommitId = getLastCommitId(pro, commitId);

                                classSetEm.add(pro + " " + lastCommitId + " " + c1);
                                proIdSet.add(pro + " " + lastCommitId);
                            }
                        }
                    }
                });
            } catch (Exception e) {
            }
        }
        serialSet("classSetMc", classSetMc);
        serialSet("classSetEc", classSetEc);
        serialSet("classSetMm", classSetMm);
        serialSet("classSetEm", classSetEm);
        serialSet("proIdSet", proIdSet);
        for(String s: proIdSet){
            String pro = s.split(" ")[0];
            String commitId = s.split(" ")[1];

            File file = new File("/Users/zzsnowy/Desktop/fighting/metrics/" + pro + "_" + commitId + "_class.csv");
            if (file.exists()) {
                continue;
            }

            String metaPath = "/Users/zzsnowy/Desktop/fighting/metrics/";
            String proPath = "/Users/zzsnowy/StudyDiary/MSA/graduationPro/experiment/projects/" + pro;
            File dir = new File(proPath);
            String command = "git checkout -f " + commitId;
            new ShellUtil().run(command, dir);
            //生成指标数据
            command = "java -jar /Users/zzsnowy/Desktop/fighting/refac/ck-0.7.1-SNAPSHOT-jar-with-dependencies.jar " +
                    proPath +
                    " true 0 False " +
                    metaPath + pro + "_" + commitId + "_";
            new ShellUtil().run(command, dir);
        }

        genFeature("classSetMc");
        genFeature("classSetEc");
        genFeature("classSetMm");
        genFeature("classSetEm");

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

