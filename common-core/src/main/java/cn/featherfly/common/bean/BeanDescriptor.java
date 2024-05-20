
package cn.featherfly.common.bean;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.collections4.map.ListOrderedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.bean.matcher.BeanPropertyMatcher;
import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.function.serializable.SerializableBiConsumer;
import cn.featherfly.common.function.serializable.SerializableConsumer;
import cn.featherfly.common.function.serializable.SerializableFunction;
import cn.featherfly.common.function.serializable.SerializableSupplier;
import cn.featherfly.common.lang.ClassUtils;
import cn.featherfly.common.lang.CollectionUtils;
import cn.featherfly.common.lang.LambdaUtils;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.lang.ServiceLoaderUtils;
import cn.featherfly.common.lang.reflect.Modifier;

/**
 * java bean 的描述信息.
 *
 * @author zhongj
 * @param <T> 描述的类型
 */
public class BeanDescriptor<T> extends AbstractPropertyAccessor<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(BeanDescriptor.class);

    private static final Map<Class<?>, BeanDescriptor<?>> BEAN_DESCRIPTORS = new ConcurrentHashMap<>();

    private static final BeanPropertyFactory FACTORY = ServiceLoaderUtils.load(BeanPropertyFactory.class,
        new ReflectionBeanPropertyFactory());

    private ListOrderedMap<String, BeanProperty<T, ?>> beanProperties = new ListOrderedMap<>();

    private Map<String, Type> typeGenericParams = new HashMap<>(0);

    /**
     * 句号（.）
     */
    protected static final char DOT = '.';

    /**
     * Instantiates a new bean descriptor.
     *
     * @param type 类型
     */
    protected BeanDescriptor(Class<T> type) {
        this.type = type;

        initTypeGenericParam(this.type);
        int index = 0;
        index = this.initFromField(this.type, index);
        this.initFromMethod(this.type, index);
    }

    /**
     * <p>
     * 初始化泛型参数
     * </p>
     * .
     *
     * @param type type
     */
    protected void initTypeGenericParam(Class<T> type) {
        // 得到泛型父类
        typeGenericParams = ClassUtils.getSuperClassGenericTypeMap(type);
    }

    // 从field开始初始化
    private int initFromField(Class<?> initType, int index) {
        if (null == initType || initType == Object.class) {
            return index;
        }
        Field[] fields = initType.getDeclaredFields();
        for (Field field : fields) {
            Class<?> fieldType = getGenericType(field.getGenericType(), field.getType());
            Method getter = ClassUtils.getGetter(field, type);
            Method setter = ClassUtils.getSetter(field, type);
            if (getter != null || setter != null) {
                BeanProperty<T, ?> prop = FACTORY.create(index, field.getName(), field, fieldType, setter, getter, type,
                    field.getDeclaringClass());
                beanProperties.put(prop.getName(), prop);
                if (LOGGER.isTraceEnabled() && initType != type) {
                    LOGGER.trace("类{}从父类{}中继承的属性：[{}]", type.getName(), initType.getName(), prop.getName());
                }
            }
            index++;
        }
        // 到父类中查找属性
        return initFromField(initType.getSuperclass(), index);
    }

    // 初始化动态set get方法
    private int initFromMethod(Class<T> type, int index) {
        Map<String, Map<String, Object>> properties = new HashMap<>();
        for (Method method : type.getMethods()) {
            // 忽略Object对象和static method
            if (Modifier.STATIC.isModifier(method.getModifiers()) || method.getDeclaringClass() == Object.class) {
                continue;
            }
            if (ClassUtils.isGetter(method)) {
                String propertyName = ClassUtils.getPropertyName(method);
                if (!hasBeanProperty(propertyName)) {
                    Map<String, Object> prop = getProperty(properties, propertyName);
                    prop.put("get", method);
                    prop.put("ownerType", type);
                    // 因为先使用field初始化了，所以现在都应该只剩动态getter或动态setter了
                    /*
                     * try { Field field = ClassUtils
                     * .getField(method.getDeclaringClass(), propertyName);
                     * prop.put("field", field); if (field.getType() ==
                     * method.getReturnType()) { prop.put("get", method); } else
                     * { prop.put("get", method); } } catch
                     * (NoSuchFieldException e) { LOGGER.debug(e.getMessage());
                     * }
                     */
                }
            } else if (ClassUtils.isSetter(method)) {
                String propertyName = ClassUtils.getPropertyName(method);
                if (!hasBeanProperty(propertyName)) {
                    Map<String, Object> prop = getProperty(properties, propertyName);
                    prop.put("set", method);
                    prop.put("ownerType", type);
                }
                // 因为先使用field初始化了，所以现在都应该只剩动态getter或动态setter了
                /*
                 * if (prop.get("field") == null) { try { Field field =
                 * ClassUtils.getField(method.getDeclaringClass(),
                 * propertyName); prop.put("field", field); } catch
                 * (NoSuchFieldException e) { LOGGER.debug(e.getMessage()); } }
                 */
            }
        }
        for (Map<String, Object> prop : properties.values()) {
            Class<?> propertyType = null;
            Class<?> declaringType = null;
            String propertyName = null;
            // Field field = null;
            Method setter = null;
            Method getter = null;
            /*
             * if (prop.get("field") != null) { field = (Field)
             * prop.get("field"); declaringType = field.getDeclaringClass();
             * propertyName = field.getName(); propertyType =
             * getGenericType(field.getGenericType(), field.getType()); }
             */
            if (prop.get("get") != null) {
                getter = (Method) prop.get("get");
                declaringType = Lang.ifNull(declaringType, getter.getDeclaringClass());
                propertyType = Lang.ifNull(propertyType,
                    getGenericType(getter.getGenericReturnType(), getter.getReturnType()));
                propertyName = Lang.ifNull(propertyName, ClassUtils.getPropertyName(getter));
            }
            if (prop.get("set") != null) {
                setter = (Method) prop.get("set");
                declaringType = Lang.ifNull(declaringType, setter.getDeclaringClass());
                propertyType = Lang.ifNull(propertyType,
                    getGenericType(setter.getGenericParameterTypes()[0], setter.getParameterTypes()[0]));
                propertyName = Lang.ifNull(propertyName, ClassUtils.getPropertyName(setter));
            }
            BeanProperty<T, ?> property = FACTORY.create(index, propertyName, null, propertyType, setter, getter, type,
                declaringType);
            beanProperties.put(property.getName(), property);
            if (LOGGER.isTraceEnabled()) {
                LOGGER.trace("类{}的属性：[{}]， 定义子类{}", property.getOwnerType().getName(), property.getName(),
                    property.getDeclaringType().getName());
            }
            index++;
        }
        return index;
    }

    private Class<?> getGenericType(Type genericTypeDeclaring, Class<?> genericType) {
        Type gt = typeGenericParams.get(genericTypeDeclaring.toString());
        // 判断类型定义的泛型参数
        if (gt == null) {
            return genericType;
        } else if (gt instanceof TypeVariable) {
            return ClassUtils.forName(((TypeVariable<?>) gt).getBounds()[0].getTypeName());
        } else {
            return (Class<?>) gt;
        }
    }

    private Map<String, Object> getProperty(Map<String, Map<String, Object>> properties, String propertyName) {
        return properties.computeIfAbsent(propertyName, key -> new HashMap<>());
    }

    /**
     * Gets the bean properties.
     *
     * @return 返回beanProperties
     */
    public Collection<BeanProperty<T, ?>> getBeanProperties() {
        return beanProperties.values();
    }

    /**
     * 返回指定索引属性.
     *
     * @param <E> the element type
     * @param index 索引
     * @return 指定属性
     */
    @SuppressWarnings("unchecked")
    public <E> BeanProperty<T, E> getBeanProperty(int index) {
        return (BeanProperty<T, E>) beanProperties.getValue(index);
    }

    /**
     * 返回指定属性. 如果没有则抛出NoSuchPropertyException异常.
     *
     * @param <B> the generic type
     * @param <E> the element type
     * @param name 属性名
     * @return 指定属性
     */
    public <B, E> BeanProperty<B, E> getBeanProperty(String name) {
        BeanProperty<B, E> property = getBeanProperty2(name);
        if (property == null) {
            throw new NoSuchPropertyException(type, name);
        }
        return property;
    }

    @SuppressWarnings("unchecked")
    private <B, E> BeanProperty<B, E> getBeanProperty2(String name) {
        int index = name.indexOf(Chars.DOT_CHAR);
        if (index != -1) {
            String fn = name.substring(0, index);
            String last = name.substring(index + 1, name.length());
            BeanProperty<?, ?> property = beanProperties.get(fn);
            if (property == null) {
                return null;
            }
            BeanDescriptor<?> bd = BeanDescriptor.getBeanDescriptor(beanProperties.get(fn).getType());
            return bd.getBeanProperty(last);
        } else {
            return (BeanProperty<B, E>) beanProperties.get(name);
        }
    }

    @SuppressWarnings("unchecked")
    private <B, E> BeanProperty<B, E> getBeanProperty0(String name) {
        BeanProperty<B, E> bp = (BeanProperty<B, E>) beanProperties.get(name);
        if (bp == null) {
            throw new NoSuchPropertyException(type, name);
        }
        return bp;
    }

    @SuppressWarnings("unchecked")
    private <B, E> BeanProperty<B, E> getBeanProperty0(int index) {
        BeanProperty<B, E> bp = (BeanProperty<B, E>) beanProperties.getValue(index);
        if (bp == null) {
            throw new NoSuchPropertyException(type, index);
        }
        return bp;
    }

    /**
     * 返回指定属性. 如果没有则抛出NoSuchPropertyException异常.
     *
     * @param <R> the property type
     * @param property 属性
     * @return 指定属性
     */
    public <R> BeanProperty<T, R> getBeanProperty(SerializableFunction<T, R> property) {
        return getBeanProperty(LambdaUtils.getLambdaPropertyName(property));
    }

    /**
     * 返回指定属性. 如果没有则抛出NoSuchPropertyException异常.
     *
     * @param <E> the element type
     * @param <R> the property type
     * @param property 属性
     * @return 指定属性
     */
    public <E, R> BeanProperty<T, R> getBeanProperty(SerializableBiConsumer<E, R> property) {
        return getBeanProperty(LambdaUtils.getLambdaPropertyName(property));
    }

    /**
     * 返回指定属性. 如果没有则抛出NoSuchPropertyException异常.
     *
     * @param <R> the property type
     * @param property 属性
     * @return 指定属性
     */
    public <R> BeanProperty<T, R> getBeanProperty(SerializableConsumer<R> property) {
        return getBeanProperty(LambdaUtils.getLambdaPropertyName(property));
    }

    /**
     * 返回指定属性. 如果没有则抛出NoSuchPropertyException异常.
     *
     * @param <R> the property type
     * @param property 属性
     * @return 指定属性
     */
    public <R> BeanProperty<T, R> getBeanProperty(SerializableSupplier<R> property) {
        return getBeanProperty(LambdaUtils.getLambdaPropertyName(property));
    }

    /**
     * 返回指定属性是否存在
     * .
     *
     * @param name 属性名
     * @return 指定属性是否存在
     */
    public boolean hasBeanProperty(String name) {
        return getBeanProperty2(name) != null;
    }

    /**
     * 返回指定子孙属性. 如果没有则抛出NoSuchPropertyException异常.
     *
     * @param name 属性名
     * @return 指定属性
     */
    public BeanProperty<?, ?> getChildBeanProperty(String name) {
        if (name.indexOf(DOT) != -1) {
            String currentPropertyName = name.substring(0, name.indexOf(DOT));
            String innerPropertyName = name.substring(name.indexOf(DOT) + 1);
            BeanProperty<?, ?> property = getBeanProperty(currentPropertyName);
            BeanDescriptor<?> propertyDescriptor = getBeanDescriptor(property.getType());
            return propertyDescriptor.getChildBeanProperty(innerPropertyName);
        } else {
            return getBeanProperty(name);
        }
    }

    /**
     * <p>
     * 查找并返回第一个符合条件BeanProperty. 如果没有则返回null.
     * </p>
     *
     * @param condition 条件判断
     * @return 第一个符合条件BeanProperty
     */
    public BeanProperty<?, ?> findBeanProperty(BeanPropertyMatcher condition) {
        for (BeanProperty<?, ?> beanProperty : getBeanProperties()) {
            if (condition.test(beanProperty)) {
                return beanProperty;
            }
        }
        return null;
    }

    /**
     * 查找并返回所有符合条件BeanProperty的集合. 如果没有则返回一个长度为0的集合.
     *
     * @param condition 条件判断
     * @return 所有符合条件BeanProperty的集合
     */
    public Collection<BeanProperty<?, ?>> findBeanPropertys(BeanPropertyMatcher condition) {
        Collection<BeanProperty<?, ?>> coll = new ArrayList<>();
        for (BeanProperty<?, ?> beanProperty : getBeanProperties()) {
            if (condition.test(beanProperty)) {
                coll.add(beanProperty);
            }
        }
        return coll;
    }

    /**
     * set property value.<br>
     * 设置属性值.
     *
     * @param obj 目标对象
     * @param name 属性名称
     * @param value 属性值
     */
    @SuppressWarnings("unchecked")
    public void setProperty(T obj, String name, Object value) {
        if (name.indexOf(DOT) != -1) {
            String currentPropertyName = name.substring(0, name.indexOf(DOT));
            String innerPropertyName = name.substring(name.indexOf(DOT) + 1);
            BeanProperty<?, ?> property = getBeanProperty(currentPropertyName);
            Object propertyValue = property.getValue(obj);
            @SuppressWarnings("rawtypes")
            BeanDescriptor propertyDescriptor = null;
            // 中间层次为空，使用默认构造函数生成一个空对象
            if (propertyValue == null) {
                propertyDescriptor = getBeanDescriptor(property.getType());
                try {
                    if (ClassUtils.isCellection(property.getType())) {
                        LOGGER.trace("类{}的属性[{}]为空，对象为Collection接口实现类，自动创建该属性对象", property.getOwnerType().getName(),
                            property.getName());
                        propertyValue = CollectionUtils.newInstance(property.getType());
                    } else if (ClassUtils.isMap(property.getType())) {
                        LOGGER.trace("类{}的属性[{}]为空，对象为MAP接口，自动创建该属性对象", property.getOwnerType().getName(),
                            property.getName());
                        propertyValue = CollectionUtils.newMap(property.getType());
                    } else if (property.getType() == Optional.class) {
                        LOGGER.trace("类{}的属性[{}]为空，对象为Optional容器，自动创建Optional和其对应的泛型对象",
                            property.getOwnerType().getName(), property.getName());
                        propertyValue = Optional.of(ClassUtils.newInstance(property.getGenericType()));
                    } else {
                        LOGGER.trace("类{}的属性[{}]为空，自动创建该属性对象[使用newInstance()]", property.getOwnerType().getName(),
                            property.getName());

                        propertyValue = ClassUtils.newInstance(property.getType());
                    }
                    property.setValue(obj, propertyValue);
                } catch (Exception e) {
                    throw new IllegalArgumentException(String.format("类%s缺少没有参数的构造函数", property.getType().getName()),
                        e);
                }
            } else {
                propertyDescriptor = getBeanDescriptor(propertyValue.getClass());
            }
            propertyDescriptor.setProperty(propertyValue, innerPropertyName, value);
        } else {
            BeanProperty<T, ?> p = getBeanProperty(name);
            p.setValue(obj, value);
        }
    }

    /**
     * <p>
     * 添加属性值（如果添加目标是Collection，则会一直添加，如果是其他类型同setProperty）
     * </p>
     * .
     *
     * @param obj 对象
     * @param name 属性名
     * @param value 属性值
     */
    public void addProperty(T obj, String name, Object value) {
        BeanProperty<?, ?> beanProperty = getChildBeanProperty(name);
        if (ClassUtils.isCellection(beanProperty.getType())) {
            @SuppressWarnings("unchecked")
            Collection<Object> collection = (Collection<Object>) getProperty(obj, name);
            if (collection == null) {
                collection = CollectionUtils.newInstance(beanProperty.getType());
                setProperty(obj, name, collection);
            }
            collection.add(value);
        } else {
            setProperty(obj, name, value);
        }
    }

    /**
     * get property value. <br>
     * 返回属性值.
     *
     * @param obj 目标对象
     * @param name 属性名
     * @return 属性
     */
    @SuppressWarnings("unchecked")
    public Object getProperty(T obj, String name) {
        if (name.indexOf(DOT) != -1) {
            String currentPropertyName = name.substring(0, name.indexOf(DOT));
            String innerPropertyName = name.substring(name.indexOf(DOT) + 1);
            BeanProperty<?, ?> property = getBeanProperty(currentPropertyName);
            Object propertyValue = property.getValue(obj);
            @SuppressWarnings("rawtypes")
            BeanDescriptor propertyDescriptor = null;
            // 如果层次中间一个为空，返回空
            if (propertyValue == null) {
                LOGGER.trace("类{}的属性[{}]为空", property.getOwnerType().getName(), property.getName());
                return null;
            } else {
                propertyDescriptor = getBeanDescriptor(propertyValue.getClass());
                return propertyDescriptor.getProperty(propertyValue, innerPropertyName);
            }
        } else {
            BeanProperty<?, ?> p = getBeanProperty(name);
            return p.getValue(obj);
        }
    }

    /**
     * <p>
     * 返回当前对象是否含有指定注解.
     * </p>
     *
     * @param <A> 注解类型
     * @param annotationClass 注解类型
     * @return 是否含有指定注解
     */
    public <A extends Annotation> boolean hasAnnotation(Class<A> annotationClass) {
        return getAnnotation(annotationClass) != null;
    }

    /**
     * <p>
     * 返回当前对象的指定类型注解.
     * </p>
     *
     * @param <A> 注解类型
     * @param annotationClass 注解类型
     * @return 当前对象的指定类型注解
     */
    public <A extends Annotation> A getAnnotation(Class<A> annotationClass) {
        try {
            return type.getAnnotation(annotationClass);
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * <p>
     * 返回当前对象的所有注解
     * </p>
     * .
     *
     * @return 当前对象的所有注解
     */
    public Annotation[] getAnnotations() {
        return type.getAnnotations();
    }

    // ********************************************************************
    // static method
    // ********************************************************************

    /**
     * 返回指定类型的描述.
     *
     * @param <T> 类型
     * @param type 类型
     * @return 指定类型的描述
     */
    public static <T> BeanDescriptor<T> getBeanDescriptor(Class<T> type) {
        @SuppressWarnings("unchecked")
        BeanDescriptor<T> bd = (BeanDescriptor<T>) BEAN_DESCRIPTORS.get(type);
        if (bd == null) {
            // MAP使用一个子类处理
            if (ClassUtils.isParent(Map.class, type)) {
                bd = new MapBeanDescriptor<>(type);
            } else {
                bd = new BeanDescriptor<>(type);
            }
            BEAN_DESCRIPTORS.put(type, bd);
        }
        return bd;
    }

    // ********************************************************************
    // private method
    // ********************************************************************

    // private BeanProperty<?,?> getTargetBeanProperty(Object obj, String name) {
    // if (name.contains(DOT)) {
    // String currentPropertyName = name.substring(0, name.indexOf(DOT));
    // String innerPropertyName = name.substring(name.indexOf(DOT) + 1);
    // BeanProperty<?,?> property = getBeanProperty(currentPropertyName);
    // Object propertyValue = property.getValue(obj);
    // //中间层次为空，使用默认构造函数生成一个空对象
    // if (propertyValue == null) {
    // LOGGER.debug("类{}的属性[{}]为空，自动创建该属性对象[使用newInstance()]",
    // new Object[]{property.getOwnerType().getName(), property.getName()});
    // try {
    // propertyValue = property.getType().newInstance();
    // property.setValue(obj, propertyValue);
    // } catch (Exception e) {
    // throw new IllegalArgumentException(
    // String.format("类%s缺少没有参数的构造函数", property.getType().getName())
    // , e);
    // }
    // }
    // return getTargetBeanProperty(propertyValue, innerPropertyName);
    // } else {
    // BeanProperty<?,?> p = getBeanProperty(name);
    // return p;
    // }
    // }

    // ********************************************************************
    // property
    // ********************************************************************

    /** 类型. */
    protected Class<T> type;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "BeanDescriptor [beanProperties=" + beanProperties + ", typeGenericParams=" + typeGenericParams
            + ", type=" + type + "]";
    }

    /**
     * Gets the type.
     *
     * @return 返回type
     */
    @Override
    public Class<T> getType() {
        return type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T instantiate() {
        return ClassUtils.newInstance(type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <V> Property<T, V> getProperty(int index) {
        return getBeanProperty0(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <V> Property<T, V> getProperty(String name) {
        return getBeanProperty0(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getPropertyValue(T obj, int index) {
        return getProperty(index).get(obj);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getPropertyValue(T bean, String name) {
        return getProperty(bean, name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPropertyValue(T obj, int index, Object value) {
        getProperty(index).set(obj, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPropertyValue(T bean, String name, Object value) {
        setProperty(bean, name, value);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public Property<T, ?>[] getProperties() {
        return beanProperties.values().toArray(new Property[beanProperties.size()]);
    }
}
