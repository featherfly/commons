
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
public class ImmutableSetTest {

    ImmutableSet<String> set;

    String key1 = "k1";
    String key2 = "k2";

    @BeforeClass
    void beforeClass() {
        set = new ImmutableSet<>(Lang.set(key1, key2));
    }

    @Test
    void testSet() {
        Set<String> s = Lang.set(key1, key2, "k3", "k4");

        assertEquals(s.size(), 4);

        Iterator<String> iter2 = s.iterator();
        iter2.next();
        iter2.remove();
        assertEquals(s.size(), 3);
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    void add() {
        set.add("");
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    void addAll() {
        set.addAll(new ArrayList<>());
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    void remove() {
        set.remove("");
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    void removeIf() {
        set.removeIf(null);
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    void clear() {
        set.clear();
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    void iteratorRemove() {
        Iterator<String> iter = set.iterator();
        iter.next();
        iter.remove();
    }
}
