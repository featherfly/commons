
package cn.featherfly.common.http;

import java.util.function.Consumer;

/**
 * HttpResponseHandler.
 *
 * @author zhongj
 */
public interface HttpRequestCompletion<T> {

    HttpRequestCompletion<T> completion(Consumer<T> action);

    HttpRequestCompletion<T> completion(Consumer<T> success, Consumer<HttpErrorResponse> error);

    HttpRequestCompletion<T> error(Consumer<HttpErrorResponse> action);
}
