package cn.featherfly.common.function;

import java.util.function.Supplier;

/**
 * The Interface EnumSupplier.
 *
 * @author zhongj
 * @param <T> the generic type
 */
@FunctionalInterface
public interface EnumSupplier<T extends Enum<T>> extends Supplier<T> {

}