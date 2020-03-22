
package cn.featherfly.common.log;

/**
 * <p>
 * Logger
 * </p>
 * <p>
 * 2019-08-29
 * </p>
 *
 * @author zhongj
 */
public interface Logger extends org.slf4j.Logger {
    /**
     * trace
     *
     * @param msg
     * @param loggerEnabled
     */
    void trace(String msg, LoggerEnabled loggerEnabled);

    /**
     * debug
     *
     * @param msg
     * @param loggerEnabled
     */
    void debug(String msg, LoggerEnabled loggerEnabled);

    /**
     * info
     *
     * @param msg
     * @param loggerEnabled
     */
    void info(String msg, LoggerEnabled loggerEnabled);

    /**
     * warn
     *
     * @param msg
     * @param loggerEnabled
     */
    void warn(String msg, LoggerEnabled loggerEnabled);

    /**
     * error
     *
     * @param msg
     * @param loggerEnabled
     */
    void error(String msg, LoggerEnabled loggerEnabled);
}