package cn.featherfly.common.lang.function;

import java.io.Serializable;
import java.util.function.BiConsumer;

/**
 * The Interface SerializableConsumer.
 *
 * @param <T> the type of the first argument to the operation
 * @param <U> the type of the second argument to the operation
 * @see java.util.function.BiConsumer
 */
@FunctionalInterface
public interface SerializableBiConsumer<T, U> extends Serializable, BiConsumer<T, U> {

}