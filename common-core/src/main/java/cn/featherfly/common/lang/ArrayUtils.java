package cn.featherfly.common.lang;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.IntFunction;
import java.util.function.ObjIntConsumer;

import cn.featherfly.common.constant.Chars;

/**
 * 数组的工具类.
 *
 * @author zhongj
 * @version 1.0
 * @since 1.0
 */
public final class ArrayUtils {

    private ArrayUtils() {
    }

    /**
     * An empty immutable {@code Object} array.
     */
    public static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];
    /**
     * An empty immutable {@code Class} array.
     */
    public static final Class<?>[] EMPTY_CLASS_ARRAY = new Class[0];
    /**
     * An empty immutable {@code Serializable} array.
     */
    public static final Serializable[] EMPTY_SERIALIZABLE_ARRAY = new Serializable[0];
    /**
     * An empty immutable {@code String} array.
     */
    public static final String[] EMPTY_STRING_ARRAY = new String[0];
    /**
     * An empty immutable {@code long} array.
     */
    public static final long[] EMPTY_LONG_ARRAY = new long[0];
    /**
     * An empty immutable {@code Long} array.
     */
    public static final Long[] EMPTY_LONG_OBJECT_ARRAY = new Long[0];
    /**
     * An empty immutable {@code int} array.
     */
    public static final int[] EMPTY_INT_ARRAY = new int[0];
    /**
     * An empty immutable {@code Integer} array.
     */
    public static final Integer[] EMPTY_INTEGER_OBJECT_ARRAY = new Integer[0];
    /**
     * An empty immutable {@code short} array.
     */
    public static final short[] EMPTY_SHORT_ARRAY = new short[0];
    /**
     * An empty immutable {@code Short} array.
     */
    public static final Short[] EMPTY_SHORT_OBJECT_ARRAY = new Short[0];
    /**
     * An empty immutable {@code byte} array.
     */
    public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    /**
     * An empty immutable {@code Byte} array.
     */
    public static final Byte[] EMPTY_BYTE_OBJECT_ARRAY = new Byte[0];
    /**
     * An empty immutable {@code double} array.
     */
    public static final double[] EMPTY_DOUBLE_ARRAY = new double[0];
    /**
     * An empty immutable {@code Double} array.
     */
    public static final Double[] EMPTY_DOUBLE_OBJECT_ARRAY = new Double[0];
    /**
     * An empty immutable {@code float} array.
     */
    public static final float[] EMPTY_FLOAT_ARRAY = new float[0];
    /**
     * An empty immutable {@code Float} array.
     */
    public static final Float[] EMPTY_FLOAT_OBJECT_ARRAY = new Float[0];
    /**
     * An empty immutable {@code boolean} array.
     */
    public static final boolean[] EMPTY_BOOLEAN_ARRAY = new boolean[0];
    /**
     * An empty immutable {@code Boolean} array.
     */
    public static final Boolean[] EMPTY_BOOLEAN_OBJECT_ARRAY = new Boolean[0];
    /**
     * An empty immutable {@code char} array.
     */
    public static final char[] EMPTY_CHAR_ARRAY = new char[0];
    /**
     * An empty immutable {@code Character} array.
     */
    public static final Character[] EMPTY_CHARACTER_OBJECT_ARRAY = new Character[0];

    /**
     * 返回传入数组是否为空（是null或size=0）. 当传入对象不是数组时，只会进行null的判断.
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
     * 返回数组是否为空（是null或是空数组）.
     *
     * @param <E> the element type
     * @param array the array
     * @return 传入数组是否为空
     */
    public static <E> boolean isEmpty(E[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 返回传入数组是否不为空（是null或size=0）. 当传入对象不是数组时，只会进行null的判断.
     *
     * @param array 传入的数组
     * @return 传入数组是否不为空
     */
    public static boolean isNotEmpty(Object array) {
        return !isEmpty(array);
    }

    /**
     * 返回传入对象是否是数组.
     *
     * @param array the object
     * @return boolean is array
     */
    public static boolean isArray(Object array) {
        if (array == null) {
            return false;
        }
        return ClassUtils.isArray(array.getClass());
    }

    /**
     * Each.
     *
     * @param <T> the generic type
     * @param consumer the consumer
     * @param array the array
     */
    public static <T> void each(ObjIntConsumer<T> consumer, @SuppressWarnings("unchecked") T... array) {
        if (array == null) {
            return;
        }
        for (int i = 0; i < array.length; i++) {
            consumer.accept(array[i], i);
        }
    }

    /**
     * Each.
     *
     * @param array the array
     * @param consumer the consumer
     */
    public static void each(Object array, ObjIntConsumer<Object> consumer) {
        if (array == null) {
            return;
        }
        if (array.getClass().isArray()) {
            for (int i = 0; i < Array.getLength(array); i++) {
                consumer.accept(Array.get(array, i), i);
            }
        } else {
            consumer.accept(array, 0);
        }
    }

    /**
     * 将传入数组进行字符串转换,使用传入的符号作为数组之间的连接符号.
     *
     * @param <A> the generic type
     * @param array 对象数组
     * @param linkSymbol the link symbol
     * @return 字符串
     */
    public static <A> String toString(A[] array, char linkSymbol) {
        StringBuilder sb = new StringBuilder();
        for (A a : array) {
            if (a == null) {
                sb.append("null");
            } else {
                sb.append(a);
            }
            if (linkSymbol != '\0') {
                sb.append(linkSymbol);
            }
        }
        if (linkSymbol != '\0' && sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    /**
     * 将传入数组进行字符串转换（与Collection的一样使用,分割）.
     *
     * @param array 对象数组
     * @return 字符串
     */
    public static String toString(Object array) {
        if (array == null) {
            return Chars.EMPTY_STR;
        }
        if (array.getClass().isArray()) {
            StringBuilder sb = new StringBuilder();
            sb.append(Chars.BRACK_L);
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < Array.getLength(array); i++) {
                Object a = Array.get(array, i);
                if (a == null) {
                    result.append("null");
                } else {
                    result.append(a);
                }
                result.append(Chars.COMMA);
            }
            if (result.length() > 0) {
                result.deleteCharAt(result.length() - 1);
            }
            sb.append(result.toString()).append(Chars.BRACK_R);
            return sb.toString();
        } else {
            return array.toString();
        }
    }

    /**
     * 将传入数组进行字符串转换（与Collection的一样使用,分割）.
     *
     * @param <E> 对象类型
     * @param objects 对象数组
     * @return 字符串
     */
    @SafeVarargs
    public static <E> String toString(E... objects) {
        StringBuilder sb = new StringBuilder();
        sb.append(Chars.BRACK_L);
        if (objects != null && objects.length > 0) {
            for (Object object : objects) {
                if (object == null) {
                    sb.append("null");
                } else {
                    sb.append(object.toString());
                }
                sb.append(Chars.COMMA);
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append(Chars.BRACK_R);
        return sb.toString();
    }

    /**
     * To list.
     *
     * @param <T> the generic type
     * @param arrays the arrays
     * @return the list
     */
    public static <T> List<T> toList(@SuppressWarnings("unchecked") T... arrays) {
        return CollectionUtils.list(arrays);
    }

    /**
     * To set.
     *
     * @param <T> the generic type
     * @param arrays the arrays
     * @return the sets
     */
    public static <T> Set<T> toSet(@SuppressWarnings("unchecked") T... arrays) {
        return CollectionUtils.set(arrays);
    }

    /**
     * 转换为以数组索引为key,数组值为value的map.
     *
     * @param <A> the generic type
     * @param arrays 数组
     * @return map
     */
    public static <A> Map<Integer, A> toMap(@SuppressWarnings("unchecked") A... arrays) {
        if (arrays == null) {
            return new LinkedHashMap<>(0);
        }
        Map<Integer, A> map = new LinkedHashMap<>(arrays.length);
        for (int i = 0; i < arrays.length; i++) {
            map.put(i, arrays[i]);
        }
        return map;
    }

    /**
     * 转换为以数组索引为key(string类型),数组值为value的map.
     *
     * @param <A> the generic type
     * @param arrays 数组
     * @return 列表
     * @deprecated use {@link #toMapStringKey(Object...)} instead
     */
    @Deprecated
    public static <A> Map<String, A> toMap2(@SuppressWarnings("unchecked") A... arrays) {
        return toMapStringKey(arrays);
    }

    /**
     * 转换为以数组索引为key(string类型),数组值为value的map.
     *
     * @param <A> the generic type
     * @param arrays 数组
     * @return 列表
     */
    public static <A> Map<String, A> toMapStringKey(@SuppressWarnings("unchecked") A... arrays) {
        if (arrays == null) {
            return new LinkedHashMap<>(0);
        }
        Map<String, A> map = new LinkedHashMap<>(arrays.length);
        for (int i = 0; i < arrays.length; i++) {
            map.put(String.valueOf(i), arrays[i]);
        }
        return map;
    }

    /**
     * To number array.
     *
     * @param <A> the generic type
     * @param type the type
     * @param array the array
     * @return the a[]
     */
    public static <A extends Number> A[] toNumbers(Class<A> type, String... array) {
        int len = 0;
        if (array != null) {
            len = array.length;
        }
        A[] as = create(type, len);
        each((a, i) -> {
            as[i] = Num.parse(a, type);
        }, array);
        return as;
    }

    /**
     * fill target array with source array .
     *
     * @param <T> 泛型
     * @param target fill target
     * @param source fill source
     */
    public static <T> void fill(T[] target, T[] source) {
        int len = target.length;
        if (len > source.length) {
            len = source.length;
        }
        System.arraycopy(source, 0, target, 0, len);
    }

    /**
     * 判断第一个传入的数组中是否存在第二个参数 .
     *
     * @param <T> 泛型
     * @param array 源数组
     * @param target 查找对象
     * @return 第一个数组中是否存在第二个对象
     */
    public static <T> boolean contain(T[] array, T target) {
        if (array == null || target == null) {
            return false;
        }
        for (T t : array) {
            if (target.equals(t)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断第一个传入的字符串数组中是否存在第二个传入的字符串.
     *
     * @param array 源字符串数组
     * @param strTarget 查找字符串
     * @param ignoreCase 忽略大小写
     * @return 第一个数组中是否存在第二个字符串
     */
    public static boolean contain(String[] array, String strTarget, boolean ignoreCase) {
        if (array == null || strTarget == null) {
            return false;
        }
        if (ignoreCase) {
            for (String str : array) {
                if (strTarget.equalsIgnoreCase(str)) {
                    return true;
                }
            }
        } else {
            for (String str : array) {
                if (strTarget.equals(str)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断第一个传入的字符串数组中是否存在第二个传入的字符串.
     *
     * @param strSet 源字符串数组
     * @param strTarget 查找字符串
     * @param ignoreCase 忽略大小写
     * @return 第一个数组中是否存在第二个字符串
     * @deprecated use {@link #contain(String[], String, boolean)} instead
     */
    @Deprecated
    public static boolean containString(String[] strSet, String strTarget, boolean ignoreCase) {
        return contain(strSet, strTarget, ignoreCase);
    }

    /**
     * 数组链接 .
     *
     * @param arr1 arr1
     * @param arr2 arr2
     * @return 链接后的数组
     */
    public static Object concat(Object arr1, Object arr2) {
        if (arr1 == null && arr2 == null) {
            return null;
        }
        if (arr1 == null) {
            return arr2;
        } else if (arr2 == null) {
            return arr1;
        }

        Class<?> componentType = ClassUtils.parentClass(arr1.getClass().getComponentType(),
            arr2.getClass().getComponentType());
        int len1 = Array.getLength(arr1);
        int len2 = Array.getLength(arr2);
        Object newArray = Array.newInstance(componentType, len1 + len2);
        System.arraycopy(arr1, 0, newArray, 0, len1);
        System.arraycopy(arr2, 0, newArray, len1, len2);
        return newArray;
    }

    /**
     * 创建数组 .
     *
     * @param <T> 泛型
     * @param type 类型
     * @param length 长度
     * @return 数组
     */
    @SuppressWarnings("unchecked")
    public static <T> T newInstance(Class<?> type, int length) {
        return (T) Array.newInstance(type, length);
    }

    /**
     * get array item by index.
     * if give object is not a array, return null.
     *
     * @param <T> the generic type
     * @param array the array
     * @param index the index
     * @return array item
     */
    @SuppressWarnings("unchecked")
    public static <T> T get(Object array, int index) {
        if (array == null) {
            return null;
        }
        return (T) Array.get(array, index);
    }

    /**
     * get array length.
     * if give object is not a array, return 0.
     *
     * @param array the array
     * @return array length
     */
    public static int getLength(Object array) {
        if (!isArray(array)) {
            return 0;
        }
        return Array.getLength(array);
    }

    /**
     * 创建数组 .
     *
     * @param <T> 泛型
     * @param type 类型
     * @param length 长度
     * @return 数组
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] create(Class<T> type, int length) {
        return (T[]) newInstance(type, length);
    }

    /**
     * create array and fill the array with given filler.
     *
     * @param <T> the array element type
     * @param type the type
     * @param length the length
     * @param filler the filler
     * @return the array
     */
    public static <T> T[] create(Class<T> type, int length, IntFunction<T> filler) {
        T[] array = create(type, length);
        each(array, (e, i) -> {
            array[i] = filler.apply(i);
        });
        return array;
    }

    /**
     * create array and fill the array with given argu.
     *
     * @param <T> the array element type
     * @param type the type
     * @param length the length
     * @param element the element
     * @return the array
     */
    public static <T> T[] createFill(Class<T> type, int length, T element) {
        T[] array = create(type, length);
        Arrays.fill(array, element);
        return array;
    }

    /**
     * fillAll.
     *
     * @param target the target
     * @param source the source
     */
    public static void fillAll(char[] target, char[] source) {
        fillAll(target, 0, source);
    }

    /**
     * 使用source从startIndex填充target.
     *
     * @param target the target
     * @param startIndex the start index
     * @param source the source
     */
    public static void fillAll(char[] target, int startIndex, char[] source) {
        int len = target.length - startIndex < source.length ? target.length : source.length;
        for (int i = 0; i < len; i++) {
            target[i + startIndex] = source[i];
        }
    }

    /**
     * fillAll.
     *
     * @param target the target
     * @param source the source
     */
    public static void fillAll(byte[] target, byte[] source) {
        fillAll(target, 0, source);
    }

    /**
     * 使用source从startIndex填充target.
     *
     * @param target the target
     * @param startIndex the start index
     * @param source the source
     */
    public static void fillAll(byte[] target, int startIndex, byte[] source) {
        int len = target.length - startIndex < source.length ? target.length : source.length;
        for (int i = 0; i < len; i++) {
            target[i + startIndex] = source[i];
        }
    }

    /**
     * fillAll.
     *
     * @param target the target
     * @param source the source
     */
    public static void fillAll(int[] target, int[] source) {
        fillAll(target, 0, source);
    }

    /**
     * 使用source从startIndex填充target.
     *
     * @param target the target
     * @param startIndex the start index
     * @param source the source
     */
    public static void fillAll(int[] target, int startIndex, int[] source) {
        int len = target.length - startIndex < source.length ? target.length : source.length;
        for (int i = 0; i < len; i++) {
            target[i + startIndex] = source[i];
        }
    }

    /**
     * fillAll.
     *
     * @param target the target
     * @param source the source
     */
    public static void fillAll(long[] target, long[] source) {
        fillAll(target, 0, source);
    }

    /**
     * 使用source从startIndex填充target.
     *
     * @param target the target
     * @param startIndex the start index
     * @param source the source
     */
    public static void fillAll(long[] target, int startIndex, long[] source) {
        int len = target.length - startIndex < source.length ? target.length : source.length;
        for (int i = 0; i < len; i++) {
            target[i + startIndex] = source[i];
        }
    }

    /**
     * fillAll.
     *
     * @param target the target
     * @param source the source
     */
    public static void fillAll(double[] target, double[] source) {
        fillAll(target, 0, source);
    }

    /**
     * 使用source从startIndex填充target.
     *
     * @param target the target
     * @param startIndex the start index
     * @param source the source
     */
    public static void fillAll(double[] target, int startIndex, double[] source) {
        int len = target.length - startIndex < source.length ? target.length : source.length;
        for (int i = 0; i < len; i++) {
            target[i + startIndex] = source[i];
        }
    }

    /**
     * fillAll.
     *
     * @param <T> the generic type
     * @param target the target
     * @param source the source
     */
    public static <T> void fillAll(T[] target, T[] source) {
        fillAll(target, 0, source);
    }

    /**
     * 使用source从startIndex填充target.
     *
     * @param <T> the generic type
     * @param target the target
     * @param startIndex the start index
     * @param source the source
     */
    public static <T> void fillAll(T[] target, int startIndex, T[] source) {
        int len = target.length - startIndex < source.length ? target.length : source.length;
        for (int i = 0; i < len; i++) {
            target[i + startIndex] = source[i];
        }
    }

    /**
     * sets value for array at index.
     *
     * @param array the array
     * @param index the index
     * @param value the value
     */
    public static void set(Object array, int index, Object value) {
        Array.set(array, index, value);
    }

    /**
     * sets value for array at index.
     *
     * @param array the array
     * @param index the index
     * @param value the value
     */
    public static void set(Object array, int index, int value) {
        Array.setInt(array, index, value);
    }

    /**
     * sets value for array at index.
     *
     * @param array the array
     * @param index the index
     * @param value the value
     */
    public static void set(Object array, int index, long value) {
        Array.setLong(array, index, value);
    }

    /**
     * sets value for array at index.
     *
     * @param array the array
     * @param index the index
     * @param value the value
     */
    public static void set(Object array, int index, boolean value) {
        Array.setBoolean(array, index, value);
    }

    /**
     * sets value for array at index.
     *
     * @param array the array
     * @param index the index
     * @param value the value
     */
    public static void set(Object array, int index, byte value) {
        Array.setByte(array, index, value);
    }

    /**
     * sets value for array at index.
     *
     * @param array the array
     * @param index the index
     * @param value the value
     */
    public static void set(Object array, int index, short value) {
        Array.setShort(array, index, value);
    }

    /**
     * sets value for array at index.
     *
     * @param array the array
     * @param index the index
     * @param value the value
     */
    public static void set(Object array, int index, char value) {
        Array.setChar(array, index, value);
    }

    /**
     * sets value for array at index.
     *
     * @param array the array
     * @param index the index
     * @param value the value
     */
    public static void set(Object array, int index, double value) {
        Array.setDouble(array, index, value);
    }

    /**
     * sets value for array at index.
     *
     * @param array the array
     * @param index the index
     * @param value the value
     */
    public static void set(Object array, int index, float value) {
        Array.setFloat(array, index, value);
    }

    /**
     * Unpack.
     *
     * @param array the array
     * @return the int[]
     */
    public static int[] unpack(Integer... array) {
        int[] a = new int[array.length];
        each((e, i) -> a[i] = e, array);
        return a;
    }

    /**
     * Unpack.
     *
     * @param array the array
     * @return the long[]
     */
    public static long[] unpack(Long... array) {
        long[] a = new long[array.length];
        each((e, i) -> a[i] = e, array);
        return a;
    }

    /**
     * Unpack.
     *
     * @param array the array
     * @return the double[]
     */
    public static double[] unpack(Double... array) {
        double[] a = new double[array.length];
        each((e, i) -> a[i] = e, array);
        return a;
    }

    /**
     * Unpack.
     *
     * @param array the array
     * @return the float[]
     */
    public static float[] unpack(Float... array) {
        float[] a = new float[array.length];
        each((e, i) -> a[i] = e, array);
        return a;
    }

    /**
     * Unpack.
     *
     * @param array the array
     * @return the short[]
     */
    public static short[] unpack(Short... array) {
        short[] a = new short[array.length];
        each((e, i) -> a[i] = e, array);
        return a;
    }

    /**
     * Unpack.
     *
     * @param array the array
     * @return the byte[]
     */
    public static byte[] unpack(Byte... array) {
        byte[] a = new byte[array.length];
        each((e, i) -> a[i] = e, array);
        return a;
    }

    /**
     * Unpack.
     *
     * @param array the array
     * @return the char[]
     */
    public static char[] unpack(Character... array) {
        char[] a = new char[array.length];
        each((e, i) -> a[i] = e, array);
        return a;
    }

    /**
     * Unpack.
     *
     * @param array the array
     * @return the boolean[]
     */
    public static boolean[] unpack(Boolean... array) {
        boolean[] a = new boolean[array.length];
        each((e, i) -> a[i] = e, array);
        return a;
    }
}
