package com.jrong98.basic.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.text.SimpleDateFormat;

/**
 * Json 操作工具类
 * <p>包括但不限于处理 FastJSON, Jackson, Gson</p>
 * @author jrong98
 * @date 2022/6/9
 */
public class JsonUtil {

    public static ObjectMapper jsonMapper;
    static {
        jsonMapper = Jackson2ObjectMapperBuilder.json()
                .dateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
                .failOnUnknownProperties(false)
                //.serializationInclusion(JsonInclude.Include.NON_NULL)
                //.defaultTyping(ObjectMapper.DefaultTypeResolverBuilder.construct(ObjectMapper.DefaultTyping.EVERYTHING, LaissezFaireSubTypeValidator.instance)
                //        .inclusion(JsonTypeInfo.As.PROPERTY)
                //        .init(JsonTypeInfo.Id.CLASS, null))
                .build();
    }

    public static ObjectMapper getJsonMapper() {
        return jsonMapper;
    }

    public static String convertToJsonStr(Object o) {
        try {
            return jsonMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
