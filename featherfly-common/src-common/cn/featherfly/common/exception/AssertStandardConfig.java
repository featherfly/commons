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

	private static final Assert a = new Assert(StandardConfigException.class);

	private AssertStandardConfig() {
	}

	/**
	 * <p>
	 * 判断不为空，如果为空，抛出StandardConfigException异常
	 * </p>
	 * @param object 判断的对象
	 * @param message 断言失败的信息
	 * @return 传入的参数
	 */
	public static <T> T isNotNull(T object, String message) {
		return a.isNotNull(object, message);
	}

	/**
	 * <p>
	 * 判断不为空，如果为空，抛出StandardConfigException异常
	 * </p>
	 * @param object 判断的对象
	 * @return 传入的参数
	 */
	public static <T> T isNotNull(T object) {
		return a.isNotNull(object);
	}

	/**
	 * <p>
	 * 判断为true，如果为false，抛出StandardConfigException异常
	 * </p>
	 * @param expression 判断的值
	 * @param message 断言失败的信息
	 * @return 传入的参数
	 */
	public static boolean isTrue(boolean expression, String message) {
		return a.isTrue(expression, message);
	}

	/**
	 * <p>
	 * 判断为false，如果为true，抛出StandardConfigException异常
	 * </p>
	 * @param expression 判断的值
	 * @param message 断言失败的信息
	 * @return 传入的参数
	 */
	public static boolean isFalse(boolean expression, String message) {
		return a.isFalse(expression, message);
	}

	/**
	 * <p>
	 * 判断不为空或空串（包括只有空字符的串），判断失败抛出StandardConfigException异常
	 * </p>
	 * @param text 判断的字符串
	 * @param message 断言失败的信息
	 * @return 传入的参数
	 */
	public static String isNotBlank(String text, String message) {
		return a.isNotBlank(text, message);
	}

	/**
	 * <p>
	 * 判断不为空或空串（包括只有空字符的串），判断失败抛出StandardConfigException异常
	 * </p>
	 * @param text 判断的字符串
	 * @return 传入的参数
	 */
	public static String isNotBlank(String text) {
		return a.isNotBlank(text);
	}

	/**
	 * <p>
	 * 判断不为空（String,Collection,Map,Array还要判断长度是否为0），判断失败抛出StandardConfigException异常
	 * </p>
	 * @param obj 判断的对象
	 * @param message 断言失败的信息
	 * @return 传入的参数
	 */
	public static <T> T isNotEmpty(T obj, String message) {
		return a.isNotEmpty(obj, message);
	}

	/**
	 * <p>
	 * 判断不为空（String,Collection,Map,Array还要判断长度是否为0），判断失败抛出StandardConfigException异常
	 * </p>
	 * @param obj 判断的对象
	 * @return 传入的参数
	 */
	public static <T> T isNotEmpty(T obj) {
		return a.isNotEmpty(obj);
	}
	/**
	 * <p>
	 * 判断不为空或空串，判断失败抛出StandardConfigException异常
	 * </p>
	 * @param text 判断的字符串
	 * @param message 断言失败的信息
	 * @return 传入的参数
	 */
	public static String isNotEmpty(String text, String message) {
		return a.isNotEmpty(text, message);
	}

	/**
	 * <p>
	 * 判断不为空或空串，判断失败抛出StandardConfigException异常
	 * </p>
	 * @param text 判断的字符串
	 * @return 传入的参数
	 */
	public static String isNotEmpty(String text) {
		return a.isNotEmpty(text);
	}

	/**
	 * <p>
	 * 判断数组不为null或size不为0，判断失败抛出StandardConfigException异常
	 * </p>
	 * @param array 需要判断的数组
	 * @param message 断言失败的信息
	 * @return 传入的参数
	 */
	public static <T> T[] isNotEmpty(T[] array, String message) {
		return a.isNotEmpty(array, message);
	}

	/**
	 * <p>
	 * 判断数组不为null或size不为0，判断失败抛出StandardConfigException异常
	 * </p>
	 * @param array 需要判断的数组
	 * @return 传入的参数
	 */
	public static <T> T[] isNotEmpty(T[] array) {
		return a.isNotEmpty(array);
	}

	/**
	 * <p>
	 * 判断集合不为null或size不为0，判断失败抛出StandardConfigException异常
	 * </p>
	 * @param collection 判断的集合
	 * @param message 断言失败的信息
	 * @return 传入的参数
	 */
	public static Collection<?> isNotEmpty(Collection<?> collection, String message) {
		return a.isNotEmpty(collection, message);
	}

	/**
	 * <p>
	 * 判断集合不为null或size不为0，判断失败抛出StandardConfigException异常
	 * </p>
	 * @param collection 判断的集合
	 * @return 传入的参数
	 */
	public static Collection<?> isNotEmpty(Collection<?> collection) {
		return a.isNotEmpty(collection);
	}

	/**
	 * <p>
	 * 判断MAP不为null或size不为0，判断失败抛出StandardConfigException异常
	 * </p>
	 * @param map 判断的集合
	 * @param message 断言失败的信息
	 * @return 传入的参数
	 */
	public static Map<?, ?> isNotEmpty(Map<?, ?> map, String message) {
		return a.isNotEmpty(map, message);
	}

	/**
	 * <p>
	 * 判断MAP不为null或size不为0，判断失败抛出StandardConfigException异常
	 * </p>
	 * @param map 判断的集合
	 * @return 传入的参数
	 */
	public static Map<?, ?> isNotEmpty(Map<?, ?> map) {
		return a.isNotEmpty(map);
	}

	/**
	 * <p>
	 * 判断传入文件对象代表的物理文件是否存在，判断失败抛出StandardConfigException异常
	 * </p>
	 * @param file 判断的文件对象
	 * @param message 断言失败的信息
	 * @return 传入的参数
	 */
	public static File isExists(File file , String message) {
		return a.isExists(file, message);
	}

	/**
	 * <p>
	 * 判断传入文件对象代表的物理文件是否存在，判断失败抛出StandardConfigException异常
	 * </p>
	 * @param file 判断的文件对象
	 * @return 传入的参数
	 */
	public static File isExists(File file) {
		return a.isExists(file);
	}

	/**
	 * <p>
	 * 判断对象（第二个参数）是指定类型（第一个参数）的实例，判断失败抛出StandardConfigException异常
	 * </p>
	 * @param clazz 类型
	 * @param obj 对象
	 * @param message 断言失败的信息
	 * @return 传入的参数
	 */
	public static <T> T isInstanceOf(Class<?> clazz, T obj, String message) {
		return a.isInstanceOf(clazz, obj, message);
	}

	/**
	 * <p>
	 * 判断对象（第二个参数）是指定类型（第一个参数）的实例，判断失败抛出StandardConfigException异常
	 * </p>
	 * @param clazz 类型
	 * @param obj 对象
	 * @return 传入的参数
	 */
	public static <T> T isInstanceOf(Class<?> clazz, T obj) {
		return a.isInstanceOf(clazz, obj);
	}
}
