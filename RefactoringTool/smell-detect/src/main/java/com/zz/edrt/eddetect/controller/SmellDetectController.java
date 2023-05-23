package com.zz.edrt.eddetect.controller;

import com.zz.edrt.eddetect.domain.CommonResult;
import com.zz.edrt.eddetect.domain.SmellDetectRule;
import com.zz.edrt.eddetect.service.SmellDetectRuleService;
import com.zz.edrt.eddetect.service.SmellDetectService;
import com.zz.edrt.eddetect.service.SmellFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@RequestMapping("/dependency/detect")
public class SmellDetectController {
    @Autowired
    private SmellDetectService smellDetectService;
    @Autowired
    private SmellDetectRuleService smellDetectRuleService;

    @Autowired
    private SmellFileService smellFileService;

    @PostMapping("/")
    public CommonResult<Boolean> detect(@RequestBody SmellDetectRule smellDetectRule) throws IOException {
        return CommonResult.success(smellDetectService.detect(smellDetectRule));
    }

    @PostMapping("/commitId")
    public CommonResult<Boolean> detectByCommitId(@RequestBody SmellDetectRule smellDetectRule, String commitId) throws IOException {
        return CommonResult.success(smellDetectService.detectByCommitId(smellDetectRule));
    }

    @PostMapping("/update")
    public CommonResult<Boolean> updateByPrimaryKey(@RequestBody SmellDetectRule smellDetectRule){
        return CommonResult.success(smellDetectRuleService.updateByPrimaryKey(smellDetectRule));
    }

    @PostMapping("/downloadFile")
    public ResponseEntity<Resource> downloadFile(@RequestBody SmellDetectRule smellDetectRule) {
        Resource resource = smellFileService.loadFileAsResource(smellDetectRule);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + smellDetectRule.getName()+ "\"")
                .body(resource);
    }

    @PostMapping("/viewFile")
    public ResponseEntity<Resource> viewFile(@RequestBody SmellDetectRule smellDetectRule) {
        Resource resource = smellFileService.loadFileAsResource(smellDetectRule);
        String contentType = MediaType.APPLICATION_PDF_VALUE; // 文件类型

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION)
                .body(resource);
    }

    @PostMapping("/path")
    public CommonResult<String> getFilePath(@RequestBody SmellDetectRule smellDetectRule) {
        return CommonResult.success(smellFileService.getFilePath(smellDetectRule));
    }

}
