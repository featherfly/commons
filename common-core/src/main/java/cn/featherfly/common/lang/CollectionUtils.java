
package cn.featherfly.common.lang;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.function.Function;
import java.util.function.ObjIntConsumer;
import java.util.function.Supplier;

import cn.featherfly.common.structure.ChainMap;
import cn.featherfly.common.structure.ChainMapImpl;

/**
 * 集合类工具.
 *
 * @author zhongj
 */
public final class CollectionUtils {

    private CollectionUtils() {
    }

    /**
     * 返回传入集合是否为空（是null或size=0） .
     *
     * @param collection 传入的集合
     * @return 传入集合是否为空
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
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
     * 返回传入map是否为空（是null或size=0） .
     *
     * @param map the map
     * @return 传入map是否为空
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    /**
     * 返回传入map是否不为空（不是null或size&gt;0） .
     *
     * @param map the map
     * @return 传入map是否不为空
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    /**
     * 批量添加元素到集合，如果collection==null返回false,或者appendCollection为空返回false.
     * 其他情况请参考{@link java.util.Collection#addAll(Collection)}
     *
     * @param <T> 泛型
     * @param source 原集合
     * @param appendCollection 需要批量添加的集合
     * @return 是否添加
     */
    public static <T> boolean addAll(Collection<T> source, Collection<T> appendCollection) {
        if (source == null) {
            return false;
        }
        if (Lang.isEmpty(appendCollection)) {
            return false;
        }
        return source.addAll(appendCollection);
    }

    /**
     * 批量添加元素到集合，如果collection==null返回false,或者elements为空返回false.
     * 其他情况请参考{@link java.util.Collections#addAll(Collection, Object...)}
     *
     * @param <T> 泛型
     * @param collection 集合
     * @param elements 需要批量添加的元素
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
     * 批量添加元素到集合，如果collection==null返回false,或者elements为空返回false.
     * 其他情况请参考{@link java.util.Collection#addAll(Collection)}
     *
     * @param collection 集合
     * @param elements 需要批量添加的元素
     * @return 是否添加
     */
    public static boolean addAll(Collection<Boolean> collection, boolean... elements) {
        if (collection == null) {
            return false;
        }
        if (Lang.isEmpty(elements)) {
            return false;
        }
        boolean result = false;
        for (boolean element : elements) {
            result |= collection.add(element);
        }
        return result;
    }

    /**
     * 批量添加元素到集合，如果collection==null返回false,或者elements为空返回false.
     * 其他情况请参考{@link java.util.Collection#addAll(Collection)}
     *
     * @param collection 集合
     * @param elements 需要批量添加的元素
     * @return 是否添加
     */
    public static boolean addAll(Collection<Short> collection, short... elements) {
        if (collection == null) {
            return false;
        }
        if (Lang.isEmpty(elements)) {
            return false;
        }
        boolean result = false;
        for (short element : elements) {
            result |= collection.add(element);
        }
        return result;
    }

    /**
     * 批量添加元素到集合，如果collection==null返回false,或者elements为空返回false.
     * 其他情况请参考{@link java.util.Collection#addAll(Collection)}
     *
     * @param collection 集合
     * @param elements 需要批量添加的元素
     * @return 是否添加
     */
    public static boolean addAll(Collection<Byte> collection, byte... elements) {
        if (collection == null) {
            return false;
        }
        if (Lang.isEmpty(elements)) {
            return false;
        }
        boolean result = false;
        for (byte element : elements) {
            result |= collection.add(element);
        }
        return result;
    }

    /**
     * 批量添加元素到集合，如果collection==null返回false,或者elements为空返回false.
     * 其他情况请参考{@link java.util.Collection#addAll(Collection)}
     *
     * @param collection 集合
     * @param elements 需要批量添加的元素
     * @return 是否添加
     */
    public static boolean addAll(Collection<Integer> collection, int... elements) {
        if (collection == null) {
            return false;
        }
        if (Lang.isEmpty(elements)) {
            return false;
        }
        boolean result = false;
        for (int element : elements) {
            result |= collection.add(element);
        }
        return result;
    }

    /**
     * 批量添加元素到集合，如果collection==null返回false,或者elements为空返回false.
     * 其他情况请参考{@link java.util.Collection#addAll(Collection)}
     *
     * @param collection 集合
     * @param elements 需要批量添加的元素
     * @return 是否添加
     */
    public static boolean addAll(Collection<Long> collection, long... elements) {
        if (collection == null) {
            return false;
        }
        if (Lang.isEmpty(elements)) {
            return false;
        }
        boolean result = false;
        for (long element : elements) {
            result |= collection.add(element);
        }
        return result;
    }

    /**
     * 批量添加元素到集合，如果collection==null返回false,或者elements为空返回false.
     * 其他情况请参考{@link java.util.Collection#addAll(Collection)}
     *
     * @param collection 集合
     * @param elements 需要批量添加的元素
     * @return 是否添加
     */
    public static boolean addAll(Collection<Double> collection, double... elements) {
        if (collection == null) {
            return false;
        }
        if (Lang.isEmpty(elements)) {
            return false;
        }
        boolean result = false;
        for (double element : elements) {
            result |= collection.add(element);
        }
        return result;
    }

    /**
     * 批量添加元素到集合，如果collection==null返回false,或者elements为空返回false.
     * 其他情况请参考{@link java.util.Collection#addAll(Collection)}
     *
     * @param collection 集合
     * @param elements 需要批量添加的元素
     * @return 是否添加
     * @deprecated {@link #addAll(Collection, byte...)}
     */
    @Deprecated
    public static boolean addByteArray(Collection<Byte> collection, byte... elements) {
        return addAll(collection, elements);
    }

    /**
     * <p>
     * 批量添加元素到集合，如果collection==null返回false,或者elements为空返回false，其他情况请参考{@link java.util.Collections#addAll(Collection, Object...)}
     * </p>
     *
     * @param collection 集合
     * @param elements 需要批量添加的元素
     * @return 是否添加
     * @deprecated {@link #addAll(Collection, int...)}
     */
    @Deprecated
    public static boolean addIntArray(Collection<Integer> collection, int... elements) {
        return addAll(collection, elements);
    }

    /**
     * 转换为数组. 如果传入集合为空（null或者size=0）或者集合内的对象都是null,返回null.
     *
     * @param <A> 泛型
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

    /**
     * 转换为数组. 如果传入集合为空（null或者size=0），返回长度为0的数组（不会返回null）.
     *
     * @param <A> 泛型
     * @param collection 集合
     * @param type 类型
     * @return 数组
     */
    public static <A> A[] toArray(Collection<A> collection, Class<A> type) {
        AssertIllegalArgument.isNotNull(type, "type");
        if (collection == null) {
            collection = Collections.emptyList();
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

    private static <A> A[] doToArray(Collection<A> collection, Class<A> type) {
        A[] results = ArrayUtils.create(type, collection.size());
        return collection.toArray(results);
    }

    /**
     * create Collection with type argument.
     * 根据传入类型创建Collection实例.
     *
     * @param <C> type of Collection
     * @param <E> type of Collection value
     * @param type 类型
     * @return Collection实例
     * @deprecated use {@link #newCollection(Class)} instead
     */
    @Deprecated
    public static <C extends Collection<E>, E> C newInstance(Class<?> type) {
        return newCollection(type);
    }

    /**
     * create Collection with type argument.
     * 根据传入类型创建Collection实例.
     *
     * @param <C> type of Collection
     * @param <E> type of Collection value
     * @param type 类型
     * @return Collection实例
     */
    @SuppressWarnings("unchecked")
    public static <C extends Collection<E>, E> C newCollection(Class<?> type) {
        AssertIllegalArgument.isParent(Collection.class, type);
        if (type == Collection.class) {
            return (C) new ArrayList<E>();
        } else if (ClassUtils.isParent(List.class, type)) {
            return (C) newList(type);
        } else if (ClassUtils.isParent(Set.class, type)) {
            return (C) newSet(type);
        } else if (ClassUtils.isParent(Queue.class, type)) {
            return (C) newQueue(type);
        } else {
            throw new IllegalArgumentException("不支持的类型：" + type.getName());
        }
    }

    /**
     * create List with type argument.
     * 根据传入类型创建List实例.
     *
     * @param <E> type of List value
     * @param type List type
     * @return instance of list
     */
    @SuppressWarnings("unchecked")
    public static <E> List<E> newList(Class<?> type) {
        return newList(type, t -> (List<E>) ClassUtils.newInstance(t));
    }

    /**
     * create List with type argument.
     * 根据传入类型创建List实例.
     *
     * @param <E> type of List value
     * @param type List type
     * @param creator if method can not create a new List use this to create
     * @return instance of list
     */
    public static <E> List<E> newList(Class<?> type, Function<Class<?>, List<E>> creator) {
        AssertIllegalArgument.isParent(List.class, type);
        switch (type.getName()) {
            case "java.util.List":
            case "java.util.ArrayList":
                return new ArrayList<>();
            case "java.util.LinkedList":
                return new LinkedList<>();
            case "java.util.Vector":
                return new Vector<>();
            default:
                if (ClassUtils.isInstanceClass(type)) {
                    return creator.apply(type);
                }
                throw new IllegalArgumentException("unsupport type：" + type.getName());
        }
    }

    /**
     * get List creator with type argument.
     * 根据传入类型创建List创建者.
     *
     * @param <E> type of List value
     * @param type List type
     * @return List creator
     */
    @SuppressWarnings("unchecked")
    public static <E> Supplier<List<E>> listCreator(Class<?> type) {
        return listCreator(type, t -> (List<E>) ClassUtils.newInstance(t));
    }

    /**
     * get List creator with type argument.
     * 根据传入类型创建List创建者.
     *
     * @param <E> type of List value
     * @param type List type
     * @param creator if method can not get a List creator use this to create
     * @return List creator
     */
    public static <E> Supplier<List<E>> listCreator(Class<?> type, Function<Class<?>, List<E>> creator) {
        AssertIllegalArgument.isParent(List.class, type);
        switch (type.getName()) {
            case "java.util.List":
            case "java.util.ArrayList":
                return () -> new ArrayList<>();
            case "java.util.LinkedList":
                return () -> new LinkedList<>();
            case "java.util.Vector":
                return () -> new Vector<>();
            default:
                if (ClassUtils.isInstanceClass(type)) {
                    return () -> creator.apply(type);
                }
                throw new IllegalArgumentException("unsupport type：" + type.getName());
        }
    }

    /**
     * create Set with type argument.
     * 根据传入类型创建Set实例.
     *
     * @param <E> type of Set value
     * @param type Set type
     * @return Set instance object
     */
    @SuppressWarnings("unchecked")
    public static <E> Set<E> newSet(Class<?> type) {
        return newSet(type, t -> (Set<E>) ClassUtils.newInstance(t));
    }

    /**
     * create Set with type argument.
     * 根据传入类型创建Set实例.
     *
     * @param <E> type of Set value
     * @param type Set type
     * @param creator if method can not create a new Set use this to create
     * @return Set instance object
     */
    public static <E> Set<E> newSet(Class<?> type, Function<Class<?>, Set<E>> creator) {
        AssertIllegalArgument.isParent(Set.class, type);
        switch (type.getName()) {
            case "java.util.Set":
            case "java.util.HashSet":
                return new HashSet<>();

            case "java.util.LinkedHashSet":
                return new LinkedHashSet<>();

            case "java.util.SortedSet":
            case "java.util.NavigableSet":
            case "java.util.TreeSet":
                return new TreeSet<>();

            case "java.util.concurrent.ConcurrentSkipListSet":
                return new ConcurrentSkipListSet<>();
            default:
                if (ClassUtils.isInstanceClass(type)) {
                    return creator.apply(type);
                }
                throw new IllegalArgumentException("unsupport type：" + type.getName());
        }
    }

    /**
     * get Set creator with type argument.
     * 根据传入类型创建Set创建者.
     *
     * @param <E> type of Set value
     * @param type Set type
     * @return Set creator
     */
    @SuppressWarnings("unchecked")
    public static <E> Supplier<Set<E>> setCreator(Class<?> type) {
        return setCreator(type, t -> (Set<E>) ClassUtils.newInstance(t));
    }

    /**
     * get Set creator with type argument.
     * 根据传入类型创建Set创建者.
     *
     * @param <E> type of Set value
     * @param type Set type
     * @param creator if method can not get a Set creator use this to create
     * @return Set creator
     */
    public static <E> Supplier<Set<E>> setCreator(Class<?> type, Function<Class<?>, Set<E>> creator) {
        AssertIllegalArgument.isParent(Set.class, type);
        switch (type.getName()) {
            case "java.util.Set":
            case "java.util.HashSet":
                return () -> new HashSet<>();

            case "java.util.LinkedHashSet":
                return () -> new LinkedHashSet<>();

            case "java.util.SortedSet":
            case "java.util.NavigableSet":
            case "java.util.TreeSet":
                return () -> new TreeSet<>();

            case "java.util.concurrent.ConcurrentSkipListSet":
                return () -> new ConcurrentSkipListSet<>();
            default:
                if (ClassUtils.isInstanceClass(type)) {
                    return () -> creator.apply(type);
                }
                throw new IllegalArgumentException("unsupport type：" + type.getName());
        }
    }

    /**
     * create Queue with type argument.
     * 根据传入类型创建Queue实例.
     *
     * @param <E> type of Queue value
     * @param type Queue type
     * @return Queue instance object
     */
    @SuppressWarnings("unchecked")
    public static <E> Queue<E> newQueue(Class<?> type) {
        return newQueue(type, t -> (Queue<E>) ClassUtils.newInstance(t));
    }

    /**
     * create Queue with type argument.
     * 根据传入类型创建Queue实例.
     *
     * @param <E> type of Queue value
     * @param type Queue type
     * @param creator if method can not create a new Set use this to create
     * @return Queue instance object
     */
    public static <E> Queue<E> newQueue(Class<?> type, Function<Class<?>, Queue<E>> creator) {
        AssertIllegalArgument.isParent(Queue.class, type);
        switch (type.getName()) {
            case "java.util.Queue":
            case "java.util.Deque":
                return new ArrayDeque<>();

            case "java.util.concurrent.ConcurrentLinkedQueue":
                return new ConcurrentLinkedQueue<>();

            case "java.util.concurrent.ConcurrentLinkedDeque":
                return new ConcurrentLinkedDeque<>();

            default:
                if (ClassUtils.isInstanceClass(type)) {
                    return creator.apply(type);
                }
                throw new IllegalArgumentException("unsupport type：" + type.getName());
        }
    }

    /**
     * get Queue creator with type argument.
     * 根据传入类型创建Queue创建者.
     *
     * @param <E> type of Queue value
     * @param type Queue type
     * @return Queue creator
     */
    @SuppressWarnings("unchecked")
    public static <E> Supplier<Queue<E>> queueCreator(Class<?> type) {
        return queueCreator(type, t -> (Queue<E>) ClassUtils.newInstance(t));
    }

    /**
     * get Queue creator with type argument.
     * 根据传入类型创建List创建者.
     *
     * @param <E> type of Queue value
     * @param type Queue type
     * @param creator if method can not get a Queue creator use this to create
     * @return Queue creator
     */
    public static <E> Supplier<Queue<E>> queueCreator(Class<?> type, Function<Class<?>, Queue<E>> creator) {
        AssertIllegalArgument.isParent(Queue.class, type);
        switch (type.getName()) {
            case "java.util.Queue":
            case "java.util.Deque":
                return () -> new ArrayDeque<>();

            case "java.util.concurrent.ConcurrentLinkedQueue":
                return () -> new ConcurrentLinkedQueue<>();

            case "java.util.concurrent.ConcurrentLinkedDeque":
                return () -> new ConcurrentLinkedDeque<>();

            default:
                if (ClassUtils.isInstanceClass(type)) {
                    return () -> creator.apply(type);
                }
                throw new IllegalArgumentException("unsupport type：" + type.getName());
        }
    }

    /**
     * create Map with type argument.
     * 根据传入的类型生成Map实例.
     *
     * @param <K> type of Map Key
     * @param <V> type of Map Value
     * @param type map type
     * @return Map instance object
     */
    @SuppressWarnings("unchecked")
    public static <K, V> Map<K, V> newMap(Class<?> type) {
        return newMap(type, t -> (Map<K, V>) ClassUtils.newInstance(t));
    }

    /**
     * create Map with type argument.
     * 根据传入的类型生成Map实例.
     *
     * @param <K> type of Map Key
     * @param <V> type of Map Value
     * @param type map type
     * @param creator if method can not create a new Map use this to create
     * @return Map instance object
     */
    public static <K, V> Map<K, V> newMap(Class<?> type, Function<Class<?>, Map<K, V>> creator) {
        AssertIllegalArgument.isParent(Map.class, type);
        switch (type.getName()) {
            case "java.util.Map":
            case "java.util.HashMap":
                return new HashMap<>();

            case "java.util.LinkedHashMap":
                return new LinkedHashMap<>();

            case "java.util.NavigableMap":
            case "java.util.TreeMap":
                return new TreeMap<>();

            case "java.util.concurrent.ConcurrentMap":
            case "java.util.concurrent.ConcurrentHashMap":
                return new ConcurrentHashMap<>();

            case "java.util.concurrent.ConcurrentNavigableMap":
            case "java.util.concurrent.ConcurrentSkipListMap":
                return new ConcurrentSkipListMap<>();
            default:
                if (ClassUtils.isInstanceClass(type)) {
                    return creator.apply(type);
                }
                throw new IllegalArgumentException("unsupport type：" + type.getName());
            // throw new IllegalArgumentException("不支持的类型：" + type.getName());
        }
    }

    /**
     * get Map creator with type argument.
     * 根据传入类型创建Map创建者.
     *
     * @param <K> the key type
     * @param <V> the value type
     * @param type Map type
     * @return Map creator
     */
    @SuppressWarnings("unchecked")
    public static <K, V> Supplier<Map<K, V>> mapCreator(Class<?> type) {
        return mapCreator(type, t -> (Map<K, V>) ClassUtils.newInstance(t));
    }

    /**
     * get Map creator with type argument.
     * 根据传入类型创建List创建者.
     *
     * @param <K> the key type
     * @param <V> the value type
     * @param type Map type
     * @param creator if method can not get a Map creator use this to create
     * @return Map creator
     */
    public static <K, V> Supplier<Map<K, V>> mapCreator(Class<?> type, Function<Class<?>, Map<K, V>> creator) {
        AssertIllegalArgument.isParent(Map.class, type);
        switch (type.getName()) {
            case "java.util.Map":
            case "java.util.HashMap":
                return () -> new HashMap<>();

            case "java.util.LinkedHashMap":
                return () -> new LinkedHashMap<>();

            case "java.util.NavigableMap":
            case "java.util.TreeMap":
                return () -> new TreeMap<>();

            case "java.util.concurrent.ConcurrentMap":
            case "java.util.concurrent.ConcurrentHashMap":
                return () -> new ConcurrentHashMap<>();

            case "java.util.concurrent.ConcurrentNavigableMap":
            case "java.util.concurrent.ConcurrentSkipListMap":
                return () -> new ConcurrentSkipListMap<>();
            default:
                if (ClassUtils.isInstanceClass(type)) {
                    return () -> creator.apply(type);
                }
                throw new IllegalArgumentException("unsupport type：" + type.getName());
        }
    }

    /**
     * Each.
     *
     * @param <T> the generic type
     * @param iterable the iterable
     * @param consumer the consumer
     */
    public static <T> void each(Iterable<T> iterable, ObjIntConsumer<T> consumer) {
        if (iterable == null) {
            return;
        }
        int i = 0;
        for (T t : iterable) {
            consumer.accept(t, i);
            i++;
        }
    }

    /**
     * Each.
     *
     * @param <K> the key type
     * @param <V> the value type
     * @param map the map
     * @param consumer the consumer
     */
    public static <K, V> void each(Map<K, V> map, ObjIntConsumer<Entry<K, V>> consumer) {
        if (map == null) {
            return;
        }
        each(map.entrySet(), consumer);
    }

    /**
     * List.
     *
     * @param <T> the generic type
     * @param args the args
     * @return the list
     */
    public static <T> ArrayList<T> list(@SuppressWarnings("unchecked") T... args) {
        ArrayList<T> list = new ArrayList<>();
        addAll(list, args);
        return list;
    }

    /**
     * Sets the.
     *
     * @param <T> the generic type
     * @param args the args
     * @return the sets
     */
    public static <T> HashSet<T> set(@SuppressWarnings("unchecked") T... args) {
        HashSet<T> set = new HashSet<>();
        addAll(set, args);
        return set;
    }

    /**
     * create new map.
     *
     * @param <K> the key type
     * @param <V> the value type
     * @param key the key
     * @param value the value
     * @return the map
     */
    public static <K, V> ChainMap<K, V> map(K key, V value) {
        return new ChainMapImpl<K, V>().set(key, value);
    }
}
