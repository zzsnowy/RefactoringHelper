package com.zz.edrt.premodel.service.impl;

import com.zz.edrt.edrtcommon.Project;
import com.zz.edrt.premodel.domain.Metric;
import com.zz.edrt.premodel.feign.DataExtractFeignService;
import com.zz.edrt.premodel.service.ModelService;
import com.zz.edrt.premodel.service.RefRecommendService;
import com.zz.edrt.premodel.util.ShellUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class RefRecommendServiceImpl implements RefRecommendService {

    @Autowired
    DataExtractFeignService dataExtractFeignService;

    @Autowired
    private ModelService modelService;

    @Override
    @Async
    public boolean getRecommendations(Project project, String model) throws IOException, InterruptedException {

        //先去查某某项目某某ID时某某模型是否获取过了 但是页面上有状态如果用户看到是成功状态就不会点按钮了除非他还想重新运行一遍 所以这一步不需要


        //代理模式

        //访问数据获取微服务，拿到数据文件路径。
        String path = dataExtractFeignService.getFilePath(project);

        //获得模型文件路径
        String mPath = modelService.getModelPath(model);

        //路径、模型文件的路径名称作为参数传递给python脚本
        String pyRefPath = "....../PycharmProjects/evolutionary-dependency-refactoring-prediction";
        new ShellUtil(). run("python predict_ref.py " + path + " " + mPath, new File(pyRefPath));

        //访问临时文件,并将计算结果转成某某依赖对应的某某重构操作的形式
        if (!readAndHandleFile(pyRefPath + "temp")){
            return false;
        }

        //生成最终的重构建议文件，并将项目、commitId、重构推荐文件路径等信息写入数据库。
        if (!processRecommendations("targetPath", pyRefPath + "temp")){
            return false;
        }

        return true;
    }

    @Override
    public Resource loadFileAsResource(Project project, String model) {
        return null;
    }

    @Override
    public List<Metric> getMetricsList() {
        return null;
    }

    private boolean processRecommendations(String path, String tempPath) {

        return false;
    }

    private boolean readAndHandleFile(String tempPath) {
        return false;
    }
}
