package cn.featherfly.common.exception;

import java.util.Locale;

/**
 * <p>
 * ExceptionCodeException
 * </p>
 *
 * @author zhongj
 * @since 1.7
 * @version 1.7
 */
public abstract class ExceptionCodeException extends LocalizedException {

    private static final long serialVersionUID = 5710245517160140690L;

    private ExceptionCode exceptionCode;

    /**
     * 构造方法
     */
    protected ExceptionCodeException() {
    }

    /**
     * 构造方法
     *
     * @param exceptionCode 错误码
     */
    protected ExceptionCodeException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }

    /**
     * 构造方法
     *
     * @param exceptionCode 错误码
     * @param ex            异常
     */
    protected ExceptionCodeException(ExceptionCode exceptionCode, Throwable ex) {
        super(exceptionCode.getMessage(), ex);
        this.exceptionCode = exceptionCode;
    }

    /**
     * 构造方法
     *
     * @param exceptionCode 错误码
     * @param args          消息文本绑定参数
     */
    protected ExceptionCodeException(ExceptionCode exceptionCode, Object[] args) {
        super(exceptionCode.getMessage(), args);
        this.exceptionCode = exceptionCode;
    }

    /**
     * 构造方法
     *
     * @param exceptionCode 错误码
     * @param args          消息文本绑定参数
     * @param ex            Throwable
     */
    protected ExceptionCodeException(ExceptionCode exceptionCode, Object[] args, Throwable ex) {
        super(exceptionCode.getMessage(), args, ex);
        this.exceptionCode = exceptionCode;
    }

    /**
     * 构造方法
     *
     * @param exceptionCode 错误码
     * @param locale        locale
     */
    protected ExceptionCodeException(ExceptionCode exceptionCode, Locale locale) {
        super(exceptionCode.getMessage(), locale);
        this.exceptionCode = exceptionCode;
    }

    /**
     * 构造方法
     *
     * @param exceptionCode 错误码
     * @param locale        locale
     * @param ex            Throwable
     */
    protected ExceptionCodeException(ExceptionCode exceptionCode, Locale locale, Throwable ex) {
        super(exceptionCode.getMessage(), locale, ex);
        this.exceptionCode = exceptionCode;
    }

    /**
     * 构造方法
     *
     * @param exceptionCode 错误码
     * @param args          消息文本绑定参数
     * @param locale        locale
     */
    protected ExceptionCodeException(ExceptionCode exceptionCode, Object[] args, Locale locale) {
        super(exceptionCode.getMessage(), args, locale);
        this.exceptionCode = exceptionCode;
    }

    /**
     * 构造方法
     *
     * @param exceptionCode 错误码
     * @param args          消息文本绑定参数
     * @param locale        locale
     * @param ex            异常
     */
    protected ExceptionCodeException(ExceptionCode exceptionCode, Object[] args, Locale locale, Throwable ex) {
        super(exceptionCode.getMessage(), args, locale, ex);
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
