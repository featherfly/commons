package cn.featherfly.common.lang;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.featherfly.common.constant.Chars;

/**
 * <p>
 * 数组的工具类
 * </p>
 *
 * @author zhongj
 * @since 1.0
 * @version 1.0
 */
public final class ArrayUtils {

    private ArrayUtils() {
    }

    /**
     * <p>
     * 返回传入数组是否为空（是null或size=0）. 当传入对象不是数组时，只会进行null的判断
     * </p>
     *
     * @param array 传入的数组
     * @return 传入数组是否为空
     */
    public static boolean isEmpty(Object array) {
        if (array == null) {
            return true;
        }
        if (array.getClass().isArray()) {
            return Array.getLength(array) == 0;
        }
        return false;
    }

    /**
     * <p>
     * 返回传入数组是否不为空（是null或size=0）. 当传入对象不是数组时，只会进行null的判断
     * </p>
     *
     * @param array 传入的数组
     * @return 传入数组是否不为空
     */
    public static boolean isNotEmpty(Object array) {
        return !isEmpty(array);
    }

    /**
     * <p>
     * 将传入数组进行字符串转换（与Collection的一样使用,分割）
     * </p>
     *
     * @param array 对象数组
     * @return 字符串
     */
    public static String toString(Object array) {
        StringBuilder sb = new StringBuilder();
        if (array != null) {
            Class<?> type = array.getClass();
            if (type.isArray()) {
                sb.append(Chars.BRACK_L);
                StringBuilder result = new StringBuilder();
                for (int i = 0; i < Array.getLength(array); i++) {
                    result.append(Array.get(array, i)).append(Chars.COMMA);
                }
                if (result.length() > 0) {
                    result.deleteCharAt(result.length() - 1);
                }
                sb.append(result.toString()).append(Chars.BRACK_R);
            }
        }
        return sb.toString();
    }

    /**
     * <p>
     * 将传入数组进行字符串转换（与Collection的一样使用,分割）
     * </p>
     *
     * @param <E>     对象类型
     * @param objects 对象数组
     * @return 字符串
     */
    @SafeVarargs
    public static <E> String toString(E... objects) {
        StringBuilder sb = new StringBuilder();
        sb.append(Chars.BRACK_L);
        if (objects != null && objects.length > 0) {
            for (Object object : objects) {
                sb.append(object.toString()).append(Chars.COMMA);
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append(Chars.BRACK_R);
        return sb.toString();
    }

    /**
     * <p>
     * 转换为列表
     * </p>
     *
     * @param arrays 数组
     * @param <T>    泛型
     * @return 列表
     */
    public static <T> List<T> toList(T[] arrays) {
        if (arrays == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(Arrays.asList(arrays));
    }

    /**
     * <p>
     * fill target array with source array
     * </p>
     *
     * @param <T>    泛型
     * @param target fill target
     * @param source fill source
     */
    public static <T> void fill(T[] target, T[] source) {
        int len = target.length;
        if (len > source.length) {
            len = source.length;
        }
        for (int i = 0; i < len; i++) {
            target[i] = source[i];
        }
    }

    /**
     * <p>
     * 判断第一个传入的数组中是否存在第二个参数
     * </p>
     *
     * @param <T>    泛型
     * @param tSet   源数组
     * @param target 查找对象
     * @return 第一个数组中是否存在第二个对象
     */
    public static <T> boolean contain(T[] tSet, T target) {
        if (tSet == null || target == null) {
            return false;
        }
        for (T t : tSet) {
            if (target.equals(t)) {
                return true;
            }
        }
        return false;
    }

    /**
     * <p>
     * 判断第一个传入的字符串数组中是否存在第二个传入的字符串
     *
     * @param strSet     源字符串数组
     * @param strTarget  查找字符串
     * @param ignoreCase 忽略大小写
     * @return 第一个数组中是否存在第二个字符串
     */
    public static boolean containString(String[] strSet, String strTarget, boolean ignoreCase) {
        if (strSet == null || strTarget == null) {
            return false;
        }
        boolean result = false;
        for (String str : strSet) {
            if (ignoreCase) {
                if (strTarget.equalsIgnoreCase(str)) {
                    result = true;
                    break;
                }
            } else {
                if (strTarget.equals(str)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * <p>
     * 数组链接
     * </p>
     *
     * @param arr1 arr1
     * @param arr2 arr2
     * @return 链接后的数组
     */
    public static Object concat(Object arr1, Object arr2) {
        int len1 = arr1 != null ? Array.getLength(arr1) : -1;
        if (len1 <= 0) {
            return arr2;
        }
        int len2 = arr2 != null ? Array.getLength(arr2) : -1;
        if (len2 <= 0) {
            return arr1;
        } else {
            Class<?> commonComponentType = ClassUtils.parentClass(arr1.getClass().getComponentType(),
                    arr2.getClass().getComponentType());
            Object newArray = Array.newInstance(commonComponentType, len1 + len2);
            System.arraycopy(arr1, 0, newArray, 0, len1);
            System.arraycopy(arr2, 0, newArray, len1, len2);
            return newArray;
        }
    }

    /**
     * <p>
     * 创建数组
     * </p>
     *
     * @param type   类型
     * @param length 长度
     * @param <T>    泛型
     * @return 数组
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] create(Class<T> type, int length) {
        Object o = Array.newInstance(type, length);
        return (T[]) o;
    }
}
