
package cn.featherfly.common.bean;

import java.util.Locale;

/**
 * <p>
 * 属性存取异常. 如果一个属性不可读而又执行了读取操作时抛出（不可写同理）.
 * </p>
 *
 * @author 钟冀
 */
public class PropertyAccessException extends PropertyException {

    private static final long serialVersionUID = -8041655239720325546L;

    /**
     *
     * @param clazz
     *            类型
     * @param propertyName
     *            属性名
     */
    public PropertyAccessException(Class<?> clazz, String propertyName) {
        super(clazz, propertyName, "property_access");
    }

    /**
     *
     * @param clazz
     *            类型
     * @param propertyName
     *            属性名
     * @param locale
     *            locale
     */
    public PropertyAccessException(Class<?> clazz, String propertyName, Locale locale) {
        super(clazz, propertyName, "property_access", locale);
    }

    /**
     *
     * @param clazz
     *            类型
     * @param propertyName
     *            属性名
     * @param cause
     *            异常
     */
    public PropertyAccessException(Class<?> clazz, String propertyName, Throwable cause) {
        super(clazz, propertyName, "property_access", cause);
    }

    // ********************************************************************
    // property
    // ********************************************************************
}
