package cn.featherfly.common.exception;

import java.io.File;
import java.util.Collection;
import java.util.Map;

import cn.featherfly.common.lang.Assert;

/**
 * <p>
 * 断言工具类，对于满足断言的情况，抛出StandardConfigException异常
 * 一般用于检查传入参数是否合法
 * </p>
 *
 * @author 钟冀
 * @since 1.0
 * @version 1.0
 */
public final class AssertStandardConfig {

    private static final Assert ASSERT = new Assert(StandardConfigException.class);

    private AssertStandardConfig() {
    }

    /**
     * <p>
     * 判断不为空，如果为空，抛出StandardConfigException异常
     * </p>
     * @param object 判断的对象
     * @param message 断言失败的信息
     * 
     */
    public static void isNotNull(Object object, String message) {
        ASSERT.isNotNull(object, message);
    }

    /**
     * <p>
     * 判断不为空，如果为空，抛出StandardConfigException异常
     * </p>
     * @param object 判断的对象
     * 
     */
    public static void isNotNull(Object object) {
        ASSERT.isNotNull(object);
    }

    /**
     * <p>
     * 判断为true，如果为false，抛出StandardConfigException异常
     * </p>
     * @param expression 判断的值
     * @param message 断言失败的信息
     * 
     */
    public static void isTrue(boolean expression, String message) {
        ASSERT.isTrue(expression, message);
    }

    /**
     * <p>
     * 判断为false，如果为true，抛出StandardConfigException异常
     * </p>
     * @param expression 判断的值
     * @param message 断言失败的信息
     * 
     */
    public static void isFalse(boolean expression, String message) {
        ASSERT.isFalse(expression, message);
    }

    /**
     * <p>
     * 判断不为空或空串（包括只有空字符的串），判断失败抛出StandardConfigException异常
     * </p>
     * @param text 判断的字符串
     * @param message 断言失败的信息
     * 
     */
    public static void isNotBlank(String text, String message) {
        ASSERT.isNotBlank(text, message);
    }

    /**
     * <p>
     * 判断不为空或空串（包括只有空字符的串），判断失败抛出StandardConfigException异常
     * </p>
     * @param text 判断的字符串
     * 
     */
    public static void isNotBlank(String text) {
        ASSERT.isNotBlank(text);
    }

    /**
     * <p>
     * 判断不为空（String,Collection,Map,Array还要判断长度是否为0），判断失败抛出StandardConfigException异常
     * </p>
     * @param obj 判断的对象
     * @param message 断言失败的信息
     * 
     */
    public static void isNotEmpty(Object obj, String message) {
        ASSERT.isNotEmpty(obj, message);
    }

    /**
     * <p>
     * 判断不为空（String,Collection,Map,Array还要判断长度是否为0），判断失败抛出StandardConfigException异常
     * </p>
     * @param obj 判断的对象
     * 
     */
    public static void isNotEmpty(Object obj) {
        ASSERT.isNotEmpty(obj);
    }
    /**
     * <p>
     * 判断不为空或空串，判断失败抛出StandardConfigException异常
     * </p>
     * @param text 判断的字符串
     * @param message 断言失败的信息
     * 
     */
    public static void isNotEmpty(String text, String message) {
        ASSERT.isNotEmpty(text, message);
    }

    /**
     * <p>
     * 判断不为空或空串，判断失败抛出StandardConfigException异常
     * </p>
     * @param text 判断的字符串
     * 
     */
    public static void isNotEmpty(String text) {
        ASSERT.isNotEmpty(text);
    }

    /**
     * <p>
     * 判断数组不为null或size不为0，判断失败抛出StandardConfigException异常
     * </p>
     * @param array 需要判断的数组
     * @param message 断言失败的信息
     * 
     */
    public static void isNotEmpty(Object[] array, String message) {
        ASSERT.isNotEmpty(array, message);
    }

    /**
     * <p>
     * 判断数组不为null或size不为0，判断失败抛出StandardConfigException异常
     * </p>
     * @param array 需要判断的数组
     * 
     */
    public static void isNotEmpty(Object[] array) {
        ASSERT.isNotEmpty(array);
    }

    /**
     * <p>
     * 判断集合不为null或size不为0，判断失败抛出StandardConfigException异常
     * </p>
     * @param collection 判断的集合
     * @param message 断言失败的信息
     * 
     */
    public static void isNotEmpty(Collection<?> collection, String message) {
        ASSERT.isNotEmpty(collection, message);
    }

    /**
     * <p>
     * 判断集合不为null或size不为0，判断失败抛出StandardConfigException异常
     * </p>
     * @param collection 判断的集合
     * 
     */
    public static void isNotEmpty(Collection<?> collection) {
        ASSERT.isNotEmpty(collection);
    }

    /**
     * <p>
     * 判断MAP不为null或size不为0，判断失败抛出StandardConfigException异常
     * </p>
     * @param map 判断的集合
     * @param message 断言失败的信息
     * 
     */
    public static void isNotEmpty(Map<?, ?> map, String message) {
        ASSERT.isNotEmpty(map, message);
    }

    /**
     * <p>
     * 判断MAP不为null或size不为0，判断失败抛出StandardConfigException异常
     * </p>
     * @param map 判断的集合
     * 
     */
    public static void isNotEmpty(Map<?, ?> map) {
        ASSERT.isNotEmpty(map);
    }

    /**
     * <p>
     * 判断传入文件对象代表的物理文件是否存在，判断失败抛出StandardConfigException异常
     * </p>
     * @param file 判断的文件对象
     * @param message 断言失败的信息
     * 
     */
    public static void isExists(File file , String message) {
        ASSERT.isExists(file, message);
    }

    /**
     * <p>
     * 判断传入文件对象代表的物理文件是否存在，判断失败抛出StandardConfigException异常
     * </p>
     * @param file 判断的文件对象
     * 
     */
    public static void isExists(File file) {
        ASSERT.isExists(file);
    }

    /**
     * <p>
     * 判断对象（第二个参数）是指定类型（第一个参数）的实例，判断失败抛出StandardConfigException异常
     * </p>
     * @param clazz 类型
     * @param obj 对象
     * @param message 断言失败的信息
     * 
     */
    public static void isInstanceOf(Class<?> clazz, Object obj, String message) {
        ASSERT.isInstanceOf(clazz, obj, message);
    }

    /**
     * <p>
     * 判断对象（第二个参数）是指定类型（第一个参数）的实例，判断失败抛出StandardConfigException异常
     * </p>
     * @param clazz 类型
     * @param obj 对象
     * 
     */
    public static void isInstanceOf(Class<?> clazz, Object obj) {
        ASSERT.isInstanceOf(clazz, obj);
    }
}
