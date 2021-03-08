
package cn.featherfly.common.api;

import javax.annotation.Nonnull;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Api Response.
 *
 * @author zhongj
 * @param <D> the generic type
 */
public class Response<D> {

    /** The Constant SUCCESS_CODE. */
    public static final String SUCCESS_CODE = "OK";

    /** The Constant DEFAULT_ERROR_CODE. */
    public static final String DEFAULT_ERROR_CODE = "ERROR";

    private D data;

    private String message;

    private String code;

    /**
     * Checks if is success.
     *
     * @return true, if is success
     */
    @JsonIgnore
    public boolean isSuccess() {
        return SUCCESS_CODE.equals(code);
    }

    /**
     * Checks if is success.
     *
     * @param response the response
     * @return true, if is success
     */
    public static boolean isSuccess(Response<?> response) {
        if (response != null) {
            return response.isSuccess();
        }
        return false;
    }

    /**
     * Creates the success.
     *
     * @param <D>  the generic type
     * @param data the data
     * @return the response
     */
    public static <D> Response<D> createSuccess(D data) {
        Response<D> response = new Response<>();
        response.setData(data);
        response.setCode(SUCCESS_CODE);
        return response;
    }

    /**
     * Creates the failure.
     *
     * @param <D>     the generic type
     * @param code    the code
     * @param message the message
     * @return the response
     */
    public static <D> Response<D> createFailure(@Nonnull String code, String message) {
        Response<D> response = new Response<>();
        response.setCode(code);
        response.setMessage(message);
        return response;
    }

    /**
     * Creates the failure.
     *
     * @param <D>     the generic type
     * @param message the message
     * @return the response
     */
    public static <D> Response<D> createError(String message) {
        Response<D> response = new Response<>();
        response.setCode(DEFAULT_ERROR_CODE);
        response.setMessage(message);
        return response;
    }

    /**
     * Instantiates a new response.
     */
    public Response() {
    }

    /**
     * 返回message.
     *
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * 设置message.
     *
     * @param message message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 返回data.
     *
     * @return data
     */
    public D getData() {
        return data;
    }

    /**
     * 设置data.
     *
     * @param data data
     */
    public void setData(D data) {
        this.data = data;
    }

    /**
     * 返回code.
     *
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置code.
     *
     * @param code code
     */
    public void setCode(String code) {
        this.code = code;
    }
}
