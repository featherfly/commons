
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-12-12 16:49:12
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.structure;

import static org.testng.Assert.assertEquals;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import cn.featherfly.common.lang.Lang;

/**
 * ImmutableMapTest.
 *
 * @author zhongj
 */
public class ImmutableListTest {

    ImmutableList<String> list;

    String key1 = "k1";
    String key2 = "k2";

    @BeforeClass
    void beforeClass() {
        list = new ImmutableList<>(Lang.list(key1, key2));
    }

    @Test
    void testList() {
        List<String> m = Lang.list(key1, key2, "k3", "k4");

        assertEquals(m.size(), 4);

        Iterator<String> iter = m.iterator();
        iter.next();
        iter.remove();
        assertEquals(m.size(), 3);
        System.out.println(m);

        ListIterator<String> iter2 = m.listIterator();
        iter2.next();
        iter2.remove();
        assertEquals(m.size(), 2);
        iter2.add("k5");
        assertEquals(m.size(), 3);

        String v = iter2.next();
        iter2.set(v + ".set");
        assertEquals(m.size(), 3);

        System.out.println(m);
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    void add() {
        list.add("");
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    void add2() {
        list.add(0, "");
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    void addAll() {
        list.addAll(null);
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    void addAll2() {
        list.addAll(0, null);
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    void remove() {
        list.remove("");
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    void remove2() {
        list.remove(0);
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    void removeIf() {
        list.removeIf(null);
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    void removeAll() {
        list.removeAll(null);
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    void set() {
        list.set(0, "");
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    void clear() {
        list.clear();
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    void replaceAll() {
        list.replaceAll(null);
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    void iteratorRemove() {
        Iterator<String> iter = list.iterator();
        iter.next();
        iter.remove();
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    void listIteratorRemove() {
        ListIterator<String> iter = list.listIterator();
        iter.next();
        iter.remove();
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    void listIteratorAdd() {
        ListIterator<String> iter = list.listIterator();
        iter.next();
        iter.add("");
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    void listIteratorSet() {
        ListIterator<String> iter = list.listIterator();
        iter.next();
        iter.set("");
    }
}
