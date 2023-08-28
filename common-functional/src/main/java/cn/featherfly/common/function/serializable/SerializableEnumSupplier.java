package cn.featherfly.common.function.serializable;

@FunctionalInterface
public interface SerializableEnumSupplier<T extends Enum<?>> extends SerializableSupplier<T> {

}