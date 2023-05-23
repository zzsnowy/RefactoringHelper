package com.zz.edrt.timing;

import com.zz.edrt.timing.feign.EdDetectFeignServeice;
import org.springframework.beans.factory.annotation.Autowired;

public class DetectTask implements Task{

    @Autowired
    EdDetectFeignServeice edDetectFeignServeice;
    @Override
    public void execute(String projectName) {
        //先查出规则
        //再携带规则访问detect执行
    }
}
