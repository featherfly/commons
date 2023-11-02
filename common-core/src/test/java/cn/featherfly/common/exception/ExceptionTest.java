
package cn.featherfly.common.exception;

import static org.testng.Assert.assertEquals;

import java.util.Locale;

import org.testng.annotations.Test;

import cn.featherfly.common.locale.Text;

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

        //        String key = ResourceBundleUtils.KEY_SIGN + code.getKey();
        String key = code.getKey();

        Locale.setDefault(Locale.CHINESE);

        String msg = "cn没有这样的属性：pn";

        try {
            throw new PracticeException(code);
        } catch (PracticeException e) {
            //            assertEquals(e.getMessage(), key);
            assertEquals(e.getMessage(), msg);
            assertEquals(e.getLocalizedMessage(), msg);
            assertEquals(e.getExceptionCode().getKey(), key);
        }

        msg = "cn has no such property：pn";

        Locale.setDefault(Locale.ENGLISH);
        try {
            throw new PracticeException(code);
        } catch (PracticeException e) {
            //            assertEquals(e.getMessage(), key);
            assertEquals(e.getMessage(), msg);
            assertEquals(e.getLocalizedMessage(), msg);
            assertEquals(e.getExceptionCode().getKey(), key);
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
            Locale.setDefault(Locale.CHINESE);
            throw new UnsupportedException();
        } catch (UnsupportedException e) {
            //            assertEquals(e.getMessage(), "#unsupported");
            assertEquals(e.getMessage(), "未提供支持");
            Locale.setDefault(Locale.ENGLISH);
            System.out.println(e.getMessage());
            System.err.println(e.getLocalizedMessage());
        }

        try {
            Locale.setDefault(Locale.ENGLISH);
            throw new UnsupportedException();
        } catch (UnsupportedException e) {
            assertEquals(e.getMessage(), "unsupported exception");
            System.out.println(e.getLocalizedMessage());
        }
    }

    @Test
    public void InitException() {
        try {
            throw new InitException(Text.HELLO.getMessage());
        } catch (InitException e) {
            assertEquals(e.getMessage(), "你好");
            assertEquals(e.getLocalizedMessage(), "你好");
        }

        try {
            throw new InitException(Text.HELLO.getMessage(Locale.ENGLISH));
        } catch (InitException e) {
            assertEquals(e.getMessage(), "hello");
            assertEquals(e.getLocalizedMessage(), "hello");
        }
    }

}
