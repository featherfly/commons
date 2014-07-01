
/**
 * @author 钟冀 - yufei
 *		 	Jan 5, 2009
 */
package cn.featherfly.common.lang;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * <p>
 * class处理的工具类
 * </p>
 * @author 钟冀
 * @since 1.0
 * @version 1.0
 */
public final class ClassUtils {

	private ClassUtils() {
	}

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ClassUtils.class);

	private static final String DOT = ".";

	private static final String SET = "set";
	private static final String GET = "get";
	private static final String IS = "is";

	/**
	 * 判断第一个参数是否实现了第二个参数代表的接口类型
	 * @deprecated 使用isParent
	 * @param classType 类型（包括类和接口）
	 * @param interfaceType 接口类型
	 * @return 判断第一个参数是否实现了第二个参数代表的接口类型
	 */
	@Deprecated
	public static boolean isImplementation(Class<?> classType, Class<?> interfaceType) {
//		Class<?>[] classes = classType.getInterfaces();
//		if(classes!=null){
//			for (Class<?> c : classes) {
//				if(c == interfaceType){
//					return true;
//				}
//			}
//		}
//		return false;
		return interfaceType.isAssignableFrom(classType);
	}

	/**
	 * <p>
	 * 查找指定类型
	 * </p>
	 * @param className 类名
	 * @return 指定类型
	 */
	public static Class<?> forName(String className) {
		try {
			return Class.forName(className);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 判断第一个参数是否是第二个参数的父类（父接口）
	 * @param parentType 父类型（包括类和接口）
	 * @param childType 子类型（包括类和接口）
	 * @return 第一个参数是否是第二个参数的父类（父接口）
	 */
	public static boolean isParent(Class<?> parentType, Class<?> childType) {
		return parentType.isAssignableFrom(childType);
	}

	/**
	 * 返回目标类型的指定名称的字段，支持多层嵌套
	 * 例如, user.address.no
	 * @param target 目标类型
	 * @param fieldName 字段名
	 * @throws NoSuchFieldException 没有找到传入字段时抛出
	 * @return 目标类型的指定名称的字段，支持多层嵌套
	 */
	public static Field getField(Class<?> target, String fieldName)
		throws NoSuchFieldException {
		if (fieldName.contains(DOT)) {
			String currentFieldName = fieldName.substring(0, fieldName.indexOf(DOT));
			String innerFieldName = fieldName.substring(fieldName.indexOf(DOT) + 1);
			return getField(target.getDeclaredField(currentFieldName).getType()
					, innerFieldName);
		} else {
			return target.getDeclaredField(fieldName);
		}
	}

	/**
	 * 返回指定类型(objectType)被指定注解(annotationType)标注的所有字段【成员变量】(field)
	 * 会把父类里有被标注的字段也进行返回
	 * @param <A> 反省
	 * @param objectType 对象类型
	 * @param annotationType 注解类型
	 * @return 指定类型被指定注解标注的所有字段
	 */
	public static <A extends Annotation> List<Field> getAnnotatedFields(Class<?> objectType,
			Class<A> annotationType) {
		return getAnnotatedFields(objectType, annotationType, true);
	}
	/**
	 * 返回指定类型(objectType)被指定注解(annotationType)标注的所有字段【成员变量】(field)
	 * fromSuper 为 true 时会把父类里有被标注的字段也进行返回，为false则只返回本类的
	 * @param <A> 反省
	 * @param objectType 对象类型
	 * @param annotationType 注解类型
	 * @param fromSuper 是否从父类型查找
	 * @return 指定类型被指定注解标注的所有字段
	 */
	public static <A extends Annotation> List<Field> getAnnotatedFields(Class<?> objectType,
			Class<A> annotationType, boolean fromSuper) {
		List<Field> fieldList = new ArrayList<Field>();
		getAnnotatedFields(objectType, fieldList, annotationType, fromSuper);
		return fieldList;
	}

	private static <A extends Annotation> void getAnnotatedFields(Class<?> objectType,
			List<Field> fieldList, Class<A> annotationType, boolean fromSuper) {
		if (objectType != null && objectType != Object.class) {
			Field[] fields = objectType.getDeclaredFields();
			for (Field field : fields) {
				if (field.getAnnotation(annotationType) != null) {
					fieldList.add(field);
				}
			}
			if (fromSuper) {
				getAnnotatedFields(objectType.getSuperclass(), fieldList, annotationType, fromSuper);
			}
		}
	}

	/**
	 * 返回指定类型(objectType)被指定注解(annotationType)标注的公共(public)方法(method)
	 * @param <A> 注解类型
	 * @param objectType 目标类型
	 * @param annotationType 注解类型
	 * @return 指定注解标注的公共方法
	 */
	public static <A extends Annotation> List<Method> getAnnotatedMethods(Class<?> objectType,
			Class<A> annotationType) {
		List<Method> methodList = new ArrayList<Method>();
		Method[] methods = objectType.getMethods();
		for (Method method : methods) {
			if (method.getAnnotation(annotationType) != null) {
				methodList.add(method);
			}
		}
		return methodList;
	}
	/**
	 * 返回访问方法的属性名.
	 * 如果不是setter或getter方法，返回null
	 * @param method 方法
	 * @return 访问方法的属性名
	 */
	public static String getPropertyName(Method method) {
		String name = null;
		final int startIndex = 3;
		if (isSetter(method)) {
			name = method.getName().substring(startIndex);
		}
		if (isGetter(method)) {
			name = method.getName();
			if (name.startsWith(IS)) {
				name = name.substring(2);
			} else if (name.startsWith(GET)) {
				name = name.substring(startIndex);
			}
		}
		if (name != null) {
			name = name.substring(0, 1).toLowerCase() + name.substring(1);
		}
		return name;
	}
	/**
	 * 是否是set方法(以set开头)
	 * @param method 方法
	 * @return 是否是set方法
	 */
	public static boolean isSetter(Method method) {
		String set = SET;
		String name = method.getName();
		return (name.startsWith(set) && !set.equals(name));
	}

	/**
	 * 是否是get方法（以get或is开头）
	 * @param method 方法
	 * @return 是否是get方法
	 */
	public static boolean isGetter(Method method) {
		String get = GET;
		String is = IS;
		String name = method.getName();
		if (name.startsWith(get) && !get.equals(name)) {
			return true;
		}
		if (name.startsWith(is) && !is.equals(name)) {
			return true;
		}
		return false;
	}
	/**
	 * 返回getter方法，包括getXxx和isXxx
	 * 没有找到返回null
	 * @param field 成员变量
	 * @param type 类型
	 * @return getter方法
	 */
	public static Method getGetter(Field field, Class<?> type) {
		Method method = null;
		String fieldName = field.getName();
		String get = GET + WordUtils.upperCaseFirst(
				fieldName);
		try {
			method = type.getMethod(get, new Class[]{});
		} catch (Exception e) {
			LOGGER.trace("没有找到get{}方法, 使用is{}查找", field.getName(), field.getName());
			try {
				String is = IS + WordUtils.upperCaseFirst(
						fieldName);
				method = type.getMethod(is, new Class[]{});
			} catch (Exception e1) {
				LOGGER.trace("没有找到get{}和is{}方法", field.getName(), field.getName());
			}
		}
		return method;
	}
	/**
	 * 返回setter方法
	 * 没有找到返回null
	 * @param field 成员变量
	 * @param type 类型
	 * @return 返回setter方法
	 */
	public static Method getSetter(Field field, Class<?> type) {
		Method method = null;
		String fieldName = field.getName();
		String set = SET + WordUtils.upperCaseFirst(fieldName);
		try {
			method = type.getMethod(set, field.getType());
		} catch (Exception e) {
			LOGGER.trace("没有找到{}的set方法", field.getName(), field.getName());
		}
		return method;
	}

	/**
	 * 是否是基础类型，包括基本类型，基本类型包装类，
	 * String,StringBuffer,StringBuilder
	 * AtomicInteger,AtomicLong,BigDecimal,BigInteger
	 * java.util.Date
	 * java.sql.Date,java.sql.Time,java.sql.Timestamp
	 * 注意：不包括这些类的子类
	 * @param type 类型
	 * @return 是否是基础类型
	 */
	public static boolean isBasicType(Class<?> type) {
		boolean isBasic = false;
		if (type.isPrimitive()) {
			isBasic = true;
		} else if (type == Byte.class) {
			isBasic = true;
		} else if (type == Character.class) {
			isBasic = true;
		} else if (type == Short.class) {
			isBasic = true;
		} else if (type == Integer.class) {
			isBasic = true;
		} else if (type == Long.class) {
			isBasic = true;
		} else if (type == Float.class) {
			isBasic = true;
		} else if (type == Double.class) {
			isBasic = true;
		} else if (type == Boolean.class) {
			isBasic = true;
		} else if (type == Integer.class) {
			isBasic = true;
		} else if (type == Integer.class) {
			isBasic = true;
		} else if (type == String.class) {
			isBasic = true;
		} else if (type == StringBuffer.class) {
			isBasic = true;
		} else if (type == StringBuilder.class) {
			isBasic = true;
		} else if (type == AtomicInteger.class) {
			isBasic = true;
		} else if (type == AtomicLong.class) {
			isBasic = true;
		} else if (type == BigDecimal.class) {
			isBasic = true;
		} else if (type == BigInteger.class) {
			isBasic = true;
		} else if (type == Date.class) {
			isBasic = true;
		} else if (type == java.sql.Date.class) {
			isBasic = true;
		} else if (type == java.sql.Time.class) {
			isBasic = true;
		} else if (type == java.sql.Timestamp.class) {
			isBasic = true;
		}
		return isBasic;
	}
	/**
	 * 是否是cellection接口,及其子接口或实现类
	 * @param type 类型
	 * @return 是否是cellection接口,及其子接口或实现类
	 */
	public static boolean isCellection(Class<?> type) {
		return isParent(Collection.class, type);
	}
	/**
	 * 是否是map接口，及其子接口或实现类
	 * @param type 类型
	 * @return 是否是map接口，及其子接口或实现类
	 */
	public static boolean isMap(Class<?> type) {
		return isParent(Map.class, type);
	}

	/**
	 * <p>
	 * Class泛型参数强制转型
	 * </p>
	 * @param <T>
	 * @param type
	 * @param castToType
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> Class<T> castGenericType(Class<?> type , T castToType) {
		return (Class<T>) type;
	}

	/**
	 * <p>
	 * 获取指定类所在的JAR包的JAR文件.如果不是JAR文件中的返回null
	 * </p>
	 * @param type 类型
	 * @return JAR文件或null
	 */
	public static File getJar(Class<?> type) {
		return getJar(type.getName());
	}
	/**
	 * <p>
	 * 获取指定类所在的JAR包的JAR文件.如果不是JAR文件中的返回null
	 * </p>
	 * @param type 类型
	 * @return JAR文件或null
	 */
	public static File getJar(String className) {
		if (LangUtils.isEmpty(className)) {
			return null;
		}
		URL url = Thread.currentThread().getContextClassLoader().getResource(
				packageToDir(className) + ".class"
				);
		if (url != null && "jar".equalsIgnoreCase(url.getProtocol())) {
			String excalmatoryMark = "!";
			String filePrefix = "file:";
			String path = url.getPath();
			url.getProtocol();
			if(path.startsWith(filePrefix)){
				String os = System.getProperty("os.name");
				if (os.startsWith("Windows")) {
					path = StringUtils.substringBetween(path, "file:/", excalmatoryMark);
				} else if (os.startsWith("Linux")) {
					path = StringUtils.substringBetween(path, filePrefix, excalmatoryMark);
				} else {
					path = StringUtils.substringBetween(path, filePrefix, excalmatoryMark);
				}
			}else{
				path = StringUtils.substringBefore(path, excalmatoryMark);
			}
			return new File(path);
		}
		return null;
	}

	/**
	 * <p>
	 * 转换包模式为目录模式.xx.yy.Ttt -> xx/yy/Ttt
	 * </p>
	 * @param type 类型
	 * @return 目录模式
	 */
	public static String packageToDir(Class<?> type) {
		if (type == null) {
			return null;
		}
		return packageToDir(type.getPackage().getName());
	}
	/**
	 * <p>
	 * 转换包模式为目录模式.xx.yy.Ttt -> xx/yy/Ttt
	 * </p>
	 * @param className 类型名称
	 * @return 目录模式
	 */
	public static String packageToDir(String className) {
		if (LangUtils.isEmpty(className)) {
			return null;
		}
		return className.replace(".", "/");
	}

	/**
	 * 通过反射,获得指定类的父类的泛型参数的实际类型. 如BuyerServiceBean extends DaoSupport<Buyer>
	 *
	 * @param clazz
	 *            clazz 需要反射的类,该类必须继承范型父类
	 * @param index
	 *            泛型参数所在索引,从0开始.
	 * @return 范型参数的实际类型, 如果没有实现ParameterizedType接口，即不支持泛型，所以直接返回
	 *         <code>Object.class</code>
	 */
	public static Class<?> getSuperClassGenricType(Class<?> clazz, int index) {
		Type genType = clazz.getGenericSuperclass();// 得到泛型父类
		// 如果没有实现ParameterizedType接口，即不支持泛型，直接返回Object.class
		if (!(genType instanceof ParameterizedType)) {
			return Object.class;
		}
		// 返回表示此类型实际类型参数的Type对象的数组,数组里放的都是对应类型的Class, 如BuyerServiceBean extends
		// DaoSupport<Buyer,Contact>就返回Buyer和Contact类型
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		if (index >= params.length || index < 0) {
			throw new RuntimeException("你输入的索引"
					+ (index < 0 ? "不能小于0" : "超出了参数的总数"));
		}
		if (!(params[index] instanceof Class)) {
			return Object.class;
		}
		return (Class<?>) params[index];
	}

	/**
	 * 通过反射,获得指定类的父类的第一个泛型参数的实际类型. 如BuyerServiceBean extends DaoSupport<Buyer>
	 *
	 * @param clazz
	 *            clazz 需要反射的类,该类必须继承泛型父类
	 * @return 泛型参数的实际类型, 如果没有实现ParameterizedType接口，即不支持泛型，所以直接返回
	 *         <code>Object.class</code>
	 */
	public static Class<?> getSuperClassGenricType(Class<?> clazz) {
		return getSuperClassGenricType(clazz, 0);
	}

	/**
	 * 通过反射,获得方法返回值泛型参数的实际类型. 如: public Map<String, Buyer> getNames(){}
	 *
	 * @param Method
	 *            method 方法
	 * @param int index 泛型参数所在索引,从0开始.
	 * @return 泛型参数的实际类型, 如果没有实现ParameterizedType接口，即不支持泛型，所以直接返回
	 *         <code>Object.class</code>
	 */
	public static Class<?> getMethodGenericReturnType(Method method, int index) {
		Type returnType = method.getGenericReturnType();
		if (returnType instanceof ParameterizedType) {
			ParameterizedType type = (ParameterizedType) returnType;
			Type[] typeArguments = type.getActualTypeArguments();
			if (index >= typeArguments.length || index < 0) {
				throw new RuntimeException("你输入的索引"
						+ (index < 0 ? "不能小于0" : "超出了参数的总数"));
			}
			return (Class<?>) typeArguments[index];
		}
		return Object.class;
	}

	/**
	 * 通过反射,获得方法返回值第一个泛型参数的实际类型. 如: public Map<String, Buyer> getNames(){}
	 *
	 * @param Method
	 *            method 方法
	 * @return 泛型参数的实际类型, 如果没有实现ParameterizedType接口，即不支持泛型，所以直接返回
	 *         <code>Object.class</code>
	 */
	public static Class<?> getMethodGenericReturnType(Method method) {
		return getMethodGenericReturnType(method, 0);
	}

	/**
	 * 通过反射,获得方法输入参数第index个输入参数的所有泛型参数的实际类型. 如: public void add(Map<String,
	 * Buyer> maps, List<String> names){}
	 *
	 * @param Method
	 *            method 方法
	 * @param int index 第几个输入参数
	 * @return 输入参数的泛型参数的实际类型集合, 如果没有实现ParameterizedType接口，即不支持泛型，所以直接返回空集合
	 */
	public static List<Class<?>> getMethodGenericParameterTypes(Method method,
			int index) {
		List<Class<?>> results = new ArrayList<Class<?>>();
		Type[] genericParameterTypes = method.getGenericParameterTypes();
		if (index >= genericParameterTypes.length || index < 0) {
			throw new RuntimeException("你输入的索引"
					+ (index < 0 ? "不能小于0" : "超出了参数的总数"));
		}
		Type genericParameterType = genericParameterTypes[index];
		if (genericParameterType instanceof ParameterizedType) {
			ParameterizedType aType = (ParameterizedType) genericParameterType;
			Type[] parameterArgTypes = aType.getActualTypeArguments();
			for (Type parameterArgType : parameterArgTypes) {
				Class<?> parameterArgClass = (Class<?>) parameterArgType;
				results.add(parameterArgClass);
			}
			return results;
		}
		return results;
	}

	/**
	 * 通过反射,获得方法输入参数第一个输入参数的所有泛型参数的实际类型. 如: public void add(Map<String, Buyer>
	 * maps, List<String> names){}
	 *
	 * @param Method
	 *            method 方法
	 * @return 输入参数的泛型参数的实际类型集合, 如果没有实现ParameterizedType接口，即不支持泛型，所以直接返回空集合
	 */
	public static List<Class<?>> getMethodGenericParameterTypes(Method method) {
		return getMethodGenericParameterTypes(method, 0);
	}

	/**
	 * 通过反射,获得Field泛型参数的实际类型. 如: public Map<String, Buyer> names;
	 *
	 * @param Field
	 *            field 字段
	 * @param int index 泛型参数所在索引,从0开始.
	 * @return 泛型参数的实际类型, 如果没有实现ParameterizedType接口，即不支持泛型，直接返回<code>Object.class</code>
	 */
	public static Class<?> getFieldGenericType(Field field, int index) {
		Type genericFieldType = field.getGenericType();
		if (genericFieldType instanceof ParameterizedType) {
			ParameterizedType aType = (ParameterizedType) genericFieldType;
			Type[] fieldArgTypes = aType.getActualTypeArguments();
			if (index >= fieldArgTypes.length || index < 0) {
				throw new RuntimeException("你输入的索引"
						+ (index < 0 ? "不能小于0" : "超出了参数的总数"));
			}
			return (Class<?>) fieldArgTypes[index];
		}
		return Object.class;
	}

	/**
	 * 通过反射,获得Field泛型参数的实际类型. 如: public Map<String, Buyer> names;
	 *
	 * @param Field
	 *            field 字段
	 * @param int index 泛型参数所在索引,从0开始.
	 * @return 泛型参数的实际类型, 如果没有实现ParameterizedType接口，即不支持泛型，直接返回<code>Object.class</code>
	 */
	public static Class<?> getFieldGenericType(Field field) {
		return getFieldGenericType(field, 0);
	}

	/**
	 * 实例化.
	 * @param clazz 类型
	 * @param <T> 泛型
	 * @return 对象
	 */
	public static <T> T newInstance(Class<T> clazz) {
		AssertIllegalArgument.isNotNull(clazz, clazz.getName() + "不能为空");
		if (clazz.isInterface()) {
			throw new IllegalArgumentException(
				StringUtils.format("传入 [#1] 是接口 不能实例化", new String[] {clazz.getName()}
				)
			);
		}
		try {
			return clazz.newInstance();
		} catch (InstantiationException e) {
			LOGGER.debug(ExceptionUtils.getStackTrace(e));
			throw new RuntimeException(
				StringUtils.format("[#1] 构造器是否为私有", new String[] {clazz.getName()}
				)
			);
		} catch (IllegalAccessException e) {
			LOGGER.debug(ExceptionUtils.getStackTrace(e));
			throw new RuntimeException(
				StringUtils.format("构造器参数不匹配", new String[] {clazz.getName()}
				)
			);
		}
	}

	/**
	 * 实例化.
	 * @param clazz 类型
	 * @param args 参数数组
	 * @param <T> 泛型
	 * @return 对象
	 */
	public static <T> T newInstance(Class<T> clazz, Object...args) {
		AssertIllegalArgument.isNotNull(clazz, clazz.getName() + "不能为空");
		if (LangUtils.isNotEmpty(args)) {
			Class<?>[] types = new Class<?>[args.length];
			for (int i = 0; i < args.length; i++) {
				types[i] = args[i].getClass();
			}
			try {
				return newInstance(clazz.getConstructor(types), args);
			} catch (SecurityException e) {
				throw new RuntimeException(
						StringUtils.format("[#1{#2}] 此构造器不可访问", new String[] {clazz.getName(), Arrays.asList(types).toString()}
						)
					);
			} catch (NoSuchMethodException e) {
				throw new RuntimeException(
						StringUtils.format("[#1{#2}] 此构造器不存在", new String[] {clazz.getName(), Arrays.asList(types).toString()}
						)
					);
			}
		} else {
			return newInstance(clazz);
		}

	}
	/**
	 * 实例化.
	 * @param ctor 构造器
	 * @param args 参数数组
	 * @param <T> 泛型
	 * @return 对象
	 */
	public static <T> T newInstance(Constructor<T> ctor, Object...args) {
		AssertIllegalArgument.isNotNull(ctor, "构造器不能为空");
		if (!Modifier.isPublic(ctor.getModifiers())
			|| !Modifier.isPublic(ctor.getDeclaringClass().getModifiers())) {
			ctor.setAccessible(true);
		}
		try {
			return ctor.newInstance(args);
		} catch (IllegalArgumentException e) {
			LOGGER.debug(ExceptionUtils.getStackTrace(e));
			throw new RuntimeException(
				StringUtils.format(" [#1] 是否定义成抽象类了 不能实例化",
					new String[] {ctor.getDeclaringClass().getName()}
				)
			);
		} catch (InstantiationException e) {
			LOGGER.debug(ExceptionUtils.getStackTrace(e));
			throw new RuntimeException(
				StringUtils.format("[#1] 构造器是否为私有", new String[] {ctor.getDeclaringClass().getName()}
				)
			);
		} catch (IllegalAccessException e) {
			LOGGER.debug(ExceptionUtils.getStackTrace(e));
			throw new RuntimeException(
				StringUtils.format("构造器参数不匹配", new String[] {ctor.getDeclaringClass().getName()}
				)
			);
		} catch (InvocationTargetException e) {
			LOGGER.debug(ExceptionUtils.getStackTrace(e));
			throw new RuntimeException(
				StringUtils.format("[#1] 构造器抛出异常", new String[] {ctor.getDeclaringClass().getName()}
				)
			);
		}
	}
}