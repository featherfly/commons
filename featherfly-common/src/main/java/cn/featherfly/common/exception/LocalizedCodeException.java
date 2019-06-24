package cn.featherfly.common.exception;

import cn.featherfly.common.locale.ResourceBundleUtils;

/**
 * <p>
 * 支持国际化消息输出的异常
 * </p>
 *
 * @author zhongj
 * @since 1.7
 * @version 1.7
 */
public abstract class LocalizedCodeException extends LocalizedException {

    private static final long serialVersionUID = -580152334157640022L;
    
    private LocalizedExceptionCode localizedExceptionCode;
    
    /**
     * 构造方法
     * 
     */
    protected LocalizedCodeException() {
    }
    
    /**
     * 构造方法
     * @param exceptionCode 错误码
     */
    protected LocalizedCodeException(LocalizedExceptionCode exceptionCode) {
        super(ResourceBundleUtils.KEY_SIGN + exceptionCode.getKey(), exceptionCode.getArgus(), exceptionCode.getLocale());
        this.localizedExceptionCode = exceptionCode;
    }

    /**
     * 构造方法
     * @param exceptionCode 错误码
     * @param ex 异常
     */
    protected LocalizedCodeException(LocalizedExceptionCode exceptionCode, Throwable ex) {
        super(ResourceBundleUtils.KEY_SIGN + exceptionCode.getKey(), exceptionCode.getArgus(), exceptionCode.getLocale(), ex);;
        this.localizedExceptionCode = exceptionCode;
    }
    
    /**
     * 返回exceptionCode
     * @return exceptionCode
     */
    public LocalizedExceptionCode getExceptionCode() {
        return localizedExceptionCode;
    }
}
