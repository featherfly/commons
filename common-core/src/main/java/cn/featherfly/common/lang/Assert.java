package cn.featherfly.common.lang;

import java.io.File;
import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 断言工具类，对于满足断言的情况，抛出指定异常 一般用于检查传入参数是否合法.
 *
 * @author zhongj
 * @version 1.0
 * @param <E> the element type
 * @since 1.0
 */
public class Assert<E extends RuntimeException> {

    /** The exception function. */
    protected Function<String, E> exceptionFunction;

    /**
     * Instantiates a new assert.
     *
     * @param exceptionFunction the exception function
     */
    public Assert(Function<String, E> exceptionFunction) {
        this.exceptionFunction = exceptionFunction;
    }

    /**
     * Instantiates a new assert.
     *
     * @param exceptionType the exception type
     */
    @Deprecated
    Assert(Class<E> exceptionType) {
        exceptionFunction = (message) -> ClassUtils.newInstance(exceptionType, message);
    }

    /**
     * 判断为空，如果不为空，抛出指定异常.
     *
     * @param object 判断的对象
     * @param message 断言失败的信息
     */
    public void isNull(Object object, String message) {
        if (object != null) {
            throwException(message);
        }
    }

    /**
     * 判断为空，如果不为空，抛出指定异常.
     *
     * @param object 判断的对象
     * @param message 断言失败的信息
     */
    public void isNull(Object object, Supplier<String> message) {
        isNull(object, nullSafeGet(message));
    }

    /**
     * 判断不为空，如果为空，抛出指定异常.
     *
     * @param object 判断的对象
     * @param message 断言失败的信息
     */
    public void isNotNull(Object object, String message) {
        if (object == null) {
            throwException(message);
        }
    }

    /**
     * 判断不为空，如果为空，抛出指定异常.
     *
     * @param object 判断的对象
     * @param message 断言失败的信息
     */
    public void isNotNull(Object object, Supplier<String> message) {
        isNotNull(object, nullSafeGet(message));
    }

    /**
     * 判断不为空，如果为空，抛出指定异常.
     *
     * @param object 判断的对象
     * @deprecated use {@link #isNotNull(Object, String)} instead
     */
    @Deprecated
    public void isNotNull(Object object) {
        isNotNull(object, "参数不能为null");
    }

    /**
     * 判断为true，如果为false，抛出指定异常.
     *
     * @param expression 判断的值
     * @param message 断言失败的信息
     */
    public void isTrue(boolean expression, String message) {
        if (!expression) {
            throwException(message);
        }
    }

    /**
     * 判断为true，如果为false，抛出指定异常.
     *
     * @param expression 判断的值
     * @param message 断言失败的信息
     */
    public void isTrue(boolean expression, Supplier<String> message) {
        isTrue(expression, nullSafeGet(message));
    }

    /**
     * 判断为false，如果为true，抛出指定异常.
     *
     * @param expression 判断的值
     * @param message 断言失败的信息
     */
    public void isFalse(boolean expression, String message) {
        if (expression) {
            throwException(message);
        }
    }

    /**
     * 判断为false，如果为true，抛出指定异常.
     *
     * @param expression 判断的值
     * @param message 断言失败的信息
     */
    public void isFalse(boolean expression, Supplier<String> message) {
        isFalse(expression, nullSafeGet(message));
    }

    /**
     * 判断不为空或空串（包括只有空字符的串），判断失败抛出指定异常.
     *
     * @param text 判断的字符串
     * @param message 断言失败的信息
     */
    public void isNotBlank(String text, String message) {
        if (!Strings.isNotBlank(text)) {
            throwException(message);
        }
    }

    /**
     * 判断不为空或空串（包括只有空字符的串），判断失败抛出指定异常.
     *
     * @param text 判断的字符串
     * @param message 断言失败的信息
     */
    public void isNotBlank(String text, Supplier<String> message) {
        isNotBlank(text, nullSafeGet(message));
    }

    /**
     * 判断不为空或空串（包括只有空字符的串），判断失败抛出指定异常.
     *
     * @param text 判断的字符串
     * @deprecated use {@link #isNotBlank(String, String)} instead
     */
    @Deprecated
    public void isNotBlank(String text) {
        isNotBlank(text, "参数必须是一个有效字符串;null，空字符串，只有空白字符的字符串都不是有效字符串");
    }

    /**
     * 判断为空（String,Collection,Map,Array还要判断长度是否为0），判断失败抛出指定异常.
     *
     * @param obj 判断的对象
     * @param message 断言失败的信息
     */
    public void isEmpty(Object obj, String message) {
        if (Lang.isNotEmpty(obj)) {
            throwException(message);
        }
    }

    /**
     * 判断为空（String,Collection,Map,Array还要判断长度是否为0），判断失败抛出指定异常.
     *
     * @param obj 判断的对象
     * @param message 断言失败的信息
     */
    public void isEmpty(Object obj, Supplier<String> message) {
        isEmpty(obj, nullSafeGet(message));
    }

    /**
     * 判断不为空（String,Collection,Map,Array还要判断长度是否为0），判断失败抛出指定异常.
     *
     * @param obj 判断的对象
     * @param message 断言失败的信息
     */
    public void isNotEmpty(Object obj, String message) {
        if (Lang.isEmpty(obj)) {
            throwException(message);
        }
    }

    /**
     * 判断不为空（String,Collection,Map,Array还要判断长度是否为0），判断失败抛出指定异常.
     *
     * @param obj 判断的对象
     * @param message 断言失败的信息
     */
    public void isNotEmpty(Object obj, Supplier<String> message) {
        isNotEmpty(obj, nullSafeGet(message));
    }

    /**
     * 判断不为空（String,Collection,Map,Array还要判断长度是否为0），判断失败抛出指定异常.
     *
     * @param obj 判断的对象
     * @deprecated use {@link #isNotEmpty(Object, String)} instead
     */
    @Deprecated
    public void isNotEmpty(Object obj) {
        isNotEmpty(obj, "参数不能为空");
    }

    /**
     * 判断不为空或空串，判断失败抛出指定异常.
     *
     * @param text 判断的字符串
     * @param message 断言失败的信息
     */
    public void isNotEmpty(String text, String message) {
        if (!Lang.isNotEmpty(text)) {
            throwException(message);
        }
    }

    /**
     * 判断不为空或空串，判断失败抛出指定异常.
     *
     * @param text 判断的字符串
     * @param message 断言失败的信息
     */
    public void isNotEmpty(String text, Supplier<String> message) {
        isNotEmpty(text, nullSafeGet(message));
    }

    /**
     * 判断不为空或空串，判断失败抛出指定异常.
     *
     * @param text 判断的字符串
     * @deprecated use {@link #isNotEmpty(String, String)} instead
     */
    @Deprecated
    public void isNotEmpty(String text) {
        isNotEmpty(text, "参数不能为空（null，空字符串）");
    }

    /**
     * 判断数组不为null或size不为0，判断失败抛出指定异常.
     *
     * @param array 需要判断的数组
     * @param message 断言失败的信息
     */
    public void isNotEmpty(Object[] array, String message) {
        if (Lang.isEmpty(array)) {
            throwException(message);
        }
    }

    /**
     * 判断数组不为null或size不为0，判断失败抛出指定异常.
     *
     * @param array 需要判断的数组
     * @param message 断言失败的信息
     */
    public void isNotEmpty(Object[] array, Supplier<String> message) {
        isNotEmpty(array, nullSafeGet(message));
    }

    /**
     * 判断数组不为null或size不为0，判断失败抛出指定异常.
     *
     * @param array 需要判断的数组
     * @deprecated use {@link #isNotEmpty(Object[], String)} instead
     */
    @Deprecated
    public void isNotEmpty(Object[] array) {
        isNotEmpty(array, "参数数组不能为null且长度不能为0");
    }

    /**
     * 判断集合不为null或size不为0，判断失败抛出指定异常.
     *
     * @param collection 判断的集合
     * @param message 断言失败的信息
     */
    public void isNotEmpty(Collection<?> collection, String message) {
        if (Lang.isEmpty(collection)) {
            throwException(message);
        }
    }

    /**
     * 判断集合不为null或size不为0，判断失败抛出指定异常.
     *
     * @param collection 判断的集合
     * @param message 断言失败的信息
     */
    public void isNotEmpty(Collection<?> collection, Supplier<String> message) {
        isNotEmpty(collection, nullSafeGet(message));
    }

    /**
     * 判断集合不为null或size不为0，判断失败抛出指定异常.
     *
     * @param collection 判断的集合
     * @deprecated use {@link #isNotEmpty(Collection, String)} instead
     */
    @Deprecated
    public void isNotEmpty(Collection<?> collection) {
        isNotEmpty(collection, "参数collection不能为null且长度不能为0");
    }

    /**
     * 判断MAP不为null或size不为0，判断失败抛出指定异常.
     *
     * @param map 判断的集合
     * @param message 断言失败的信息
     */
    public void isNotEmpty(Map<?, ?> map, String message) {
        if (Lang.isEmpty(map)) {
            throwException(message);
        }
    }

    /**
     * 判断MAP不为null或size不为0，判断失败抛出指定异常.
     *
     * @param map 判断的集合
     * @param message 断言失败的信息
     */
    public void isNotEmpty(Map<?, ?> map, Supplier<String> message) {
        isNotEmpty(map, nullSafeGet(message));
    }

    /**
     * 判断MAP不为null或size不为0，判断失败抛出指定异常.
     *
     * @param map 判断的集合
     * @deprecated use {@link #isNotEmpty(Map, String)} instead
     */
    @Deprecated
    public void isNotEmpty(Map<?, ?> map) {
        isNotEmpty(map, "参数map不能为null且长度不能为0");
    }

    /**
     * 判断传入文件对象代表的物理文件是否存在，判断失败抛出指定异常.
     *
     * @param file 判断的文件对象
     * @param message 断言失败的信息
     */
    public void isExists(File file, String message) {
        if (!Lang.isExists(file)) {
            throwException(message);
        }
    }

    /**
     * 判断传入文件对象代表的物理文件是否存在，判断失败抛出指定异常.
     *
     * @param file 判断的文件对象
     * @param message 断言失败的信息
     */
    public void isExists(File file, Supplier<String> message) {
        isExists(file, nullSafeGet(message));
    }

    /**
     * 判断传入文件对象代表的物理文件是否存在，判断失败抛出指定异常.
     *
     * @param file 判断的文件对象
     */
    public void isExists(File file) {
        isExists(file, "参数file不能为null且文件必须存在");
    }

    /**
     * 判断对象（第二个参数）是指定类型（第一个参数）的实例，判断失败抛出指定异常.
     *
     * @param clazz 类型
     * @param obj 对象
     * @param message 断言失败的信息
     */
    public void isInstanceOf(Class<?> clazz, Object obj, String message) {
        isNotNull(clazz, "参数clazz不能为空");
        isTrue(clazz.isInstance(obj),
            new StringBuilder().append(message).append("参数obj '")
                .append(obj == null ? "[null]" : obj.getClass().getName()).append("' 必须是参数clazz '")
                .append(clazz.getName()).append("' 的实例！").toString());
    }

    /**
     * 判断对象（第二个参数）是指定类型（第一个参数）的实例，判断失败抛出指定异常.
     *
     * @param clazz 类型
     * @param obj 对象
     * @param message 断言失败的信息
     */
    public void isInstanceOf(Class<?> clazz, Object obj, Supplier<String> message) {
        isInstanceOf(clazz, obj, nullSafeGet(message));
    }

    /**
     * 判断对象（第二个参数）是指定类型（第一个参数）的实例，判断失败抛出指定异常.
     *
     * @param clazz 类型
     * @param obj 对象
     */
    public void isInstanceOf(Class<?> clazz, Object obj) {
        isInstanceOf(clazz, obj, "");
    }

    private void throwException(String msg) {
        throw exceptionFunction.apply(msg);
    }

    private static String nullSafeGet(Supplier<String> messageSupplier) {
        return messageSupplier != null ? messageSupplier.get() : null;
    }
}
