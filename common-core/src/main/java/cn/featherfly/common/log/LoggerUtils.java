
package cn.featherfly.common.log;

import java.util.HashMap;
import java.util.Map;

import cn.featherfly.common.lang.LangUtils;

/**
 * <p>
 * LoggerUtils
 * </p>
 *
 * @author zhongj
 */
public class LoggerUtils {

    public static Map<String, Logger> loggers = new HashMap<>();

    public static Logger logger() {
        String className = LangUtils.getInvoker().getClassName();
        Logger logger = loggers.get(className);
        if (logger == null) {
            synchronized (loggers) {
                if (logger == null) {
                    logger = LoggerFactory.getLogger(className);
                    loggers.put(className, logger);
                }
            }
        }
        return logger;
    }

    //
    //    /**
    //     * @param msg
    //     * @see cn.featherfly.common.log.Logger#debug(java.lang.String)
    //     */
    //    public static void debug(String msg) {
    //        getLogger().debug(msg);
    //    }
    //
    //    /**
    //     * @param msg
    //     * @param loggerEnabled
    //     * @see cn.featherfly.common.log.Logger#trace(java.lang.String,
    //     *      cn.featherfly.common.log.LoggerEnabled)
    //     */
    //    public static void trace(String msg, LoggerEnabled loggerEnabled) {
    //        getLogger().trace(msg, loggerEnabled);
    //    }
    //
    //    /**
    //     * @param msg
    //     * @param loggerEnabled
    //     * @see cn.featherfly.common.log.Logger#debug(java.lang.String,
    //     *      cn.featherfly.common.log.LoggerEnabled)
    //     */
    //    public static void debug(String msg, LoggerEnabled loggerEnabled) {
    //        getLogger().debug(msg, loggerEnabled);
    //    }
    //
    //    /**
    //     * @param msg
    //     * @param loggerEnabled
    //     * @see cn.featherfly.common.log.Logger#info(java.lang.String,
    //     *      cn.featherfly.common.log.LoggerEnabled)
    //     */
    //    public static void info(String msg, LoggerEnabled loggerEnabled) {
    //        getLogger().info(msg, loggerEnabled);
    //    }
    //
    //    /**
    //     * @param msg
    //     * @param loggerEnabled
    //     * @see cn.featherfly.common.log.Logger#warn(java.lang.String,
    //     *      cn.featherfly.common.log.LoggerEnabled)
    //     */
    //    public static void warn(String msg, LoggerEnabled loggerEnabled) {
    //        getLogger().warn(msg, loggerEnabled);
    //    }
    //
    //    /**
    //     * @param msg
    //     * @param loggerEnabled
    //     * @see cn.featherfly.common.log.Logger#error(java.lang.String,
    //     *      cn.featherfly.common.log.LoggerEnabled)
    //     */
    //    public static void error(String msg, LoggerEnabled loggerEnabled) {
    //        getLogger().error(msg, loggerEnabled);
    //    }
    //
    //    /**
    //     * @return
    //     * @see org.slf4j.Logger#getName()
    //     */
    //    public String getName() {
    //        return getLogger().getName();
    //    }
    //
    //    /**
    //     * @param msg
    //     * @see org.slf4j.Logger#trace(java.lang.String)
    //     */
    //    public static void trace(String msg) {
    //        getLogger().trace(msg);
    //    }
    //
    //    /**
    //     * @param format
    //     * @param arg
    //     * @see org.slf4j.Logger#trace(java.lang.String, java.lang.Object)
    //     */
    //    public static void trace(String format, Object arg) {
    //        getLogger().trace(format, arg);
    //    }
    //
    //    /**
    //     * @param format
    //     * @param arg1
    //     * @param arg2
    //     * @see org.slf4j.Logger#trace(java.lang.String, java.lang.Object,
    //     *      java.lang.Object)
    //     */
    //    public static void trace(String format, Object arg1, Object arg2) {
    //        getLogger().trace(format, arg1, arg2);
    //    }
    //
    //    /**
    //     * @param format
    //     * @param arguments
    //     * @see org.slf4j.Logger#trace(java.lang.String, java.lang.Object[])
    //     */
    //    public static void trace(String format, Object... arguments) {
    //        getLogger().trace(format, arguments);
    //    }
    //
    //    /**
    //     * @param msg
    //     * @param t
    //     * @see org.slf4j.Logger#trace(java.lang.String, java.lang.Throwable)
    //     */
    //    public static void trace(String msg, Throwable t) {
    //        getLogger().trace(msg, t);
    //    }
    //
    //    /**
    //     * @param format
    //     * @param arg
    //     * @see org.slf4j.Logger#debug(java.lang.String, java.lang.Object)
    //     */
    //    public static void debug(String format, Object arg) {
    //        getLogger().debug(format, arg);
    //    }
    //
    //    /**
    //     * @param format
    //     * @param arg1
    //     * @param arg2
    //     * @see org.slf4j.Logger#debug(java.lang.String, java.lang.Object,
    //     *      java.lang.Object)
    //     */
    //    public static void debug(String format, Object arg1, Object arg2) {
    //        getLogger().debug(format, arg1, arg2);
    //    }
    //
    //    /**
    //     * @param format
    //     * @param arguments
    //     * @see org.slf4j.Logger#debug(java.lang.String, java.lang.Object[])
    //     */
    //    public static void debug(String format, Object... arguments) {
    //        getLogger().debug(format, arguments);
    //    }
    //
    //    /**
    //     * @param msg
    //     * @param t
    //     * @see org.slf4j.Logger#debug(java.lang.String, java.lang.Throwable)
    //     */
    //    public static void debug(String msg, Throwable t) {
    //        getLogger().debug(msg, t);
    //    }
    //
    //    /**
    //     * @param msg
    //     * @see org.slf4j.Logger#info(java.lang.String)
    //     */
    //    public static void info(String msg) {
    //        getLogger().info(msg);
    //    }
    //
    //    /**
    //     * @param format
    //     * @param arg
    //     * @see org.slf4j.Logger#info(java.lang.String, java.lang.Object)
    //     */
    //    public static void info(String format, Object arg) {
    //        getLogger().info(format, arg);
    //    }
    //
    //    /**
    //     * @param format
    //     * @param arg1
    //     * @param arg2
    //     * @see org.slf4j.Logger#info(java.lang.String, java.lang.Object,
    //     *      java.lang.Object)
    //     */
    //    public static void info(String format, Object arg1, Object arg2) {
    //        getLogger().info(format, arg1, arg2);
    //    }
    //
    //    /**
    //     * @param format
    //     * @param arguments
    //     * @see org.slf4j.Logger#info(java.lang.String, java.lang.Object[])
    //     */
    //    public static void info(String format, Object... arguments) {
    //        getLogger().info(format, arguments);
    //    }
    //
    //    /**
    //     * @param msg
    //     * @param t
    //     * @see org.slf4j.Logger#info(java.lang.String, java.lang.Throwable)
    //     */
    //    public static void info(String msg, Throwable t) {
    //        getLogger().info(msg, t);
    //    }
    //
    //    /**
    //     * @param msg
    //     * @see org.slf4j.Logger#warn(java.lang.String)
    //     */
    //    public static void warn(String msg) {
    //        getLogger().warn(msg);
    //    }
    //
    //    /**
    //     * @param format
    //     * @param arg
    //     * @see org.slf4j.Logger#warn(java.lang.String, java.lang.Object)
    //     */
    //    public static void warn(String format, Object arg) {
    //        getLogger().warn(format, arg);
    //    }
    //
    //    /**
    //     * @param format
    //     * @param arguments
    //     * @see org.slf4j.Logger#warn(java.lang.String, java.lang.Object[])
    //     */
    //    public static void warn(String format, Object... arguments) {
    //        getLogger().warn(format, arguments);
    //    }
    //
    //    /**
    //     * @param format
    //     * @param arg1
    //     * @param arg2
    //     * @see org.slf4j.Logger#warn(java.lang.String, java.lang.Object,
    //     *      java.lang.Object)
    //     */
    //    public static void warn(String format, Object arg1, Object arg2) {
    //        getLogger().warn(format, arg1, arg2);
    //    }
    //
    //    /**
    //     * @param msg
    //     * @param t
    //     * @see org.slf4j.Logger#warn(java.lang.String, java.lang.Throwable)
    //     */
    //    public static void warn(String msg, Throwable t) {
    //        getLogger().warn(msg, t);
    //    }
    //
    //    /**
    //     * @param msg
    //     * @see org.slf4j.Logger#error(java.lang.String)
    //     */
    //    public static void error(String msg) {
    //        getLogger().error(msg);
    //    }
    //
    //    /**
    //     * @param format
    //     * @param arg
    //     * @see org.slf4j.Logger#error(java.lang.String, java.lang.Object)
    //     */
    //    public static void error(String format, Object arg) {
    //        getLogger().error(format, arg);
    //    }
    //
    //    /**
    //     * @param format
    //     * @param arg1
    //     * @param arg2
    //     * @see org.slf4j.Logger#error(java.lang.String, java.lang.Object,
    //     *      java.lang.Object)
    //     */
    //    public static void error(String format, Object arg1, Object arg2) {
    //        getLogger().error(format, arg1, arg2);
    //    }
    //
    //    /**
    //     * @param format
    //     * @param arguments
    //     * @see org.slf4j.Logger#error(java.lang.String, java.lang.Object[])
    //     */
    //    public static void error(String format, Object... arguments) {
    //        getLogger().error(format, arguments);
    //    }
    //
    //    /**
    //     * @param msg
    //     * @param t
    //     * @see org.slf4j.Logger#error(java.lang.String, java.lang.Throwable)
    //     */
    //    public static void error(String msg, Throwable t) {
    //        getLogger().error(msg, t);
    //    }
}
