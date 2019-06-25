
package cn.featherfly.common.gentool.exception.gencode;

/**
 * <p>
 * HelloException
 * </p>
 * 
 * @author yufei
 */
public class GenException extends cn.featherfly.common.exception.LocalizedCodeException{

    private static final long serialVersionUID = -1;

    
    /**
     * @param exceptionCode exceptionCode
     */
    public GenException(GenExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    /**
     * @param exceptionCode exceptionCode
     * @param ex ex
     */
    public GenException(GenExceptionCode exceptionCode, Throwable ex) {
        super(exceptionCode, ex);
    }
}
