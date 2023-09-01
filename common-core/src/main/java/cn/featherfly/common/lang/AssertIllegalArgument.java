package cn.featherfly.common.lang;

import java.io.File;
import java.util.Collection;
import java.util.Map;

import cn.featherfly.common.function.serializable.SerializableNumberSupplier;
import cn.featherfly.common.function.serializable.SerializableSupplier;
import cn.featherfly.common.lang.asserts.IllegalArgumentAssert;

/**
 * <p>
 * 断言工具类，对于满足断言的情况，抛出IllegalArgumentException异常. 一般用于检查传入参数是否合法
 * </p>
 *
 * @author zhongj
 * @version 1.0
 * @since 1.0
 */
public final class AssertIllegalArgument {

    /** The Constant ASSERT. */
    private static final IllegalArgumentAssert ASSERT = new IllegalArgumentAssert();

    /**
     * Instantiates a new assert illegal argument.
     */
    private AssertIllegalArgument() {
    }

    /**
     * Checks if is not null.
     *
     * @param object    object
     * @param arguDescp argument descption
     * @see cn.featherfly.common.lang.asserts.LocalizedAssert#isNotNull(java.lang.Object,
     *      java.lang.String)
     */
    public static void isNotNull(Object object, String arguDescp) {
        ASSERT.isNotNull(object, arguDescp);
    }

    /**
     * Checks if is not blank.
     *
     * @param text      text
     * @param arguDescp argument descption
     * @see cn.featherfly.common.lang.asserts.LocalizedAssert#isNotBlank(java.lang.String,
     *      java.lang.String)
     */
    public static void isNotBlank(String text, String arguDescp) {
        ASSERT.isNotBlank(text, arguDescp);
    }

    /**
     * Checks if is not empty.
     *
     * @param obj       obj
     * @param arguDescp arguDescp
     * @see cn.featherfly.common.lang.asserts.LocalizedAssert#isNotEmpty(java.lang.Object,
     *      java.lang.String)
     */
    public static void isNotEmpty(Object obj, String arguDescp) {
        ASSERT.isNotEmpty(obj, arguDescp);
    }

    /**
     * Checks if is not empty.
     *
     * @param text      text
     * @param arguDescp argument descption
     * @see cn.featherfly.common.lang.asserts.LocalizedAssert#isNotEmpty(java.lang.String,
     *      java.lang.String)
     */
    public static void isNotEmpty(String text, String arguDescp) {
        ASSERT.isNotEmpty(text, arguDescp);
    }

    /**
     * Checks if is not empty.
     *
     * @param array     array
     * @param arguDescp arguDescp
     * @see cn.featherfly.common.lang.asserts.LocalizedAssert#isNotEmpty(java.lang.Object[],
     *      java.lang.String)
     */
    public static void isNotEmpty(Object[] array, String arguDescp) {
        ASSERT.isNotEmpty(array, arguDescp);
    }

    /**
     * Checks if is not empty.
     *
     * @param collection collection
     * @param arguDescp  arguDescp
     * @see cn.featherfly.common.lang.asserts.LocalizedAssert#isNotEmpty(java.util.Collection,
     *      java.lang.String)
     */
    public static void isNotEmpty(Collection<?> collection, String arguDescp) {
        ASSERT.isNotEmpty(collection, arguDescp);
    }

    /**
     * Checks if is not empty.
     *
     * @param map       map
     * @param arguDescp arguDescp
     * @see cn.featherfly.common.lang.asserts.LocalizedAssert#isNotEmpty(java.util.Map,
     *      java.lang.String)
     */
    public static void isNotEmpty(Map<?, ?> map, String arguDescp) {
        ASSERT.isNotEmpty(map, arguDescp);
    }

    /**
     * Checks if is exists.
     *
     * @param file      file
     * @param arguDescp argument descption
     * @see cn.featherfly.common.lang.asserts.LocalizedAssert#isExists(java.io.File,
     *      java.lang.String)
     */
    public static void isExists(File file, String arguDescp) {
        ASSERT.isExists(file, arguDescp);
    }

    /**
     * Checks if is instance of.
     *
     * @param clazz clazz
     * @param obj   obj
     * @see cn.featherfly.common.lang.asserts.LocalizedAssert#isInstanceOf(java.lang.Class,
     *      java.lang.Object)
     */
    public static void isInstanceOf(Class<?> clazz, Object obj) {
        ASSERT.isInstanceOf(clazz, obj);
    }

    /**
     * Checks if is parent.
     *
     * @param parentType parentType
     * @param subType    subType
     * @see cn.featherfly.common.lang.asserts.LocalizedAssert#isParent(java.lang.Class,
     *      java.lang.Class)
     */
    public static void isParent(Class<?> parentType, Class<?> subType) {
        ASSERT.isParent(parentType, subType);
    }

    /**
     * Checks if is file.
     *
     * @param file      the file
     * @param arguDescp the argu descp
     * @see cn.featherfly.common.lang.asserts.LocalizedAssert#isFile(java.io.File,
     *      java.lang.String)
     */
    public static void isFile(File file, String arguDescp) {
        ASSERT.isFile(file, arguDescp);
    }

    /**
     * Checks if is directory.
     *
     * @param file      the file
     * @param arguDescp the argu descp
     * @see cn.featherfly.common.lang.asserts.LocalizedAssert#isDirectory(java.io.File,
     *      java.lang.String)
     */
    public static void isDirectory(File file, String arguDescp) {
        ASSERT.isDirectory(file, arguDescp);
    }

    /**
     * Checks if is not interface.
     *
     * @param classType the class type
     * @see cn.featherfly.common.lang.asserts.LocalizedAssert#isNotInterface(java.lang.Class)
     */
    public static void isNotInterface(Class<?> classType) {
        ASSERT.isNotInterface(classType);
    }

    /**
     * Checks if is in range.
     *
     * @param <N>       the number type
     * @param value     the value
     * @param min       the min
     * @param max       the max
     * @param arguDescp the argu descp
     * @see cn.featherfly.common.lang.asserts.LocalizedAssert#isInRange(java.lang.Number,
     *      java.lang.Number, java.lang.Number, java.lang.String)
     */
    public static <N extends Number> void isInRange(N value, N min, N max, String arguDescp) {
        ASSERT.isInRange(value, min, max, arguDescp);
    }

    /**
     * Checks if is in range.
     *
     * @param <N>   the number type
     * @param value the value
     * @param min   the min
     * @param max   the max
     * @see cn.featherfly.common.lang.asserts.LocalizedAssert#isInRange(SerializableNumberSupplier,
     *      java.lang.Number, java.lang.Number)
     */
    public static <N extends Number> void isInRange(SerializableNumberSupplier<N> value, N min, N max) {
        ASSERT.isInRange(value, min, max);
    }

    /**
     * Checks if is gt.
     *
     * @param <N>       the number type
     * @param value     the value
     * @param min       the min
     * @param arguDescp the argu descp
     * @see cn.featherfly.common.lang.asserts.LocalizedAssert#isGt(java.lang.Number,
     *      java.lang.Number, java.lang.String)
     */
    public static <N extends Number> void isGt(N value, N min, String arguDescp) {
        ASSERT.isGt(value, min, arguDescp);
    }

    /**
     * Checks if is gt.
     *
     * @param <N>   the number type
     * @param value the value
     * @param min   the min
     * @see cn.featherfly.common.lang.asserts.LocalizedAssert#isGt(SerializableNumberSupplier,
     *      java.lang.Number)
     */
    public static <N extends Number> void isGt(SerializableNumberSupplier<N> value, N min) {
        ASSERT.isGt(value, min);
    }

    /**
     * Checks if is ge.
     *
     * @param <N>       the number type
     * @param value     the value
     * @param min       the min
     * @param arguDescp the argu descp
     * @see cn.featherfly.common.lang.asserts.LocalizedAssert#isGe(java.lang.Number,
     *      java.lang.Number, java.lang.String)
     */
    public static <N extends Number> void isGe(N value, N min, String arguDescp) {
        ASSERT.isGe(value, min, arguDescp);
    }

    /**
     * Checks if is ge.
     *
     * @param <N>   the number type
     * @param value the value
     * @param min   the min
     * @see cn.featherfly.common.lang.asserts.LocalizedAssert#isGe(SerializableNumberSupplier,
     *      java.lang.Number)
     */
    public static <N extends Number> void isGe(SerializableNumberSupplier<N> value, N min) {
        ASSERT.isGe(value, min);
    }

    /**
     * Checks if is lt.
     *
     * @param <N>       the number type
     * @param value     the value
     * @param max       the max
     * @param arguDescp the argu descp
     * @see cn.featherfly.common.lang.asserts.LocalizedAssert#isLt(java.lang.Number,
     *      java.lang.Number, java.lang.String)
     */
    public static <N extends Number> void isLt(N value, N max, String arguDescp) {
        ASSERT.isLt(value, max, arguDescp);
    }

    /**
     * Checks if is lt.
     *
     * @param <N>   the number type
     * @param value the value
     * @param max   the max
     * @see cn.featherfly.common.lang.asserts.LocalizedAssert#isLt(SerializableNumberSupplier,
     *      java.lang.Number)
     */
    public static <N extends Number> void isLt(SerializableNumberSupplier<N> value, N max) {
        ASSERT.isLt(value, max);
    }

    /**
     * Checks if is le.
     *
     * @param <N>       the number type
     * @param value     the value
     * @param max       the max
     * @param arguDescp the argu descp
     * @see cn.featherfly.common.lang.asserts.LocalizedAssert#isLe(java.lang.Number,
     *      java.lang.Number, java.lang.String)
     */
    public static <N extends Number> void isLe(N value, N max, String arguDescp) {
        ASSERT.isLe(value, max, arguDescp);
    }

    /**
     * Checks if is le.
     *
     * @param <N>   the number type
     * @param value the value
     * @param max   the max
     * @see cn.featherfly.common.lang.asserts.LocalizedAssert#isLe(SerializableNumberSupplier,
     *      java.lang.Number)
     */
    public <N extends Number> void isLe(SerializableNumberSupplier<N> value, N max) {
        ASSERT.isLe(value, max);
    }

    /**
     * Checks if is not null.
     *
     * @param <T>              the generic type
     * @param propertySupplier the property supplier
     * @see cn.featherfly.common.lang.asserts.LocalizedAssert#isNotNull(SerializableSupplier)
     */
    public static <T> void isNotNull(SerializableSupplier<T> propertySupplier) {
        ASSERT.isNotNull(propertySupplier);
    }

    /**
     * Checks if is not blank.
     *
     * @param propertySupplier the property supplier
     * @see cn.featherfly.common.lang.asserts.LocalizedAssert#isNotBlank(SerializableSupplier)
     */
    public static void isNotBlank(SerializableSupplier<String> propertySupplier) {
        ASSERT.isNotBlank(propertySupplier);
    }

    /**
     * Checks if is not empty.
     *
     * @param <T>              the generic type
     * @param propertySupplier the property supplier
     * @see cn.featherfly.common.lang.asserts.LocalizedAssert#isNotEmpty(SerializableSupplier)
     */
    public static <T> void isNotEmpty(SerializableSupplier<T> propertySupplier) {
        ASSERT.isNotEmpty(propertySupplier);
    }

}
