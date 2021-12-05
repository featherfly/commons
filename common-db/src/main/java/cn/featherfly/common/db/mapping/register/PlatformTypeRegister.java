package cn.featherfly.common.db.mapping.register;

import java.sql.JDBCType;
import java.sql.SQLType;

import cn.featherfly.common.db.mapping.JavaToSqlTypeRegister;
import cn.featherfly.common.db.mapping.SqlTypeToJavaRegister;
import cn.featherfly.common.model.app.Platform;

/**
 * The Class PlatformTypeRegister.
 *
 * @author zhongj
 */
public class PlatformTypeRegister implements SqlTypeToJavaRegister<Platform>, JavaToSqlTypeRegister<Platform> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<Platform> getJavaType() {
        return Platform.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SQLType getSqlType() {
        return JDBCType.INTEGER;
    }
}
