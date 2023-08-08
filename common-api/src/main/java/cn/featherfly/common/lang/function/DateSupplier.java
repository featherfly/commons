package cn.featherfly.common.lang.function;

import java.util.Date;

/**
 * DateSupplier.
 *
 * @author zhongj
 * @deprecated {@link SerializableDateSupplier}
 */
@FunctionalInterface
@Deprecated
public interface DateSupplier<T extends Date> extends SerializableDateSupplier<T> {

}