package com.zz.edrt.dataextract.label;


import org.refactoringminer.api.GitHistoryRefactoringMiner;
import org.refactoringminer.api.GitService;
import org.refactoringminer.rm1.GitHistoryRefactoringMinerImpl;
import org.refactoringminer.util.GitServiceImpl;
import org.eclipse.jgit.lib.Repository;
import org.springframework.stereotype.Service;


import java.io.*;
import java.util.*;

@Service
public class Label {


    public void labelAndClean(String pro) throws Exception {
        String dpathname = "/Users/zzsnowy/StudyDiary/MSA/graduationPro/experiment/dependencies/" + pro;

        System.out.println("请选择是否从文件读取：1.是 2.否");
        Scanner input = new Scanner(System.in);
        int k = input.nextInt();
        //int k = 2;

        File writename = new File("/Users/zzsnowy/StudyDiary/MSA/graduationPro/experiment/MoveRefCommitId/" + pro + ".txt");
        writename.createNewFile();
        BufferedWriter out = new BufferedWriter(new FileWriter(writename));

        File writenameDep = new File("/Users/zzsnowy/StudyDiary/MSA/graduationPro/experiment/MoveRefAtDepCommitId/" + pro + ".txt");
        writenameDep.createNewFile();
        BufferedWriter outDep = new BufferedWriter(new FileWriter(writenameDep));

        if(k == 1){

            InputStream in =  new  FileInputStream( "io" + File.separator + pro + "map.txt" );
            ObjectInputStream os =  new  ObjectInputStream(in);
            Map<String, List<String>[]> dClassifyMap = (Map<String, List<String>[]>) os.readObject();
            os.close();

            labelDependency(pro, out, outDep, dpathname, dClassifyMap);
        } else if(k == 2){

            GitService gitService = new GitServiceImpl();
            GitHistoryRefactoringMiner miner = new GitHistoryRefactoringMinerImpl();

            Repository repo = gitService.cloneIfNotExists(
                    "/Users/zzsnowy/StudyDiary/MSA/graduationPro/experiment/projects/" + pro,
                    "");

            String pathname = "/Users/zzsnowy/StudyDiary/MSA/graduationPro/experiment/commitId/" + pro + "/" + pro + ".txt";
            File filename = new File(pathname);
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(filename));
            BufferedReader br = new BufferedReader(reader);
            String line = "";
            line = br.readLine();

            Map<String, List<String>[]> dependencyClassifyMap = new HashMap<>();
            while (line != null) {
                String coarse = line.split(" ")[0];
                String fine = line.split(" ")[1];
                String lastFineCommitId = line.split(" ")[2];
                try {
                    miner.detectAtCommit(repo, coarse, new MoveRefactoringHandler(fine, lastFineCommitId, dependencyClassifyMap) {});

                } catch (Exception e) {
                }
                line = br.readLine();
            }

            removeMap(dependencyClassifyMap);

            //序列化
            try (OutputStream op =  new  FileOutputStream( "io" + File.separator + pro +"map.txt" );
                 ObjectOutputStream ops =  new  ObjectOutputStream(op);) {
                ops.writeObject(dependencyClassifyMap);
            }

            labelDependency(pro, out, outDep, dpathname, dependencyClassifyMap);

        } else {
            System.out.println("输入有误！");
        }

        out.flush(); // 把缓存区内容压入文件
        out.close(); // 关闭文件

        outDep.flush(); // 把缓存区内容压入文件
        outDep.close(); // 关闭文件
    }

    private static void removeMap(Map<String, List<String>[]> dependencyClassifyMap) {
        List<String> r = new ArrayList<>();
        for (Map.Entry<String, List<String>[]> entry : dependencyClassifyMap.entrySet()){
            List<String> listMM = entry.getValue()[0];
            List<String> listMF = entry.getValue()[1];
            if(listMM.isEmpty() && listMF.isEmpty()){
                r.add(entry.getKey());
            }
        }
        for(String s : r){
            dependencyClassifyMap.remove(s);
//            logger.info("{}被移除", s);
        }


    }


    private static void labelDependency(String pro, BufferedWriter out, BufferedWriter outDep, String dpathname, Map<String, List<String>[]> dependencyClassifyMap) throws IOException {

        for (Map.Entry<String, List<String>[]> entry : dependencyClassifyMap.entrySet()) {

            String commitId = entry.getKey();
            List<String> moveField = new ArrayList<>();
            List<String> moveMethod = new ArrayList<>();
            List<String> noLabel = new ArrayList<>();

//            logger.info("正在处理{}", commitId);
            out.write(commitId + "\n");
            List<String> listMM = entry.getValue()[0];
            List<String> listMF = entry.getValue()[1];
            String pathname = dpathname + "/" + pro + "_" + commitId + ".txt";

            File filename = new File(pathname);

            if (!filename.exists()){
//                logger.info("pro:{}, {}, 依赖文件不存在！", pro, commitId);
                continue;
            }
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(filename));
            BufferedReader br = new BufferedReader(reader);
            String line = "";
            line = br.readLine();
            boolean f = false;
            while (line != null) {
                boolean flag = false;
                for(String mm : listMM){
                    if (line.matches(mm)){
                        f = true;
                        flag = true;
                        moveMethod.add(line);
                        System.out.println("MM:" + line);
                    }
                }

                for(String mf : listMF){
                    if (line.matches(mf)){
                        f = true;
                        flag = true;
                        moveField.add(line);
                        System.out.println("MF:" + line);
                    }
                }
                if(!flag){
                    noLabel.add(line);
                }
                line = br.readLine();
            }
            if(f){
                DependencyHandler dependencyHandler = new DependencyHandler(pro, commitId, moveMethod, moveField, noLabel, outDep);
                dependencyHandler.handle();
            }

        }
    }

}
