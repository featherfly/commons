package cn.featherfly.common.lang.function;

@FunctionalInterface
public interface SerializableEnumSupplier<T extends Enum<?>> extends SerializableSupplier<T> {

}