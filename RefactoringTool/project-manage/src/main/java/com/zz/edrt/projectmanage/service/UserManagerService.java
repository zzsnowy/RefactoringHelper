package com.zz.edrt.projectmanage.service;

import com.zz.edrt.projectmanage.domain.User;

public interface UserManagerService {
    User selectByName(String username);
}
