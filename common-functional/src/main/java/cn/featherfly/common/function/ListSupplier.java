
package cn.featherfly.common.function;

import java.util.List;

/**
 * The Interface ListSupplier.
 *
 * @author zhongj
 * @param <E> the element type
 * @see java.util.function.Supplier
 */
@FunctionalInterface
public interface ListSupplier<E> extends BaseCollectionSupplier<List<E>, E> {

}
