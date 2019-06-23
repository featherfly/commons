package cn.featherfly.common.lang;

import java.io.File;
import java.util.Collection;
import java.util.Map;

/**
 * <p>
 * 断言工具类，对于满足断言的情况，抛出指定异常
 * 一般用于检查传入参数是否合法
 * </p>
 *
 * @author 钟冀
 * @since 1.0
 * @version 1.0
 */
public final class AssertUtils {

    private AssertUtils() {
    }

    /**
     * <p>
     * 判断不为空，如果为空，抛出指定异常
     * </p>
     * @param object 判断的对象
     * @param message 断言失败的信息
     * @param exceptionType 异常类型
     * @param <E> 泛型
     */
    public static <E extends RuntimeException> void isNotNull(Object object, String message
                    , Class<E> exceptionType) {
        new Assert(exceptionType).isNotNull(object, message);
    }

    /**
     * <p>
     * 判断不为空，如果为空，抛出指定异常
     * </p>
     * @param object 判断的对象
     * @param exceptionType 异常类型
     * @param <E> 泛型
     */
    public static <E extends RuntimeException> void isNotNull(Object object, Class<E> exceptionType) {
        new Assert(exceptionType).isNotNull(object);
    }

    /**
     * <p>
     * 判断为true，如果为false，抛出指定异常
     * </p>
     * @param expression 判断的值
     * @param message 断言失败的信息
     * @param exceptionType 异常类型
     * @param <E> 泛型
     */
    public static <E extends RuntimeException> void isTrue(boolean expression, String message
                    , Class<E> exceptionType) {
        new Assert(exceptionType).isTrue(expression, message);
    }

    /**
     * <p>
     * 判断为false，如果为true，抛出指定异常
     * </p>
     * @param expression 判断的值
     * @param message 断言失败的信息
     * @param exceptionType 异常类型
     * @param <E> 泛型
     */
    public static <E extends RuntimeException> void isFalse(boolean expression, String message
                    , Class<E> exceptionType) {
        new Assert(exceptionType).isFalse(expression, message);
    }

    /**
     * <p>
     * 判断不为空或空串（包括只有空字符的串），判断失败抛出指定异常
     * </p>
     * @param text 判断的字符串
     * @param message 断言失败的信息
     * @param exceptionType 异常类型
     * @param <E> 泛型
     */
    public static <E extends RuntimeException> void isNotBlank(String text, String message
                    , Class<E> exceptionType) {
        new Assert(exceptionType).isNotBlank(text, message);
    }

    /**
     * <p>
     * 判断不为空或空串（包括只有空字符的串），判断失败抛出指定异常
     * </p>
     * @param text 判断的字符串
     * @param exceptionType 异常类型
     * @param <E> 泛型
     */
    public static <E extends RuntimeException> void isNotBlank(String text, Class<E> exceptionType) {
        new Assert(exceptionType).isNotBlank(text);
    }

    /**
     * <p>
     * 判断不为空（String,Collection,Map,Array还要判断长度是否为0），判断失败抛出指定异常
     * </p>
     * @param obj 判断的对象
     * @param message 断言失败的信息
     * @param exceptionType 异常类型
     * @param <E> 泛型
     */
    public static <E extends RuntimeException> void isNotEmpty(Object obj, String message
                    , Class<E> exceptionType) {
        new Assert(exceptionType).isNotEmpty(obj, message);
    }

    /**
     * <p>
     * 判断不为空（String,Collection,Map,Array还要判断长度是否为0），判断失败抛出指定异常
     * </p>
     * @param obj 判断的对象
     * @param exceptionType 异常类型
     * @param <E> 泛型
     */
    public static <E extends RuntimeException> void isNotEmpty(Object obj, Class<E> exceptionType) {
        new Assert(exceptionType).isNotEmpty(obj);
    }
    /**
     * <p>
     * 判断不为空或空串，判断失败抛出指定异常
     * </p>
     * @param text 判断的字符串
     * @param message 断言失败的信息
     * @param exceptionType 异常类型
     * @param <E> 泛型
     */
    public static <E extends RuntimeException> void isNotEmpty(String text, String message
                    , Class<E> exceptionType) {
        new Assert(exceptionType).isNotEmpty(text, message);
    }

    /**
     * <p>
     * 判断不为空或空串，判断失败抛出指定异常
     * </p>
     * @param text 判断的字符串
     * @param exceptionType 异常类型
     * @param <E> 泛型
     */
    public static <E extends RuntimeException> void isNotEmpty(String text, Class<E> exceptionType) {
        new Assert(exceptionType).isNotEmpty(text);
    }

    /**
     * <p>
     * 判断数组不为null或size不为0，判断失败抛出指定异常
     * </p>
     * @param array 需要判断的数组
     * @param message 断言失败的信息
     * @param exceptionType 异常类型
     * @param <E> 泛型
     */
    public static <E extends RuntimeException> void isNotEmpty(Object[] array, String message
                    , Class<E> exceptionType) {
        new Assert(exceptionType).isNotEmpty(array, message);
    }

    /**
     * <p>
     * 判断数组不为null或size不为0，判断失败抛出指定异常
     * </p>
     * @param array 需要判断的数组
     * @param exceptionType 异常类型
     * @param <E> 泛型
     */
    public static <E extends RuntimeException> void isNotEmpty(Object[] array, Class<E> exceptionType) {
        new Assert(exceptionType).isNotEmpty(array);
    }

    /**
     * <p>
     * 判断集合不为null或size不为0，判断失败抛出指定异常
     * </p>
     * @param collection 判断的集合
     * @param message 断言失败的信息
     * @param exceptionType 异常类型
     * @param <E> 泛型
     */
    public static <E extends RuntimeException> void isNotEmpty(Collection<?> collection, String message
                    , Class<E> exceptionType) {
        new Assert(exceptionType).isNotEmpty(collection, message);
    }

    /**
     * <p>
     * 判断集合不为null或size不为0，判断失败抛出指定异常
     * </p>
     * @param collection 判断的集合
     * @param exceptionType 异常类型
     * @param <E> 泛型
     */
    public static <E extends RuntimeException> void isNotEmpty(Collection<?> collection, Class<E> exceptionType) {
        new Assert(exceptionType).isNotEmpty(collection);
    }

    /**
     * <p>
     * 判断MAP不为null或size不为0，判断失败抛出指定异常
     * </p>
     * @param map 判断的集合
     * @param message 断言失败的信息
     * @param exceptionType 异常类型
     * @param <E> 泛型
     */
    public static <E extends RuntimeException> void isNotEmpty(Map<?, ?> map, String message
                    , Class<E> exceptionType) {
        new Assert(exceptionType).isNotEmpty(map, message);
    }

    /**
     * <p>
     * 判断MAP不为null或size不为0，判断失败抛出指定异常
     * </p>
     * @param map 判断的集合
     * @param exceptionType 异常类型
     * @param <E> 泛型
     */
    public static <E extends RuntimeException> void isNotEmpty(Map<?, ?> map, Class<E> exceptionType) {
        new Assert(exceptionType).isNotEmpty(map);
    }

    /**
     * <p>
     * 判断传入文件对象代表的物理文件是否存在，判断失败抛出指定异常
     * </p>
     * @param file 判断的文件对象
     * @param message 断言失败的信息
     * @param exceptionType 异常类型
     * @param <E> 泛型
     */
    public static <E extends RuntimeException> void isExists(File file , String message, Class<E> exceptionType) {
        new Assert(exceptionType).isExists(file, message);
    }

    /**
     * <p>
     * 判断传入文件对象代表的物理文件是否存在，判断失败抛出指定异常
     * </p>
     * @param file 判断的文件对象
     * @param exceptionType 异常类型
     * @param <E> 泛型
     */
    public static <E extends RuntimeException> void isExists(File file, Class<E> exceptionType) {
        new Assert(exceptionType).isExists(file);
    }

    /**
     * <p>
     * 判断对象（第二个参数）是指定类型（第一个参数）的实例，判断失败抛出指定异常
     * </p>
     * @param clazz 类型
     * @param obj 对象
     * @param message 断言失败的信息
     * @param exceptionType 异常类型
     * @param <E> 泛型
     */
    public static <E extends RuntimeException> void isInstanceOf(Class<?> clazz, Object obj, String message
                    , Class<E> exceptionType) {
        new Assert(exceptionType).isInstanceOf(clazz, obj, message);
    }

    /**
     * <p>
     * 判断对象（第二个参数）是指定类型（第一个参数）的实例，判断失败抛出指定异常
     * </p>
     * @param clazz 类型
     * @param obj 对象
     * @param exceptionType 异常类型
     * @param <E> 泛型
     */
    public static <E extends RuntimeException> void isInstanceOf(Class<?> clazz, Object obj
                    , Class<E> exceptionType) {
        new Assert(exceptionType).isInstanceOf(clazz, obj);
    }
}
