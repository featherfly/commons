
package cn.featherfly.common.lang;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import cn.featherfly.common.constant.Chars;

/**
 * <p>
 * CharsTest
 * </p>
 * <p>
 * 2019-08-26
 * </p>
 *
 * @author zhongj
 */
public class CharsTest {

    @Test
    public void test() {
        assertEquals(Chars.QM, "'");
        assertEquals(Chars.DQM, "\"");
        assertEquals(Chars.QM_CHAR, '\'');
        assertEquals(Chars.DQM_CHAR, '"');
        assertEquals(Chars.TL, "\\");
        assertEquals(Chars.TL_CHAR, '\\');
        System.out.println(Chars.QM);
        System.out.println(Chars.DQM);
        System.out.println(Chars.QM_CHAR);
        System.out.println(Chars.DQM_CHAR);
        System.out.println(Chars.TL);
        System.out.println(Chars.TL_CHAR);
    }
}
