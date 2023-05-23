package com.zz.edrt.eddetect.service.impl;

import com.zz.edrt.eddetect.domain.SmellDetectRule;
import com.zz.edrt.eddetect.mapper.SmellDetectRuleMapper;
import com.zz.edrt.eddetect.service.SmellDetectService;
import com.zz.edrt.eddetect.util.ShellUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class SmellDetectServiceImpl implements SmellDetectService {

    @Autowired
    SmellDetectRuleMapper smellDetectRuleMapper;

    @Autowired
    SmellDetectRuleMapper hisSmellDetectRuleMapper;


    @Override
    public boolean detect(SmellDetectRule detectRule){
        //查询某项目某id的状态是否已是成功 若是就直接返回 但是页面上有状态如果用户看到是成功状态就不会点按钮了除非他还想重新运行一遍

        //clone project 生成细粒度历史 检测依赖
        if(!clonePro(detectRule)){
            return false;
        }
        if(!genFineHistory(detectRule)){
            return false;
        }
        return detectSmell(detectRule);
    }

    @Override
    public boolean detectByCommitId(SmellDetectRule smellDetectRule) {

        //调用mapper存储路径到数据库 - 对应历史数据的那张表
        return false;
    }


    private static boolean clonePro(SmellDetectRule smellDetectRule){
        try{
            String command = "git clone " + smellDetectRule.getUrl();
            File dir = new File("/Users/zzsnowy/edrtFolder/Project");
            ShellUtil.run(command, dir);
            return true;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean genFineHistory(SmellDetectRule detectRule){
        try {
            String command = "/Users/zzsnowy/.pyenv/versions/myenv37/bin/kenja.historage.convert " + detectRule.getName() + " ../hs/" + detectRule.getName();
            File dir = new File("/Users/zzsnowy/edrtFolder/Project");
            ShellUtil.run(command, dir);
            return true;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }
    private static boolean detectSmell(SmellDetectRule detectRule){
        try {
            String name = detectRule.getName();
            File dir = new File("/Users/zzsnowy/edrtFolder");
            String command = "cp co-change /Users/zzsnowy/edrtFolder/hs/" + name;
            ShellUtil.run(command, dir);
            if(!createAndWriteDetectShFile(detectRule)){
                return false;
            }
            ShellUtil.executeShell("/Users/zzsnowy/edrtFolder/hs/" + name + "/detect.sh", "");
            //调用mapper存储路径到数据库
            return true;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }
    private static boolean createAndWriteDetectShFile(SmellDetectRule detectRule){
        try {
            String path = "/Users/zzsnowy/edrtFolder/hs/" + detectRule.getName();
            File detectFile = new File(path + "/detect.sh");
            FileWriter writer = new FileWriter(detectFile);
            writer.write("#! /bin/bash\n");
            writer.write("cd " + path + "\n");
            writer.write("./co-change -filter \"[\\s\\S]*(MT|FE)[\\s\\S]*\" -min-confidence " + detectRule.getConfidence() + " -min-support-count "
                    + detectRule.getSupportCount() + " -output rules-and-commits > /Users/zzsnowy/edrtFolder/dependency/" + detectRule.getName() + ".txt");
            writer.close();
            return true;
        } catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }
}
