
package cn.featherfly.common.log;

import java.util.function.Supplier;

/**
 * <p>
 * Logger
 * </p>
 *
 * @author zhongj
 */
public interface Logger extends org.slf4j.Logger {
    /**
     * trace
     *
     * @param msg
     * @param supplier
     */
    void trace(String msg, Supplier<Object[]> supplier);

    /**
     * debug
     *
     * @param msg
     * @param supplier
     */
    void debug(String msg, Supplier<Object[]> supplier);

    /**
     * info
     *
     * @param msg
     * @param supplier
     */
    void info(String msg, Supplier<Object[]> supplier);

    /**
     * warn
     *
     * @param msg
     * @param supplier
     */
    void warn(String msg, Supplier<Object[]> supplier);

    /**
     * error
     *
     * @param msg
     * @param supplier
     */
    void error(String msg, Supplier<Object[]> supplier);
}