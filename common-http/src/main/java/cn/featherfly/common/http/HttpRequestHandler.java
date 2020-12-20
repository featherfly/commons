
package cn.featherfly.common.http;

import java.util.function.Consumer;

/**
 * HttpResponseHandler.
 *
 * @author zhongj
 */
public interface HttpRequestHandler<T> {

    HttpRequestHandler<T> completion(Consumer<T> action);

    HttpRequestHandler<T> completion(Consumer<T> success, Consumer<HttpErrorResponse> error);

    HttpRequestHandler<T> error(Consumer<HttpErrorResponse> action);
}
