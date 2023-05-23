package com.zz.edrt.projectmanage.domain;

public class User {
    private String username;
    private String password;
    private String description;
    private boolean isTeamMember;
    private boolean isTeamManager;
    private String teamName;
    private String publishScope;

    public User(String username, String password, String description, boolean isTeamMember, boolean isTeamManager, String teamName, String publishScope) {
        this.username = username;
        this.password = password;
        this.description = description;
        this.isTeamMember = isTeamMember;
        this.isTeamManager = isTeamManager;
        this.teamName = teamName;
        this.publishScope = publishScope;
    }

    public User() {

    }

    // getter and setter methods
}
