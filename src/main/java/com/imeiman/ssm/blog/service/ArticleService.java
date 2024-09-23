package com.imeiman.ssm.blog.service;


import com.github.pagehelper.PageInfo;
import com.imeiman.ssm.blog.domain.entity.Article;
import com.imeiman.ssm.blog.domain.entity.Comment;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
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

    Integer getArticleViewCountInteger();

    Date getArticleLastUpdateTimeDate();

    Article getById(Integer articleId);

    List<Article> listArticleByViewCount(Integer limit);

    List<Article> listRandomArticle(Integer limit);

    List<Article> listByArticleLikeCount(Integer limit);

    List<Article> getArticleByTagIdList(Integer tagId);
}
