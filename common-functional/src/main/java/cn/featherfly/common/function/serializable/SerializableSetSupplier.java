
package cn.featherfly.common.function.serializable;

import java.util.Set;

import cn.featherfly.common.function.SetSupplier;

/**
 * The Interface SerializableSetSupplier.
 *
 * @author zhongj
 * @param <E> the element type
 * @see SerializableSupplier
 * @see SetSupplier
 */
@FunctionalInterface
public interface SerializableSetSupplier<E> extends SerializableSupplier<Set<E>>, SetSupplier<E> {

}
