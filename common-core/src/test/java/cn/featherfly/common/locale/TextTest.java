
package cn.featherfly.common.locale;

import java.util.Locale;

import org.testng.annotations.Test;

/**
 * <p>
 * TextTest
 * </p>
 *
 * @author zhongj
 */
public class TextTest {
    @Test
    void test() {
        System.out.println(Text.HELLO.getMessage());
        System.out.println(Text.BYE.getMessage());
        System.out.println(Text.HEHE.getMessage("??"));
    }

    @Test
    void test2() {
        System.out.println(Text.HELLO.getMessage(Locale.ENGLISH));
        System.out.println(Text.BYE.getMessage(Locale.ENGLISH));
        System.out.println(Text.HEHE.getMessage(Locale.ENGLISH, "??"));
    }
}
