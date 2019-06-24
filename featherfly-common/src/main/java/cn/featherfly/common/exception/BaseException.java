
package cn.featherfly.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * BaseException
 * </p>
 * 
 * @author zhongj 
 * @since 1.7
 * @version 1.7
 */
public abstract class BaseException extends RuntimeException{

    private static final long serialVersionUID = -7432844750473664747L;
    
    /**
     * logger
     */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 
     */
    public BaseException() {
        super();
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public BaseException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * @param message
     * @param cause
     */
    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     */
    public BaseException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public BaseException(Throwable cause) {
        super(cause);
    }

}
