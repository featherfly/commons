
package cn.featherfly.common.api;

/**
 * <p>
 * Api Response
 * </p>
 *
 * @author zhongj
 */
public class Response<D> {

    private D data;

    private String message;

    private String code;

    private Integer status = 1;

    /**
     */
    public Response() {
    }

    /**
     * 返回message
     *
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * 设置message
     *
     * @param message message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 返回status
     *
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置status
     *
     * @param status status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 返回data
     *
     * @return data
     */
    public D getData() {
        return data;
    }

    /**
     * 设置data
     *
     * @param data data
     */
    public void setData(D data) {
        this.data = data;
    }

    /**
     * 返回code
     *
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置code
     *
     * @param code code
     */
    public void setCode(String code) {
        this.code = code;
    }
}
