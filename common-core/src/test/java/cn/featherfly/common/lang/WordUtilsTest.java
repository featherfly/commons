
package cn.featherfly.common.lang;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import cn.featherfly.common.lang.number.ChineseNumber;

/**
 * <p>
 * StringUtilsTest
 * </p>
 * <p>
 * 2019-08-16
 * </p>
 *
 * @author zhongj
 */
public class WordUtilsTest {

    ChineseNumber cn;

    String s;

    int intNum = -1;

    long longNum = -1;

    boolean simple = false;

    @Test
    public void testChineseNumber1() {
        boolean simple = true;
        cn = new ChineseNumber(simple);
        for (int i = 0; i < 10; i++) {
            String s = WordUtils.toChineseNumber(i, simple);
            System.out.println(s + " - " + cn.getChineseNumber(i));
            assertEquals(s, cn.getChineseNumber(i));
        }
        simple = false;
        cn = new ChineseNumber(simple);
        for (int i = 0; i < 10; i++) {
            String s = WordUtils.toChineseNumber(i, simple);
            System.out.println(s + " - " + cn.getChineseNumber(i));
            assertEquals(s, cn.getChineseNumber(i));
        }
    }

    @Test
    public void testChineseNumber2() {
        simple = false;

        intNum = 10;
        s = WordUtils.toChineseNumber(intNum, simple);
        System.out.println(intNum + "\t" + s);
        assertEquals(s, "壹拾");

        intNum = 11;
        s = WordUtils.toChineseNumber(intNum, simple);
        System.out.println(intNum + "\t" + s);
        assertEquals(s, "壹拾壹");

        intNum = 15;
        s = WordUtils.toChineseNumber(intNum, simple);
        System.out.println(intNum + "\t" + s);
        assertEquals(s, "壹拾伍");

        intNum = 90;
        s = WordUtils.toChineseNumber(intNum, simple);
        System.out.println(intNum + "\t" + s);
        assertEquals(s, "玖拾");

        intNum = 99;
        s = WordUtils.toChineseNumber(intNum, simple);
        System.out.println(intNum + "\t" + s);
        assertEquals(s, "玖拾玖");
    }

    @Test
    public void testChineseNumber2Simple() {
        simple = true;

        intNum = 10;
        s = WordUtils.toChineseNumber(intNum, simple);
        System.out.println(intNum + "\t" + s);
        assertEquals(s, "一十");

        intNum = 11;
        s = WordUtils.toChineseNumber(intNum, simple);
        System.out.println(intNum + "\t" + s);
        assertEquals(s, "一十一");

        intNum = 15;
        s = WordUtils.toChineseNumber(intNum, simple);
        System.out.println(intNum + "\t" + s);
        assertEquals(s, "一十五");

        intNum = 90;
        s = WordUtils.toChineseNumber(intNum, simple);
        System.out.println(intNum + "\t" + s);
        assertEquals(s, "九十");

        intNum = 99;
        s = WordUtils.toChineseNumber(intNum, simple);
        System.out.println(intNum + "\t" + s);
        assertEquals(s, "九十九");
    }

    @Test
    public void testChineseNumber3Simple() {
        simple = true;

        intNum = 100;
        s = WordUtils.toChineseNumber(intNum, simple);
        System.out.println(intNum + "\t" + s);
        assertEquals(s, "一百");

        intNum = 110;
        s = WordUtils.toChineseNumber(intNum, simple);
        System.out.println(intNum + "\t" + s);
        assertEquals(s, "一百一十");

        intNum = 101;
        s = WordUtils.toChineseNumber(intNum, simple);
        System.out.println(intNum + "\t" + s);
        assertEquals(s, "一百零一");

        intNum = 111;
        s = WordUtils.toChineseNumber(intNum, simple);
        System.out.println(intNum + "\t" + s);
        assertEquals(s, "一百一十一");
    }

    @Test
    public void testChineseNumber4Simple() {
        simple = true;

        intNum = 1000;
        s = WordUtils.toChineseNumber(intNum, simple);
        System.out.println(intNum + "\t" + s);
        assertEquals(s, "一千");

        intNum = 1001;
        s = WordUtils.toChineseNumber(intNum, simple);
        System.out.println(intNum + "\t" + s);
        assertEquals(s, "一千零一");

        intNum = 1010;
        s = WordUtils.toChineseNumber(intNum, simple);
        System.out.println(intNum + "\t" + s);
        assertEquals(s, "一千零一十");

        intNum = 1110;
        s = WordUtils.toChineseNumber(intNum, simple);
        System.out.println(intNum + "\t" + s);
        assertEquals(s, "一千一百一十");

        intNum = 1100;
        s = WordUtils.toChineseNumber(intNum, simple);
        System.out.println(intNum + "\t" + s);
        assertEquals(s, "一千一百");

        intNum = 1111;
        s = WordUtils.toChineseNumber(intNum, simple);
        System.out.println(intNum + "\t" + s);
        assertEquals(s, "一千一百一十一");
    }

    @Test
    public void testChineseNumber5Simple() {
        simple = true;

        intNum = 10000;
        s = WordUtils.toChineseNumber(intNum, simple);
        System.out.println(intNum + "\t" + s);
        assertEquals(s, "一万");

        intNum = 10001;
        s = WordUtils.toChineseNumber(intNum, simple);
        System.out.println(intNum + "\t" + s);
        assertEquals(s, "一万零一");

        intNum = 10010;
        s = WordUtils.toChineseNumber(intNum, simple);
        System.out.println(intNum + "\t" + s);
        assertEquals(s, "一万零一十");

        intNum = 10100;
        s = WordUtils.toChineseNumber(intNum, simple);
        System.out.println(intNum + "\t" + s);
        assertEquals(s, "一万零一百");

        intNum = 11110;
        s = WordUtils.toChineseNumber(intNum, simple);
        System.out.println(intNum + "\t" + s);
        assertEquals(s, "一万一千一百一十");

        intNum = 11000;
        s = WordUtils.toChineseNumber(intNum, simple);
        System.out.println(intNum + "\t" + s);
        assertEquals(s, "一万一千");

        intNum = 11111;
        s = WordUtils.toChineseNumber(intNum, simple);
        System.out.println(intNum + "\t" + s);
        assertEquals(s, "一万一千一百一十一");
    }

    @Test
    public void testChineseNumber6Simple() {
        simple = true;

        intNum = 100000;
        s = WordUtils.toChineseNumber(intNum, simple);
        System.out.println(intNum + "\t" + s);
        assertEquals(s, "一十万");

        intNum = 100001;
        s = WordUtils.toChineseNumber(intNum, simple);
        System.out.println(intNum + "\t" + s);
        assertEquals(s, "一十万零一");

        intNum = 100010;
        s = WordUtils.toChineseNumber(intNum, simple);
        System.out.println(intNum + "\t" + s);
        assertEquals(s, "一十万零一十");

        intNum = 100100;
        s = WordUtils.toChineseNumber(intNum, simple);
        System.out.println(intNum + "\t" + s);
        assertEquals(s, "一十万零一百");

        intNum = 111100;
        s = WordUtils.toChineseNumber(intNum, simple);
        System.out.println(intNum + "\t" + s);
        assertEquals(s, "一十一万一千一百");

        intNum = 111110;
        s = WordUtils.toChineseNumber(intNum, simple);
        System.out.println(intNum + "\t" + s);
        assertEquals(s, "一十一万一千一百一十");

        intNum = 110000;
        s = WordUtils.toChineseNumber(intNum, simple);
        System.out.println(intNum + "\t" + s);
        assertEquals(s, "一十一万");

        intNum = 111111;
        s = WordUtils.toChineseNumber(intNum, simple);
        System.out.println(intNum + "\t" + s);
        assertEquals(s, "一十一万一千一百一十一");
    }

    @Test
    public void testChineseNumber7Simple() {
        simple = true;

        intNum = 100040332;
        s = WordUtils.toChineseNumber(intNum, simple);
        System.out.println(intNum + "\t" + s);
        assertEquals(s, "一亿零四万零三百三十二");

        intNum = 103040332;
        s = WordUtils.toChineseNumber(intNum, simple);
        System.out.println(intNum + "\t" + s);
        assertEquals(s, "一亿零三百零四万零三百三十二");

        intNum = 133040332;
        s = WordUtils.toChineseNumber(intNum, simple);
        System.out.println(intNum + "\t" + s);
        assertEquals(s, "一亿三千三百零四万零三百三十二");

        intNum = 1103040302;
        s = WordUtils.toChineseNumber(intNum, simple);
        System.out.println(intNum + "\t" + s);
        assertEquals(s, "一十一亿零三百零四万零三百零二");

        intNum = 2147483647;
        s = WordUtils.toChineseNumber(intNum, simple);
        System.out.println(intNum + "\t" + s);
        assertEquals(s, "二十一亿四千七百四十八万三千六百四十七");

        longNum = 3002002147483647l;
        s = WordUtils.toChineseNumber(longNum, simple);
        System.out.println(intNum + "\t" + s);
        assertEquals(s, "三千零二兆零二十一亿四千七百四十八万三千六百四十七");

        longNum = 9223372036854775807l;
        s = WordUtils.toChineseNumber(longNum, simple);
        System.out.println(intNum + "\t" + s);
        assertEquals(s, "九百二十二京三千三百七十二兆零三百六十八亿五千四百七十七万五千八百零七");
    }

    @Test
    public void testChineseMoneyNumber() {
        s = WordUtils.toChineseMoneyNumber(12d);
        System.out.println(intNum + "\t" + s);
        assertEquals(s, "壹拾贰圆整");

        s = WordUtils.toChineseMoneyNumber(12.3);
        System.out.println(intNum + "\t" + s);
        assertEquals(s, "壹拾贰圆叁角整");
        s = WordUtils.toChineseMoneyNumber(12.34);
        System.out.println(intNum + "\t" + s);
        assertEquals(s, "壹拾贰圆叁角肆分整");

        s = WordUtils.toChineseMoneyNumber(12.04);
        System.out.println(intNum + "\t" + s);
        assertEquals(s, "壹拾贰圆零角肆分整");

    }

}
