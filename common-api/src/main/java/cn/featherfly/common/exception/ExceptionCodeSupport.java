package cn.featherfly.common.exception;

/**
 * ExceptionCodeSupport.
 *
 * @author zhongj
 */
public interface ExceptionCodeSupport<E extends ExceptionCode> {

    /**
     * Gets the exception code.
     *
     * @return the exception code
     */
    E getExceptionCode();
}
