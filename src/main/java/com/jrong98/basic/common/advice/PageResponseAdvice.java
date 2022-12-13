package com.jrong98.basic.common.advice;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jrong98.basic.common.util.JsonUtil;
import com.jrong98.basic.common.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Collections;
import java.util.List;

/**
 * 分页内容包装器
 * @author jrong98
 * @date 2022/6/22
 */
@Order(value = 88888888)
@RestControllerAdvice(value = "com.jrong98.basic.web")
public class PageResponseAdvice implements ResponseBodyAdvice<Object> {

    public final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return IPage.class.isAssignableFrom(returnType.getMethod().getReturnType());
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // if the order is not specified during the bean registration process
        if (!(body instanceof IPage)) {
            log.warn("The original data has been modified, unsupported type [ {} ]", body.getClass().getName());
            return body;
        }
        IPage<?> page = (IPage<?>) body;
        ObjectNode root = JsonUtil.getJsonMapper().createObjectNode();
        root.put("page", page.getPages());
        root.put("total", page.getTotal());

        ArrayNode data = root.putArray("data");
        List<?> records = Utils.ifNull(page.getRecords(), Collections.emptyList());
        if (!records.isEmpty()) {
            ArrayNode value = JsonUtil.getJsonMapper().convertValue(records, ArrayNode.class);
            data.addAll(value);
        }
        return root;
    }
}
