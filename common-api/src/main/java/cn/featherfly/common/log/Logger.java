
package cn.featherfly.common.log;

import java.util.function.Supplier;

/**
 * Logger.
 *
 * @author zhongj
 */
public interface Logger extends LoggerFunctionArgs {

    /**
     * Return the name of this <code>Logger</code> instance.
     *
     * @return name of this logger instance
     */
    String getName();

    default boolean isEnabled(Level level) {
        if (level == null) {
            return false;
        }
        switch (level) {
            case ERROR:
                return isErrorEnabled();
            case WARN:
                return isWarnEnabled();
            case INFO:
                return isInfoEnabled();
            case DEBUG:
                return isDebugEnabled();
            case TRACE:
                return isTraceEnabled();
            default:
                return false;
        }
    }

    /**
     * Is the logger instance enabled for the TRACE level?
     *
     * @return True if this Logger is enabled for the TRACE level, false
     *         otherwise.
     * @since 1.4
     */
    boolean isTraceEnabled();

    /**
     * Log a message at the TRACE level.
     *
     * @param msg the message string to be logged
     * @since 1.4
     */
    void trace(String msg);

    /**
     * Log a message at the TRACE level according to the specified format and
     * argument.
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the TRACE level.
     * </p>
     *
     * @param format the format string
     * @param arg the argument
     * @since 1.4
     */
    void trace(String format, Object arg);

    /**
     * Log a message at the TRACE level according to the specified format and
     * arguments.
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the TRACE level.
     * </p>
     *
     * @param format the format string
     * @param arg1 the first argument
     * @param arg2 the second argument
     * @since 1.4
     */
    void trace(String format, Object arg1, Object arg2);

    /**
     * Log a message at the TRACE level according to the specified format and
     * arguments.
     * <p>
     * This form avoids superfluous string concatenation when the logger is
     * disabled for the TRACE level. However, this variant incurs the hidden
     * (and relatively small) cost of creating an <code>Object[]</code> before
     * invoking the method, even if this logger is disabled for TRACE. The
     * variants taking {@link #trace(String, Object) one} and
     * {@link #trace(String, Object, Object) two} arguments exist solely in
     * order to avoid this hidden cost.
     * </p>
     *
     * @param format the format string
     * @param arguments a list of 3 or more arguments
     * @since 1.4
     */
    void trace(String format, Object... arguments);

    /**
     * Log an exception (throwable) at the TRACE level with an accompanying
     * message.
     *
     * @param msg the message accompanying the exception
     * @param t the exception (throwable) to log
     * @since 1.4
     */
    void trace(String msg, Throwable t);

    /**
     * trace.
     *
     * @param msg the msg
     * @param supplier the supplier
     */
    @Override
    default <A> void trace(String msg, Supplier<A[]> supplier) {
        if (isTraceEnabled()) {
            trace(msg, supplier.get());
        }
    }

    /**
     * Is the logger instance enabled for the DEBUG level?
     *
     * @return True if this Logger is enabled for the DEBUG level, false
     *         otherwise.
     */
    boolean isDebugEnabled();

    /**
     * Log a message at the DEBUG level.
     *
     * @param msg the message string to be logged
     */
    void debug(String msg);

    /**
     * Log a message at the DEBUG level according to the specified format and
     * argument.
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the DEBUG level.
     * </p>
     *
     * @param format the format string
     * @param arg the argument
     */
    void debug(String format, Object arg);

    /**
     * Log a message at the DEBUG level according to the specified format and
     * arguments.
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the DEBUG level.
     * </p>
     *
     * @param format the format string
     * @param arg1 the first argument
     * @param arg2 the second argument
     */
    void debug(String format, Object arg1, Object arg2);

    /**
     * Log a message at the DEBUG level according to the specified format and
     * arguments.
     * <p>
     * This form avoids superfluous string concatenation when the logger is
     * disabled for the DEBUG level. However, this variant incurs the hidden
     * (and relatively small) cost of creating an <code>Object[]</code> before
     * invoking the method, even if this logger is disabled for DEBUG. The
     * variants taking {@link #debug(String, Object) one} and
     * {@link #debug(String, Object, Object) two} arguments exist solely in
     * order to avoid this hidden cost.
     * </p>
     *
     * @param format the format string
     * @param arguments a list of 3 or more arguments
     */
    void debug(String format, Object... arguments);

    /**
     * Log an exception (throwable) at the DEBUG level with an accompanying
     * message.
     *
     * @param msg the message accompanying the exception
     * @param t the exception (throwable) to log
     */
    void debug(String msg, Throwable t);

    /**
     * debug.
     *
     * @param msg the msg
     * @param supplier the supplier
     */
    @Override
    default <A> void debug(String msg, Supplier<A[]> supplier) {
        if (isDebugEnabled()) {
            debug(msg, supplier.get());
        }
    }

    /**
     * Is the logger instance enabled for the INFO level?
     *
     * @return True if this Logger is enabled for the INFO level, false
     *         otherwise.
     */
    boolean isInfoEnabled();

    /**
     * Log a message at the INFO level.
     *
     * @param msg the message string to be logged
     */
    void info(String msg);

    /**
     * Log a message at the INFO level according to the specified format and
     * argument.
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the INFO level.
     * </p>
     *
     * @param format the format string
     * @param arg the argument
     */
    void info(String format, Object arg);

    /**
     * Log a message at the INFO level according to the specified format and
     * arguments.
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the INFO level.
     * </p>
     *
     * @param format the format string
     * @param arg1 the first argument
     * @param arg2 the second argument
     */
    void info(String format, Object arg1, Object arg2);

    /**
     * Log a message at the INFO level according to the specified format and
     * arguments.
     * <p>
     * This form avoids superfluous string concatenation when the logger is
     * disabled for the INFO level. However, this variant incurs the hidden (and
     * relatively small) cost of creating an <code>Object[]</code> before
     * invoking the method, even if this logger is disabled for INFO. The
     * variants taking {@link #info(String, Object) one} and
     * {@link #info(String, Object, Object) two} arguments exist solely in order
     * to avoid this hidden cost.
     * </p>
     *
     * @param format the format string
     * @param arguments a list of 3 or more arguments
     */
    void info(String format, Object... arguments);

    /**
     * Log an exception (throwable) at the INFO level with an accompanying
     * message.
     *
     * @param msg the message accompanying the exception
     * @param t the exception (throwable) to log
     */
    void info(String msg, Throwable t);

    /**
     * info.
     *
     * @param msg the msg
     * @param supplier the supplier
     */
    @Override
    default <A> void info(String msg, Supplier<A[]> supplier) {
        if (isInfoEnabled()) {
            info(msg, supplier.get());
        }
    }

    /**
     * Is the logger instance enabled for the WARN level?
     *
     * @return True if this Logger is enabled for the WARN level, false
     *         otherwise.
     */
    boolean isWarnEnabled();

    /**
     * Log a message at the WARN level.
     *
     * @param msg the message string to be logged
     */
    void warn(String msg);

    /**
     * Log a message at the WARN level according to the specified format and
     * argument.
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the WARN level.
     * </p>
     *
     * @param format the format string
     * @param arg the argument
     */
    void warn(String format, Object arg);

    /**
     * Log a message at the WARN level according to the specified format and
     * arguments.
     * <p>
     * This form avoids superfluous string concatenation when the logger is
     * disabled for the WARN level. However, this variant incurs the hidden (and
     * relatively small) cost of creating an <code>Object[]</code> before
     * invoking the method, even if this logger is disabled for WARN. The
     * variants taking {@link #warn(String, Object) one} and
     * {@link #warn(String, Object, Object) two} arguments exist solely in order
     * to avoid this hidden cost.
     * </p>
     *
     * @param format the format string
     * @param arguments a list of 3 or more arguments
     */
    void warn(String format, Object... arguments);

    /**
     * Log a message at the WARN level according to the specified format and
     * arguments.
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the WARN level.
     * </p>
     *
     * @param format the format string
     * @param arg1 the first argument
     * @param arg2 the second argument
     */
    void warn(String format, Object arg1, Object arg2);

    /**
     * Log an exception (throwable) at the WARN level with an accompanying
     * message.
     *
     * @param msg the message accompanying the exception
     * @param t the exception (throwable) to log
     */
    void warn(String msg, Throwable t);

    /**
     * warn.
     *
     * @param msg the msg
     * @param supplier the supplier
     */
    @Override
    default <A> void warn(String msg, Supplier<A[]> supplier) {
        if (isWarnEnabled()) {
            warn(msg, supplier.get());
        }
    }

    /**
     * Is the logger instance enabled for the ERROR level?
     *
     * @return True if this Logger is enabled for the ERROR level, false
     *         otherwise.
     */
    boolean isErrorEnabled();

    /**
     * Log a message at the ERROR level.
     *
     * @param msg the message string to be logged
     */
    void error(String msg);

    /**
     * Log a message at the ERROR level according to the specified format and
     * argument.
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the ERROR level.
     * </p>
     *
     * @param format the format string
     * @param arg the argument
     */
    void error(String format, Object arg);

    /**
     * Log a message at the ERROR level according to the specified format and
     * arguments.
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the ERROR level.
     * </p>
     *
     * @param format the format string
     * @param arg1 the first argument
     * @param arg2 the second argument
     */
    void error(String format, Object arg1, Object arg2);

    /**
     * Log a message at the ERROR level according to the specified format and
     * arguments.
     * <p>
     * This form avoids superfluous string concatenation when the logger is
     * disabled for the ERROR level. However, this variant incurs the hidden
     * (and relatively small) cost of creating an <code>Object[]</code> before
     * invoking the method, even if this logger is disabled for ERROR. The
     * variants taking {@link #error(String, Object) one} and
     * {@link #error(String, Object, Object) two} arguments exist solely in
     * order to avoid this hidden cost.
     * </p>
     *
     * @param format the format string
     * @param arguments a list of 3 or more arguments
     */
    void error(String format, Object... arguments);

    /**
     * Log an exception (throwable) at the ERROR level with an accompanying
     * message.
     *
     * @param msg the message accompanying the exception
     * @param t the exception (throwable) to log
     */
    void error(String msg, Throwable t);

    /**
     * error.
     *
     * @param msg the msg
     * @param supplier the supplier
     */
    @Override
    default <A> void error(String msg, Supplier<A[]> supplier) {
        if (isErrorEnabled()) {
            error(msg, supplier.get());
        }
    }

    //    /**
    //     * trace.
    //     *
    //     * @param msg      the msg
    //     * @param supplier the supplier
    //     */
    //    void trace(String msg, MapSupplier<String, Object> supplier);
    //
    //    /**
    //     * debug.
    //     *
    //     * @param msg      the msg
    //     * @param supplier the supplier
    //     */
    //    void debug(String msg, MapSupplier<String, Object> supplier);
    //
    //    /**
    //     * info.
    //     *
    //     * @param msg      the msg
    //     * @param supplier the supplier
    //     */
    //    void info(String msg, MapSupplier<String, Object> supplier);
    //
    //    /**
    //     * warn.
    //     *
    //     * @param msg      the msg
    //     * @param supplier the supplier
    //     */
    //    void warn(String msg, MapSupplier<String, Object> supplier);
    //
    //    /**
    //     * error.
    //     *
    //     * @param msg      the msg
    //     * @param supplier the supplier
    //     */
    //    void error(String msg, MapSupplier<String, Object> supplier);
}