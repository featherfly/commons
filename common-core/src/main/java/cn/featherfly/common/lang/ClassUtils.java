
/**
 * @author zhongj - yufei Jan 5, 2009
 */
package cn.featherfly.common.lang;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.exception.ReflectException;
import cn.featherfly.common.lang.matcher.FieldMatcher;
import cn.featherfly.common.lang.matcher.MethodMatcher;
import cn.featherfly.common.lang.reflect.GenericType;

/**
 * class处理的工具类. .
 *
 * @author zhongj
 * @version 1.0
 * @since 1.0
 */
public final class ClassUtils {

    private ClassUtils() {
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(ClassUtils.class);

    private static final String DOT = ".";

    private static final String SET = "set";
    private static final String GET = "get";
    private static final String IS = "is";

    /**
     * 查找指定类型 .
     * <p>
     * use type name hash code for swith key when match primitive type.
     *
     * <pre>
     * <code>
     * forName("int") is the same as {@linkplain #forName(String, boolean) forName("int", false)}
     * </code>
     * </pre>
     *
     * @param className 类名
     * @return 指定类型
     */
    public static Class<?> forName(String className) {
        return forName(className, false);
    }

    /**
     * 查找指定类型 .
     *
     * @param className 类名
     * @param useHashCodeOnly use type name hash code only for swith key when match primitive type
     * @return 指定类型
     */
    public static Class<?> forName(String className, boolean useHashCodeOnly) {
        if (Lang.isEmpty(className)) {
            return null;
        }

        Class<?> type = getPrimitiveType(className, true);

        if (type != null) {
            return type;
        }

        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * get class name. if type is null, return string "null".
     *
     * @param obj the obj
     * @return name
     */
    public static String getClassName(Object obj) {
        if (obj == null) {
            return "null";
        } else {
            return obj.getClass().getName();
        }
    }

    /**
     * 判断第一个参数是否是第二个参数的父类（父接口）.
     *
     * @param parentType 父类型（包括类和接口）
     * @param childType 子类型（包括类和接口）
     * @return 第一个参数是否是第二个参数的父类（父接口）
     */
    public static boolean isParent(Class<?> parentType, Class<?> childType) {
        if (parentType == null) {
            return false;
        }
        return parentType.isAssignableFrom(childType);
    }

    /**
     * 返回传入类型的共同父类.
     *
     * @param c1 类型1
     * @param c2 类型2
     * @return 共同父类
     */
    public static Class<?> parentClass(Class<?> c1, Class<?> c2) {
        if (c1 == c2) {
            return c1;
        }
        if (c1 == Object.class || c1.isAssignableFrom(c2)) {
            return c1;
        }
        if (c2.isAssignableFrom(c1)) {
            return c2;
        }
        if (c1.isPrimitive() || c2.isPrimitive()) {
            throw new IllegalArgumentException(
                new StringBuilder("incompatible types ").append(c1).append(" and ").append(c2).toString());
        } else {
            return Object.class;
        }
    }

    private static Field getField0(Class<?> type, String fieldName) {
        try {
            return type.getField(fieldName);
        } catch (NoSuchFieldException | SecurityException e) {
            throw new ReflectException(e);
        }
    }

    /**
     * 返回目标类型的指定名称的字段，支持多层嵌套 例如, user.address.no
     *
     * @param type 目标类型
     * @param fieldName 字段名
     * @return 目标类型的指定名称的字段，支持多层嵌套
     */
    public static Field getField(Class<?> type, String fieldName) {
        try {
            if (fieldName.contains(DOT)) {
                String currentFieldName = fieldName.substring(0, fieldName.indexOf(DOT));
                String innerFieldName = fieldName.substring(fieldName.indexOf(DOT) + 1);
                return getField(type.getDeclaredField(currentFieldName).getType(), innerFieldName);
            } else {
                return type.getDeclaredField(fieldName);
            }
        } catch (NoSuchFieldException | SecurityException e) {
            throw new ReflectException(e);
        }
    }

    /**
     * 返回目标类型的指定类型的静态字段值，支持多层嵌套 例如, user.address.no
     *
     * @param type 目标类型
     * @param fieldName 字段名
     * @return 目标类型的指定名称的字段，支持多层嵌套
     */
    public static Object getFieldValue(Class<?> type, String fieldName) {
        try {
            return getField(type, fieldName).get(type);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            throw new ReflectException(e);
        }
    }

    /**
     * 返回目标对象的指定类型的字段值，支持多层嵌套 例如, user.address.no
     *
     * @param object 目标对象
     * @param fieldName 字段名
     * @return 目标类型的指定名称的字段，支持多层嵌套
     */
    public static Object getFieldValue(Object object, String fieldName) {
        try {
            return getField(object.getClass(), fieldName).get(object);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            throw new ReflectException(e);
        }
    }

    /**
     * 设置目标类型的指定类型的静态字段值，支持多层嵌套 例如, user.address.no
     *
     * @param type 目标类型
     * @param fieldName 字段名
     * @param value the value
     */
    public static void setFieldValue(Class<?> type, String fieldName, Object value) {
        try {
            getField(type, fieldName).set(type, value);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            throw new ReflectException(e);
        }
    }

    /**
     * 设置目标对象的指定类型的字段值，支持多层嵌套 例如, user.address.no
     *
     * @param object 目标对象
     * @param fieldName 字段名
     * @param value value
     */
    public static void setFieldValue(Object object, String fieldName, Object value) {
        try {
            getField(object.getClass(), fieldName).set(object, value);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            throw new ReflectException(e);
        }
    }

    /**
     * Gets the method.
     *
     * @param type the type
     * @param methodName the method name
     * @param parameterTypes the parameter types
     * @return the method
     */
    public static Method getMethod(Class<?> type, String methodName, Class<?>... parameterTypes) {
        try {
            return type.getMethod(methodName, parameterTypes);
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }

    /**
     * invoke static method.
     *
     * @param type type
     * @param methodName method name
     * @return method return value
     */
    public static Object invokeMethod(Class<?> type, String methodName) {
        return invokeMethod(type, methodName, new Object[0]);
    }

    /**
     * invoke static method.
     *
     * @param type type
     * @param methodName method name
     * @param args method arguments
     * @return method return value
     */
    public static Object invokeMethod(Class<?> type, String methodName, Object... args) {
        return invokeMethod(type, matchMethod(type, methodName, args), args);
    }

    /**
     * invoke static method.
     *
     * @param type type
     * @param method method
     * @param args method arguments
     * @return method return value
     */
    public static Object invokeMethod(Class<?> type, Method method, Object... args) {
        try {
            return method.invoke(null, args);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException e) {
            throw new ReflectException(e);
        }
    }

    /**
     * invoke object method.
     *
     * @param object object
     * @param methodName method name
     * @return method return value
     */
    public static Object invokeMethod(Object object, String methodName) {
        return invokeMethod(object, methodName, new Object[0]);
    }

    /**
     * invoke object method.
     *
     * @param object object
     * @param methodName method name
     * @param args method arguments
     * @return method return value
     */
    public static Object invokeMethod(Object object, String methodName, Object... args) {
        return invokeMethod(object, matchMethod(object.getClass(), methodName, args), args);
    }

    /**
     * invoke object method.
     *
     * @param object object
     * @param method method
     * @param args method arguments
     * @return method return value
     */
    public static Object invokeMethod(Object object, Method method, Object... args) {
        try {
            return method.invoke(object, args);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException e) {
            throw new ReflectException(e);
        }
    }

    /**
     * 获取标注的注解实力对象，如果没有标注，返回null.
     *
     * @param <A1> 被标注的注解泛型
     * @param <A2> 标注的注解泛型
     * @param annotation 被标注的注解实例对象
     * @param annotationPresent 标注的注解类型
     * @return 返回标注的注解实力对象
     */
    public static <A1 extends Annotation, A2 extends Annotation> A2 getAnnotation(A1 annotation,
        Class<A2> annotationPresent) {
        return getAnnotation(annotation.annotationType(), annotationPresent, false);
    }

    /**
     * 返回指定类型(objectType)的指定注解类型(annotationType), 会把父类里有被标注的字段也进行返回.
     *
     * @param <A> 注解泛型
     * @param objectType 对象类型
     * @param annotationType 注解类型
     * @return 指定注解类型
     */
    public static <A extends Annotation> A getAnnotation(Class<?> objectType, Class<A> annotationType) {
        return getAnnotation(objectType, annotationType, true);
    }

    /**
     * 返回指定类型(objectType)的指定注解类型(annotationType), fromSuper 为 true
     * 时如果没有找到annotionType,则继续从父类查找.
     *
     * @param <A> 注解泛型
     * @param objectType 对象类型
     * @param annotationType 注解类型
     * @param fromSuper 是否从父类型查找
     * @return 指定注解类型
     */
    public static <A extends Annotation> A getAnnotation(Class<?> objectType, Class<A> annotationType,
        boolean fromSuper) {
        if (objectType != null && objectType != Object.class) {
            A a = objectType.getAnnotation(annotationType);
            if (a == null && fromSuper) {
                return getAnnotation(objectType.getSuperclass(), annotationType, fromSuper);
            } else {
                return a;
            }
        }
        return null;
    }

    /**
     * 获取标注的注解实力对象，如果没有标注，返回null.
     *
     * @param <A1> 被标注的注解泛型
     * @param <A2> 标注的注解泛型
     * @param annotation 被标注的注解实例对象
     * @param annotationPresent 标注的注解类型
     * @return 返回标注的注解实力对象数组
     */
    public static <A1 extends Annotation, A2 extends Annotation> A2[] getAnnotations(A1 annotation,
        Class<A2> annotationPresent) {
        return getAnnotations(annotation.annotationType(), annotationPresent, false);
    }

    /**
     * 返回指定类型(objectType)的指定注解类型(annotationType), 会把父类里有被标注的字段也进行返回.
     *
     * @param <A> 注解泛型
     * @param objectType 对象类型
     * @param annotationType 注解类型
     * @return 指定注解类型数组
     */
    public static <A extends Annotation> A[] getAnnotations(Class<?> objectType, Class<A> annotationType) {
        return getAnnotations(objectType, annotationType, true);
    }

    /**
     * 返回指定类型(objectType)的指定注解类型(annotationType), fromSuper 为 true
     * 时如果没有找到annotionType,则继续从父类查找.
     *
     * @param <A> 注解泛型
     * @param objectType 对象类型
     * @param annotationType 注解类型
     * @param fromSuper 是否从父类型查找
     * @return 指定注解类型数组
     */
    public static <A extends Annotation> A[] getAnnotations(Class<?> objectType, Class<A> annotationType,
        boolean fromSuper) {
        if (objectType != null && objectType != Object.class) {
            A[] a = objectType.getAnnotationsByType(annotationType);
            if (a == null && fromSuper) {
                return getAnnotations(objectType.getSuperclass(), annotationType, fromSuper);
            } else {
                return a;
            }
        }
        return null;
    }

    /**
     * 返回指定类型(objectType)被指定注解(annotationType)标注的所有字段【成员变量】(field)
     * 会把父类里有被标注的字段也进行返回.
     *
     * @param <A> 注解泛型
     * @param objectType 对象类型
     * @param annotationType 注解类型
     * @return 指定类型被指定注解标注的所有字段
     */
    public static <A extends Annotation> List<Field> getAnnotatedFields(Class<?> objectType, Class<A> annotationType) {
        return getAnnotatedFields(objectType, annotationType, true);
    }

    /**
     * 返回指定类型(objectType)被指定注解(annotationType)标注的所有字段【成员变量】(field) fromSuper 为
     * true 时会把父类里有被标注的字段也进行返回，为false则只返回本类的.
     *
     * @param <A> 注解泛型
     * @param objectType 对象类型
     * @param annotationType 注解类型
     * @param fromSuper 是否从父类型查找
     * @return 指定类型被指定注解标注的所有字段
     */
    public static <A extends Annotation> List<Field> getAnnotatedFields(Class<?> objectType, Class<A> annotationType,
        boolean fromSuper) {
        List<Field> fieldList = new ArrayList<>();
        getAnnotatedFields(objectType, fieldList, annotationType, fromSuper);
        return fieldList;
    }

    private static <A extends Annotation> void getAnnotatedFields(Class<?> objectType, List<Field> fieldList,
        Class<A> annotationType, boolean fromSuper) {
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
     * 返回指定类型(objectType)被指定注解(annotationType)标注的公共(public)方法(method).
     *
     * @param <A> 注解类型
     * @param objectType 目标类型
     * @param annotationType 注解类型
     * @return 指定注解标注的公共方法
     */
    public static <A extends Annotation> List<Method> getAnnotatedMethods(Class<?> objectType,
        Class<A> annotationType) {
        List<Method> methodList = new ArrayList<>();
        Method[] methods = objectType.getMethods();
        for (Method method : methods) {
            if (method.getAnnotation(annotationType) != null) {
                methodList.add(method);
            }
        }
        return methodList;
    }

    /**
     * 返回访问方法的属性名. 如果不是setter或getter方法，返回null
     *
     * @param method 方法
     * @return 访问方法的属性名
     */
    public static String getPropertyName(Method method) {
        String name = null;
        if (isSetter(method)) {
            name = method.getName().substring(SET.length());
        }
        if (isGetter(method)) {
            name = method.getName();
            if (name.startsWith(IS)) {
                name = name.substring(IS.length());
            } else if (name.startsWith(GET)) {
                name = name.substring(GET.length());
            }
        }
        if (name != null) {
            name = name.substring(0, 1).toLowerCase() + name.substring(1);
        }
        return name;
    }

    /**
     * 是否是set方法(以set开头).
     *
     * @param method 方法
     * @return 是否是set方法
     */
    public static boolean isSetter(Method method) {
        String set = SET;
        String name = method.getName();
        if (Modifier.isStatic(method.getModifiers())) {
            return false;
        }
        return method.getParameterTypes().length == 1 && name.startsWith(set) && !set.equals(name);
    }

    /**
     * 是否是get方法（以get或is开头）.
     *
     * @param method 方法
     * @return 是否是get方法
     */
    public static boolean isGetter(Method method) {
        String get = GET;
        String is = IS;
        String name = method.getName();
        if (Modifier.isStatic(method.getModifiers()) || method.getReturnType() == void.class
            || method.getParameterCount() > 0) {
            return false;
        }
        if (method.getReturnType() == Boolean.TYPE) {
            return name.startsWith(is) && !is.equals(name);
        } else {
            return name.startsWith(get) && !get.equals(name);
        }
    }

    /**
     * 返回getter方法，包括getXxx和isXxx 没有找到返回null.
     *
     * @param field 成员变量
     * @param type 类型
     * @return getter方法
     */
    public static Method getGetter(Field field, Class<?> type) {
        Method method = null;
        String fieldName = field.getName();
        String get = GET + WordUtils.upperCaseFirst(fieldName);
        try {
            method = type.getMethod(get, new Class[] {});
            if (Modifier.isStatic(method.getModifiers())) {
                LOGGER.trace("{} method is static, not a java bean getter", method.getName());
                throw new NoSuchMethodException("method is static, not a java bean getter");
            }
        } catch (Exception e) {
            if (field.getType() != Boolean.TYPE) {
                return method;
            }
            LOGGER.trace("没有找到get{}方法, 使用is{}查找", field.getName(), field.getName());
            try {
                String is = IS + WordUtils.upperCaseFirst(fieldName);
                method = type.getMethod(is, new Class[] {});
                if (Modifier.isStatic(method.getModifiers())) {
                    LOGGER.trace("{} method is static, not a java bean getter", method.getName());
                    method = null;
                }
            } catch (Exception e1) {
                LOGGER.trace("没有找到get{}和is{}方法", field.getName(), field.getName());
            }
        }
        return method;
    }

    /**
     * 返回setter方法 没有找到返回null.
     *
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
            if (Modifier.isStatic(method.getModifiers())) {
                LOGGER.trace("{} method is static, not a java bean setter", method.getName());
                method = null;
            }
        } catch (Exception e) {
            LOGGER.trace("没有找到{}的set方法", field.getName(), field.getName());
        }
        return method;
    }

    /**
     * 是否是基础类型，包括基本类型，基本类型包装类， String,StringBuffer,StringBuilder
     * AtomicInteger,AtomicLong,BigDecimal,BigInteger java.util.Date
     * java.sql.Date,java.sql.Time,java.sql.Timestamp 注意：不包括这些类的子类
     *
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
     * 是否是数组.
     *
     * @param type 类型
     * @return 是否是map接口，及其子接口或实现类
     */
    public static boolean isArray(Class<?> type) {
        if (type == null) {
            return false;
        }
        return type.isArray();
    }

    /**
     * 是否是cellection接口,及其子接口或实现类.
     *
     * @param type 类型
     * @return 是否是cellection接口,及其子接口或实现类
     */
    public static boolean isCellection(Class<?> type) {
        return isParent(Collection.class, type);
    }

    /**
     * 是否是map接口，及其子接口或实现类.
     *
     * @param type 类型
     * @return 是否是map接口，及其子接口或实现类
     */
    public static boolean isMap(Class<?> type) {
        return isParent(Map.class, type);
    }

    /**
     * 是否是record类.
     *
     * @param type 类型
     * @return 是否是map接口，及其子接口或实现类
     */
    public static boolean isRecord(Class<?> type) {
        Class<?> parent = type.getSuperclass();
        if (parent == null) {
            return false;
        }
        return "java.lang.Record".equals(parent.getName())
            && Modifier.isFinal(type.getModifiers());
    }

    /**
     * 判断传入类型是否是抽象类 .
     *
     * @param type type
     * @return 是否是抽象类
     */
    public static boolean isAbstractClass(Class<?> type) {
        return Modifier.isAbstract(type.getModifiers());
    }

    /**
     * 判断传入类型是否是可实例化的类 .
     *
     * @param type type
     * @return 是否是可实例化的类
     */
    public static boolean isInstanceClass(Class<?> type) {
        return !isAbstractClass(type);
    }

    /**
     * Class泛型参数强制转型.
     *
     * @param <T> 泛型
     * @param type type
     * @param castToType castToType
     * @return 泛型Class
     */
    @SuppressWarnings("unchecked")
    public static <T> Class<T> castGenericType(Class<?> type, T castToType) {
        return (Class<T>) type;
    }

    /**
     * 获取指定类所在的JAR包的JAR文件.如果不是JAR文件中的返回null.
     *
     * @param type 类型
     * @return JAR文件或null
     */
    public static File getJar(Class<?> type) {
        return getJar(type.getName());
    }

    /**
     * 获取指定类所在的JAR包的JAR文件.如果不是JAR文件中的返回null.
     *
     * @param className className
     * @return JAR文件或null
     */
    public static File getJar(String className) {
        if (Lang.isEmpty(className)) {
            return null;
        }
        URL url = Thread.currentThread().getContextClassLoader().getResource(packageToDir(className) + ".class");
        if (url != null && "jar".equalsIgnoreCase(url.getProtocol())) {
            String excalmatoryMark = "!";
            String filePrefix = "file:";
            String path = url.getPath();
            url.getProtocol();
            if (path.startsWith(filePrefix)) {
                String os = System.getProperty("os.name");
                if (os.startsWith("Windows")) {
                    path = org.apache.commons.lang3.StringUtils.substringBetween(path, "file:/", excalmatoryMark);
                } else if (os.startsWith("Linux")) {
                    path = org.apache.commons.lang3.StringUtils.substringBetween(path, filePrefix, excalmatoryMark);
                } else {
                    path = org.apache.commons.lang3.StringUtils.substringBetween(path, filePrefix, excalmatoryMark);
                }
            } else {
                path = org.apache.commons.lang3.StringUtils.substringBefore(path, excalmatoryMark);
            }
            return new File(path);
        }
        return null;
    }

    /**
     * <p>
     * 转换包模式为目录模式.xx.yy.Ttt -&gt; xx/yy/Ttt
     * </p>
     *
     * @param type 类型
     * @return 目录模式表示的type文件
     */
    public static String packageToFile(Class<?> type) {
        if (type == null) {
            return null;
        }
        return packageToFile(type, true);
    }

    /**
     * <p>
     * 转换包模式为目录模式.xx.yy.Ttt -&gt; xx/yy/Ttt
     * </p>
     *
     * @param type 类型
     * @param containExt 包含扩展名
     * @return 目录模式表示的type文件
     */
    public static String packageToFile(Class<?> type, boolean containExt) {
        if (type == null) {
            return null;
        }
        if (containExt) {
            return packageToDir(type.getName()) + ".class";
        } else {
            return packageToDir(type.getName());
        }
    }

    /**
     * <p>
     * 转换包模式为目录模式.xx.yy.Ttt -&gt; xx/yy/Ttt
     * </p>
     *
     * @param type 类型
     * @return 目录模式字符串
     */
    public static String packageToDir(Class<?> type) {
        if (type == null) {
            return null;
        }
        return packageToDir(type.getPackage());
    }

    /**
     * <p>
     * 转换包模式为目录模式.xx.yy.Ttt -&gt; xx/yy/Ttt
     * </p>
     *
     * @param pack 包
     * @return 目录模式字符串
     */
    public static String packageToDir(Package pack) {
        if (pack == null) {
            return null;
        }
        return packageToDir(pack.getName());
    }

    /**
     * <p>
     * 转换包模式为目录模式.xx.yy.Ttt -&gt; xx/yy/Ttt
     * </p>
     *
     * @param packageString 包模式字符串
     * @return 目录模式字符串
     */
    public static String packageToDir(String packageString) {
        if (Lang.isEmpty(packageString)) {
            return null;
        }
        return packageString.replace(".", "/");
    }

    // ********************************************************************
    //    获取泛型参数
    // ********************************************************************

    /**
     * 通过反射,获得指定类的父类的泛型参数的实际类型. 如BuyerServiceBean extends
     * DaoSupport&lt;Buyer&gt;
     *
     * @param <T> 泛型
     * @param clazz clazz 需要反射的类,该类必须继承范型父类
     * @param index 泛型参数所在索引,从0开始.
     * @return 范型参数的实际类型, 如果没有实现ParameterizedType接口，即不支持泛型，所以直接返回
     *         <code>Object.class</code>
     * @deprecated 拼写错误，后续删除该方法 {@link #getSuperClassGenericType(Class, int)}
     */
    @Deprecated
    @SuppressWarnings("unchecked")
    public static <T> Class<T> getSuperClassGenricType(Class<?> clazz, int index) {
        // 得到泛型父类
        Type genType = clazz.getGenericSuperclass();
        // 如果没有实现ParameterizedType接口，即不支持泛型，直接返回Object.class
        if (!(genType instanceof ParameterizedType)) {
            return (Class<T>) Object.class;
        }
        // 返回表示此类型实际类型参数的Type对象的数组,数组里放的都是对应类型的Class, 如BuyerServiceBean extends
        // DaoSupport<Buyer,Contact>就返回Buyer和Contact类型
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        if (index >= params.length || index < 0) {
            throw new IllegalArgumentException("你输入的索引" + (index < 0 ? "不能小于0" : "超出了参数的总数"));
        }
        if (!(params[index] instanceof Class)) {
            return (Class<T>) Object.class;
        }
        return (Class<T>) params[index];
    }

    /**
     * 通过反射,获得指定类的父类的第一个泛型参数的实际类型. 如BuyerServiceBean extends
     * DaoSupport&lt;Buyer&gt;
     *
     * @param <T> 泛型
     * @param clazz clazz 需要反射的类,该类必须继承泛型父类
     * @return 泛型参数的实际类型, 如果没有实现ParameterizedType接口，即不支持泛型，所以直接返回
     *         <code>Object.class</code>
     * @deprecated 拼写错误，后续删除该方法 {@link #getSuperClassGenericType(Class)}
     */
    @Deprecated
    public static <T> Class<T> getSuperClassGenricType(Class<?> clazz) {
        return getSuperClassGenricType(clazz, 0);
    }

    /**
     * 通过反射,获得指定类的父类的泛型参数的实际类型与父类定义泛型是的定义之间的映射关系.
     *
     * @param clazz clazz 需要反射的类,该类必须继承泛型父类
     * @return 使用父类定义泛型的参数名作为KEY,子类实例化泛型的TYPE作为VALUE作为 <code>Object.class</code>
     * @deprecated 拼写错误，后续删除该方法 {@link #getSuperClassGenericTypeMap(Class)}
     */
    @Deprecated
    public static Map<String, Type> getSuperClassGenricTypeMap(Class<?> clazz) {
        Map<String, Type> typeGenericParams = new HashMap<>(0);
        // 得到泛型父类
        Type genType = clazz.getGenericSuperclass();
        if (genType instanceof ParameterizedType) {
            // 如果是泛型父类拿到泛型父类定义中已经明确的泛型
            ParameterizedType pt = (ParameterizedType) genType;
            Type[] types = pt.getActualTypeArguments();
            // 获取父类型的泛型定义
            TypeVariable<?>[] tvs = clazz.getSuperclass().getTypeParameters();
            for (int i = 0; i < types.length; i++) {
                typeGenericParams.put(tvs[i].getName(), types[i]);
            }
        }
        return typeGenericParams;
    }

    /**
     * 通过反射,获得指定类的父类的泛型参数的实际类型. 如BuyerServiceBean extends
     * DaoSupport&lt;Buyer&gt;
     *
     * @param clazz clazz 需要反射的类,该类必须继承范型父类
     * @param index 泛型参数所在索引,从0开始.
     * @return 范型参数(ParameterizedType)
     */
    public static ParameterizedType getSuperClassGenericParameterizedType(Class<?> clazz, int index) {
        // 得到泛型父类
        Type genType = clazz.getGenericSuperclass();
        // 如果没有实现ParameterizedType接口，即不支持泛型，抛出异常
        if (!(genType instanceof ParameterizedType)) {
            throw new IllegalArgumentException(
                "there is no generic parameter with super class, such as : public class StringList extends ArrayList<String>");
        }
        // 返回表示此类型实际类型参数的Type对象的数组,数组里放的都是对应类型的Class, 如BuyerServiceBean extends
        // DaoSupport<Buyer,Contact>就返回Buyer和Contact类型
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        if (index >= params.length || index < 0) {
            throw new IllegalArgumentException("索引参数[index]" + (index < 0 ? "不能小于0" : "超出了参数的总数"));
        }

        Type type = params[index];
        if (type instanceof ParameterizedType) {
            return (ParameterizedType) type;
        } else {
            throw new IllegalArgumentException("super class generic parameter " + index + " is not ParameterizedType");
        }
    }

    /**
     * 通过反射,获得指定类的父类的第一个泛型参数的实际类型. 如BuyerServiceBean extends
     * DaoSupport&lt;Buyer&gt;
     *
     * @param clazz clazz 需要反射的类,该类必须继承泛型父类
     * @return 范型参数(ParameterizedType)
     */
    public static ParameterizedType getSuperClassGenericParameterizedType(Class<?> clazz) {
        return getSuperClassGenericParameterizedType(clazz, 0);
    }

    /**
     * 通过反射,获得指定类的父类的泛型参数的实际类型. 如BuyerServiceBean extends
     * DaoSupport&lt;Buyer&gt;
     *
     * @param <T> 泛型
     * @param clazz clazz 需要反射的类,该类必须继承范型父类
     * @param index 泛型参数所在索引,从0开始.
     * @return 范型参数的实际类型, 如果没有实现ParameterizedType接口，即不支持泛型，直接返回Object.class
     */
    @SuppressWarnings("unchecked")
    public static <T> Class<T> getSuperClassGenericType(Class<?> clazz, int index) {
        // 得到泛型父类
        Type genType = clazz.getGenericSuperclass();
        // 如果没有实现ParameterizedType接口，即不支持泛型，返回Object.class
        if (!(genType instanceof ParameterizedType)) {
            return (Class<T>) Object.class;
        }
        // 返回表示此类型实际类型参数的Type对象的数组,数组里放的都是对应类型的Class, 如BuyerServiceBean extends
        // DaoSupport<Buyer,Contact>就返回Buyer和Contact类型
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        if (index >= params.length || index < 0) {
            throw new IllegalArgumentException("你输入的索引" + (index < 0 ? "不能小于0" : "超出了参数的总数"));
        }

        Type type = params[index];
        if (type instanceof ParameterizedType) {
            return getClassType(type);
        } else if (!(type instanceof Class)) {
            return (Class<T>) Object.class;
        }
        return (Class<T>) type;
    }

    /**
     * 通过反射,获得指定类的父类的第一个泛型参数的实际类型. 如BuyerServiceBean extends
     * DaoSupport&lt;Buyer&gt;
     *
     * @param <T> 泛型
     * @param clazz clazz 需要反射的类,该类必须继承泛型父类
     * @return 泛型参数的实际类型, 如果没有实现ParameterizedType接口，即不支持泛型，所以直接返回
     *         <code>Object.class</code>
     */
    public static <T> Class<T> getSuperClassGenericType(Class<?> clazz) {
        return getSuperClassGenericType(clazz, 0);
    }

    /**
     * 通过反射,获得指定类的父类的泛型参数的实际类型与父类定义泛型是的定义之间的映射关系.
     *
     * @param clazz clazz 需要反射的类,该类必须继承泛型父类
     * @return 使用父类定义泛型的参数名作为KEY,子类实例化泛型的TYPE作为VALUE作为 <code>Object.class</code>
     */
    public static Map<String, Type> getSuperClassGenericTypeMap(Class<?> clazz) {
        Map<String, Type> typeGenericParams = new HashMap<>(0);
        // 得到泛型父类
        Type genType = clazz.getGenericSuperclass();
        if (genType instanceof ParameterizedType) {
            // 如果是泛型父类拿到泛型父类定义中已经明确的泛型
            ParameterizedType pt = (ParameterizedType) genType;
            Type[] types = pt.getActualTypeArguments();
            // 获取父类型的泛型定义
            TypeVariable<?>[] tvs = clazz.getSuperclass().getTypeParameters();
            for (int i = 0; i < types.length; i++) {
                typeGenericParams.put(tvs[i].getName(), types[i]);
            }
        }
        return typeGenericParams;
    }

    /**
     * 通过反射,获得指定类的父类的泛型参数的实际类型与父类定义泛型是的定义之间的映射关系.
     *
     * @param clazz clazz 需要反射的类,该类必须继承泛型父类
     * @return 使用父类定义泛型的参数名作为KEY,子类实例化泛型的TYPE作为VALUE作为 <code>Object.class</code>
     */
    public static Map<Class<?>, Map<String, Type>> getSuperClassAllGenericTypeMap(Class<?> clazz) {
        //        Map<String, Type> typeGenericParams = new HashMap<>();
        //        // 得到泛型父类
        //        Type genType = clazz.getGenericSuperclass();
        //        if (genType instanceof ParameterizedType) {
        //            // 如果是泛型父类拿到泛型父类定义中已经明确的泛型
        //            ParameterizedType pt = (ParameterizedType) genType;
        //            Type[] types = pt.getActualTypeArguments();
        //            // 获取父类型的泛型定义
        //            TypeVariable<?>[] tvs = clazz.getSuperclass().getTypeParameters();
        //            for (int i = 0; i < types.length; i++) {
        //                typeGenericParams.put(tvs[i].getName(), types[i]);
        //            }
        //        }
        return getSuperClassAllGenericTypeMap(clazz, new HashMap<>());
    }

    private static Map<Class<?>, Map<String, Type>> getSuperClassAllGenericTypeMap(Class<?> clazz,
        Map<Class<?>, Map<String, Type>> typeGenericMap) {
        if (clazz.getSuperclass() == Object.class) {
            return typeGenericMap;
        }
        typeGenericMap.put(clazz.getSuperclass(), getSuperClassGenericTypeMap(clazz));
        return getSuperClassAllGenericTypeMap(clazz.getSuperclass(), typeGenericMap);
    }

    /**
     * 获得指定类实现的接口的泛型参数的实际类型. 如BuyerServiceBean implements
     * DaoSupport&lt;Buyer&gt;
     *
     * @param <T> 泛型
     * @param clazz clazz 需要反射的类,该类必须继承范型父类
     * @param index 泛型参数所在索引,从0开始.
     * @param interfaceType 实现的接口类型
     * @return 范型参数的实际类型,
     *         如果没有实现ParameterizedType接口，即不支持泛型，直接返回<code>Object.class</code>
     */
    @SuppressWarnings("unchecked")
    public static <T> Class<T> getInterfaceGenericType(Class<?> clazz, int index, Class<?> interfaceType) {
        for (Type type : clazz.getGenericInterfaces()) {
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                if (parameterizedType.getRawType() == interfaceType) {
                    // 返回表示此类型实际类型参数的Type对象的数组,数组里放的都是对应类型的Class, 如BuyerServiceBean extends
                    // DaoSupport<Buyer,Contact>就返回Buyer和Contact类型
                    Type[] params = parameterizedType.getActualTypeArguments();
                    if (index >= params.length || index < 0) {
                        throw new IllegalArgumentException("你输入的索引" + (index < 0 ? "不能小于0" : "超出了参数的总数"));
                    }
                    if (!(params[index] instanceof Class)) {
                        return (Class<T>) Object.class;
                    }
                    return (Class<T>) params[index];
                }
            }
        }
        return (Class<T>) Object.class;
    }

    /**
     * 获得指定类实现的接口的第一个泛型参数的实际类型. 如BuyerServiceBean implements
     * DaoSupport&lt;Buyer&gt;
     *
     * @param <T> 泛型
     * @param clazz clazz 需要反射的类,该类必须继承泛型父类
     * @param interfaceType 实现的接口类型
     * @return 泛型参数的实际类型, 如果没有实现ParameterizedType接口，即不支持泛型，所以直接返回
     *         <code>Object.class</code>
     */
    public static <T> Class<T> getInterfaceGenericType(Class<?> clazz, Class<?> interfaceType) {
        return getInterfaceGenericType(clazz, 0, interfaceType);
    }

    /**
     * 通过反射,获得指定类的父类的泛型参数的实际类型与父类定义泛型是的定义之间的映射关系.
     *
     * @param clazz clazz 需要反射的类,该类必须继承泛型父类
     * @param interfaceType the interface type
     * @return 使用父类定义泛型的参数名作为KEY,子类实例化泛型的TYPE作为VALUE作为 <code>Object.class</code>
     */
    public static Map<String, Type> getInterfaceGenericTypeMap(Class<?> clazz, Class<?> interfaceType) {
        Map<String, Type> typeGenericParams = new HashMap<>(0);
        for (Type type : clazz.getGenericInterfaces()) {
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                if (parameterizedType.getRawType() == interfaceType) {
                    // 如果是泛型父类拿到泛型父类定义中已经明确的泛型
                    Type[] types = parameterizedType.getActualTypeArguments();
                    // 获取父类型的泛型定义
                    TypeVariable<?>[] tvs = interfaceType.getTypeParameters();
                    for (int i = 0; i < types.length; i++) {
                        typeGenericParams.put(tvs[i].getName(), types[i]);
                    }
                }
            }
        }
        return typeGenericParams;
    }

    /**
     * 通过反射,获得指定类的父类的泛型参数的实际类型与父类定义泛型是的定义之间的映射关系.
     *
     * @param clazz clazz 需要反射的类,该类必须继承泛型父类
     * @return 使用父类定义泛型的参数名作为KEY,子类实例化泛型的TYPE作为VALUE作为 <code>Object.class</code>
     */
    public static Map<Class<?>, Map<String, Type>> getInterfaceGenericTypeMap(Class<?> clazz) {
        Map<Class<?>, Map<String, Type>> result = new HashMap<>();
        for (Class<?> interfaceType : clazz.getInterfaces()) {
            result.put(interfaceType, getInterfaceGenericTypeMap(clazz, interfaceType));
        }
        return result;
    }

    /**
     * <pre>
     * 获得方法返回对象的实际类型,如果是具现化的泛型类型，则返回具现化的类型.
     * 例:
     *  <code>
     *  public class StringList extends ArrayList&lt;String&gt; {
     *  }
     *
     *  ClassUtils.getMethodReturnType(StringList.class, "get", int.class) == String.class
     *
     *  // 获取StringList对应的{@link java.util.List#get(int)}返回泛型对象的实际类型
     * </code>
     * </pre>
     *
     * @param <T> the generic type
     * @param type the type
     * @param methodName the method name
     * @param parameterTypes the parameter types
     * @return 方法返回对象的实际类型.
     */
    public static <T> Class<T> getMethodReturnType(Class<?> type, String methodName, Class<?>... parameterTypes) {
        return getMethodReturnType(type, getMethod(type, methodName, parameterTypes));
    }

    /**
     * 获得方法返回对象的实际类型,如果是具现化的泛型类型，则返回具现化的类型.
     *
     * @param <T> 泛型
     * @param type the type
     * @param method 方法
     * @return 方法返回对象的实际类型.
     * @see #getMethodReturnType(Class, String, Class...)
     */
    public static <T> Class<T> getMethodReturnType(Class<?> type, Method method) {
        return getGenericInstanceType(type, method.getGenericReturnType());
    }

    /**
     * <pre>
     * 获得方法返回值第一个泛型参数的实际类型. 如: public Map&lt;String, Buyer&gt; getNames(){}
     * 例：
     * <code>
     *  public class StringLongMap extends HashMap&lt;String,Long&gt; {
     *  }
     *
     *  ClassUtils.getMethodReturnTypeGenericParameterType(StringLongMap.class, "keySet", new Class[0]) == String.class
     *  ClassUtils.getMethodReturnTypeGenericParameterType(StringLongMap.class, "values", new Class[0]) == Long.class
     *
     * </code>
     * </pre>
     *
     * @param <T> 泛型
     * @param type the type
     * @param methodName the method name
     * @param parameterTypes the parameter types
     * @return 泛型参数的实际类型, 如果没有实现ParameterizedType接口，即不支持泛型，所以直接返回
     *         <code>Object.class</code>
     */
    public static <T> Class<T> getMethodReturnTypeGenericParameterType(Class<?> type, String methodName,
        Class<?>... parameterTypes) {
        return getMethodReturnTypeGenericParameterType(type, getMethod(type, methodName, parameterTypes));
    }

    /**
     * 获得方法返回值第一个泛型参数的实际类型. 如: public Map&lt;String, Buyer&gt; getNames(){}
     *
     * @param <T> 泛型
     * @param type the type
     * @param method 方法
     * @return 泛型参数的实际类型, 如果没有实现ParameterizedType接口，即不支持泛型，所以直接返回
     *         <code>Object.class</code>
     * @see #getMethodReturnTypeGenericParameterType(Class, String, Class...)
     */
    public static <T> Class<T> getMethodReturnTypeGenericParameterType(Class<?> type, Method method) {
        return getMethodReturnTypeGenericParameterType(type, method, 0);
    }

    /**
     * <pre>
     * 获得方法返回值泛型参数的实际类型. 如: public Map&lt;String, Buyer&gt; getNames(){}
     * 例：
     * <code>
     *  public class StringLongMap extends HashMap&lt;String,Long&gt; {
     *  }
     *
     *  ClassUtils.getMethodReturnTypeGenericParameterType(StringLongMap.class, "keySet", new Class[0]) == String.class
     *  ClassUtils.getMethodReturnTypeGenericParameterType(StringLongMap.class, "values", new Class[0]) == Long.class
     *
     *  public class Super&lt;K,V&gt; {
     *      public Map&lt;K,V&gt; map() {...}
     *  }
     *
     *  public class Sub implements Super&lt;String, Long&gt; {
     *  }
     *
     *  ClassUtils.getMethodReturnTypeGenericParameterType(Sub.class, "map", new Class[0]) == String.class
     *  ClassUtils.getMethodReturnTypeGenericParameterType(Sub.class, Sub.class.getMethod("map", new Class[0]), 1) == Long.class
     *
     * </code>
     * </pre>
     *
     * @param <T> 泛型
     * @param type the type
     * @param method 方法
     * @param index 泛型参数所在索引,从0开始.
     * @return 泛型参数的实际类型, 如果没有实现ParameterizedType接口，即不支持泛型，所以直接返回
     *         <code>Object.class</code>
     */
    public static <T> Class<T> getMethodReturnTypeGenericParameterType(Class<?> type, Method method, int index) {
        return getGenericParameterType(type, method.getGenericReturnType(), index);
    }

    /**
     * <pre>
     * 获得方法返回对象的所有泛型参数的实际类型列表，如果是已经具现化的泛型，则返回具现化的类型.
     * </pre>
     *
     * @param type the type
     * @param methodName the method name
     * @param parameterTypes the parameter types
     * @return 泛型参数的实际类型,
     *         如果没有实现ParameterizedType接口，即不支持泛型，直接返回<code>Object.class</code>
     */
    public static List<Class<?>> getMethodReturnTypeGenericParameterTypes(Class<?> type, String methodName,
        Class<?>... parameterTypes) {
        return getMethodReturnTypeGenericParameterTypes(type, getMethod(type, methodName, parameterTypes));
    }

    /**
     * <pre>
     * 获得方法返回对象的所有泛型参数的实际类型列表，如果是已经具现化的泛型，则返回具现化的类型.
     * </pre>
     *
     * @param type the type
     * @param method the method
     * @return 泛型参数的实际类型,
     *         如果没有实现ParameterizedType接口，即不支持泛型，直接返回<code>Object.class</code>
     */
    public static List<Class<?>> getMethodReturnTypeGenericParameterTypes(Class<?> type, Method method) {
        return getGenericParameterTypes(type, method.getGenericReturnType());
    }

    /**
     * <pre>
     * 获得第一个方法参数对象的实际类型,如果是具现化的泛型类型，则返回具现化的类型.
     * </pre>
     *
     * @param <T> the generic type
     * @param type the type
     * @param methodName the method name
     * @param parameterTypes the parameter types
     * @return 方法参数对象的实际类型.
     * @see #getMethodParameterType(Class, Method, int)
     */
    public static <T> Class<T> getMethodParameterType(Class<?> type, String methodName, Class<?>... parameterTypes) {
        return getMethodParameterType(type, getMethod(type, methodName, parameterTypes));
    }

    /**
     * 获得第一个方法参数对象的实际类型,如果是具现化的泛型类型，则返回具现化的类型.
     *
     * @param <T> 泛型
     * @param type the type
     * @param method 方法
     * @return 方法参数的实际类型.
     * @see #getMethodParameterType(Class, Method, int)
     */
    public static <T> Class<T> getMethodParameterType(Class<?> type, Method method) {
        return getMethodParameterType(type, method, 0);
    }

    /**
     * <pre>
     * 获得指定方法参数对象的实际类型,如果是具现化的泛型类型，则返回具现化的类型.
     *
     * <code>
     *  public class Super&lt;K,V extends Number&gt; {
     *      public void set(K k, V v) {...}
     *  }
     *
     *  public class Sub implements Super&lt;String, Long&gt; {
     *  }
     *
     *  Method method = Sub.class.getMethod("set", Object.class, Number.class);
     *  ClassUtils.getMethodParameterType(Sub.class, method) == String.class
     *  ClassUtils.getMethodParameterType(Sub.class, method, 1) == Long.class
     * </code>
     * </pre>
     *
     * @param <T> 泛型
     * @param type the type
     * @param method 方法
     * @param paramIndex the param index
     * @return 方法参数对象的实际类型.
     */
    public static <T> Class<T> getMethodParameterType(Class<?> type, Method method, int paramIndex) {
        Parameter[] ps = method.getParameters();
        //        AssertIllegalArgument.isGe(paramIndex, 0, "paramIndex");
        //        AssertIllegalArgument.isLt(paramIndex, ps.length, "paramIndex");
        if (paramIndex >= ps.length || paramIndex < 0) {
            throw new IllegalArgumentException("你输入的索引" + (paramIndex < 0 ? "不能小于0" : "超出了参数的总数" + ps.length));
        }
        Parameter p = ps[paramIndex];
        return getGenericInstanceType(type, p.getParameterizedType(), p.getType());
    }

    /**
     * 获得方法参数对象的实际类型,如果是具现化的泛型类型，则返回具现化的类型.
     *
     * @param type the type
     * @param methodName the method name
     * @param parameterTypes the parameter types
     * @return 方法参数对象的实际类型列表.
     * @see #getMethodReturnType(Class, String, Class...)
     */
    public static List<Class<?>> getMethodParameterTypes(Class<?> type, String methodName, Class<?>... parameterTypes) {
        return getMethodParameterTypes(type, getMethod(type, methodName, parameterTypes));
    }

    /**
     * 获得方法参数对象的实际类型,如果是具现化的泛型类型，则返回具现化的类型.
     *
     * @param type the type
     * @param method 方法
     * @return 方法返回对象的实际类型.
     * @see #getMethodReturnType(Class, String, Class...)
     */
    public static List<Class<?>> getMethodParameterTypes(Class<?> type, Method method) {
        List<Class<?>> types = new ArrayList<>();
        for (Parameter p : method.getParameters()) {
            types.add(getGenericInstanceType(type, p.getParameterizedType(), p.getType()));
        }
        return types;
    }

    /**
     * 获得方法输入参数第一个输入参数的第一个泛型参数的实际类型. 如: public void add(Map&lt;String, Buyer&gt;
     * maps, List&lt;String&gt; names){}
     *
     * @param type the type
     * @param methodName the method name
     * @param parameterTypes the parameter types
     * @return 输入参数的泛型参数的实际类型集合, 如果没有实现ParameterizedType接口，即不支持泛型，所以直接返回空集合
     */
    public static Class<?> getMethodGenericParameterType(Class<?> type, String methodName, Class<?>... parameterTypes) {
        return getMethodGenericParameterType(type, getMethod(type, methodName, parameterTypes));
    }

    /**
     * 获得方法输入参数第一个输入参数的第一个泛型参数的实际类型. 如: public void add(Map&lt;String, Buyer&gt;
     * maps, List&lt;String&gt; names){}
     *
     * @param type the type
     * @param method 方法
     * @return 输入参数的泛型参数的实际类型集合, 如果没有实现ParameterizedType接口，即不支持泛型，所以直接返回空集合
     */
    public static Class<?> getMethodGenericParameterType(Class<?> type, Method method) {
        return getMethodGenericParameterType(type, method, 0);
    }

    /**
     * 获得方法输入参数第index个输入参数的第一个泛型参数的实际类型. 如: public void add(Map&lt;String,
     * Buyer&gt; maps, List&lt;String&gt; names){}
     *
     * @param type the type
     * @param method 方法
     * @param index 第几个输入参数
     * @return 输入参数的泛型参数的实际类型集合, 如果没有实现ParameterizedType接口，即不支持泛型，所以直接返回空集合
     */
    public static Class<?> getMethodGenericParameterType(Class<?> type, Method method, int index) {
        return getMethodGenericParameterType(type, method, index, 0);
    }

    /**
     * 获得方法输入参数第index个输入参数的所有泛型参数的实际类型. 如: public void add(Map&lt;String,
     * Buyer&gt; maps, List&lt;String&gt; names){}
     *
     * @param <T> the generic type
     * @param type the type
     * @param method 方法
     * @param paramIndex 第几个输入参数
     * @param genericTypeIndex 第几个泛型参数
     * @return 输入参数的泛型参数的实际类型集合, 如果没有实现ParameterizedType接口，即不支持泛型，所以直接返回空集合
     */
    public static <T> Class<T> getMethodGenericParameterType(Class<?> type, Method method, int paramIndex,
        int genericTypeIndex) {
        Type[] genericParameterTypes = method.getGenericParameterTypes();
        if (paramIndex >= genericParameterTypes.length || paramIndex < 0) {
            throw new IllegalArgumentException(
                "你输入的索引" + (paramIndex < 0 ? "不能小于0" : "超出了参数的总数" + genericParameterTypes.length));
        }
        return getGenericParameterType(type, genericParameterTypes[paramIndex], genericTypeIndex);
    }

    /**
     * 获得方法所有参数的所有泛型参数的实际类型列表. 如: public void add(Map&lt;String, Buyer&gt; maps,
     * List&lt;String&gt; names){}
     *
     * @param type the type
     * @param method 方法
     * @return 输入参数的泛型参数的实际类型集合, 如果没有实现ParameterizedType接口，即不支持泛型，所以直接返回空集合
     */
    public static List<List<Class<?>>> getMethodGenericParameterTypesAll(Class<?> type, Method method) {
        List<List<Class<?>>> list = new ArrayList<>();
        for (int i = 0; i < method.getParameters().length; i++) {
            list.add(getMethodGenericParameterTypes(type, method, i));
        }
        return list;
    }

    /**
     * 获得方法输入参数第一个输入参数的所有泛型参数的实际类型列表. 如: public void add(Map&lt;String,
     * Buyer&gt; maps, List&lt;String&gt; names){}
     *
     * @param type the type
     * @param method 方法
     * @return 输入参数的泛型参数的实际类型集合, 如果没有实现ParameterizedType接口，即不支持泛型，所以直接返回空集合
     */
    public static List<Class<?>> getMethodGenericParameterTypes(Class<?> type, Method method) {
        return getMethodGenericParameterTypes(type, method, 0);
    }

    /**
     * 获得方法输入参数第index个输入参数的所有泛型参数的实际类型. 如: public void add(Map&lt;String,
     * Buyer&gt; maps, List&lt;String&gt; names){}
     *
     * @param type the type
     * @param method 方法
     * @param index 第几个输入参数
     * @return 输入参数的泛型参数的实际类型集合, 如果没有实现ParameterizedType接口，即不支持泛型，所以直接返回空集合
     */
    public static List<Class<?>> getMethodGenericParameterTypes(Class<?> type, Method method, int index) {
        Type[] genericParameterTypes = method.getGenericParameterTypes();
        if (index >= genericParameterTypes.length || index < 0) {
            throw new IllegalArgumentException("你输入的索引" + (index < 0 ? "不能小于0" : "超出了参数的总数"));
        }
        Type genericParameterType = genericParameterTypes[index];
        return getGenericParameterTypes(type, genericParameterType);
        //        List<Class<?>> results = new ArrayList<>();
        //        for (int i = 0; i < genericParameterTypes.length; i++) {
        //            Class<?> parameterArgClass = getGenericType(genericParameterType, index);
        //            results.add(parameterArgClass);
        //        }
        //        if (genericParameterType instanceof ParameterizedType) {
        //            ParameterizedType aType = (ParameterizedType) genericParameterType;
        //            Type[] parameterArgTypes = aType.getActualTypeArguments();
        //            for (Type parameterArgType : parameterArgTypes) {
        //                Class<?> parameterArgClass = (Class<?>) parameterArgType;
        //                results.add(parameterArgClass);
        //            }
        //            return results;
        //        }
        //        return results;
    }

    /**
     * <pre>
     * 获得Field参数的实际类型，如果是已经具现化的泛型，则返回具现化的类型.
     * 例：
     * <code>
     *  public abstract class Entity&lt;ID, N&gt; {
     *      public ID id;
     *
     *      public N age;
     *  }
     *
     *  public class User extends Entity&lt;Long,Integer&gt; {
     *  }
     *
     *  ClassUtils.getFieldType(User.class, "id") == Long.class
     *  ClassUtils.getFieldType(User.class, "age") == Integer.class
     *
     *  </code>
     * </pre>
     *
     * @param <T> 泛型
     * @param type the type
     * @param fieldName the field name
     * @return field的实际类型,如果是一个没有具现化的泛型，则返回Object.class
     */
    public static <T> Class<T> getFieldType(Class<?> type, String fieldName) {
        return getFieldType(type, getField0(type, fieldName));
    }

    /**
     * <pre>
     * 获得Field参数的实际类型，如果是已经具现化的泛型，则返回具现化的类型.
     * </pre>
     *
     * @param <T> 泛型
     * @param type the type
     * @param field 字段
     * @return field的实际类型,如果是一个没有具现化的泛型，则返回Object.class
     * @see #getFieldType(Class, String)
     */
    public static <T> Class<T> getFieldType(Class<?> type, Field field) {
        return getGenericInstanceType(type, field.getGenericType());
    }

    /**
     * <pre>
     * 获得Field的第一个泛型参数的实际类型，如果是已经具现化的泛型，则返回具现化的类型.
     * </pre>
     *
     * @param <T> the generic type
     * @param type the type
     * @param fieldName the field name
     * @return 泛型参数的实际类型,
     *         如果没有实现ParameterizedType接口，即不支持泛型，直接返回<code>Object.class</code>
     */
    public static <T> Class<T> getFieldGenericParameterType(Class<?> type, String fieldName) {
        return getFieldGenericParameterType(type, getField0(type, fieldName));
    }

    /**
     * <pre>
     * 获得Field的指定位置的泛型参数的实际类型，如果是已经具现化的泛型，则返回具现化的类型.
     * </pre>
     *
     * @param <T> the generic type
     * @param type the type
     * @param fieldName the field name
     * @param index the index
     * @return 泛型参数的实际类型,
     *         如果没有实现ParameterizedType接口，即不支持泛型，直接返回<code>Object.class</code>
     */
    public static <T> Class<T> getFieldGenericParameterType(Class<?> type, String fieldName, int index) {
        return getFieldGenericParameterType(type, getField0(type, fieldName), index);
    }

    /**
     * <pre>
     * 获得Field的第一个泛型参数的实际类型，如果是已经具现化的泛型，则返回具现化的类型.
     * </pre>
     *
     * @param <T> the generic type
     * @param type the type
     * @param field 字段
     * @return 泛型参数的实际类型,
     *         如果没有实现ParameterizedType接口，即不支持泛型，直接返回<code>Object.class</code>
     * @see #getFieldType(Class, String)
     */
    public static <T> Class<T> getFieldGenericParameterType(Class<?> type, Field field) {
        return getFieldGenericParameterType(type, field, 0);
    }

    /**
     * <pre>
     * 获得Field的泛型参数的实际类型，如果是已经具现化的泛型，则返回具现化的类型.
     * </pre>
     *
     * @param <T> the generic type
     * @param type the type
     * @param field 字段
     * @param index 泛型参数所在索引,从0开始.
     * @return 泛型参数的实际类型,
     *         如果没有实现ParameterizedType接口，即不支持泛型，直接返回<code>Object.class</code>
     */
    public static <T> Class<T> getFieldGenericParameterType(Class<?> type, Field field, int index) {
        return getGenericParameterType(type, field.getGenericType(), index);
    }

    /**
     * <pre>
     * 获得Field的所有泛型参数的实际类型列表，如果是已经具现化的泛型，则返回具现化的类型.
     * </pre>
     *
     * @param type the type
     * @param fieldName the field name
     * @return 泛型参数的实际类型,
     *         如果没有实现ParameterizedType接口，即不支持泛型，直接返回<code>Object.class</code>
     */
    public static List<Class<?>> getFieldGenericParameterTypes(Class<?> type, String fieldName) {
        return getFieldGenericParameterTypes(type, getField0(type, fieldName));
    }

    /**
     * <pre>
     * 获得Field的所有泛型参数的实际类型列表，如果是已经具现化的泛型，则返回具现化的类型.
     * </pre>
     *
     * @param type the type
     * @param field 字段
     * @return 泛型参数的实际类型,
     *         如果没有实现ParameterizedType接口，即不支持泛型，直接返回<code>Object.class</code>
     */
    public static List<Class<?>> getFieldGenericParameterTypes(Class<?> type, Field field) {
        return getGenericParameterTypes(type, field.getGenericType());
    }

    @SuppressWarnings("unchecked")
    private static <T> Class<T> getGenericInstanceType(Class<?> clazz, TypeVariable<?> typeVariable) {
        if (typeVariable.getGenericDeclaration() instanceof Class) {
            return getGenericInstanceType(clazz, (Class<?>) typeVariable.getGenericDeclaration(),
                typeVariable.getName());
        } else if (typeVariable.getGenericDeclaration() instanceof Method) {
            return (Class<T>) typeVariable.getBounds()[0];
        } else if (typeVariable.getGenericDeclaration() instanceof Constructor) {
            return (Class<T>) typeVariable.getBounds()[0];
        } else {
            return (Class<T>) Object.class;
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> Class<T> getGenericInstanceType(Class<?> clazz, Class<?> genericInstanceType,
        String genericName) {
        if (clazz == Object.class) {
            return (Class<T>) Object.class;
        }
        if (clazz == genericInstanceType) {
            for (TypeVariable<?> tv : clazz.getTypeParameters()) {
                if (genericName.equals(tv.getName())) {
                    return (Class<T>) tv.getBounds()[0];
                }
            }
        }
        Type result = null;
        Map<String, Type> genericMap = null;
        if (clazz.getSuperclass() == genericInstanceType) {
            genericMap = ClassUtils.getSuperClassGenericTypeMap(clazz);
            result = genericMap.get(genericName);
        }
        if (result == null) {
            genericMap = ClassUtils.getInterfaceGenericTypeMap(clazz).get(genericInstanceType);
            if (genericMap != null) {
                result = genericMap.get(genericName);
            }
        }
        if (result == null) {
            return getGenericInstanceType(clazz.getSuperclass(), genericInstanceType, genericName);
        }
        return (Class<T>) ClassUtils.forName(result.getTypeName());
    }

    @SuppressWarnings("unchecked")
    private static <T> Class<T> getGenericInstanceType(Class<?> clazz, Type type, Class<?> defaultType) {
        if (type instanceof TypeVariable) {
            TypeVariable<?> tv = (TypeVariable<?>) type;
            if (tv.getGenericDeclaration() instanceof Class) {
                return getGenericInstanceType(clazz, (Class<?>) tv.getGenericDeclaration(), tv.getName());
                //                Type result = null;
                //                Map<String, Type> genericMap = ClassUtils.getSuperClassGenericTypeMap(clazz);
                //                result = genericMap.get(tv.getName());
                //                if (result == null) {
                //                    genericMap = ClassUtils.getInterfaceGenericTypeMap(clazz).get(tv.getGenericDeclaration());
                //                    if (genericMap != null) {
                //                        result = genericMap.get(tv.getName());
                //                    }
                //                }
                //                if (result == null) {
                //                    return getGenericInstanceType(clazz.getSuperclass(), (Class<?>) tv.getGenericDeclaration());
                //                }
                //                return (Class<T>) ClassUtils.forName(result.getTypeName());
            } else if (tv.getGenericDeclaration() instanceof Method) {
                // <N> N getName()
                // <N> void setName(N n)
                // 这种情况
                return (Class<T>) tv.getBounds()[0];
            } else if (tv.getGenericDeclaration() instanceof Constructor) {
                return (Class<T>) tv.getBounds()[0];
            }
        } else if (type instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) type;
            return (Class<T>) pt.getRawType();
        } else if (type instanceof Class) {
            return (Class<T>) type;
        }
        return (Class<T>) defaultType;
    }

    private static <T> Class<T> getGenericInstanceType(Class<?> clazz, Type type) {
        return getGenericInstanceType(clazz, type, Object.class);
    }

    @SuppressWarnings("unchecked")
    private static <T> Class<T> getGenericParameterType(Class<?> clazz, Type type, int index) {
        if (type instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) type;
            Type[] fieldArgTypes = pt.getActualTypeArguments();
            if (index >= fieldArgTypes.length || index < 0) {
                throw new IllegalArgumentException(
                    "你输入的索引" + (index < 0 ? "不能小于0" : "超出了泛型参数的总数" + fieldArgTypes.length));
            }
            Type genericType = fieldArgTypes[index];
            if (genericType instanceof Class) {
                return (Class<T>) genericType;
            } else if (genericType instanceof WildcardType) {
                WildcardType w = (WildcardType) genericType;
                if (Lang.isNotEmpty(w.getLowerBounds())) {
                    return (Class<T>) w.getLowerBounds()[0];
                } else if (Lang.isNotEmpty(w.getUpperBounds())) {
                    return (Class<T>) w.getUpperBounds()[0];
                }
            } else if (genericType instanceof TypeVariable) {
                TypeVariable<?> tv = (TypeVariable<?>) genericType;
                return getGenericInstanceType(clazz, tv);
            }
        }
        //        else if (type instanceof TypeVariable) {
        //            TypeVariable<?> tv = (TypeVariable<?>) type;
        //            return getGenericInstanceType(clazz, tv);
        //        }
        //        else if (type instanceof Class) {
        //            return (Class<T>) type;
        //        }
        return (Class<T>) Object.class;
    }

    private static List<Class<?>> getGenericParameterTypes(Class<?> clazz, Type type) {
        List<Class<?>> results = new ArrayList<>();
        if (type instanceof ParameterizedType) {
            ParameterizedType aType = (ParameterizedType) type;
            Type[] fieldArgTypes = aType.getActualTypeArguments();
            for (Type genericType : fieldArgTypes) {
                if (genericType instanceof Class) {
                    results.add((Class<?>) genericType);
                } else if (genericType instanceof WildcardType) {
                    WildcardType w = (WildcardType) genericType;
                    if (Lang.isNotEmpty(w.getLowerBounds())) {
                        results.add((Class<?>) w.getLowerBounds()[0]);
                    } else if (Lang.isNotEmpty(w.getUpperBounds())) {
                        results.add((Class<?>) w.getUpperBounds()[0]);
                    }
                } else if (genericType instanceof TypeVariable) {
                    results.add(getGenericInstanceType(clazz, (TypeVariable<?>) genericType));
                } else if (genericType instanceof ParameterizedType) {
                    ParameterizedType gType = (ParameterizedType) type;
                    results.add((Class<?>) gType.getRawType());
                }
            }
        }
        return results;
    }

    private static <T> GenericType<T> getGenericType(Class<?> clazz, ParameterizedType pType, int index) {
        Type[] fieldArgTypes = pType.getActualTypeArguments();
        if (index >= fieldArgTypes.length || index < 0) {
            throw new IllegalArgumentException("你输入的索引" + (index < 0 ? "不能小于0" : "超出了泛型参数的总数" + fieldArgTypes.length));
        }
        return craeteGenericType(fieldArgTypes[index], clazz, pType);
    }

    private static <T> GenericType<T> getGenericType(Class<?> clazz, Type type, int index) {
        if (type instanceof ParameterizedType) {
            ParameterizedType pType = (ParameterizedType) type;
            return getGenericType(clazz, pType, index);
        }
        return null;
    }

    //    private static GenericType<?> getGenericType(Class<?> clazz, Type type) {
    //        if (type instanceof ParameterizedType) {
    //            ParameterizedType pType = (ParameterizedType) type;
    //            GenericType<?> gt = new GenericType<>((Class<?>) pType.getRawType());
    //            setParameterizedType(clazz, pType, gt);
    //            return gt;
    //        }
    //        return new GenericType<>((Class<?>) type);
    //    }

    private static List<GenericType<?>> getGenericTypes(Class<?> clazz, Type type) {
        if (type instanceof ParameterizedType) {
            ParameterizedType pType = (ParameterizedType) type;
            GenericType<?> gt = new GenericType<>((Class<?>) pType.getRawType());
            setParameterizedType(clazz, pType, gt);
            return gt.getGenericTypes();
        }
        return new ArrayList<>();
    }

    private static void setParameterizedType(Class<?> clazz, ParameterizedType parameterizedType, GenericType<?> gt) {
        Type[] fieldArgTypes = parameterizedType.getActualTypeArguments();
        for (Type fieldArgType : fieldArgTypes) {
            addGenericType(fieldArgType, clazz, parameterizedType, gt);
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> GenericType<T> craeteGenericType(Type fieldArgType, Class<?> clazz,
        ParameterizedType parameterizedType) {
        if (fieldArgType instanceof Class) {
            return new GenericType<>((Class<T>) fieldArgType);
        } else if (fieldArgType instanceof WildcardType) {
            WildcardType w = (WildcardType) fieldArgType;
            if (Lang.isNotEmpty(w.getLowerBounds())) {
                return new GenericType<>((Class<T>) w.getLowerBounds()[0]);
            } else if (Lang.isNotEmpty(w.getUpperBounds())) {
                return new GenericType<>((Class<T>) w.getUpperBounds()[0]);
            }
        } else if (fieldArgType instanceof TypeVariable) {
            return new GenericType<>(getGenericInstanceType(clazz, (TypeVariable<?>) fieldArgType));
        } else if (fieldArgType instanceof ParameterizedType) {
            ParameterizedType pType = (ParameterizedType) fieldArgType;
            GenericType<T> genericType = new GenericType<>((Class<T>) pType.getRawType());
            setParameterizedType(clazz, pType, genericType);
            return genericType;
        }
        return null;
    }

    private static void addGenericType(Type fieldArgType, Class<?> clazz, ParameterizedType parameterizedType,
        GenericType<?> gt) {
        GenericType<?> genericType = craeteGenericType(fieldArgType, clazz, parameterizedType);
        if (genericType != null) {
            gt.addGenericType(genericType);
        }
        //        if (fieldArgType instanceof Class) {
        //            gt.addGenericType(new GenericType<>((Class<?>) fieldArgType));
        //        } else if (fieldArgType instanceof WildcardType) {
        //            WildcardType w = (WildcardType) fieldArgType;
        //            if (Lang.isNotEmpty(w.getLowerBounds())) {
        //                gt.addGenericType(new GenericType<>((Class<?>) w.getLowerBounds()[0]));
        //            } else if (Lang.isNotEmpty(w.getUpperBounds())) {
        //                gt.addGenericType(new GenericType<>((Class<?>) w.getUpperBounds()[0]));
        //            }
        //        } else if (fieldArgType instanceof TypeVariable) {
        //            gt.addGenericType(new GenericType<>(getGenericInstanceType(clazz, (TypeVariable<?>) fieldArgType)));
        //        } else if (fieldArgType instanceof ParameterizedType) {
        //            ParameterizedType pType = (ParameterizedType) fieldArgType;
        //            GenericType<?> genericType = new GenericType<>((Class<?>) pType.getRawType());
        //            gt.addGenericType(genericType);
        //            setParameterizedType(clazz, parameterizedType, genericType);
        //        }
    }

    // ********************************************************************
    //    实例化对象
    // ********************************************************************

    /**
     * 实例化.
     *
     * @param <T> 泛型
     * @param clazz 类型
     * @return 对象
     */
    public static <T> T newInstance(Class<T> clazz) {
        AssertIllegalArgument.isNotNull(clazz, "Class<T> clazz");
        AssertIllegalArgument.isNotInterface(clazz);
        //        if (clazz.isInterface()) {
        //            throw new IllegalArgumentException(
        //                    Str.format("传入 {0} 是接口 不能实例化", new Object[] { clazz.getName() }));
        //        }
        try {
            return clazz.newInstance();
        } catch (InstantiationException e) {
            LOGGER.debug(ExceptionUtils.getStackTrace(e));
            throw new ReflectException(Str.format("{0} 构造器是否为私有", new Object[] { clazz.getName() }));
        } catch (IllegalAccessException e) {
            LOGGER.debug(ExceptionUtils.getStackTrace(e));
            throw new ReflectException(Str.format("{0} 构造器参数不匹配", new Object[] { clazz.getName() }));
        }
    }

    /**
     * 实例化.
     *
     * @param <T> 泛型
     * @param clazz 类型
     * @param args 参数数组
     * @return 对象
     */
    public static <T> T newInstance(Class<T> clazz, Object... args) {
        AssertIllegalArgument.isNotNull(clazz, "Class<T> clazz");
        if (Lang.isNotEmpty(args)) {
            Constructor<T> matchConstructor = matchConstructor(clazz, args);
            return newInstance(matchConstructor, args);
        } else {
            return newInstance(clazz);
        }
    }

    /**
     * 实例化.
     *
     * @param <T> 泛型
     * @param constructor 构造器
     * @param args 参数数组
     * @return 对象
     */
    public static <T> T newInstance(Constructor<T> constructor, Object... args) {
        AssertIllegalArgument.isNotNull(constructor, "Constructor<T> constructor");
        if (!Modifier.isPublic(constructor.getModifiers())
            || !Modifier.isPublic(constructor.getDeclaringClass().getModifiers())) {
            constructor.setAccessible(true);
        }
        try {
            return constructor.newInstance(args);
        } catch (IllegalArgumentException e) {
            LOGGER.debug(ExceptionUtils.getStackTrace(e));
            throw new ReflectException(Str.format(" {0} 是否定义成抽象类了 不能实例化", constructor.getDeclaringClass().getName()));
        } catch (InstantiationException e) {
            LOGGER.debug(ExceptionUtils.getStackTrace(e));
            throw new ReflectException(Str.format("{0} 构造器是否为私有", constructor.getDeclaringClass().getName()));
        } catch (IllegalAccessException e) {
            LOGGER.debug(ExceptionUtils.getStackTrace(e));
            throw new ReflectException(Str.format("{0} 构造器参数不匹配", constructor.getDeclaringClass().getName()));
        } catch (InvocationTargetException e) {
            LOGGER.debug(ExceptionUtils.getStackTrace(e));
            throw new ReflectException(Str.format("{0} 构造器抛出异常", constructor.getDeclaringClass().getName()));
        }
    }

    private static <E extends Executable> E matchMethod(Class<?> type, String name, Object[] args) {
        List<Executable> matchs = new ArrayList<>();
        for (Method method : type.getMethods()) {
            if (method.getName().equals(name) && method.getParameterCount() == args.length) {
                matchs.add(method);
            }
        }
        return match(matchs, type, args, true);
    }

    private static <E extends Executable> E matchConstructor(Class<?> type, Object[] args) {
        List<Executable> matchs = new ArrayList<>();
        for (Constructor<?> constructor : type.getConstructors()) {
            if (constructor.getParameterCount() == args.length) {
                matchs.add(constructor);
            }
        }
        return match(matchs, type, args, false);
    }

    @SuppressWarnings("unchecked")
    private static <E extends Executable> E match(List<Executable> matchs, Class<?> type, Object[] args,
        boolean isMethod) {
        Executable matchExecutable = null;
        if (!matchs.isEmpty()) {
            Class<?>[] arguTypes = new Class<?>[args.length];
            for (int i = 0; i < args.length; i++) {
                arguTypes[i] = args[i].getClass();
            }
            for (Executable executable : matchs) {
                boolean matchAllParamType = true;
                Class<?>[] paramTypes = executable.getParameterTypes();
                for (int i = 0; i < arguTypes.length; i++) {
                    if (paramTypes[i] != arguTypes[i]) {
                        matchAllParamType = false;
                        break;
                    }
                }
                if (matchAllParamType) {
                    matchExecutable = executable;
                    break;
                }
            }
            if (matchExecutable == null) {
                for (Executable executable : matchs) {
                    boolean matchAllParamType = true;
                    Class<?>[] paramTypes = executable.getParameterTypes();
                    for (int i = 0; i < arguTypes.length; i++) {
                        if (!ClassUtils.isParent(paramTypes[i], arguTypes[i])) {
                            matchAllParamType = false;
                            break;
                        }
                    }
                    if (matchAllParamType) {
                        matchExecutable = executable;
                        break;
                    }
                }
            }
        }
        if (matchExecutable == null) {
            throw new ReflectException(Str.format("{0}({1}) 此{2}不存在",
                new Object[] { type.getName(), Arrays.asList(args).toString(), isMethod ? "方法" : "构造器" }));
        }
        return (E) matchExecutable;
    }

    // ********************************************************************
    //    查找
    // ********************************************************************

    /**
     * <p>
     * 查找并返回第一个符合条件的Field. 如果没有则返回null.
     * </p>
     *
     * @param type 匹配条件
     * @param matcher 匹配条件
     * @return 第一个符合条件Field
     */
    public static Field findField(Class<?> type, FieldMatcher matcher) {
        if (type != null) {
            for (Field field : type.getDeclaredFields()) {
                if (matcher.test(field)) {
                    return field;
                }
            }
        }
        return null;
    }

    /**
     * <p>
     * 查找并返回所有符合条件Field的集合. 如果没有则返回一个长度为0的集合.
     * </p>
     *
     * @param type 类型
     * @param matcher 匹配条件
     * @return 所有符合条件Field的集合
     */
    public static Collection<Field> findFields(Class<?> type, FieldMatcher matcher) {
        Collection<Field> fields = new ArrayList<>();
        if (type != null) {
            for (Field field : type.getDeclaredFields()) {
                if (matcher.test(field)) {
                    fields.add(field);
                }
            }
        }
        return fields;
    }

    /**
     * <p>
     * 查找并返回第一个符合条件的Method. 如果没有则返回null.
     * </p>
     *
     * @param type 类型
     * @param matcher 匹配条件
     * @return 第一个符合条件Method
     */
    public static Method findMethod(Class<?> type, MethodMatcher matcher) {
        if (type != null) {
            for (Method method : type.getDeclaredMethods()) {
                if (matcher.test(method)) {
                    return method;
                }
            }
        }
        return null;
    }

    /**
     * <p>
     * 查找并返回所有符合条件Method的集合. 如果没有则返回一个长度为0的集合.
     * </p>
     *
     * @param type 类型
     * @param matcher 匹配条件
     * @return 所有符合条件Method的集合
     */
    public static Collection<Method> findMethods(Class<?> type, MethodMatcher matcher) {
        Collection<Method> methods = new ArrayList<>();
        if (type != null) {
            for (Method method : type.getDeclaredMethods()) {
                if (matcher.test(method)) {
                    methods.add(method);
                }
            }
        }
        return methods;
    }

    /**
     * Gets the primitive type.
     *
     * @param primitiveTypeName the primitive type name
     * @return the primitive type if match, else null
     */
    public static Class<?> getPrimitiveType(String primitiveTypeName) {
        return getPrimitiveType(primitiveTypeName, false);
    }

    /**
     * Gets the primitive type.
     *
     * @param primitiveTypeName the primitive type name
     * @param useHashCodeOnly use type name hash code for swith key when match primitive type only.
     *        hash code conflicts may occur.
     * @return the primitive type
     */
    public static Class<?> getPrimitiveType(String primitiveTypeName, boolean useHashCodeOnly) {
        if (useHashCodeOnly) {
            return getPrimitiveTypeUseHashCodeOnly(primitiveTypeName);
        }
        return getPrimitiveTypeSwitch(primitiveTypeName);
    }

    /**
     * Gets a function for get the primitive type.
     *
     * @param useHashCodeOnly use type name hash code for swith key when match primitive type only.
     *        hash code conflicts may occur.
     * @return the function to get primitive type if match, else null
     */
    public static Function<String, Class<?>> getPrimitiveTypeFunction(boolean useHashCodeOnly) {
        if (useHashCodeOnly) {
            return ClassUtils::getPrimitiveTypeUseHashCodeOnly;
        }
        return ClassUtils::getPrimitiveTypeSwitch;
    }

    private static Class<?> getPrimitiveTypeUseHashCodeOnly(String primitiveTypeName) {
        switch (primitiveTypeName.hashCode()) {
            case TypeNames.BOOL_CODE:
                return Boolean.TYPE;
            case TypeNames.BYTE_CODE:
                return Byte.TYPE;
            case TypeNames.CHAR_CODE:
                return Character.TYPE;
            case TypeNames.SHORT_CODE:
                return Short.TYPE;
            case TypeNames.INT_CODE:
                return Integer.TYPE;
            case TypeNames.LONG_CODE:
                return Long.TYPE;
            case TypeNames.FLOAT_CODE:
                return Float.TYPE;
            case TypeNames.DOUBLE_CODE:
                return Double.TYPE;
            case TypeNames.VOID_CODE:
                return Void.TYPE;
            default:
                return null;
        }
    }

    private static Class<?> getPrimitiveTypeSwitch(String primitiveTypeName) {
        switch (primitiveTypeName) {
            case TypeNames.BOOL_NAME:
                return Boolean.TYPE;
            case TypeNames.BYTE_NAME:
                return Byte.TYPE;
            case TypeNames.CHAR_NAME:
                return Character.TYPE;
            case TypeNames.SHORT_NAME:
                return Short.TYPE;
            case TypeNames.INT_NAME:
                return Integer.TYPE;
            case TypeNames.LONG_NAME:
                return Long.TYPE;
            case TypeNames.FLOAT_NAME:
                return Float.TYPE;
            case TypeNames.DOUBLE_NAME:
                return Double.TYPE;
            case TypeNames.VOID_NAME:
                return Void.TYPE;
            default:
                return null;
        }
    }

    /**
     * Gets the primitive wrapped.
     *
     * @param type the type
     * @return the primitive wrapped
     */
    public static Class<?> getPrimitiveWrapped(Class<?> type) {
        return getPrimitiveWrapped(type, false);
    }

    /**
     * Gets the primitive wrapped.
     *
     * @param type the type
     * @param useHashCodeOnly use type name hash code for swith key when match type only.
     *        hash code conflicts may occur.
     * @return the primitive wrapped if match, else null
     */
    public static Class<?> getPrimitiveWrapped(Class<?> type, boolean useHashCodeOnly) {
        if (useHashCodeOnly) {
            return getPrimitiveWrappedUseHashCodeOnly(type);
        }
        return getPrimitiveWrappedSwitch(type);
    }

    /**
     * Gets a function for get the primitive wrapped.
     *
     * @param useHashCodeOnly use type name hash code for swith key when match type only.
     *        hash code conflicts may occur.
     * @return the function to get primitive type if match, else null
     */
    public static Function<Class<?>, Class<?>> getPrimitiveWrappedFunction(boolean useHashCodeOnly) {
        if (useHashCodeOnly) {
            return ClassUtils::getPrimitiveWrappedUseHashCodeOnly;
        }
        return ClassUtils::getPrimitiveWrappedSwitch;
    }

    private static Class<?> getPrimitiveWrappedUseHashCodeOnly(Class<?> type) {
        switch (type.getName().hashCode()) {
            case TypeNames.BOOL_CODE:
                return Boolean.class;
            case TypeNames.BYTE_CODE:
                return Byte.class;
            case TypeNames.CHAR_CODE:
                return Character.class;
            case TypeNames.SHORT_CODE:
                return Short.class;
            case TypeNames.INT_CODE:
                return Integer.class;
            case TypeNames.LONG_CODE:
                return Long.class;
            case TypeNames.FLOAT_CODE:
                return Float.class;
            case TypeNames.DOUBLE_CODE:
                return Double.class;
            case TypeNames.VOID_CODE:
                return Void.class;
            default:
                return null;
        }
    }

    private static Class<?> getPrimitiveWrappedSwitch(Class<?> type) {
        switch (type.getName()) {
            case TypeNames.BOOL_NAME:
                return Boolean.class;
            case TypeNames.BYTE_NAME:
                return Byte.class;
            case TypeNames.CHAR_NAME:
                return Character.class;
            case TypeNames.SHORT_NAME:
                return Short.class;
            case TypeNames.INT_NAME:
                return Integer.class;
            case TypeNames.LONG_NAME:
                return Long.class;
            case TypeNames.FLOAT_NAME:
                return Float.class;
            case TypeNames.DOUBLE_NAME:
                return Double.class;
            case TypeNames.VOID_NAME:
                return Void.class;
            default:
                return null;
        }
    }

    /**
     * Gets the primitive wrapped.
     *
     * @param type the type
     * @return the primitive class value method name if match, else null
     */
    public static String getPrimitiveClassValueMethodName(Class<?> type) {
        return getPrimitiveClassValueMethodName(type, false);
    }

    /**
     * Gets the primitive wrapped.
     *
     * @param type the type
     * @param useHashCodeOnly use type name hash code for swith key when match primitive type only.
     *        hash code conflicts may occur.
     * @return the primitive class value method name if match, else null
     */
    public static String getPrimitiveClassValueMethodName(Class<?> type, boolean useHashCodeOnly) {
        if (useHashCodeOnly) {
            return getPrimitiveClassValueMethodNameUseHashCodeOnly(type);
        }
        return getPrimitiveClassValueMethodNameSwitch(type);
    }

    /**
     * Gets the primitive class value method name function.
     *
     * @param useHashCodeOnly use type name hash code for swith key when match primitive type only.
     *        hash code conflicts may occur.
     * @return the function to get primitive class value method name
     */
    public static Function<Class<?>, String> getPrimitiveClassValueMethodNameFunction(boolean useHashCodeOnly) {
        if (useHashCodeOnly) {
            return ClassUtils::getPrimitiveClassValueMethodNameUseHashCodeOnly;
        }
        return ClassUtils::getPrimitiveClassValueMethodNameSwitch;
    }

    private static String getPrimitiveClassValueMethodNameUseHashCodeOnly(Class<?> type) {
        switch (type.getName().hashCode()) {
            case TypeNames.BOOL_CODE:
            case TypeNames.BOOLEAN_CODE:
                return "booleanValue";
            case TypeNames.BYTE_CODE:
            case TypeNames.BYTE_TYPE_CODE:
                return "byteValue";
            case TypeNames.CHAR_CODE:
            case TypeNames.CHARACTER_CODE:
                return "charValue";
            case TypeNames.SHORT_CODE:
            case TypeNames.SHORT_TYPE_CODE:
                return "shortValue";
            case TypeNames.INT_CODE:
            case TypeNames.INTEGER_CODE:
                return "intValue";
            case TypeNames.LONG_CODE:
            case TypeNames.LONG_TYPE_CODE:
                return "longValue";
            case TypeNames.FLOAT_CODE:
            case TypeNames.FLOAT_TYPE_CODE:
                return "floatValue";
            case TypeNames.DOUBLE_CODE:
            case TypeNames.DOUBLE_TYPE_CODE:
                return "doubleValue";
            default:
                return null;
        }
    }

    private static String getPrimitiveClassValueMethodNameSwitch(Class<?> type) {
        switch (type.getName()) {
            case TypeNames.BOOL_NAME:
            case TypeNames.BOOLEAN_NAME:
                return "booleanValue";
            case TypeNames.BYTE_NAME:
            case TypeNames.BYTE_TYPE_NAME:
                return "byteValue";
            case TypeNames.CHAR_NAME:
            case TypeNames.CHARACTER_NAME:
                return "charValue";
            case TypeNames.SHORT_NAME:
            case TypeNames.SHORT_TYPE_NAME:
                return "shortValue";
            case TypeNames.INT_NAME:
            case TypeNames.INTEGER_NAME:
                return "intValue";
            case TypeNames.LONG_NAME:
            case TypeNames.LONG_TYPE_NAME:
                return "longValue";
            case TypeNames.FLOAT_NAME:
            case TypeNames.FLOAT_TYPE_NAME:
                return "floatValue";
            case TypeNames.DOUBLE_NAME:
            case TypeNames.DOUBLE_TYPE_NAME:
                return "doubleValue";
            default:
                return null;
        }
    }

    /**
     * get the obj class.
     *
     * @param <T> the generic type
     * @param obj the obj
     * @return the class type. null when obj is null.
     */
    @SuppressWarnings("unchecked")
    public static <T> Class<T> getClass(T obj) {
        if (obj == null) {
            return null;
        }
        return (Class<T>) obj.getClass();
    }

    /**
     * getClassType.
     *
     * @param <T> the generic type
     * @param type the type
     * @return the class type
     */
    @SuppressWarnings("unchecked")
    public static <T> Class<T> getClassType(Type type) {
        if (type instanceof ParameterizedType) {
            return getClassType(((ParameterizedType) type).getRawType());
        } else if (!(type instanceof Class)) {
            return (Class<T>) Object.class;
        }
        return (Class<T>) type;
    }

    /**
     * getRawType.
     *
     * @param type type
     * @return raw type
     */
    public static Type getRawType(Type type) {
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            return parameterizedType.getRawType();
        } else {
            return type;
        }
    }

    /**
     * java.lang.Class utils
     *
     * @author zhongj
     */
    public static final class Types {

        private Types() {
        }

        /**
         * 通过反射,获得指定类的父类的泛型参数的实际类型. 如BuyerServiceBean extends
         * DaoSupport&lt;Buyer&gt;
         *
         * @param <T> 泛型
         * @param clazz clazz 需要反射的类,该类必须继承范型父类
         * @param index 泛型参数所在索引,从0开始.
         * @return 范型参数的实际类型, 如果没有实现ParameterizedType接口，即不支持泛型，直接返回null
         */
        public static <T> GenericType<T> getSuperClassGenericType(Class<?> clazz, int index) {
            return getGenericType(clazz, clazz.getGenericSuperclass(), index);
        }

        /**
         * 通过反射,获得指定类的父类的第一个泛型参数的实际类型. 如BuyerServiceBean extends
         * DaoSupport&lt;Buyer&gt;
         *
         * @param <T> 泛型
         * @param clazz clazz 需要反射的类,该类必须继承泛型父类
         * @return 泛型参数的实际类型, 如果没有实现ParameterizedType接口，即不支持泛型，所以直接返回
         *         <code>Object.class</code>
         */
        public static <T> GenericType<T> getSuperClassGenericType(Class<?> clazz) {
            return getSuperClassGenericType(clazz, 0);
        }

        /**
         * 获得指定类实现的接口的泛型参数的实际类型. 如BuyerServiceBean implements
         * DaoSupport&lt;Buyer&gt;
         *
         * @param <T> 泛型
         * @param clazz clazz 需要反射的类,该类必须继承范型父类
         * @param index 泛型参数所在索引,从0开始.
         * @param interfaceType 实现的接口类型
         * @return 范型参数的实际类型, 如果没有实现ParameterizedType接口，即不支持泛型，直接返回null
         */
        public static <T> GenericType<T> getInterfaceGenericType(Class<?> clazz, int index, Class<?> interfaceType) {
            for (Type type : clazz.getGenericInterfaces()) {
                if (type instanceof ParameterizedType) {
                    ParameterizedType parameterizedType = (ParameterizedType) type;
                    if (parameterizedType.getRawType() == interfaceType) {
                        return getGenericType(clazz, type, index);
                    }
                }
            }
            return null;
        }

        /**
         * 获得指定类实现的接口的第一个泛型参数的实际类型. 如BuyerServiceBean implements
         * DaoSupport&lt;Buyer&gt;
         *
         * @param <T> 泛型
         * @param clazz clazz 需要反射的类,该类必须继承泛型父类
         * @param interfaceType 实现的接口类型
         * @return 泛型参数的实际类型, 如果没有实现ParameterizedType接口，即不支持泛型，所以直接返回null
         */
        public static <T> GenericType<T> getInterfaceGenericType(Class<?> clazz, Class<?> interfaceType) {
            return getInterfaceGenericType(clazz, 0, interfaceType);
        }
    }

    /**
     * java.lang.reflect.Field utils
     *
     * @author zhongj
     */
    public static final class Fields {

        private Fields() {
        }

        /**
         * <pre>
         * 获得Field的第一个泛型参数的实际类型，如果是已经具现化的泛型，则返回具现化的类型.
         * </pre>
         *
         * @param <T> the generic type
         * @param type the type
         * @param fieldName the field name
         * @return 泛型参数的实际类型, 如果没有实现ParameterizedType接口，即不支持泛型，直接返回null
         */
        public static <T> GenericType<T> getGenericParameterType(Class<?> type, String fieldName) {
            return getGenericParameterType(type, getField0(type, fieldName));
        }

        /**
         * <pre>
         * 获得Field的指定位置的泛型参数的实际类型，如果是已经具现化的泛型，则返回具现化的类型.
         * </pre>
         *
         * @param <T> the generic type
         * @param type the type
         * @param fieldName the field name
         * @param index the index
         * @return 泛型参数的实际类型, 如果没有实现ParameterizedType接口，即不支持泛型，直接返回null
         */
        public static <T> GenericType<T> getGenericParameterType(Class<?> type, String fieldName, int index) {
            return getGenericParameterType(type, getField0(type, fieldName), index);
        }

        /**
         * <pre>
         * 获得Field的第一个泛型参数的实际类型，如果是已经具现化的泛型，则返回具现化的类型.
         * </pre>
         *
         * @param <T> the generic type
         * @param type the type
         * @param field 字段
         * @return 泛型参数的实际类型, 如果没有实现ParameterizedType接口，即不支持泛型，直接返回null
         * @see #getFieldType(Class, String)
         */
        public static <T> GenericType<T> getGenericParameterType(Class<?> type, Field field) {
            return getGenericParameterType(type, field, 0);
        }

        /**
         * <pre>
         * 获得Field的泛型参数的实际类型，如果是已经具现化的泛型，则返回具现化的类型.
         * </pre>
         *
         * @param <T> the generic type
         * @param type the type
         * @param field 字段
         * @param index 泛型参数所在索引,从0开始.
         * @return 泛型参数的实际类型, 如果没有实现ParameterizedType接口，即不支持泛型，直接返回null
         */
        public static <T> GenericType<T> getGenericParameterType(Class<?> type, Field field, int index) {
            return getGenericType(type, field.getGenericType(), index);
        }

        /**
         * <pre>
         * 获得Field的所有泛型参数的实际类型列表，如果是已经具现化的泛型，则返回具现化的类型.
         * </pre>
         *
         * @param type the type
         * @param fieldName the field name
         * @return 泛型参数的实际类型, 如果没有实现ParameterizedType接口，即不支持泛型，直接返回empty list
         */
        public static List<GenericType<?>> getGenericParameterTypes(Class<?> type, String fieldName) {
            return getGenericParameterTypes(type, getField0(type, fieldName));
        }

        /**
         * <pre>
         * 获得Field的所有泛型参数的实际类型列表，如果是已经具现化的泛型，则返回具现化的类型.
         * </pre>
         *
         * @param type the type
         * @param field 字段
         * @return 泛型参数的实际类型, 如果没有实现ParameterizedType接口，即不支持泛型，直接返回empty list
         */
        public static List<GenericType<?>> getGenericParameterTypes(Class<?> type, Field field) {
            return getGenericTypes(type, field.getGenericType());
        }
    }

    /**
     * java.lang.reflect.Method utils
     *
     * @author zhongj
     */
    public static final class Methods {

        private Methods() {
        }

        /**
         * <pre>
         * 获得方法返回值第一个泛型参数的实际类型. 如: public Map&lt;String, Buyer&gt; getNames(){}
         * 例：
         * <code>
         *  public class StringLongMap extends HashMap&lt;String,Long&gt; {
         *  }
         *
         *  ClassUtils.Methods.getReturnTypeGenericParameterType(StringLongMap.class, "keySet", new Class[0]) == String.class
         *  ClassUtils.Methods.getMethodReturnTypeGenericParameterType(StringLongMap.class, "values", new Class[0]) == Long.class
         *
         * </code>
         * </pre>
         *
         * @param <T> 泛型
         * @param type the type
         * @param methodName the method name
         * @param parameterTypes the parameter types
         * @return 泛型参数的实际类型, 如果没有实现ParameterizedType接口，即不支持泛型，返回null
         */
        public static <T> GenericType<T> getReturnTypeGenericParameterType(Class<?> type, String methodName,
            Class<?>... parameterTypes) {
            return getReturnTypeGenericParameterType(type, getMethod(type, methodName, parameterTypes));
        }

        /**
         * 获得方法返回值第一个泛型参数的实际类型. 如: public Map&lt;String, Buyer&gt; getNames(){}
         *
         * @param <T> 泛型
         * @param type the type
         * @param method 方法
         * @return 泛型参数的实际类型, 如果没有实现ParameterizedType接口，即不支持泛型，返回null
         * @see #getReturnTypeGenericParameterType(Class, String, Class...)
         */
        public static <T> GenericType<T> getReturnTypeGenericParameterType(Class<?> type, Method method) {
            return getReturnTypeGenericParameterType(type, method, 0);
        }

        /**
         * <pre>
         * 获得方法返回值泛型参数的实际类型. 如: public Map&lt;String, Buyer&gt; getNames(){}
         * 例：
         * <code>
         *  public class StringLongMap extends HashMap&lt;String,Long&gt; {
         *  }
         *
         *  ClassUtils.Methods.getReturnTypeGenericParameterType(StringLongMap.class, "keySet", new Class[0]) == String.class
         *  ClassUtils.Methods.getReturnTypeGenericParameterType(StringLongMap.class, "values", new Class[0]) == Long.class
         *
         *  public class Super&lt;K,V&gt; {
         *      public Map&lt;K,V&gt; map() {...}
         *  }
         *
         *  public class Sub implements Super&lt;String, Long&gt; {
         *  }
         *
         *  ClassUtils.Methods.getReturnTypeGenericParameterType(Sub.class, "map", new Class[0]) == String.class
         *  ClassUtils.Methods.getReturnTypeGenericParameterType(Sub.class, Sub.class.getMethod("map", new Class[0]), 1) == Long.class
         *
         * </code>
         * </pre>
         *
         * @param <T> 泛型
         * @param type the type
         * @param method 方法
         * @param index 泛型参数所在索引,从0开始.
         * @return 泛型参数的实际类型, 如果没有实现ParameterizedType接口，即不支持泛型，返回null
         */
        public static <T> GenericType<T> getReturnTypeGenericParameterType(Class<?> type, Method method, int index) {
            return getGenericType(type, method.getGenericReturnType(), index);
        }

        /**
         * <pre>
         * 获得方法返回对象所有泛型参数列表，如果是已经具现化的泛型，则返回具现化的类型.
         * </pre>
         *
         * @param type the type
         * @param methodName the method name
         * @param parameterTypes the parameter types
         * @return 方法返回参数类型为根的树形结构,
         */
        public static List<GenericType<?>> getReturnTypeGenericParameterTypes(Class<?> type, String methodName,
            Class<?>... parameterTypes) {
            return getReturnTypeGenericParameterTypes(type, getMethod(type, methodName, parameterTypes));
        }

        /**
         * <pre>
         * 获得方法返回对象所有泛型参数列表，如果是已经具现化的泛型，则返回具现化的类型.
         * </pre>
         *
         * @param type the type
         * @param method the method
         * @return 方法返回参数类型为根的树形结构,
         */
        public static List<GenericType<?>> getReturnTypeGenericParameterTypes(Class<?> type, Method method) {
            return getGenericTypes(type, method.getGenericReturnType());
        }

        /**
         * 获得方法输入参数第一个输入参数的第一个泛型参数的实际类型. 如: public void add(Map&lt;String,
         * Buyer&gt; maps, List&lt;String&gt; names){}
         *
         * @param <T> the generic type
         * @param type the type
         * @param methodName the method name
         * @param parameterTypes the parameter types
         * @return 输入参数的泛型参数的实际类型集合, 如果没有实现ParameterizedType接口，即不支持泛型，所以直接返回空集合
         */
        public static <T> GenericType<T> getGenericParameterType(Class<?> type, String methodName,
            Class<?>... parameterTypes) {
            return getGenericParameterType(type, getMethod(type, methodName, parameterTypes));
        }

        /**
         * 获得方法输入参数第一个输入参数的第一个泛型参数的实际类型. 如: public void add(Map&lt;String,
         * Buyer&gt; maps, List&lt;String&gt; names){}
         *
         * @param <T> the generic type
         * @param type the type
         * @param method 方法
         * @return 输入参数的泛型参数的实际类型集合, 如果没有实现ParameterizedType接口，即不支持泛型，所以直接返回空集合
         */
        public static <T> GenericType<T> getGenericParameterType(Class<?> type, Method method) {
            return getGenericParameterType(type, method, 0);
        }

        /**
         * 获得方法输入参数第index个输入参数的第一个泛型参数的实际类型. 如: public void add(Map&lt;String,
         * Buyer&gt; maps, List&lt;String&gt; names){}
         *
         * @param <T> the generic type
         * @param type the type
         * @param method 方法
         * @param index 第几个输入参数
         * @return 输入参数的泛型参数的实际类型集合, 如果没有实现ParameterizedType接口，即不支持泛型，所以直接返回空集合
         */
        public static <T> GenericType<T> getGenericParameterType(Class<?> type, Method method, int index) {
            return getGenericParameterType(type, method, index, 0);
        }

        /**
         * 获得方法输入参数第index个输入参数的所有泛型参数的实际类型. 如: public void add(Map&lt;String,
         * Buyer&gt; maps, List&lt;String&gt; names){}
         *
         * @param <T> the generic type
         * @param type the type
         * @param method 方法
         * @param paramIndex 第几个输入参数
         * @param genericTypeIndex 第几个泛型参数
         * @return 输入参数的泛型参数的实际类型集合, 如果没有实现ParameterizedType接口，即不支持泛型，所以直接返回空集合
         */
        public static <T> GenericType<T> getGenericParameterType(Class<?> type, Method method, int paramIndex,
            int genericTypeIndex) {
            Type[] genericParameterTypes = method.getGenericParameterTypes();
            if (paramIndex >= genericParameterTypes.length || paramIndex < 0) {
                throw new IllegalArgumentException(
                    "你输入的索引" + (paramIndex < 0 ? "不能小于0" : "超出了参数的总数" + genericParameterTypes.length));
            }
            return getGenericType(type, genericParameterTypes[paramIndex], genericTypeIndex);
        }

        /**
         * 获得方法所有参数的所有泛型参数的实际类型列表. 如: public void add(Map&lt;String, Buyer&gt;
         * maps, List&lt;String&gt; names){}
         *
         * @param type the type
         * @param method 方法
         * @return 输入参数的泛型参数的实际类型集合, 如果没有实现ParameterizedType接口，即不支持泛型，所以直接返回空集合
         */
        public static List<List<GenericType<?>>> getGenericParameterTypesAll(Class<?> type, Method method) {
            List<List<GenericType<?>>> list = new ArrayList<>();
            for (int i = 0; i < method.getParameters().length; i++) {
                list.add(getGenericParameterTypes(type, method, i));
            }
            return list;
        }

        /**
         * 获得方法输入参数第一个输入参数的所有泛型参数的实际类型列表. 如: public void add(Map&lt;String,
         * Buyer&gt; maps, List&lt;String&gt; names){}
         *
         * @param type the type
         * @param method 方法
         * @return 输入参数的泛型参数的实际类型集合, 如果没有实现ParameterizedType接口，即不支持泛型，所以直接返回空集合
         */
        public static List<GenericType<?>> getGenericParameterTypes(Class<?> type, Method method) {
            return getGenericParameterTypes(type, method, 0);
        }

        /**
         * 获得方法输入参数第index个输入参数的所有泛型参数的实际类型. 如: public void add(Map&lt;String,
         * Buyer&gt; maps, List&lt;String&gt; names){}
         *
         * @param type the type
         * @param method 方法
         * @param index 第几个输入参数
         * @return 输入参数的泛型参数的实际类型集合, 如果没有实现ParameterizedType接口，即不支持泛型，所以直接返回空集合
         */
        public static List<GenericType<?>> getGenericParameterTypes(Class<?> type, Method method, int index) {
            Type[] genericParameterTypes = method.getGenericParameterTypes();
            if (index >= genericParameterTypes.length || index < 0) {
                throw new IllegalArgumentException("你输入的索引" + (index < 0 ? "不能小于0" : "超出了参数的总数"));
            }
            Type genericParameterType = genericParameterTypes[index];
            return getGenericTypes(type, genericParameterType);
        }
    }
}