package cn.featherfly.common.exception;

/**
 * <p>
 * 支持国际化消息输出的异常
 * </p>
 *
 * @author 钟冀
 * @since 1.7
 * @version 1.7
 */
public abstract class LocalizedCodeException extends BaseException {

    private static final long serialVersionUID = -580152334157640022L;
    
    private LocalizedExceptionCode localizedExceptionCode;
    
    /**
     * 构造方法
     */
    protected LocalizedCodeException() {
    }
    
    /**
     * 构造方法
     * @param message 信息
     */
    protected LocalizedCodeException(String message) {
        super(message);
    }
    
    /**
     * 构造方法
     * @param ex 异常
     */
    protected LocalizedCodeException(Throwable ex) {
        super(ex);
    }
    
    /**
     * 构造方法
     * @param message 信息
     * @param ex 异常
     */
    protected LocalizedCodeException(String message, Throwable ex) {
        super(message, ex);
    }
    
    /**
     * 构造方法
     * @param exceptionCode 错误码
     */
    protected LocalizedCodeException(LocalizedExceptionCode exceptionCode) {
        this(exceptionCode.getMessage());
        this.localizedExceptionCode = exceptionCode;
    }

    /**
     * 构造方法
     * @param exceptionCode 错误码
     * @param ex 异常
     */
    protected LocalizedCodeException(LocalizedExceptionCode exceptionCode, Throwable ex) {
        this(exceptionCode.getMessage(), ex);
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
