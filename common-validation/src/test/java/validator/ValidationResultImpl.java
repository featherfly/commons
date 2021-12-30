
/*
 * All rights Reserved, Designed By zhongj
 * @Title: ValidationResultImpl.java
 * @Package validator
 * @Description: TODO (用一句话描述该文件做什么)
 * @author: zhongj
 * @date: 2021-12-20 17:41:20
 * @Copyright: 2021 www.featherfly.cn Inc. All rights reserved.
 */
package validator;

/**
 * ValidationResultImpl.
 *
 * @author zhongj
 */
public class ValidationResultImpl implements ValidationResult {

    private boolean valid;

    private String message;

    private Object value;

    /**
     * Instantiates a new validation result impl.
     */
    public ValidationResultImpl() {
    }

    /**
     * Instantiates a new validation result impl.
     *
     * @param valid   the valid
     * @param message the message
     * @param value   the value
     */
    public ValidationResultImpl(boolean valid, String message, Object value) {
        super();
        this.valid = valid;
        this.message = message;
        this.value = value;
    }

    /**
     * set valid value.
     *
     * @param valid valid
     */
    public void setValid(boolean valid) {
        this.valid = valid;
    }

    /**
     * set message value.
     *
     * @param message message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * set value value.
     *
     * @param value value
     */
    public void setValue(Object value) {
        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid() {
        return valid;
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
    public Object getValue() {
        return value;
    }

}
