
package cn.featherfly.common.bean;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * 动态创建指定类型指定属性对应的BeanProperty子类的工厂.<br>
 * 优点：默认的反射BeanProperty比动态生成的设置慢一倍，读取慢几倍.<br>
 * 缺点：生成大量类，占用内存空间，强类型.
 * </p>
 *
 * @author 钟冀
 *
 */
public class ReflectionBeanPropertyFactory implements BeanPropertyFactory {

    /**
     * logger
     */
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     */
    public ReflectionBeanPropertyFactory() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> BeanProperty<T> create(String propertyName, Field field, Class<T> propertyType, Method setMethod,
            Method getMethod, Class<?> ownerType, Class<?> declaringType) {
        return new BeanProperty<T>(propertyName, field, propertyType, setMethod, getMethod, ownerType, declaringType);
    }

    // /**
    // * {@inheritDoc}
    // */
    // @Override
    // public <T> BeanProperty<T> create(Class<T> type, String propertyName) {
    // try {
    // Field field = type.getDeclaredField(propertyName);
    // Method getter = ClassUtils.getGetter(field, type);
    // Method setter = ClassUtils.getSetter(field, type);
    // return create(type, field, setter, getter);
    // } catch (Exception e) {
    // LogUtils.debug(e, LOGGER);
    // throw new NoSuchPropertyException(type, propertyName, e);
    // }
    // }
}
