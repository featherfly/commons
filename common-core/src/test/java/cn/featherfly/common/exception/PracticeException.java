
package cn.featherfly.common.exception;

/**
 * <p>
 * PracticeException
 * </p>
 * 
 * @author yufei
 */
public class PracticeException extends cn.featherfly.common.exception.LocalizedCodeException{

    private static final long serialVersionUID = -1;

    
    /**
     * @param exceptionCode exceptionCode
     */
    public PracticeException(PracticeExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    /**
     * @param exceptionCode exceptionCode
     * @param ex ex
     */
    public PracticeException(PracticeExceptionCode exceptionCode, Throwable ex) {
        super(exceptionCode, ex);
    }
}
