package cn.featherfly.common.function;

import java.util.function.Supplier;

/**
 * The Interface NumberSupplier.
 *
 * @author zhongj
 * @param <T> the generic type
 * @param <T> the type(extends Number) of results supplied by this supplier
 * @see java.util.function.Supplier
 */
@FunctionalInterface
public interface NumberSupplier<T extends Number> extends Supplier<T> {

}