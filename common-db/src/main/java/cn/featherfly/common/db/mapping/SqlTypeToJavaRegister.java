
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
public interface SqlTypeToJavaRegister<E extends Serializable> {

    SqlType getSqlType();
}
