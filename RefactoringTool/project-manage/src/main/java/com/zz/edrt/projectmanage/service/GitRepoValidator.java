package com.zz.edrt.projectmanage.service;

import java.net.HttpURLConnection;
import java.net.URL;

public class GitRepoValidator {

    public static boolean isGitRepoValid(String gitRepoUrl) {
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

//    public static void main(String[] args) {
//        String gitRepoUrl = "https://github.com/openai/triton.git";
//        boolean isValid = isGitRepoValid(gitRepoUrl);
//        System.out.println(isValid);
//    }
}
