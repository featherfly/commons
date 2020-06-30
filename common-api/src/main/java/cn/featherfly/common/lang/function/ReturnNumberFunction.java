package cn.featherfly.common.lang.function;

@FunctionalInterface
public interface ReturnNumberFunction<T, R extends Number> extends SerializableFunction<T, R> {

}