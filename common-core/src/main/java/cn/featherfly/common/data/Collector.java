
package cn.featherfly.common.data;

import java.util.function.Function;

/**
 * Collector.
 *
 * @author zhongj
 * @param <T> the generic type
 * @param <R> the generic type
 */
@FunctionalInterface
public interface Collector<T, R> extends Function<T, R> {

}
