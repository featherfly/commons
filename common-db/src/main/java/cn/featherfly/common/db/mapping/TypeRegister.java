
package cn.featherfly.common.db.mapping;

import java.sql.SQLType;

/**
 * <p>
 * SqlTypeToJavaRegister
 * </p>
 * .
 *
 * @author zhongj
 * @param <E> to regist java type
 */
public interface TypeRegister<E extends Object> {

    /**
     * Gets the java type.
     *
     * @return the java type
     */
    Class<E> getJavaType();

    /**
     * Gets the sql type.
     *
     * @return the sql type
     */
    SQLType getSqlType();
}
