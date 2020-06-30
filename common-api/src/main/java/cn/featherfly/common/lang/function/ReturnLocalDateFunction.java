package cn.featherfly.common.lang.function;

import java.time.LocalDate;

@FunctionalInterface
public interface ReturnLocalDateFunction<T> extends SerializableFunction<T, LocalDate> {

}