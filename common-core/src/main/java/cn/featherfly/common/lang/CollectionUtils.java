
package cn.featherfly.common.lang;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * <p>
 * 集合类工具
 * </p>
 * .
 *
 * @author zhongj
 */
public final class CollectionUtils {

    private CollectionUtils() {
    }

    /**
     * <p>
     * 返回传入集合是否为空（是null或size=0）
     * </p>
     * .
     *
     * @param collection 传入的集合
     * @return 传入集合是否为空
     */
    public static boolean isEmpty(Collection<?> collection) {
        return Lang.isEmpty(collection);
    }

    /**
     * <p>
     * 返回传入集合是否不为空（不是null或size&gt;0）
     * </p>
     * .
     *
     * @param collection 传入的集合
     * @return 传入集合是否不为空
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    /**
     * <p>
     * 批量添加元素到集合，如果collection==null返回false,或者elements为空返回false，其他情况请参考{@link java.util.Collections#addAll(Collection, Object...)}
     * </p>
     *
     * @param <T>        泛型
     * @param collection 集合
     * @param elements   需要批量添加的元素
     * @return 是否添加
     */
    public static <T> boolean addAll(Collection<T> collection, @SuppressWarnings("unchecked") T... elements) {
        if (collection == null) {
            return false;
        }
        if (Lang.isEmpty(elements)) {
            return false;
        }
        return Collections.addAll(collection, elements);
    }

    /**
     * <p>
     * 批量添加元素到集合，如果collection==null返回false,或者elements为空返回false，其他情况请参考{@link java.util.Collections#addAll(Collection, Object...)}
     * </p>
     *
     * @param collection 集合
     * @param elements   需要批量添加的元素
     * @return 是否添加
     */
    public static boolean addByteArray(Collection<Byte> collection, byte... elements) {
        if (collection == null) {
            return false;
        }
        if (Lang.isEmpty(elements)) {
            return false;
        }
        boolean result = false;
        for (byte element : elements) {
            result = collection.add(element);
        }
        return result;
    }

    /**
     * <p>
     * 批量添加元素到集合，如果collection==null返回false,或者elements为空返回false，其他情况请参考{@link java.util.Collections#addAll(Collection, Object...)}
     * </p>
     *
     * @param collection 集合
     * @param elements   需要批量添加的元素
     * @return 是否添加
     */
    public static boolean addIntArray(Collection<Integer> collection, int... elements) {
        if (collection == null) {
            return false;
        }
        if (Lang.isEmpty(elements)) {
            return false;
        }
        boolean result = false;
        for (int element : elements) {
            result = collection.add(element);
        }
        return result;
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
        AssertIllegalArgument.isNotNull(type, "Class<A> type");
        if (collection == null) {
            collection = new ArrayList<>();
        }
        return doToArray(collection, type);
    }

    /**
     * 转换为数组. 如果传入集合为空（null或者size=0），返回长度为0的数组（不会返回null）.
     *
     * @param collection 集合
     * @return 数组
     */
    public static byte[] toByteArray(Collection<Byte> collection) {
        if (collection == null) {
            collection = new ArrayList<>();
        }
        byte[] bs = new byte[collection.size()];
        int index = 0;
        for (Byte b : collection) {
            bs[index] = b;
            index++;
        }
        return bs;
    }

    /**
     * 转换为数组. 如果传入集合为空（null或者size=0），返回长度为0的数组（不会返回null）.
     *
     * @param collection 集合
     * @return 数组
     */
    public static int[] toIntArray(Collection<Integer> collection) {
        if (collection == null) {
            collection = new ArrayList<>();
        }
        int[] bs = new int[collection.size()];
        int index = 0;
        for (Integer b : collection) {
            bs[index] = b;
            index++;
        }
        return bs;
    }

    /**
     * 转换为数组. 如果传入集合为空（null或者size=0）或者集合内的对象都是null,返回null.
     *
     * @param <A>        泛型
     * @param collection 集合
     * @return 数组
     */
    @SuppressWarnings("unchecked")
    public static <A> A[] toArray(Collection<A> collection) {
        if (isEmpty(collection)) {
            return null;
        }
        Class<A> type = null;
        for (A a : collection) {
            if (a != null) {
                type = (Class<A>) a.getClass();
            }
        }
        if (type == null) {
            return null;
        }
        return doToArray(collection, type);
    }

    private static <A> A[] doToArray(Collection<A> collection, Class<A> type) {
        A[] results = ArrayUtils.create(type, collection.size());
        int i = 0;
        for (A a : collection) {
            results[i] = a;
            i++;
        }
        return results;
    }

    /**
     * <p>
     * 根据传入类型创建Collection实例
     * </p>
     * .
     *
     * @param <C>  返回Collection实例泛型
     * @param <E>  返回Collection的泛型
     * @param type 类型
     * @return Collection实例
     */
    @SuppressWarnings("unchecked")
    public static <C extends Collection<E>, E> C newInstance(Class<?> type) {
        //        AssertIllegalArgument.isTrue(ClassUtils.isParent(Collection.class, type), "传入类型必须是Collection接口的子接口或实现类");
        AssertIllegalArgument.isParent(Collection.class, type);
        if (ClassUtils.isInstanceClass(type)) {
            return (C) ClassUtils.newInstance(type);
        } else {
            C collection = null;
            if (type == Collection.class) {
                collection = (C) new ArrayList<E>();
            } else if (ClassUtils.isParent(type, ArrayList.class)) {
                collection = (C) new ArrayList<E>();
            } else if (ClassUtils.isParent(type, HashSet.class)) {
                collection = (C) new HashSet<E>();
            } else if (ClassUtils.isParent(type, ArrayDeque.class)) {
                collection = (C) new ArrayDeque<E>();
            }
            if (collection != null) {
                return collection;
            }
            throw new IllegalArgumentException("不支持的类型：" + type.getName());
        }
    }

    /**
     * <p>
     * 根据传入的类型生成Map实例
     * </p>
     * .
     *
     * @param <K>  Map Key泛型
     * @param <V>  Map Value泛型
     * @param type 类型
     * @return Map实例
     */
    @SuppressWarnings("unchecked")
    public static <K, V> Map<K, V> newMap(Class<?> type) {
        //        AssertIllegalArgument.isTrue(ClassUtils.isParent(Map.class, type), "传入类型必须是Map接口的子接口或实现类");
        AssertIllegalArgument.isParent(Map.class, type);
        if (ClassUtils.isInstanceClass(type)) {
            return (Map<K, V>) ClassUtils.newInstance(type);
        } else {
            if (type == Map.class) {
                return new HashMap<>();
            }
            throw new IllegalArgumentException("不支持的类型：" + type.getName());
        }
    }

    /**
     * Each.
     *
     * @param <T>      the generic type
     * @param iterable the iterable
     * @param consumer the consumer
     */
    public static <T> void each(Iterable<T> iterable, BiConsumer<T, Integer> consumer) {
        if (iterable != null) {
            int i = 0;
            for (T t : iterable) {
                consumer.accept(t, i);
                i++;
            }
        }
    }
}
