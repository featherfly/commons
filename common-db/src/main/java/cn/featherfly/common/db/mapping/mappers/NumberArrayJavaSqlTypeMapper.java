package cn.featherfly.common.db.mapping.mappers;

import java.sql.CallableStatement;
import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLType;

import cn.featherfly.common.db.JdbcUtils;
import cn.featherfly.common.db.mapping.AbstractGenericJavaSqlTypeMapper;
import cn.featherfly.common.lang.ArrayUtils;
import cn.featherfly.common.lang.Num;
import cn.featherfly.common.lang.reflect.Type;

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
    public boolean support(Type<N[]> type) {
        return getType().getType().equals(type.getType());
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
            JdbcUtils.setParameterNull(prep, parameterIndex);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(CallableStatement call, String parameterName, N[] values) {
        if (values != null) {
            StringBuilder result = new StringBuilder();
            for (N value : values) {
                result.append(value).append(",");
            }
            if (result.length() > 0) {
                result.deleteCharAt(result.length() - 1);
            }
            JdbcUtils.setParameter(call, parameterName, result.toString());
        } else {
            JdbcUtils.setParameterNull(call, parameterName);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(ResultSet rs, int parameterIndex, N[] values) {
        if (values != null) {
            StringBuilder result = new StringBuilder();
            for (N value : values) {
                result.append(value).append(",");
            }
            if (result.length() > 0) {
                result.deleteCharAt(result.length() - 1);
            }
            JdbcUtils.setParameter(rs, parameterIndex, result.toString());
        } else {
            JdbcUtils.setParameterNull(rs, parameterIndex);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public N[] get(ResultSet rs, int columnIndex) {
        String value = JdbcUtils.getResultSetValue(rs, columnIndex, String.class);
        if (value != null) {
            String[] values = value.split(",");
            N[] numbers = ArrayUtils.create(numberType, values.length);
            for (int i = 0; i < values.length; i++) {
                numbers[i] = Num.parse(values[i], numberType);
            }
            return numbers;
        } else {
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public N[] get(CallableStatement call, int paramIndex) {
        String value = JdbcUtils.getCallableParam(call, paramIndex, String.class);
        if (value != null) {
            String[] values = value.split(",");
            N[] numbers = ArrayUtils.create(numberType, values.length);
            for (int i = 0; i < values.length; i++) {
                numbers[i] = Num.parse(values[i], numberType);
            }
            return numbers;
        } else {
            return null;
        }
    }
}
