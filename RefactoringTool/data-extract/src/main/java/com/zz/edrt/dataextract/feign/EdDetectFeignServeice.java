package com.zz.edrt.dataextract.feign;

import com.zz.edrt.eddetect.domain.SmellDetectRule;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@FeignClient("ed-detect")
public interface EdDetectFeignServeice {

    @RequestMapping("/dependency/detect/")
    public boolean detect(@RequestBody SmellDetectRule smellDetectRule);

    public boolean detectByCommitId(SmellDetectRule smellDetectRule, String commitId);
}


