
/*
 * Thgk-Commons
 * copyright featherfly 2010-2020, all rights reserved.
 * created on May 21, 2010 3:46:26 PM
 */
package cn.featherfly.common.lang;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.Date;

import org.testng.annotations.Test;


public class DateUtilsTest {

    Date date1 = DateUtils.getTime(2010, 5, 21, 20, 10, 10);
    Date date2 = DateUtils.getTime(2011, 4, 21, 20, 10, 10);
    Date date3 = DateUtils.getTime(2011, 5, 20, 20, 10, 10);
    Date date4 = DateUtils.getTime(2011, 5, 21, 19, 10, 10);
    Date date5 = DateUtils.getTime(2011, 5, 21, 20, 9, 10);
    Date date6 = DateUtils.getTime(2011, 5, 21, 20, 10, 9);
    Date date7 = DateUtils.getTime(2011, 5, 21, 20, 10, 10);
    Date date8 = DateUtils.getTime(2011, 5, 21, 20, 10, 10);

    Date date11 = DateUtils.getTime(2010, 5, 21, 20, 10, 10);
    Date date12 = DateUtils.getTime(2011, 1, 4, 20, 10, 10);
    Date date13 = DateUtils.getTime(2011, 1, 22, 20, 10, 10);
    Date date14 = DateUtils.getTime(2011, 5, 4, 20, 10, 10);
    Date date15 = DateUtils.getTime(2011, 5, 22, 20, 10, 10);
    Date date16 = DateUtils.getTime(2012, 5, 3, 20, 10, 10);
    Date date17 = DateUtils.getTime(2012, 5, 22, 20, 10, 10);
    Date date18 = DateUtils.getTime(2013, 5, 22, 20, 10, 10);

    @Test
    public void testGetYearNum() {
    	assertEquals(0, DateUtils.getYearNumber(date11, date12));
    	assertEquals(0, DateUtils.getYearNumber(date11, date14));
    	assertEquals(1, DateUtils.getYearNumber(date11, date15));
    	assertEquals(1, DateUtils.getYearNumber(date11, date16));
    	assertEquals(2, DateUtils.getYearNumber(date11, date17));
    	assertEquals(3, DateUtils.getYearNumber(date11, date18));
    }

    @Test
    public void testGetMonthNum() {
    	assertEquals(7, DateUtils.getMonthNumber(date11, date12));
    	assertEquals(8, DateUtils.getMonthNumber(date11, date13));
    	assertEquals(11, DateUtils.getMonthNumber(date11, date14));
    	assertEquals(12, DateUtils.getMonthNumber(date11, date15));
    	assertEquals(23, DateUtils.getMonthNumber(date11, date16));
    	assertEquals(24, DateUtils.getMonthNumber(date11, date17));
    }

    @Test
    public void testFormart(){
        String strDate = "2010-01-20";
        Date firstDate = DateUtils.getDate(2010, 1, 20);
        assertEquals(strDate, DateUtils.formartDate(firstDate));

        String strDate2 = "20/1/2010";
        assertEquals(strDate2, DateUtils.formart(firstDate,"dd/M/yyyy"));

        String strDate3 = "2010-01-20 10:10:10";
        Date firstDate3 = DateUtils.getTime(2010, 1, 20, 10, 10, 10);
        assertEquals(strDate3, DateUtils.formartTime(firstDate3));

        String strDate4 = "2010-01-20 10:10";
        assertEquals(strDate4, DateUtils.formart(firstDate3,"yyyy-MM-dd hh:ss"));

        assertEquals("", DateUtils.formart(null,"yyyy-MM-dd hh:ss"));
    }

    @Test
    public void testParse(){
        String strDate = "2010-01-20";
        Date date1 = DateUtils.getDate(2010, 1, 20);
        Date date2 = DateUtils.parseDate(strDate);

        assertEquals(DateUtils.getYear(date1),DateUtils.getYear(date2));
        assertEquals(DateUtils.getMonth(date1),DateUtils.getMonth(date2));
        assertEquals(DateUtils.getDayOfMonth(date1),DateUtils.getDayOfMonth(date2));

        strDate = "20/1/2010";
        date1 = DateUtils.getDate(2010, 1, 20);
        date2 = DateUtils.parse(strDate,"dd/M/yyyy");
        assertEquals(DateUtils.getYear(date1),DateUtils.getYear(date2));
        assertEquals(DateUtils.getMonth(date1),DateUtils.getMonth(date2));
        assertEquals(DateUtils.getDayOfMonth(date1),DateUtils.getDayOfMonth(date2));

        strDate = "2010-01-20 10:10:10";
        date1 = DateUtils.getTime(2010, 1, 20, 10, 10, 10);
        date2 = DateUtils.parseTime(strDate);
        assertEquals(DateUtils.getYear(date1),DateUtils.getYear(date2));
        assertEquals(DateUtils.getMonth(date1),DateUtils.getMonth(date2));
        assertEquals(DateUtils.getDayOfMonth(date1),DateUtils.getDayOfMonth(date2));
        assertEquals(DateUtils.getHour(date1),DateUtils.getHour(date2));
        assertEquals(DateUtils.getMinute(date1),DateUtils.getMinute(date2));
        assertEquals(DateUtils.getSecond(date1),DateUtils.getSecond(date2));

        strDate = "2010-01-20 10:10";
        date1 = DateUtils.getTime(2010, 1, 20, 10, 10, 10);
        date2 = DateUtils.parse(strDate,"yyyy-MM-dd hh:mm");
        assertEquals(DateUtils.getYear(date1),DateUtils.getYear(date2));
        assertEquals(DateUtils.getMonth(date1),DateUtils.getMonth(date2));
        assertEquals(DateUtils.getDayOfMonth(date1),DateUtils.getDayOfMonth(date2));
        assertEquals(DateUtils.getHour(date1),DateUtils.getHour(date2));
        assertEquals(DateUtils.getMinute(date1),DateUtils.getMinute(date2));

        try {
        	DateUtils.parse(strDate,"yyyy/MM");
        } catch(IllegalArgumentException e){
        	return;
        }
        assertTrue(false);
    }
    @Test
    public void testDayofweek() {
    	// 星期一
    	Date date = DateUtils.getDate(2011, 3, 21);
    	assertEquals(DateUtils.MONDAY, DateUtils.getDayOfWeek(date));
    	// 星期二
    	date = DateUtils.getDate(2011, 3, 22);
    	assertEquals(DateUtils.TUESDAY, DateUtils.getDayOfWeek(date));
    	// 星期三
    	date = DateUtils.getDate(2011, 3, 23);
    	assertEquals(DateUtils.WEDNESDAY, DateUtils.getDayOfWeek(date));
    	// 星期四
    	date = DateUtils.getDate(2011, 3, 24);
    	assertEquals(DateUtils.THURSDAY, DateUtils.getDayOfWeek(date));
    	// 星期五
    	date = DateUtils.getDate(2011, 3, 25);
    	assertEquals(DateUtils.FRIDAY, DateUtils.getDayOfWeek(date));
    	// 星期六
    	date = DateUtils.getDate(2011, 3, 26);
    	assertEquals(DateUtils.SATURDAY, DateUtils.getDayOfWeek(date));
    	// 星期日
    	date = DateUtils.getDate(2011, 3, 27);
    	assertEquals(DateUtils.SUNDAY, DateUtils.getDayOfWeek(date));
    }
    @Test
    public void testDayofYear() {
    	Date date = DateUtils.getDate(2011, 2, 1);
    	assertEquals(32, DateUtils.getDayOfYear(date));
    	date = DateUtils.getDate(2011, 3, 1);
    	assertEquals(60, DateUtils.getDayOfYear(date));
    }
    @Test
    public void testWeekofYear() {
    	//星期六
    	Date date = DateUtils.getDate(2011, 1, 1);
    	assertEquals(1, DateUtils.getWeekOfYear(date));
    	//星期天
    	date = DateUtils.getDate(2011, 1, 2);
    	assertEquals(1, DateUtils.getWeekOfYear(date));
    	//星期天
    	date = DateUtils.getDate(2011, 1, 30);
    	assertEquals(5, DateUtils.getWeekOfYear(date));
    	date = DateUtils.getDate(2011, 1, 31);
    	assertEquals(6, DateUtils.getWeekOfYear(date));

    	date = DateUtils.getDate(2011, 2, 1);
    	assertEquals(6, DateUtils.getWeekOfYear(date));
    	//星期天
    	date = DateUtils.getDate(2011, 2, 6);
    	assertEquals(6, DateUtils.getWeekOfYear(date));
    	//星期一
    	date = DateUtils.getDate(2011, 2, 7);
    	assertEquals(7, DateUtils.getWeekOfYear(date));
    }

    @Test
    public void testCompareYear(){
        Date firstDate = DateUtils.getDate(2010, 1, 20);
        Date secondDate = DateUtils.getDate(2011, 1, 21);
        Date thirdDate = DateUtils.getDate(2011, 1, 21);
        assertEquals(DateUtils.COMPARE_LT, DateUtils.compareYear(firstDate, secondDate));
        assertEquals(DateUtils.COMPARE_EQ, DateUtils.compareYear(secondDate, thirdDate));
        assertEquals(DateUtils.COMPARE_GT, DateUtils.compareYear(thirdDate, firstDate));
    }
    @Test
    public void testCompareMonth(){
        Date date1 = DateUtils.getDate(2010, 1, 21);
        Date date2 = DateUtils.getDate(2010, 10, 21);
        Date date3 = DateUtils.getDate(2011, 2, 21);
        Date date4 = DateUtils.getDate(2011, 2, 21);
        assertEquals(DateUtils.COMPARE_LT, DateUtils.compareMonth(date1, date2));
        assertEquals(DateUtils.COMPARE_LT, DateUtils.compareMonth(date2, date3));
        assertEquals(DateUtils.COMPARE_EQ, DateUtils.compareMonth(date3, date4));
        assertEquals(DateUtils.COMPARE_GT, DateUtils.compareMonth(date3, date1));
        assertEquals(DateUtils.COMPARE_GT, DateUtils.compareMonth(date4, date2));
    }

    @Test
    public void testCompareDay(){
        Date date1 = DateUtils.getDate(2010, 1, 21);
        Date date2 = DateUtils.getDate(2010, 10, 1);
        Date date3 = DateUtils.getDate(2010, 10, 1);
        Date date4 = DateUtils.getDate(2011, 2, 21);
        Date date5 = DateUtils.getDate(2011, 2, 21);
        assertEquals(DateUtils.COMPARE_LT, DateUtils.compareDay(date1, date2));
        assertEquals(DateUtils.COMPARE_EQ, DateUtils.compareDay(date2, date3));
        assertEquals(DateUtils.COMPARE_LT, DateUtils.compareDay(date3, date4));
        assertEquals(DateUtils.COMPARE_EQ, DateUtils.compareDay(date4, date5));
        assertEquals(DateUtils.COMPARE_GT, DateUtils.compareDay(date5, date3));
    }

    @Test
    public void testCompareHour(){
        Date date1 = DateUtils.getTime(2010, 1, 21, 1, 0, 0);
        Date date2 = DateUtils.getTime(2011, 1, 21, 1, 0, 0);
        Date date3 = DateUtils.getTime(2010, 2, 21, 1, 0, 0);
        Date date4 = DateUtils.getTime(2010, 1, 21, 2, 0, 0);
        Date date5 = DateUtils.getTime(2010, 1, 21, 1, 0, 0);

        assertEquals(DateUtils.COMPARE_LT, DateUtils.compareHour(date1, date2));
        assertEquals(DateUtils.COMPARE_LT, DateUtils.compareHour(date1, date3));
        assertEquals(DateUtils.COMPARE_LT, DateUtils.compareHour(date1, date2));
        assertEquals(DateUtils.COMPARE_LT, DateUtils.compareHour(date1, date4));

        assertEquals(DateUtils.COMPARE_EQ, DateUtils.compareHour(date1, date5));

        assertEquals(DateUtils.COMPARE_GT, DateUtils.compareHour(date4, date1));
        assertEquals(DateUtils.COMPARE_GT, DateUtils.compareHour(date3, date1));
        assertEquals(DateUtils.COMPARE_GT, DateUtils.compareHour(date2, date1));
        assertEquals(DateUtils.COMPARE_GT, DateUtils.compareHour(date2, date3));
    }

    @Test
    public void testCompareMinute(){
        Date date1 = DateUtils.getTime(2010, 5, 21, 10, 10, 0);
        Date date2 = DateUtils.getTime(2011, 4, 21, 10, 10, 0);
        Date date3 = DateUtils.getTime(2011, 5, 20, 10, 10, 0);
        Date date4 = DateUtils.getTime(2011, 5, 21, 9, 10, 0);
        Date date5 = DateUtils.getTime(2011, 5, 21, 10, 9, 0);
        Date date6 = DateUtils.getTime(2011, 5, 21, 10, 10, 0);
        Date date7 = DateUtils.getTime(2011, 5, 21, 10, 10, 0);

        assertEquals(DateUtils.COMPARE_LT, DateUtils.compareMinute(date1, date2));
        assertEquals(DateUtils.COMPARE_LT, DateUtils.compareMinute(date2, date3));
        assertEquals(DateUtils.COMPARE_LT, DateUtils.compareMinute(date3, date4));
        assertEquals(DateUtils.COMPARE_LT, DateUtils.compareMinute(date4, date5));
        assertEquals(DateUtils.COMPARE_LT, DateUtils.compareMinute(date5, date6));

        assertEquals(DateUtils.COMPARE_LT, DateUtils.compareMinute(date1, date6));
        assertEquals(DateUtils.COMPARE_LT, DateUtils.compareMinute(date2, date6));
        assertEquals(DateUtils.COMPARE_LT, DateUtils.compareMinute(date3, date6));
        assertEquals(DateUtils.COMPARE_LT, DateUtils.compareMinute(date4, date6));
        assertEquals(DateUtils.COMPARE_LT, DateUtils.compareMinute(date5, date6));

        assertEquals(DateUtils.COMPARE_EQ, DateUtils.compareMinute(date6, date7));

        assertEquals(DateUtils.COMPARE_GT, DateUtils.compareMinute(date2, date1));
        assertEquals(DateUtils.COMPARE_GT, DateUtils.compareMinute(date3, date2));
        assertEquals(DateUtils.COMPARE_GT, DateUtils.compareMinute(date4, date3));
        assertEquals(DateUtils.COMPARE_GT, DateUtils.compareMinute(date5, date4));
        assertEquals(DateUtils.COMPARE_GT, DateUtils.compareMinute(date6, date5));

        assertEquals(DateUtils.COMPARE_GT, DateUtils.compareMinute(date6, date1));
        assertEquals(DateUtils.COMPARE_GT, DateUtils.compareMinute(date6, date2));
        assertEquals(DateUtils.COMPARE_GT, DateUtils.compareMinute(date6, date3));
        assertEquals(DateUtils.COMPARE_GT, DateUtils.compareMinute(date6, date4));
        assertEquals(DateUtils.COMPARE_GT, DateUtils.compareMinute(date6, date5));
    }

    @Test
    public void testCompareSecond(){
        Date date1 = DateUtils.getTime(2010, 5, 21, 10, 10, 10);
        Date date2 = DateUtils.getTime(2011, 4, 21, 10, 10, 10);
        Date date3 = DateUtils.getTime(2011, 5, 20, 10, 10, 10);
        Date date4 = DateUtils.getTime(2011, 5, 21, 9, 10, 10);
        Date date5 = DateUtils.getTime(2011, 5, 21, 10, 9, 10);
        Date date6 = DateUtils.getTime(2011, 5, 21, 10, 10, 9);
        Date date7 = DateUtils.getTime(2011, 5, 21, 10, 10, 10);
        Date date8 = DateUtils.getTime(2011, 5, 21, 10, 10, 10);

        assertEquals(DateUtils.COMPARE_LT, DateUtils.compareSecond(date1, date2));
        assertEquals(DateUtils.COMPARE_LT, DateUtils.compareSecond(date2, date3));
        assertEquals(DateUtils.COMPARE_LT, DateUtils.compareSecond(date3, date4));
        assertEquals(DateUtils.COMPARE_LT, DateUtils.compareSecond(date4, date5));
        assertEquals(DateUtils.COMPARE_LT, DateUtils.compareSecond(date5, date6));
        assertEquals(DateUtils.COMPARE_LT, DateUtils.compareSecond(date6, date7));

        assertEquals(DateUtils.COMPARE_LT, DateUtils.compareSecond(date1, date7));
        assertEquals(DateUtils.COMPARE_LT, DateUtils.compareSecond(date2, date7));
        assertEquals(DateUtils.COMPARE_LT, DateUtils.compareSecond(date3, date7));
        assertEquals(DateUtils.COMPARE_LT, DateUtils.compareSecond(date4, date7));
        assertEquals(DateUtils.COMPARE_LT, DateUtils.compareSecond(date5, date7));
        assertEquals(DateUtils.COMPARE_LT, DateUtils.compareSecond(date6, date7));

        assertEquals(DateUtils.COMPARE_EQ, DateUtils.compareSecond(date7, date8));

        assertEquals(DateUtils.COMPARE_GT, DateUtils.compareSecond(date2, date1));
        assertEquals(DateUtils.COMPARE_GT, DateUtils.compareSecond(date3, date2));
        assertEquals(DateUtils.COMPARE_GT, DateUtils.compareSecond(date4, date3));
        assertEquals(DateUtils.COMPARE_GT, DateUtils.compareSecond(date5, date4));
        assertEquals(DateUtils.COMPARE_GT, DateUtils.compareSecond(date6, date5));
        assertEquals(DateUtils.COMPARE_GT, DateUtils.compareSecond(date7, date6));

        assertEquals(DateUtils.COMPARE_GT, DateUtils.compareSecond(date7, date1));
        assertEquals(DateUtils.COMPARE_GT, DateUtils.compareSecond(date7, date2));
        assertEquals(DateUtils.COMPARE_GT, DateUtils.compareSecond(date7, date3));
        assertEquals(DateUtils.COMPARE_GT, DateUtils.compareSecond(date7, date4));
        assertEquals(DateUtils.COMPARE_GT, DateUtils.compareSecond(date7, date5));
        assertEquals(DateUtils.COMPARE_GT, DateUtils.compareSecond(date7, date6));
    }

    @Test
    public void testBeforeDay_() {
        Date firstDate = DateUtils.getDate(2010, 1, 20);
        Date secondDate = DateUtils.getDate(2010, 1, 21);
        assertTrue(DateUtils.beforeDay(firstDate, secondDate));

        firstDate = DateUtils.getDate(2010, 1, 20);
        secondDate = DateUtils.getDate(2010, 1, 20);
        assertFalse(DateUtils.beforeDay(firstDate, secondDate));

        firstDate = DateUtils.getDate(2010, 1, 20);
        secondDate = DateUtils.getDate(2010, 1, 19);
        assertFalse(DateUtils.beforeDay(firstDate, secondDate));

        // -----------

        firstDate = DateUtils.getDate(2009, 1, 20);
        secondDate = DateUtils.getDate(2010, 1, 20);
        assertTrue(DateUtils.beforeDay(firstDate, secondDate));

        firstDate = DateUtils.getDate(2010, 1, 20);
        secondDate = DateUtils.getDate(2009, 1, 20);
        assertFalse(DateUtils.beforeDay(firstDate, secondDate));

        // -----------

        firstDate = DateUtils.getDate(2010, 1, 20);
        secondDate = DateUtils.getDate(2010, 2, 20);
        assertTrue(DateUtils.beforeDay(firstDate, secondDate));

        firstDate = DateUtils.getDate(2010, 2, 20);
        secondDate = DateUtils.getDate(2010, 1, 20);
        assertFalse(DateUtils.beforeDay(firstDate, secondDate));

    }

    @Test
    public void testBeforeYear(){
        assertTrue(DateUtils.beforeYear(date1, date2));

        assertFalse(DateUtils.beforeYear(date2, date3));
        assertFalse(DateUtils.beforeYear(date3, date4));
        assertFalse(DateUtils.beforeYear(date4, date5));
        assertFalse(DateUtils.beforeYear(date5, date6));
        assertFalse(DateUtils.beforeYear(date6, date7));
        assertFalse(DateUtils.beforeYear(date7, date8));

        assertFalse(DateUtils.beforeYear(date2, date1));
    }

    @Test
    public void testEqualsYear(){
        assertFalse(DateUtils.equalsYear(date1, date2));

        assertTrue(DateUtils.equalsYear(date2, date3));
        assertTrue(DateUtils.equalsYear(date3, date4));
        assertTrue(DateUtils.equalsYear(date4, date5));
        assertTrue(DateUtils.equalsYear(date5, date6));
        assertTrue(DateUtils.equalsYear(date6, date7));
        assertTrue(DateUtils.equalsYear(date7, date8));

        assertFalse(DateUtils.equalsYear(date2, date1));
    }

    @Test
    public void testAfterYear() {
        assertFalse(DateUtils.afterYear(date1, date2));

        assertFalse(DateUtils.afterYear(date2, date3));
        assertFalse(DateUtils.afterYear(date3, date4));
        assertFalse(DateUtils.afterYear(date4, date5));
        assertFalse(DateUtils.afterYear(date5, date6));
        assertFalse(DateUtils.afterYear(date6, date7));
        assertFalse(DateUtils.afterYear(date7, date8));

        assertTrue(DateUtils.afterYear(date2, date1));
    }

    @Test
    public void testBeforeMonth(){
        assertTrue(DateUtils.beforeMonth(date1, date2));
        assertTrue(DateUtils.beforeMonth(date2, date3));
        assertTrue(DateUtils.beforeMonth(date1, date3));
        assertTrue(DateUtils.beforeMonth(date2, date3));

        assertFalse(DateUtils.beforeMonth(date3, date4));
        assertFalse(DateUtils.beforeMonth(date4, date5));
        assertFalse(DateUtils.beforeMonth(date5, date6));
        assertFalse(DateUtils.beforeMonth(date6, date7));
        assertFalse(DateUtils.beforeMonth(date7, date8));

        assertFalse(DateUtils.beforeMonth(date2, date1));
        assertFalse(DateUtils.beforeMonth(date3, date2));

        assertFalse(DateUtils.beforeMonth(date3, date1));
        assertFalse(DateUtils.beforeMonth(date3, date2));
    }

    @Test
    public void testEqualsMonth(){
        assertFalse(DateUtils.equalsMonth(date1, date2));
        assertFalse(DateUtils.equalsMonth(date2, date3));
        assertFalse(DateUtils.equalsMonth(date1, date3));
        assertFalse(DateUtils.equalsMonth(date2, date3));

        assertTrue(DateUtils.equalsMonth(date3, date4));
        assertTrue(DateUtils.equalsMonth(date4, date5));
        assertTrue(DateUtils.equalsMonth(date5, date6));
        assertTrue(DateUtils.equalsMonth(date6, date7));
        assertTrue(DateUtils.equalsMonth(date7, date8));

        assertFalse(DateUtils.equalsMonth(date2, date1));
        assertFalse(DateUtils.equalsMonth(date3, date2));

        assertFalse(DateUtils.equalsMonth(date3, date1));
        assertFalse(DateUtils.equalsMonth(date3, date2));
    }

    @Test
    public void testAfterMonth(){
        assertFalse(DateUtils.afterMonth(date1, date2));
        assertFalse(DateUtils.afterMonth(date2, date3));
        assertFalse(DateUtils.afterMonth(date1, date3));
        assertFalse(DateUtils.afterMonth(date2, date3));

        assertFalse(DateUtils.afterMonth(date3, date4));
        assertFalse(DateUtils.afterMonth(date4, date5));
        assertFalse(DateUtils.afterMonth(date5, date6));
        assertFalse(DateUtils.afterMonth(date6, date7));
        assertFalse(DateUtils.afterMonth(date7, date8));

        assertTrue(DateUtils.afterMonth(date2, date1));
        assertTrue(DateUtils.afterMonth(date3, date2));

        assertTrue(DateUtils.afterMonth(date3, date1));
        assertTrue(DateUtils.afterMonth(date3, date2));
    }

    @Test
    public void testBeforeDay(){
        assertTrue(DateUtils.beforeDay(date1, date2));
        assertTrue(DateUtils.beforeDay(date2, date3));
        assertTrue(DateUtils.beforeDay(date3, date4));
        assertTrue(DateUtils.beforeDay(date1, date4));
        assertTrue(DateUtils.beforeDay(date2, date4));
        assertTrue(DateUtils.beforeDay(date3, date4));

        assertFalse(DateUtils.beforeDay(date4, date5));
        assertFalse(DateUtils.beforeDay(date5, date6));
        assertFalse(DateUtils.beforeDay(date6, date7));
        assertFalse(DateUtils.beforeDay(date7, date8));

        assertFalse(DateUtils.beforeDay(date2, date1));
        assertFalse(DateUtils.beforeDay(date3, date2));
        assertFalse(DateUtils.beforeDay(date4, date3));

        assertFalse(DateUtils.beforeDay(date4, date1));
        assertFalse(DateUtils.beforeDay(date4, date2));
        assertFalse(DateUtils.beforeDay(date4, date3));
    }

    @Test
    public void testEqualsDay(){
        assertFalse(DateUtils.equalsDay(date1, date2));
        assertFalse(DateUtils.equalsDay(date2, date3));
        assertFalse(DateUtils.equalsDay(date3, date4));
        assertFalse(DateUtils.equalsDay(date1, date4));
        assertFalse(DateUtils.equalsDay(date2, date4));
        assertFalse(DateUtils.equalsDay(date3, date4));

        assertTrue(DateUtils.equalsDay(date4, date5));
        assertTrue(DateUtils.equalsDay(date5, date6));
        assertTrue(DateUtils.equalsDay(date6, date7));
        assertTrue(DateUtils.equalsDay(date7, date8));

        assertFalse(DateUtils.equalsDay(date2, date1));
        assertFalse(DateUtils.equalsDay(date3, date2));
        assertFalse(DateUtils.equalsDay(date4, date3));

        assertFalse(DateUtils.equalsDay(date4, date1));
        assertFalse(DateUtils.equalsDay(date4, date2));
        assertFalse(DateUtils.equalsDay(date4, date3));
    }

    @Test
    public void testAfterDay(){
        assertFalse(DateUtils.afterDay(date1, date2));
        assertFalse(DateUtils.afterDay(date2, date3));
        assertFalse(DateUtils.afterDay(date3, date4));
        assertFalse(DateUtils.afterDay(date1, date4));
        assertFalse(DateUtils.afterDay(date2, date4));
        assertFalse(DateUtils.afterDay(date3, date4));

        assertFalse(DateUtils.afterDay(date4, date5));
        assertFalse(DateUtils.afterDay(date5, date6));
        assertFalse(DateUtils.afterDay(date6, date7));
        assertFalse(DateUtils.afterDay(date7, date8));

        assertTrue(DateUtils.afterDay(date2, date1));
        assertTrue(DateUtils.afterDay(date3, date2));
        assertTrue(DateUtils.afterDay(date4, date3));

        assertTrue(DateUtils.afterDay(date4, date1));
        assertTrue(DateUtils.afterDay(date4, date2));
        assertTrue(DateUtils.afterDay(date4, date3));
    }

    @Test
    public void testBeforeHour(){
        assertTrue(DateUtils.beforeHour(date1, date2));
        assertTrue(DateUtils.beforeHour(date2, date3));
        assertTrue(DateUtils.beforeHour(date3, date4));
        assertTrue(DateUtils.beforeHour(date4, date5));
        assertTrue(DateUtils.beforeHour(date1, date5));
        assertTrue(DateUtils.beforeHour(date2, date5));
        assertTrue(DateUtils.beforeHour(date3, date5));
        assertTrue(DateUtils.beforeHour(date4, date5));

        assertFalse(DateUtils.beforeHour(date5, date6));
        assertFalse(DateUtils.beforeHour(date6, date7));
        assertFalse(DateUtils.beforeHour(date7, date8));

        assertFalse(DateUtils.beforeHour(date2, date1));
        assertFalse(DateUtils.beforeHour(date3, date2));
        assertFalse(DateUtils.beforeHour(date4, date3));
        assertFalse(DateUtils.beforeHour(date5, date4));

        assertFalse(DateUtils.beforeHour(date5, date1));
        assertFalse(DateUtils.beforeHour(date5, date2));
        assertFalse(DateUtils.beforeHour(date5, date3));
        assertFalse(DateUtils.beforeHour(date5, date4));
    }

    @Test
    public void testEqualsHour(){
        assertFalse(DateUtils.equalsHour(date1, date2));
        assertFalse(DateUtils.equalsHour(date2, date3));
        assertFalse(DateUtils.equalsHour(date3, date4));
        assertFalse(DateUtils.equalsHour(date4, date5));
        assertFalse(DateUtils.equalsHour(date1, date5));
        assertFalse(DateUtils.equalsHour(date2, date5));
        assertFalse(DateUtils.equalsHour(date3, date5));
        assertFalse(DateUtils.equalsHour(date4, date5));

        assertTrue(DateUtils.equalsHour(date5, date6));
        assertTrue(DateUtils.equalsHour(date6, date7));
        assertTrue(DateUtils.equalsHour(date7, date8));

        assertFalse(DateUtils.equalsHour(date2, date1));
        assertFalse(DateUtils.equalsHour(date3, date2));
        assertFalse(DateUtils.equalsHour(date4, date3));
        assertFalse(DateUtils.equalsHour(date5, date4));

        assertFalse(DateUtils.equalsHour(date5, date1));
        assertFalse(DateUtils.equalsHour(date5, date2));
        assertFalse(DateUtils.equalsHour(date5, date3));
        assertFalse(DateUtils.equalsHour(date5, date4));
    }

    @Test
    public void testAfterHour(){
        assertFalse(DateUtils.afterHour(date1, date2));
        assertFalse(DateUtils.afterHour(date2, date3));
        assertFalse(DateUtils.afterHour(date3, date4));
        assertFalse(DateUtils.afterHour(date4, date5));
        assertFalse(DateUtils.afterHour(date1, date5));
        assertFalse(DateUtils.afterHour(date2, date5));
        assertFalse(DateUtils.afterHour(date3, date5));
        assertFalse(DateUtils.afterHour(date4, date5));

        assertTrue(DateUtils.afterHour(date2, date1));
        assertTrue(DateUtils.afterHour(date3, date2));
        assertTrue(DateUtils.afterHour(date4, date3));
        assertTrue(DateUtils.afterHour(date5, date4));

        assertTrue(DateUtils.afterHour(date5, date1));
        assertTrue(DateUtils.afterHour(date5, date2));
        assertTrue(DateUtils.afterHour(date5, date3));
        assertTrue(DateUtils.afterHour(date5, date4));
    }
    @Test
    public void testBeforeMinute(){
        assertTrue(DateUtils.beforeMinute(date1, date2));
        assertTrue(DateUtils.beforeMinute(date2, date3));
        assertTrue(DateUtils.beforeMinute(date3, date4));
        assertTrue(DateUtils.beforeMinute(date4, date5));
        assertTrue(DateUtils.beforeMinute(date5, date6));
        assertTrue(DateUtils.beforeMinute(date1, date6));
        assertTrue(DateUtils.beforeMinute(date2, date6));
        assertTrue(DateUtils.beforeMinute(date3, date6));
        assertTrue(DateUtils.beforeMinute(date4, date6));
        assertTrue(DateUtils.beforeMinute(date5, date6));

        assertFalse(DateUtils.beforeMinute(date6, date7));
        assertFalse(DateUtils.beforeMinute(date7, date8));

        assertFalse(DateUtils.beforeMinute(date2, date1));
        assertFalse(DateUtils.beforeMinute(date3, date2));
        assertFalse(DateUtils.beforeMinute(date4, date3));
        assertFalse(DateUtils.beforeMinute(date5, date4));
        assertFalse(DateUtils.beforeMinute(date6, date5));

        assertFalse(DateUtils.beforeMinute(date6, date1));
        assertFalse(DateUtils.beforeMinute(date6, date2));
        assertFalse(DateUtils.beforeMinute(date6, date3));
        assertFalse(DateUtils.beforeMinute(date6, date4));
        assertFalse(DateUtils.beforeMinute(date6, date5));
    }

    @Test
    public void testEqualsMinute(){
        assertFalse(DateUtils.equalsMinute(date1, date2));
        assertFalse(DateUtils.equalsMinute(date2, date3));
        assertFalse(DateUtils.equalsMinute(date3, date4));
        assertFalse(DateUtils.equalsMinute(date4, date5));
        assertFalse(DateUtils.equalsMinute(date5, date6));
        assertFalse(DateUtils.equalsMinute(date1, date6));
        assertFalse(DateUtils.equalsMinute(date2, date6));
        assertFalse(DateUtils.equalsMinute(date3, date6));
        assertFalse(DateUtils.equalsMinute(date4, date6));
        assertFalse(DateUtils.equalsMinute(date5, date6));

        assertTrue(DateUtils.equalsMinute(date6, date7));
        assertTrue(DateUtils.equalsMinute(date7, date8));

        assertFalse(DateUtils.equalsMinute(date2, date1));
        assertFalse(DateUtils.equalsMinute(date3, date2));
        assertFalse(DateUtils.equalsMinute(date4, date3));
        assertFalse(DateUtils.equalsMinute(date5, date4));
        assertFalse(DateUtils.equalsMinute(date6, date5));

        assertFalse(DateUtils.equalsMinute(date6, date1));
        assertFalse(DateUtils.equalsMinute(date6, date2));
        assertFalse(DateUtils.equalsMinute(date6, date3));
        assertFalse(DateUtils.equalsMinute(date6, date4));
        assertFalse(DateUtils.equalsMinute(date6, date5));
    }

    @Test
    public void testAfterMinute(){
        assertFalse(DateUtils.afterMinute(date1, date2));
        assertFalse(DateUtils.afterMinute(date2, date3));
        assertFalse(DateUtils.afterMinute(date3, date4));
        assertFalse(DateUtils.afterMinute(date4, date5));
        assertFalse(DateUtils.afterMinute(date5, date6));
        assertFalse(DateUtils.afterMinute(date1, date6));
        assertFalse(DateUtils.afterMinute(date2, date6));
        assertFalse(DateUtils.afterMinute(date3, date6));
        assertFalse(DateUtils.afterMinute(date4, date6));
        assertFalse(DateUtils.afterMinute(date5, date6));
        assertFalse(DateUtils.afterMinute(date6, date6));

        assertTrue(DateUtils.afterMinute(date2, date1));
        assertTrue(DateUtils.afterMinute(date3, date2));
        assertTrue(DateUtils.afterMinute(date4, date3));
        assertTrue(DateUtils.afterMinute(date5, date4));
        assertTrue(DateUtils.afterMinute(date6, date5));

        assertTrue(DateUtils.afterMinute(date6, date1));
        assertTrue(DateUtils.afterMinute(date6, date2));
        assertTrue(DateUtils.afterMinute(date6, date3));
        assertTrue(DateUtils.afterMinute(date6, date4));
        assertTrue(DateUtils.afterMinute(date6, date5));
    }
    @Test
    public void testBeforeSecond(){
        assertTrue(DateUtils.beforeSecond(date1, date2));
        assertTrue(DateUtils.beforeSecond(date2, date3));
        assertTrue(DateUtils.beforeSecond(date3, date4));
        assertTrue(DateUtils.beforeSecond(date4, date5));
        assertTrue(DateUtils.beforeSecond(date5, date6));
        assertTrue(DateUtils.beforeSecond(date6, date7));
        assertTrue(DateUtils.beforeSecond(date1, date7));
        assertTrue(DateUtils.beforeSecond(date2, date7));
        assertTrue(DateUtils.beforeSecond(date3, date7));
        assertTrue(DateUtils.beforeSecond(date4, date7));
        assertTrue(DateUtils.beforeSecond(date5, date7));
        assertTrue(DateUtils.beforeSecond(date6, date7));

        assertFalse(DateUtils.beforeSecond(date7, date8));

        assertFalse(DateUtils.beforeSecond(date2, date1));
        assertFalse(DateUtils.beforeSecond(date3, date2));
        assertFalse(DateUtils.beforeSecond(date4, date3));
        assertFalse(DateUtils.beforeSecond(date5, date4));
        assertFalse(DateUtils.beforeSecond(date6, date5));
        assertFalse(DateUtils.beforeSecond(date7, date6));

        assertFalse(DateUtils.beforeSecond(date7, date1));
        assertFalse(DateUtils.beforeSecond(date7, date2));
        assertFalse(DateUtils.beforeSecond(date7, date3));
        assertFalse(DateUtils.beforeSecond(date7, date4));
        assertFalse(DateUtils.beforeSecond(date7, date5));
        assertFalse(DateUtils.beforeSecond(date7, date6));
    }
    @Test
    public void testEqualsSecond(){
        assertFalse(DateUtils.equalsSecond(date1, date2));
        assertFalse(DateUtils.equalsSecond(date2, date3));
        assertFalse(DateUtils.equalsSecond(date3, date4));
        assertFalse(DateUtils.equalsSecond(date4, date5));
        assertFalse(DateUtils.equalsSecond(date5, date6));
        assertFalse(DateUtils.equalsSecond(date6, date7));
        assertFalse(DateUtils.equalsSecond(date1, date7));
        assertFalse(DateUtils.equalsSecond(date2, date7));
        assertFalse(DateUtils.equalsSecond(date3, date7));
        assertFalse(DateUtils.equalsSecond(date4, date7));
        assertFalse(DateUtils.equalsSecond(date5, date7));
        assertFalse(DateUtils.equalsSecond(date6, date7));

        assertTrue(DateUtils.equalsSecond(date7, date8));

        assertFalse(DateUtils.equalsSecond(date2, date1));
        assertFalse(DateUtils.equalsSecond(date3, date2));
        assertFalse(DateUtils.equalsSecond(date4, date3));
        assertFalse(DateUtils.equalsSecond(date5, date4));
        assertFalse(DateUtils.equalsSecond(date6, date5));
        assertFalse(DateUtils.equalsSecond(date7, date6));

        assertFalse(DateUtils.equalsSecond(date7, date1));
        assertFalse(DateUtils.equalsSecond(date7, date2));
        assertFalse(DateUtils.equalsSecond(date7, date3));
        assertFalse(DateUtils.equalsSecond(date7, date4));
        assertFalse(DateUtils.equalsSecond(date7, date5));
        assertFalse(DateUtils.equalsSecond(date7, date6));
    }
    @Test
    public void testAfterSecond(){
        assertFalse(DateUtils.afterSecond(date1, date2));
        assertFalse(DateUtils.afterSecond(date2, date3));
        assertFalse(DateUtils.afterSecond(date3, date4));
        assertFalse(DateUtils.afterSecond(date4, date5));
        assertFalse(DateUtils.afterSecond(date5, date6));
        assertFalse(DateUtils.afterSecond(date6, date7));
        assertFalse(DateUtils.afterSecond(date1, date7));
        assertFalse(DateUtils.afterSecond(date2, date7));
        assertFalse(DateUtils.afterSecond(date3, date7));
        assertFalse(DateUtils.afterSecond(date4, date7));
        assertFalse(DateUtils.afterSecond(date5, date7));
        assertFalse(DateUtils.afterSecond(date6, date7));

        assertFalse(DateUtils.afterSecond(date7, date8));

        assertTrue(DateUtils.afterSecond(date2, date1));
        assertTrue(DateUtils.afterSecond(date3, date2));
        assertTrue(DateUtils.afterSecond(date4, date3));
        assertTrue(DateUtils.afterSecond(date5, date4));
        assertTrue(DateUtils.afterSecond(date6, date5));
        assertTrue(DateUtils.afterSecond(date7, date6));

        assertTrue(DateUtils.afterSecond(date7, date1));
        assertTrue(DateUtils.afterSecond(date7, date2));
        assertTrue(DateUtils.afterSecond(date7, date3));
        assertTrue(DateUtils.afterSecond(date7, date4));
        assertTrue(DateUtils.afterSecond(date7, date5));
        assertTrue(DateUtils.afterSecond(date7, date6));
    }

    @Test
    public void testMaxDayOfMonth(){
        int day = DateUtils.getMaxDayOfMonth(2010, 1);
        assertTrue(day == 31);
        day = DateUtils.getMaxDayOfMonth(2010, 2);
        assertTrue(day == 28);
        day = DateUtils.getMaxDayOfMonth(2008, 2);
        assertTrue(day == 29);
        day = DateUtils.getMaxDayOfMonth(2010, 3);
        assertTrue(day == 31);
        day = DateUtils.getMaxDayOfMonth(2010, 4);
        assertTrue(day == 30);
        day = DateUtils.getMaxDayOfMonth(2010, 5);
        assertTrue(day == 31);
        day = DateUtils.getMaxDayOfMonth(2010, 6);
        assertTrue(day == 30);
        day = DateUtils.getMaxDayOfMonth(2010, 7);
        assertTrue(day == 31);
        day = DateUtils.getMaxDayOfMonth(2010, 8);
        assertTrue(day == 31);
        day = DateUtils.getMaxDayOfMonth(2010, 9);
        assertTrue(day == 30);
        day = DateUtils.getMaxDayOfMonth(2010, 10);
        assertTrue(day == 31);
        day = DateUtils.getMaxDayOfMonth(2010, 11);
        assertTrue(day == 30);
        day = DateUtils.getMaxDayOfMonth(2010, 12);
        assertTrue(day == 31);

        try {
            day = DateUtils.getMaxDayOfMonth(2010, 0);
        }catch (IllegalArgumentException e) {
            return;
        }
        assertTrue(false);
    }

    @Test
    public void testGetDayNumber4(){
    	Date startDate = DateUtils.getDate(2010, 1, 1);
    	Date endDate = DateUtils.getDate(2011, 1, 1);
    	assertEquals(366, DateUtils.getDayNumber(startDate, endDate));

    	startDate = DateUtils.getDate(2010, 1, 1);
    	endDate = DateUtils.getDate(2011, 3, 6);
    	assertEquals(430, DateUtils.getDayNumber(startDate, endDate));

    	startDate = DateUtils.getDate(2011, 1, 1);
    	endDate = DateUtils.getDate(2011, 12, 31);
    	assertEquals(365, DateUtils.getDayNumber(startDate, endDate));
    }

    @Test(expectedExceptions={IllegalArgumentException.class})
    public void testGetDayNumber(){
    	Date startDate = DateUtils.getDate(2011, 1, 1);
    	Date endDate = DateUtils.getDate(2011, 1, 1);
    	assertEquals(1, DateUtils.getDayNumber(startDate, endDate));
    	startDate = DateUtils.getDate(2011, 1, 1);
    	endDate = DateUtils.getDate(2011, 1, 2);
    	assertEquals(2, DateUtils.getDayNumber(startDate, endDate));
    	startDate = DateUtils.getDate(2011, 1, 1);
    	endDate = DateUtils.getDate(2011, 2, 1);
    	assertEquals(32, DateUtils.getDayNumber(startDate, endDate));
    	startDate = DateUtils.getDate(2011, 1, 1);
    	endDate = DateUtils.getDate(2011, 3, 10);
    	assertEquals(69, DateUtils.getDayNumber(startDate, endDate));
    	// 抛出异常
    	assertEquals(69, DateUtils.getDayNumber(endDate, startDate));
    }
    @Test(expectedExceptions={IllegalArgumentException.class})
    public void testGetDayNumber2(){
    	Date endDate = DateUtils.getDate(2011, 1, 1);
    	assertEquals(1, DateUtils.getDayNumber(null, endDate));
    }
    @Test(expectedExceptions={IllegalArgumentException.class})
    public void testGetDayNumber3(){
    	Date startDate = DateUtils.getDate(2011, 1, 1);
    	assertEquals(1, DateUtils.getDayNumber(startDate, null));
    }

    @Test(expectedExceptions={IllegalArgumentException.class})
    public void testGetWorkDayNumber(){
    	Date startDate = DateUtils.getDate(2011, 1, 1);
    	Date endDate = DateUtils.getDate(2011, 1, 1);
    	assertEquals(0, DateUtils.getWorkDayNumber(startDate, endDate));
    	startDate = DateUtils.getDate(2011, 1, 1);
    	endDate = DateUtils.getDate(2011, 1, 2);
    	assertEquals(0, DateUtils.getWorkDayNumber(startDate, endDate));
    	startDate = DateUtils.getDate(2011, 1, 1);
    	endDate = DateUtils.getDate(2011, 1, 3);
    	assertEquals(1, DateUtils.getWorkDayNumber(startDate, endDate));
    	startDate = DateUtils.getDate(2011, 1, 1);
    	endDate = DateUtils.getDate(2011, 2, 1);
    	assertEquals(22, DateUtils.getWorkDayNumber(startDate, endDate));
    	startDate = DateUtils.getDate(2011, 1, 1);
    	endDate = DateUtils.getDate(2011, 3, 10);
    	assertEquals(49, DateUtils.getWorkDayNumber(startDate, endDate));
    	// 抛出异常
    	assertEquals(69, DateUtils.getDayNumber(endDate, startDate));
    }
    @Test(expectedExceptions={IllegalArgumentException.class})
    public void testGetWorkDayNumber2(){
    	Date endDate = DateUtils.getDate(2011, 1, 1);
    	assertEquals(1, DateUtils.getWorkDayNumber(null, endDate));
    }
    @Test(expectedExceptions={IllegalArgumentException.class})
    public void testGetWorkDayNumber3(){
    	Date startDate = DateUtils.getDate(2011, 1, 1);
    	assertEquals(1, DateUtils.getWorkDayNumber(startDate, null));
    }
    @Test
    public void testGetAge() {
        Date birthday = DateUtils.getDate(2000, 5, 5);
        
        Date compareDay1 = DateUtils.getDate(2014, 5, 6);
        Date compareDay2 = DateUtils.getDate(2014, 5, 5);
        Date compareDay3 = DateUtils.getDate(2014, 5, 4);
        
        Date compareDay4 = DateUtils.getDate(2014, 6, 6);
        Date compareDay5 = DateUtils.getDate(2014, 6, 5);
        Date compareDay6 = DateUtils.getDate(2014, 6, 4);
        
        Date compareDay7 = DateUtils.getDate(2014, 4, 6);
        Date compareDay8 = DateUtils.getDate(2014, 4, 5);
        Date compareDay9 = DateUtils.getDate(2014, 4, 4);
     
        int age1 = 14;
        int age2 = 13;
        
        assertEquals(age1, DateUtils.getAge(birthday, compareDay1));
        assertEquals(age1, DateUtils.getAge(birthday, compareDay2));
        assertEquals(age2, DateUtils.getAge(birthday, compareDay3));
        
        assertEquals(age1, DateUtils.getAge(birthday, compareDay4));
        assertEquals(age1, DateUtils.getAge(birthday, compareDay5));
        assertEquals(age1, DateUtils.getAge(birthday, compareDay6));
        
        assertEquals(age2, DateUtils.getAge(birthday, compareDay7));
        assertEquals(age2, DateUtils.getAge(birthday, compareDay8));
        assertEquals(age2, DateUtils.getAge(birthday, compareDay9));
        
    }
}
