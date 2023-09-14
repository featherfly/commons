package cn.featherfly.common.function.serializable;

import java.util.Date;

import cn.featherfly.common.function.DateSupplier;

/**
 * The Interface SerializableDateSupplier.
 *
 * @author zhongj
 * @param <T> the type extends Date of results supplied by this supplier
 * @see java.util.function.Supplier
 * @see DateSupplier
 */
@FunctionalInterface
public interface SerializableDateSupplier<T extends Date> extends SerializableSupplier<T>, DateSupplier<T> {

}