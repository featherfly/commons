
/*
 * All rights Reserved, Designed By zhongj
 * @Title: SerializablePredicate.java
 * @Description: SerializablePredicate
 * @author: zhongj
 * @date: 2023-08-28 15:11:28
 * @Copyright: 2023 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.function.serializable;

import java.io.Serializable;
import java.util.function.Predicate;

/**
 * The Interface SerializablePredicate.
 *
 * @author zhongj
 * @param <T> the type of the input to the predicate
 * @see java.util.function.Predicate
 */
@FunctionalInterface
public interface SerializablePredicate<T> extends Predicate<T>, Serializable {

}
