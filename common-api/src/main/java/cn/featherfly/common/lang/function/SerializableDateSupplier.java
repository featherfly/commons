package cn.featherfly.common.lang.function;

import java.util.Date;

@FunctionalInterface
public interface SerializableDateSupplier<T extends Date> extends SerializableSupplier<T> {

}