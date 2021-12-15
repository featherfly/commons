package cn.featherfly.common.db.mapping.mappers;

import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLType;

import cn.featherfly.common.db.JdbcUtils;
import cn.featherfly.common.db.mapping.AbstractGenericJavaSqlTypeMapper;
import cn.featherfly.common.db.mapping.JdbcMappingException;
import cn.featherfly.common.lang.GenericType;
import cn.featherfly.common.lang.Strings;
import cn.featherfly.common.model.app.Platform;
import cn.featherfly.common.model.app.Platforms;

/**
 * The Class PlatformArrayJavaSqlTypeMapper.
 *
 * @author zhongj
 */
public class PlatformArrayJavaSqlTypeMapper extends AbstractGenericJavaSqlTypeMapper<Platform[]> {

    /**
     * Instantiates a new platform array java sql type mapper.
     */
    public PlatformArrayJavaSqlTypeMapper() {
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

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean support(GenericType<Platform[]> type) {
        return getGenericType().getType().equals(type.getType());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(PreparedStatement prep, int parameterIndex, Platform[] values) {
        if (values != null) {
            StringBuilder result = new StringBuilder();
            for (Platform platform : values) {
                result.append(platform.name()).append(",");
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
    public Platform[] get(ResultSet rs, int columnIndex) {
        String value = (String) JdbcUtils.getResultSetValue(rs, columnIndex, String.class);
        if (value != null) {
            String[] values = value.split(",");
            Platform[] platforms = new Platform[values.length];
            for (int i = 0; i < values.length; i++) {
                try {
                    platforms[i] = Platforms.valueOf(values[i]);
                } catch (IllegalArgumentException e) {
                    try {
                        platforms[i] = Platforms.valueOf(Integer.parseInt(values[i]));
                    } catch (NumberFormatException e2) {
                        throw new JdbcMappingException(
                                Strings.format("convert {0} to type {1} error", values[i], Platforms.class.getName()));
                    }
                }
            }
            return platforms;
        } else {
            return null;
        }
    }

}
