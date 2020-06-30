package cn.featherfly.common.lang.function;

import java.time.LocalTime;

@FunctionalInterface
public interface ReturnLocalTimeFunction<T> extends SerializableFunction<T, LocalTime> {

}