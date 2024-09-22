package com.imeiman.ssm.blog.service;

import com.imeiman.ssm.blog.domain.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    Integer getCount();

    List<Category> getAll();

    List<Category> getCategoryByArticleIdList(Integer integer);
}
