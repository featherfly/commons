
package cn.featherfly.common.exception;

import static org.testng.Assert.assertEquals;

import java.util.Locale;

import org.testng.annotations.Test;

import cn.featherfly.common.locale.ResourceBundleUtils;

/**
 * <p>
 * PracticeExceptionTest 类的说明放这里
 * </p>
 *
 * @author zhongj
 */
public class ExceptionTest {

    PracticeExceptionCode code = PracticeExceptionCode.createNoPropertyCode("cn", "pn");

    @Test
    public void testPracticeException() {

        String key = ResourceBundleUtils.KEY_SIGN + code.getKey();

        Locale.setDefault(Locale.CHINESE);

        try {
            throw new PracticeException(code);
        } catch (PracticeException e) {
            assertEquals(e.getMessage(), key);
            assertEquals(e.getLocalizedMessage(), "cn没有这样的属性：pn");
        }

        Locale.setDefault(Locale.ENGLISH);
        try {
            throw new PracticeException(code);
        } catch (PracticeException e) {
            assertEquals(e.getMessage(), key);
            assertEquals(e.getLocalizedMessage(), "cn has no such property：pn");
        }
    }

    @Test
    public void testLoadedMessageLocalizedExceptionCode() {
        Locale.setDefault(Locale.CHINESE);
        LoadedMessageLocalizedExceptionCode ec = new LoadedMessageLocalizedExceptionCode(PracticeException.class,
                code.getModule(), code.getNum(), code.getKey(), code.getArgus());
        assertEquals(ec.getMessage(), "cn没有这样的属性：pn");

        Locale.setDefault(Locale.ENGLISH);
        ec = new LoadedMessageLocalizedExceptionCode(PracticeException.class, code.getModule(), code.getNum(),
                code.getKey(), code.getArgus());
        assertEquals(ec.getMessage(), "cn has no such property：pn");
    }

    @Test
    public void testUnsupportedException() {
        try {
            throw new UnsupportedException();
        } catch (UnsupportedException e) {
            assertEquals(e.getMessage(), "#unsupported");
            System.out.println(e.getLocalizedMessage());
        }
    }

    @Test
    public void testUnsupportedException2() {
        try {
            throw new UnsupportedException();
        } catch (UnsupportedException e) {
            assertEquals(e.getMessage(), "#unsupported");
            Locale.setDefault(Locale.ENGLISH);
            System.out.println(e.getLocalizedMessage());
        }
    }
}
