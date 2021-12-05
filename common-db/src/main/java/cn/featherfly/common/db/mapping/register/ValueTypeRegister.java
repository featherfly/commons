package cn.featherfly.common.db.mapping.register;

import java.sql.JDBCType;
import java.sql.SQLType;

import cn.featherfly.common.db.mapping.JavaToSqlTypeRegister;
import cn.featherfly.common.db.mapping.SqlTypeToJavaRegister;
import cn.featherfly.common.model.Value;

/**
 * The Class ValueTypeRegister.
 *
 * @author zhongj
 * @param <V> the value type
 * @param <E> the element type
 */
public class ValueTypeRegister<V extends Value<E>, E> implements SqlTypeToJavaRegister<V>, JavaToSqlTypeRegister<V> {

    private Class<V> javaType;

    /**
     * Object to json type register.
     *
     * @param javaType the java type
     */
    public ValueTypeRegister(Class<V> javaType) {
        super();
        this.javaType = javaType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<V> getJavaType() {
        return javaType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SQLType getSqlType() {
        return JDBCType.INTEGER;
    }
}