
package cn.featherfly.common.bean;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.lang.ClassUtils;


/**
 * <p>
 * map 的描述信息
 * </p>
 *
 * @author 钟冀
 * @param <T> 描述的类型
 */
public final class MapBeanDescriptor<T> extends BeanDescriptor<T>{

    private static final Logger LOGGER = LoggerFactory.getLogger(MapBeanDescriptor.class);

    /**
     * @param type 类型
     */
    protected MapBeanDescriptor(Class<T> type) {
        super(type);
        if (!ClassUtils.isParent(Map.class, type)) {
            throw new IllegalArgumentException(String.format("类型%s不是Map类型及其子类型", type.getName()));
        }
    }

    /**
     * <p>
     * 设置属性
     * @param obj 目标对象
     * @param name 属性名称
     * @param value 属性值
     */
    @Override
    @SuppressWarnings("unchecked")
    public void setProperty(T obj, String name, Object value) {
        if (obj == null) {
            return ;
        }
        if (!(obj instanceof Map)) {
            throw new IllegalArgumentException(String.format("类型%s不是Map类型及其子类型", obj.getClass().getName()));
        }
        Map<String, Object> map = (Map<String, Object>) obj;
        if (name.contains(DOT)) {
            String currentPropertyName = name.substring(0, name.indexOf(DOT));
            String innerPropertyName = name.substring(name.indexOf(DOT) + 1);
            Object propertyValue = map.get(currentPropertyName);
            //中间层次为空，返回，因为不知道具体类型
            if (propertyValue == null) {
                LOGGER.trace("类{}的属性[{}]为空，将忽略嵌套的赋值",
                                new Object[]{type.getName(), name});
                return;
            }
            // getBeanDescriptor 会根据类型自动返回相应的 BeanDescriptor
            @SuppressWarnings("rawtypes")
            BeanDescriptor propertyDescriptor = getBeanDescriptor(propertyValue.getClass());
            propertyDescriptor.setProperty(propertyValue, innerPropertyName, value);
        } else {
            map.put(name, value);
        }
    }

    /**
     * <p>
     * 返回属性
     * </p>
     * @param obj 目标对象
     * @param name 属性名
     * @return 属性
     */
    @Override @SuppressWarnings("unchecked")
    public Object getProperty(T obj, String name) {
        if (obj == null) {
            return null;
        }
        if (!(obj instanceof Map)) {
            throw new IllegalArgumentException(String.format("类型%s不是Map类型及其子类型", obj.getClass().getName()));
        }
        Map<String, Object> map = (Map<String, Object>) obj;
        if (name.contains(DOT)) {
            String currentPropertyName = name.substring(0, name.indexOf(DOT));
            String innerPropertyName = name.substring(name.indexOf(DOT) + 1);
            Object propertyValue = map.get(currentPropertyName);
            //如果层次中间一个为空，返回空
            Object result = null;
            if (propertyValue == null) {
                LOGGER.trace("类{}的属性[{}]为空",
                                new Object[]{type.getName(), currentPropertyName});
            } else {
                // getBeanDescriptor 会根据类型自动返回相应的 BeanDescriptor
                @SuppressWarnings("rawtypes")
                BeanDescriptor propertyDescriptor = getBeanDescriptor(propertyValue.getClass());
                result = propertyDescriptor.getProperty(propertyValue, innerPropertyName);
            }
            return result;
        } else {
            return map.get(name);
        }
    }
}
