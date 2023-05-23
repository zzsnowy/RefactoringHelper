package com.zz.edrt.dataextract.controller;

import com.zz.edrt.dataextract.service.DataPreService;
import com.zz.edrt.eddetect.domain.SmellDetectRule;
import com.zz.edrt.edrtcommon.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/data/prepare")
public class DataPreController {
    @Autowired
    private DataPreService dataPreService;

    @PostMapping("/prepare")
    public boolean preparet(@RequestBody List<SmellDetectRule> smellDetectRules) throws Exception {

        //遍历
        boolean res;
        for(SmellDetectRule rule : smellDetectRules){
            res = prepareByProject(rule);
            if(!res){
                return false;
            }
        }
        return true;
    }

    @PostMapping("/prepareByProject")
    public boolean prepareByProject(@RequestBody SmellDetectRule smellDetectRule) throws Exception {
        return dataPreService.prepare(smellDetectRule);
    }

    @RequestMapping("/path")
    public String getFeatureFilePath(@RequestParam Project project) {
        return dataPreService.getFeatureFilePath(project);
    }
}
