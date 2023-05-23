package com.zz.edrt.projectmanage.service.impl;

import com.zz.edrt.edrtcommon.Project;
import com.zz.edrt.projectmanage.mapper.ProjectMapper;
import com.zz.edrt.projectmanage.service.ProjectManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Service
public class ProjectManagerServiceImpl implements ProjectManagerService {

    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public List<Project> getProjectList() {
        return projectMapper.selectAll();
    }

    @Override
    public boolean insertProject(Project project) {
        System.out.println(project.toString());
        return projectMapper.insert(project) > 0;
    }

    @Override
    public boolean deleteProjectByPrimaryKey(String name) {
        return projectMapper.deleteByPrimaryKey(name) > 0;
    }

    public boolean isGitRepoValid(String gitRepoUrl) {
        try {
            URL url = new URL(gitRepoUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String contentType = connection.getContentType();
                if (contentType != null && contentType.startsWith("application/x-git-receive-pack")) {
                    return true;
                }
            }
        } catch (Exception e) {
            // handle exception
        }
        return false;
    }

    @Override
    public boolean updateByPrimaryKey(Project project) {
        return projectMapper.updateByPrimaryKey(project) > 0;
    }

}
