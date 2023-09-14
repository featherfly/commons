package cn.featherfly.common.function;

import java.util.function.Supplier;

/**
 * The Interface EnumSupplier.
 *
 * @author zhongj
 * @param <T> the type(extends Enum) of results supplied by this supplier
 * @see java.util.function.Supplier
 */
@FunctionalInterface
public interface EnumSupplier<T extends Enum<T>> extends Supplier<T> {

}