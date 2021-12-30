
/*
 * All rights Reserved, Designed By zhongj
 * @Title: Validator.java
 * @Package validator
 * @Description: TODO (用一句话描述该文件做什么)
 * @author: zhongj
 * @date: 2021-12-20 17:37:20
 * @Copyright: 2021 www.featherfly.cn Inc. All rights reserved.
 */
package validator;

/**
 * Validator.
 *
 * @author zhongj
 */
public interface Validator {

    /**
     * Validate.
     *
     * @param value the value
     * @return the validation result
     */
    default ValidationResult validate(Object value) {
        return validate(value, "");
    }

    /**
     * Validate.
     *
     * @param value     the value
     * @param valueName the value name
     * @return the validation result
     */
    ValidationResult validate(Object value, String valueName);
}
