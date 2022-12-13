package com.jrong98.basic.common.advice;

import com.jrong98.basic.common.annotation.ResponseBodyWrapper;
import com.jrong98.basic.common.entity.ResponseWrapper;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

/**
 * 响应内容包装器
 * @author jrong98
 * @date 2022/6/8
 */
@Order(value = 99999999)
@RestControllerAdvice(value = "com.jrong98.basic.web")
public class ResponseBodyWrapperAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        Class<?> rType = Objects.requireNonNull(returnType.getMethod()).getReturnType();
        if (ResponseWrapper.class.isAssignableFrom(rType)) {
            return false;
        }
        return returnType.getDeclaringClass().isAnnotationPresent(ResponseBodyWrapper.class)
                || returnType.getMethod().isAnnotationPresent(ResponseBodyWrapper.class);
    }

    @Override
    public ResponseWrapper beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (returnType.getMethod() == null) {
            return null;
        }
        ResponseWrapper wrapper = new ResponseWrapper()
                .setErrcode(0)
                .setErrmsg("success");
        if (body == null && ClassUtils.isAssignable(Iterable.class, returnType.getMethod().getReturnType())) {
            return wrapper.setData(Collections.emptyList());
        }
        if (body == null && ClassUtils.isAssignable(Map.class, returnType.getMethod().getReturnType())) {
            return wrapper.setData(Collections.emptyMap());
        }
        if (body == null && returnType.getMethod().getReturnType().isPrimitive()) {
            return wrapper.setData(Collections.emptyMap());
        }
        return wrapper.setData(body);
    }
}
