package cn.featherfly.common.function;

import java.util.function.Supplier;

/**
 * The Interface NumberSupplier.
 *
 * @author zhongj
 * @param <T> the generic type
 */
@FunctionalInterface
public interface NumberSupplier<T extends Number> extends Supplier<T> {

}