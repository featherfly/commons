
package cn.featherfly.common.db.mapping;

import java.io.Serializable;

import cn.featherfly.common.db.metadata.SqlType;

/**
 * <p>
 * JavaToSqlTypeMapper
 * </p>
 * .
 *
 * @author zhongj
 * @param <E> to regist java type
 */
public interface JavaToSqlTypeMapper<E extends Serializable> {

    /**
     * Gets the sql type.
     *
     * @param <C>      the generic type
     * @param javaType the java type
     * @return the sql type
     */
    <C extends Serializable> SqlType getSqlType(Class<C> javaType);
}
