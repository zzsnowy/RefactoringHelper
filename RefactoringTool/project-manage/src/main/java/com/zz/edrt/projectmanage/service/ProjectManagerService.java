package com.zz.edrt.projectmanage.service;

import com.zz.edrt.edrtcommon.Project;

import java.util.List;


public interface ProjectManagerService {

    public List<Project> getProjectList();

    public boolean insertProject(Project project);

    public boolean deleteProjectByPrimaryKey(String name);

    public boolean updateByPrimaryKey(Project project);

}
