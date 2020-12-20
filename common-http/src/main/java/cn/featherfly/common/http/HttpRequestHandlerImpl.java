
package cn.featherfly.common.http;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

/**
 * HttpRequestHandlerImpl.
 *
 * @author zhongj
 */
public class HttpRequestHandlerImpl<T> implements HttpRequestHandler<T> {

    CompletableFuture<T> completableFuture;
    CompletableFuture<HttpErrorResponse> errorFuture;

    public HttpRequestHandlerImpl() {
        completableFuture = new CompletableFuture<>();
        errorFuture = new CompletableFuture<>();
    }

    /**
     * set httpErrorResponse value
     *
     * @param httpErrorResponse httpErrorResponse
     */
    public void setHttpErrorResponse(HttpErrorResponse httpErrorResponse) {
        errorFuture.complete(httpErrorResponse);
    }

    /**
     * set response value
     *
     * @param response response
     */
    public void setResponse(T response) {
        completableFuture.complete(response);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HttpRequestHandler<T> completion(Consumer<T> action) {
        completableFuture.thenAccept(action);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HttpRequestHandler<T> error(Consumer<HttpErrorResponse> action) {
        errorFuture.thenAccept(action);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HttpRequestHandler<T> completion(Consumer<T> success, Consumer<HttpErrorResponse> error) {
        completableFuture.thenAccept(success);
        errorFuture.thenAccept(error);
        return this;
    }

}
