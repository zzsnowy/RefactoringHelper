package com.zz.edrt.eddetect.feign;

import com.zz.edrt.edrtcommon.Project;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Component
@FeignClient("project-manage")
public interface ProjectFeignService {
    @RequestMapping("/project/main")
    public List<Project> getProjectList();
}
