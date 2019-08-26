
package cn.featherfly.common.lang;

import org.slf4j.Logger;
import org.slf4j.Marker;

/**
 * <p>
 * LoggerWrapper
 * </p>
 * <p>
 * 2019-08-26
 * </p>
 *
 * @author zhongj
 */
public class LoggerWrapper implements Logger {

    private Logger logger;

    /**
     * @param logger
     */
    public LoggerWrapper(Logger logger) {
        super();
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
     * trace
     *
     * @param msg
     * @param loggerEnabled
     */
    public void trace(String msg, LoggerEnabled loggerEnabled) {
        if (isTraceEnabled()) {
            trace(msg, loggerEnabled.arguments());
        }
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
     * @param marker
     * @return
     * @see org.slf4j.Logger#isTraceEnabled(org.slf4j.Marker)
     */
    @Override
    public boolean isTraceEnabled(Marker marker) {
        return logger.isTraceEnabled(marker);
    }

    /**
     * @param marker
     * @param msg
     * @see org.slf4j.Logger#trace(org.slf4j.Marker, java.lang.String)
     */
    @Override
    public void trace(Marker marker, String msg) {
        logger.trace(marker, msg);
    }

    /**
     * @param marker
     * @param format
     * @param arg
     * @see org.slf4j.Logger#trace(org.slf4j.Marker, java.lang.String,
     *      java.lang.Object)
     */
    @Override
    public void trace(Marker marker, String format, Object arg) {
        logger.trace(marker, format, arg);
    }

    /**
     * @param marker
     * @param format
     * @param arg1
     * @param arg2
     * @see org.slf4j.Logger#trace(org.slf4j.Marker, java.lang.String,
     *      java.lang.Object, java.lang.Object)
     */
    @Override
    public void trace(Marker marker, String format, Object arg1, Object arg2) {
        logger.trace(marker, format, arg1, arg2);
    }

    /**
     * @param marker
     * @param format
     * @param argArray
     * @see org.slf4j.Logger#trace(org.slf4j.Marker, java.lang.String,
     *      java.lang.Object[])
     */
    @Override
    public void trace(Marker marker, String format, Object... argArray) {
        logger.trace(marker, format, argArray);
    }

    /**
     * @param marker
     * @param msg
     * @param t
     * @see org.slf4j.Logger#trace(org.slf4j.Marker, java.lang.String,
     *      java.lang.Throwable)
     */
    @Override
    public void trace(Marker marker, String msg, Throwable t) {
        logger.trace(marker, msg, t);
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
     * {@inheritDoc}
     */
    @Override
    public void debug(String format, Object arg) {
        logger.debug(format, arg);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void debug(String format, Object arg1, Object arg2) {
        logger.debug(format, arg1, arg2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void debug(String format, Object... arguments) {
        logger.debug(format, arguments);
    }

    /**
     * debug
     *
     * @param msg
     * @param loggerEnabled
     */
    public void debug(String msg, LoggerEnabled loggerEnabled) {
        if (isDebugEnabled()) {
            debug(msg, loggerEnabled.arguments());
        }
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
     * @param marker
     * @return
     * @see org.slf4j.Logger#isDebugEnabled(org.slf4j.Marker)
     */
    @Override
    public boolean isDebugEnabled(Marker marker) {
        return logger.isDebugEnabled(marker);
    }

    /**
     * @param marker
     * @param msg
     * @see org.slf4j.Logger#debug(org.slf4j.Marker, java.lang.String)
     */
    @Override
    public void debug(Marker marker, String msg) {
        logger.debug(marker, msg);
    }

    /**
     * @param marker
     * @param format
     * @param arg
     * @see org.slf4j.Logger#debug(org.slf4j.Marker, java.lang.String,
     *      java.lang.Object)
     */
    @Override
    public void debug(Marker marker, String format, Object arg) {
        logger.debug(marker, format, arg);
    }

    /**
     * @param marker
     * @param format
     * @param arg1
     * @param arg2
     * @see org.slf4j.Logger#debug(org.slf4j.Marker, java.lang.String,
     *      java.lang.Object, java.lang.Object)
     */
    @Override
    public void debug(Marker marker, String format, Object arg1, Object arg2) {
        logger.debug(marker, format, arg1, arg2);
    }

    /**
     * @param marker
     * @param format
     * @param arguments
     * @see org.slf4j.Logger#debug(org.slf4j.Marker, java.lang.String,
     *      java.lang.Object[])
     */
    @Override
    public void debug(Marker marker, String format, Object... arguments) {
        logger.debug(marker, format, arguments);
    }

    /**
     * @param marker
     * @param msg
     * @param t
     * @see org.slf4j.Logger#debug(org.slf4j.Marker, java.lang.String,
     *      java.lang.Throwable)
     */
    @Override
    public void debug(Marker marker, String msg, Throwable t) {
        logger.debug(marker, msg, t);
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
     * info
     *
     * @param msg
     * @param loggerEnabled
     */
    public void info(String msg, LoggerEnabled loggerEnabled) {
        if (isInfoEnabled()) {
            info(msg, loggerEnabled.arguments());
        }
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
     * @param marker
     * @return
     * @see org.slf4j.Logger#isInfoEnabled(org.slf4j.Marker)
     */
    @Override
    public boolean isInfoEnabled(Marker marker) {
        return logger.isInfoEnabled(marker);
    }

    /**
     * @param marker
     * @param msg
     * @see org.slf4j.Logger#info(org.slf4j.Marker, java.lang.String)
     */
    @Override
    public void info(Marker marker, String msg) {
        logger.info(marker, msg);
    }

    /**
     * @param marker
     * @param format
     * @param arg
     * @see org.slf4j.Logger#info(org.slf4j.Marker, java.lang.String,
     *      java.lang.Object)
     */
    @Override
    public void info(Marker marker, String format, Object arg) {
        logger.info(marker, format, arg);
    }

    /**
     * @param marker
     * @param format
     * @param arg1
     * @param arg2
     * @see org.slf4j.Logger#info(org.slf4j.Marker, java.lang.String,
     *      java.lang.Object, java.lang.Object)
     */
    @Override
    public void info(Marker marker, String format, Object arg1, Object arg2) {
        logger.info(marker, format, arg1, arg2);
    }

    /**
     * @param marker
     * @param format
     * @param arguments
     * @see org.slf4j.Logger#info(org.slf4j.Marker, java.lang.String,
     *      java.lang.Object[])
     */
    @Override
    public void info(Marker marker, String format, Object... arguments) {
        logger.info(marker, format, arguments);
    }

    /**
     * @param marker
     * @param msg
     * @param t
     * @see org.slf4j.Logger#info(org.slf4j.Marker, java.lang.String,
     *      java.lang.Throwable)
     */
    @Override
    public void info(Marker marker, String msg, Throwable t) {
        logger.info(marker, msg, t);
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
     * warn
     *
     * @param msg
     * @param loggerEnabled
     */
    public void warn(String msg, LoggerEnabled loggerEnabled) {
        if (isWarnEnabled()) {
            warn(msg, loggerEnabled.arguments());
        }
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
     * @param marker
     * @return
     * @see org.slf4j.Logger#isWarnEnabled(org.slf4j.Marker)
     */
    @Override
    public boolean isWarnEnabled(Marker marker) {
        return logger.isWarnEnabled(marker);
    }

    /**
     * @param marker
     * @param msg
     * @see org.slf4j.Logger#warn(org.slf4j.Marker, java.lang.String)
     */
    @Override
    public void warn(Marker marker, String msg) {
        logger.warn(marker, msg);
    }

    /**
     * @param marker
     * @param format
     * @param arg
     * @see org.slf4j.Logger#warn(org.slf4j.Marker, java.lang.String,
     *      java.lang.Object)
     */
    @Override
    public void warn(Marker marker, String format, Object arg) {
        logger.warn(marker, format, arg);
    }

    /**
     * @param marker
     * @param format
     * @param arg1
     * @param arg2
     * @see org.slf4j.Logger#warn(org.slf4j.Marker, java.lang.String,
     *      java.lang.Object, java.lang.Object)
     */
    @Override
    public void warn(Marker marker, String format, Object arg1, Object arg2) {
        logger.warn(marker, format, arg1, arg2);
    }

    /**
     * @param marker
     * @param format
     * @param arguments
     * @see org.slf4j.Logger#warn(org.slf4j.Marker, java.lang.String,
     *      java.lang.Object[])
     */
    @Override
    public void warn(Marker marker, String format, Object... arguments) {
        logger.warn(marker, format, arguments);
    }

    /**
     * @param marker
     * @param msg
     * @param t
     * @see org.slf4j.Logger#warn(org.slf4j.Marker, java.lang.String,
     *      java.lang.Throwable)
     */
    @Override
    public void warn(Marker marker, String msg, Throwable t) {
        logger.warn(marker, msg, t);
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
     * {@inheritDoc}
     */
    @Override
    public void error(String format, Object... arguments) {
        logger.error(format, arguments);
    }

    /**
     * error
     *
     * @param msg
     * @param loggerEnabled
     */
    public void error(String msg, LoggerEnabled loggerEnabled) {
        if (isErrorEnabled()) {
            error(msg, loggerEnabled.arguments());
        }
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
     * @param marker
     * @return
     * @see org.slf4j.Logger#isErrorEnabled(org.slf4j.Marker)
     */
    @Override
    public boolean isErrorEnabled(Marker marker) {
        return logger.isErrorEnabled(marker);
    }

    /**
     * @param marker
     * @param msg
     * @see org.slf4j.Logger#error(org.slf4j.Marker, java.lang.String)
     */
    @Override
    public void error(Marker marker, String msg) {
        logger.error(marker, msg);
    }

    /**
     * @param marker
     * @param format
     * @param arg
     * @see org.slf4j.Logger#error(org.slf4j.Marker, java.lang.String,
     *      java.lang.Object)
     */
    @Override
    public void error(Marker marker, String format, Object arg) {
        logger.error(marker, format, arg);
    }

    /**
     * @param marker
     * @param format
     * @param arg1
     * @param arg2
     * @see org.slf4j.Logger#error(org.slf4j.Marker, java.lang.String,
     *      java.lang.Object, java.lang.Object)
     */
    @Override
    public void error(Marker marker, String format, Object arg1, Object arg2) {
        logger.error(marker, format, arg1, arg2);
    }

    /**
     * @param marker
     * @param format
     * @param arguments
     * @see org.slf4j.Logger#error(org.slf4j.Marker, java.lang.String,
     *      java.lang.Object[])
     */
    @Override
    public void error(Marker marker, String format, Object... arguments) {
        logger.error(marker, format, arguments);
    }

    /**
     * @param marker
     * @param msg
     * @param t
     * @see org.slf4j.Logger#error(org.slf4j.Marker, java.lang.String,
     *      java.lang.Throwable)
     */
    @Override
    public void error(Marker marker, String msg, Throwable t) {
        logger.error(marker, msg, t);
    }

}
