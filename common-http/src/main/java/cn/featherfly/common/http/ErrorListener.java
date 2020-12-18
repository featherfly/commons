package cn.featherfly.common.http;

/**
 * ErrorListener.
 * 
 * @author zhongj
 */
public interface ErrorListener {

    /**
     * Error.
     *
     * @param error the error
     */
    void error(HttpErrorResponse error);
}
