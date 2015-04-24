
package cn.featherfly.common.bean;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.collections.map.ListOrderedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.bean.condition.BeanPropertyMatcher;
import cn.featherfly.common.lang.ClassUtils;
import cn.featherfly.common.lang.ServiceLoaderUtils;


/**
 * <p>
 * java bean 的描述信息
 * </p>
 *
 * @author 钟冀
 * @param <T> 描述的类型
 */
public class BeanDescriptor<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(BeanDescriptor.class);

    private static final Map<Class<?>, BeanDescriptor<?>> BEAN_DESCRIPTORS =
                    new ConcurrentHashMap<Class<?>, BeanDescriptor<?>>();
    
    private static final BeanPropertyFactory FACTORY = ServiceLoaderUtils.load(BeanPropertyFactory.class
                    , new ReflectionBeanPropertyFactory());
    
//    private static BeanPropertyFactory factory = new ReflectionBeanPropertyFactory();
//    
//    static {
//        ServiceLoader<BeanPropertyFactory> serviceLoader = ServiceLoader.load(BeanPropertyFactory.class);
//        List<BeanPropertyFactory> factorys = new ArrayList<BeanPropertyFactory>();        
//        for (BeanPropertyFactory factory : serviceLoader) {
//            factorys.add(factory);
//        }
//        if (factorys.size() > 1) {
//            throw new IllegalArgumentException("找到多个BeanPropertyFactory实现 -> " + factorys) ;
//        }
//        if (factorys.isEmpty()) {
//            factory = new ReflectionBeanPropertyFactory();
//        } else {
//            factory = factorys.get(0);
//        }
//    }

//    private Map<String, BeanProperty> beanProperties = new HashMap<String, BeanProperty>(0);

    private ListOrderedMap beanProperties = new ListOrderedMap();

    /**
     * 句号（.）
     */
    protected static final String DOT = "." ;

    /**
     * @param type 类型
     */
    protected BeanDescriptor(Class<T> type) {
        this.type = type;
        this.initField(this.type);
    }

    //从field开始初始化
    private void initField(Class<?> parent) {
        // TODO 从field查找完成再查找set,get方法，单纯的设置、读取方法，没有field，用于动态读取、设置
        if (null == parent || parent == Object.class) {
            return ;
        }
        Field[] fields = parent.getDeclaredFields();
        for (Field field : fields) {
            Method getter = ClassUtils.getGetter(field, this.type);
            Method setter = ClassUtils.getSetter(field, this.type);
            if (getter != null || setter != null) {
                BeanProperty<?> prop = FACTORY.create(this.type, field, setter, getter);
                beanProperties.put(prop.getName(), prop);
                if (LOGGER.isTraceEnabled() && parent != this.type) {
                    LOGGER.trace("类{}从父类{}中继承的属性：[{}]", new Object[]{this.type.getName()
                                    , parent.getName(), prop.getName()});
                }
            }
        }
        //到父类中查找属性
        initField(parent.getSuperclass());
    }
//    // 初始化动态set get方法
//    private void initMethod() {
//        for (Method method : this.type.getMethods()) {
//            if (ClassUtils.isGetter(method) && beanProperties.containsKey(method.getName())) {
//                
//            }
//        }
//        for (Method method : this.type.getMethods()) {
//            if (ClassUtils.isSetter(method)) {
//                
//            }
//        }
//    }

    /**
     * @return 返回beanProperties
     */
    @SuppressWarnings("unchecked")
    public Collection<BeanProperty<?>> getBeanProperties() {
        return beanProperties.values();
    }

    /**
     * <p>
     * 返回指定索引属性.
     * </p>
     * @param index 索引
     * @return 指定属性
     */
    public BeanProperty<?> getBeanProperty(int index) {
        return (BeanProperty<?>) beanProperties.getValue(index);
    }

    /**
     * <p>
     * 返回指定属性.
     * 如果没有则抛出NoSuchPropertyException异常
     * </p>
     * @param name 属性名
     * @return 指定属性
     */
    public BeanProperty<?> getBeanProperty(String name) {
        BeanProperty<?> property = (BeanProperty<?>) beanProperties.get(name);
        if (property == null) {
            throw new NoSuchPropertyException(type, name);
        }
        return property;
    }

    /**
     * <p>
     * 返回指定子孙属性.
     * 如果没有则抛出NoSuchPropertyException异常
     * </p>
     * @param name 属性名
     * @return 指定属性
     */
    public BeanProperty<?> getChildBeanProperty(String name) {
        if (name.contains(DOT)) {
            String currentPropertyName = name.substring(0, name.indexOf(DOT));
            String innerPropertyName = name.substring(name.indexOf(DOT) + 1);
            BeanProperty<?> property = getBeanProperty(currentPropertyName);
            BeanDescriptor<?> propertyDescriptor = getBeanDescriptor(property.getType());
            return propertyDescriptor.getChildBeanProperty(innerPropertyName);
        } else {
            return getBeanProperty(name);
        }
    }

    /**
     * <p>
     * 查找并返回第一个符合条件BeanProperty.
     * 如果没有则返回null.
     * </p>
     * @param condition 条件判断
     * @return 第一个符合条件BeanProperty
     */
    public BeanProperty<?> findBeanProperty(BeanPropertyMatcher condition) {
        for (BeanProperty<?> beanProperty : getBeanProperties()) {
            if (condition.match(beanProperty)) {
                return beanProperty;
            }
        }
        return null;
    }
    /**
     * <p>
     * 查找并返回所有符合条件BeanProperty的集合.
     * 如果没有则返回一个长度为0的集合.
     * </p>
     * @param condition 条件判断
     * @return 所有符合条件BeanProperty的集合
     */
    public Collection<BeanProperty<?>> findBeanPropertys(BeanPropertyMatcher condition) {
        Collection<BeanProperty<?>> coll = new ArrayList<>();
        for (BeanProperty<?> beanProperty : getBeanProperties()) {
            if (condition.match(beanProperty)) {
                coll.add(beanProperty);
            }
        }
        return coll;
    }
    /**
     * <p>
     * 设置属性
     * </p>
     * @param obj 目标对象
     * @param name 属性名称
     * @param value 属性值
     */
    @SuppressWarnings("unchecked")
    public void setProperty(T obj, String name, Object value) {
        if (name.contains(DOT)) {
            String currentPropertyName = name.substring(0, name.indexOf(DOT));
            String innerPropertyName = name.substring(name.indexOf(DOT) + 1);
            BeanProperty<?> property = getBeanProperty(currentPropertyName);
            @SuppressWarnings("rawtypes")
            BeanDescriptor propertyDescriptor = getBeanDescriptor(property.getType());
            Object propertyValue = property.getValue(obj);
            //中间层次为空，使用默认构造函数生成一个空对象
            if (propertyValue == null) {
                try {
                    if (property.getType() == Map.class) {
                        LOGGER.trace("类{}的属性[{}]为空，对象为MAP接口，自动创建该属性对象[使用HashMap]", new Object[]
                        {property.getOwnerType().getName(), property.getName()});
                        propertyValue = new HashMap<Object, Object>();
                    } else if (property.getType() == List.class) {
                        LOGGER.trace("类{}的属性[{}]为空，对象为List接口，自动创建该属性对象[使用ArrayList]"
                                        , new Object[]
                                        {property.getOwnerType().getName()
                                                , property.getName()});
                        propertyValue = new ArrayList<Object>();
                    } else if (property.getType() == Set.class) {
                        LOGGER.trace("类{}的属性[{}]为空，对象为Set接口，自动创建该属性对象[使用HashSet]", new Object[]
                        {property.getOwnerType().getName(), property.getName()});
                        propertyValue = new HashSet<Object>();
                    } else {
                        LOGGER.trace("类{}的属性[{}]为空，自动创建该属性对象[使用newInstance()]", new Object[]
                        {property.getOwnerType().getName(), property.getName()});
                        propertyValue = property.getType().newInstance();
                    }
                    property.setValue(obj, propertyValue);
                } catch (Exception e) {
                    throw new IllegalArgumentException(
                            String.format("类%s缺少没有参数的构造函数", property.getType().getName())
                            , e);
                }
            }
            propertyDescriptor.setProperty(propertyValue, innerPropertyName, value);
        } else {
            BeanProperty<?> p = getBeanProperty(name);
            p.setValue(obj, value);
        }
    }

    /**
     * <p>
     * 添加属性值（如果添加目标是Collection，则会一直添加，如果是其他类型同setProperty）
     * </p>
     * @param obj 对象
     * @param name 属性名
     * @param value 属性值
     */
    public void addProperty(T obj, String name, Object value) {
        BeanProperty<?> beanProperty = getChildBeanProperty(name);
        if (ClassUtils.isParent(Collection.class, beanProperty.getType())) {
            @SuppressWarnings("unchecked")
            Collection<Object> collection = (Collection<Object>) getProperty(obj, name);
            if (collection == null) {
                collection = new ArrayList<Object>();
            }
            collection.add(value);
        } else {
            setProperty(obj, name, value);
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
    @SuppressWarnings("unchecked")
    public Object getProperty(T obj, String name) {
        if (name.contains(DOT)) {
            String currentPropertyName = name.substring(0, name.indexOf(DOT));
            String innerPropertyName = name.substring(name.indexOf(DOT) + 1);
            BeanProperty<?> property = getBeanProperty(currentPropertyName);
            @SuppressWarnings("rawtypes")
            BeanDescriptor propertyDescriptor = getBeanDescriptor(property.getType());
            Object propertyValue = property.getValue(obj);
            //如果层次中间一个为空，返回空
            if (propertyValue == null) {
                LOGGER.trace("类{}的属性[{}]为空", new Object[]{property.getOwnerType().getName()
                                , property.getName()});
                return null;
            }
            return propertyDescriptor.getProperty(propertyValue, innerPropertyName);
        } else {
            BeanProperty<?> p = getBeanProperty(name);
            return p.getValue(obj);
        }
    }

    /**
     * <p>
     * 返回当前对象是否含有指定注解.
     * </p>
     * @param <A> 注解类型
     * @param annotationClass 注解类型
     * @return 是否含有指定注解
     */
    public <A extends Annotation> boolean hasAnnotation(Class<A> annotationClass) {
        return getAnnotation(annotationClass) != null ;
    }

    /**
     * <p>
     * 返回当前对象的指定类型注解.
     * </p>
     * @param <A> 注解类型
     * @param annotationClass 注解类型
     * @return 当前对象的指定类型注解
     */
    public <A extends Annotation> A getAnnotation(Class<A> annotationClass) {
        try {
            return (A) this.type.getAnnotation(annotationClass);
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * <p>
     * 返回当前对象的所有注解
     * </p>
     * @return 当前对象的所有注解
     */
    public Annotation[] getAnnotations() {
        return this.type.getAnnotations();
    }


    // ********************************************************************
    //    static method
    // ********************************************************************
    
    /**
     * 返回指定类型的描述
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
                bd = new MapBeanDescriptor<T>(type);
            } else {
                bd = new BeanDescriptor<T>(type);                
            }
            BEAN_DESCRIPTORS.put(type, bd);
        }
        return bd;
    }

    // ********************************************************************
    //    private method
    // ********************************************************************

//    private BeanProperty<?> getTargetBeanProperty(Object obj, String name) {
//        if (name.contains(DOT)) {
//            String currentPropertyName = name.substring(0, name.indexOf(DOT));
//            String innerPropertyName = name.substring(name.indexOf(DOT) + 1);
//            BeanProperty<?> property = getBeanProperty(currentPropertyName);
//            Object propertyValue = property.getValue(obj);
//            //中间层次为空，使用默认构造函数生成一个空对象
//            if (propertyValue == null) {
//                LOGGER.debug("类{}的属性[{}]为空，自动创建该属性对象[使用newInstance()]",
//                    new Object[]{property.getOwnerType().getName(), property.getName()});
//                try {
//                    propertyValue = property.getType().newInstance();
//                    property.setValue(obj, propertyValue);
//                } catch (Exception e) {
//                    throw new IllegalArgumentException(
//                            String.format("类%s缺少没有参数的构造函数", property.getType().getName())
//                            , e);
//                }
//            }
//            return getTargetBeanProperty(propertyValue, innerPropertyName);
//        } else {
//            BeanProperty<?> p = getBeanProperty(name);
//            return p;
//        }
//    }

    // ********************************************************************
    //    property
    // ********************************************************************

    /**
     * 类型
     */
    protected Class<T> type;

    /**
     * @return 返回type
     */
    public Class<T> getType() {
        return type;
    }
}
