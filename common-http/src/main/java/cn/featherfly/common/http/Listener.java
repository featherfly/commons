package cn.featherfly.common.http;

/**
 * The Interface Listener.
 *
 * @param <T> the generic type
 * @author zhongj
 */
public interface Listener<T> {

    /**
     * Success.
     *
     * @param t the t
     */
    void success(T t);

    /**
     * Failure.
     *
     * @param t the t
     */
    void failure(T t);
}
