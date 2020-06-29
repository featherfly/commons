package cn.featherfly.common.lang.function;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.function.Supplier;

@FunctionalInterface
public interface LocalDateTimeSupplier extends Serializable, Supplier<LocalDateTime> {

}