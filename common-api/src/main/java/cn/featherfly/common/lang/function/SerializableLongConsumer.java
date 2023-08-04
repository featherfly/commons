package cn.featherfly.common.lang.function;

import java.io.Serializable;
import java.util.function.LongConsumer;

/**
 * The Interface SerializableLongConsumer.
 *
 * @see java.util.function.Consumer
 */
@FunctionalInterface
public interface SerializableLongConsumer extends Serializable, LongConsumer {

}