package com.zz.edrt.projectmanage.service;

public interface PermissionService {
    boolean allowMemberJoinTeam(String teamName, String username);
}
