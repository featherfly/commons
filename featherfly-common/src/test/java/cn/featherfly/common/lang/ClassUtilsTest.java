
/*
 * Thgk-Commons copyright featherfly 2010-2020, all rights reserved. created on
 * May 21, 2010 3:46:26 PM
 */
package cn.featherfly.common.lang;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import cn.featherfly.common.lang.vo.User;

public class ClassUtilsTest {

    @Test
    public void testIsParent() {
        assertTrue(ClassUtils.isParent(Map.class, HashMap.class));
        assertFalse(ClassUtils.isParent(HashMap.class, Map.class));
    }

    @Test
    public void testIsAbstractClass() {
        assertTrue(ClassUtils.isAbstractClass(Map.class));
        assertTrue(ClassUtils.isAbstractClass(AbstractList.class));
        assertFalse(ClassUtils.isAbstractClass(ArrayList.class));
    }

    @Test
    public void testIsInstanceClass() {
        assertFalse(ClassUtils.isInstanceClass(Map.class));
        assertFalse(ClassUtils.isInstanceClass(AbstractList.class));
        assertTrue(ClassUtils.isInstanceClass(ArrayList.class));
    }

    @Test
    public void testNewInstance() {
        List<String> list = new ArrayList<>();
        list.add("ArrayList1");
        User user = ClassUtils.newInstance(User.class, list);
        System.out.println(user);
        assertEquals(user.construct, "List");

        list = new LinkedList<>();
        list.add("LinkedList1");
        user = ClassUtils.newInstance(User.class, list);
        System.out.println(user);
        assertEquals(user.construct, "LinkedList");
    }

    @Test
    public void testNewInstance2() {
        ClassUtils.newInstance(null);
    }
}
