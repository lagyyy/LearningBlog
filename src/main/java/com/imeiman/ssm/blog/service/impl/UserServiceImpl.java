package com.imeiman.ssm.blog.service.impl;

import com.imeiman.ssm.blog.domain.entity.User;
import com.imeiman.ssm.blog.mapper.UserMapper;
import com.imeiman.ssm.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: NGZ
 * Date: 2024/9/22
 * Time: 19:35
 * Description:
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getById(Integer id) {
        return userMapper.getById(id);
    }
}
