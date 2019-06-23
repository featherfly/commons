
package cn.featherfly.common.storage;

import cn.featherfly.common.exception.LocalizedCodeException;
import cn.featherfly.common.exception.LocalizedExceptionCode;

/**
 * <p>
 * StorageException
 * </p>
 * 
 * @author 钟冀
 */
public class StorageException extends LocalizedCodeException {

    private static final long serialVersionUID = 172103511641085163L;

    /**
     * 
     */
    public StorageException() {
        super();
    }

    /**
     * @param exceptionCode
     * @param ex
     */
    public StorageException(LocalizedExceptionCode exceptionCode,
            Throwable ex) {
        super(exceptionCode, ex);
    }

    /**
     * @param exceptionCode
     */
    public StorageException(LocalizedExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    /**
     * @param message
     * @param ex
     */
    public StorageException(String message, Throwable ex) {
        super(message, ex);
    }

    /**
     * @param message
     */
    public StorageException(String message) {
        super(message);
    }

    /**
     * @param ex
     */
    public StorageException(Throwable ex) {
        super(ex);
    }
}
