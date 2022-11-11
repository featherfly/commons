package cn.featherfly.common.db.mapping.mappers;

import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLType;
import java.util.ArrayList;
import java.util.List;

import cn.featherfly.common.db.JdbcUtils;
import cn.featherfly.common.db.mapping.AbstractGenericJavaSqlTypeMapper;
import cn.featherfly.common.lang.NumberUtils;
import cn.featherfly.common.lang.reflect.Type;

/**
 * The Class NumberArrayJavaSqlTypeMapper.
 *
 * @author zhongj
 * @param <N> the number type
 */
public class NumberListJavaSqlTypeMapper<N extends Number> extends AbstractGenericJavaSqlTypeMapper<List<N>> {

    private Class<N> numberType;

    /**
     * Instantiates a new platform array java sql type mapper.
     *
     * @param numberType the number type
     */
    public NumberListJavaSqlTypeMapper(Class<N> numberType) {
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
    public boolean support(Type<List<N>> type) {
        return getType().getType().equals(type.getType());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(PreparedStatement prep, int parameterIndex, List<N> values) {
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
    public List<N> get(ResultSet rs, int columnIndex) {
        String value = JdbcUtils.getResultSetValue(rs, columnIndex, String.class);
        if (value != null) {
            String[] values = value.split(",");
            List<N> numbers = new ArrayList<>(values.length);
            for (String v : values) {
                numbers.add(NumberUtils.parse(v, numberType));
            }
            return numbers;
        } else {
            return null;
        }
    }

}
