
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2023-11-14 15:13:14
 * @Copyright: 2023 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.bean.function;

/**
 * BeanPropertyGetterEnum.
 *
 * @author zhongj
 * @param <T> the generic bean type
 * @param <E> the generic property value type
 */
public interface BeanPropertyGetterEnum<T, E extends Enum<E>> extends BeanPropertyGetter<T, E> {
}
