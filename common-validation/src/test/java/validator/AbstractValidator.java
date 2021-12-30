
/*
 * All rights Reserved, Designed By zhongj
 * @Title: NotNullValidator.java
 * @Package validator
 * @Description: AbstractValidator
 * @author: zhongj
 * @date: 2021-12-20 17:41:20
 * @Copyright: 2021 www.featherfly.cn Inc. All rights reserved.
 */
package validator;

/**
 * The Class AbstractValidator.
 *
 * @author zhongj
 */
public abstract class AbstractValidator implements Validator {

    /**
     * {@inheritDoc}
     */
    @Override
    public ValidationResult validate(Object value, String valueName) {
        return new ValidationResultImpl(value != null, getMessage(valueName), value);
    }

    protected abstract String getMessage(Object... values);
}
