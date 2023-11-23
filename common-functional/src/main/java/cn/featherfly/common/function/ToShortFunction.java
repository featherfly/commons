package cn.featherfly.common.function;

import java.util.function.Function;

/**
 * Represents a function that produces an int-valued result. This is the
 * {@code int}-producing primitive specialization for {@link Function}.
 * <p>
 * This is a <a href="package-summary.html">functional interface</a> whose
 * functional method is {@link #applyAsShort(Object)}.
 *
 * @param <T> the type of the input to the function
 * @see Function
 */
@FunctionalInterface
public interface ToShortFunction<T> {

    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    short applyAsShort(T value);
}
