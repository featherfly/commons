package cn.featherfly.common.exception;

import java.util.Locale;
import java.util.function.BiFunction;
import java.util.function.Function;

import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.ClassUtils;
import cn.featherfly.common.locale.ResourceBundleUtils;

/**
 * 抛出国际化支持的异常信息.
 *
 * @author zhongj
 * @since 1.7
 * @version 1.7
 */
public final class LocalizedExceptionUtils {

    /**
     * Instantiates a new localized exception utils.
     */
    private LocalizedExceptionUtils() {
    }

    /**
     * 抛出指定类型的异常.
     *
     * @param exceptionType throw exception type
     * @param message       message
     * @param args          消息绑定参数
     */
    public static void throwException(Function<String, ? extends RuntimeException> newException,
            Class<? extends RuntimeException> exceptionType, String message, Object... args) {
        throw newException.apply(getMessage(exceptionType, message, args, null));
    }

    /**
     * 抛出指定类型的异常.
     *
     * @param exceptionType throw exception type
     * @param locale        locale
     * @param message       message
     * @param args          消息绑定参数
     */
    public static void throwException(Function<String, ? extends RuntimeException> newException,
            Class<? extends RuntimeException> exceptionType, Locale locale, String message, Object... args) {
        throw newException.apply(getMessage(exceptionType, message, args, locale));
    }

    /**
     * 抛出指定类型的异常.
     *
     * @param exceptionType throw exception type
     * @param cause         the cause (which is saved for later retrieval by the
     *                      {@link Throwable#getCause()} method). (A null value
     *                      is permitted, and indicates that the cause is
     *                      nonexistent or unknown.)
     * @param message       message
     * @param args          消息绑定参数
     */
    public static void throwException(BiFunction<String, Throwable, ? extends RuntimeException> newException,
            Class<? extends RuntimeException> exceptionType, Throwable cause, String message, Object... args) {
        throw newException.apply(getMessage(exceptionType, message, args, null), cause);
    }

    /**
     * 抛出指定类型的异常.
     *
     * @param exceptionType throw exception type
     * @param cause         the cause (which is saved for later retrieval by the
     *                      {@link Throwable#getCause()} method). (A null value
     *                      is permitted, and indicates that the cause is
     *                      nonexistent or unknown.)
     * @param locale        locale
     * @param message       message
     * @param args          消息绑定参数
     */
    public static void throwException(BiFunction<String, Throwable, ? extends RuntimeException> newException,
            Class<? extends RuntimeException> exceptionType, Throwable cause, Locale locale, String message,
            Object... args) {
        throw newException.apply(getMessage(exceptionType, message, args, locale), cause);
    }

    /**
     * 抛出指定类型的异常.
     *
     * @param exceptionType throw exception type
     * @param message       message
     * @param args          消息绑定参数
     */
    public static void throwException(Class<? extends RuntimeException> exceptionType, String message, Object... args) {
        throw ClassUtils.newInstance(exceptionType, getMessage(exceptionType, message, args, null));
    }

    /**
     * 抛出指定类型的异常.
     *
     * @param exceptionType throw exception type
     * @param locale        locale
     * @param message       message
     * @param args          消息绑定参数
     */
    public static void throwException(Class<? extends RuntimeException> exceptionType, Locale locale, String message,
            Object... args) {
        throw ClassUtils.newInstance(exceptionType, getMessage(exceptionType, message, args, locale));
    }

    /**
     * 抛出指定类型的异常.
     *
     * @param exceptionType throw exception type
     * @param cause         the cause (which is saved for later retrieval by the
     *                      {@link Throwable#getCause()} method). (A null value
     *                      is permitted, and indicates that the cause is
     *                      nonexistent or unknown.)
     * @param message       message
     * @param args          消息绑定参数
     */
    public static void throwException(Class<? extends RuntimeException> exceptionType, Throwable cause, String message,
            Object... args) {
        throw ClassUtils.newInstance(exceptionType, getMessage(exceptionType, message, args, null), cause);
    }

    /**
     * 抛出指定类型的异常.
     *
     * @param exceptionType throw exception type
     * @param cause         the cause (which is saved for later retrieval by the
     *                      {@link Throwable#getCause()} method). (A null value
     *                      is permitted, and indicates that the cause is
     *                      nonexistent or unknown.)
     * @param locale        locale
     * @param message       message
     * @param args          消息绑定参数
     */
    public static void throwException(Class<? extends RuntimeException> exceptionType, Throwable cause, Locale locale,
            String message, Object... args) {
        throw ClassUtils.newInstance(exceptionType, getMessage(exceptionType, message, args, locale), cause);
    }

    private static String getMessage(Class<? extends RuntimeException> exceptionType, String message, Object[] args,
            Locale locale) {
        AssertIllegalArgument.isNotNull(exceptionType, "Class<? extends RuntimeException> exceptionType");
        String msg = null;
        int keyIndex = message.indexOf(ResourceBundleUtils.KEY_SIGN);
        char firstChar = message.charAt(0);
        if (firstChar == ResourceBundleUtils.RESOURCE_SIGN && keyIndex != -1) {
            msg = ResourceBundleUtils.getString(message, args, locale);
        } else if (firstChar == ResourceBundleUtils.KEY_SIGN) {
            msg = ResourceBundleUtils.getString(exceptionType, message.substring(1), args, locale);
        } else {
            msg = message;
        }
        return msg;
    }
}
