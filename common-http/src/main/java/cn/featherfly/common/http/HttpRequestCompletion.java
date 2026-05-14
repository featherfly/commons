
package cn.featherfly.common.http;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * HttpResponseHandler.
 *
 * @author zhongj
 * @param <T> the generic type
 */
public interface HttpRequestCompletion<T> {

    /**
     * Completion.
     *
     * @param action the action
     * @return the http request completion
     */
    HttpRequestCompletion<T> completion(Consumer<T> action);

    /**
     * Completion.
     *
     * @param success the success
     * @param error the error
     * @return the http request completion
     */
    HttpRequestCompletion<T> completion(Consumer<T> success, Consumer<HttpErrorResponse> error);

    /**
     * Error.
     *
     * @param action the action
     * @return the http request completion
     */
    HttpRequestCompletion<T> error(Consumer<HttpErrorResponse> action);

    /**
     * Exceptionally.
     *
     * @param fn the fn
     * @return the http request completion
     */
    HttpRequestCompletion<T> exceptionally(Function<Throwable, ? extends T> fn);
}
