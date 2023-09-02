
package cn.featherfly.common.lang;

import java.util.Date;

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
     * @param params        the params
     */
    public static void log(String messageFormat, Object... params) {
        System.out.println(Strings.format(messageFormat, params));
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
     * @param params        the params
     */
    public static void error(String messageFormat, Object... params) {
        System.err.println(Strings.format(messageFormat, params));
    }

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        Console.log("test console");
        Console.error("test console");
        String time = Dates.formatTime(new Date());
        Console.log("test console at {}", time);
        Console.error("test console at {}", time);

        String name = "yufei";
        int age = 18;
        Object[] params = new Object[] { time, name, age };

        Console.log("test console at {}", params);
        Console.error("test console at {}", params);
    }
}
