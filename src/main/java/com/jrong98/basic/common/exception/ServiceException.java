package com.jrong98.basic.common.exception;

import com.jrong98.basic.common.ServiceErr;
import lombok.Getter;

/**
 * 业务异常
 * @author jrong98
 * @date 2022/6/21
 */
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 9210747521553122979L;

    @Getter
    private ServiceErr serviceErr;

    public ServiceException(ServiceErr err) {
        super();
        this.serviceErr = err;
    }

}
