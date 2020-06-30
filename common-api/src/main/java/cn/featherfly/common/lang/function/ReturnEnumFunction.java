package cn.featherfly.common.lang.function;

@FunctionalInterface
public interface ReturnEnumFunction<T, R extends Enum<?>> extends SerializableFunction<T, R> {

}