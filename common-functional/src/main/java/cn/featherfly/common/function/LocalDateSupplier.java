package cn.featherfly.common.function;

import java.time.LocalDate;
import java.util.function.Supplier;

/**
 * The Interface LocalDateSupplier.
 *
 * @author zhongj
 * @see java.util.function.Supplier
 */
@FunctionalInterface
public interface LocalDateSupplier extends Supplier<LocalDate> {

}