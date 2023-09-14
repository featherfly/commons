package cn.featherfly.common.function.serializable;

import cn.featherfly.common.function.EnumSupplier;

/**
 * The Interface SerializableEnumSupplier.
 *
 * @author zhongj
 * @param <T> the type extends Enum of results supplied by this supplier
 * @see SerializableSupplier
 * @see EnumSupplier
 */
@FunctionalInterface
public interface SerializableEnumSupplier<T extends Enum<T>> extends SerializableSupplier<T>, EnumSupplier<T> {

}