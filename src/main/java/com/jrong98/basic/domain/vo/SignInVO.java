package com.jrong98.basic.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * 登录
 * @author jrong98
 * @date 2022/6/21
 */
@Getter
@Setter
public class SignInVO {

    /**
     * 租户id
     * @mock 000000
     */
    @NotEmpty(message = "The [ tenantId ] is required.")
    private String tenantId;

    /**
     * 登录邮箱
     * @mock kakarotto@gmail.com
     */
    @NotEmpty(message = "The [ email ] is required.")
    private String email;

    /**
     * 登录秘密
     * @required
     * @mock 1B8F6B0008D91BA52EED8A7571CFB631
     */
    @NotEmpty(message = "The [ password ] is required.")
    private String password;

    /**
     * 验证码
     * @mock 66375
     */
    private String verifyCode;

    /**
     * 请求ip
     * @mock 8.8.8.8
     * @ignore
     */
    @JsonIgnore
    private String requestIp;

}
