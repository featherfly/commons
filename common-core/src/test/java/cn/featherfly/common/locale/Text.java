
package cn.featherfly.common.locale;

import java.text.MessageFormat;
import java.util.Locale;

import cn.featherfly.common.lang.LangUtils;

/**
 * <p>
 * Text
 * </p>
 *
 * @author zhongj
 */
public enum Text implements LocalizedMessage {

    HELLO("hello", "你好"),
    BYE("bye bye", "再见"),
    HEHE("he {0} he", "嘿{0}嘿");

    private Text(String message, String messageZh) {
        this.message = message;
        this.messageZh = messageZh;
    }

    private String message;

    private String messageZh;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMessage(Locale locale, Object... argus) {
        if (locale != null && locale.getLanguage().equals(Locale.CHINESE.getLanguage())) {
            if (LangUtils.isNotEmpty(argus)) {
                return MessageFormat.format(messageZh, argus);
            }
            return messageZh;
        } else {
            if (LangUtils.isNotEmpty(argus)) {
                return MessageFormat.format(message, argus);
            }
            return message;
        }
    }
}
