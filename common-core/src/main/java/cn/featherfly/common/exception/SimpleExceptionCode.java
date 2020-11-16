
package cn.featherfly.common.exception;

/**
 * <p>
 * SimpleExceptionCode
 * </p>
 *
 * @author zhongj
 * @since 1.7
 * @version 1.7
 */
public class SimpleExceptionCode implements ExceptionCode {

    protected String module;

    protected Integer num;

    protected String message;

    public SimpleExceptionCode(String module, Integer num, String message) {
        this.module = module;
        this.num = num;
        this.message = message;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getNum() {
        return num;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getModule() {
        return module;
    }
}