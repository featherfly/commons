
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2025-02-26 17:12:26
 * @Copyright: 2025 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.log;

import java.util.function.Supplier;

/**
 * Log.
 *
 * @author zhongj
 */
public class Log {

    private static final Object[] EMPTY_ARRAY = new Object[0];

    private final Logger logger;

    private final Level level;

    private final boolean enable;

    /**
     * Instantiates a new log.
     *
     * @param logger the logger
     * @param level the level
     */
    public Log(Logger logger, Level level) {
        super();
        this.logger = logger;
        enable = logger.isEnabled(level);
        this.level = level;
    }

    /**
     * Return the name of this <code>Log</code> instance.
     *
     * @return name of this logger instance
     */
    public String getName() {
        return logger.getName();
    }

    /**
     * Is the log instance enabled for the it's level?.
     *
     * @return True if this Logger is enabled for the TRACE level, false
     *         otherwise.
     * @since 1.4
     */
    public boolean isEnabled() {
        return enable;
    }

    /**
     * Gets the level.
     *
     * @return the level
     */
    public Level getLevel() {
        return level;
    }

    /**
     * Log a message at the log level.
     *
     * @param msg the message string to be logged
     */
    public void log(String msg) {
        log(msg, EMPTY_ARRAY);
    }

    /**
     * Log a message at the log level according to the specified format and
     * argument.
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the log level.
     * </p>
     *
     * @param format the format string
     * @param arg the argument
     */
    public void log(String format, Object arg) {
        log(format, new Object[] { arg });
    }

    /**
     * Log a message at the log level according to the specified format and
     * arguments.
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the log level.
     * </p>
     *
     * @param format the format string
     * @param arg1 the first argument
     * @param arg2 the second argument
     */
    public void log(String format, Object arg1, Object arg2) {
        log(format, new Object[] { arg1, arg2 });
    }

    /**
     * Log a message at the log level according to the specified format and
     * arguments.
     * <p>
     * This form avoids superfluous string concatenation when the logger is
     * disabled for the log level. However, this variant incurs the hidden
     * (and relatively small) cost of creating an <code>Object[]</code> before
     * invoking the method, even if this logger is disabled for log. The
     * variants taking {@link #log(String, Object) one} and
     * {@link #log(String, Object, Object) two} arguments exist solely in
     * order to avoid this hidden cost.
     * </p>
     *
     * @param format the format string
     * @param arguments a list of 3 or more arguments
     */
    public void log(String format, Object... arguments) {
        switch (level) {
            case ERROR:
                logger.error(format, arguments);
                break;
            case WARN:
                logger.warn(format, arguments);
                break;
            case INFO:
                logger.info(format, arguments);
                break;
            case DEBUG:
                logger.debug(format, arguments);
                break;
            case TRACE:
                logger.trace(format, arguments);
                break;
            default:
                throw new IllegalArgumentException("unknow logger level " + level);
        }
    }

    /**
     * Log an exception (throwable) at the log level with an accompanying
     * message.
     *
     * @param msg the message accompanying the exception
     * @param t the exception (throwable) to log
     */
    public void log(String msg, Throwable t) {
        switch (level) {
            case ERROR:
                logger.error(msg, t);
                break;
            case WARN:
                logger.warn(msg, t);
                break;
            case INFO:
                logger.info(msg, t);
                break;
            case DEBUG:
                logger.debug(msg, t);
                break;
            case TRACE:
                logger.trace(msg, t);
                break;
            default:
                throw new IllegalArgumentException("unknow logger level " + level);
        }
    }

    /**
     * log.
     *
     * @param <A> the generic type
     * @param msg the msg
     * @param supplier the arugments array supplier
     */
    public <A> void log(String msg, Supplier<A[]> supplier) {
        if (isEnabled()) {
            log(msg, supplier.get());
        }
    }
}
