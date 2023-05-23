package com.zz.edrt.projectmanage.service.impl;

import com.zz.edrt.projectmanage.domain.Team;
import com.zz.edrt.projectmanage.domain.User;
import com.zz.edrt.projectmanage.service.PermissionService;
import com.zz.edrt.projectmanage.service.TeamManagerService;
import com.zz.edrt.projectmanage.service.UserManagerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.HttpURLConnection;
import java.net.URL;

public class PermissionServiceImpl implements PermissionService {

    @Autowired
    TeamManagerService teamManagerService;

    @Autowired
    UserManagerService userManagerService;
    @Override
    public boolean allowMemberJoinTeam(String teamName, String username) {
        //判断团队中是否存在该成员
        Team teamTemp = teamManagerService.selectByName(teamName);
        User user = userManagerService.selectByName(username);
        if(teamTemp.getMembers().contains(user)){
            return false;
        }
        teamTemp.addMember(user);
        return true;
    }
}
////验证代码仓地址是否有效
//public boolean isGitRepoValid(String gitRepoUrl) {
//    try {
//        URL url = new URL(gitRepoUrl);
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//        connection.setRequestMethod("HEAD");
//        int responseCode = connection.getResponseCode();
//        if (responseCode == HttpURLConnection.HTTP_OK) {
//            String contentType = connection.getContentType();
//            if (contentType != null && contentType.startsWith("application/x-git-receive-pack")) {
//                return true;
//            }
//        }
//    } catch (Exception e) {......}
//    return false;
//}
//@Override
//public boolean allowMemberJoinTeam(String teamName, String username) {
//    //判断团队中是否存在该成员
//    ......
//    if(teamTemp.getMembers().contains(user)){
//        return false;
//    }
//    //添加成员
//    teamTemp.addMember(user);
//    return true;
//}