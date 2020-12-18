package cn.featherfly.common.http;

/**
 * The Enum HttpMethod.
 * 
 * @author zhongj
 */
public enum HttpMethod {

    /** The get. */
    GET(0),

    /** The post. */
    POST(1),

    /** The put. */
    PUT(2),

    /** The delete. */
    DELETE(3),

    /** The head. */
    HEAD(4),

    /** The options. */
    OPTIONS(5),

    /** The trace. */
    TRACE(6),

    /** The patch. */
    PATCH(7);

    private int value;

    private HttpMethod(int value) {
        this.value = value;
    }

    /**
     * To volley code.
     *
     * @return the int
     */
    public int toVolleyCode() {
        return value;
    }

    /**
     * To ok http code.
     *
     * @return the string
     */
    public String toOkHttpCode() {
        return toString();
    }
}
