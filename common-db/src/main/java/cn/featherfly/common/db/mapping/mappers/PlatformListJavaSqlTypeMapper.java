package cn.featherfly.common.db.mapping.mappers;

import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLType;
import java.util.ArrayList;
import java.util.List;

import cn.featherfly.common.db.JdbcUtils;
import cn.featherfly.common.db.mapping.AbstractGenericJavaSqlTypeMapper;
import cn.featherfly.common.lang.GenericType;
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

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean support(GenericType<List<Platform>> type) {
        return getGenericType().getType().equals(type.getType());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(PreparedStatement prep, int parameterIndex, List<Platform> values) {
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
    public List<Platform> get(ResultSet rs, int columnIndex) {
        String value = (String) JdbcUtils.getResultSetValue(rs, columnIndex, String.class);
        if (value != null) {
            String[] values = value.split(",");
            List<Platform> platforms = new ArrayList<>(values.length);
            for (String v : values) {
                platforms.add(Platforms.valueOf(v));
            }
            return platforms;
        } else {
            return null;
        }
    }

}
