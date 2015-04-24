
package cn.featherfly.common.bean;


/**
 * <p>
 * 属性存取异常.
 * 如果一个属性不可读而又执行了读取操作时抛出（不可写同理）.
 * </p>
 *
 * @author 钟冀
 */
public class PropertyAccessException extends RuntimeException{

    private static final long serialVersionUID = -8041655239720325546L;

    private static final String MSG_START = "%s的属性%s读取异常";

    private static final String MSG_SPLITOR = " , ";

    /**
     *
     * @param clazz 类型
     * @param propertyName 属性名
     */
    public PropertyAccessException(Class<?> clazz, String propertyName) {
        super(String.format(MSG_START, clazz.getName(), propertyName));
    }

    /**
     *
     * @param clazz 类型
     * @param propertyName 属性名
     * @param message 信息
     */
    public PropertyAccessException(Class<?> clazz, String propertyName, String message) {
        super(String.format(MSG_START, clazz.getName(), propertyName) + MSG_SPLITOR + message);
    }

    /**
     *
     * @param clazz 类型
     * @param propertyName 属性名
     * @param message 信息
     * @param cause 异常
     */
    public PropertyAccessException(Class<?> clazz, String propertyName, String message, Throwable cause) {
        super(String.format(MSG_START, clazz.getName(), propertyName) + MSG_SPLITOR + message , cause);
    }

    /**
     *
     * @param clazz 类型
     * @param propertyName 属性名
     * @param cause 异常
     */
    public PropertyAccessException(Class<?> clazz, String propertyName, Throwable cause) {
        super(String.format(MSG_START, clazz.getName(), propertyName), cause);
    }

    // ********************************************************************
    // property
    // ********************************************************************

    private String propertyName;

    /**
     * @return 返回propertyName
     */
    public String getPropertyName() {
        return propertyName;
    }

    /**
     * @param propertyName 设置propertyName
     */
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }
}
