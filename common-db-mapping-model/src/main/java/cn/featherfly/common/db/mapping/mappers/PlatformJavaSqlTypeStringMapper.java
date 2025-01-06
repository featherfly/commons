package cn.featherfly.common.db.mapping.mappers;

import java.sql.CallableStatement;
import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLType;

import cn.featherfly.common.db.JdbcUtils;
import cn.featherfly.common.db.mapping.AbstractGenericJavaSqlTypeMapper;
import cn.featherfly.common.db.mapping.JdbcMappingException;
import cn.featherfly.common.lang.Str;
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

    //    /**
    //     * {@inheritDoc}
    //     */
    //    @Override
    //    public boolean support(GenericType<Platform> type) {
    //        return ClassUtils.isParent(getGenericType().getType(), type.getType());
    //    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(PreparedStatement prep, int parameterIndex, Platform value) {
        if (value != null) {
            JdbcUtils.setParameter(prep, parameterIndex, value.value());
        } else {
            JdbcUtils.setParameterNull(prep, parameterIndex);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(CallableStatement call, String parameterName, Platform value) {
        if (value != null) {
            JdbcUtils.setParameter(call, parameterName, value.value());
        } else {
            JdbcUtils.setParameterNull(call, parameterName);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(ResultSet rs, int parameterIndex, Platform value) {
        if (value != null) {
            JdbcUtils.setParameter(rs, parameterIndex, value.value());
        } else {
            JdbcUtils.setParameterNull(rs, parameterIndex);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Platform get(ResultSet rs, int columnIndex) {
        return toPlatform(JdbcUtils.getResultSetValue(rs, columnIndex, String.class));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Platform get(CallableStatement call, int paramIndex) {
        return toPlatform(JdbcUtils.getCallableParam(call, paramIndex, String.class));
    }

    private Platform toPlatform(String value) {
        if (value != null) {
            try {
                return Platforms.valueOf(value);
            } catch (IllegalArgumentException e) {
                throw new JdbcMappingException(
                    Str.format("convert {0} to type {1} error", value, Platforms.class.getName()));
            }
        } else {
            return null;
        }
    }

}
