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
public class Assert {

//    private static final String ASSERT_TITLE = "[断言失败] - ";

    private Class<?> exception;

    /**
     * @param exceptionType 断言失败抛出的异常类型
     * @param <E> 泛型
     */
    public <E extends RuntimeException> Assert(Class<E> exceptionType) {
        this.exception = exceptionType;
    }

    /**
     * <p>
     * 判断不为空，如果为空，抛出指定异常
     * </p>
     * @param object 判断的对象
     * @param message 断言失败的信息
     * @传入的参数
     */
    public void isNotNull(Object object, String message) {
        if (object == null) {
            throwException(message);
        }
    }
    
    /**
     * <p>
     * 判断不为空，如果为空，抛出指定异常
     * </p>
     * @param object 判断的对象
     * @传入的参数
     */
    public void isNotNull(Object object) {
        isNotNull(object,
                "参数不能为null");        
    }

    /**
     * <p>
     * 判断为true，如果为false，抛出指定异常
     * </p>
     * @param expression 判断的值
     * @param message 断言失败的信息
     * @传入的参数
     */
    public void isTrue(boolean expression, String message) {
        if (!expression) {
            throwException(message);
        }
    }

    /**
     * <p>
     * 判断为false，如果为true，抛出指定异常
     * </p>
     * @param expression 判断的值
     * @param message 断言失败的信息
     * @传入的参数
     */
    public void isFalse(boolean expression, String message) {
        if (expression) {
            throwException(message);
        }
    }

    /**
     * <p>
     * 判断不为空或空串（包括只有空字符的串），判断失败抛出指定异常
     * </p>
     * @param text 判断的字符串
     * @param message 断言失败的信息
     * @传入的参数
     */
    public void isNotBlank(String text, String message) {
        if (!StringUtils.isNotBlank(text)) {
            throwException(message);
        }
    }

    /**
     * <p>
     * 判断不为空或空串（包括只有空字符的串），判断失败抛出指定异常
     * </p>
     * @param text 判断的字符串
     * @传入的参数
     */
    public void isNotBlank(String text) {
        isNotBlank(
                text,
                "参数必须是一个有效字符串;null，空字符串，只有空白字符的字符串都不是有效字符串");
    }

    /**
     * <p>
     * 判断不为空（String,Collection,Map,Array还要判断长度是否为0），判断失败抛出指定异常
     * </p>
     * @param obj 判断的对象
     * @param message 断言失败的信息
     * @传入的参数
     */
    public void isNotEmpty(Object obj, String message) {
        if (!LangUtils.isNotEmpty(obj)) {
            throwException(message);
        }
    }

    /**
     * <p>
     * 判断不为空（String,Collection,Map,Array还要判断长度是否为0），判断失败抛出指定异常
     * </p>
     * @param obj 判断的对象
     * @传入的参数
     */
    public void isNotEmpty(Object obj) {
        isNotEmpty(
                obj, "参数不能为空");
    }
    /**
     * <p>
     * 判断不为空或空串，判断失败抛出指定异常
     * </p>
     * @param text 判断的字符串
     * @param message 断言失败的信息
     * @传入的参数
     */
    public void isNotEmpty(String text, String message) {
        if (!LangUtils.isNotEmpty(text)) {
            throwException(message);
        }
    }

    /**
     * <p>
     * 判断不为空或空串，判断失败抛出指定异常
     * </p>
     * @param text 判断的字符串
     * @传入的参数
     */
    public void isNotEmpty(String text) {
        isNotEmpty(
                text, "参数不能为空（null，空字符串）");
    }

    /**
     * <p>
     * 判断数组不为null或size不为0，判断失败抛出指定异常
     * </p>
     * @param array 需要判断的数组
     * @param message 断言失败的信息
     * @传入的参数
     */
    public void isNotEmpty(Object[] array, String message) {
        if (LangUtils.isEmpty(array)) {
            throwException(message);
        }
    }

    /**
     * <p>
     * 判断数组不为null或size不为0，判断失败抛出指定异常
     * </p>
     * @param array 需要判断的数组
     * @传入的参数
     */
    public void isNotEmpty(Object[] array) {
        isNotEmpty(array,
                "参数数组不能为null且长度不能为0");
    }

    /**
     * <p>
     * 判断集合不为null或size不为0，判断失败抛出指定异常
     * </p>
     * @param collection 判断的集合
     * @param message 断言失败的信息
     * @传入的参数
     */
    public void isNotEmpty(Collection<?> collection, String message) {
        if (LangUtils.isEmpty(collection)) {
            throwException(message);
        }
    }

    /**
     * <p>
     * 判断集合不为null或size不为0，判断失败抛出指定异常
     * </p>
     * @param collection 判断的集合
     * @传入的参数
     */
    public void isNotEmpty(Collection<?> collection) {
        isNotEmpty(collection,
                "参数collection不能为null且长度不能为0");
    }

    /**
     * <p>
     * 判断MAP不为null或size不为0，判断失败抛出指定异常
     * </p>
     * @param map 判断的集合
     * @param message 断言失败的信息
     * @传入的参数
     */
    public void isNotEmpty(Map<?, ?> map, String message) {
        if (LangUtils.isEmpty(map)) {
            throwException(message);
        }
    }

    /**
     * <p>
     * 判断MAP不为null或size不为0，判断失败抛出指定异常
     * </p>
     * @param map 判断的集合
     * @传入的参数
     */
    public void isNotEmpty(Map<?, ?> map) {
        isNotEmpty(map,
            "参数map不能为null且长度不能为0");
    }

    /**
     * <p>
     * 判断传入文件对象代表的物理文件是否存在，判断失败抛出指定异常
     * </p>
     * @param file 判断的文件对象
     * @param message 断言失败的信息
     * @传入的参数
     */
    public void isExists(File file , String message) {
        if (!LangUtils.isExists(file)) {
            throwException(message);
        }
    }

    /**
     * <p>
     * 判断传入文件对象代表的物理文件是否存在，判断失败抛出指定异常
     * </p>
     * @param file 判断的文件对象
     * @传入的参数
     */
    public void isExists(File file) {
        isExists(file, "参数file不能为null且文件必须存在");
    }

    /**
     * <p>
     * 判断对象（第二个参数）是指定类型（第一个参数）的实例，判断失败抛出指定异常
     * </p>
     * @param clazz 类型
     * @param obj 对象
     * @param message 断言失败的信息
     * @传入的参数
     */
    public void isInstanceOf(Class<?> clazz, Object obj, String message) {
        isNotNull(clazz, "参数clazz不能为空");
        isTrue(clazz.isInstance(obj), new StringBuilder()
                        .append(message)
                        .append("参数obj '")
                        .append(obj == null ? "[null]" : obj.getClass().getName())
                        .append("' 必须是参数clazz '").append(clazz.getName())
                        .append("' 的实例！")
                        .toString());
    }

    /**
     * <p>
     * 判断对象（第二个参数）是指定类型（第一个参数）的实例，判断失败抛出指定异常
     * </p>
     * @param clazz 类型
     * @param obj 对象
     * @传入的参数
     */
    public void isInstanceOf(Class<?> clazz, Object obj) {
        isInstanceOf(clazz, obj, "");
    }

    @SuppressWarnings("unchecked")
    private void throwException(String msg) {
        throw ClassUtils.newInstance((Class<RuntimeException>) exception, msg);
    }
//
//    public static void main(String[] args) {
//        Assert asserts = new Assert(ClassCastException.class);
//        asserts.isNotNull(null);
//    }
}
