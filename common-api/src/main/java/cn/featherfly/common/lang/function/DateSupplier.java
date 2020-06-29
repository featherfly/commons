package cn.featherfly.common.lang.function;

import java.io.Serializable;
import java.util.Date;
import java.util.function.Supplier;

@FunctionalInterface
public interface DateSupplier<T extends Date> extends Serializable, Supplier<T> {

}