package cn.featherfly.common.spring.jdbc.datasouce;

import org.springframework.dao.DataAccessException;

/**
 * <p>
 * ReadWriteDataSourceTransactionException
 * </p>
 * 
 * @author zhongj
 */
public class ReadWriteDataSourceTransactionException extends DataAccessException {

    private static final long serialVersionUID = -263482866844988907L;
    
    /**
     * @param message message
     */
    ReadWriteDataSourceTransactionException(String message) {
        super(message);
    }
    
    /**
     * 
     * @param message message
     * @param cause Throwable
     */
    ReadWriteDataSourceTransactionException(String message, Throwable cause) {
        super(message, cause);
    }
}