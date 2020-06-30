package cn.featherfly.common.lang.function;

import java.util.Date;

@FunctionalInterface
public interface ReturnDateFunction<T, R extends Date> extends SerializableFunction<T, R> {

}