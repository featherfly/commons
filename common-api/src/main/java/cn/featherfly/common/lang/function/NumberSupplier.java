package cn.featherfly.common.lang.function;

/**
 * NumberSupplier.
 *
 * @author zhongj
 * @deprecated {@link SerializableNumberSupplier}
 */
@FunctionalInterface
@Deprecated
public interface NumberSupplier<T extends Number> extends SerializableNumberSupplier<T> {

}