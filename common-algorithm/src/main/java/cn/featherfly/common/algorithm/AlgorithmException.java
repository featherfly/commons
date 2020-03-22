
package cn.featherfly.common.algorithm;

/**
 * <p>
 * AlgorithmException
 * </p>
 *
 * @author zhongj
 */
public class AlgorithmException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 7713425353152953851L;

    /**
     *
     */
    public AlgorithmException() {
        super();
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public AlgorithmException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * @param message
     * @param cause
     */
    public AlgorithmException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     */
    public AlgorithmException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public AlgorithmException(Throwable cause) {
        super(cause);
    }
}
