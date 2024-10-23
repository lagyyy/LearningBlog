package com.imeiman.ssm.blog.mapper;

import com.imeiman.ssm.blog.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User getById(Integer id);

    User getUserByNameOrEmail(String username);

    Integer updateUser(User userByNameOrEmail);
}
