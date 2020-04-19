
package cn.featherfly.common.db.mapping;

import java.io.Serializable;

import cn.featherfly.common.db.metadata.SqlType;

/**
 * <p>
 * SqlTypeToJavaRegister
 * </p>
 * .
 *
 * @author zhongj
 * @param <E> to regist java type
 */
public interface SqlTypeToJavaMapper<E extends Serializable> {

    /**
     * Gets the sql type.
     *
     * @param <C>     the generic type
     * @param sqlType the sql type
     * @return the sql type
     */
    <C extends Serializable> Class<C> getJavaType(SqlType sqlType);
}
