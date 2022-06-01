
package cn.featherfly.common.data;

import java.util.function.Predicate;

/**
 * 匹配器.
 *
 * @param <O> 泛型
 * @author zhongj
 */
@FunctionalInterface
public interface Matcher<O> extends Predicate<O> {

}
