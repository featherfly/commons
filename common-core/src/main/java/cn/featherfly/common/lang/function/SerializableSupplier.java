package cn.featherfly.common.lang.function;

import java.io.Serializable;
import java.util.function.Supplier;

@FunctionalInterface
public interface SerializableSupplier<T> extends Serializable, Supplier<T> {

}