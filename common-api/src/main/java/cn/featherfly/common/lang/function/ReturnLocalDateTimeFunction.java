package cn.featherfly.common.lang.function;

import java.time.LocalDateTime;

@FunctionalInterface
public interface ReturnLocalDateTimeFunction<T> extends SerializableFunction<T, LocalDateTime> {

}