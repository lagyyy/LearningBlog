package com.imeiman.ssm.blog.domain.dto;

import com.imeiman.ssm.blog.domain.entity.Category;
import com.imeiman.ssm.blog.domain.entity.Tag;
import com.imeiman.ssm.blog.domain.entity.User;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: NGZ
 * Date: 2024/9/25
 * Time: 10:18
 * Description:
 */
@Data
public class ArticleEditDTO {
    private Integer articleId;

    private String articleTitle;

    private String articleContent;

    private Integer articleParentCategoryId;

    private Integer articleChildCategoryId;

    private Integer articleOrder;

    private Integer articleStatus;

    private String articleThumbnail;

    private List<Integer> articleTagIds;
}
