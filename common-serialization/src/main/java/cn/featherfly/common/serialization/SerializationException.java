
package cn.featherfly.common.serialization;

/**
 * <p>
 * SerializationException
 * </p>
 * 
 * @author zhongj
 */
public class SerializationException extends cn.featherfly.common.exception.LocalizedCodeException{

    private static final long serialVersionUID = -1;

    
    /**
     * @param exceptionCode exceptionCode
     */
    public SerializationException(SerializationExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    /**
     * @param exceptionCode exceptionCode
     * @param ex ex
     */
    public SerializationException(SerializationExceptionCode exceptionCode, Throwable ex) {
        super(exceptionCode, ex);
    }
}
