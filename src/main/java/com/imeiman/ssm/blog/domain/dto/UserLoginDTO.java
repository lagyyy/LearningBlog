package com.imeiman.ssm.blog.domain.dto;

import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: NGZ
 * Date: 2024/9/24
 * Time: 11:46
 * Description:
 */

@Data
public class UserLoginDTO {
    private String userLastLoginIp;

    private Date userRegisterTime;

    private Date userLastLoginTime;

    private Integer userId;
}
