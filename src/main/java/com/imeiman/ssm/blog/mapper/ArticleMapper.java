package com.imeiman.ssm.blog.mapper;

import com.imeiman.ssm.blog.domain.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.checkerframework.checker.units.qual.A;

import java.sql.Date;
import java.util.List;

@Mapper
public interface ArticleMapper {
    List<Article> pageArticle(@Param(value = "status") Integer status,
                              @Param(value = "pageIndex") Integer pageIndex,
                              @Param(value = "pageSize")Integer pageSize);

    boolean updateById(Article article);

    Integer getArticleCount();

    Integer getAllByArticleCommentCountInteger();

    Integer getArticleViewCountInteger();

    Date getArticleLastUpdateTimeDate();

    Article getById(@Param("articleId") Integer articleId,Integer status);

    List<Article> listArticleByViewCount(Integer limit);

    List<Article> listRandomArticle(Integer limit);

    List<Article> listByArticleLikeCount(Integer limit);

    List<Article> getArticleByTagIdList(Integer tagId);

    List<Article> getArticleByCategoryIdList(Integer categoryId);

    List<Article> pageAllArticle(Integer pageIndex, Integer pageSize);

    int insertOne(Article article);
}
