package cn.featherfly.common.function;

import java.time.LocalTime;
import java.util.function.Supplier;

/**
 * The Interface LocalTimeSupplier.
 *
 * @author zhongj
 * @see java.util.function.Supplier
 */
@FunctionalInterface
public interface LocalTimeSupplier extends Supplier<LocalTime> {

}