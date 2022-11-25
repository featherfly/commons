
/*
 * Thgk-Commons copyright featherfly 2010-2020, all rights reserved. created on
 * May 21, 2010 9:42:54 AM
 */
package cn.featherfly.common.lang;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

public class LangTest {
    @Test
    public void testObjectEmpty() {
        List<Object> list = new ArrayList<>();
        Object obj = null;
        Lang.ifEmpty(obj, () -> list.add("1"), () -> null);
        assertEquals(list.size(), 1);

        obj = "123";
        Lang.ifEmpty(obj, () -> list.add("1"), () -> null);
        assertEquals(list.size(), 1);

        obj = null;
        Lang.ifNotEmpty(obj, () -> list.add("1"), () -> null);
        assertEquals(list.size(), 1);

        obj = "123";
        Lang.ifNotEmpty(obj, () -> list.add("1"), () -> null);
        assertEquals(list.size(), 2);
    }

    @Test
    public void testStringEmpty() {
        List<Object> list = new ArrayList<>();
        String obj = "";
        Lang.ifEmpty(obj, () -> list.add("1"), () -> null);
        assertEquals(list.size(), 1);

        obj = "123";
        Lang.ifEmpty(obj, () -> list.add("1"), () -> null);
        assertEquals(list.size(), 1);

        obj = null;
        Lang.ifNotEmpty(obj, () -> list.add("1"), () -> null);
        assertEquals(list.size(), 1);

        obj = "123";
        Lang.ifNotEmpty(obj, () -> list.add("1"), () -> null);
        assertEquals(list.size(), 2);
    }

    @Test
    public void testArrayEmpty() {
        List<Object> list = new ArrayList<>();
        String[] obj = new String[] {};
        Lang.ifEmpty(obj, () -> list.add("1"), () -> null);
        assertEquals(list.size(), 1);

        obj = new String[1];
        Lang.ifEmpty(obj, () -> list.add("1"), () -> null);
        assertEquals(list.size(), 1);

        obj = null;
        Lang.ifNotEmpty(obj, () -> list.add("1"), () -> null);
        assertEquals(list.size(), 1);

        obj = new String[1];
        Lang.ifNotEmpty(obj, () -> list.add("1"), () -> null);
        assertEquals(list.size(), 2);
    }

    @Test
    public void testCollectionEmpty() {
        List<Object> list = new ArrayList<>();
        List<String> obj = new ArrayList<>();
        Lang.ifEmpty(obj, () -> list.add("1"), () -> null);
        assertEquals(list.size(), 1);

        obj.add("a");
        Lang.ifEmpty(obj, () -> list.add("1"), () -> null);
        assertEquals(list.size(), 1);

        obj = null;
        Lang.ifNotEmpty(obj, () -> list.add("1"), () -> null);
        assertEquals(list.size(), 1);

        obj = new ArrayList<>();
        obj.add("b");
        Lang.ifNotEmpty(obj, () -> list.add("1"), () -> null);
        assertEquals(list.size(), 2);
    }

    @Test
    public void testMapEmpty() {
        List<Object> list = new ArrayList<>();
        Map<String, String> obj = new HashMap<>();
        Lang.ifEmpty(obj, () -> list.add("1"), () -> null);
        assertEquals(list.size(), 1);

        obj.put("a", "a");
        Lang.ifEmpty(obj, () -> list.add("1"), () -> null);
        assertEquals(list.size(), 1);

        obj = null;
        Lang.ifNotEmpty(obj, () -> list.add("1"), () -> null);
        assertEquals(list.size(), 1);

        obj = new HashMap<>();
        obj.put("b", "b");
        Lang.ifNotEmpty(obj, () -> list.add("1"), () -> null);
        assertEquals(list.size(), 2);
    }

    @Test
    public void testFileExist() {
        List<Object> list = new ArrayList<>();
        File obj = null;
        Lang.ifExists(obj, f -> list.add("1"));
        assertEquals(list.size(), 0);

        obj = new File(
                ClassLoaderUtils.getResource(ClassUtils.packageToFile(this.getClass()), this.getClass()).getFile());
        System.out.println(obj.getAbsolutePath());
        Lang.ifExists(obj, t -> {
            list.add("1");
        });
        assertEquals(list.size(), 1);

        obj = new File("z:/aabbccddeeffgg");
        Lang.ifNotExists(obj, t -> {
            list.add("1");
        });
        assertEquals(list.size(), 2);

        obj = new File(
                ClassLoaderUtils.getResource(ClassUtils.packageToFile(this.getClass()), this.getClass()).getFile());
        Lang.ifNotExists(obj, t -> {
            list.add("1");
        });
        assertEquals(list.size(), 2);
    }

    @Test
    public void testPickFirst() {
        String s = "yufei";
        String result = Lang.pickFirst(null, s);
        assertEquals(s, result);

        result = Lang.pickFirst(s, null);
        assertEquals(s, result);

        result = Lang.pickFirst(null, null, s);
        assertEquals(s, result);

        result = Lang.pickFirst(null, s, null);
        assertEquals(s, result);

        result = Lang.pickFirst(null, s, "yi", null);
        assertEquals(s, result);

        result = Lang.pickFirst(null, "yi", s, null);
        assertFalse(s.equals(result));
    }

    @Test
    public void testPickLast() {
        String s = "yufei";
        String result = Lang.pickLast(null, s);
        assertEquals(s, result);

        result = Lang.pickLast(s, null);
        assertEquals(s, result);

        result = Lang.pickLast(s, null, null);
        assertEquals(s, result);

        result = Lang.pickLast(null, s, null);
        assertEquals(s, result);

        result = Lang.pickLast(null, s, "yi", null);
        assertFalse(s.equals(result));

        result = Lang.pickLast(null, "yi", s, null);
        assertEquals(s, result);
    }

    @Test
    public void testGetInvoker() {
        StackTraceElement e = Lang.getInvoker();
        System.out.println(Strings.format("e.getClassName = {0}     e.getMethodName() = {1}", e.getClassName(),
                e.getMethodName()));
        assertEquals(e.getMethodName(), "testGetInvoker");

        assertInvoker1("testGetInvoker");
        assertInvoker2("testGetInvoker");

        System.out.println();
        List<StackTraceElement> ss = Lang.getInvokers();
        assertEquals(ss.get(0).getMethodName(), "testGetInvoker");
        for (StackTraceElement s : ss) {
            System.out.println(s.getClassName() + " " + s.getMethodName());

            assertEquals(Lang.getInvoker().getMethodName(), "testGetInvoker");
        }

    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testGetInvokerException() {
        Lang.getInvoker(0);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testGetInvokerException2() {
        Lang.getInvoker(Integer.MAX_VALUE);
    }

    private void assertInvoker1(final String invokeMethod) {
        System.out.println("** assertInvoker1");
        StackTraceElement e = Lang.getInvoker(2);
        assertEquals(e.getClassName(), this.getClass().getName());
        System.out.println(Strings.format("assertEquals(e.getMethodName(), invokeMethod) = assertEquals({0}, {1})",
                e.getMethodName(), invokeMethod));
        //        assertEquals(Lang.getInvoker().getMethodName(), "getInvoker1");
        assertEquals(e.getMethodName(), invokeMethod);
    }

    private void assertInvoker2(String invokeMethod) {
        System.out.println("** assertInvoker2");
        StackTraceElement e = Lang.getInvoker(2);
        assertEquals(e.getClassName(), this.getClass().getName());
        //        assertEquals(Lang.getInvoker().getMethodName(), "getInvoker2");
        assertEquals(e.getMethodName(), invokeMethod);

        assertInvoker1("assertInvoker2");

        //        new Thread(() -> {
        //            // 这里是lambd，lambd名称是动态生成的
        //            assertInvoker1("getInvoker2");
        //        }).run();
    }
}
