
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
     * @param msg the msg
     * @param supplier the supplier
     */
    <A> void trace(String msg, Supplier<A[]> supplier);

    /**
     * debug.
     *
     * @param msg the msg
     * @param supplier the supplier
     */
    <A> void debug(String msg, Supplier<A[]> supplier);

    /**
     * info.
     *
     * @param msg the msg
     * @param supplier the supplier
     */
    <A> void info(String msg, Supplier<A[]> supplier);

    /**
     * warn.
     *
     * @param msg the msg
     * @param supplier the supplier
     */
    <A> void warn(String msg, Supplier<A[]> supplier);

    /**
     * error.
     *
     * @param msg the msg
     * @param supplier the supplier
     */
    <A> void error(String msg, Supplier<A[]> supplier);
}
