package cn.featherfly.common.lang;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * <p>
 * 对一些在语法上显得拖沓的常用操作进行封装的工具类
 * </p>
 *
 * @author zhongj
 * @since 1.0
 * @version 1.0
 */
public final class LangUtils {

    private LangUtils() {
    }

    /**
     * <p>
     * 如果第一个参数为空(null），返回第二个参数，否则返回第一个参数
     * </p>
     *
     * @param <T>           泛型
     * @param target        目标参数
     * @param defaultTarget 默认值
     * @return 第一个参数为空(null），返回第二个参数，否则返回第一个参数
     */
    public static <T> T pick(T target, T defaultTarget) {
        return target == null ? defaultTarget : target;
    }

    /**
     * <p>
     * 返回第一个非空的项，!=null
     * <p>
     *
     * @param <T>         泛型
     * @param pickedItems 需要选择的对象
     * @return 第一个非空的对象
     */
    @SafeVarargs
    public static <T> T pickFirst(T... pickedItems) {
        if (pickedItems != null) {
            for (T t : pickedItems) {
                if (t != null) {
                    return t;
                }
            }
        }
        return null;
    }

    /**
     * <p>
     * 返回最后一个非空的对象，!=null
     * </p>
     *
     * @param <T>         泛型
     * @param pickedItems 需要选择的对象
     * @return 最后一个非空的对象
     */
    @SafeVarargs
    public static <T> T pickLast(T... pickedItems) {
        if (pickedItems != null) {
            for (int i = pickedItems.length - 1; i >= 0; i--) {
                T t = pickedItems[i];
                if (t != null) {
                    return t;
                }
            }
        }
        return null;
    }

    /**
     * <p>
     * 返回传入对象是否为空，（String、Collection、Map、Array还要判断长度是否为0）
     * </p>
     *
     * @param object 传入的对象
     * @return 传入对象是否为空
     */
    public static boolean isEmpty(Object object) {
        if (object == null) {
            return true;
        }
        if (object instanceof String) {
            return isEmpty((String) object);
        }
        if (object instanceof Collection) {
            return isEmpty((Collection<?>) object);
        }
        if (object instanceof Map) {
            return isEmpty((Map<?, ?>) object);
        }
        if (object.getClass().isArray()) {
            return Array.getLength(object) == 0;
        }
        return false;
    }

    /**
     * <p>
     * 返回传入对象是否不为空（String、Collection、Map、Array还要判断长度是否为0）
     * </p>
     *
     * @param object 传入的对象
     * @return 传入对象是否不为空
     */
    public static boolean isNotEmpty(Object object) {
        return !isEmpty(object);
    }

    /**
     * ifTrue
     *
     * @param bool    bool
     * @param isTrue  exec when bool is true
     * @param isFalse exec when bool is false
     * @return obj
     */
    public static <O, R> R ifTrue(boolean bool, Supplier<R> isTrue, Supplier<R> isFalse) {
        if (bool) {
            return isTrue.get();
        } else {
            return isFalse.get();
        }
    }

    /**
     * ifTrue
     *
     * @param bool    bool
     * @param isFalse exec when bool is false
     * @param isTrue  exec when bool is true
     * @return obj
     */
    public static <O, R> R ifFalse(boolean bool, Supplier<R> isFalse, Supplier<R> isTrue) {
        if (bool) {
            return isTrue.get();
        } else {
            return isFalse.get();
        }
    }

    /**
     * <p>
     * 判断传入对象是否为空，（String、Collection、Map、Array还要判断长度是否为0），如果为空则执行传入的方法
     * </p>
     *
     * @param object   传入的对象
     * @param empty    exec when empty
     * @param notEmpty exec when not empty
     * @return object
     */
    public static <O, R> R ifEmpty(O object, Supplier<R> empty, Supplier<R> notEmpty) {
        if (isEmpty(object)) {
            return empty.get();
        } else {
            return notEmpty.get();
        }
    }

    /**
     * <p>
     * 判断传入对象是否为空，（String、Collection、Map、Array还要判断长度是否为0），如果为空则执行传入的方法
     * </p>
     *
     * @param object   传入的对象
     * @param empty    exec when empty
     * @param notEmpty exec when not empty
     * @return object
     */
    public static <O, R> R ifEmpty(O object, Supplier<R> empty, Function<O, R> notEmpty) {
        if (isEmpty(object)) {
            return empty.get();
        } else {
            return notEmpty.apply(object);
        }
    }

    /**
     * <p>
     * 判断传入对象是否不为空，（String、Collection、Map、Array还要判断长度是否为0），如果不为空则执行传入的方法
     * </p>
     *
     * @param object   传入的对象
     * @param consumer 需要执行的方法
     */
    public static <O> void ifNotEmpty(O object, Consumer<O> consumer) {
        if (LangUtils.isNotEmpty(object)) {
            consumer.accept(object);
        }
    }

    /**
     * <p>
     * 判断传入对象是否不为空，（String、Collection、Map、Array还要判断长度是否为0），如果不为空则执行传入的方法
     * </p>
     *
     * @param object   传入的对象
     * @param notEmpty 需要执行的方法
     * @return object
     */
    public static <O, R> R ifNotEmpty(O object, Supplier<R> notEmpty, Supplier<R> empty) {
        if (isNotEmpty(object)) {
            return notEmpty.get();
        } else {
            return empty.get();
        }
    }

    /**
     * <p>
     * 判断传入对象是否不为空，（String、Collection、Map、Array还要判断长度是否为0），如果不为空则执行传入的方法
     * </p>
     *
     * @param object   传入的对象
     * @param notEmpty 需要执行的方法
     * @return object
     */
    public static <O, R> R ifNotEmpty(O object, Function<O, R> notEmpty, Supplier<R> empty) {
        if (isNotEmpty(object)) {
            return notEmpty.apply(object);
        } else {
            return empty.get();
        }
    }

    /**
     * <p>
     * 返回传入字符串是否为空（是null或是空字符串）
     * </p>
     *
     * @param string 传入的字符串
     * @return 传入字符串是否为空
     */
    public static boolean isEmpty(String string) {
        return string == null || "".equals(string);
    }

    //    /**
    //     * <p>
    //     * 判断传入字符串是否为空（是null或是空字符串），如果为空，则执行传入的方法
    //     * </p>
    //     *
    //     * @param string   传入的字符串
    //     * @param consumer 需要执行的方法
    //     * @param consumer 判断为空时执行的方法
    //     */
    //    public static void isEmpty(String string, Consumer<String> consumer) {
    //        if (isEmpty(string)) {
    //            consumer.accept(string);
    //        }
    //    }

    /**
     * <p>
     * 返回传入字符串是否不为空（不是null或不是空字符串）
     * </p>
     *
     * @param string 传入的字符串
     * @return 传入字符串是否不为空
     */
    public static boolean isNotEmpty(String string) {
        return !isEmpty(string);
    }

    //    /**
    //     * <p>
    //     * 判断传入字符串是否不为空（是null或是空字符串），如果不为空，则执行传入的方法
    //     * </p>
    //     *
    //     * @param string   传入的字符串
    //     * @param consumer 需要执行的方法
    //     * @param consumer 判断为空时执行的方法
    //     */
    //    public static void isNotEmpty(String string, Consumer<String> consumer) {
    //        if (isNotEmpty(string)) {
    //            consumer.accept(string);
    //        }
    //    }

    /**
     * <p>
     * 返回数组是否为空（是null或是空数组）
     * </p>
     *
     * @param array 传入的数组
     * @return 传入数组是否为空
     */
    public static boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

    //    /**
    //     * <p>
    //     * 判断数组是否为空（是null或是空数组），如果为空，则执行传入的方法
    //     * </p>
    //     *
    //     * @param array    传入的数组
    //     * @param consumer 需要执行的方法
    //     */
    //    public static <E> void isEmpty(E[] array, Consumer<E[]> consumer) {
    //        if (isEmpty(array)) {
    //            consumer.accept(array);
    //        }
    //    }

    /**
     * <p>
     * 返回数组是否不为空（null或空数组）
     * </p>
     *
     * @param array 传入的数组
     * @return 传入数组是否不为空
     */
    public static boolean isNotEmpty(Object[] array) {
        return !isEmpty(array);
    }

    //    /**
    //     * <p>
    //     * 判断数组是否不为空（是null或是空数组），如果不为空，则执行传入的方法
    //     * </p>
    //     *
    //     * @param array    传入的数组
    //     * @param consumer 需要执行的方法
    //     */
    //    public static <E> void isNotEmpty(E[] array, Consumer<E[]> consumer) {
    //        if (isNotEmpty(array)) {
    //            consumer.accept(array);
    //        }
    //    }

    /**
     * <p>
     * 返回传入集合是否为空（是null或size=0）
     * </p>
     *
     * @param collection 传入的集合
     * @return 传入集合是否为空
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    //    /**
    //     * <p>
    //     * 判断集合是否为空（是null或size=0），如果为空，则执行传入的方法
    //     * </p>
    //     *
    //     * @param collection 传入的集合
    //     * @param consumer   需要执行的方法
    //     */
    //    public static <E> void isEmpty(Collection<E> collection, Consumer<Collection<E>> consumer) {
    //        if (isEmpty(collection)) {
    //            consumer.accept(collection);
    //        }
    //    }

    /**
     * <p>
     * 返回传入集合是否不为空（不是null或size&gt;0）
     * </p>
     *
     * @param collection 传入的集合
     * @return 传入集合是否不为空
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    //    /**
    //     * <p>
    //     * 判断集合是否不为空（是null或size&gt;0），如果不为空，则执行传入的方法
    //     * </p>
    //     *
    //     * @param collection 传入的集合
    //     * @param consumer   需要执行的方法
    //     */
    //    public static <E> void isNotEmpty(Collection<E> collection, Consumer<Collection<E>> consumer) {
    //        if (isNotEmpty(collection)) {
    //            consumer.accept(collection);
    //        }
    //    }

    /**
     * <p>
     * 返回传入map是否为空（是null或size=0）
     * </p>
     *
     * @param map 传入的map
     * @return 传入map是否为空
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    /**
     * <p>
     * 返回传入map是否不为空（不是null或size&gt;0）
     * </p>
     *
     * @param map 传入的map
     * @return 传入map是否不为空
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    //    /**
    //     * <p>
    //     * 判断map是否为空（是null或size=0），如果为空，则执行传入的方法
    //     * </p>
    //     *
    //     * @param map      传入的map
    //     * @param consumer 需要执行的方法
    //     */
    //    public static <K, V> void isEmpty(Map<K, V> map, Consumer<Map<K, V>> consumer) {
    //        if (isEmpty(map)) {
    //            consumer.accept(map);
    //        }
    //    }

    //    /**
    //     * <p>
    //     * 判断map是否不为空（是null或size=0），如果不为空，则执行传入的方法
    //     * </p>
    //     *
    //     * @param map      传入的map
    //     * @param consumer 需要执行的方法
    //     */
    //    public static <K, V> void isNotEmpty(Map<K, V> map, Consumer<Map<K, V>> consumer) {
    //        if (isNotEmpty(map)) {
    //            consumer.accept(map);
    //        }
    //    }

    /**
     * <p>
     * 判断传入文件对象代表的物理文件是否存在
     * </p>
     *
     * @param file 判断的文件
     * @return 传入文件对象代表的物理文件是否存在
     */
    public static boolean isExists(File file) {
        return file != null && file.exists();
    }

    /**
     * <p>
     * 判断传入文件对象代表的物理文件是否不存在
     * </p>
     *
     * @param file 判断的文件
     * @return 传入文件对象代表的物理文件是否存在
     */
    public static boolean isNotExists(File file) {
        return !isExists(file);
    }

    /**
     * <p>
     * 判断传入文件对象代表的物理文件是否存在，存在则执行传入方法
     * </p>
     *
     * @param file     判断的文件
     * @param consumer 需要执行的方法
     */
    public static void ifExists(File file, Consumer<File> consumer) {
        if (isExists(file)) {
            consumer.accept(file);
        }
    }

    /**
     * <p>
     * 判断传入文件对象代表的物理文件是否存在，存在则执行传入方法
     * </p>
     *
     * @param file      判断的文件
     * @param exists    exec when exists
     * @param notExists exec when not exists
     * @return obj
     */
    public static <R> R ifExists(File file, Function<File, R> exists, Function<File, R> notExists) {
        if (isExists(file)) {
            return exists.apply(file);
        } else {
            return notExists.apply(file);
        }
    }

    /**
     * <p>
     * 判断传入文件对象代表的物理文件是否不存在，不存在则执行传入方法
     * </p>
     *
     * @param file     判断的文件
     * @param consumer 需要执行的方法
     */
    public static void ifNotExists(File file, Consumer<File> consumer) {
        if (isNotExists(file)) {
            consumer.accept(file);
        }
    }

    /**
     * <p>
     * 判断传入文件对象代表的物理文件是否不存在，不存在则执行传入方法
     * </p>
     *
     * @param file      判断的文件
     * @param notExists exec when not exists
     * @param exists    exec when exists
     * @return obj
     */
    public static <R> R ifNotExists(File file, Function<File, R> notExists, Function<File, R> exists) {
        if (isNotExists(file)) {
            return notExists.apply(file);
        } else {
            return exists.apply(file);
        }
    }

    /**
     * <p>
     * 判断传入文件对象代表的物理文件是否不存在，不存在则执行传入方法
     * </p>
     *
     * @param file     判断的文件
     * @param supplier 需要执行的方法
     * @return obj
     */
    public static <R> R ifNotExists(File file, Supplier<R> supplier) {
        if (isNotExists(file)) {
            return supplier.get();
        }
        return null;
    }

    /**
     * <p>
     * 将传入对象转换为枚举
     * </p>
     *
     * @param toClass 枚举的类型
     * @param object  需要转换的对象
     * @param <T>     泛型
     * @return 转换后的枚举，如果是无法转换或不存在的枚举类型，则返回null
     */
    public static <T extends Enum<T>> T toEnum(Class<T> toClass, Object object) {
        if (object != null) {
            if (object instanceof String[]) {
                return toEnum(toClass, ((String[]) object)[0]);
            } else if (object instanceof String) {
                return toEnum(toClass, (String) object);
            } else if (object instanceof Integer || object.getClass() == int.class) {
                Integer ordinal = (Integer) object;
                return toEnum(toClass, ordinal);
            } else if (object instanceof Byte || object.getClass() == byte.class) {
                Byte ordinal = (Byte) object;
                return toEnum(toClass, new Integer(ordinal));
            } else if (object instanceof Short || object.getClass() == short.class) {
                Short ordinal = (Short) object;
                return toEnum(toClass, new Integer(ordinal));
            }
        }
        return null;
    }

    private static <T extends Enum<T>> T toEnum(Class<T> toClass, String value) {
        try {
            int ordinal = Integer.parseInt(value);
            return toEnum(toClass, ordinal);
        } catch (NumberFormatException e) {
            return Enum.valueOf(toClass, value);
        }
    }

    private static <T extends Enum<T>> T toEnum(Class<T> toClass, Integer ordinal) {
        T[] es = toClass.getEnumConstants();
        for (T e : es) {
            if (e.ordinal() == ordinal) {
                return e;
            }
        }
        return null;
    }

    /**
     * <p>
     * 安全的equals，防止空指针异常
     * </p>
     *
     * @param target      比较对象
     * @param otherTarget 另一个比较对象
     * @return 比较结果
     */
    public static boolean equals(Object target, Object otherTarget) {
        return target == otherTarget || target != null && target.equals(otherTarget);
    }

    /**
     * 返回hash code，如果传入参数为null,返回0.
     *
     * @param o 对象
     * @return hash code
     * @see Object#hashCode
     */
    public static int hashCode(Object o) {
        return o != null ? o.hashCode() : 0;
    }

    /**
     * <p>
     * 转换为String，如果不能转换（null）则使用默认值
     * </p>
     *
     * @param obj          obj
     * @param defaultValue defaultValue
     * @return string
     */
    public static String toString(Object obj, String defaultValue) {
        return obj != null ? obj.toString() : defaultValue;
    }

    /**
     * <p>
     * 转换为String，如果不能转换（null）则返回null
     * </p>
     *
     * @param obj obj
     * @return string
     */
    public static String toString(Object obj) {
        return toString(obj, null);
    }

    /**
     * <p>
     * 获取调用getInvoker方法所在的方法被调用的信息（即调用方法、类等）
     * </p>
     *
     * @return StackTraceElement
     */
    public static StackTraceElement getInvoker() {
        final String methodName = "getInvoker";
        boolean findThis = false;
        for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
            if (findThis) {
                return stackTraceElement;
            }
            findThis = stackTraceElement.getClassName().equals(LangUtils.class.getName())
                    && stackTraceElement.getMethodName().equals(methodName);
        }
        return null;
    }

    /**
     * <p>
     * 获取调用此方法的调用方法栈
     * </p>
     *
     * @return List&lt;StackTraceElement&gt;
     */
    public static List<StackTraceElement> getInvokers() {
        final String methodName = "getInvokers";
        List<StackTraceElement> invokers = new ArrayList<>();
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        boolean findThis = false;
        for (StackTraceElement stackTraceElement : stackTraceElements) {
            if (findThis) {
                invokers.add(stackTraceElement);
            }
            findThis = stackTraceElement.getClassName().equals(LangUtils.class.getName())
                    && stackTraceElement.getMethodName().equals(methodName);
        }
        return invokers;
    }

    //    /**
    //     * <p>
    //     * 获取调用getInvoker方法所在的方法被调用的信息（即调用方法、类等）
    //     * </p>
    //     *
    //     * @return StackTraceElement
    //     */
    //    public static StackTraceElement getInvokerAll() {
    //        final String methodName = "getInvokerAll";
    //        for (Entry<Thread, StackTraceElement[]> entry : Thread.getAllStackTraces().entrySet()) {
    //            StackTraceElement[] stackTraceElements = entry.getValue();
    //            boolean findThis = false;
    //            boolean findInvokeThis = false;
    //            boolean findInvokor = false;
    //            for (StackTraceElement stackTraceElement : stackTraceElements) {
    //                if (findThis) {
    //                    findInvokeThis = true;
    //                    findThis = false;
    //                }
    //                if (stackTraceElement.getClassName().equals(LangUtils.class.getName())
    //                        && stackTraceElement.getMethodName().equals(methodName)) {
    //                    findThis = true;
    //                }
    //                if (findInvokor) {
    //                    return stackTraceElement;
    //                }
    //                if (findInvokeThis) {
    //                    findInvokeThis = false;
    //                    findInvokor = true;
    //                }
    //            }
    //        }
    //        return null;
    //    }
    //
    //    /**
    //     * <p>
    //     * 获取调用此方法的调用方法栈
    //     * </p>
    //     *
    //     * @return List&lt;StackTraceElement&gt;
    //     */
    //    public static List<StackTraceElement> getInvokersAll() {
    //        final String methodName = "getInvokersAll";
    //        List<StackTraceElement> invokers = new ArrayList<>();
    //        for (Entry<Thread, StackTraceElement[]> entry : Thread.getAllStackTraces().entrySet()) {
    //            StackTraceElement[] stackTraceElements = entry.getValue();
    //            boolean findThis = false;
    //            boolean findInvokeThis = false;
    //            boolean findInvokor = false;
    //            for (StackTraceElement stackTraceElement : stackTraceElements) {
    //                if (findThis) {
    //                    findInvokeThis = true;
    //                    findThis = false;
    //                }
    //                if (stackTraceElement.getClassName().equals(LangUtils.class.getName())
    //                        && stackTraceElement.getMethodName().equals(methodName)) {
    //                    findThis = true;
    //                }
    //                if (findInvokor) {
    //                    invokers.add(stackTraceElement);
    //                }
    //                if (findInvokeThis) {
    //                    findInvokeThis = false;
    //                    findInvokor = true;
    //                }
    //            }
    //        }
    //        return invokers;
    //    }
}
