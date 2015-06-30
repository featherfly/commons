
package cn.featherfly.common.bean;

import java.util.Locale;

/**
 * <p>
 * 没有找到相应属性的异常.
 * 如果一个成员变量既没有set方法也没有get方法就会抛出该异常.
 * </p>
 *
 * @author 钟冀
 */
public class NoSuchPropertyException extends PropertyException{

    private static final long serialVersionUID = -8041655239720325546L;

    /**
     *
     * @param clazz 类型
     * @param propertyName 属性名
     */
    public NoSuchPropertyException(Class<?> clazz, String propertyName) {
        super(clazz, propertyName, "no_property");
    }
    /**
     *
     * @param clazz 类型
     * @param propertyName 属性名
     * @param locale locale
     */
    public NoSuchPropertyException(Class<?> clazz, String propertyName, Locale locale) {
        super(clazz, propertyName, "no_property", locale);
    }

    /**
     *
     * @param clazz 类型
     * @param propertyName 属性名
     * @param cause 异常
     */
    public NoSuchPropertyException(Class<?> clazz, String propertyName, Throwable cause) {
        super(clazz, propertyName, "no_property", cause);
    }

    // ********************************************************************
    // property
    // ********************************************************************
}
