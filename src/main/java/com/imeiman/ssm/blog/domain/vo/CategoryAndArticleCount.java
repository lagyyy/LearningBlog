package com.imeiman.ssm.blog.domain.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created with IntelliJ IDEA.
 * User: NGZ
 * Date: 2024/10/22
 * Time: 15:32
 * Description:
 */

@Data
@Accessors(chain = true)
public class CategoryAndArticleCount {
    private Integer categoryId;

    private Integer categoryPid;

    private String categoryName;

    private String categoryDescription;

    private Integer categoryOrder;

    private String categoryIcon;

    private Integer articleCount;
}
