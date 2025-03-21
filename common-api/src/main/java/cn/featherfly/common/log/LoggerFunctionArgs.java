
package cn.featherfly.common.log;

import java.util.function.Supplier;

/**
 * LoggerFunctionArgs.
 *
 * @author zhongj
 */
public interface LoggerFunctionArgs {

    /**
     * trace.
     *
     * @param <A> the generic type
     * @param msg the msg
     * @param supplier the supplier
     */
    <A> void trace(String msg, Supplier<A[]> supplier);

    /**
     * debug.
     *
     * @param <A> the generic type
     * @param msg the msg
     * @param supplier the supplier
     */
    <A> void debug(String msg, Supplier<A[]> supplier);

    /**
     * info.
     *
     * @param <A> the generic type
     * @param msg the msg
     * @param supplier the supplier
     */
    <A> void info(String msg, Supplier<A[]> supplier);

    /**
     * warn.
     *
     * @param <A> the generic type
     * @param msg the msg
     * @param supplier the supplier
     */
    <A> void warn(String msg, Supplier<A[]> supplier);

    /**
     * error.
     *
     * @param <A> the generic type
     * @param msg the msg
     * @param supplier the supplier
     */
    <A> void error(String msg, Supplier<A[]> supplier);
}
