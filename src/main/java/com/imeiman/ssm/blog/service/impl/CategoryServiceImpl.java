package com.imeiman.ssm.blog.service.impl;

import com.imeiman.ssm.blog.mapper.CategoryMapper;
import com.imeiman.ssm.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public Integer getCount() {
        Integer count = categoryMapper.getCount();
        return count;
    }
}
