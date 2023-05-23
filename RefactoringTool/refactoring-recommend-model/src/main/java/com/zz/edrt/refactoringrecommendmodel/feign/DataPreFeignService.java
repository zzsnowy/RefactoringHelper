package com.zz.edrt.refactoringrecommendmodel.feign;

import com.zz.edrt.edrtcommon.Project;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient("data-extract")
public interface DataPreFeignService {

    @RequestMapping("/data/extract/path")
    public String getFeatureFilePath(@RequestParam Project project);
}
