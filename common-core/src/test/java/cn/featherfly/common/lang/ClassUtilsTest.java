
/*
 * Thgk-Commons copyright featherfly 2010-2020, all rights reserved. created on
 * May 21, 2010 3:46:26 PM
 */
package cn.featherfly.common.lang;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.lang.annotation.Documented;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import cn.featherfly.common.lang.vo.Bar;
import cn.featherfly.common.lang.vo.Coo;
import cn.featherfly.common.lang.vo.Foo;
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

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testNewInstance2() {
        ClassUtils.newInstance(null);
    }

    @Test
    public void testGetAnnotation() {
        Bar bar = Coo.class.getAnnotation(Bar.class);
        Foo foo = ClassUtils.getAnnotation(bar, Foo.class);
        assertEquals("test", foo.value());
        assertNull(ClassUtils.getAnnotation(bar, Documented.class));
    }

    @Test
    public void testGenericType() throws NoSuchFieldException, SecurityException {
        System.out.println(User.class.getDeclaredField("optional").getGenericType());
        System.out.println(User.class.getDeclaredField("obj").getGenericType());
        System.out.println(User.class.getDeclaredField("obj1").getGenericType());
        System.out.println(User.class.getDeclaredField("obj2").getGenericType());
        System.out.println(User.class.getDeclaredField("obj3").getGenericType());
        System.out.println();
        System.out.println(
                "optional.genericType-> " + ClassUtils.getFieldGenericType(User.class.getDeclaredField("optional")));
        System.out.println("obj.genericType-> " + ClassUtils.getFieldGenericType(User.class.getDeclaredField("obj")));
        System.out.println("obj1.genericType-> " + ClassUtils.getFieldGenericType(User.class.getDeclaredField("obj1")));
        System.out.println("obj2.genericType-> " + ClassUtils.getFieldGenericType(User.class.getDeclaredField("obj2")));
        System.out.println("obj3.genericType-> " + ClassUtils.getFieldGenericType(User.class.getDeclaredField("obj3")));

        assertEquals(ClassUtils.getFieldGenericType(User.class.getDeclaredField("optional")), String.class);
        assertEquals(ClassUtils.getFieldGenericType(User.class.getDeclaredField("obj")), Object.class);
        assertEquals(ClassUtils.getFieldGenericType(User.class.getDeclaredField("obj1")), Number.class);
        assertEquals(ClassUtils.getFieldGenericType(User.class.getDeclaredField("obj2")), User.class);
        assertEquals(ClassUtils.getFieldGenericType(User.class.getDeclaredField("obj3")), Object.class);
    }
}
