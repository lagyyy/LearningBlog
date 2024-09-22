package com.imeiman.ssm.blog.mapper;

import com.imeiman.ssm.blog.domain.entity.Category;

import java.util.List;

public interface CategoryMapper {
    List<Category> getCategoryByArticleIdList(Integer integer);

    Integer getCount();

    List<Category> getAll();
}
