package com.zz.edrt.premodel.controller;

import com.zz.edrt.premodel.domain.Model;
import com.zz.edrt.premodel.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/model")
public class ModelController {

    @Autowired
    private ModelService modelService;

//    @GetMapping("/main")
//    public List<Model> viewMain() {
//        return modelService.getModelList();
//    }

}