package com.jrong98.basic.domain.vo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * 用户密码 VO
 * @author jrong98
 * @date 2022/6/23
 */
@Getter
@Setter
public class UserPasswdVO {

    /**
     * 用户密码
     * @mock 1B8F6B0008D91BA52EED8A7571CFB631
     */
    @NotEmpty(message = "The [ password ] is required.")
    private String password;

    /**
     * 终止用户令牌
     * @mock false
     */
    private Boolean terminateUserToken;
}
