package com.zz.edrt.premodel.controller;

import com.zz.edrt.edrtcommon.Project;
import com.zz.edrt.premodel.domain.Metric;
import com.zz.edrt.premodel.domain.Model;
import com.zz.edrt.premodel.service.ModelMetricsService;
import com.zz.edrt.premodel.service.ModelService;
import com.zz.edrt.premodel.service.RefRecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;


//这个类太臃肿了，可以把模型、指标分别放到各自的controller中
@RestController
@RequestMapping("/model")
public class RefRecommendController {
    @Autowired
    private ModelService modelService;

    @Autowired
    private RefRecommendService refRecommendService;

    @Autowired
    private ModelMetricsService modelMetricsService;

    @GetMapping("/main")
    public List<Model> viewMain() {
        return modelService.getModelList();
    }

    @RequestMapping("/ref")
    public boolean refByProAndModel(@RequestParam Project project, @RequestParam String model) throws IOException, InterruptedException {
        return refRecommendService.getRecommendations(project, model);
    }

    @RequestMapping("/downloadFile")
    public ResponseEntity<Resource> downloadFile(@RequestParam Project project, @RequestParam String model) {
        Resource resource = refRecommendService.loadFileAsResource(project, model);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + project.getName() + "\"")
                .body(resource);
    }

    @RequestMapping("/viewFile")
    public ResponseEntity<Resource> viewFile(@RequestParam Project project, @RequestParam String model) {
        Resource resource = refRecommendService.loadFileAsResource(project, model);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "image/png")
                .body(resource);
    }

    @GetMapping("/metric")
    public List<Metric> viewMetrics(){
        return modelMetricsService.getMetricsList();
    }

    @PostMapping("/addModel")
    public boolean addModel(@RequestBody Model model){
        modelService.insert(model);
        return false;
    }


}
