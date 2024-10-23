package com.imeiman.ssm.blog.domain.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 文章和标签关联
 *
 */
@Data
@Accessors(chain = true)
public class ArticleTagRef implements Serializable {
    private static final long serialVersionUID = -5816783232020910492L;

    private Integer articleId;

    private Integer tagId;

    public ArticleTagRef() {
    }

    public ArticleTagRef(Integer articleId, Integer tagId) {
        this.articleId = articleId;
        this.tagId = tagId;
    }
}
