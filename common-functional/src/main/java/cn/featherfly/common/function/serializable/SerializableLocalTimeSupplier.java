package cn.featherfly.common.function.serializable;

import java.time.LocalTime;

import cn.featherfly.common.function.LocalTimeSupplier;

/**
 * The Interface SerializableLocalTimeSupplier.
 *
 * @author zhongj
 * @see SerializableSupplier
 * @see LocalTimeSupplier
 */
@FunctionalInterface
public interface SerializableLocalTimeSupplier extends SerializableSupplier<LocalTime>, LocalTimeSupplier {

}