
/**
 * @author zhongj - yufei Jan 5, 2009
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
import java.lang.reflect.TypeVariable;
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

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.lang.matcher.FieldMatcher;
import cn.featherfly.common.lang.matcher.MethodMatcher;

/**
 * <p>
 * class处理的工具类
 * </p>
 *
 * @author zhongj
 * @since 1.0
 * @version 1.0
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
     * <p>
     * 查找指定类型
     * </p>
     *
     * @param className 类名
     * @return 指定类型
     */
    public static Class<?> forName(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 判断第一个参数是否是第二个参数的父类（父接口）
     *
     * @param parentType 父类型（包括类和接口）
     * @param childType  子类型（包括类和接口）
     * @return 第一个参数是否是第二个参数的父类（父接口）
     */
    public static boolean isParent(Class<?> parentType, Class<?> childType) {
        if (parentType == null) {
            return false;
        }
        return parentType.isAssignableFrom(childType);
    }

    /**
     * <p>
     * 返回传入类型的共同父类
     * </p>
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

    /**
     * 返回目标类型的指定名称的字段，支持多层嵌套 例如, user.address.no
     *
     * @param target    目标类型
     * @param fieldName 字段名
     * @throws NoSuchFieldException 没有找到传入字段时抛出
     * @return 目标类型的指定名称的字段，支持多层嵌套
     */
    public static Field getField(Class<?> target, String fieldName) throws NoSuchFieldException {
        if (fieldName.contains(DOT)) {
            String currentFieldName = fieldName.substring(0, fieldName.indexOf(DOT));
            String innerFieldName = fieldName.substring(fieldName.indexOf(DOT) + 1);
            return getField(target.getDeclaredField(currentFieldName).getType(), innerFieldName);
        } else {
            return target.getDeclaredField(fieldName);
        }
    }

    /**
     * 返回指定类型(objectType)的指定注解类型(annotationType), 会把父类里有被标注的字段也进行返回
     *
     * @param <A>            注解泛型
     * @param objectType     对象类型
     * @param annotationType 注解类型
     * @return 指定注解类型
     */
    public static <A extends Annotation> A getAnnotation(Class<?> objectType, Class<A> annotationType) {
        return getAnnotation(objectType, annotationType, true);
    }

    /**
     * 返回指定类型(objectType)的指定注解类型(annotationType), fromSuper 为 true
     * 时如果没有找到annotionType,则继续从父类查找
     *
     * @param <A>            注解泛型
     * @param objectType     对象类型
     * @param annotationType 注解类型
     * @param fromSuper      是否从父类型查找
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
     * 返回指定类型(objectType)被指定注解(annotationType)标注的所有字段【成员变量】(field)
     * 会把父类里有被标注的字段也进行返回
     *
     * @param <A>            注解泛型
     * @param objectType     对象类型
     * @param annotationType 注解类型
     * @return 指定类型被指定注解标注的所有字段
     */
    public static <A extends Annotation> List<Field> getAnnotatedFields(Class<?> objectType, Class<A> annotationType) {
        return getAnnotatedFields(objectType, annotationType, true);
    }

    /**
     * 返回指定类型(objectType)被指定注解(annotationType)标注的所有字段【成员变量】(field) fromSuper 为
     * true 时会把父类里有被标注的字段也进行返回，为false则只返回本类的
     *
     * @param <A>            注解泛型
     * @param objectType     对象类型
     * @param annotationType 注解类型
     * @param fromSuper      是否从父类型查找
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
     * 返回指定类型(objectType)被指定注解(annotationType)标注的公共(public)方法(method)
     *
     * @param <A>            注解类型
     * @param objectType     目标类型
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
     * 是否是set方法(以set开头)
     *
     * @param method 方法
     * @return 是否是set方法
     */
    public static boolean isSetter(Method method) {
        String set = SET;
        String name = method.getName();
        return name.startsWith(set) && !set.equals(name) && method.getParameterTypes().length == 1;
    }

    /**
     * 是否是get方法（以get或is开头）
     *
     * @param method 方法
     * @return 是否是get方法
     */
    public static boolean isGetter(Method method) {
        String get = GET;
        String is = IS;
        String name = method.getName();
        if (name.startsWith(get) && !get.equals(name) && method.getReturnType() != void.class) {
            return true;
        }
        if (name.startsWith(is) && !is.equals(name) && method.getReturnType() != void.class) {
            return true;
        }
        return false;
    }

    /**
     * 返回getter方法，包括getXxx和isXxx 没有找到返回null
     *
     * @param field 成员变量
     * @param type  类型
     * @return getter方法
     */
    public static Method getGetter(Field field, Class<?> type) {
        Method method = null;
        String fieldName = field.getName();
        String get = GET + WordUtils.upperCaseFirst(fieldName);
        try {
            method = type.getMethod(get, new Class[] {});
        } catch (Exception e) {
            LOGGER.trace("没有找到get{}方法, 使用is{}查找", field.getName(), field.getName());
            try {
                String is = IS + WordUtils.upperCaseFirst(fieldName);
                method = type.getMethod(is, new Class[] {});
            } catch (Exception e1) {
                LOGGER.trace("没有找到get{}和is{}方法", field.getName(), field.getName());
            }
        }
        return method;
    }

    /**
     * 返回setter方法 没有找到返回null
     *
     * @param field 成员变量
     * @param type  类型
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
     * 是否是cellection接口,及其子接口或实现类
     *
     * @param type 类型
     * @return 是否是cellection接口,及其子接口或实现类
     */
    public static boolean isCellection(Class<?> type) {
        return isParent(Collection.class, type);
    }

    /**
     * 是否是map接口，及其子接口或实现类
     *
     * @param type 类型
     * @return 是否是map接口，及其子接口或实现类
     */
    public static boolean isMap(Class<?> type) {
        return isParent(Map.class, type);
    }

    /**
     * <p>
     * 判断传入类型是否是抽象类
     * </p>
     *
     * @param type type
     * @return 是否是抽象类
     */
    public static boolean isAbstractClass(Class<?> type) {
        return cn.featherfly.common.lang.reflect.Modifier.ABSTRACT.isModifier(type.getModifiers());
    }

    /**
     * <p>
     * 判断传入类型是否是可实例化的类
     * </p>
     *
     * @param type type
     * @return 是否是可实例化的类
     */
    public static boolean isInstanceClass(Class<?> type) {
        return !isAbstractClass(type);
    }

    /**
     * <p>
     * Class泛型参数强制转型
     * </p>
     *
     * @param <T>        泛型
     * @param type       type
     * @param castToType castToType
     * @return 泛型Class
     */
    @SuppressWarnings("unchecked")
    public static <T> Class<T> castGenericType(Class<?> type, T castToType) {
        return (Class<T>) type;
    }

    /**
     * <p>
     * 获取指定类所在的JAR包的JAR文件.如果不是JAR文件中的返回null
     * </p>
     *
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
     *
     * @param className className
     * @return JAR文件或null
     */
    public static File getJar(String className) {
        if (LangUtils.isEmpty(className)) {
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
                    path = StringUtils.substringBetween(path, "file:/", excalmatoryMark);
                } else if (os.startsWith("Linux")) {
                    path = StringUtils.substringBetween(path, filePrefix, excalmatoryMark);
                } else {
                    path = StringUtils.substringBetween(path, filePrefix, excalmatoryMark);
                }
            } else {
                path = StringUtils.substringBefore(path, excalmatoryMark);
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
     * 转换包模式为目录模式.xx.yy.Ttt -&gt; xx/yy/Ttt
     * </p>
     *
     * @param className 类型名称
     * @return 目录模式
     */
    public static String packageToDir(String className) {
        if (LangUtils.isEmpty(className)) {
            return null;
        }
        return className.replace(".", "/");
    }

    // ********************************************************************
    //    获取泛型参数
    // ********************************************************************

    /**
     * 通过反射,获得指定类的父类的泛型参数的实际类型. 如BuyerServiceBean extends
     * DaoSupport&lt;Buyer&gt;
     *
     * @param clazz clazz 需要反射的类,该类必须继承范型父类
     * @param index 泛型参数所在索引,从0开始.
     * @param <T>   泛型
     * @return 范型参数的实际类型, 如果没有实现ParameterizedType接口，即不支持泛型，所以直接返回
     *         <code>Object.class</code>
     */
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
            throw new RuntimeException("你输入的索引" + (index < 0 ? "不能小于0" : "超出了参数的总数"));
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
     * @param clazz clazz 需要反射的类,该类必须继承泛型父类
     * @param <T>   泛型
     * @return 泛型参数的实际类型, 如果没有实现ParameterizedType接口，即不支持泛型，所以直接返回
     *         <code>Object.class</code>
     */
    public static <T> Class<T> getSuperClassGenricType(Class<?> clazz) {
        return getSuperClassGenricType(clazz, 0);
    }

    /**
     * 通过反射,获得指定类的父类的泛型参数的实际类型与父类定义泛型是的定义之间的映射关系.
     *
     * @param clazz clazz 需要反射的类,该类必须继承泛型父类
     * @return 使用父类定义泛型的参数名作为KEY,子类实例化泛型的TYPE作为VALUE作为 <code>Object.class</code>
     */
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
     * 通过反射,获得方法返回值泛型参数的实际类型. 如: public Map&lt;String, Buyer&gt; getNames(){}
     *
     * @param method 方法
     * @param index  泛型参数所在索引,从0开始.
     * @param <T>    泛型
     * @return 泛型参数的实际类型, 如果没有实现ParameterizedType接口，即不支持泛型，所以直接返回
     *         <code>Object.class</code>
     */
    @SuppressWarnings("unchecked")
    public static <T> Class<T> getMethodGenericReturnType(Method method, int index) {
        Type returnType = method.getGenericReturnType();
        if (returnType instanceof ParameterizedType) {
            ParameterizedType type = (ParameterizedType) returnType;
            Type[] typeArguments = type.getActualTypeArguments();
            if (index >= typeArguments.length || index < 0) {
                throw new RuntimeException("你输入的索引" + (index < 0 ? "不能小于0" : "超出了参数的总数"));
            }
            return (Class<T>) typeArguments[index];
        }
        return (Class<T>) Object.class;
    }

    /**
     * 通过反射,获得方法返回值第一个泛型参数的实际类型. 如: public Map&lt;String, Buyer&gt; getNames(){}
     *
     * @param method 方法
     * @param <T>    泛型
     * @return 泛型参数的实际类型, 如果没有实现ParameterizedType接口，即不支持泛型，所以直接返回
     *         <code>Object.class</code>
     */
    public static <T> Class<T> getMethodGenericReturnType(Method method) {
        return getMethodGenericReturnType(method, 0);
    }

    /**
     * 通过反射,获得方法输入参数第index个输入参数的所有泛型参数的实际类型. 如: public void add(Map&lt;String,
     * Buyer&gt; maps, List&lt;String&gt; names){}
     *
     * @param method 方法
     * @param index  第几个输入参数
     * @return 输入参数的泛型参数的实际类型集合, 如果没有实现ParameterizedType接口，即不支持泛型，所以直接返回空集合
     */
    public static List<Class<?>> getMethodGenericParameterTypes(Method method, int index) {
        List<Class<?>> results = new ArrayList<>();
        Type[] genericParameterTypes = method.getGenericParameterTypes();
        if (index >= genericParameterTypes.length || index < 0) {
            throw new RuntimeException("你输入的索引" + (index < 0 ? "不能小于0" : "超出了参数的总数"));
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
     * 通过反射,获得方法输入参数第一个输入参数的所有泛型参数的实际类型. 如: public void add(Map&lt;String,
     * Buyer&gt; maps, List&lt;String&gt; names){}
     *
     * @param method 方法
     * @return 输入参数的泛型参数的实际类型集合, 如果没有实现ParameterizedType接口，即不支持泛型，所以直接返回空集合
     */
    public static List<Class<?>> getMethodGenericParameterTypes(Method method) {
        return getMethodGenericParameterTypes(method, 0);
    }

    /**
     * 通过反射,获得Field泛型参数的实际类型. 如: public Map&lt;String, Buyer&gt; names;
     *
     * @param field 字段
     * @param index 泛型参数所在索引,从0开始.
     * @param <T>   泛型
     * @return 泛型参数的实际类型,
     *         如果没有实现ParameterizedType接口，即不支持泛型，直接返回<code>Object.class</code>
     */
    @SuppressWarnings("unchecked")
    public static <T> Class<T> getFieldGenericType(Field field, int index) {
        Type genericFieldType = field.getGenericType();
        if (genericFieldType instanceof ParameterizedType) {
            ParameterizedType aType = (ParameterizedType) genericFieldType;
            Type[] fieldArgTypes = aType.getActualTypeArguments();
            if (index >= fieldArgTypes.length || index < 0) {
                throw new RuntimeException("你输入的索引" + (index < 0 ? "不能小于0" : "超出了参数的总数"));
            }
            return (Class<T>) fieldArgTypes[index];
        }
        return (Class<T>) Object.class;
    }

    /**
     * 通过反射,获得Field泛型参数的实际类型. 如: public Map&lt;String, Buyer&gt; names;
     *
     * @param field 字段
     * @param <T>   泛型
     * @return 泛型参数的实际类型,
     *         如果没有实现ParameterizedType接口，即不支持泛型，直接返回<code>Object.class</code>
     */
    public static <T> Class<T> getFieldGenericType(Field field) {
        return getFieldGenericType(field, 0);
    }

    // ********************************************************************
    //    实例化对象
    // ********************************************************************

    /**
     * 实例化.
     *
     * @param clazz 类型
     * @param <T>   泛型
     * @return 对象
     */
    public static <T> T newInstance(Class<T> clazz) {
        AssertIllegalArgument.isNotNull(clazz, "Class<T> clazz");
        AssertIllegalArgument.isNotInterface(clazz);
        //        if (clazz.isInterface()) {
        //            throw new IllegalArgumentException(
        //                    StringUtils.format("传入 [#1] 是接口 不能实例化", new String[] { clazz.getName() }));
        //        }
        try {
            return clazz.newInstance();
        } catch (InstantiationException e) {
            LOGGER.debug(ExceptionUtils.getStackTrace(e));
            throw new RuntimeException(StringUtils.format("[#1] 构造器是否为私有", new String[] { clazz.getName() }));
        } catch (IllegalAccessException e) {
            LOGGER.debug(ExceptionUtils.getStackTrace(e));
            throw new RuntimeException(StringUtils.format("构造器参数不匹配", new String[] { clazz.getName() }));
        }
    }

    /**
     * 实例化.
     *
     * @param clazz 类型
     * @param args  参数数组
     * @param <T>   泛型
     * @return 对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T newInstance(Class<T> clazz, Object... args) {
        AssertIllegalArgument.isNotNull(clazz, "Class<T> clazz");
        if (LangUtils.isNotEmpty(args)) {
            List<Constructor<T>> matchConstructors = new ArrayList<>();
            for (Constructor<?> constructor : clazz.getConstructors()) {
                if (constructor.getParameterCount() == args.length) {
                    matchConstructors.add((Constructor<T>) constructor);
                }
            }
            Class<?>[] arguTypes = new Class<?>[args.length];
            for (int i = 0; i < args.length; i++) {
                arguTypes[i] = args[i].getClass();
            }
            Constructor<T> matchConstructor = null;
            for (Constructor<T> constructor : matchConstructors) {
                boolean matchAllParamType = true;
                Class<?>[] paramTypes = constructor.getParameterTypes();
                for (int i = 0; i < arguTypes.length; i++) {
                    if (paramTypes[i] != arguTypes[i]) {
                        matchAllParamType = false;
                        break;
                    }
                }
                if (matchAllParamType) {
                    matchConstructor = constructor;
                    break;
                }
            }
            if (matchConstructor == null) {
                for (Constructor<T> constructor : matchConstructors) {
                    boolean matchAllParamType = true;
                    Class<?>[] paramTypes = constructor.getParameterTypes();
                    for (int i = 0; i < arguTypes.length; i++) {
                        if (!ClassUtils.isParent(paramTypes[i], arguTypes[i])) {
                            matchAllParamType = false;
                            break;
                        }
                    }
                    if (matchAllParamType) {
                        matchConstructor = constructor;
                        break;
                    }
                }
            }
            if (matchConstructor == null) {
                throw new RuntimeException(StringUtils.format("[#1{#2}] 此构造器不存在",
                        new String[] { clazz.getName(), Arrays.asList(arguTypes).toString() }));
            }
            try {
                return newInstance(matchConstructor, args);
            } catch (SecurityException e) {
                throw new RuntimeException(StringUtils.format("[#1{#2}] 此构造器不可访问",
                        new String[] { clazz.getName(), Arrays.asList(arguTypes).toString() }));
            }
        } else {
            return newInstance(clazz);
        }

    }

    /**
     * 实例化.
     *
     * @param constructor 构造器
     * @param args        参数数组
     * @param <T>         泛型
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
            throw new RuntimeException(StringUtils.format(" [#1] 是否定义成抽象类了 不能实例化",
                    new String[] { constructor.getDeclaringClass().getName() }));
        } catch (InstantiationException e) {
            LOGGER.debug(ExceptionUtils.getStackTrace(e));
            throw new RuntimeException(
                    StringUtils.format("[#1] 构造器是否为私有", new String[] { constructor.getDeclaringClass().getName() }));
        } catch (IllegalAccessException e) {
            LOGGER.debug(ExceptionUtils.getStackTrace(e));
            throw new RuntimeException(
                    StringUtils.format("构造器参数不匹配", new String[] { constructor.getDeclaringClass().getName() }));
        } catch (InvocationTargetException e) {
            LOGGER.debug(ExceptionUtils.getStackTrace(e));
            throw new RuntimeException(
                    StringUtils.format("[#1] 构造器抛出异常", new String[] { constructor.getDeclaringClass().getName() }));
        }
    }

    // ********************************************************************
    //    查找
    // ********************************************************************

    /**
     * <p>
     * 查找并返回第一个符合条件的Field. 如果没有则返回null.
     * </p>
     *
     * @param type    匹配条件
     * @param matcher 匹配条件
     * @return 第一个符合条件Field
     */
    public static Field findField(Class<?> type, FieldMatcher matcher) {
        if (type != null) {
            for (Field field : type.getDeclaredFields()) {
                if (matcher.match(field)) {
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
     * @param type    类型
     * @param matcher 匹配条件
     * @return 所有符合条件Field的集合
     */
    public static Collection<Field> findFields(Class<?> type, FieldMatcher matcher) {
        Collection<Field> fields = new ArrayList<>();
        if (type != null) {
            for (Field field : type.getDeclaredFields()) {
                if (matcher.match(field)) {
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
     * @param type    类型
     * @param matcher 匹配条件
     * @return 第一个符合条件Method
     */
    public static Method findMethod(Class<?> type, MethodMatcher matcher) {
        if (type != null) {
            for (Method method : type.getDeclaredMethods()) {
                if (matcher.match(method)) {
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
     * @param type    类型
     * @param matcher 匹配条件
     * @return 所有符合条件Method的集合
     */
    public static Collection<Method> findMethods(Class<?> type, MethodMatcher matcher) {
        Collection<Method> methods = new ArrayList<>();
        if (type != null) {
            for (Method method : type.getDeclaredMethods()) {
                if (matcher.match(method)) {
                    methods.add(method);
                }
            }
        }
        return methods;
    }

    /**
     * getRawType
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
}