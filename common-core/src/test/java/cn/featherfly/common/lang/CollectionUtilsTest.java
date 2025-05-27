
/*
 * Thgk-Commons
 * copyright featherfly 2010-2020, all rights reserved.
 * created on May 21, 2010 3:46:26 PM
 */
package cn.featherfly.common.lang;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Queue;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;

import org.testng.annotations.Test;

public class CollectionUtilsTest {

    @Test
    public void newCollection() {
        Collection<String> c = null;

        c = CollectionUtils.newCollection(Collection.class);
        assertTrue(c instanceof ArrayList);

        c = CollectionUtils.newCollection(List.class);
        assertTrue(c instanceof ArrayList);

        c = CollectionUtils.newCollection(ArrayList.class);
        assertTrue(c instanceof ArrayList);

        c = CollectionUtils.newCollection(LinkedList.class);
        assertTrue(c instanceof LinkedList);

        c = CollectionUtils.newCollection(Vector.class);
        assertTrue(c instanceof Vector);

        c = CollectionUtils.newCollection(MyStringList.class);
        assertTrue(c instanceof MyStringList);

        // ----------------------------------------------------------------------------------------------------------------

        c = CollectionUtils.newSet(Set.class);
        assertTrue(c instanceof HashSet);

        c = CollectionUtils.newSet(HashSet.class);
        assertTrue(c instanceof HashSet);

        c = CollectionUtils.newSet(LinkedHashSet.class);
        assertTrue(c instanceof LinkedHashSet);

        c = CollectionUtils.newSet(SortedSet.class);
        assertTrue(c instanceof TreeSet);

        c = CollectionUtils.newSet(NavigableSet.class);
        assertTrue(c instanceof TreeSet);

        c = CollectionUtils.newSet(TreeSet.class);
        assertTrue(c instanceof TreeSet);

        c = CollectionUtils.newSet(ConcurrentSkipListSet.class);
        assertTrue(c instanceof ConcurrentSkipListSet);

        c = CollectionUtils.newSet(MyStringSet.class);
        assertTrue(c instanceof MyStringSet);

        // ----------------------------------------------------------------------------------------------------------------

        c = CollectionUtils.newQueue(Queue.class);
        assertTrue(c instanceof ArrayDeque);

        c = CollectionUtils.newQueue(Deque.class);
        assertTrue(c instanceof ArrayDeque);

        c = CollectionUtils.newQueue(ConcurrentLinkedQueue.class);
        assertTrue(c instanceof ConcurrentLinkedQueue);

        c = CollectionUtils.newQueue(ConcurrentLinkedDeque.class);
        assertTrue(c instanceof ConcurrentLinkedDeque);

        c = CollectionUtils.newQueue(MyStringQueue.class);
        assertTrue(c instanceof MyStringQueue);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void newCollectionException() {
        CollectionUtils.newCollection(Map.class);
    }

    @Test
    public void listCreator() {
        List<?> list = null;

        list = CollectionUtils.listCreator(List.class).get();
        assertTrue(list instanceof ArrayList);

        list = CollectionUtils.listCreator(ArrayList.class).get();
        assertTrue(list instanceof ArrayList);

        list = CollectionUtils.listCreator(LinkedList.class).get();
        assertTrue(list instanceof LinkedList);

        list = CollectionUtils.listCreator(Vector.class).get();
        assertTrue(list instanceof Vector);

        list = CollectionUtils.listCreator(MyStringList.class).get();
        assertTrue(list instanceof MyStringList);
    }

    @Test
    public void newList() {
        List<String> list = null;

        list = CollectionUtils.newList(List.class);
        assertTrue(list instanceof ArrayList);

        list = CollectionUtils.newList(ArrayList.class);
        assertTrue(list instanceof ArrayList);

        list = CollectionUtils.newList(LinkedList.class);
        assertTrue(list instanceof LinkedList);

        list = CollectionUtils.newList(Vector.class);
        assertTrue(list instanceof Vector);

        list = CollectionUtils.newList(MyStringList.class);
        assertTrue(list instanceof MyStringList);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void newListException() {
        CollectionUtils.newList(Map.class);
    }

    @Test
    public void setCreator() {
        Set<?> set = null;

        set = CollectionUtils.setCreator(Set.class).get();
        assertTrue(set instanceof HashSet);

        set = CollectionUtils.setCreator(HashSet.class).get();
        assertTrue(set instanceof HashSet);

        set = CollectionUtils.setCreator(LinkedHashSet.class).get();
        assertTrue(set instanceof LinkedHashSet);

        set = CollectionUtils.setCreator(SortedSet.class).get();
        assertTrue(set instanceof TreeSet);

        set = CollectionUtils.setCreator(NavigableSet.class).get();
        assertTrue(set instanceof TreeSet);

        set = CollectionUtils.setCreator(TreeSet.class).get();
        assertTrue(set instanceof TreeSet);

        set = CollectionUtils.setCreator(ConcurrentSkipListSet.class).get();
        assertTrue(set instanceof ConcurrentSkipListSet);

        set = CollectionUtils.setCreator(MyStringSet.class).get();
        assertTrue(set instanceof MyStringSet);
    }

    @Test
    public void newSet() {
        Set<String> set = null;

        set = CollectionUtils.newSet(Set.class);
        assertTrue(set instanceof HashSet);

        set = CollectionUtils.newSet(HashSet.class);
        assertTrue(set instanceof HashSet);

        set = CollectionUtils.newSet(LinkedHashSet.class);
        assertTrue(set instanceof LinkedHashSet);

        set = CollectionUtils.newSet(SortedSet.class);
        assertTrue(set instanceof TreeSet);

        set = CollectionUtils.newSet(NavigableSet.class);
        assertTrue(set instanceof TreeSet);

        set = CollectionUtils.newSet(TreeSet.class);
        assertTrue(set instanceof TreeSet);

        set = CollectionUtils.newSet(ConcurrentSkipListSet.class);
        assertTrue(set instanceof ConcurrentSkipListSet);

        set = CollectionUtils.newSet(MyStringSet.class);
        assertTrue(set instanceof MyStringSet);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void newSetException() {
        CollectionUtils.newSet(Map.class);
    }

    @Test
    public void queueCreator() {
        Queue<?> queue = null;

        queue = CollectionUtils.queueCreator(Queue.class).get();
        assertTrue(queue instanceof ArrayDeque);

        queue = CollectionUtils.queueCreator(Deque.class).get();
        assertTrue(queue instanceof ArrayDeque);

        queue = CollectionUtils.queueCreator(ConcurrentLinkedQueue.class).get();
        assertTrue(queue instanceof ConcurrentLinkedQueue);

        queue = CollectionUtils.queueCreator(ConcurrentLinkedDeque.class).get();
        assertTrue(queue instanceof ConcurrentLinkedDeque);

        queue = CollectionUtils.queueCreator(MyStringQueue.class).get();
        assertTrue(queue instanceof MyStringQueue);
    }

    @Test
    public void newQueue() {
        Queue<String> queue = null;

        queue = CollectionUtils.newQueue(Queue.class);
        assertTrue(queue instanceof ArrayDeque);

        queue = CollectionUtils.newQueue(Deque.class);
        assertTrue(queue instanceof ArrayDeque);

        queue = CollectionUtils.newQueue(ConcurrentLinkedQueue.class);
        assertTrue(queue instanceof ConcurrentLinkedQueue);

        queue = CollectionUtils.newQueue(ConcurrentLinkedDeque.class);
        assertTrue(queue instanceof ConcurrentLinkedDeque);

        queue = CollectionUtils.newQueue(MyStringQueue.class);
        assertTrue(queue instanceof MyStringQueue);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void newQueueException() {
        CollectionUtils.newQueue(Map.class);
    }

    @Test
    public void map() {
        final String titleKey = "title";
        final String contentKey = "content";
        String title = "title-1";
        String content = "content-1";
        Map<String, String> map = CollectionUtils.map(titleKey, title)
            .set(contentKey, content);
        assertEquals(map.get(titleKey), title);
        assertEquals(map.get(contentKey), content);
    }

    @Test
    public void newMap() {
        Map<String, String> map = null;

        map = CollectionUtils.newMap(Map.class);
        assertTrue(map instanceof HashMap);

        map = CollectionUtils.newMap(HashMap.class);
        assertTrue(map instanceof HashMap);

        map = CollectionUtils.newMap(LinkedHashMap.class);
        assertTrue(map instanceof LinkedHashMap);

        map = CollectionUtils.newMap(NavigableMap.class);
        assertTrue(map instanceof TreeMap);
        map = CollectionUtils.newMap(TreeMap.class);
        assertTrue(map instanceof TreeMap);

        map = CollectionUtils.newMap(ConcurrentMap.class);
        assertTrue(map instanceof ConcurrentHashMap);
        map = CollectionUtils.newMap(ConcurrentHashMap.class);
        assertTrue(map instanceof ConcurrentHashMap);

        map = CollectionUtils.newMap(ConcurrentNavigableMap.class);
        assertTrue(map instanceof ConcurrentSkipListMap);
        map = CollectionUtils.newMap(ConcurrentSkipListMap.class);
        assertTrue(map instanceof ConcurrentSkipListMap);

        map = CollectionUtils.newMap(MyStringMap.class);
        assertTrue(map instanceof MyStringMap);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void newMapException() {
        CollectionUtils.newMap(Queue.class);
    }

}

class MyStringList extends ArrayList<String> {

    private static final long serialVersionUID = -9048117429973764477L;

}

class MyStringQueue extends ArrayDeque<String> {

    private static final long serialVersionUID = -8483845773998387959L;

}

class MyStringSet extends HashSet<String> {

    private static final long serialVersionUID = 8958327821860939157L;

}

class MyStringMap extends HashMap<String, String> {

    private static final long serialVersionUID = -5624220227120658072L;

}
