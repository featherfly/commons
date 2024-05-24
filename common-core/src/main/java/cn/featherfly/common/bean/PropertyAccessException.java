
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

    private static final String PROPERTY_NO_GETTER = "property_no_getter";

    private static final String PROPERTY_NO_SETTER = "property_no_setter";

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
     * @param locale the locale
     */
    public PropertyAccessException(Class<?> clazz, Object propertyNameOrIndex, Object nestedNameOrIndex,
        Class<?> propertyType, Locale locale) {
        super(clazz, propertyNameOrIndex, PROPERTY_NESTED_ACCESS,
            new Object[] { nestedNameOrIndex, propertyType.getName() }, locale);
    }

    private PropertyAccessException(Class<?> clazz, Object propertyNameOrIndex, String key, Locale locale) {
        super(clazz, propertyNameOrIndex, key, locale);
    }

    private PropertyAccessException(Class<?> clazz, Object propertyNameOrIndex, String key) {
        super(clazz, propertyNameOrIndex, key);
    }

    // ----------------------------------------------------------------------------------------------------------------

    /**
     * Throw property access.
     *
     * @param type the type
     * @param propertyName the property name
     * @return the property access exception
     */
    public static PropertyAccessException propertyAccess(Class<?> type, String propertyName) {
        return new PropertyAccessException(type, propertyName, PROPERTY_ACCESS);
    }

    /**
     * Throw property access.
     *
     * @param type the type
     * @param propertyName the property name
     * @param locale the locale
     * @return the property access exception
     */
    public static PropertyAccessException propertyAccess(Class<?> type, String propertyName, Locale locale) {
        return new PropertyAccessException(type, propertyName, PROPERTY_ACCESS, locale);
    }

    /**
     * Throw property no setter.
     *
     * @param type the type
     * @param propertyName the property name
     * @return the property access exception
     */
    public static PropertyAccessException propertyNoSetter(Class<?> type, String propertyName) {
        return new PropertyAccessException(type, propertyName, PROPERTY_NO_SETTER);
    }

    /**
     * Throw property no setter.
     *
     * @param type the type
     * @param propertyName the property name
     * @param locale the locale
     * @return the property access exception
     */
    public static PropertyAccessException propertyNoSetter(Class<?> type, String propertyName, Locale locale) {
        return new PropertyAccessException(type, propertyName, PROPERTY_NO_SETTER, locale);
    }

    /**
     * Throw property no getter.
     *
     * @param type the type
     * @param propertyName the property name
     * @return the property access exception
     */
    public static PropertyAccessException propertyNoGetter(Class<?> type, String propertyName) {
        return new PropertyAccessException(type, propertyName, PROPERTY_NO_GETTER);
    }

    /**
     * Throw property no getter.
     *
     * @param type the type
     * @param propertyName the property name
     * @param locale the locale
     * @return the property access exception
     */
    public static PropertyAccessException propertyNoGetter(Class<?> type, String propertyName, Locale locale) {
        return new PropertyAccessException(type, propertyName, PROPERTY_NO_GETTER, locale);
    }

    // ********************************************************************
    // property
    // ********************************************************************
}
