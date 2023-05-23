package com.zz.edrt.timing;

import com.zz.edrt.timing.feign.DataPreFeignService;

public class PreDataTask implements Task{

    DataPreFeignService dataPreFeignService;
    @Override
    public void execute(String projectName) {

    }
}
