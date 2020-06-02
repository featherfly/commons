
package cn.featherfly.common.db.mapping;

import java.io.Serializable;

/**
 * <p>
 * JavaToSqlTypeRegister
 * </p>
 * .
 *
 * @author zhongj
 * @param <E> to regist java type
 */
public interface JavaToSqlTypeRegister<E extends Serializable> extends TypeRegister<E> {

}
