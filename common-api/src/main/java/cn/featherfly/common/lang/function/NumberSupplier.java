package cn.featherfly.common.lang.function;

import java.io.Serializable;
import java.util.function.Supplier;

@FunctionalInterface
public interface NumberSupplier<T extends Number> extends Serializable, Supplier<T> {

}