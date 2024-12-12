
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-12-12 16:49:12
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.structure;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import cn.featherfly.common.lang.Lang;

/**
 * ImmutableMapTest.
 *
 * @author zhongj
 */
public class ImmutableCollectionTest {

    ImmutableCollection<String> collection;

    String key1 = "k1";
    String key2 = "k2";

    @BeforeClass
    void beforeClass() {
        if (System.currentTimeMillis() % 2 == 0) {
            collection = new ImmutableCollection<>(Lang.set(key1, key2));
        } else {
            collection = new ImmutableCollection<>(Lang.list(key1, key2));
        }
    }

    @Test
    void testCollection() {
        Set<String> s = Lang.set(key1, key2, "k3", "k4");

        assertEquals(s.size(), 4);

        Iterator<String> iter2 = s.iterator();
        iter2.next();
        iter2.remove();
        assertEquals(s.size(), 3);
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    void add() {
        collection.add("");
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    void addAll() {
        collection.addAll(new ArrayList<>());
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    void remove() {
        collection.remove("");
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    void removeIf() {
        collection.removeIf(null);
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    void clear() {
        collection.clear();
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    void iteratorRemove() {
        Iterator<String> iter = collection.iterator();
        iter.next();
        iter.remove();
    }
}
