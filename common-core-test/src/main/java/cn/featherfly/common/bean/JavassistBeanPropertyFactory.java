
package cn.featherfly.common.bean;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.lang.ClassUtils;
import cn.featherfly.common.lang.WordUtils;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtMethod;
import javassist.Modifier;

/**
 * <p>
 * 动态创建指定类型指定属性对应的BeanProperty子类的工厂. 优点：默认的反射BeanProperty比动态生成的设置慢一倍，读取慢几倍.
 * 缺点：生成大量类，占用内存空间.
 * </p>
 *
 * @author zhongj
 */
public class JavassistBeanPropertyFactory implements BeanPropertyFactory {

    public JavassistBeanPropertyFactory() {
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(JavassistBeanPropertyFactory.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> BeanProperty<T> create(String propertyName, Field field, Class<T> propertyType, Method setMethod,
            Method getMethod, Class<?> ownerType, Class<?> declaringType) {
        try {
            return create(ownerType, propertyName, field, propertyType, setMethod, getMethod, declaringType);
        } catch (Exception e) {
            throw new NoSuchPropertyException(ownerType, field.getName(), e);
        }
    }

    //	/**
    //	 * <p>
    //	 * 动态创建指定类型指定属性对应的BeanProperty子类
    //	 * </p>
    //	 * @param type 类型
    //	 * @param propertyName 属性名
    //	 * @return 动态创建的BeanProperty子类
    //	 */
    //	@Override
    //	public <T> BeanProperty<T> create(Class<T> type, String propertyName) {
    //		Field field;
    //		try {
    //			field = type.getDeclaredField(propertyName);
    //			Method getter = ClassUtils.getGetter(field, type);
    //			Method setter = ClassUtils.getSetter(field, type);
    //			return create(type, propertyName, field, setter, getter);
    //		} catch (Exception e) {
    //			throw new NoSuchPropertyException(type, propertyName, e);
    //		}
    //	}

    /*
     * <p>
     * 创建BeanProperty
     * </p>
     * @param type 属性所在的类型
     * @param name 属性名称
     * @param field 属性字段
     * @param setMethod 属性设置方法
     * @param getMethod 属性读取方法
     * @return BeanProperty
     */
    @SuppressWarnings("unchecked")
    private <T> BeanProperty<T> create(Class<?> ownerType, String name, Field field, Class<T> propertyType,
            Method setMethod, Method getMethod, Class<?> declaringType) {
        if (setMethod == null && getMethod == null) {
            throw new NoSuchPropertyException(ownerType, name);
        }
        try {
            ClassPool pool = ClassPool.getDefault();

            String upperCaseFirstName = WordUtils.upperCaseFirst(name);
            String propertyClassName = ownerType.getPackage() + "._" + ownerType.getSimpleName() + upperCaseFirstName
                    + "Property";
            CtClass beanPropertyClass = pool.makeClass(propertyClassName);
            beanPropertyClass.setSuperclass(pool.getCtClass(BeanProperty.class.getName()));

            CtConstructor constraConstructor = new CtConstructor(
                    new CtClass[] { pool.getCtClass(String.class.getName()), pool.getCtClass(Field.class.getName()),
                            pool.getCtClass(Class.class.getName()), pool.getCtClass(Method.class.getName()),
                            pool.getCtClass(Method.class.getName()), pool.getCtClass(Class.class.getName()),
                            pool.getCtClass(Class.class.getName()) },
                    beanPropertyClass);
            constraConstructor.setModifiers(Modifier.PUBLIC);
            constraConstructor.setBody("super($1, $2, $3, $4, $5, $6, $7);");
            beanPropertyClass.addConstructor(constraConstructor);

            CtClass objectType = pool.get(Object.class.getName());

            String setterName = "set" + upperCaseFirstName;
            CtMethod setter = new CtMethod(CtClass.voidType, "setValue", new CtClass[] { objectType, objectType },
                    beanPropertyClass);
            String setterBody = null;
            if (setMethod == null) {
                setterBody = String.format("{" + "throw new " + PropertyAccessException.class.getName()
                        + "(%s.class,\"%s\",\"属性不可写\");" + "}", ownerType.getName(), name);
            } else {
                if (propertyType.isPrimitive()) {
                    String valueMethodName = ClassUtils.getPrimitiveClassValueMethodName(propertyType);
                    setterBody = String.format("{%1$s value = (%1$s) $2;%2$s obj = (%2$s)$1; obj.%3$s(value.%4$s()); }",
                            ClassUtils.getPrimitiveWrapped(propertyType).getName(), ownerType.getName(), setterName,
                            valueMethodName);
                } else {
                    setterBody = String.format("{%1$s value = (%1$s) $2;%2$s obj = (%2$s)$1;obj.%3$s(value);}",
                            propertyType.getName(), ownerType.getName(), setterName);
                }
            }
            LOGGER.trace("类型{}属性{}的设置方法内容：{}", new Object[] { ownerType.getName(), name, setterBody });
            setter.setBody(setterBody);
            setter.setModifiers(Modifier.PUBLIC);
            beanPropertyClass.addMethod(setter);

            String getterName = "get" + upperCaseFirstName;
            CtMethod getter = new CtMethod(objectType, "getValue", new CtClass[] { objectType }, beanPropertyClass);
            String getterBody = null;
            if (getMethod == null) {
                getterBody = String.format("{" + "throw new " + PropertyAccessException.class.getName()
                        + "(%s.class,\"%s\",\"属性不可读\");" + "}", ownerType.getName(), name);
            } else {
                if (propertyType.isPrimitive()) {
                    getterBody = String.format("{%1$s obj = (%1$s)$1;return %2$s.valueOf(obj.%3$s());}",
                            ownerType.getName(), ClassUtils.getPrimitiveWrapped(propertyType).getName(), getterName);
                } else {
                    getterBody = String.format("{" + "%1$s obj = (%1$s)$1;" + "return obj.%2$s();" + "}",
                            ownerType.getName(), getterName);
                }
            }

            LOGGER.trace("类型{}属性{}的读取方法内容：{}", new Object[] { ownerType.getName(), name, getterBody });
            getter.setBody(getterBody);
            getter.setModifiers(Modifier.PUBLIC);
            beanPropertyClass.addMethod(getter);

            beanPropertyClass.toClass();
            beanPropertyClass.detach();
            return (BeanProperty<T>) Class.forName(propertyClassName)
                    .getConstructor(String.class, Field.class, Class.class, Method.class, Method.class, Class.class,
                            Class.class)
                    .newInstance(name, field, propertyType, setMethod, getMethod, ownerType, declaringType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
