package com.zz.edrt.projectmanage.service;

import com.zz.edrt.projectmanage.domain.Team;

public interface TeamManagerService {
    Team selectByName(String teamName);
}
