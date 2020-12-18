package cn.featherfly.common.http;

import java.util.Arrays;
import java.util.Map;

/**
 * The Class HttpResponse.
 * 
 * @author zhongj
 */
public class HttpResponse {

    /** The HTTP status code. */
    public final int statusCode;

    /** Raw data from this response. */
    public final byte[] data;

    /** Response headers. */
    public final Map<String, String> headers;

    /** True if the server returned a 304 (Not Modified). */
    public final boolean notModified;

    /** Network roundtrip time in milliseconds. */
    public final long networkTimeMs;

    /**
     * Creates a new network response.
     *
     * @param statusCode    the HTTP status code
     * @param data          Response body
     * @param headers       Headers returned with this response, or null for
     *                      none
     * @param notModified   True if the server returned a 304 and the data was
     *                      already in cache
     * @param networkTimeMs Round-trip network time to receive network response
     */
    public HttpResponse(int statusCode, byte[] data, Map<String, String> headers, boolean notModified,
            long networkTimeMs) {
        this.statusCode = statusCode;
        this.data = data;
        this.headers = headers;
        this.notModified = notModified;
        this.networkTimeMs = networkTimeMs;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "HttpResponse{" + "statusCode=" + statusCode + ", data=" + Arrays.toString(data) + ", headers=" + headers
                + ", notModified=" + notModified + ", networkTimeMs=" + networkTimeMs + '}';
    }
}
