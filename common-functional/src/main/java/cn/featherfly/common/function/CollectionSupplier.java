
package cn.featherfly.common.function;

import java.util.Collection;

/**
 * The Interface CollectionSupplier.
 *
 * @author zhongj
 * @param <E> the element type
 * @see java.util.function.Supplier
 */
@FunctionalInterface
public interface CollectionSupplier<E> extends BaseCollectionSupplier<Collection<E>, E> {

}
