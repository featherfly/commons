package cn.featherfly.common.db.mapping.mappers;

import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLType;

import cn.featherfly.common.db.JdbcUtils;
import cn.featherfly.common.db.mapping.AbstractGenericJavaSqlTypeMapper;
import cn.featherfly.common.db.mapping.JdbcMappingException;
import cn.featherfly.common.lang.ClassUtils;
import cn.featherfly.common.lang.GenericType;
import cn.featherfly.common.lang.Strings;
import cn.featherfly.common.model.app.Platform;
import cn.featherfly.common.model.app.Platforms;

/**
 * The Class PlatformJavaSqlTypeMapper.
 *
 * @author zhongj
 */
public class PlatformJavaSqlTypeStringMapper extends AbstractGenericJavaSqlTypeMapper<Platform> {

    /**
     * Instantiates a new platform java sql type mapper.
     */
    public PlatformJavaSqlTypeStringMapper() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean support(SQLType sqlType) {
        return JDBCType.VARCHAR == sqlType || JDBCType.NVARCHAR == sqlType || JDBCType.CHAR == sqlType
                || JDBCType.NCHAR == sqlType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean support(GenericType<Platform> type) {
        return ClassUtils.isParent(getGenericType().getType(), type.getType());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(PreparedStatement prep, int parameterIndex, Platform value) {
        if (value != null) {
            JdbcUtils.setParameter(prep, parameterIndex, value.value());
        } else {
            JdbcUtils.setParameter(prep, parameterIndex, null);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Platform get(ResultSet rs, int columnIndex) {
        String value = (String) JdbcUtils.getResultSetValue(rs, columnIndex, String.class);
        if (value != null) {
            try {
                return Platforms.valueOf(value);
            } catch (IllegalArgumentException e) {
                throw new JdbcMappingException(
                        Strings.format("convert {0} to type {1} error", value, Platforms.class.getName()));
            }
        } else {
            return null;
        }
    }

}
