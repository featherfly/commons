
package cn.featherfly.common.algorithm;

/**
 * AlgorithmException.
 *
 * @author zhongj
 */
public class AlgorithmException extends RuntimeException {

    private static final long serialVersionUID = 7713425353152953851L;

    /**
     * Instantiates a new algorithm exception.
     */
    public AlgorithmException() {
        super();
    }

    /**
     * Instantiates a new algorithm exception.
     *
     * @param message the message
     * @param cause the cause
     * @param enableSuppression the enable suppression
     * @param writableStackTrace the writable stack trace
     */
    public AlgorithmException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * Instantiates a new algorithm exception.
     *
     * @param message the message
     * @param cause the cause
     */
    public AlgorithmException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new algorithm exception.
     *
     * @param message the message
     */
    public AlgorithmException(String message) {
        super(message);
    }

    /**
     * Instantiates a new algorithm exception.
     *
     * @param cause the cause
     */
    public AlgorithmException(Throwable cause) {
        super(cause);
    }
}
