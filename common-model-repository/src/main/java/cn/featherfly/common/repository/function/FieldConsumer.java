
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2023-11-27 18:55:27
 * @Copyright: 2023 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.repository.function;

import java.util.function.Consumer;

import cn.featherfly.common.repository.Field;

/**
 * FieldConsumer.
 *
 * @author zhongj
 */
public interface FieldConsumer extends Consumer<String> {

    /**
     * Performs this operation on the given argument.
     *
     * @param field the field
     */
    default void accept(Field field) {
        accept(field.name());
    }
}
