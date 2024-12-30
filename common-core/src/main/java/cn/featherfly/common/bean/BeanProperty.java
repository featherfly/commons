
package cn.featherfly.common.bean;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.exception.ReflectException;
import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.ClassUtils;
import cn.featherfly.common.lang.CollectionUtils;
import cn.featherfly.common.lang.Str;
import cn.featherfly.common.lang.reflect.Type;

/**
 * java bean 的属性.
 *
 * @author zhongj
 * @version 1.0
 * @param <T> the bean generic type
 * @param <V> the property generic type
 * @since 1.0
 */
public class BeanProperty<T, V> implements Type<V>, Property<T, V> {

    private static final Logger LOGGER = LoggerFactory.getLogger(BeanProperty.class);

    private final int index;

    private final String name;

    // 属性类型，支持泛型类型自动探测
    private final Class<V> type;

    //    private Class<?>[] genericTypes;

    private final Method setter;

    private final Method getter;

    private final Field field;

    // 属性所在的类的类型
    private final Class<T> ownerType;

    // 属性定义所在的类（可以被子类继承）

    private Class<?> declaringType;

    private Collection<Annotation> annotations;

    /**
     * Instantiates a new bean property.
     *
     * @param index the index
     * @param propertyName 属性名称
     * @param field 存取数据的字段
     * @param propertyType 属性类型
     * @param setter 设置方法
     * @param getter 读取方法
     * @param ownerType 属性所在的类型
     * @param declaringType 定义属性的类型 （可能是ownerType的父类，也可能一样）
     */
    protected BeanProperty(int index, String propertyName, Field field, Class<V> propertyType, Method setter,
        Method getter, Class<T> ownerType, Class<?> declaringType) {
        this.ownerType = ownerType;
        this.declaringType = declaringType;
        this.field = field;
        name = propertyName;
        this.index = index;
        type = propertyType;
        this.setter = setter;
        this.getter = getter;
        initAnnotation();
    }

    //    private void initGenericType() {
    //        if (field != null) {
    //            genericTypes = ClassUtils.getFieldGenericParameterTypes(ownerType, field).toArray(new Class<?>[] {});
    //            //            return ClassUtils.getFieldGenericType(ownerType, field);
    //        } else if (getter != null) {
    //            genericTypes = ClassUtils.getMethodReturnTypeGenericParameterType(ownerType, getter);
    //        } else {
    //            return ClassUtils.getMethodGenericParameterType(ownerType, setter);
    //        }
    //    }

    private void initAnnotation() {
        annotations = new HashSet<>();
        if (isWritable()) {
            CollectionUtils.addAll(annotations, setter.getAnnotations());
        }
        if (isReadable()) {
            CollectionUtils.addAll(annotations, getter.getAnnotations());
        }
        if (field != null) {
            CollectionUtils.addAll(annotations, field.getAnnotations());
        }
    }
    // /**
    // * @param ownerType 当前类类型
    // * @param extendsFrom 定义属性的类型
    // * @param field 成员变量
    // * @param setter 读取方法
    // * @param getter 写入方法
    // */
    // protected BeanProperty(Class<?> ownerType, Class<?> extendsFrom, Field
    // field, Method setter, Method getter) {
    // this.ownerType = ownerType;
    // this.extendsFrom = extendsFrom;
    // this.field = field;
    // this.name = field.getName();
    // this.type = field.getType();
    // this.setter = setter;
    // this.getter = getter;
    // }

    /**
     * 设置属性 .
     *
     * @param obj 需要设置属性的对象
     * @param value 属性值
     */
    public void setValue(Object obj, Object value) {
        checkType(obj.getClass());
        if (isWritable()) {
            try {
                if (type == Optional.class) {
                    setter.invoke(obj, Optional.ofNullable(value));
                } else {
                    setter.invoke(obj, value);
                }
            } catch (Exception e) {
                throw new ReflectException(Str.format("set {0}.{1} error", ownerType.getName(), name), e);
            }
        } else {
            // throw new
            // PropertyAccessException(obj.getClass().getName()+"的对象的属性："+name+"不可写");
            LOGGER.warn("{}类型对象的属性：{} 不可写", new Object[] { obj.getClass().getName(), name });
            return;
        }
    }

    /**
     * 强制设置属性,使用field而非setter设置 .
     *
     * @param obj 设置属性的目标对象
     * @param value 属性值
     */
    public void setValueForce(Object obj, Object value) {
        if (isWritable()) {
            setValue(obj, value);
        } else if (field != null) {
            checkType(obj.getClass());
            try {
                field.setAccessible(true);
                if (type == Optional.class) {
                    field.set(obj, Optional.ofNullable(value));
                } else {
                    field.set(obj, value);
                }
            } catch (Exception e) {
                throw new ReflectException(Str.format("set {0}.{1} force error", ownerType.getName(), name), e);
            }
        }
    }

    /**
     * 获取属性 .
     *
     * @param obj 获取属性的目标对象
     * @return 属性
     */
    @SuppressWarnings("unchecked")
    public V getValue(Object obj) {
        checkType(obj.getClass());
        if (isReadable()) {
            try {
                return (V) getter.invoke(obj);
            } catch (Exception e) {
                throw new ReflectException(Str.format("get {0}.{1} error", ownerType.getName(), name), e);
            }
        } else {
            // YUFEI_TODO 如果不可读，返回NULL，和可读正常返回NULL会有混淆，具体处理策略再斟酌
            // throw new
            // PropertyAccessException(obj.getClass().getName()+"的对象的属性："+name+"不可读");
            LOGGER.warn("{}类型对象的属性：{}不可读", obj.getClass().getName(), name);
            return null;
        }
    }

    /**
     * 强制获取属性，使用field而非getter获取 .
     *
     * @param obj 获取属性的目标对象
     * @return 属性
     */
    @SuppressWarnings("unchecked")
    public V getValueForce(Object obj) {
        if (isReadable()) {
            return getValue(obj);
        }
        if (field == null) {
            return null;
        }
        checkType(obj.getClass());
        try {
            field.setAccessible(true);
            return (V) field.get(obj);
        } catch (Exception e) {
            throw new ReflectException(Str.format("get {0}.{1} force error", ownerType.getName(), name), e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isReadable() {
        return getter != null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isWritable() {
        return setter != null;
    }

    /**
     * <pre>
     * 如果属性是一个支持泛型的对象，返回其泛型，如属性为类型java.util.Optional&lt;java.lang.String&gt;，则返回java.lang.String.
     * </pre>
     *
     * @return 泛型的定义类型
     */
    public Class<?> getGenericType() {
        if (field != null) {
            return ClassUtils.getFieldGenericParameterType(ownerType, field);
        } else if (getter != null) {
            return ClassUtils.getMethodReturnTypeGenericParameterType(ownerType, getter);
        } else {
            return ClassUtils.getMethodGenericParameterType(ownerType, setter);
        }
    }

    /**
     * <pre>
     * 如果属性是一个支持泛型的对象，返回其所有泛型列表.
     * 如属性为类型java.util.Map&lt;java.lang.String, java.lang.Integer&gt;，则返回[java.lang.String,java.lang.Integer].
     * </pre>
     *
     * @return 泛型的定义类型
     */
    public List<Class<?>> getGenericTypes() {
        if (field != null) {
            return ClassUtils.getFieldGenericParameterTypes(ownerType, field);
        } else if (getter != null) {
            return ClassUtils.getMethodReturnTypeGenericParameterTypes(ownerType, getter);
        } else {
            return ClassUtils.getMethodGenericParameterTypes(ownerType, setter);
        }
    }

    /**
     * 返回当前属性是否含有指定注解.
     *
     * @param <A> 注解类型
     * @param annotationClass 注解类型
     * @return 是否含有指定注解
     */
    public <A extends Annotation> boolean hasAnnotation(Class<A> annotationClass) {
        return getAnnotation(annotationClass) != null;
    }

    /**
     * 返回当前属性的指定类型注解.
     *
     * @param <A> 注解类型
     * @param annotationClass 注解类型
     * @return 注解
     */
    public <A extends Annotation> A getAnnotation(Class<A> annotationClass) {
        if (annotationClass == null) {
            return null;
        }
        for (Annotation annotation : annotations) {
            if (annotation.annotationType().equals(annotationClass)) {
                @SuppressWarnings("unchecked")
                A a = (A) annotation;
                return a;
            }
        }
        return null;
        // A a = null;
        // if (this.field != null) {
        // a = this.field.getAnnotation(annotationClass);
        // }
        // if (a != null) {
        // LOGGER.debug("在类成员变量[{}]中找到传入注解[{}]", this.field.getName(),
        // annotationClass.getName());
        // return a;
        // }
        //
        // if (isReadable()) {
        // a = this.getter.getAnnotation(annotationClass);
        // if (a != null) {
        // LOGGER.debug("在类方法[{}]中找到传入注解[{}]", this.getter.getName(),
        // annotationClass.getName());
        // return a;
        // }
        // }
        // if (isWritable()) {
        // a = this.setter.getAnnotation(annotationClass);
        // if (a != null) {
        // LOGGER.debug("在类方法[{}]中找到传入注解[{}]", this.setter.getName(),
        // annotationClass.getName());
        // return a;
        // }
        // }
        // return null;
        // return this.field.getAnnotation(annotationClass);
    }

    /**
     * 返回当前属性的所有注解 .
     *
     * @return 当前属性的所有注解
     */
    public Annotation[] getAnnotations() {
        // return this.field.getAnnotations();
        return annotations.toArray(new Annotation[] {});
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("%s[%d].%s{%s}", ownerType.getName(), getIndex(), getName(), type.getName());
    }

    // 检查类型
    private void checkType(Class<?> objClass) {
        // if (!ClassUtils.isParent(clazz, objClass)) {
        // throw new IllegalArgumentException(
        // String.format("传入对象类型[%s]不是当前对象[%s]的子类"
        // , objClass.getName()
        // , clazz.getName()));
        // }
        AssertIllegalArgument.isParent(ownerType, objClass);
        //        if (objClass.isInstance(ownerType)) {
        //            throw new IllegalArgumentException(
        //                    String.format("传入对象类型[%s]不是当前属性所属对象[%s]类型或子类型", objClass.getName(), ownerType.getName()));
        //        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<T> getInstanceType() {
        return getOwnerType();
    }

    /**
     * 返回属性名称.
     *
     * @return 返回name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * 返回属性类型.
     *
     * @return 返回type
     */
    @Override
    public Class<V> getType() {
        return type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTypeName() {
        return type.getName();
    }

    /**
     * Gets the field.
     *
     * @return 返回field
     */
    public Field getField() {
        return field;
    }

    /**
     * 返回ownerType.
     *
     * @return ownerType
     */
    public Class<T> getOwnerType() {
        return ownerType;
    }

    /**
     * 返回declaringType.
     *
     * @return declaringType
     */
    public Class<?> getDeclaringType() {
        return declaringType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        if (field != null) {
            return ownerType.hashCode() + field.hashCode();
        } else if (getter != null) {
            return ownerType.hashCode() + getter.hashCode();
        } else {
            return ownerType.hashCode() + setter.hashCode();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != BeanProperty.class) {
            return false;
        }
        BeanProperty<?, ?> target = (BeanProperty<?, ?>) obj;
        if (!ownerType.equals(target.ownerType)) {
            return false;
        }
        if (field != null) {
            return field.equals(target.field);
        } else if (getter != null) {
            return getter.equals(target.getter);
        } else {
            return setter.equals(target.setter);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getIndex() {
        return index;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(T object, V value) {
        setValue(object, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V get(T object) {
        return getValue(object);
    }

    /**
     * get setter value
     *
     * @return setter
     */
    public Method getSetter() {
        return setter;
    }

    /**
     * get getter value
     *
     * @return getter
     */
    public Method getGetter() {
        return getter;
    }

    @Override
    public PropertyAccessor<V> getPropertyAccessor() {
        if (type.getPackage().getName().startsWith("java.lang")) { // ignore java lib
            return null;
        }
        return BeanDescriptor.getBeanDescriptor(type);
    }
}
