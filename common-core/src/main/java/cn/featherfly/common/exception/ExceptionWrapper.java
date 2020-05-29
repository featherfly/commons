
package cn.featherfly.common.exception;

import cn.featherfly.common.lang.ClassUtils;

/**
 * <p>
 * ExceptionWrapper
 * </p>
 * .
 *
 * @author zhongj
 * @param <E> the element type
 */
public class ExceptionWrapper<E extends RuntimeException> {

    /** exception type. */
    protected Class<E> exception;

    /**
     * Instantiates a new exception wrapper.
     *
     * @param exception the exception
     */
    public ExceptionWrapper(Class<E> exception) {
        super();
        this.exception = exception;
    }

    /**
     * throw exception with throwable.
     *
     * @param throwable the throwable
     */
    public void throwException(Throwable throwable) {
        throw ClassUtils.newInstance(exception, throwable);
    }

    /**
     * throw exception with throwable and msg.
     *
     * @param msg       the msg
     * @param throwable the throwable
     */
    public void throwException(String msg, Throwable throwable) {
        throw ClassUtils.newInstance(exception, msg, throwable);
    }

}
