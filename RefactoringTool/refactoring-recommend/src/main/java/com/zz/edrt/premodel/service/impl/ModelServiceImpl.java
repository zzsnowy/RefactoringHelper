package com.zz.edrt.premodel.service.impl;

import com.zz.edrt.premodel.domain.Model;
import com.zz.edrt.premodel.mapper.ModelMapper;
import com.zz.edrt.premodel.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelServiceImpl extends Model implements ModelService {
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Model> getModelList() {
        return modelMapper.selectAll();
    }

    @Override
    public String getModelPath(String model) {
        return null;
    }

    @Override
    public void insert(Model model) {

    }
}
