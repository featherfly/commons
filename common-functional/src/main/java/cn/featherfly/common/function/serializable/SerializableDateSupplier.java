package cn.featherfly.common.function.serializable;

import java.util.Date;

@FunctionalInterface
public interface SerializableDateSupplier<T extends Date> extends SerializableSupplier<T> {

}