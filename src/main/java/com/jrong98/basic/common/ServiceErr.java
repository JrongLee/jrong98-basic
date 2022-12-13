package com.jrong98.basic.common;

import lombok.Getter;

/**
 * 服务异常错误码
 * <ul>
 *     <li>1-100000 预留值</li>
 *     <li>每隔一个业务块加 1000</li>
 * </ul>
 * @author jrong98
 * @date 2022/6/21
 */
public enum ServiceErr {

    /** 参数验证失败 */
    SE4000(4000),
    /** 内部错误 */
    SE5000(5000),
    /** 未授权的 */
    SE4001(4001),

    /** 登录错误; 错误的用户名或者密码 */
    SIGN_IN_ERROR__INCORRECT_ACCOUNT_OR_PASSWORD(200101),
    /** 登录错误; 不允许登录（被禁用） */
    SIGN_IN_ERROR__NOT_ALLOWED(200102)
    ;

    /**
     * 错误码
     */
    @Getter
    private int code;

    ServiceErr(int code) {
        this.code = code;
    }
}
