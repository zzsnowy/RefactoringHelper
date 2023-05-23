package com.zz.edrt.dataextract.service.impl;

import com.zz.edrt.dataextract.feign.EdDetectFeignServeice;
import com.zz.edrt.dataextract.label.Label;
import com.zz.edrt.dataextract.service.DataPreService;
import com.zz.edrt.dataextract.service.FeatureExtractService;
import com.zz.edrt.eddetect.domain.SmellDetectRule;
import com.zz.edrt.edrtcommon.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataPreServiceImpl implements DataPreService {

    @Autowired
    EdDetectFeignServeice edDetectFeignServeice;
    @Autowired
    Label label;

    @Autowired
    FeatureExtractService featureExtractService;

    @Override
    public boolean prepare(SmellDetectRule smellDetectRule) throws Exception {
        //获取项目所有commitID
        List<String> hisCommits = getHisCommitID(smellDetectRule.getName());
        //调用label的minerdetect先挖掘（然后获取有效的commitId - 先挖掘，只有发生过重构操作才是有效的，留下）

        //下面应该是用有效的commitId生成依赖

        //生成历史依赖依赖
        for(String s : hisCommits){
            edDetectFeignServeice.detectByCommitId(smellDetectRule, s);
        }
        //标注
        label.labelAndClean(smellDetectRule.getName());
        //特征提取
        featureExtractService.genFeature(new Project(smellDetectRule.getName(), smellDetectRule.getUrl()));
        //存储路径、状态、有效的commit文件的地址
        return false;
    }

    @Override
    public String getFeatureFilePath(Project project) {
        return null;
    }

    private List<String> getHisCommitID(String name) {
        return new ArrayList<>();
    }
}

