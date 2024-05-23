
package cn.featherfly.common.bean;

import java.util.Locale;

import org.apache.commons.lang3.ArrayUtils;

import cn.featherfly.common.exception.LocalizedException;

/**
 * 属性异常.
 *
 * @author zhongj
 */
public abstract class PropertyException extends LocalizedException {

    private static final long serialVersionUID = -3444415089178286828L;

    private static final String baseName = "@" + PropertyException.class.getSimpleName() + "#";

    /**
     * Instantiates a new property exception.
     *
     * @param clazz 类型
     * @param propertyNameOrIndex the property name or index
     * @param key 资源文件key
     */
    protected PropertyException(Class<?> clazz, Object propertyNameOrIndex, String key) {
        super(baseName + key, new Object[] { clazz.getName(), propertyNameOrIndex });
        this.propertyNameOrIndex = propertyNameOrIndex;
    }

    /**
     * Instantiates a new property exception.
     *
     * @param clazz 类型
     * @param propertyNameOrIndex the property name or index
     * @param key 资源文件key
     * @param locale locale
     */
    protected PropertyException(Class<?> clazz, Object propertyNameOrIndex, String key, Locale locale) {
        super(baseName + key, new Object[] { clazz.getName(), propertyNameOrIndex }, locale);
        this.propertyNameOrIndex = propertyNameOrIndex;
    }

    /**
     * Instantiates a new property exception.
     *
     * @param clazz 类型
     * @param propertyNameOrIndex the property name or index
     * @param key key
     * @param cause 异常
     */
    protected PropertyException(Class<?> clazz, Object propertyNameOrIndex, String key, Throwable cause) {
        super(baseName + key, new Object[] { clazz.getName(), propertyNameOrIndex }, cause);
        this.propertyNameOrIndex = propertyNameOrIndex;
    }

    /**
     * Instantiates a new property exception.
     *
     * @param clazz the clazz
     * @param propertyNameOrIndex the property name or index
     * @param key the key
     * @param args the args
     */
    protected PropertyException(Class<?> clazz, Object propertyNameOrIndex, String key, Object[] args) {
        super(baseName + key, ArrayUtils.addAll(new Object[] { clazz.getName(), propertyNameOrIndex }, args));
        this.propertyNameOrIndex = propertyNameOrIndex;
    }

    /**
     * Instantiates a new property exception.
     *
     * @param clazz the clazz
     * @param propertyNameOrIndex the property name or index
     * @param key the key
     * @param locale the locale
     * @param args the args
     */
    protected PropertyException(Class<?> clazz, Object propertyNameOrIndex, String key, Object[] args, Locale locale) {
        super(baseName + key, ArrayUtils.addAll(new Object[] { clazz.getName(), propertyNameOrIndex }, args), locale);
        this.propertyNameOrIndex = propertyNameOrIndex;
    }

    /**
     * Instantiates a new property exception.
     *
     * @param clazz the clazz
     * @param propertyNameOrIndex the property name or index
     * @param key the key
     * @param cause the cause
     * @param args the args
     */
    protected PropertyException(Class<?> clazz, Object propertyNameOrIndex, String key, Object[] args,
        Throwable cause) {
        super(baseName + key, ArrayUtils.addAll(new Object[] { clazz.getName(), propertyNameOrIndex }, args), cause);
        this.propertyNameOrIndex = propertyNameOrIndex;
    }

    // ********************************************************************
    // property
    // ********************************************************************

    private final Object propertyNameOrIndex;

    /**
     * Gets the property name.
     *
     * @return 返回propertyName
     */
    public Object getPropertyName() {
        return propertyNameOrIndex;
    }
}
