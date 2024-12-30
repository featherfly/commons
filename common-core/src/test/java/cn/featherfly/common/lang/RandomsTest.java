
/*
 * Thgk-Component-Commons
 * copyright featherfly 2010-2020, all rights reserved.
 * created on May 20, 2010 6:06:31 PM
 */
package cn.featherfly.common.lang;

import org.testng.annotations.Test;

import cn.featherfly.common.lang.Randoms.CharType;

public class RandomsTest {

    @Test
    public void testBoolean() {
        System.out.println("testBoolean -------------------------------");
        for (int i = 0; i < 10; i++) {
            System.out.println(Randoms.getBoolean());
        }
    }

    @Test
    public void testGaussian() {
        System.out.println("testGaussian -------------------------------");
        for (int i = 0; i < 10; i++) {
            System.out.println(Randoms.getGaussian());
        }
    }

    @Test
    public void testDouble() {
        System.out.println("testDouble -------------------------------");
        for (int i = 0; i < 10; i++) {
            System.out.println(Randoms.getDouble());
        }
    }

    @Test
    public void testLetter() {
        System.out.println("testLetter -------------------------------");
        for (int i = 0; i < 10; i++) {
            System.out.println(Randoms.getLetter());
        }
    }

    @Test
    public void testLetterUpperCase() {
        System.out.println("testLetterUpperCase -------------------------------");
        for (int i = 0; i < 10; i++) {
            System.out.println(Randoms.getLetterUpperCase());
        }
    }

    @Test
    public void testLetterLowerCase() {
        System.out.println("testLetterLowerCase -------------------------------");
        for (int i = 0; i < 10; i++) {
            System.out.println(Randoms.getLetterLowerCase());
        }
    }

    @Test
    public void testNumber() {
        System.out.println("testNumber -------------------------------");
        for (int i = 0; i < 10; i++) {
            System.out.println(Randoms.getNumber());
        }
    }

    @Test
    public void testWord() {
        System.out.println("testWord -------------------------------");
        for (int i = 0; i < 10; i++) {
            System.out.println(Randoms.getWord());
        }
    }

    @Test
    public void testString() {
        System.out.println("testString -------------------------------");
        for (int i = 0; i < 10; i++) {
            System.out.println(Randoms.getString(i + 5));
        }
    }

    @Test
    public void testStringLowerCase() {
        System.out.println("testStringLowerCase -------------------------------");
        for (int i = 0; i < 10; i++) {
            String result = Randoms.getString(i + 5, new CharType[] { CharType.LOWER_CASE });
            System.out.println(result);
        }
    }

    @Test
    public void testStringUpperCase() {
        System.out.println("testStringUpperCase -------------------------------");
        for (int i = 0; i < 10; i++) {
            String result = Randoms.getString(i + 5, new CharType[] { CharType.UPPER_CASE });
            System.out.println(result);
        }
    }

    @Test
    public void testStringNumberCase() {
        System.out.println("testStringNumberCase -------------------------------");
        for (int i = 0; i < 10; i++) {
            String result = Randoms.getString(i + 5, new CharType[] { CharType.NUMBER_CASE });
            System.out.println(result);
        }
    }

    @Test
    public void testStringLowerCaseNumberCase() {
        System.out.println("testStringLowerCaseNumberCase -------------------------------");
        for (int i = 0; i < 10; i++) {
            String result = Randoms.getString(i + 5, new CharType[] { CharType.NUMBER_CASE, CharType.LOWER_CASE });
            System.out.println(result);
        }
    }

    @Test
    public void testStringUpperCaseNumberCase() {
        System.out.println("testStringUpperCaseNumberCase -------------------------------");
        for (int i = 0; i < 10; i++) {
            String result = Randoms.getString(i + 5, new CharType[] { CharType.NUMBER_CASE, CharType.UPPER_CASE });
            System.out.println(result);
        }
    }

    @Test
    public void testStringUpperCaseLowerCase() {
        System.out.println("testStringUpperCaseNumberCase -------------------------------");
        for (int i = 0; i < 10; i++) {
            String result = Randoms.getString(i + 5, new CharType[] { CharType.LOWER_CASE, CharType.UPPER_CASE });
            System.out.println(result);
        }
    }

    @Test
    public void testStringUpperCaseLowerCaseNumberCase() {
        System.out.println("testStringUpperCaseNumberCase -------------------------------");
        for (int i = 0; i < 10; i++) {
            String result = Randoms.getString(i + 5,
                new CharType[] { CharType.LOWER_CASE, CharType.UPPER_CASE, CharType.NUMBER_CASE });
            System.out.println(result);
        }
    }
}
