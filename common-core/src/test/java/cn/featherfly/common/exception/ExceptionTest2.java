
package cn.featherfly.common.exception;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

/**
 * PracticeExceptionTest2
 *
 * @author zhongj
 */
public class ExceptionTest2 {

    @Test
    public void testStringFormat() {
        String msg = "cn has no such property：pn";
        String argu1 = "cn";
        String argu2 = "pn";

        try {
            throw new PracticeException2("{} has no such property：{}", new Object[] { argu1, argu2 });
        } catch (PracticeException2 e) {
            assertEquals(e.getMessage(), msg);
            assertEquals(e.getLocalizedMessage(), msg);
        }
    }
}
