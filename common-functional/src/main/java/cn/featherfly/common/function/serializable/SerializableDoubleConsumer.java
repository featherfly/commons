package cn.featherfly.common.function.serializable;

import java.io.Serializable;
import java.util.function.DoubleConsumer;

/**
 * The Interface SerializableDoubleConsumer.
 *
 * @see java.util.function.Consumer
 */
@FunctionalInterface
public interface SerializableDoubleConsumer extends Serializable, DoubleConsumer {

}