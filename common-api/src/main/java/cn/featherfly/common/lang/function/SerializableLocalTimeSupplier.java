package cn.featherfly.common.lang.function;

import java.time.LocalTime;

@FunctionalInterface
public interface SerializableLocalTimeSupplier extends SerializableSupplier<LocalTime> {

}