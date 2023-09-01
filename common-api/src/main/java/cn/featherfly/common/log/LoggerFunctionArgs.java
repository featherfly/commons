
package cn.featherfly.common.log;

import cn.featherfly.common.function.ArraySupplier;

/**
 * LoggerFunctionArgs.
 *
 * @author zhongj
 */
public interface LoggerFunctionArgs {
    /**
     * trace.
     *
     * @param msg      the msg
     * @param supplier the supplier
     */
    void trace(String msg, ArraySupplier<Object> supplier);

    /**
     * debug.
     *
     * @param msg      the msg
     * @param supplier the supplier
     */
    void debug(String msg, ArraySupplier<Object> supplier);

    /**
     * info.
     *
     * @param msg      the msg
     * @param supplier the supplier
     */
    void info(String msg, ArraySupplier<Object> supplier);

    /**
     * warn.
     *
     * @param msg      the msg
     * @param supplier the supplier
     */
    void warn(String msg, ArraySupplier<Object> supplier);

    /**
     * error.
     *
     * @param msg      the msg
     * @param supplier the supplier
     */
    void error(String msg, ArraySupplier<Object> supplier);
}
