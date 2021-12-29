package cn.featherfly.common.db.mapping.mappers;

import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLType;

import cn.featherfly.common.db.JdbcUtils;
import cn.featherfly.common.db.mapping.AbstractGenericJavaSqlTypeMapper;
import cn.featherfly.common.lang.ClassUtils;
import cn.featherfly.common.lang.GenericType;
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
        Integer value = (Integer) JdbcUtils.getResultSetValue(rs, columnIndex, Integer.class);
        if (value != null) {
            return Platforms.valueOf(value);
        } else {
            return null;
        }
    }

}
