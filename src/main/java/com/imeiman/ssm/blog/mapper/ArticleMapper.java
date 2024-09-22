package com.imeiman.ssm.blog.mapper;

import com.imeiman.ssm.blog.domain.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.List;

@Mapper
public interface ArticleMapper {
    List<Article> pageArticle(@Param(value = "status") Integer status,
                              @Param(value = "pageIndex") Integer pageIndex,
                              @Param(value = "pageSize")Integer pageSize);

    Integer getArticleCount();

    Integer getAllByArticleCommentCountInteger();

    Integer getArticleViewCountInteger();

    Date getArticleLastUpdateTimeDate();

    Article getById(Integer articleId);
}
