package cn.featherfly.common.function.serializable;

import java.time.LocalDateTime;

import cn.featherfly.common.function.LocalDateTimeSupplier;

/**
 * The Interface SerializableLocalDateTimeSupplier.
 *
 * @author zhongj
 * @see SerializableSupplier
 * @see LocalDateTimeSupplier
 */
@FunctionalInterface
public interface SerializableLocalDateTimeSupplier extends SerializableSupplier<LocalDateTime>, LocalDateTimeSupplier {

}