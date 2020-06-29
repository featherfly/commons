package cn.featherfly.common.lang.function;

import java.util.Date;

@FunctionalInterface
public interface DateSupplier<T extends Date> extends SerializableSupplier<T> {

}