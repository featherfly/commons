package cn.featherfly.common.lang;

import java.io.File;
import java.util.Collection;
import java.util.Map;
import java.util.function.Supplier;

/**
 * The assertion utility class, which throws an IllegalArgumentException when the assertion is
 * satisfied, is generally used to check whether the passed argument is valid.
 * 断言工具类，对于满足断言的情况，抛出IllegalArgumentException异常,一般用于检查传入参数是否合法.
 *
 * @author zhongj
 * @since 1.13.0
 */
public final class Asserts {

    private static final Assert<IllegalArgumentException> ASSERT = new Assert<>(IllegalArgumentException::new);

    /**
     */
    private Asserts() {
    }

    /**
     * Checks if is true.
     *
     * @param expression the expression
     * @param message the message
     */
    public static void isTrue(boolean expression, Supplier<String> message) {
        ASSERT.isTrue(expression, message);
    }

    /**
     * 判断为true，如果为false，抛出指定异常.
     *
     * @param expression 判断的值
     * @param message 断言失败的信息
     */
    public static void isTrue(boolean expression, String message) {
        ASSERT.isTrue(expression, message);
    }

    /**
     * 判断为false，如果为true，抛出指定异常.
     *
     * @param expression 判断的值
     * @param message 断言失败的信息
     */
    public static void isFalse(boolean expression, String message) {
        ASSERT.isFalse(expression, message);
    }

    /**
     * Checks if is false.
     *
     * @param expression the expression
     * @param message the message
     */
    public static void isFalse(boolean expression, Supplier<String> message) {
        ASSERT.isFalse(expression, message);
    }

    /**
     * Checks if is null.
     *
     * @param object the object
     * @param message the message
     */
    public static void isNull(Object object, String message) {
        ASSERT.isNull(object, message);
    }

    /**
     * Checks if is null.
     *
     * @param object the object
     * @param message the message
     */
    public static void isNull(Object object, Supplier<String> message) {
        ASSERT.isNull(object, message);
    }

    /**
     * 判断不为空，如果为空，抛出指定异常.
     *
     * @param object 判断的对象
     * @param message 断言失败的信息
     */
    public static void isNotNull(Object object, String message) {
        ASSERT.isNotNull(object, message);
    }

    /**
     * Checks if is not null.
     *
     * @param object the object
     * @param message the message
     */
    public static void isNotNull(Object object, Supplier<String> message) {
        ASSERT.isNotNull(object, message);
    }

    /**
     * 判断不为空或空串（包括只有空字符的串），判断失败抛出指定异常.
     *
     * @param text 判断的字符串
     * @param message 断言失败的信息
     */
    public static void isNotBlank(String text, String message) {
        ASSERT.isNotBlank(text, message);
    }

    /**
     * Checks if is not blank.
     *
     * @param text the text
     * @param message the message
     */
    public static void isNotBlank(String text, Supplier<String> message) {
        ASSERT.isNotBlank(text, message);
    }

    /**
     * Checks if is empty.
     *
     * @param obj the obj
     * @param message the message
     */
    public static void isEmpty(Object obj, String message) {
        ASSERT.isEmpty(obj, message);
    }

    /**
     * Checks if is empty.
     *
     * @param obj the obj
     * @param message the message
     */
    public static void isEmpty(Object obj, Supplier<String> message) {
        ASSERT.isEmpty(obj, message);
    }

    /**
     * 判断不为空（String,Collection,Map,Array还要判断长度是否为0），判断失败抛出指定异常.
     *
     * @param obj 判断的对象
     * @param message 断言失败的信息
     */
    public static void isNotEmpty(Object obj, String message) {
        ASSERT.isNotEmpty(obj, message);
    }

    /**
     * Checks if is not empty.
     *
     * @param obj the obj
     * @param message the message
     */
    public static void isNotEmpty(Object obj, Supplier<String> message) {
        ASSERT.isNotEmpty(obj, message);
    }

    /**
     * 判断不为空或空串，判断失败抛出指定异常.
     *
     * @param text 判断的字符串
     * @param message 断言失败的信息
     */
    public static void isNotEmpty(String text, String message) {
        ASSERT.isNotEmpty(text, message);
    }

    /**
     * Checks if is not empty.
     *
     * @param text the text
     * @param message the message
     */
    public static void isNotEmpty(String text, Supplier<String> message) {
        ASSERT.isNotEmpty(text, message);
    }

    /**
     * Checks if is not empty.
     *
     * @param array the array
     * @param message the message
     */
    public static void isNotEmpty(Object[] array, Supplier<String> message) {
        ASSERT.isNotEmpty(array, message);
    }

    /**
     * 判断数组不为null或size不为0，判断失败抛出指定异常.
     *
     * @param array 需要判断的数组
     * @param message 断言失败的信息
     */
    public static void isNotEmpty(Object[] array, String message) {
        ASSERT.isNotEmpty(array, message);
    }

    /**
     * 判断集合不为null或size不为0，判断失败抛出指定异常.
     *
     * @param collection 判断的集合
     * @param message 断言失败的信息
     */
    public static void isNotEmpty(Collection<?> collection, String message) {
        ASSERT.isNotEmpty(collection, message);
    }

    /**
     * Checks if is not empty.
     *
     * @param collection the collection
     * @param message the message
     */
    public static void isNotEmpty(Collection<?> collection, Supplier<String> message) {
        ASSERT.isNotEmpty(collection, message);
    }

    /**
     * 判断MAP不为null或size不为0，判断失败抛出指定异常.
     *
     * @param map 判断的集合
     * @param message 断言失败的信息
     */
    public static void isNotEmpty(Map<?, ?> map, String message) {
        ASSERT.isNotEmpty(map, message);
    }

    /**
     * Checks if is not empty.
     *
     * @param map the map
     * @param message the message
     */
    public static void isNotEmpty(Map<?, ?> map, Supplier<String> message) {
        ASSERT.isNotEmpty(map, message);
    }

    /**
     * 判断传入文件对象代表的物理文件是否存在，判断失败抛出指定异常.
     *
     * @param file 判断的文件对象
     * @param message 断言失败的信息
     */
    public static void isExists(File file, String message) {
        ASSERT.isExists(file, message);
    }

    /**
     * Checks if is exists.
     *
     * @param file the file
     * @param message the message
     */
    public static void isExists(File file, Supplier<String> message) {
        ASSERT.isExists(file, message);
    }

    /**
     * 判断传入文件对象代表的物理文件是否存在，判断失败抛出指定异常.
     *
     * @param file 判断的文件对象
     */
    public static void isExists(File file) {
        ASSERT.isExists(file);
    }

    /**
     * Checks if is instance of.
     *
     * @param clazz the clazz
     * @param obj the obj
     * @param message the message
     */
    public static void isInstanceOf(Class<?> clazz, Object obj, Supplier<String> message) {
        ASSERT.isInstanceOf(clazz, obj, message);
    }

    /**
     * 判断对象（第二个参数）是指定类型（第一个参数）的实例，判断失败抛出指定异常.
     *
     * @param clazz 类型
     * @param obj 对象
     * @param message 断言失败的信息
     */
    public static void isInstanceOf(Class<?> clazz, Object obj, String message) {
        ASSERT.isInstanceOf(clazz, obj, message);
    }

    /**
     * 判断对象（第二个参数）是指定类型（第一个参数）的实例，判断失败抛出指定异常.
     *
     * @param clazz 类型
     * @param obj 对象
     */
    public static void isInstanceOf(Class<?> clazz, Object obj) {
        ASSERT.isInstanceOf(clazz, obj);
    }
}
