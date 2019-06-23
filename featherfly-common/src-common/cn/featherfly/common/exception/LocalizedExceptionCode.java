
package cn.featherfly.common.exception;

import cn.featherfly.common.exception.ExceptionCode;

/**
 * <p>
 * LocalizedExceptionCode
 * </p>
 * 
 * @author 钟冀
 * @since 1.7
 * @version 1.7
 */
public interface LocalizedExceptionCode extends ExceptionCode {

    /**
     * <p>
     * 返回消息绑定参数
     * </p>
     * @return 消息绑定参数
     */
    Object[] getArgus();
    
    /**
     * <p>
     * 返回资源文件中的key
     * </p>
     * @return 资源文件中的key
     */
    String getKey();
}
