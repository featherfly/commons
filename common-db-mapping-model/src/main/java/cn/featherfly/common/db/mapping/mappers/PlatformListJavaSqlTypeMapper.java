package cn.featherfly.common.db.mapping.mappers;

import java.sql.CallableStatement;
import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLType;
import java.util.ArrayList;
import java.util.List;

import cn.featherfly.common.db.JdbcUtils;
import cn.featherfly.common.db.mapping.AbstractGenericJavaSqlTypeMapper;
import cn.featherfly.common.db.mapping.JdbcMappingException;
import cn.featherfly.common.lang.Str;
import cn.featherfly.common.model.app.Platform;
import cn.featherfly.common.model.app.Platforms;

/**
 * The Class PlatformListJavaSqlTypeMapper.
 *
 * @author zhongj
 */
public class PlatformListJavaSqlTypeMapper extends AbstractGenericJavaSqlTypeMapper<List<Platform>> {

    /**
     * Instantiates a new platform array java sql type mapper.
     */
    public PlatformListJavaSqlTypeMapper() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean support(SQLType sqlType) {
        return JDBCType.VARCHAR == sqlType || JDBCType.NVARCHAR == sqlType || JDBCType.CHAR == sqlType
            || JDBCType.NCHAR == sqlType;
    }

    //    /**
    //     * {@inheritDoc}
    //     */
    //    @Override
    //    public boolean support(GenericType<List<Platform>> type) {
    //        return getGenericType().getType().equals(type.getType());
    //    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(PreparedStatement prep, int parameterIndex, List<Platform> values) {
        if (values != null) {
            JdbcUtils.setParameter(prep, parameterIndex, toString(values));
        } else {
            JdbcUtils.setParameterNull(prep, parameterIndex);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(CallableStatement call, String parameterName, List<Platform> values) {
        if (values != null) {
            JdbcUtils.setParameter(call, parameterName, toString(values));
        } else {
            JdbcUtils.setParameterNull(call, parameterName);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(ResultSet rs, int parameterIndex, List<Platform> values) {
        if (values != null) {
            JdbcUtils.setParameter(rs, parameterIndex, toString(values));
        } else {
            JdbcUtils.setParameterNull(rs, parameterIndex);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Platform> get(ResultSet rs, int columnIndex) {
        return toPlatforms(JdbcUtils.getString(rs, columnIndex));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Platform> get(CallableStatement call, int paramIndex) {
        return toPlatforms(JdbcUtils.getString(call, paramIndex));
    }

    private String toString(List<Platform> values) {
        StringBuilder result = new StringBuilder();
        for (Platform platform : values) {
            result.append(platform.name()).append(",");
        }
        if (result.length() > 0) {
            result.deleteCharAt(result.length() - 1);
        }
        return result.toString();
    }

    private List<Platform> toPlatforms(String value) {
        if (value != null) {
            String[] values = value.split(",");
            List<Platform> platforms = new ArrayList<>(values.length);
            for (int i = 0; i < values.length; i++) {
                try {
                    platforms.add(Platforms.valueOf(values[i]));
                } catch (IllegalArgumentException e) {
                    try {
                        platforms.add(Platforms.valueOf(Integer.parseInt(values[i])));
                    } catch (NumberFormatException e2) {
                        throw new JdbcMappingException(
                            Str.format("convert {0} to type {1} error", values[i], Platforms.class.getName()));
                    }
                }
            }
            return platforms;
        } else {
            return null;
        }
    }
}
