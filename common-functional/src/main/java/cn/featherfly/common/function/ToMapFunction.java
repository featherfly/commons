package cn.featherfly.common.function;

import java.util.Map;
import java.util.function.Function;

/**
 * The Interface ToMapFunction.
 *
 * @author zhongj
 * @param <T> the type of the input to the function
 * @param <K> the key type
 * @param <V> the value type
 */
@FunctionalInterface
public interface ToMapFunction<T, K, V> extends Function<T, Map<K, V>> {

}