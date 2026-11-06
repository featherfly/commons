
package cn.featherfly.common.lang;

import static org.testng.Assert.assertEquals;

import java.util.Optional;

import org.testng.annotations.Test;

/**
 * <p>
 * ArrayUtilsTest
 * </p>
 *
 * @author zhongj
 */
public class ArrayUtilsTest {

    String[] ss = new String[] { "a", "b", "c" };

    String[] ns = new String[] { "1", "2", "3" };

    @Test
    public void testEach() {
        ArrayUtils.each((a, i) -> {
            System.out.println(i + " -> " + a);
            assertEquals(a, ss[i]);
        }, ss);
    }

    @Test
    public void testNumbers() {
        Integer[] is = ArrayUtils.toNumbers(Integer.class, ns);
        ArrayUtils.each((a, i) -> {
            System.out.println(i + " -> " + a);
            assertEquals(a, is[i]);
        }, is);
    }

    @Test
    public void concat() {
        String[] strs1 = Lang.array("1", "2");
        String[] strs2 = Lang.array("3", "4", "5");
        assertEquals(5, ArrayUtils.getLength(ArrayUtils.concat(strs1, strs2)));

        Object[] objs = Lang.array("1", "2");
        assertEquals(5, ArrayUtils.getLength(ArrayUtils.concat(objs, strs2)));

        int[] ints1 = Lang.array(1, 2);
        int[] ints2 = Lang.array(1, 2, 3, 4);
        assertEquals(6, ArrayUtils.getLength(ArrayUtils.concat(ints1, ints2)));
    }

    @Test
    public void concatArray() {
        String[] strs1 = Lang.array("1", "2");
        String[] strs2 = Lang.array("3", "4", "5");
        String[] strs3 = Lang.array("6", "7");

        String[] result = ArrayUtils.concatArray(strs1, strs2);
        assertEquals(5, result.length);
        System.out.println(ArrayUtils.toString(new String[] { "1", "2", "3", "4", "5" }));
        System.out.println(ArrayUtils.toString(result));
        assertEquals(ArrayUtils.toString(new String[] { "1", "2", "3", "4", "5" }),
            ArrayUtils.toString(result));

        result = ArrayUtils.concatArray(strs1, strs2, strs3);
        assertEquals(7, result.length);
        assertEquals(ArrayUtils.toString(new String[] { "1", "2", "3", "4", "5", "6", "7" }),
            ArrayUtils.toString(result));

        //
        //        Object[] objs = Lang.array("1", "2");
        //        assertEquals(5, ArrayUtils.getLength(ArrayUtils.concat(objs, strs2)));
        //
        //        int[] ints1 = Lang.array(1, 2);
        //        int[] ints2 = Lang.array(1, 2, 3, 4);
        //        assertEquals(6, ArrayUtils.getLength(ArrayUtils.concat(ints1, ints2)));
    }

    @Test
    public void concatObject() {
        String[] strs = Lang.array("str1", "str2");
        StringBuilder[] sbs = Lang.array(new StringBuilder("sb1"), new StringBuilder("sb2"));
        int[] ints = Lang.array(1, 2);
        Optional<String>[] opts = Lang.array(Optional.of("opt1"), Optional.of("opt2"));

        Object result = ArrayUtils.concatObjects(strs, sbs);
        System.out.println(ArrayUtils.toString(result));
        assertEquals(4, ArrayUtils.getLength(result));
        assertEquals(CharSequence.class, result.getClass().getComponentType());
        assertEquals(ArrayUtils.toString(new CharSequence[] { "str1", "str2", "sb1", "sb2" }),
            ArrayUtils.toString(result));

        // ----------------------------------------------
        System.out.println(result.getClass());
        System.out.println(result.getClass().getComponentType());
        result = ArrayUtils.concatObjects(strs, ints);
        assertEquals(4, ArrayUtils.getLength(result));
        // int[] 应该是用Integer包装类处理了
        assertEquals(Comparable.class, result.getClass().getComponentType());
        assertEquals(ArrayUtils.toString(new Comparable[] { "str1", "str2", "1", "2" }),
            ArrayUtils.toString(result));

        // ----------------------------------------------
        result = ArrayUtils.concatObjects(strs, ints, opts);
        assertEquals(6, ArrayUtils.getLength(result));
        assertEquals(Object.class, result.getClass().getComponentType());
        assertEquals(ArrayUtils.toString(new Object[] { "str1", "str2", "1", "2", "Optional[opt1]", "Optional[opt2]" }),
            ArrayUtils.toString(result));

    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testContactException() {
        int[] ints1 = Lang.array(1, 2);
        long[] ints2 = Lang.array(1L, 2L, 3L, 4L);
        assertEquals(6, ArrayUtils.getLength(ArrayUtils.concat(ints1, ints2)));
    }
}
