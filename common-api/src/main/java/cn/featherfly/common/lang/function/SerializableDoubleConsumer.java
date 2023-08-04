package cn.featherfly.common.lang.function;

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