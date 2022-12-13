package com.jrong98.basic.module.jackson.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.jrong98.basic.module.jackson.annotation.EnumOutput;

import java.io.IOException;

/**
 * @author jrong98
 * @date 2022/6/22
 */
public class EnumOutputSerializer extends StdSerializer<Integer> implements ContextualSerializer {

    private String[] values;

    public EnumOutputSerializer() {
        super(Integer.class);
    }

    @Override
    public void serialize(Integer value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeString(values[value]);
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) throws JsonMappingException {
        EnumOutput annotation = property.getAnnotation(EnumOutput.class);
        if (null == annotation) {
            annotation = property.getContextAnnotation(EnumOutput.class);
        }
        if (annotation == null) {
        }
        else {
            values = annotation.value();
        }
        return this;
    }
}
