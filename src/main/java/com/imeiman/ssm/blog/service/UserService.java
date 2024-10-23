package com.imeiman.ssm.blog.service;

import com.imeiman.ssm.blog.domain.entity.User;

public interface UserService {
    User getById(Integer id);

    User getUserByNameOrEmail(String username);

    Integer updateUser(User userByNameOrEmail);
}
