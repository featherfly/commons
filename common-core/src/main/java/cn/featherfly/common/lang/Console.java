
package cn.featherfly.common.lang;

/**
 * Console.
 *
 * @author zhongj
 */
public class Console {

    /**
     * Log.
     *
     * @param message the message
     */
    public static void log(String message) {
        log(message, ArrayUtils.EMPTY_OBJECT_ARRAY);
    }

    /**
     * Log.
     *
     * @param messageFormat the message format
     * @param params the params
     */
    public static void log(String messageFormat, Object... params) {
        System.out.println(Str.format(messageFormat, params));
    }

    /**
     * Error.
     *
     * @param message the message
     */
    public static void error(String message) {
        error(message, ArrayUtils.EMPTY_OBJECT_ARRAY);
    }

    /**
     * Error.
     *
     * @param messageFormat the message format
     * @param params the params
     */
    public static void error(String messageFormat, Object... params) {
        System.err.println(Str.format(messageFormat, params));
    }
}
