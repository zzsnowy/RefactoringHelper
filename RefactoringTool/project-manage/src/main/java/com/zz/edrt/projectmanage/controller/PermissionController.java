package com.zz.edrt.projectmanage.controller;

import com.zz.edrt.projectmanage.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;

public class PermissionController {

    @Autowired
    PermissionService permissionService;

    public boolean allowMemberJoinTeam(String teamName, String username) {

        return permissionService.allowMemberJoinTeam(teamName, username);
    }

    public boolean assignTeamManager(String teamName, String username) {
        return false;
    }

    public boolean assignModelPublishPermission(String teamName, String username, String publishScope) {
        return false;
    }

}
