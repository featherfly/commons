
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2023-11-27 18:55:27
 * @Copyright: 2023 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.repository.function;

import java.util.function.Function;

import cn.featherfly.common.repository.Field;

/**
 * The Interface FieldFunction.
 *
 * @author zhongj
 */
public interface FieldFunction extends Function<String, String> {

    /**
     * Applies this function to the given argument.
     *
     * @param field the function argument
     * @return the function result
     */
    default String apply(Field field) {
        return apply(field.name());
    }
}
