
/*
 * Thgk-Commons copyright featherfly 2010-2020, all rights reserved. created on
 * May 21, 2010 9:42:54 AM
 */
package cn.featherfly.common.lang;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

public class LangTest {

    @Test
    public void isEmpty_Array() {
        String[] obj = new String[] {};
        assertTrue(Lang.isEmpty(obj));

        Object[] obj2 = new Object[] {};
        assertTrue(Lang.isEmpty(obj2));
    }

    @Test
    public void ifEmpty_Object() {
        Object obj = null;
        Object result = null;
        result = Lang.ifEmptyOrElse(obj, () -> Integer.valueOf(1), (s) -> Integer.valueOf(2));
        assertEquals(result, 1);

        obj = "123";
        result = Lang.ifEmptyOrElse(obj, () -> Integer.valueOf(1), (s) -> Integer.valueOf(2));
        assertEquals(result, 2);

        obj = null;
        result = Lang.ifNotEmptyOrElse(obj, (s) -> Integer.valueOf(1), () -> Integer.valueOf(2));
        assertEquals(result, 2);

        obj = "123";
        result = Lang.ifNotEmptyOrElse(obj, (s) -> Integer.valueOf(1), () -> Integer.valueOf(2));
        assertEquals(result, 1);
    }

    @Test
    public void ifEmpty_String() {
        String obj = "";
        Object result = null;
        result = Lang.ifEmptyOrElse(obj, () -> Integer.valueOf(1), (s) -> Integer.valueOf(2));
        assertEquals(result, 1);

        obj = "123";
        result = Lang.ifEmptyOrElse(obj, () -> Integer.valueOf(1), (s) -> Integer.valueOf(2));
        assertEquals(result, 2);

        obj = null;
        result = Lang.ifNotEmptyOrElse(obj, (s) -> Integer.valueOf(1), () -> Integer.valueOf(2));
        assertEquals(result, 2);

        obj = "123";
        result = Lang.ifNotEmptyOrElse(obj, (s) -> Integer.valueOf(1), () -> Integer.valueOf(2));
        assertEquals(result, 1);
    }

    @Test
    public void ifEmpty_Array() {
        String[] obj = new String[] {};
        Object result = null;
        result = Lang.ifEmptyOrElse(obj, () -> Integer.valueOf(1), (s) -> Integer.valueOf(2));
        assertEquals(result, 1);

        obj = new String[1];
        result = Lang.ifEmptyOrElse(obj, () -> Integer.valueOf(1), (s) -> Integer.valueOf(2));
        assertEquals(result, 2);

        obj = null;
        result = Lang.ifNotEmptyOrElse(obj, (s) -> Integer.valueOf(1), () -> Integer.valueOf(2));
        assertEquals(result, 2);

        obj = new String[1];
        result = Lang.ifNotEmptyOrElse(obj, (s) -> Integer.valueOf(1), () -> Integer.valueOf(2));
        assertEquals(result, 1);
    }

    @Test
    public void ifEmpty_Collection() {
        List<String> obj = new ArrayList<>();
        Object result = null;
        result = Lang.ifEmptyOrElse(obj, () -> Integer.valueOf(1), (s) -> Integer.valueOf(2));
        assertEquals(result, 1);

        obj.add("a");
        result = Lang.ifEmptyOrElse(obj, () -> Integer.valueOf(1), (s) -> Integer.valueOf(2));
        assertEquals(result, 2);

        obj = null;
        result = Lang.ifNotEmptyOrElse(obj, (s) -> Integer.valueOf(1), () -> Integer.valueOf(2));
        assertEquals(result, 2);

        obj = new ArrayList<>();
        obj.add("b");
        result = Lang.ifNotEmptyOrElse(obj, (s) -> Integer.valueOf(1), () -> Integer.valueOf(2));
        assertEquals(result, 1);
    }

    @Test
    public void ifEmpty_Map() {
        Map<String, String> obj = new HashMap<>();
        Object result = null;
        result = Lang.ifEmptyOrElse(obj, () -> Integer.valueOf(1), (s) -> Integer.valueOf(2));
        assertEquals(result, 1);

        obj.put("a", "a");
        result = Lang.ifEmptyOrElse(obj, () -> Integer.valueOf(1), (s) -> Integer.valueOf(2));
        assertEquals(result, 2);

        obj = null;
        result = Lang.ifNotEmptyOrElse(obj, (s) -> Integer.valueOf(1), () -> Integer.valueOf(2));
        assertEquals(result, 2);

        obj = new HashMap<>();
        obj.put("b", "b");
        result = Lang.ifNotEmptyOrElse(obj, (s) -> Integer.valueOf(1), () -> Integer.valueOf(2));
        assertEquals(result, 1);
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
    public void ifNull() {
        String s = "yufei";
        String snull = null;
        String result = Lang.ifNull(null, s);
        assertEquals(result, s);

        result = Lang.ifNull(s, snull);
        assertEquals(result, s);

    }

    @Test
    public void ifNull2() {
        String s = "yufei";
        String snull = null;
        String result = Lang.ifNull(snull, () -> s);
        assertEquals(result, s);

        result = Lang.ifNull(s, () -> null);
        assertEquals(result, s);

    }

    @Test
    public void ifNull3() {
        String s = "yufei";
        String yi = "yi";
        String snull = null;
        String result = null;

        result = Lang.ifNull(s, () -> snull, () -> snull);
        assertEquals(result, s);

        result = Lang.ifNull(snull, () -> snull, () -> s);
        assertEquals(result, s);

        result = Lang.ifNull(snull, () -> s, () -> snull);
        assertEquals(result, s);

        result = Lang.ifNull(snull, () -> s, () -> yi, () -> snull);
        assertEquals(result, s);

        result = Lang.ifNull(snull, () -> yi, () -> s, () -> snull);
        assertEquals(result, yi);

        // ----------------------------------------------------------------------------------------------------------------

        result = Lang.ifNull(() -> s, () -> snull, () -> snull);
        assertEquals(result, s);

        result = Lang.ifNull(() -> snull, () -> snull, () -> s);
        assertEquals(result, s);

        result = Lang.ifNull(() -> snull, () -> s, () -> snull);
        assertEquals(result, s);

        result = Lang.ifNull(() -> snull, () -> s, () -> yi, () -> snull);
        assertEquals(result, s);

        result = Lang.ifNull(() -> snull, () -> yi, () -> s, () -> snull);
        assertEquals(result, yi);
    }

    @Test
    public void ifNotNullFirst() {
        String s = "yufei";
        String yi = "yi";
        String snull = null;
        String result = null;

        result = Lang.ifNotNullFirst(s, snull);
        assertEquals(result, s);

        result = Lang.ifNotNullFirst(snull, snull, s);
        assertEquals(result, s);

        result = Lang.ifNotNullFirst(snull, s, snull);
        assertEquals(result, s);

        result = Lang.ifNotNullFirst(snull, s, yi, snull);
        assertEquals(result, s);

        result = Lang.ifNotNullFirst(snull, yi, s, snull);
        assertEquals(result, yi);
    }

    @Test
    public void ifEmpty() {
        String s = "yufei";
        String snull = "";
        String result = Lang.ifEmpty(snull, s);
        assertEquals(result, s);

        result = Lang.ifEmpty(s, snull);
        assertEquals(result, s);

    }

    @Test
    public void ifEmpty2() {
        String s = "yufei";
        String snull = "";
        String result = Lang.ifEmpty(snull, () -> s);
        assertEquals(result, s);

        result = Lang.ifNull(s, () -> null);
        assertEquals(result, s);

    }

    @Test
    public void ifEmpty3() {
        String s = "yufei";
        String yi = "yi";
        String snull = "";
        String result = null;

        result = Lang.ifEmpty(s, () -> snull, () -> snull);
        assertEquals(result, s);

        result = Lang.ifEmpty(snull, () -> snull, () -> s);
        assertEquals(result, s);

        result = Lang.ifEmpty(snull, () -> s, () -> snull);
        assertEquals(result, s);

        result = Lang.ifEmpty(snull, () -> s, () -> yi, () -> snull);
        assertEquals(result, s);

        result = Lang.ifEmpty(snull, () -> yi, () -> s, () -> snull);
        assertEquals(result, yi);

        // ----------------------------------------------------------------------------------------------------------------

        result = Lang.ifEmpty(() -> s, () -> snull, () -> snull);
        assertEquals(result, s);

        result = Lang.ifEmpty(() -> snull, () -> snull, () -> s);
        assertEquals(result, s);

        result = Lang.ifEmpty(() -> snull, () -> s, () -> snull);
        assertEquals(result, s);

        result = Lang.ifEmpty(() -> snull, () -> s, () -> yi, () -> snull);
        assertEquals(result, s);

        result = Lang.ifEmpty(() -> snull, () -> yi, () -> s, () -> snull);
        assertEquals(result, yi);
    }

    @Test
    public void ifNotEmptyFirst() {
        String s = "yufei";
        String yi = "yi";
        String snull = "";
        String result = null;

        result = Lang.ifNotEmptyFirst(s, snull);
        assertEquals(result, s);

        result = Lang.ifNotEmptyFirst(snull, snull, s);
        assertEquals(result, s);

        result = Lang.ifNotEmptyFirst(snull, s, snull);
        assertEquals(result, s);

        result = Lang.ifNotEmptyFirst(snull, s, yi, snull);
        assertEquals(result, s);

        result = Lang.ifNotEmptyFirst(snull, yi, s, snull);
        assertEquals(result, yi);
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
        System.out.println(
            Str.format("e.getClassName = {0}     e.getMethodName() = {1}", e.getClassName(), e.getMethodName()));
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
        System.out.println(Str.format("assertEquals(e.getMethodName(), invokeMethod) = assertEquals({0}, {1})",
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
