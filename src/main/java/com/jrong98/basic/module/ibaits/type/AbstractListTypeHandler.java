package com.jrong98.basic.module.ibaits.type;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;

/**
 * 列表类型处理器
 * @author jrong98
 * @date 2022/6/21
 */
@MappedTypes({List.class})
@MappedJdbcTypes(JdbcType.VARCHAR)
public abstract class AbstractListTypeHandler<E> extends BaseTypeHandler<List<E>> {

    protected static final char DELIMITER = ',';

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<E> parameter, JdbcType jdbcType) throws SQLException {
        if (parameter == null || parameter.isEmpty()) {
            ps.setNull(i, jdbcType.TYPE_CODE);
        }
    }

    @Override
    public List<E> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        return StringUtils.isNoneBlank(value) ? str2list(value) : Collections.emptyList();
    }

    @Override
    public List<E> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        return StringUtils.isNoneBlank(value) ? str2list(value) : Collections.emptyList();
    }

    @Override
    public List<E> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        return StringUtils.isNoneBlank(value) ? str2list(value) : Collections.emptyList();
    }

    /**
     * 字符串转列表
     * @param value 值
     * @return list
     */
    public abstract List<E> str2list(String value);

    protected String list2str(List<E> value) {
        if (null == value) {
            return null;
        }
        StringJoiner joiner = new StringJoiner(String.valueOf(DELIMITER));
        for (E e : value) {
            if (null == e)
                continue;
            joiner.add(e.toString());
        }
        return joiner.toString();
    }
}
