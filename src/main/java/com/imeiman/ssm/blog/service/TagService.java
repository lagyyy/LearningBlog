package com.imeiman.ssm.blog.service;

import com.imeiman.ssm.blog.domain.entity.Tag;

import java.util.List;

public interface TagService {
    List<Tag> getAllTags();

    Integer getTagCount();

    List<Tag> getArticleTagById(Integer id);
}
