package cn.featherfly.common.bean;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.bean.rule.CopyRule;
import cn.featherfly.common.bean.rule.CopyRuleAlwaysCopy;
import cn.featherfly.common.bean.rule.CopyRules;
import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.ClassUtils;
import cn.featherfly.common.lang.Lang;

/**
 * JAVA BEAN 工具类.
 *
 * @author zhongj
 */
public final class BeanUtils {

    private BeanUtils() {
    }

    //    private static final cn.featherfly.common.logging.Logger LOGGER = LoggerManager.getLogger();

    private static final Logger LOGGER = LoggerFactory.getLogger(BeanUtils.class);

    private static final CopyRule DEFAULT_RULE = new CopyRuleAlwaysCopy();

    /**
     * <p>
     * 设置属性值
     * </p>
     *
     * @param <E> 传入对象类型
     * @param bean 对象
     * @param name 属性名
     * @param value 属性值
     */
    public static <E> void setProperty(E bean, String name, Object value) {
        AssertIllegalArgument.isNotNull(bean, "E bean");
        AssertIllegalArgument.isNotEmpty(name, "String name");
        BeanDescriptor<
            E> beanDescriptor = BeanDescriptor.getBeanDescriptor(ClassUtils.castGenericType(bean.getClass(), bean));
        beanDescriptor.setProperty(bean, name, value);
    }

    /**
     * <p>
     * 添加属性值（如果添加目标是Collection，则会一直添加，如果是其他类型同setProperty）
     * </p>
     *
     * @param <E> 传入对象类型
     * @param bean 对象
     * @param name 属性名
     * @param value 属性值
     */
    public static <E> void addProperty(E bean, String name, Object value) {
        AssertIllegalArgument.isNotNull(bean, "E bean");
        AssertIllegalArgument.isNotEmpty(name, "String name");
        BeanDescriptor<
            E> beanDescriptor = BeanDescriptor.getBeanDescriptor(ClassUtils.castGenericType(bean.getClass(), bean));
        beanDescriptor.getChildBeanProperty(name);
        beanDescriptor.addProperty(bean, name, value);
    }

    /**
     * <p>
     * 设置字段值（绕过访问控制，可以设置私有字段）
     * </p>
     *
     * @param <E> 传入对象类型
     * @param bean 对象
     * @param name 字段名
     * @param value 字段值
     */
    public static <E> void setField(E bean, String name, Object value) {
        AssertIllegalArgument.isNotNull(bean, "E bean");
        AssertIllegalArgument.isNotEmpty(name, "String name");
        BeanDescriptor<
            E> beanDescriptor = BeanDescriptor.getBeanDescriptor(ClassUtils.castGenericType(bean.getClass(), bean));
        beanDescriptor.getBeanProperty(name).setValueForce(bean, value);
    }

    /**
     * <p>
     * 返回属性值
     * </p>
     *
     * @param <E> 传入对象类型
     * @param bean 对象
     * @param name 属性名
     * @return 属性值
     */
    public static <E> Object getProperty(E bean, String name) {
        AssertIllegalArgument.isNotNull(bean, "E bean");
        AssertIllegalArgument.isNotEmpty(name, "String name");
        BeanDescriptor<
            E> beanDescriptor = BeanDescriptor.getBeanDescriptor(ClassUtils.castGenericType(bean.getClass(), bean));
        return beanDescriptor.getProperty(bean, name);
    }

    /**
     * <p>
     * 返回字段值（绕过访问控制，可以访问私有字段）
     * </p>
     *
     * @param <E> 传入对象类型
     * @param bean 对象
     * @param name 字段名
     * @return 字段值
     */
    public static <E> Object getField(E bean, String name) {
        AssertIllegalArgument.isNotNull(bean, "E bean");
        AssertIllegalArgument.isNotEmpty(name, "String name");
        BeanDescriptor<
            E> beanDescriptor = BeanDescriptor.getBeanDescriptor(ClassUtils.castGenericType(bean.getClass(), bean));
        return beanDescriptor.getBeanProperty(name).getValueForce(bean);
    }

    /**
     * <p>
     * 用传入参数的可读属性生成MAP
     * </p>
     *
     * @param <E> 传入对象类型
     * @param bean 传入对象
     * @return 装可读属性的MAP
     */
    public static <E> Map<String, Object> toMap(E bean) {
        Map<String, Object> map = new HashMap<>();
        if (bean == null) {
            return map;
        }
        BeanDescriptor<
            E> beanDescriptor = BeanDescriptor.getBeanDescriptor(ClassUtils.castGenericType(bean.getClass(), bean));
        for (BeanProperty<E, ?> beanProperty : beanDescriptor.getBeanProperties()) {
            if (beanProperty.isReadable()) {
                map.put(beanProperty.getName(), beanProperty.getValue(bean));
            }
        }
        return map;
    }

    /**
     * <p>
     * 以传入类型生成的实例对象，并用传入的MAP填充其属性值（可写属性，忽略map中的null值）.
     * </p>
     *
     * @param <E> 传入对象类型
     * @param map 传入map
     * @param type 对象类型
     * @return 生成的对象
     */
    public static <E> E fromMap(Map<String, Object> map, Class<E> type) {
        return fromMap(map, type, true);
    }

    /**
     * <p>
     * 以传入类型生成的实例对象，并用传入的MAP填充其属性值（可写属性）.
     * </p>
     *
     * @param <E> 传入对象类型
     * @param map 传入map
     * @param type 对象类型
     * @param ignoreNull 是否忽略map中的null值
     * @return 生成的实例
     */
    public static <E> E fromMap(Map<String, Object> map, Class<E> type, boolean ignoreNull) {
        AssertIllegalArgument.isNotNull(type, "Class<E> type");
        E e = BeanUtils.instantiateClass(type);
        fillProperties(map, e, ignoreNull);
        return e;
    }

    /**
     * <p>
     * 用传入的MAP填充传入的bean的属性值（可写属性），忽略map中的null值.
     * </p>
     *
     * @param <E> 传入对象类型
     * @param map 传入map
     * @param bean 传入对象
     */
    public static <E> void fillProperties(Map<String, Object> map, E bean) {
        fillProperties(map, bean, true);
    }

    /**
     * <p>
     * 用传入的MAP填充传入的bean的属性值（可写属性）.
     * </p>
     *
     * @param <E> 传入对象类型
     * @param map 传入map
     * @param bean 传入对象
     * @param ignoreNull 是否忽略map中的null值
     */
    public static <E> void fillProperties(Map<String, Object> map, E bean, boolean ignoreNull) {
        AssertIllegalArgument.isNotNull(bean, "Class<E> type");
        if (Lang.isEmpty(map)) {
            return;
        }
        BeanDescriptor<
            E> beanDescriptor = BeanDescriptor.getBeanDescriptor(ClassUtils.castGenericType(bean.getClass(), bean));
        for (BeanProperty<E, ?> beanProperty : beanDescriptor.getBeanProperties()) {
            String name = beanProperty.getName();
            if (beanProperty.isWritable() && map.containsKey(name)) {
                Object value = map.get(name);
                if (ignoreNull && value == null) {
                    continue;
                }
                beanProperty.setValue(bean, value);
            }
        }
    }

    /**
     * <p>
     * 对象属性合并
     * </p>
     *
     * @param <E> 传入对象类型
     * @param target 目标对象
     * @param from 源对象
     */
    public static <E> void mergeProperties(E target, E from) {
        mergeProperties(target, from, DEFAULT_RULE);
    }

    /**
     * <p>
     * 对象属性合并
     * </p>
     *
     * @param <E> 传入对象类型
     * @param target 目标对象
     * @param from 源对象
     * @param copyRuleEnum 已有的复制规则枚举对象
     */
    public static <E> void mergeProperties(E target, E from, CopyRules copyRuleEnum) {
        mergeProperties(target, from, copyRuleEnum.getCopyRule());
    }

    /**
     * <p>
     * 对象属性合并
     * </p>
     *
     * @param <E> 传入对象类型
     * @param target 目标对象
     * @param from 源对象
     * @param rule 复制规则
     */
    public static <E> void mergeProperties(E target, E from, CopyRule rule) {
        if (target == null) {
            LOGGER.debug("目标对象target为空");
            return;
        }
        if (from == null) {
            LOGGER.debug("来源对象from为空");
            return;
        }
        BeanDescriptor<E> targetBeanDescriptor = BeanDescriptor
            .getBeanDescriptor(ClassUtils.castGenericType(target.getClass(), target));
        BeanDescriptor<
            E> fromBeanDescriptor = BeanDescriptor.getBeanDescriptor(ClassUtils.castGenericType(from.getClass(), from));
        Iterator<BeanProperty<E, ?>> iter = fromBeanDescriptor.getBeanProperties().iterator();
        while (iter.hasNext()) {
            BeanProperty<E, ?> fromProperty = iter.next();
            String name = fromProperty.getName();
            copyProperty(target, targetBeanDescriptor, from, fromProperty, name, rule);
        }
    }

    /**
     * <p>
     * 对象属性复制，复制源和复制目标必须是一样的类型或者继承关系
     * </p>
     *
     * @param <E> 传入对象类型
     * @param target 目标对象
     * @param from 源对象
     */
    public static <E> void copyProperties(E target, E from) {
        copyProperties(target, from, DEFAULT_RULE);
    }

    /**
     * <p>
     * 对象属性复制，复制源和复制目标必须是一样的类型或者继承关系
     * </p>
     *
     * @param <E> 传入对象类型
     * @param target 目标对象
     * @param from 源对象
     * @param copyRuleEnum 已有的复制规则枚举对象
     */
    public static <E> void copyProperties(E target, E from, CopyRules copyRuleEnum) {
        copyProperties(target, from, copyRuleEnum.getCopyRule());
    }

    /**
     * <p>
     * 对象属性复制，复制源和复制目标必须是一样的类型或者继承关系
     * </p>
     *
     * @param <E> 传入对象类型
     * @param target 目标对象
     * @param from 源对象
     * @param rule 复制规则
     */
    public static <E> void copyProperties(E target, E from, CopyRule rule) {
        if (target == null) {
            LOGGER.debug("目标对象target为空");
            return;
        }
        if (from == null) {
            LOGGER.debug("来源对象from为空");
            return;
        }
        if (target.getClass() == from.getClass() || ClassUtils.isParent(from.getClass(), target.getClass())) {
            BeanDescriptor<E> targetBeanDescriptor = BeanDescriptor
                .getBeanDescriptor(ClassUtils.castGenericType(target.getClass(), target));
            BeanDescriptor<E> fromBeanDescriptor = BeanDescriptor
                .getBeanDescriptor(ClassUtils.castGenericType(from.getClass(), from));
            Iterator<BeanProperty<E, ?>> iter = fromBeanDescriptor.getBeanProperties().iterator();
            while (iter.hasNext()) {
                BeanProperty<E, ?> fromProperty = iter.next();
                String name = fromProperty.getName();
                copyProperty(target, targetBeanDescriptor, from, fromProperty, name, rule);
            }

            for (Field field : from.getClass().getFields()) {
                try {
                    ClassUtils.setFieldValue(target, field.getName(), ClassUtils.getFieldValue(from, field.getName()));
                } catch (Exception e) {
                    // no field in from, ignore
                }
            }

        } else if (ClassUtils.isParent(target.getClass(), from.getClass())) {
            BeanDescriptor<E> targetBeanDescriptor = BeanDescriptor
                .getBeanDescriptor(ClassUtils.castGenericType(target.getClass(), target));
            //            BeanDescriptor<E> fromBeanDescriptor = BeanDescriptor.getBeanDescriptor(
            //                    ClassUtils.castGenericType(from.getClass(), from));
            // 目标是父类，所以循环目标的属性
            Iterator<BeanProperty<E, ?>> iter = targetBeanDescriptor.getBeanProperties().iterator();
            while (iter.hasNext()) {
                BeanProperty<E, ?> fromProperty = iter.next();
                String name = fromProperty.getName();
                copyProperty(target, from, name, rule);
            }
            for (Field field : target.getClass().getFields()) {
                try {
                    ClassUtils.setFieldValue(target, field.getName(), ClassUtils.getFieldValue(from, field.getName()));
                } catch (Exception e) {
                    // no field in from, ignore
                }
            }
        } else {
            throw new IllegalArgumentException(String.format("目标对象和源对象不是相同类型也不是继承关系target[%s]，from[%s]",
                target.getClass().getName(), from.getClass().getName()));
        }
    }

    /**
     * <p>
     * 对象指定的属性复制
     * </p>
     *
     * @param <E> 传入对象类型
     * @param target 目标对象
     * @param from 源对象
     * @param name 属性名称
     */
    public static <E> void copyProperty(E target, E from, String name) {
        copyProperty(target, from, name, DEFAULT_RULE);
    }

    /**
     * <p>
     * 对象指定的属性复制
     * </p>
     *
     * @param <E> 传入对象类型
     * @param target 目标对象
     * @param from 源对象
     * @param name 属性名称
     * @param copyRuleEnum 已有的复制规则枚举对象
     */
    public static <E> void copyProperty(E target, E from, String name, CopyRules copyRuleEnum) {
        copyProperty(target, from, name, copyRuleEnum.getCopyRule());
    }

    /**
     * <p>
     * 对象指定的属性复制
     * </p>
     *
     * @param <E> 传入对象类型
     * @param target 目标对象
     * @param from 源对象
     * @param name 属性名称
     * @param rule 复制规则
     */
    public static <E> void copyProperty(E target, E from, String name, CopyRule rule) {
        @SuppressWarnings("unchecked")
        BeanDescriptor<E> targetBeanDescriptor = BeanDescriptor.getBeanDescriptor((Class<E>) target.getClass());
        @SuppressWarnings("unchecked")
        BeanDescriptor<E> fromBeanDescriptor = BeanDescriptor.getBeanDescriptor((Class<E>) from.getClass());
        BeanProperty<E, ?> fromProperty = fromBeanDescriptor.getBeanProperty(name);
        copyProperty(target, targetBeanDescriptor, from, fromProperty, name, rule);
    }

    private static <E> void copyProperty(E target, BeanDescriptor<?> targetBeanDescriptor, E from,
        BeanProperty<E, ?> fromProperty, String name, CopyRule rule) {
        try {
            BeanProperty<E, ?> targetProperty = targetBeanDescriptor.getBeanProperty(name);
            copyProperty(target, targetProperty, from, fromProperty, name, rule);
        } catch (NoSuchPropertyException e) {
            LOGGER.debug("类{}没有属性{}", new Object[] { target.getClass().getName(), name });
            return;
        }
        //        if (!fromProperty.isReadable()) {
        //            LOGGER.debug("类{}的属性{}不可读", new Object[]{from.getClass().getName()
        //                    , fromProperty.getName()});
        //        }
        //        Object value = fromProperty.getValue(from);
        //        if (rule.isCopyEnabled(target, from, name, value)) {
        //            targetBeanDescriptor.getBeanProperty(name)
        //                .setValue(target, value);
        //        }
        //            LOGGER.debug("类{}的属性{}不是可读写属性", new Object[]{from.getClass().getName()
        //                                , fromProperty.getName()});
    }

    private static <E> void copyProperty(E target, BeanProperty<E, ?> targetProperty, E from,
        BeanProperty<E, ?> fromProperty, String name, CopyRule rule) {
        if (!fromProperty.isReadable()) {
            LOGGER.debug("类{}的属性{}不可读", new Object[] { from.getClass().getName(), fromProperty.getName() });
        } else if (!targetProperty.isWritable()) {
            LOGGER.debug("类{}的属性{}不可写", new Object[] { target.getClass().getName(), targetProperty.getName() });
        } else {
            Object value = fromProperty.getValue(from);
            if (rule.isCopyEnabled(target, from, name, value)) {
                targetProperty.setValue(target, value);
            }
        }
    }

    // ********************************************************************
    //    原框架类中的方法
    // ********************************************************************

    /**
     * 实例化.
     *
     * @param clazz 类型
     * @param <T> 泛型
     * @return 对象
     */
    public static <T> T instantiateClass(Class<T> clazz) {
        return ClassUtils.newInstance(clazz);
    }

    /**
     * 实例化.
     *
     * @param clazz 类型
     * @param args 参数数组
     * @param <T> 泛型
     * @return 对象
     */
    public static <T> T instantiateClass(Class<T> clazz, Object... args) {
        return ClassUtils.newInstance(clazz, args);
    }

    /**
     * 实例化.
     *
     * @param ctor 构造器
     * @param args 参数数组
     * @param <T> 泛型
     * @return 对象
     */
    public static <T> T instantiateClass(Constructor<T> ctor, Object[] args) {
        return ClassUtils.newInstance(ctor, args);
    }

    public static <E> String toString(E object) {
        if (object == null) {
            return "null";
        }
        final StringBuilder builder = new StringBuilder();
        builder.append(object.getClass().getSimpleName()).append("[\n");
        BeanDescriptor<
            E> beanDescriptor = BeanDescriptor.getBeanDescriptor(ClassUtils.castGenericType(object.getClass(), object));
        beanDescriptor.getBeanProperties().forEach(bp -> {
            builder.append("  ").append(bp.getName()).append(" = ").append(bp.getValueForce(object)).append("\n");
        });
        builder.append("]");
        return builder.toString();
    }
}
