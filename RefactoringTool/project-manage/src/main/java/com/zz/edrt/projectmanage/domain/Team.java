package com.zz.edrt.projectmanage.domain;

import java.util.List;

public class Team {
    private String teamName;

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    private String description;
    private List<User> members;

    public Team(String teamName, String description, List<User> members) {
        this.teamName = teamName;
        this.description = description;
        this.members = members;
    }

    public void addMember(User user) {
    }

    // getter and setter methods
}
