
package cn.featherfly.common.function.serializable;

import java.util.List;

import cn.featherfly.common.function.ListSupplier;

/**
 * The Interface ListSupplier.
 *
 * @author zhongj
 * @param <E> the element type
 * @see SerializableSupplier
 * @see ListSupplier
 */
@FunctionalInterface
public interface SerializableListSupplier<E> extends SerializableSupplier<List<E>>, ListSupplier<E> {

}
