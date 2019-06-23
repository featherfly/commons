
package cn.featherfly.common.structure;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 带链式调用的HASHMAP
 * </p>
 * @param <K> 键的类型
 * @param <V> 值的类型
 * @author 钟冀
 */
public class HashChainMap<K, V> extends HashMap<K, V> {

    private static final long serialVersionUID = 7403898439744127401L;

    /**
     * @see java.util.HashMap
     */
    public HashChainMap() {
        super();
    }

    /**
     * @see java.util.HashMap
     * @param  initialCapacity 初始化的大小.
     */
    public HashChainMap(int initialCapacity) {
        super(initialCapacity);
    }

    /**
     * <p>
     * 带链式调用的put方法.
     * {@link java.util.HashMap#put(Object, Object)}
     * </p>
     * @param key 指定值将要关联的键。
     * @param value 指定键将要关联的值。
     * @return 当前MAP
     */
    public HashChainMap<K, V> putChain(K key, V value) {
        this.put(key, value);
        return this;
    }
    /**
     * <p>
     * 带链式调用的putAll方法.
     * {@link java.util.HashMap#putAll(Map)}
     * </p>
     * @param m 要在此映射中存储的映射关系
     * @return 当前MAP
     */
    public HashChainMap<K, V> putAllChain(Map<? extends K, ? extends V> m) {
        this.putAll(m);
        return this;
    }
}
