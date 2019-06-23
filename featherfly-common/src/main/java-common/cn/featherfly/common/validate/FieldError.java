package cn.featherfly.common.validate;

/**
 * @author Zhong Ji
 */
public class FieldError {

    private String name;

    private String message;
    
    private String value;

    /**
     * 返回name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置name
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

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
     * 返回value
     * @return value
     */
    public String getValue() {
        return value;
    }

    /**
     * 设置value
     * @param value value
     */
    public void setValue(String value) {
        this.value = value;
    }
}
