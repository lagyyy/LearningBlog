package com.imeiman.ssm.blog.mapper;

import com.imeiman.ssm.blog.domain.entity.Category;
import com.imeiman.ssm.blog.domain.vo.CategoryAndArticleCount;

import java.util.ArrayList;
import java.util.List;

public interface CategoryMapper {
    List<Category> getCategoryByArticleIdList(Integer integer);

    Integer getCount();

    List<Category> getAll();

    Category getByCategoryId(Integer categoryPid);

    List<CategoryAndArticleCount> getAllAndArticleCounts();


    int deleteById(Integer categoryId);

    int insertOne(Category category);

    Category getById(Integer categoryId);

    int update(Category category);
}
