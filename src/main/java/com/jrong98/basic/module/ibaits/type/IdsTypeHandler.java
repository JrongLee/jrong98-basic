package com.jrong98.basic.module.ibaits.type;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Ids 类型处理器
 * @author jrong98
 * @date 2022/6/21
 */
public class IdsTypeHandler extends AbstractListTypeHandler<Long> {

    @Override
    public List<Long> str2list(String value) {
        String[] values = StringUtils.split(value, DELIMITER);
        if (ArrayUtils.isEmpty(values)) {
            return Collections.emptyList();
        }
        return Arrays.stream(values)
                .map(Long::valueOf)
                .collect(Collectors.toList());
    }
}
