package com.zz.edrt.eddetect.service.impl;

import com.zz.edrt.eddetect.domain.SmellDetectRule;
import com.zz.edrt.eddetect.feign.ProjectFeignService;
import com.zz.edrt.eddetect.mapper.SmellDetectRuleMapper;
import com.zz.edrt.eddetect.service.SmellDetectRuleService;
import com.zz.edrt.edrtcommon.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SmellDetectRuleServiceImpl implements SmellDetectRuleService {
    @Autowired
    private ProjectFeignService projectFeignService;

    @Autowired
    SmellDetectRuleMapper smellDetectRuleMapper;
    @Override
    public List<SmellDetectRule> getRuleList() {

        List<Project> projectList = projectFeignService.getProjectList();
        List<SmellDetectRule> ruleList = new ArrayList<>();
        for (Project pro : projectList){
            SmellDetectRule rule = smellDetectRuleMapper.selectByPrimaryKey(pro.getName());
            if(rule != null){
                ruleList.add(rule);
                continue;
            }
            SmellDetectRule tmpRule = new SmellDetectRule();
            tmpRule.setName(pro.getName());
            tmpRule.setUrl(pro.getUrl());
            ruleList.add(tmpRule);
        }
        return ruleList;
    }

    @Override
    public boolean updateByPrimaryKey(SmellDetectRule smellDetectRule) {
        SmellDetectRule smellDetectRule1 = smellDetectRuleMapper.selectByPrimaryKey(smellDetectRule.getName());
        if(smellDetectRule1 != null){
            return smellDetectRuleMapper.updateByPrimaryKey(smellDetectRule) > 0;
        }
        return smellDetectRuleMapper.insert(smellDetectRule) > 0;
    }

    @Override
    public SmellDetectRule selectByPrimaryKey(String name) {
        return null;
    }

}
