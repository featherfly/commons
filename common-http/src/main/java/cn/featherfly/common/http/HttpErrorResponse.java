package cn.featherfly.common.http;

import java.util.Map;

/**
 * The Class HttpErrorResponse.
 *
 * @author zhongj
 */
public class HttpErrorResponse implements Error {

    private String message;

    private HttpResponse httpResponse;

    /**
     * Instantiates a new http error response.
     *
     * @param message the message
     */
    public HttpErrorResponse(String message) {
        this.message = message;
    }

    /**
     * Instantiates a new http error response.
     *
     * @param message      the message
     * @param httpResponse the http response
     */
    public HttpErrorResponse(String message, HttpResponse httpResponse) {
        super();
        this.message = message;
        this.httpResponse = httpResponse;
    }

    /**
     * Instantiates a new http error response.
     *
     * @param statusCode    the status code
     * @param data          the data
     * @param headers       the headers
     * @param notModified   the not modified
     * @param networkTimeMs the network time ms
     * @param message       the message
     */
    public HttpErrorResponse(int statusCode, byte[] data, Map<String, String> headers, boolean notModified,
            long networkTimeMs, String message) {
        httpResponse = new HttpResponse(statusCode, data, headers, notModified, networkTimeMs);
        this.message = message;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * Gets the http response.
     *
     * @return the http response
     */
    public HttpResponse getHttpResponse() {
        return httpResponse;
    }
}
