
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2023-11-14 15:13:14
 * @Copyright: 2023 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.bean.function;

import java.time.LocalDateTime;

/**
 * BeanPropertyGetterLocalDate.
 *
 * @author zhongj
 * @param <T> the generic bean type
 */
public interface BeanPropertyGetterLocalDateTime<T> extends BeanPropertyGetter<T, LocalDateTime> {

    /**
     * Gets the property type.
     *
     * @return the property type
     */
    @Override
    default Class<LocalDateTime> getType() {
        return LocalDateTime.class;
    }
}
