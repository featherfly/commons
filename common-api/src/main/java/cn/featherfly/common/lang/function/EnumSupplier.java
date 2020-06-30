package cn.featherfly.common.lang.function;

@FunctionalInterface
public interface EnumSupplier<T extends Enum<?>> extends SerializableSupplier<T> {

}