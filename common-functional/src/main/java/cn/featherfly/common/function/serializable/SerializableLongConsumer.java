package cn.featherfly.common.function.serializable;

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