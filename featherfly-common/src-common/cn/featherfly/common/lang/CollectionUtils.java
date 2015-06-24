
package cn.featherfly.common.lang;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * <p>
 * 集合类工具
 * </p>
 * @author 钟冀
 */
public final class CollectionUtils {

    private CollectionUtils() {
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
        return LangUtils.isEmpty(collection);
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
     * 批量添加元素到集合，如果collection==null返回false,或者elements为空返回false，其他情况请参考{@link #java.util.Collections.addAll}
     * </p>
     * @param collection 集合
     * @param elements 需要批量添加的元素
     * @param <T> 泛型
     * @return 是否添加
     */
    public static <T> boolean addAll(Collection<T> collection, @SuppressWarnings("unchecked") T...elements) {
        if (collection == null) {
            return false;
        }
        if (LangUtils.isEmpty(elements)) {
            return false;
        }
        return Collections.addAll(collection, elements);
    }
    
    /**
     * <p>
     * 转换为数组.
     * 如果传入集合为空（null或者size=0），返回长度为0的数组（不会返回null）.
     * </p>
     * @param <A> 泛型
     * @param collection 集合
     * @param type 类型
     * @return 数组
     */
    public static <A> A[] toArray(Collection<A> collection, Class<A> type) {
        AssertIllegalArgument.isNotNull(type, "type不能为空");
        A[] results = null;
        if (collection == null) {
            collection = new ArrayList<A>();
        }
        results = ArrayUtils.create(type, collection.size());
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
     * @param type 类型
     * @param <C> 返回Collection实例泛型
     * @param <E> 返回Collection的泛型
     * @return Collection实例
     */
    @SuppressWarnings("unchecked")
    public static <C extends Collection<E>, E> C newInstance(Class<?> type) {
        AssertIllegalArgument.isTrue(ClassUtils.isParent(Collection.class, type)
                        , "传入类型必须是Collection接口的子接口或实现类");
        if (ClassUtils.isInstanceClass(type)) {
            return (C) ClassUtils.newInstance(type);
        } else {
            if (type == Collection.class) {
                return (C) new ArrayList<E>();
            } else if (ClassUtils.isParent(type, ArrayList.class)) {//type == List.class
                return (C) new ArrayList<E>();
//            } else if (type == Set.class) {
            } else if (ClassUtils.isParent(type, HashSet.class)) {
                return (C) new HashSet<E>();
//            } else if (type == Queue.class) {
            } else if (ClassUtils.isParent(type, ArrayDeque.class)) {
                return (C) new ArrayDeque<E>();
            }
            throw new IllegalArgumentException("不支持的类型：" + type.getName());
         }
    }
    
    @SuppressWarnings("unchecked")
    public static <K, V> Map<K, V> newMap(Class<?> type) {
        AssertIllegalArgument.isTrue(ClassUtils.isParent(Map.class, type)
                        , "传入类型必须是Map接口的子接口或实现类");
        if (ClassUtils.isInstanceClass(type)) {
            return (Map<K, V>) ClassUtils.newInstance(type);
        } else {
            if (type == Map.class) {
                return  new HashMap<K, V>();
            }
            throw new IllegalArgumentException("不支持的类型：" + type.getName());
         }
    }
}
