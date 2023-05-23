package com.zz.edrt.premodel.feign;

import com.zz.edrt.edrtcommon.Project;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient("data-extract")
public interface DataExtractFeignService {

    @RequestMapping("/data/prepare/path")
    public String getFilePath(@RequestParam Project project);
}
