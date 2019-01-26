package com.dawid.dao;

import com.dawid.entity.User;

public interface UserDAO {
    User findByUserName(String userName);

    void save(User user);
}
