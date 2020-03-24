
package cn.featherfly.common.bean;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.ClassUtils;
import cn.featherfly.common.lang.GenericType;

/**
 * <p>
 * java bean 的属性
 * </p>
 *
 * @param <T> 泛型
 * @author zhongj
 * @since 1.0
 * @version 1.0
 */
public class BeanProperty<T> implements GenericType<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(BeanProperty.class);

    private String name;

    // 属性类型，支持泛型类型自动探测
    private Class<T> type;

    private Method setter;

    private Method getter;

    private Field field;

    // 属性所在的类的类型
    private Class<?> ownerType;

    // 属性定义所在的类（可以被子类继承）

    private Class<?> declaringType;

    private Collection<Annotation> annotations;

    /**
     * @param propertyName  属性名称
     * @param field         存取数据的字段
     * @param propertyType  属性类型
     * @param setter        设置方法
     * @param getter        读取方法
     * @param ownerType     属性所在的类型
     * @param declaringType 定义属性的类型 （可能是ownerType的父类，也可能一样）
     */
    protected BeanProperty(String propertyName, Field field, Class<T> propertyType, Method setter, Method getter,
            Class<?> ownerType, Class<?> declaringType) {
        this.ownerType = ownerType;
        this.declaringType = declaringType;
        this.field = field;
        this.name = propertyName;
        this.type = propertyType;
        this.setter = setter;
        this.getter = getter;
        initAnnotation();
    }

    private void initAnnotation() {
        annotations = new HashSet<>();
        if (isWritable()) {
            for (Annotation a : setter.getAnnotations()) {
                annotations.add(a);
            }
        }
        if (isReadable()) {
            for (Annotation a : getter.getAnnotations()) {
                annotations.add(a);
            }
        }
        if (field != null) {
            for (Annotation a : field.getAnnotations()) {
                annotations.add(a);
            }
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
     * <p>
     * 设置属性
     * </p>
     *
     * @param obj   需要设置属性的对象
     * @param value 属性值
     */
    public void setValue(Object obj, Object value) {
        checkType(obj.getClass());
        if (isWritable()) {
            try {
                if (type == Optional.class) {
                    setter.invoke(obj, Optional.of(value));
                } else {
                    setter.invoke(obj, value);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            // throw new
            // PropertyAccessException(obj.getClass().getName()+"的对象的属性："+name+"不可写");
            LOGGER.warn("{}类型对象的属性：{} 不可写", new Object[] { obj.getClass().getName(), name });
            return;
        }
    }

    /**
     * <p>
     * 强制设置属性,使用field而非setter设置
     * </p>
     *
     * @param obj   设置属性的目标对象
     * @param value 属性值
     */
    public void setValueForce(Object obj, Object value) {
        if (isWritable()) {
            setValue(obj, value);
        } else if (field != null) {
            checkType(obj.getClass());
            try {
                field.setAccessible(true);
                field.set(obj, value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * <p>
     * 获取属性
     * </p>
     *
     * @param obj 获取属性的目标对象
     * @return 属性
     */
    public Object getValue(Object obj) {
        checkType(obj.getClass());
        if (isReadable()) {
            try {
                return getter.invoke(obj);
            } catch (Exception e) {
                throw new RuntimeException(e);
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
     * <p>
     * 强制获取属性，使用field而非getter获取
     * </p>
     *
     * @param obj 获取属性的目标对象
     * @return 属性
     */
    public Object getValueForce(Object obj) {
        if (isReadable()) {
            return getValue(obj);
        }
        if (field == null) {
            return null;
        }
        checkType(obj.getClass());
        try {
            field.setAccessible(true);
            return field.get(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <p>
     * 当前属性是否是可读属性，拥有getter
     * </p>
     *
     * @return 是否可读
     */
    public boolean isReadable() {
        return getter != null;
    }

    /**
     * <p>
     * 当前属性是否是可写属性，拥有setter
     * </p>
     *
     * @return 是否可写
     */
    public boolean isWritable() {
        return setter != null;
    }

    /**
     * <pre>
     * 如果属性是一个支持的对象，返回其泛型，如属性为类型java.util.Optional&lt;java.lang.String&gt;，则返回java.lang.String.
     * </pre>
     *
     * @return 泛型的定义类型
     */
    public Class<?> getGenericType() {
        if (field != null) {
            return ClassUtils.getFieldGenericType(field);
        } else if (getter != null) {
            return ClassUtils.getMethodGenericReturnType(getter);
        } else {
            return ClassUtils.getMethodGenericParameterType(setter);
        }
    }

    /**
     * <p>
     * 返回当前属性是否含有指定注解.
     * </p>
     *
     * @param <A>             注解类型
     * @param annotationClass 注解类型
     * @return 是否含有指定注解
     */
    public <A extends Annotation> boolean hasAnnotation(Class<A> annotationClass) {
        return getAnnotation(annotationClass) != null;
    }

    /**
     * <p>
     * 返回当前属性的指定类型注解.
     * </p>
     *
     * @param <A>             注解类型
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
     * <p>
     * 返回当前属性的所有注解
     * </p>
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
        return String.format("%s#%s[%s]", ownerType.getName(), getName(), type.getName());
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
     * 返回属性名称
     *
     * @return 返回name
     */
    public String getName() {
        return name;
    }

    /**
     * 返回属性类型
     *
     * @return 返回type
     */
    @Override
    public Class<T> getType() {
        return type;
    }

    /**
     * @return 返回field
     */
    public Field getField() {
        return field;
    }

    /**
     * 返回ownerType
     *
     * @return ownerType
     */
    public Class<?> getOwnerType() {
        return ownerType;
    }

    /**
     * 返回declaringType
     *
     * @return declaringType
     */
    public Class<?> getDeclaringType() {
        return declaringType;
    }

}
