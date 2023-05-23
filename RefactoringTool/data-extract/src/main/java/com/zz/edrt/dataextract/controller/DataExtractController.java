package com.zz.edrt.dataextract.controller;

import com.zz.edrt.dataextract.service.DataExtractService;
import com.zz.edrt.edrtcommon.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/data/extract")
public class DataExtractController {
    @Autowired
    private DataExtractService dataExtractService;

    @PostMapping("/")
    public boolean extract(@RequestBody Project project) throws IOException {
        return dataExtractService.detect(project);
    }

    @RequestMapping("/path")
    public String getFilePath(@RequestParam Project project) {
        return dataExtractService.getFilePath(project);
    }
}
