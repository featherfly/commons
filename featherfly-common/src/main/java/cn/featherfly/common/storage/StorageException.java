
package cn.featherfly.common.storage;

import cn.featherfly.common.exception.LocalizedCodeException;
import cn.featherfly.common.exception.LocalizedException;
import cn.featherfly.common.exception.LocalizedExceptionCode;

/**
 * <p>
 * StorageException
 * </p>
 * 
 * @author 钟冀
 */
public class StorageException extends LocalizedException {

    private static final long serialVersionUID = 172103511641085163L;

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
