
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
 * 动态创建指定类型指定属性对应的BeanProperty子类的工厂.<br/>
 * 优点：默认的反射BeanProperty比动态生成的设置慢一倍，读取慢几倍.<br/>
 * 缺点：生成大量类，占用内存空间.
 * </p>
 *
 * @author 钟冀
*
 */
public class JavassistBeanPropertyFactory implements BeanPropertyFactory {

	public JavassistBeanPropertyFactory() {
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(JavassistBeanPropertyFactory.class);

	/**
	 *<p>
	 * 动态创建指定类型指定属性对应的BeanProperty子类
	 * </p>
	 * @param type 类型
	 * @param field 存取数据的字段
	 * @param setMethod 设置方法
	 * @param getMethod 读取方法
	 * @return 动态创建的BeanProperty子类
	 */
	@Override
	public <T> BeanProperty<T> create(Class<?> type, Field field, Class<T> fieldType, Method setMethod, Method getMethod) {
		try {
			return create(type, field.getName(), field, fieldType, setMethod, getMethod);
		} catch (Exception e) {
			throw new NoSuchPropertyException(type, field.getName(), e);
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
    private <T> BeanProperty<T> create(Class<?> type, String name, Field field, Class<T> fieldType, Method setMethod, Method getMethod) {
		if (setMethod == null && getMethod == null) {
			throw new NoSuchPropertyException(type, name, "没有读取和写入方法");
		}

		try {
			ClassPool pool = ClassPool.getDefault();

			String upperCaseFirstName = WordUtils.upperCaseFirst(name);
			String propertyClassName = type.getPackage() + "._" + type.getSimpleName()
							+ upperCaseFirstName + "Property";
			CtClass beanPropertyClass = pool.makeClass(propertyClassName);
			beanPropertyClass.setSuperclass(pool.getCtClass(BeanProperty.class.getName()));

			CtConstructor constraConstructor = new CtConstructor(
					new CtClass[]{pool.getCtClass(Field.class.getName()),
						pool.getCtClass(Method.class.getName()),
						pool.getCtClass(Method.class.getName())
					}, beanPropertyClass);
			constraConstructor.setModifiers(Modifier.PUBLIC);
			constraConstructor.setBody("super(" + type.getName() + ".class, $1, $2, $3);");
			beanPropertyClass.addConstructor(constraConstructor);

			CtClass objectType = pool.get(Object.class.getName());

			String setterName = "set" + upperCaseFirstName;
			CtMethod setter = new CtMethod(CtClass.voidType, "setValue",
					new CtClass[] {objectType, objectType}, beanPropertyClass);
			String setterBody = null;
			if (setMethod == null) {
				setterBody = String.format("{"
						+ "throw new " + PropertyAccessException.class.getName()
						+ "(%s.class,\"%s\",\"属性不可写\");"
						+ "}", type.getName(), name);
			} else {
				setterBody = String.format("{"
						+ "%1$s value = (%1$s) $2;"
						+ "%2$s obj = (%2$s)$1;"
						+ "obj.%3$s(value);"
						+ "}", fieldType.getName(), type.getName(), setterName);
			}
			LOGGER.trace("类型{}属性{}的设置方法内容：{}", new Object[]{type.getName(), name, setterBody});
			setter.setBody(setterBody);
			setter.setModifiers(Modifier.PUBLIC);
			beanPropertyClass.addMethod(setter);

			String getterName = "get" + upperCaseFirstName;
			CtMethod getter = new CtMethod(objectType, "getValue",
					new CtClass[] {objectType}, beanPropertyClass);
			String getterBody = null;
			if (getMethod == null) {
				getterBody = String.format("{"
					+ "throw new " + PropertyAccessException.class.getName()
					+ "(%s.class,\"%s\",\"属性不可读\");"
					+ "}", type.getName(), name);
			} else {
				getterBody = String.format("{"
						+ "%1$s obj = (%1$s)$1;"
						+ "return obj.%2$s();"
						+ "}", type.getName(), getterName);
			}


			LOGGER.trace("类型{}属性{}的读取方法内容：{}", new Object[]{type.getName(), name, getterBody});
			getter.setBody(getterBody);
			getter.setModifiers(Modifier.PUBLIC);
			beanPropertyClass.addMethod(getter);

			beanPropertyClass.toClass();
			beanPropertyClass.detach();
			return (BeanProperty<T>) Class.forName(propertyClassName)
					.getConstructor(Field.class, Method.class, Method.class)
					.newInstance(field, setMethod, getMethod);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
