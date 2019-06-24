
package cn.featherfly.common.storage;

import cn.featherfly.common.exception.LocalizedException;

/**
 * <p>
 * StorageException
 * </p>
 * 
 * @author zhongj
 */
public class StorageException extends LocalizedException {

    private static final long serialVersionUID = 172103511641085163L;

    /**
     * @param message message
     * @param ex ex
     */
    public StorageException(String message, Throwable ex) {
        super(message, ex);
    }

    /**
     * @param message message
     */
    public StorageException(String message) {
        super(message);
    }

    /**
     * @param ex ex
     */
    public StorageException(Throwable ex) {
        super(ex);
    }
}
