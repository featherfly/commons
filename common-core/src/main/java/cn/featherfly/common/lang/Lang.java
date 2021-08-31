package cn.featherfly.common.lang;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import cn.featherfly.common.exception.ExceptionWrapper;
import cn.featherfly.common.exception.IOException;

/**
 * 对一些在语法上显得拖沓的常用操作进行封装的工具类 .
 *
 * @author zhongj
 * @since 1.8.6
 */
public final class Lang {

    /** The Constant WRAPPER. */
    private static final ExceptionWrapper<RuntimeException> WRAPPER = new ExceptionWrapper<>(RuntimeException.class);

    /**
     * Instantiates a new lang utils.
     */
    private Lang() {
    }

    /**
     * 如果第一个参数为空(null），返回第二个参数，否则返回第一个参数 .
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
     * 返回第一个非空的项，!=null .
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
     * 返回最后一个非空的对象，!=null .
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
     * 返回传入对象是否为空，（String、Collection、Map、Array还要判断长度是否为0） .
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
        if (object instanceof Optional) {
            isEmpty((Optional<?>) object);
        }
        return false;
    }

    /**
     * 返回传入对象是否不为空（String、Collection、Map、Array还要判断长度是否为0） .
     *
     * @param object 传入的对象
     * @return 传入对象是否不为空
     */
    public static boolean isNotEmpty(Object object) {
        return !isEmpty(object);
    }

    /**
     * ifTrue.
     *
     * @param <O>     the generic type
     * @param <R>     the generic type
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
     * ifTrue.
     *
     * @param <O>     the generic type
     * @param <R>     the generic type
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
     * 判断传入对象是否为空，（String、Collection、Map、Array还要判断长度是否为0），如果为空则执行传入的方法 .
     *
     * @param <O>      the generic type
     * @param <R>      the generic type
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
     * 判断传入对象是否为空，（String、Collection、Map、Array还要判断长度是否为0），如果为空则执行传入的方法 .
     *
     * @param <O>      the generic type
     * @param <R>      the generic type
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
     * 判断传入对象是否不为空，（String、Collection、Map、Array还要判断长度是否为0），如果不为空则执行传入的方法 .
     *
     * @param <O>      the generic type
     * @param object   传入的对象
     * @param consumer 需要执行的方法
     */
    public static <O> void ifNotEmpty(O object, Consumer<O> consumer) {
        if (Lang.isNotEmpty(object)) {
            consumer.accept(object);
        }
    }

    /**
     * 判断传入对象是否不为空，（String、Collection、Map、Array还要判断长度是否为0），如果不为空则执行传入的方法 .
     *
     * @param <O>      the generic type
     * @param <R>      the generic type
     * @param object   传入的对象
     * @param notEmpty 需要执行的方法
     * @param empty    the empty
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
     * 判断传入对象是否不为空，（String、Collection、Map、Array还要判断长度是否为0），如果不为空则执行传入的方法 .
     *
     * @param <O>      the generic type
     * @param <R>      the generic type
     * @param object   传入的对象
     * @param notEmpty 需要执行的方法
     * @param empty    the empty
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
     * 返回传入Optional是否为空（是null或者内部数据为null） .
     *
     * @param optional 传入的Optional
     * @return 传入Optional是否为空
     */
    public static boolean isEmpty(Optional<?> optional) {
        if (optional == null) {
            return true;
        }
        return isEmpty(optional.orElse(null));
    }

    /**
     * 返回传入Optional是否不为空（不是null或者内部数据不为null） .
     *
     * @param optional 传入的Optional
     * @return 传入Optional是否为空
     */
    public static boolean isNotEmpty(Optional<?> optional) {
        return !isEmpty(optional);
    }

    /**
     * 返回传入字符串是否为空（是null或是空字符串） .
     *
     * @param string 传入的字符串
     * @return 传入字符串是否为空
     */
    public static boolean isEmpty(String string) {
        return string == null || "".equals(string);
    }

    //    /**
    //
    //     * 判断传入字符串是否为空（是null或是空字符串），如果为空，则执行传入的方法
    //
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
     * 返回传入字符串是否不为空（不是null或不是空字符串） .
     *
     * @param string 传入的字符串
     * @return 传入字符串是否不为空
     */
    public static boolean isNotEmpty(String string) {
        return !isEmpty(string);
    }

    //    /**
    //
    //     * 判断传入字符串是否不为空（是null或是空字符串），如果不为空，则执行传入的方法
    //
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
     * 返回数组是否为空（是null或是空数组） .
     *
     * @param array 传入的数组
     * @return 传入数组是否为空
     */
    public static boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

    //    /**
    //
    //     * 判断数组是否为空（是null或是空数组），如果为空，则执行传入的方法
    //
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
     * 返回数组是否不为空（null或空数组） .
     *
     * @param array 传入的数组
     * @return 传入数组是否不为空
     */
    public static boolean isNotEmpty(Object[] array) {
        return !isEmpty(array);
    }

    //    /**
    //
    //     * 判断数组是否不为空（是null或是空数组），如果不为空，则执行传入的方法
    //
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
     * 返回传入集合是否为空（是null或size=0） .
     *
     * @param collection 传入的集合
     * @return 传入集合是否为空
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    //    /**
    //
    //     * 判断集合是否为空（是null或size=0），如果为空，则执行传入的方法
    //
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
     * 返回传入集合是否不为空（不是null或size&gt;0） .
     *
     * @param collection 传入的集合
     * @return 传入集合是否不为空
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    //    /**
    //
    //     * 判断集合是否不为空（是null或size&gt;0），如果不为空，则执行传入的方法
    //
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
     * 返回传入map是否为空（是null或size=0） .
     *
     * @param map 传入的map
     * @return 传入map是否为空
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    /**
     * 返回传入map是否不为空（不是null或size&gt;0） .
     *
     * @param map 传入的map
     * @return 传入map是否不为空
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    //    /**
    //
    //     * 判断map是否为空（是null或size=0），如果为空，则执行传入的方法
    //
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
    //
    //     * 判断map是否不为空（是null或size=0），如果不为空，则执行传入的方法
    //
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
     * 判断传入文件对象代表的物理文件是否存在 .
     *
     * @param file 判断的文件
     * @return 传入文件对象代表的物理文件是否存在
     */
    public static boolean isExists(File file) {
        return file != null && file.exists();
    }

    /**
     * 判断传入文件对象代表的物理文件是否不存在 .
     *
     * @param file 判断的文件
     * @return 传入文件对象代表的物理文件是否存在
     */
    public static boolean isNotExists(File file) {
        return !isExists(file);
    }

    /**
     * 判断传入文件对象代表的物理文件是否存在，存在则执行传入方法 .
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
     * 判断传入文件对象代表的物理文件是否存在，存在则执行传入方法 .
     *
     * @param <R>       the generic type
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
     * 判断传入文件对象代表的物理文件是否不存在，不存在则执行传入方法 .
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
     * 判断传入文件对象代表的物理文件是否不存在，不存在则执行传入方法 .
     *
     * @param <R>       the generic type
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
     * 判断传入文件对象代表的物理文件是否不存在，不存在则执行传入方法 .
     *
     * @param <R>      the generic type
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
     * 将传入对象转换为枚举 .
     *
     * @param <T>     泛型
     * @param toClass 枚举的类型
     * @param object  需要转换的对象
     * @return 转换后的枚举，如果是无法转换或不存在的枚举类型，则返回null
     */
    public static <T extends Enum<T>> T toEnum(Class<T> toClass, Object object) {
        if (object != null) {
            if (object instanceof Enum) {
                return toEnum(toClass, ((Enum<?>) object).name());
            } else if (object instanceof String) {
                return toEnum(toClass, (String) object);
            } else if (object instanceof Integer || object.getClass() == int.class) {
                Integer ordinal = (Integer) object;
                return toEnum(toClass, ordinal);
            } else if (object instanceof String[]) {
                return toEnum(toClass, ((String[]) object)[0]);
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

    /**
     * To enum.
     *
     * @param <T>     the generic type
     * @param toClass the to class
     * @param value   the value
     * @return the t
     */
    private static <T extends Enum<T>> T toEnum(Class<T> toClass, String value) {
        try {
            int ordinal = Integer.parseInt(value);
            return toEnum(toClass, ordinal);
        } catch (NumberFormatException e) {
            return Enum.valueOf(toClass, value);
        }
    }

    /**
     * To enum.
     *
     * @param <T>     the generic type
     * @param toClass the to class
     * @param ordinal the ordinal
     * @return the t
     */
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
     * 安全的equals，防止空指针异常 .
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
     * 转换为String，如果不能转换（null）则使用默认值 .
     *
     * @param obj          obj
     * @param defaultValue defaultValue
     * @return string
     */
    public static String toString(Object obj, String defaultValue) {
        return obj != null ? obj.toString() : defaultValue;
    }

    /**
     * 转换为String，如果不能转换（null）则返回null .
     *
     * @param obj obj
     * @return string
     */
    public static String toString(Object obj) {
        return toString(obj, null);
    }

    /**
     * 获取调用getInvoker方法所在的方法被调用的信息（即调用方法、类等） .
     *
     * @return StackTraceElement
     */
    public static StackTraceElement getInvoker() {
        final String methodName = "getInvoker";
        int i = 0;
        for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
            if (stackTraceElement.getClassName().equals(Lang.class.getName())
                    && stackTraceElement.getMethodName().equals(methodName)) {
                return Thread.currentThread().getStackTrace()[i + 2];
            }
            i++;
        }
        return null;
    }

    /**
     * 获取调用此方法的调用方法栈 .
     *
     * @return List&lt;StackTraceElement&gt;
     */
    public static List<StackTraceElement> getInvokers() {
        final String methodName = "getInvokers";
        List<StackTraceElement> invokers = new ArrayList<>();
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        int i = 0;
        for (StackTraceElement stackTraceElement : stackTraceElements) {
            if (stackTraceElement.getClassName().equals(Lang.class.getName())
                    && stackTraceElement.getMethodName().equals(methodName)) {
                for (int j = i + 2; j < stackTraceElements.length; j++) {
                    invokers.add(stackTraceElements[j]);
                }
                return invokers;
            }
            i++;
        }
        return invokers;
    }

    /**
     * 转换为数组. 如果传入集合为空（null或者size=0）或者集合内的对象都是null,返回null.
     *
     * @param <A>        泛型
     * @param collection 集合
     * @return 数组
     */
    public static <A> A[] toArray(Collection<A> collection) {
        return CollectionUtils.toArray(collection);
    }

    /**
     * <p>
     * 转换为数组. 如果传入集合为空（null或者size=0），返回长度为0的数组（不会返回null）.
     * </p>
     *
     * @param <A>        泛型
     * @param collection 集合
     * @param type       类型
     * @return 数组
     */
    public static <A> A[] toArray(Collection<A> collection, Class<A> type) {
        return CollectionUtils.toArray(collection, type);
    }

    /**
     * <p>
     * 转换为列表
     * </p>
     * .
     *
     * @param <A>    the generic type
     * @param arrays 数组
     * @return 列表
     */
    public static <A> List<A> toList(A[] arrays) {
        return ArrayUtils.toList(arrays);
    }

    /**
     * 转换为以数组索引为key,数组值为value的map.
     *
     * @param <A>    the generic type
     * @param arrays 数组
     * @return map
     */
    public static <A> Map<Integer, A> toMap(A[] arrays) {
        return ArrayUtils.toMap(arrays);
    }

    /**
     * 转换为以数组索引为key,数组值为value的map.
     *
     * @param <A>    the generic type
     * @param arrays 数组
     * @return map
     * @deprecated use {@link #toMapStringKey(Object...)} instead
     */
    @Deprecated
    public static <A> Map<String, A> toMap2(A[] arrays) {
        return ArrayUtils.toMap2(arrays);
    }

    /**
     * 转换为以数组索引为key(string类型),数组值为value的map.
     *
     * @param <A>    the generic type
     * @param arrays 数组
     * @return 列表
     */
    public static <A> Map<String, A> toMapStringKey(@SuppressWarnings("unchecked") A... arrays) {
        return ArrayUtils.toMapStringKey(arrays);
    }

    /**
     * Wrap throw.
     *
     * @param throwable the throwable
     */
    public static void wrapThrow(Throwable throwable) {
        AssertIllegalArgument.isNotNull(throwable, "throwable");
        WRAPPER.throwException(throwable);
    }

    /**
     * Wrap throw.
     *
     * @param ioException the io exception
     */
    public static void wrapThrow(java.io.IOException ioException) {
        AssertIllegalArgument.isNotNull(ioException, "ioException");
        throw new IOException(ioException);
    }

    /**
     * Wrap throw.
     *
     * @param <E>                     the element type
     * @param throwable               the throwable
     * @param wrappedRuntimeException the wrapped runtime exception
     */
    public static <E extends RuntimeException> void wrapThrow(Throwable throwable, Class<E> wrappedRuntimeException) {
        AssertIllegalArgument.isNotNull(throwable, "throwable");
        AssertIllegalArgument.isNotNull(wrappedRuntimeException, "wrappedRuntimeException");
        new ExceptionWrapper<>(wrappedRuntimeException).throwException(throwable);
    }

    /**
     * Each.
     *
     * @param <T>      the generic type
     * @param array    the array
     * @param consumer the consumer
     */
    public static <T> void each(T[] array, BiConsumer<T, Integer> consumer) {
        ArrayUtils.each(array, consumer);
    }

    /**
     * Each.
     *
     * @param <T>      the generic type
     * @param iterable the iterable
     * @param consumer the consumer
     */
    public static <T> void each(Iterable<T> iterable, BiConsumer<T, Integer> consumer) {
        CollectionUtils.each(iterable, consumer);
    }

    /**
     * array.
     *
     * @param objs the objs
     * @return Object[]
     */
    public static Object[] array(Object... objs) {
        return objs;
    }

    /**
     * List.
     *
     * @param <E>      the element type
     * @param elements the elements
     * @return the list
     */
    public static <E> List<E> list(@SuppressWarnings("unchecked") E... elements) {
        List<E> list = new ArrayList<>();
        CollectionUtils.addAll(list, elements);
        return list;
    }
}
