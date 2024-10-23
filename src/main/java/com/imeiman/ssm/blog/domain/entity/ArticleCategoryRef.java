package com.imeiman.ssm.blog.domain.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 文章分类关联表
 *
 */
@Data
@Accessors(chain = true)
public class ArticleCategoryRef implements Serializable{

    private static final long serialVersionUID = -6809206515467725995L;

    private Integer articleId;

    private Integer categoryId;

    public ArticleCategoryRef() {
    }

    public ArticleCategoryRef(Integer articleId, Integer categoryId) {
        this.articleId = articleId;
        this.categoryId = categoryId;
    }
}