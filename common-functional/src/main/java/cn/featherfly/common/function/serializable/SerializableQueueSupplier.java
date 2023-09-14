
package cn.featherfly.common.function.serializable;

import java.util.Queue;

import cn.featherfly.common.function.QueueSupplier;

/**
 * The Interface SerializableQueueSupplier.
 *
 * @author zhongj
 * @param <E> the element type
 * @see SerializableSupplier
 * @see QueueSupplier
 */
@FunctionalInterface
public interface SerializableQueueSupplier<E> extends SerializableSupplier<Queue<E>>, QueueSupplier<E> {

}
