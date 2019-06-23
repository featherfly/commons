package cn.featherfly.common.exception;

/**
 * <p>
 * ExceptionCodeException
 * </p>
 *
 * @author 钟冀
 * @since 1.7
 * @version 1.7
 */
public abstract class ExceptionCodeException extends BaseException {
        
    private static final long serialVersionUID = 5710245517160140690L;
    
    private ExceptionCode exceptionCode;
    
    /**
     * 构造方法
     */
    protected ExceptionCodeException() {
    }
    
    /**
     * 构造方法
     * @param message 信息
     */
    protected ExceptionCodeException(String message) {
        super(message);
    }
    
    /**
     * 构造方法
     * @param ex 异常
     */
    protected ExceptionCodeException(Throwable ex) {
        super(ex);
    }
    
    /**
     * 构造方法
     * @param message 信息
     * @param ex 异常
     */
    protected ExceptionCodeException(String message, Throwable ex) {
        super(message, ex);
    }

    /**
     * 构造方法
     * 
     * @param exceptionCode
     *            错误码
     */
    protected ExceptionCodeException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }
    
    /**
     * 构造方法
     * 
     * @param exceptionCode
     *            错误码
     * @param ex
     *            异常
     */
    protected ExceptionCodeException(ExceptionCode exceptionCode, Throwable ex) {
        super(exceptionCode.getMessage(), ex);
        this.exceptionCode = exceptionCode;
    }

    /**
     * 返回exceptionCode
     * 
     * @return exceptionCode
     */
    public ExceptionCode getExceptionCode() {
        return exceptionCode;
    }
}
