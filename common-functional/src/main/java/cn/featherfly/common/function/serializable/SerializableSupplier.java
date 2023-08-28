package cn.featherfly.common.function.serializable;

import java.io.Serializable;
import java.util.function.Supplier;

/**
 * The Interface SerializableSupplier.
 *
 * @param <T> the type of results supplied by this supplier
 * @see java.util.function.Supplier
 */
@FunctionalInterface
public interface SerializableSupplier<T> extends Serializable, Supplier<T> {

}