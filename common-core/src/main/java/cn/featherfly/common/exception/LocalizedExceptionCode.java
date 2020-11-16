
package cn.featherfly.common.exception;

import java.util.Locale;

/**
 * <p>
 * LocalizedExceptionCode
 * </p>
 *
 * @author zhongj
 * @since 1.7
 * @version 1.7
 */
public interface LocalizedExceptionCode extends ExceptionCode {

    /**
     * <p>
     * 返回消息绑定参数
     * </p>
     *
     * @return 消息绑定参数
     */
    Object[] getArgus();

    /**
     * <p>
     * 返回资源文件中的key
     * </p>
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
