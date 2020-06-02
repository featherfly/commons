
package cn.featherfly.common.db.mapping;

import java.io.Serializable;

/**
 * <p>
 * SqlTypeToJavaRegister
 * </p>
 * .
 *
 * @author zhongj
 * @param <E> to regist java type
 */
public interface SqlTypeToJavaRegister<E extends Serializable> extends TypeRegister<E> {

}
