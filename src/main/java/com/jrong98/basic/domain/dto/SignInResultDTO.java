package com.jrong98.basic.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

/**
 * 登录结果 DTO
 * @author jrong98
 * @date 2022/6/21
 */
@Getter
@Setter
public class SignInResultDTO {

    /**
     * 客户端
     * @mock TzlCezYFznhp5j6rY9Qu5g
     */
    private String client;

    /**
     * 访问令牌
     * @mock eyJhbGciOiJIUzI1NiJ9.eyJUZW5hbnQiOiIwMDAwMDAiLCJVc2VybmFtZSI6Imtha2Fyb3R0b0BnbWFpbC5jb20iLCJJZCI6Njk0NDk4NTIxNDgwMDEwNTQ3MiwiZXhwIjoxNjU2NDE5NDY2LCJpYXQiOjE2NTU4MTQ2NjYsIkF2YXRhciI6Ii91c2VyL3BhdGgvMTQ0MzQ4Nzg0OS5wbmciLCJOaWNrbmFtZSI6IuWNoeWNoee9l-eJuSJ9.8l_KSBHjHA3M-5BHgqKnldq-XLwGMf4JYrvJHWuISS0
     */
    private String accessToken;

    /**
     * 刷新令牌
     * @ignore
     */
    @JsonIgnore
    private String refreshToken;

    /**
     * 过期时间（Unix timestamp）
     * @mock 1656604800
     */
    private Long expiry;

    /**
     * 用户id
     * @mock kakarotto@gmail.com
     */
    private String uid;

    /**
     * 令牌类型
     * @mock Bearer
     */
    private String tokenType;

}
