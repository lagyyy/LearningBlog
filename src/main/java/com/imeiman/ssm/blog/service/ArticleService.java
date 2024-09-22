package com.imeiman.ssm.blog.service;


import com.github.pagehelper.PageInfo;
import com.imeiman.ssm.blog.domain.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface ArticleService {
    PageInfo<Article> pageArticle(Integer status,
                              Integer pageIndex,
                              Integer pageSize);

    PageInfo<Article> pageArticle(Integer pageIndex,
                                  Integer pageSize,
                                  HashMap<String, Object> criteria);

    Integer getArticleCount();

    Integer getAllByArticleCommentCountInteger();
}
