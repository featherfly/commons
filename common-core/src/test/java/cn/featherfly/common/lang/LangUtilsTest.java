
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

public class LangUtilsTest {
    @Test
    public void testObjectEmpty() {
        final List<Object> list = new ArrayList<>();
        Object obj = null;
        Lang.ifEmpty(obj, () -> list.add("1"), () -> null);
        assertEquals(list.size(), 1);

        obj = "123";
        Lang.ifEmpty(obj, () -> list.add("1"), () -> null);
        assertEquals(list.size(), 1);

        obj = null;
        Lang.ifNotEmptyOrElse(obj, (o) -> list.add("1"), () -> null);
        assertEquals(list.size(), 1);

        obj = "123";
        Lang.ifNotEmptyOrElse(obj, (o) -> list.add("1"), () -> null);
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
        Lang.ifNotEmptyOrElse(obj, (o) -> list.add("1"), () -> null);
        assertEquals(list.size(), 1);

        obj = "123";
        Lang.ifNotEmptyOrElse(obj, (o) -> list.add("1"), () -> null);
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
        Lang.ifNotEmptyOrElse(obj, (o) -> list.add("1"), () -> null);
        assertEquals(list.size(), 1);

        obj = new String[1];
        Lang.ifNotEmptyOrElse(obj, (o) -> list.add("1"), () -> null);
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
        Lang.ifNotEmptyOrElse(obj, (o) -> list.add("1"), () -> null);
        assertEquals(list.size(), 1);

        obj = new ArrayList<>();
        obj.add("b");
        Lang.ifNotEmptyOrElse(obj, (o) -> list.add("1"), () -> null);
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
        Lang.ifNotEmptyOrElse(obj, (o) -> list.add("1"), () -> null);
        assertEquals(list.size(), 1);

        obj = new HashMap<>();
        obj.put("b", "b");
        Lang.ifNotEmptyOrElse(obj, (o) -> list.add("1"), () -> null);
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
        String result = Lang.ifNotNullFirst(null, s);
        assertEquals(s, result);

        result = Lang.ifNotNullFirst(s, null);
        assertEquals(s, result);

        result = Lang.ifNotNullFirst(null, null, s);
        assertEquals(s, result);

        result = Lang.ifNotNullFirst(null, s, null);
        assertEquals(s, result);

        result = Lang.ifNotNullFirst(null, s, "yi", null);
        assertEquals(s, result);

        result = Lang.ifNotNullFirst(null, "yi", s, null);
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
        assertInvoker1("testGetInvoker");
        assertInvoker2("testGetInvoker");
    }

    private void assertInvoker1(final String invokeMethod) {
        assertEquals(Lang.getInvoker().getClassName(), this.getClass().getName());
        //        assertEquals(Lang.getInvoker().getMethodName(), "getInvoker1");
        assertEquals(Lang.getInvoker().getMethodName(), invokeMethod);
    }

    private void assertInvoker2(String invokeMethod) {
        assertEquals(Lang.getInvoker().getClassName(), this.getClass().getName());
        //        assertEquals(Lang.getInvoker().getMethodName(), "getInvoker2");
        assertEquals(Lang.getInvoker().getMethodName(), invokeMethod);

        assertInvoker1("assertInvoker2");

        //        new Thread(() -> {
        //            // 这里是lambd，lambd名称是动态生成的
        //            assertInvoker1("getInvoker2");
        //        }).run();
    }
}
