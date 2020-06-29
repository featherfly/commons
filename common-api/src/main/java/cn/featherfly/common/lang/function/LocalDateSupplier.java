package cn.featherfly.common.lang.function;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.function.Supplier;

@FunctionalInterface
public interface LocalDateSupplier extends Serializable, Supplier<LocalDate> {

}