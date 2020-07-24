
package cn.featherfly.common.repository.builder;

/**
 * <p>
 * BuilderException
 * </p>
 * 
 * @author zhongj
 */
public class BuilderException extends cn.featherfly.common.exception.LocalizedCodeException{

    private static final long serialVersionUID = -1;

    
    /**
     * @param exceptionCode exceptionCode
     */
    public BuilderException(BuilderExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    /**
     * @param exceptionCode exceptionCode
     * @param ex ex
     */
    public BuilderException(BuilderExceptionCode exceptionCode, Throwable ex) {
        super(exceptionCode, ex);
    }
}
