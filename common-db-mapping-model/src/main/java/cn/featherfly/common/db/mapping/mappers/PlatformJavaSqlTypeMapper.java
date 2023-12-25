package cn.featherfly.common.db.mapping.mappers;

import java.sql.CallableStatement;
import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLType;

import cn.featherfly.common.db.JdbcUtils;
import cn.featherfly.common.db.mapping.AbstractGenericJavaSqlTypeMapper;
import cn.featherfly.common.model.app.Platform;
import cn.featherfly.common.model.app.Platforms;

/**
 * The Class PlatformJavaSqlTypeMapper.
 *
 * @author zhongj
 */
public class PlatformJavaSqlTypeMapper extends AbstractGenericJavaSqlTypeMapper<Platform> {

    /**
     * Instantiates a new platform java sql type mapper.
     */
    public PlatformJavaSqlTypeMapper() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean support(SQLType sqlType) {
        return JDBCType.INTEGER == sqlType || JDBCType.BIGINT == sqlType || JDBCType.SMALLINT == sqlType
                || JDBCType.TINYINT == sqlType;
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
        return toPlatform(JdbcUtils.getInteger(rs, columnIndex));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Platform get(CallableStatement call, int paramIndex) {
        return toPlatform(JdbcUtils.getInteger(call, paramIndex));
    }

    private Platform toPlatform(Integer value) {
        if (value != null) {
            return Platforms.valueOf(value);
        } else {
            return null;
        }
    }
}
