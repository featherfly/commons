
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2026-09-24 18:09:24
 * @Copyright: 2026 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.exception;

import static org.testng.Assert.assertEquals;

import java.time.LocalDate;
import java.util.Locale;

import org.testng.annotations.Test;

import cn.featherfly.common.lang.Dates;
import cn.featherfly.common.tuple.Tuples;

/**
 * ExceptionCodeExceptionCreatorTest.
 * 
 * @author zhongj
 */
public class ExceptionCodeExceptionCreatorTest {

    ExceptionCodeExceptionImpl e;

    @Test
    public void code() {
        e = ExceptionCodeExceptionImpl.STRING.create("yufei");
        System.out.println(e.getExceptionCode().toString());
        assertEquals(e.getExceptionCode().getModule(), "CODE");
        assertEquals(e.getExceptionCode().getNum(), 100001);
        assertEquals(e.getExceptionCode().getMessage(), "#string");
        assertEquals(e.getExceptionCode().getCode(), "CODE100001");

        e = ExceptionCodeExceptionImpl.INTEGER.create(18);
        System.out.println(e.getExceptionCode().toString());
        assertEquals(e.getExceptionCode().getModule(), "CODE");
        assertEquals(e.getExceptionCode().getNum(), 100002);
        assertEquals(e.getExceptionCode().getMessage(), "#integer");
        assertEquals(e.getExceptionCode().getCode(), "CODE100002");

        e = ExceptionCodeExceptionImpl.STRING_INTEGER.create(Tuples.of("yufei", 18));
        System.out.println(e.getExceptionCode().toString());
        assertEquals(e.getExceptionCode().getModule(), "CODE");
        assertEquals(e.getExceptionCode().getNum(), 100003);
        assertEquals(e.getExceptionCode().getMessage(), "#string_integer");
        assertEquals(e.getExceptionCode().getCode(), "CODE100003");

        e = ExceptionCodeExceptionImpl.DATE.create(Dates.toDate(LocalDate.of(2012, 12, 12)));
        System.out.println(e.getExceptionCode().toString());
        assertEquals(e.getExceptionCode().getModule(), "CODE");
        assertEquals(e.getExceptionCode().getNum(), 100004);
        assertEquals(e.getExceptionCode().getMessage(), "#date");
        assertEquals(e.getExceptionCode().getCode(), "CODE100004");
    }

    @Test
    public void chineseDefaultLocale() {
        Locale.setDefault(Locale.CHINESE);
        e = ExceptionCodeExceptionImpl.INTEGER.create(18);
        System.out.println(e.getMessage());
        System.out.println(e.getLocalizedMessage());
        assertEquals(e.getMessage(), "整数参数 18");

        e = ExceptionCodeExceptionImpl.STRING.create("yufei");
        System.out.println(e.getMessage());
        System.out.println(e.getLocalizedMessage());
        assertEquals(e.getMessage(), "字符串参数 yufei");

        e = ExceptionCodeExceptionImpl.STRING_INTEGER.create(Tuples.of("yufei", 18));
        System.out.println(e.getMessage());
        System.out.println(e.getLocalizedMessage());
        assertEquals(e.getMessage(), "字符串和整数参数 yufei - 18");

        e = ExceptionCodeExceptionImpl.DATE.create(Dates.toDate(LocalDate.of(2012, 12, 12)));
        System.out.println(e.getMessage());
        System.out.println(e.getLocalizedMessage());
        assertEquals(e.getMessage(), "日期参数 2012年12月12日");
    }

    @Test
    public void chineseGivenLocale() {
        Locale.setDefault(Locale.ENGLISH);
        e = ExceptionCodeExceptionImpl.INTEGER.create(18, Locale.CHINESE);
        assertEquals(e.getMessage(), "整数参数 18");

        e = ExceptionCodeExceptionImpl.STRING.create("yufei", Locale.CHINESE);
        assertEquals(e.getMessage(), "字符串参数 yufei");

        e = ExceptionCodeExceptionImpl.STRING_INTEGER.create(Tuples.of("yufei", 18), Locale.CHINESE);
        assertEquals(e.getMessage(), "字符串和整数参数 yufei - 18");

        // MessageFormat使用了默认Locale Locale.ENGLISH
        e = ExceptionCodeExceptionImpl.DATE.create(Dates.toDate(LocalDate.of(2012, 12, 12)), Locale.CHINESE);
        assertEquals(e.getMessage(), "日期参数 December 12, 2012");
    }

    @Test
    public void englishDefaultLocale() {
        Locale.setDefault(Locale.ENGLISH);
        e = ExceptionCodeExceptionImpl.INTEGER.create(18);
        System.out.println(e.getMessage());
        System.out.println(e.getLocalizedMessage());
        assertEquals(e.getMessage(), "int argument 18");

        e = ExceptionCodeExceptionImpl.STRING.create("yufei");
        System.out.println(e.getMessage());
        System.out.println(e.getLocalizedMessage());
        assertEquals(e.getMessage(), "string argument yufei");

        e = ExceptionCodeExceptionImpl.STRING_INTEGER.create(Tuples.of("yufei", 18));
        System.out.println(e.getMessage());
        System.out.println(e.getLocalizedMessage());
        assertEquals(e.getMessage(), "string and int argument yufei - 18");

        e = ExceptionCodeExceptionImpl.DATE.create(Dates.toDate(LocalDate.of(2012, 12, 12)));
        System.out.println(e.getMessage());
        System.out.println(e.getLocalizedMessage());
        assertEquals(e.getMessage(), "date argument December 12, 2012");
    }

    @Test
    public void englishGivenLocale() {
        Locale.setDefault(Locale.CHINESE);
        e = ExceptionCodeExceptionImpl.INTEGER.create(18, Locale.ENGLISH);
        assertEquals(e.getMessage(), "int argument 18");

        e = ExceptionCodeExceptionImpl.STRING.create("yufei", Locale.ENGLISH);
        assertEquals(e.getMessage(), "string argument yufei");

        e = ExceptionCodeExceptionImpl.STRING_INTEGER.create(Tuples.of("yufei", 18), Locale.ENGLISH);
        assertEquals(e.getMessage(), "string and int argument yufei - 18");

        // MessageFormat使用了默认Locale Locale.CHINESE
        e = ExceptionCodeExceptionImpl.DATE.create(Dates.toDate(LocalDate.of(2012, 12, 12)), Locale.ENGLISH);
        assertEquals(e.getMessage(), "date argument 2012年12月12日");
    }
}
