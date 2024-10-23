package com.imeiman.ssm.blog.mapper;

import com.imeiman.ssm.blog.domain.entity.ArticleTagRef;

import java.util.List;

/**
* @author ngz
* @description 针对表【article_tag_ref】的数据库操作Mapper
* @createDate 2024-10-18 11:27:37
* @Entity generator.domain.ArticleTagRef
*/
public interface ArticleTagRefMapper {
    Integer deleteByArticleId(Integer articleId);

    int insertBatch(List<ArticleTagRef> articleTagRefs);
}




