package cn.featherfly.common.function;

import java.time.LocalDateTime;
import java.util.function.Supplier;

/**
 * The Interface LocalDateTimeSupplier.
 *
 * @author zhongj
 * @see java.util.function.Supplier
 */
@FunctionalInterface
public interface LocalDateTimeSupplier extends Supplier<LocalDateTime> {

}