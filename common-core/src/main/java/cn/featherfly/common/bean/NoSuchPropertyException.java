
package cn.featherfly.common.bean;

import java.util.Locale;

/**
 * <p>
 * 没有找到相应属性的异常.
 * 如果一个成员变量既没有set方法也没有get方法就会抛出该异常.
 * </p>
 *
 * @author zhongj
 */
public class NoSuchPropertyException extends PropertyException {

    private static final long serialVersionUID = -8041655239720325546L;

    /**
     * Instantiates a new no such property exception.
     *
     * @param clazz 类型
     * @param propertyName 属性名
     */
    public NoSuchPropertyException(Class<?> clazz, String propertyName) {
        super(clazz, propertyName, "no_property");
    }

    /**
     * Instantiates a new no such property exception.
     *
     * @param clazz 类型
     * @param propertyName 属性名
     * @param locale locale
     */
    public NoSuchPropertyException(Class<?> clazz, String propertyName, Locale locale) {
        super(clazz, propertyName, "no_property", locale);
    }

    /**
     * Instantiates a new no such property exception.
     *
     * @param clazz 类型
     * @param propertyName 属性名
     * @param cause 异常
     */
    public NoSuchPropertyException(Class<?> clazz, String propertyName, Throwable cause) {
        super(clazz, propertyName, "no_property", cause);
    }

    /**
     * Instantiates a new no such property exception.
     *
     * @param clazz 类型
     * @param propertyIndex the property index
     */
    public NoSuchPropertyException(Class<?> clazz, int propertyIndex) {
        super(clazz, propertyIndex + "", "no_property_index");
    }

    /**
     * Instantiates a new no such property exception.
     *
     * @param clazz 类型
     * @param propertyIndex the property index
     * @param locale locale
     */
    public NoSuchPropertyException(Class<?> clazz, int propertyIndex, Locale locale) {
        super(clazz, propertyIndex + "", "no_property_index", locale);
    }

    /**
     * Instantiates a new no such property exception.
     *
     * @param clazz 类型
     * @param propertyIndex the property index
     * @param cause 异常
     */
    public NoSuchPropertyException(Class<?> clazz, int propertyIndex, Throwable cause) {
        super(clazz, propertyIndex + "", "no_property_index", cause);
    }

    // ********************************************************************
    // property
    // ********************************************************************
}
