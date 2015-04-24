package cn.featherfly.common.lang;

import java.io.File;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 *
 * <p>
 * 对一些在语法上显得拖沓的常用操作进行封装的工具类
 * </p>
 * @author 钟冀
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
     * @param <T> 泛型
     * @param target 目标参数
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
     * @param <T>
     *            泛型
     * @param pickedItems
     *            需要选择的对象
     * @return 第一个非空的对象
     */
    @SafeVarargs
    public static <T> T pickFirst(T...pickedItems) {
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
     * @param <T>
     *            泛型
     * @param pickedItems
     *            需要选择的对象
     * @return 最后一个非空的对象
     */
    @SafeVarargs
    public static <T> T pickLast(T...pickedItems) {
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
     * @param object
     *            传入的对象
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
     * @param object
     *            传入的对象
     * @return 传入对象是否不为空
     */
    public static boolean isNotEmpty(Object object) {
        return !isEmpty(object);
    }


    /**
     * <p>
     * 返回传入字符串是否为空（是null或是空字符串）
     * </p>
     * @param string
     *            传入的字符串
     * @return 传入字符串是否为空
     */
    public static boolean isEmpty(String string) {
        return string == null || "".equals(string);
    }

    /**
     * <p>
     * 返回传入字符串是否不为空（不是null或不是空字符串）
     * </p>
     * @param string
     *            传入的字符串
     * @return 传入字符串是否不为空
     */
    public static boolean isNotEmpty(String string) {
        return !isEmpty(string);
    }

    /**
     * <p>
     * 返回数组是否为空（是null或是空数组）
     * </p>
     * @param array
     *            传入的数组
     * @return 传入数组是否为空
     */
    public static boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

    /**
     * <p>
     * 返回数组是否不为空（null或空数组）
     * </p>
     * @param array
     *            传入的数组
     * @return 传入数组是否不为空
     */
    public static boolean isNotEmpty(Object[] array) {
        return !isEmpty(array);
    }

    /**
     * <p>
     * 返回传入集合是否为空（是null或size=0）
     * </p>
     * @param collection
     *            传入的集合
     * @return 传入集合是否为空
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }
    /**
     * <p>
     * 返回传入集合是否不为空（不是null或size>0）
     * </p>
     * @param collection
     *            传入的集合
     * @return 传入集合是否不为空
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }
    /**
     * <p>
     * 返回传入map是否为空（是null或size=0）
     * </p>
     * @param map
     *            传入的map
     * @return 传入map是否为空
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }
    /**
     * <p>
     * 返回传入map是否不为空（不是null或size>0）
     * </p>
     * @param map
     *            传入的map
     * @return 传入map是否不为空
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    /**
     * <p>
     * 判断传入文件对象代表的物理文件是否存在
     * </p>
     * @param file 判断的文件
     * @return 传入文件对象代表的物理文件是否存在
     */
    public static boolean isExists(File file) {
        return file != null && file.exists();
    }
    
    /**
     * <p>
     * 将传入对象转换为枚举
     * </p>
     * @param toClass 枚举的类型
     * @param object 需要转换的对象
     * @param <T> 泛型
     * @return 转换后的枚举，如果是无法转换或不存在的枚举类型，则返回null
     */
    public static <T extends Enum<T>> T toEnum(Class<T> toClass, Object object) {
        if (object != null) {
            if (object instanceof String[]) {
                return Enum.valueOf(toClass, ((String[]) object)[0]);
            } else if (object instanceof String) {
                return Enum.valueOf(toClass, (String) object);
            } else if (object instanceof Integer || object.getClass() == int.class) {
                Integer ordinal = (Integer) object;
                T[] es = (T[]) toClass.getEnumConstants();
                for (T e : es) {
                    if (e.ordinal() == ordinal) {
                        return (T) e;
                    }
                }
            }
        }
        return null;
    }
    
    /**
     * <p>
     * 安全的equals，防止空指针异常
     * </p>
     * @param target 比较对象
     * @param otherTarget 另一个比较对象
     * @return 比较结果
     */
    public static boolean equals(Object target, Object otherTarget) {
        return (target == otherTarget) || (target != null && target.equals(otherTarget));
    }
    
     /**
     * 返回hash code，如果传入参数为null,返回0.
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
     * @param obj obj
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
     * @param obj obj
     * @return string
     */
    public static String toString(Object obj) {
        return toString(obj, null);
    }
}
