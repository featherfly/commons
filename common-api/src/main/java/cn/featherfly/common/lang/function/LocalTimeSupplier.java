package cn.featherfly.common.lang.function;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.function.Supplier;

@FunctionalInterface
public interface LocalTimeSupplier extends Serializable, Supplier<LocalTime> {

}