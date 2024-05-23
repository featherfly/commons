
package cn.featherfly.common.bean;

import java.util.Locale;

/**
 * property access exception.
 *
 * @author zhongj
 */
public class PropertyAccessException extends PropertyException {

    private static final String PROPERTY_ACCESS = "property_access";

    private static final String PROPERTY_NESTED_ACCESS = "property_nested_access";

    private static final long serialVersionUID = -8041655239720325546L;

    /**
     * Instantiates a new property access exception.
     *
     * @param clazz the clazz
     * @param propertyNameOrIndex the property name or index
     * @param nestedNameOrIndex the nested name or index
     * @param propertyType the property type
     */
    public PropertyAccessException(Class<?> clazz, Object propertyNameOrIndex, Object nestedNameOrIndex,
        Class<?> propertyType) {
        super(clazz, propertyNameOrIndex, PROPERTY_NESTED_ACCESS,
            new Object[] { nestedNameOrIndex, propertyType.getName() });
    }

    /**
     * Instantiates a new property access exception.
     *
     * @param clazz the clazz
     * @param propertyNameOrIndex the property name or index
     * @param nestedNameOrIndex the nested name or index
     * @param propertyType the property type
     */
    public PropertyAccessException(Class<?> clazz, Object propertyNameOrIndex, Object nestedNameOrIndex,
        Class<?> propertyType, Locale locale) {
        super(clazz, propertyNameOrIndex, PROPERTY_NESTED_ACCESS,
            new Object[] { nestedNameOrIndex, propertyType.getName() }, locale);
    }

    /**
     * Instantiates a new property access exception.
     *
     * @param clazz 类型
     * @param propertyName 属性名
     */
    public PropertyAccessException(Class<?> clazz, String propertyName) {
        super(clazz, propertyName, PROPERTY_ACCESS);
    }

    /**
     * Instantiates a new property access exception.
     *
     * @param clazz 类型
     * @param propertyName 属性名
     * @param locale locale
     */
    public PropertyAccessException(Class<?> clazz, String propertyName, Locale locale) {
        super(clazz, propertyName, PROPERTY_ACCESS, locale);
    }

    /**
     * Instantiates a new property access exception.
     *
     * @param clazz 类型
     * @param propertyName 属性名
     * @param cause 异常
     */
    public PropertyAccessException(Class<?> clazz, String propertyName, Throwable cause) {
        super(clazz, propertyName, PROPERTY_ACCESS, cause);
    }

    // ********************************************************************
    // property
    // ********************************************************************
}
