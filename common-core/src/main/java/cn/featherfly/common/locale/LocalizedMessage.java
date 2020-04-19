
package cn.featherfly.common.locale;

import java.util.Locale;

/**
 * <p>
 * LocaleMessage
 * </p>
 * .
 *
 * @author zhongj
 * @since 1.8.2
 */
public interface LocalizedMessage {

    /**
     * Gets the message.
     *
     * @param args the args
     * @return the message
     */
    default String getMessage(Object... args) {
        return getMessage(Locale.getDefault(), args);
    }

    /**
     * Gets the message.
     *
     * @param locale the locale
     * @param args   the args
     * @return the message
     */
    String getMessage(Locale locale, Object... args);
}
