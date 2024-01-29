
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2023-11-21 13:07:21
 * @Copyright: 2023 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.repository;

import cn.featherfly.common.operator.Function;

/**
 * FunctionField.
 *
 * @author zhongj
 */
public interface FunctionField extends Field {

    /**
     * Function.
     *
     * @return the function
     */
    Function function();
}
