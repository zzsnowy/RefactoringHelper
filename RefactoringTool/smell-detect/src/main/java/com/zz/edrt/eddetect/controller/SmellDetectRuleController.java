package com.zz.edrt.eddetect.controller;

import com.zz.edrt.eddetect.domain.CommonResult;
import com.zz.edrt.eddetect.domain.SmellDetectRule;
import com.zz.edrt.eddetect.service.SmellDetectRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/dependency/detect/rule")
public class SmellDetectRuleController {

    @Autowired
    private SmellDetectRuleService smellDetectRuleService;

    @GetMapping("/main")
    public List<SmellDetectRule> viewMain() {
        return smellDetectRuleService.getRuleList();
    }
    @PostMapping("/update")
    public CommonResult<Boolean> updateRuleByPrimaryKey(@RequestBody SmellDetectRule smellDetectRule) {
        return CommonResult.success(smellDetectRuleService.updateByPrimaryKey(smellDetectRule));
    }

    @GetMapping("/select")
    public SmellDetectRule selectByPrimaryKey(String name){
        return smellDetectRuleService.selectByPrimaryKey(name);
    }
}
