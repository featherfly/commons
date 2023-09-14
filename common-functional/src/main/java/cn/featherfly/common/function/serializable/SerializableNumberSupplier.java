package cn.featherfly.common.function.serializable;

import cn.featherfly.common.function.NumberSupplier;

/**
 * The Interface SerializableNumberSupplier.
 *
 * @author zhongj
 * @param <T> the type extends Number of results supplied by this supplier
 * @see SerializableSupplier
 * @see NumberSupplier
 */
@FunctionalInterface
public interface SerializableNumberSupplier<T extends Number> extends SerializableSupplier<T>, NumberSupplier<T> {

}