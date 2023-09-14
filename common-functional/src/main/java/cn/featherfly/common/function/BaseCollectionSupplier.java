
package cn.featherfly.common.function;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * The Interface BaseCollectionSupplier.
 *
 * @author zhongj
 * @param <C> the generic collection type
 * @param <E> the element type
 * @see java.util.function.Supplier
 */
@FunctionalInterface
public interface BaseCollectionSupplier<C extends Collection<E>, E> extends Supplier<C> {

}
