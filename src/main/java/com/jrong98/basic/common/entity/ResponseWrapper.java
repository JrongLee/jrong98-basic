package com.jrong98.basic.common.entity;

import com.jrong98.basic.common.annotation.ResponseBodyWrapper;
import lombok.Getter;

/**
 * Web 统一先响应类
 * <p>参考 {@link ResponseBodyWrapper}</p>
 * @author jrong98
 * @date 2022/6/8
 */
@Getter
public class ResponseWrapper {

    /**
     * 响应码
     */
    private int errcode;

    /**
     * 响应消息
     */
    private String errmsg;

    /**
     * 响应数据
     */
    private Object data;


    public ResponseWrapper setErrcode(int errcode) {
        this.errcode = errcode;
        return this;
    }

    public ResponseWrapper setErrmsg(String errmsg) {
        this.errmsg = errmsg;
        return this;
    }

    public ResponseWrapper setData(Object data) {
        this.data = data;
        return this;
    }
}
