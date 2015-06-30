
package cn.featherfly.common.logging;

/**
 * <p>
 * LoggerSlf4jProxy
 * </p>
 * 
 * @author 钟冀
 * @version 1.3
 * @since 1.3
 */
public class Slf4jLoggerProxy implements Logger {

    private org.slf4j.Logger logger;

    /**
     * @param logger
     *            silf4j logger
     */
    public Slf4jLoggerProxy(org.slf4j.Logger logger) {
        this.logger = logger;
    }

    /**
     * @return
     * @see org.slf4j.Logger#getName()
     */
    @Override
    public String getName() {
        return logger.getName();
    }

    /**
     * @return
     * @see org.slf4j.Logger#isTraceEnabled()
     */
    @Override
    public boolean isTraceEnabled() {
        return logger.isTraceEnabled();
    }

    /**
     * @param msg
     * @see org.slf4j.Logger#trace(java.lang.String)
     */
    @Override
    public void trace(String msg) {
        logger.trace(msg);
    }

    /**
     * @param format
     * @param arg
     * @see org.slf4j.Logger#trace(java.lang.String, java.lang.Object)
     */
    @Override
    public void trace(String format, Object arg) {
        logger.trace(format, arg);
    }

    /**
     * @param format
     * @param arg1
     * @param arg2
     * @see org.slf4j.Logger#trace(java.lang.String, java.lang.Object,
     *      java.lang.Object)
     */
    @Override
    public void trace(String format, Object arg1, Object arg2) {
        logger.trace(format, arg1, arg2);
    }

    /**
     * @param format
     * @param arguments
     * @see org.slf4j.Logger#trace(java.lang.String, java.lang.Object[])
     */
    @Override
    public void trace(String format, Object... arguments) {
        logger.trace(format, arguments);
    }

    /**
     * @param msg
     * @param t
     * @see org.slf4j.Logger#trace(java.lang.String, java.lang.Throwable)
     */
    @Override
    public void trace(String msg, Throwable t) {
        logger.trace(msg, t);
    }

    /**
     * @return
     * @see org.slf4j.Logger#isDebugEnabled()
     */
    @Override
    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    /**
     * @param msg
     * @see org.slf4j.Logger#debug(java.lang.String)
     */
    @Override
    public void debug(String msg) {
        logger.debug(msg);
    }

    /**
     * @param format
     * @param arg
     * @see org.slf4j.Logger#debug(java.lang.String, java.lang.Object)
     */
    @Override
    public void debug(String format, Object arg) {
        logger.debug(format, arg);
    }

    /**
     * @param format
     * @param arg1
     * @param arg2
     * @see org.slf4j.Logger#debug(java.lang.String, java.lang.Object,
     *      java.lang.Object)
     */
    @Override
    public void debug(String format, Object arg1, Object arg2) {
        logger.debug(format, arg1, arg2);
    }

    /**
     * @param format
     * @param arguments
     * @see org.slf4j.Logger#debug(java.lang.String, java.lang.Object[])
     */
    @Override
    public void debug(String format, Object... arguments) {
        logger.debug(format, arguments);
    }

    /**
     * @param msg
     * @param t
     * @see org.slf4j.Logger#debug(java.lang.String, java.lang.Throwable)
     */
    @Override
    public void debug(String msg, Throwable t) {
        logger.debug(msg, t);
    }

    /**
     * @return
     * @see org.slf4j.Logger#isInfoEnabled()
     */
    @Override
    public boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

    /**
     * @param msg
     * @see org.slf4j.Logger#info(java.lang.String)
     */
    @Override
    public void info(String msg) {
        logger.info(msg);
    }

    /**
     * @param format
     * @param arg
     * @see org.slf4j.Logger#info(java.lang.String, java.lang.Object)
     */
    @Override
    public void info(String format, Object arg) {
        logger.info(format, arg);
    }

    /**
     * @param format
     * @param arg1
     * @param arg2
     * @see org.slf4j.Logger#info(java.lang.String, java.lang.Object,
     *      java.lang.Object)
     */
    @Override
    public void info(String format, Object arg1, Object arg2) {
        logger.info(format, arg1, arg2);
    }

    /**
     * @param format
     * @param arguments
     * @see org.slf4j.Logger#info(java.lang.String, java.lang.Object[])
     */
    @Override
    public void info(String format, Object... arguments) {
        logger.info(format, arguments);
    }

    /**
     * @param msg
     * @param t
     * @see org.slf4j.Logger#info(java.lang.String, java.lang.Throwable)
     */
    @Override
    public void info(String msg, Throwable t) {
        logger.info(msg, t);
    }

    /**
     * @return
     * @see org.slf4j.Logger#isWarnEnabled()
     */
    @Override
    public boolean isWarnEnabled() {
        return logger.isWarnEnabled();
    }

    /**
     * @param msg
     * @see org.slf4j.Logger#warn(java.lang.String)
     */
    @Override
    public void warn(String msg) {
        logger.warn(msg);
    }

    /**
     * @param format
     * @param arg
     * @see org.slf4j.Logger#warn(java.lang.String, java.lang.Object)
     */
    @Override
    public void warn(String format, Object arg) {
        logger.warn(format, arg);
    }

    /**
     * @param format
     * @param arguments
     * @see org.slf4j.Logger#warn(java.lang.String, java.lang.Object[])
     */
    @Override
    public void warn(String format, Object... arguments) {
        logger.warn(format, arguments);
    }

    /**
     * @param format
     * @param arg1
     * @param arg2
     * @see org.slf4j.Logger#warn(java.lang.String, java.lang.Object,
     *      java.lang.Object)
     */
    @Override
    public void warn(String format, Object arg1, Object arg2) {
        logger.warn(format, arg1, arg2);
    }

    /**
     * @param msg
     * @param t
     * @see org.slf4j.Logger#warn(java.lang.String, java.lang.Throwable)
     */
    @Override
    public void warn(String msg, Throwable t) {
        logger.warn(msg, t);
    }

    /**
     * @return
     * @see org.slf4j.Logger#isErrorEnabled()
     */
    @Override
    public boolean isErrorEnabled() {
        return logger.isErrorEnabled();
    }

    /**
     * @param msg
     * @see org.slf4j.Logger#error(java.lang.String)
     */
    @Override
    public void error(String msg) {
        logger.error(msg);
    }

    /**
     * @param format
     * @param arg
     * @see org.slf4j.Logger#error(java.lang.String, java.lang.Object)
     */
    @Override
    public void error(String format, Object arg) {
        logger.error(format, arg);
    }

    /**
     * @param format
     * @param arg1
     * @param arg2
     * @see org.slf4j.Logger#error(java.lang.String, java.lang.Object,
     *      java.lang.Object)
     */
    @Override
    public void error(String format, Object arg1, Object arg2) {
        logger.error(format, arg1, arg2);
    }

    /**
     * @param format
     * @param arguments
     * @see org.slf4j.Logger#error(java.lang.String, java.lang.Object[])
     */
    @Override
    public void error(String format, Object... arguments) {
        logger.error(format, arguments);
    }

    /**
     * @param msg
     * @param t
     * @see org.slf4j.Logger#error(java.lang.String, java.lang.Throwable)
     */
    @Override
    public void error(String msg, Throwable t) {
        logger.error(msg, t);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void log(LoggerLevel level, String msg) {
        switch (level) {
        case DEBUG:
            debug(msg);
            break;
        case ERROR:
            error(msg);
            break;
        case WARN:
            warn(msg);
            break;
        case INFO:
            info(msg);
            break;
        case TRACE:
            trace(msg);
            break;
        default:
            break;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void log(LoggerLevel level, String format, Object arg) {
        switch (level) {
        case DEBUG:
            debug(format, arg);
            break;
        case ERROR:
            error(format, arg);
            break;
        case WARN:
            warn(format, arg);
            break;
        case INFO:
            info(format, arg);
            break;
        case TRACE:
            trace(format, arg);
            break;
        default:
            break;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void log(LoggerLevel level, String format, Object arg1,
            Object arg2) {
        switch (level) {
        case DEBUG:
            debug(format, arg1, arg2);
            break;
        case ERROR:
            error(format, arg1, arg2);
            break;
        case WARN:
            warn(format, arg1, arg2);
            break;
        case INFO:
            info(format, arg1, arg2);
            break;
        case TRACE:
            trace(format, arg1, arg2);
            break;
        default:
            break;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void log(LoggerLevel level, String format, Object... args) {
        switch (level) {
        case DEBUG:
            debug(format, args);
            break;
        case ERROR:
            error(format, args);
            break;
        case WARN:
            warn(format, args);
            break;
        case INFO:
            info(format, args);
            break;
        case TRACE:
            trace(format, args);
            break;
        default:
            break;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEnabled(LoggerLevel level) {
        boolean result = false;
        switch (level) {
        case DEBUG:
            result = isDebugEnabled();
            break;
        case ERROR:
            result = isErrorEnabled();
            break;
        case WARN:
            result = isWarnEnabled();
            break;
        case INFO:
            result = isInfoEnabled();
            break;
        case TRACE:
            result = isTraceEnabled();
            break;
        default:
            break;
        }
        return result;
    }

}
