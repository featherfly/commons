
/*
 * All rights Reserved, Designed By zhongj
 * @Title: ValidationResult.java
 * @Package validator
 * @Description: TODO (用一句话描述该文件做什么)
 * @author: zhongj
 * @date: 2021-12-20 17:38:20
 * @Copyright: 2021 www.featherfly.cn Inc. All rights reserved.
 */
package validator;

/**
 * ValidationResult.
 *
 * @author zhongj
 */
public interface ValidationResult {

    boolean isValid();

    String getMessage();

    Object getValue();
}
