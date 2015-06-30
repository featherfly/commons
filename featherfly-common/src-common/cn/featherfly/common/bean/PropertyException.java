
package cn.featherfly.common.bean;

import java.util.Locale;

import cn.featherfly.common.exception.LocalizedException;

/**
 * <p>
 * 属性异常.
 * </p>
 *
 * @author 钟冀
 */
public abstract class PropertyException extends LocalizedException{

    private static final long serialVersionUID = -3444415089178286828L;
    
    private static final String baseName = "@" + PropertyException.class.getSimpleName() + "#";

    /**
     *
     * @param clazz 类型
     * @param propertyName 属性名
     * @param key 资源文件key
     */
    public PropertyException(Class<?> clazz, String propertyName, String key) {
        super(baseName + key, new Object[] {clazz.getName(), propertyName});
        this.propertyName = propertyName;
    }
    /**
     *
     * @param clazz 类型
     * @param propertyName 属性名
     * @param key 资源文件key
     * @param locale locale
     */
    public PropertyException(Class<?> clazz, String propertyName, String key, Locale locale) {
        super(baseName + key, new Object[] {clazz.getName(), propertyName}, locale);
        this.propertyName = propertyName;
    }

    /**
     *
     * @param clazz 类型
     * @param propertyName 属性名
     * @param cause 异常
     */
    public PropertyException(Class<?> clazz, String propertyName, String key, Throwable cause) {
        super(baseName + key, new Object[] {clazz.getName(), propertyName}, cause);
        this.propertyName = propertyName;
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
