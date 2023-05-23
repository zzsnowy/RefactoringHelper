package com.zz.edrt.timing;

import com.zz.edrt.timing.feign.DataExtractFeignService;

public class GenDataTask implements Task{

    DataExtractFeignService dataExtractFeignService;
    @Override
    public void execute(String projectName) {

    }
}
