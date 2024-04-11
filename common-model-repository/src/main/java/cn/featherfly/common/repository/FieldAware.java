
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-03-29 17:44:29
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.repository;

/**
 * FieldAwareExpression.
 *
 * @author zhongj
 */
public interface FieldAware<F extends Field> {

    /**
     * Field.
     *
     * @return the field
     */
    F field();
}
