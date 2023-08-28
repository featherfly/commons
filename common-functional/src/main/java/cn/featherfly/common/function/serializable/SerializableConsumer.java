package cn.featherfly.common.function.serializable;

import java.io.Serializable;
import java.util.function.Consumer;

/**
 * The Interface SerializableConsumer.
 *
 * @param <T> the type of the input to the operation
 * @see java.util.function.Consumer
 */
@FunctionalInterface
public interface SerializableConsumer<T> extends Serializable, Consumer<T> {

}