
package cn.featherfly.common.function.serializable;

import cn.featherfly.common.function.ArraySupplier;

/**
 * ArraySupplier.
 *
 * @author zhongj
 * @param <E> the element type
 * @see SerializableSupplier
 * @see ArraySupplier
 */
@FunctionalInterface
public interface SerializableArraySupplier<E> extends SerializableSupplier<E[]>, ArraySupplier<E> {

}
