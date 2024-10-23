package com.imeiman.ssm.blog.mapper;

import com.imeiman.ssm.blog.domain.entity.ArticleCategoryRef;
import com.imeiman.ssm.blog.domain.entity.ArticleTagRef;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: NGZ
 * Date: 2024/9/26
 * Time: 15:26
 * Description:
 */

@Mapper
public interface ArticleCategoryRefMapper {
    Integer deleteByArticleId(Integer articleId);

    // 批量插入用户
    int insertBatch(List<ArticleCategoryRef> articleTagRefs);


    int deleteByIds(ArrayList<Integer> ids);
}
