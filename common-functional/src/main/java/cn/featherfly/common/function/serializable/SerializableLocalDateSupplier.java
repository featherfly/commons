package cn.featherfly.common.function.serializable;

import java.time.LocalDate;

import cn.featherfly.common.function.LocalDateSupplier;

/**
 * The Interface SerializableLocalDateSupplier.
 *
 * @author zhongj
 * @see SerializableSupplier
 * @see LocalDateSupplier
 */
@FunctionalInterface
public interface SerializableLocalDateSupplier extends SerializableSupplier<LocalDate>, LocalDateSupplier {

}