
package cn.featherfly.common.function.serializable;

import java.util.Collection;

import cn.featherfly.common.function.CollectionSupplier;

/**
 * The Interface CollectionSupplier.
 *
 * @author zhongj
 * @param <E> the element type
 * @see SerializableSupplier
 * @see CollectionSupplier
 */
@FunctionalInterface
public interface SerializableCollectionSupplier<E> extends SerializableSupplier<Collection<E>>, CollectionSupplier<E> {

}
