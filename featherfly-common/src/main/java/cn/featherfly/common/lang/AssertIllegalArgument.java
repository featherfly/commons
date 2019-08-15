package cn.featherfly.common.lang;

import java.io.File;
import java.util.Collection;
import java.util.Map;

import cn.featherfly.common.lang.asserts.IllegalArgumentAssert;

/**
 * <p>
 * 断言工具类，对于满足断言的情况，抛出IllegalArgumentException异常. 一般用于检查传入参数是否合法
 * </p>
 *
 * @author zhongj
 * @since 1.0
 * @version 1.0
 */
public final class AssertIllegalArgument {

    private static final IllegalArgumentAssert ASSERT = new IllegalArgumentAssert();

    private AssertIllegalArgument() {
    }

    /**
     * @param object    object
     * @param arguDescp argument descption
     * @see cn.featherfly.common.lang.asserts.LocalizedAssert#isNotNull(java.lang.Object,
     *      java.lang.String)
     */
    public static void isNotNull(Object object, String arguDescp) {
        ASSERT.isNotNull(object, arguDescp);
    }

    /**
     * @param text      text
     * @param arguDescp argument descption
     * @see cn.featherfly.common.lang.asserts.LocalizedAssert#isNotBlank(java.lang.String,
     *      java.lang.String)
     */
    public static void isNotBlank(String text, String arguDescp) {
        ASSERT.isNotBlank(text, arguDescp);
    }

    /**
     * @param obj obj
     * @param arg arg
     * @see cn.featherfly.common.lang.asserts.LocalizedAssert#isNotEmpty(java.lang.Object,
     *      java.lang.String)
     */
    public static void isNotEmpty(Object obj, String arguDescp) {
        ASSERT.isNotEmpty(obj, arguDescp);
    }

    /**
     * @param text      text
     * @param arguDescp argument descption
     * @see cn.featherfly.common.lang.asserts.LocalizedAssert#isNotEmpty(java.lang.String,
     *      java.lang.String)
     */
    public static void isNotEmpty(String text, String arguDescp) {
        ASSERT.isNotEmpty(text, arguDescp);
    }

    /**
     * @param array array
     * @param arg   arg
     * @see cn.featherfly.common.lang.asserts.LocalizedAssert#isNotEmpty(java.lang.Object[],
     *      java.lang.String)
     */
    public static void isNotEmpty(Object[] array, String arguDescp) {
        ASSERT.isNotEmpty(array, arguDescp);
    }

    /**
     * @param collection collection
     * @param arg        arg
     * @see cn.featherfly.common.lang.asserts.LocalizedAssert#isNotEmpty(java.util.Collection,
     *      java.lang.String)
     */
    public static void isNotEmpty(Collection<?> collection, String arguDescp) {
        ASSERT.isNotEmpty(collection, arguDescp);
    }

    /**
     * @param map map
     * @param arg arg
     * @see cn.featherfly.common.lang.asserts.LocalizedAssert#isNotEmpty(java.util.Map,
     *      java.lang.String)
     */
    public static void isNotEmpty(Map<?, ?> map, String arguDescp) {
        ASSERT.isNotEmpty(map, arguDescp);
    }

    /**
     * @param file      file
     * @param arguDescp argument descption
     * @see cn.featherfly.common.lang.asserts.LocalizedAssert#isExists(java.io.File,
     *      java.lang.String)
     */
    public static void isExists(File file, String arguDescp) {
        ASSERT.isExists(file, arguDescp);
    }

    /**
     * @param clazz clazz
     * @param obj   obj
     * @see cn.featherfly.common.lang.asserts.LocalizedAssert#isInstanceOf(java.lang.Class,
     *      java.lang.Object)
     */
    public static void isInstanceOf(Class<?> clazz, Object obj) {
        ASSERT.isInstanceOf(clazz, obj);
    }

    /**
     * @param parentType parentType
     * @param subType    subType
     * @see cn.featherfly.common.lang.asserts.LocalizedAssert#isParent(java.lang.Class,
     *      java.lang.Class)
     */
    public static void isParent(Class<?> parentType, Class<?> subType) {
        ASSERT.isParent(parentType, subType);
    }

}
