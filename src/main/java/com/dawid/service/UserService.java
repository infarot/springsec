package com.dawid.service;

import com.dawid.crm.UserCrm;
import com.dawid.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findByUserName(String userName);

    void save(UserCrm userCrm);
}
