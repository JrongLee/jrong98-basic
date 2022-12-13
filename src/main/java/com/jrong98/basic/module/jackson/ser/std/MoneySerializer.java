package com.jrong98.basic.module.jackson.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

/**
 * 金额序列号器
 * @author jrong98
 * @date 2022/6/21
 */
public class MoneySerializer extends StdSerializer<Integer> {

    private static final long serialVersionUID = -7002976262151270734L;

    public final static MoneySerializer instance = new MoneySerializer();

    public MoneySerializer() {
        super(Integer.class);
    }

    @Override
    public void serialize(Integer value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeNumber(value.doubleValue() / 100.0);
    }

}
