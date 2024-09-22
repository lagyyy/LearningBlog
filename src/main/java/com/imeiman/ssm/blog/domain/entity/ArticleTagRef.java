package com.imeiman.ssm.blog.domain.entity;

import lombok.Data;

@Data
public class ArticleTagRef {
    private Integer articleId;
    private Integer tagId;
}
