
/*
 * All rights Reserved, Designed By zhongj
 * @Title: ValueType.java
 * @Package cn.featherfly.common.lang
 * @Description: ValueType
 * @author: zhongj
 * @date: 2022-11-10 14:08:10
 * @Copyright: 2022 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.lang.vt;

import cn.featherfly.common.lang.reflect.Type;

/**
 * ValueType.
 *
 * @author zhongj
 * @param <T> the generic type
 */
public interface ValueType<T> extends Type<T>, Value<T> {

}
