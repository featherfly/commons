
package cn.featherfly.common.structure;

import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * chian invoke TreeMap.
 *
 * @author zhongj
 * @param <K> the key type
 * @param <V> the value type
 */
public class TreeChianMap<K, V> extends TreeMap<K, V> implements ChainMap<K, V> {

    private static final long serialVersionUID = 1913544534596071359L;

    /**
     * Instantiates a new tree chian map.
     */
    public TreeChianMap() {
        super();
    }

    /**
     * Instantiates a new tree chian map.
     *
     * @param comparator the comparator
     */
    public TreeChianMap(Comparator<? super K> comparator) {
        super(comparator);
    }

    /**
     * Instantiates a new tree chian map.
     *
     * @param m the m
     */
    public TreeChianMap(Map<? extends K, ? extends V> m) {
        super(m);
    }

    /**
     * Instantiates a new tree chian map.
     *
     * @param m the m
     */
    public TreeChianMap(SortedMap<K, ? extends V> m) {
        super(m);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ChainMap<K, V> putChain(K key, V value) {
        put(key, value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ChainMap<K, V> putAllChain(Map<? extends K, ? extends V> m) {
        putAll(m);
        return this;
    }

}
