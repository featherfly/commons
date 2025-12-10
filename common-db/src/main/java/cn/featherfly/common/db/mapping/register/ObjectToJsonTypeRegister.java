package cn.featherfly.common.db.mapping.register;

import java.io.Serializable;
import java.sql.JDBCType;
import java.sql.SQLType;

import cn.featherfly.common.db.mapping.JavaToSqlTypeRegister;
import cn.featherfly.common.db.mapping.SqlTypeToJavaRegister;

/**
 * The Class ObjectToJsonTypeRegister.
 *
 * @author zhongj
 * @param <E> the element type
 */
public class ObjectToJsonTypeRegister<E extends Serializable>
    implements SqlTypeToJavaRegister<E>, JavaToSqlTypeRegister<E> {

    private final Class<E> javaType;

    private final boolean storeAsString;

    /**
     * Instantiates a new object to json type register.
     *
     * @param javaType the java type
     */
    public ObjectToJsonTypeRegister(Class<E> javaType) {
        this(javaType, true);
    }

    /**
     * Instantiates a new object to json type register.
     *
     * @param javaType the java type
     * @param storeAsString the store as string
     */
    public ObjectToJsonTypeRegister(Class<E> javaType, boolean storeAsString) {
        super();
        this.javaType = javaType;
        this.storeAsString = storeAsString;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<E> getJavaType() {
        return javaType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SQLType getSqlType() {
        if (storeAsString) {
            return JDBCType.LONGNVARCHAR;
        } else {
            return JDBCType.BLOB;
        }
    }
}