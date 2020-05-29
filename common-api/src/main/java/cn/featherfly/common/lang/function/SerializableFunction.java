package cn.featherfly.common.lang.function;

import java.io.Serializable;
import java.util.function.Function;

@FunctionalInterface
public interface SerializableFunction<T, R> extends Serializable, Function<T, R> {

}