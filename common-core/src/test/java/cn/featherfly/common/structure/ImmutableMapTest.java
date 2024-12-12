
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-12-12 16:49:12
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.structure;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * ImmutableMapTest.
 *
 * @author zhongj
 */
public class ImmutableMapTest {

    ImmutableMap<String, Object> map;

    String key1 = "k1";
    String value1 = "v1";
    String key2 = "k2";
    String value2 = "v2";

    @BeforeClass
    void beforeClass() {
        map = new ImmutableMap<>(new ChainMapImpl<String, Object>().putChain(key1, value2).putChain(key2, value2));
    }

    @Test
    void testMap() {
        Map<String, Object> m = new ChainMapImpl<String, Object>().putChain(key1, value2).putChain(key2, value2)
            .putChain("k3", "3").putChain("k4", "4");

        assertEquals(m.size(), 4);
        Iterator<Entry<String, Object>> iter = m.entrySet().iterator();
        iter.next();
        iter.remove();
        assertEquals(m.size(), 3);

        Iterator<String> iter2 = m.keySet().iterator();
        iter2.next();
        iter2.remove();
        assertEquals(m.size(), 2);

        Iterator<Object> iter3 = m.values().iterator();
        iter3.next();
        iter3.remove();
        assertEquals(m.size(), 1);
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    void put() {
        map.put("", "");
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    void putAll() {
        map.putAll(new HashMap<>());
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    void putIfAbsent() {
        map.putIfAbsent("", "");
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    void remove() {
        map.remove("");
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    void remove2() {
        map.remove("", "");
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    void compute() {
        map.compute("", null);
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    void computeIfAbsent() {
        map.computeIfAbsent("", null);
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    void computeIfPresent() {
        map.computeIfPresent("", null);
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    void clear() {
        map.clear();
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    void merge() {
        map.merge("", "", null);
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    void replace() {
        map.replace("", "");
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    void replace2() {
        map.replace("", "", "");
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    void replaceAll() {
        map.replaceAll(null);
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    void entrySetIterRemove() {
        Iterator<Entry<String, Object>> iter = map.entrySet().iterator();
        iter.next();
        iter.remove();
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    void keySetIterRemove() {
        Iterator<String> iter = map.keySet().iterator();
        iter.next();
        iter.remove();
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    void valuesIterRemove() {
        Iterator<Object> iter = map.values().iterator();
        iter.next();
        iter.remove();
    }

}
