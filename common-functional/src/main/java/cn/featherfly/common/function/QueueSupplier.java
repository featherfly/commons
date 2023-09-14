
package cn.featherfly.common.function;

import java.util.Queue;

/**
 * The Interface QueueSupplier.
 *
 * @author zhongj
 * @param <E> the element type
 * @see BaseCollectionSupplier
 */
@FunctionalInterface
public interface QueueSupplier<E> extends BaseCollectionSupplier<Queue<E>, E> {

}
