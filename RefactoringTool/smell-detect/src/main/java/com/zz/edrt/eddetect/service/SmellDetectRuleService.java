package com.zz.edrt.eddetect.service;

import com.zz.edrt.eddetect.domain.SmellDetectRule;

import java.util.List;

public interface SmellDetectRuleService {
    public List<SmellDetectRule> getRuleList();

    public boolean updateByPrimaryKey(SmellDetectRule smellDetectRule);

    public SmellDetectRule selectByPrimaryKey(String name);
}
