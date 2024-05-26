
package cn.featherfly.common.repository.builder;

/**
 * BuilderException.
 *
 * @author zhongj
 */
public class BuilderException extends cn.featherfly.common.exception.LocalizedCodeException {

    private static final long serialVersionUID = -1;

    /**
     * Instantiates a new builder exception.
     *
     * @param exceptionCode exceptionCode
     */
    public BuilderException(BuilderExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    /**
     * Instantiates a new builder exception.
     *
     * @param exceptionCode exceptionCode
     * @param ex ex
     */
    public BuilderException(BuilderExceptionCode exceptionCode, Throwable ex) {
        super(exceptionCode, ex);
    }
}
