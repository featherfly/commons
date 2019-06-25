
package cn.featherfly.common.bean.vo;

/**
 * <p>
 * Result
 * 类的说明放这里
 * </p>
 * 
 * @author zhongj
 */
public class Result<D> {

    private String message;
    
    private D data;

    /**
     * 返回message
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * 设置message
     * @param message message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 返回data
     * @return data
     */
    public D getData() {
        return data;
    }

    /**
     * 设置data
     * @param data data
     */
    public void setData(D data) {
        this.data = data;
    }
}
