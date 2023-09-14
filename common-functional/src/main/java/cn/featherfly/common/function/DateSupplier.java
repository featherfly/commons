package cn.featherfly.common.function;

import java.util.Date;
import java.util.function.Supplier;

/**
 * The Interface DateSupplier.
 *
 * @author zhongj
 * @param <T> the type(extends Date) of results supplied by this supplier
 * @see java.util.function.Supplier
 */
@FunctionalInterface
public interface DateSupplier<T extends Date> extends Supplier<T> {

}