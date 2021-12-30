
/*
 * All rights Reserved, Designed By zhongj
 * @Title: NotNullValidator.java
 * @Package validator
 * @Description: TODO (用一句话描述该文件做什么)
 * @author: zhongj
 * @date: 2021-12-20 17:41:20
 * @Copyright: 2021 www.featherfly.cn Inc. All rights reserved.
 */
package validator;

import java.text.MessageFormat;

/**
 * NotNullValidator.
 *
 * @author zhongj
 */
public class NotNullValidator extends AbstractValidator {

    private static final MessageFormat FORMAT = new MessageFormat("{0} can not be null");

    //    /**
    //     * {@inheritDoc}
    //     */
    //    @Override
    //    public ValidationResult validate(Object value, String valueName) {
    //        return new ValidationResultImpl(value != null, getMessage(valueName), value);
    //    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getMessage(Object... values) {
        return FORMAT.format(values);
    }

}
