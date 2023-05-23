package com.zz.edrt.projectmanage.controller;

import com.zz.edrt.edrtcommon.Project;
import com.zz.edrt.projectmanage.service.ProjectManagerService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectManagerController {

    @Autowired
    private ProjectManagerService projectManagerService;

    @GetMapping("/main")
    public List<Project> viewMain() {
        return projectManagerService.getProjectList();
    }

    @PostMapping("/add")
    public boolean addProject(@RequestBody Project project) {
        return projectManagerService.insertProject(project);
    }

    @DeleteMapping("delete/{name}")
    public boolean deleteProjectById(@PathVariable("name") String name) {
        return projectManagerService.deleteProjectByPrimaryKey(name);
    }

    @PostMapping("/update")
    public boolean updateProjectByPrimaryKey(@RequestBody Project project) {
        return projectManagerService.updateByPrimaryKey(project);
    }

}