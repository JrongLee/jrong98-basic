package com.jrong98.basic.common.advice;

import com.jrong98.basic.common.ServiceErr;
import com.jrong98.basic.common.entity.ResponseWrapper;
import com.jrong98.basic.common.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author jrong98
 * @date 2022/6/21
 */
@RestControllerAdvice(value = "com.jrong98.basic.web")
public class ExceptionAdvice {

    public final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * 业务异常
     * @param exception
     * @return
     */
    @ExceptionHandler(value = ServiceException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseWrapper serviceException(ServiceException exception) {
        ServiceErr err = exception.getServiceErr();
        return new ResponseWrapper()
                .setErrcode(err.getCode())
                .setErrmsg("internal error.");
    }

    /**
     * 参数检验异常
     * @param exception
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseWrapper methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();
        FieldError error = result.getFieldError();
        return new ResponseWrapper()
                .setErrcode(ServiceErr.SE4000.getCode())
                .setErrmsg(error.getDefaultMessage());
    }

    /**
     * 所有内部异常
     * @param exception
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseWrapper exception(Exception exception) {
        log.error("Internal Server Error", exception);
        return new ResponseWrapper()
                .setErrcode(ServiceErr.SE5000.getCode())
                .setErrmsg("Internal Server Error");
    }
}
