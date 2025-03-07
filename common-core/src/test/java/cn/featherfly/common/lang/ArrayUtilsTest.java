
package cn.featherfly.common.lang;

import static org.testng.Assert.assertEquals;

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
    public void testContact() {
        String[] strs1 = Lang.array("1", "2");
        String[] strs2 = Lang.array("3", "4", "5");
        assertEquals(5, ArrayUtils.getLength(ArrayUtils.concat(strs1, strs2)));

        Object[] objs = Lang.array("1", "2");
        assertEquals(5, ArrayUtils.getLength(ArrayUtils.concat(objs, strs2)));

        int[] ints1 = Lang.array(1, 2);
        int[] ints2 = Lang.array(1, 2, 3, 4);
        assertEquals(6, ArrayUtils.getLength(ArrayUtils.concat(ints1, ints2)));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testContactException() {
        int[] ints1 = Lang.array(1, 2);
        long[] ints2 = Lang.array(1L, 2L, 3L, 4L);
        assertEquals(6, ArrayUtils.getLength(ArrayUtils.concat(ints1, ints2)));
    }
}
