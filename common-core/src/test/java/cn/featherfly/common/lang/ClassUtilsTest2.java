
/*
 * Thgk-Commons copyright featherfly 2010-2020, all rights reserved. created on
 * May 21, 2010 3:46:26 PM
 */
package cn.featherfly.common.lang;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import cn.featherfly.common.lang.vo.GenericTest;

/**
 * test getPrimitiveType performance
 *
 * @author zhongj
 */
public class ClassUtilsTest2 {

    static Class<GenericTest> genericType = GenericTest.class;

    final int loop = 1000000;

    @Test
    void getPrimitiveType() {
        for (int i = 0; i < loop; i++) {
            assertEquals(ClassUtils.getPrimitiveType("boolean"), Boolean.TYPE);
            assertEquals(ClassUtils.getPrimitiveType("byte"), Byte.TYPE);
            assertEquals(ClassUtils.getPrimitiveType("short"), Short.TYPE);
            assertEquals(ClassUtils.getPrimitiveType("int"), Integer.TYPE);
            assertEquals(ClassUtils.getPrimitiveType("long"), Long.TYPE);
            assertEquals(ClassUtils.getPrimitiveType("double"), Double.TYPE);
            assertEquals(ClassUtils.getPrimitiveType("float"), Float.TYPE);
            assertEquals(ClassUtils.getPrimitiveType("void"), Void.TYPE);
        }
    }

    @Test
    void getPrimitiveType2() {
        for (int i = 0; i < loop; i++) {
            assertEquals(ClassUtils.getPrimitiveType("boolean", true), Boolean.TYPE);
            assertEquals(ClassUtils.getPrimitiveType("byte", true), Byte.TYPE);
            assertEquals(ClassUtils.getPrimitiveType("short", true), Short.TYPE);
            assertEquals(ClassUtils.getPrimitiveType("int", true), Integer.TYPE);
            assertEquals(ClassUtils.getPrimitiveType("long", true), Long.TYPE);
            assertEquals(ClassUtils.getPrimitiveType("double", true), Double.TYPE);
            assertEquals(ClassUtils.getPrimitiveType("float", true), Float.TYPE);
            assertEquals(ClassUtils.getPrimitiveType("void", true), Void.TYPE);
        }
    }
}
