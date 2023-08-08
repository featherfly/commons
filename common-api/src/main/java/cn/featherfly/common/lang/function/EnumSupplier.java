package cn.featherfly.common.lang.function;

/**
 * EnumSupplier.
 *
 * @author zhongj
 * @deprecated {@link SerializableEnumSupplier}
 */
@FunctionalInterface
@Deprecated
public interface EnumSupplier<T extends Enum<?>> extends SerializableEnumSupplier<T> {

}