package cn.featherfly.common.lang.function;

import java.time.LocalTime;

@FunctionalInterface
public interface LocalTimeSupplier extends SerializableSupplier<LocalTime> {

}