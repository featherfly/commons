package cn.featherfly.common.function.serializable;

import java.io.Serializable;
import java.util.function.IntConsumer;

/**
 * The Interface SerializableIntConsumer.
 *
 * @see java.util.function.Consumer
 */
@FunctionalInterface
public interface SerializableIntConsumer extends Serializable, IntConsumer {

}