package cn.featherfly.common.function;

import java.util.Date;
import java.util.function.Supplier;

/**
 * The Interface DateSupplier.
 *
 * @author zhongj
 * @param <T> the generic type
 */
@FunctionalInterface
public interface DateSupplier<T extends Date> extends Supplier<T> {

}