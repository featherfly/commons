
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
        ArrayUtils.each(ss, (a, i) -> {
            System.out.println(i + " -> " + a);
            assertEquals(a, ss[i]);
        });
    }

    @Test
    public void testNumbers() {
        Integer[] is = ArrayUtils.toNumbers(Integer.class, ns);
        ArrayUtils.each(is, (a, i) -> {
            System.out.println(i + " -> " + a);
            assertEquals(a, is[i]);
        });

    }
}
