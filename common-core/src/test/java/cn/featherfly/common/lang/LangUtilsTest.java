
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
        List<Object> list = new ArrayList<>();
        Object obj = null;
        LangUtils.isEmpty(obj, t -> {
            list.add("1");
        });
        assertEquals(list.size(), 1);

        obj = "123";
        LangUtils.isEmpty(obj, t -> {
            list.add("1");
        });
        assertEquals(list.size(), 1);

        obj = null;
        LangUtils.isNotEmpty(obj, t -> {
            list.add("1");
        });
        assertEquals(list.size(), 1);

        obj = "123";
        LangUtils.isNotEmpty(obj, t -> {
            list.add("1");
        });
        assertEquals(list.size(), 2);
    }

    @Test
    public void testStringEmpty() {
        List<Object> list = new ArrayList<>();
        String obj = "";
        LangUtils.isEmpty(obj, t -> {
            list.add("1");
        });
        assertEquals(list.size(), 1);

        obj = "123";
        LangUtils.isEmpty(obj, t -> {
            list.add("1");
        });
        assertEquals(list.size(), 1);

        obj = null;
        LangUtils.isNotEmpty(obj, t -> {
            list.add("1");
        });
        assertEquals(list.size(), 1);

        obj = "123";
        LangUtils.isNotEmpty(obj, t -> {
            list.add("1");
        });
        assertEquals(list.size(), 2);
    }

    @Test
    public void testArrayEmpty() {
        List<Object> list = new ArrayList<>();
        String[] obj = new String[] {};
        LangUtils.isEmpty(obj, t -> {
            list.add("1");
        });
        assertEquals(list.size(), 1);

        obj = new String[1];
        LangUtils.isEmpty(obj, t -> {
            list.add("1");
        });
        assertEquals(list.size(), 1);

        obj = null;
        LangUtils.isNotEmpty(obj, t -> {
            list.add("1");
        });
        assertEquals(list.size(), 1);

        obj = new String[1];
        LangUtils.isNotEmpty(obj, t -> {
            list.add("1");
        });
        assertEquals(list.size(), 2);
    }

    @Test
    public void testCollectionEmpty() {
        List<Object> list = new ArrayList<>();
        List<String> obj = new ArrayList<>();
        LangUtils.isEmpty(obj, t -> {
            list.add("1");
        });
        assertEquals(list.size(), 1);

        obj.add("a");
        LangUtils.isEmpty(obj, t -> {
            list.add("1");
        });
        assertEquals(list.size(), 1);

        obj = null;
        LangUtils.isNotEmpty(obj, t -> {
            list.add("1");
        });
        assertEquals(list.size(), 1);

        obj = new ArrayList<>();
        obj.add("b");
        LangUtils.isNotEmpty(obj, t -> {
            list.add("1");
        });
        assertEquals(list.size(), 2);
    }

    @Test
    public void testMapEmpty() {
        List<Object> list = new ArrayList<>();
        Map<String, String> obj = new HashMap<>();
        LangUtils.isEmpty(obj, t -> {
            list.add("1");
        });
        assertEquals(list.size(), 1);

        obj.put("a", "a");
        LangUtils.isEmpty(obj, t -> {
            list.add("1");
        });
        assertEquals(list.size(), 1);

        obj = null;
        LangUtils.isNotEmpty(obj, t -> {
            list.add("1");
        });
        assertEquals(list.size(), 1);

        obj = new HashMap<>();
        obj.put("b", "b");
        LangUtils.isNotEmpty(obj, t -> {
            list.add("1");
        });
        assertEquals(list.size(), 2);
    }

    @Test
    public void testFileExist() {
        List<Object> list = new ArrayList<>();
        File obj = null;
        LangUtils.isExists(obj, t -> {
            list.add("1");
        });
        assertEquals(list.size(), 0);

        obj = new File(
                ClassLoaderUtils.getResource(ClassUtils.packageToFile(this.getClass()), this.getClass()).getFile());
        System.out.println(obj.getAbsolutePath());
        LangUtils.isExists(obj, t -> {
            list.add("1");
        });
        assertEquals(list.size(), 1);

        obj = new File("z:/aabbccddeeffgg");
        LangUtils.isNotExists(obj, t -> {
            list.add("1");
        });
        assertEquals(list.size(), 2);

        obj = new File(
                ClassLoaderUtils.getResource(ClassUtils.packageToFile(this.getClass()), this.getClass()).getFile());
        LangUtils.isNotExists(obj, t -> {
            list.add("1");
        });
        assertEquals(list.size(), 2);
    }

    @Test
    public void testPickFirst() {
        String s = "yufei";
        String result = LangUtils.pickFirst(null, s);
        assertEquals(s, result);

        result = LangUtils.pickFirst(s, null);
        assertEquals(s, result);

        result = LangUtils.pickFirst(null, null, s);
        assertEquals(s, result);

        result = LangUtils.pickFirst(null, s, null);
        assertEquals(s, result);

        result = LangUtils.pickFirst(null, s, "yi", null);
        assertEquals(s, result);

        result = LangUtils.pickFirst(null, "yi", s, null);
        assertFalse(s.equals(result));
    }

    @Test
    public void testPickLast() {
        String s = "yufei";
        String result = LangUtils.pickLast(null, s);
        assertEquals(s, result);

        result = LangUtils.pickLast(s, null);
        assertEquals(s, result);

        result = LangUtils.pickLast(s, null, null);
        assertEquals(s, result);

        result = LangUtils.pickLast(null, s, null);
        assertEquals(s, result);

        result = LangUtils.pickLast(null, s, "yi", null);
        assertFalse(s.equals(result));

        result = LangUtils.pickLast(null, "yi", s, null);
        assertEquals(s, result);
    }

    @Test
    public void testGetInvoker() {
        assertEquals(LangUtils.getInvoker().getClassName(), this.getClass().getName());
        assertEquals(LangUtils.getInvoker().getMethodName(), "testGetInvoker");

        getInvoker2();

        assertEquals(LangUtils.getInvoker().getClassName(), this.getClass().getName());
        assertEquals(LangUtils.getInvoker().getMethodName(), "testGetInvoker");
    }

    private void getInvoker1() {
        assertEquals(LangUtils.getInvoker().getClassName(), this.getClass().getName());
        assertEquals(LangUtils.getInvoker().getMethodName(), "getInvoker1");
    }

    private void getInvoker2() {
        assertEquals(LangUtils.getInvoker().getClassName(), this.getClass().getName());
        assertEquals(LangUtils.getInvoker().getMethodName(), "getInvoker2");
        new Thread(() -> {
            getInvoker1();
        }).run();
    }
}