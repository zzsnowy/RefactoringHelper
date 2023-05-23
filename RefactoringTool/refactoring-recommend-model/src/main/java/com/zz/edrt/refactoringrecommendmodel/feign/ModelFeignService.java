package com.zz.edrt.refactoringrecommendmodel.feign;

import com.zz.edrt.premodel.domain.Model;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
@Component
@FeignClient("refactoring-recommend")
public interface ModelFeignService {
    @PostMapping("/addModel")
    public boolean addModel(@RequestBody Model model);
}
