package com.zz.edrt.premodel.service;

import com.zz.edrt.premodel.domain.Model;

import java.util.List;

public interface ModelService {
    public List<Model> getModelList();

    String getModelPath(String model);

    void insert(Model model);
}
