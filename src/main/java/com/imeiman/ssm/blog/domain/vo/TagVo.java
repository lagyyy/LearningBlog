package com.imeiman.ssm.blog.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Created with IntelliJ IDEA.
 * User: NGZ
 * Date: 2024/10/22
 * Time: 13:57
 * Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class TagVo {
    private Integer tagId;
    private String tagName;
    private Integer articleCount;
}
