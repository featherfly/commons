package cn.featherfly.common.db.mapping.register;

import java.sql.JDBCType;
import java.sql.SQLType;

import cn.featherfly.common.db.mapping.JavaToSqlTypeRegister;
import cn.featherfly.common.db.mapping.SqlTypeToJavaRegister;
import cn.featherfly.common.model.app.Platforms;

/**
 * The Class PlatformsTypeRegister.
 *
 * @author zhongj
 */
public class PlatformsTypeRegister implements SqlTypeToJavaRegister<Platforms>, JavaToSqlTypeRegister<Platforms> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<Platforms> getJavaType() {
        return Platforms.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SQLType getSqlType() {
        return JDBCType.INTEGER;
    }

}