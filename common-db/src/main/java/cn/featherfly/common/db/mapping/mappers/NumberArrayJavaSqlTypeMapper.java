package cn.featherfly.common.db.mapping.mappers;

import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLType;

import cn.featherfly.common.db.JdbcUtils;
import cn.featherfly.common.db.mapping.AbstractGenericJavaSqlTypeMapper;
import cn.featherfly.common.lang.ArrayUtils;
import cn.featherfly.common.lang.GenericType;
import cn.featherfly.common.lang.NumberUtils;

/**
 * The Class NumberArrayJavaSqlTypeMapper.
 *
 * @author zhongj
 * @param <N> the number type
 */
public class NumberArrayJavaSqlTypeMapper<N extends Number> extends AbstractGenericJavaSqlTypeMapper<N[]> {

    private Class<N> numberType;

    /**
     * Instantiates a new platform array java sql type mapper.
     *
     * @param numberType the number type
     */
    public NumberArrayJavaSqlTypeMapper(Class<N> numberType) {
        super();
        this.numberType = numberType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean support(SQLType sqlType) {
        return JDBCType.VARCHAR == sqlType || JDBCType.NVARCHAR == sqlType || JDBCType.CHAR == sqlType
                || JDBCType.NCHAR == sqlType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean support(GenericType<N[]> type) {
        return getGenericType().getType().equals(type.getType());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(PreparedStatement prep, int parameterIndex, N[] values) {
        if (values != null) {
            StringBuilder result = new StringBuilder();
            for (N value : values) {
                result.append(value).append(",");
            }
            if (result.length() > 0) {
                result.deleteCharAt(result.length() - 1);
            }
            JdbcUtils.setParameter(prep, parameterIndex, result.toString());
        } else {
            JdbcUtils.setParameter(prep, parameterIndex, null);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public N[] get(ResultSet rs, int columnIndex) {
        String value = (String) JdbcUtils.getResultSetValue(rs, columnIndex, String.class);
        if (value != null) {
            String[] values = value.split(",");
            N[] numbers = ArrayUtils.create(numberType, values.length);
            for (int i = 0; i < values.length; i++) {
                numbers[i] = NumberUtils.parse(values[i], numberType);
            }
            return numbers;
        } else {
            return null;
        }
    }

}
