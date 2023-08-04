package cn.featherfly.common.lang.function;

/**
 * EnumSupplier.
 *
 * @author zhongj
 */
@FunctionalInterface
public interface EnumSupplier<T extends Enum<?>> extends SerializableEnumSupplier<T> {

}