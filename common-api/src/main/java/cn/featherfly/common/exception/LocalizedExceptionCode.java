
package cn.featherfly.common.exception;

import java.util.Locale;

/**
 * LocalizedExceptionCode.
 *
 * @author zhongj
 * @since 0.4.0
 */
public interface LocalizedExceptionCode extends ExceptionCode {

    /**
     * 返回消息绑定参数.
     *
     * @return 消息绑定参数
     */
    Object[] getArgus();

    /**
     * 返回资源文件中的key.
     *
     * @return 资源文件中的key
     */
    String getKey();

    /**
     * 返回locale
     *
     * @return locale
     */
    Locale getLocale();
}
