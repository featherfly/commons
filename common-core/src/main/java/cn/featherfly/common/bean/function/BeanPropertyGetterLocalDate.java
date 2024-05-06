
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2023-11-14 15:13:14
 * @Copyright: 2023 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.bean.function;

import java.time.LocalDate;

/**
 * BeanPropertyGetterLocalDate.
 *
 * @author zhongj
 * @param <T> the generic bean type
 */
public interface BeanPropertyGetterLocalDate<T> extends BeanPropertyGetter<T, LocalDate> {

    /**
     * Gets the property type.
     *
     * @return the property type
     */
    @Override
    default Class<LocalDate> getType() {
        return LocalDate.class;
    }
}
