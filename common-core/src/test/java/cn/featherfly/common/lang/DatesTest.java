
/*
 * Thgk-Commons
 * copyright featherfly 2010-2020, all rights reserved.
 * created on May 21, 2010 3:46:26 PM
 */
package cn.featherfly.common.lang;

import static org.testng.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import cn.featherfly.common.exception.UnsupportedException;

public class DatesTest {

    final int year = 2000;
    final int month = 1;
    final int day = 2;
    final int hour = 3;
    final int minute = 4;
    final int second = 5;

    LocalDateTime localDateTime = LocalDateTime.of(year, month, day, hour, minute, second);
    LocalDate localDate = localDateTime.toLocalDate();
    LocalTime localTime = localDateTime.toLocalTime();
    Date date = Dates.toDate(localDateTime);

    private void assertDate(LocalDateTime localDateTime) {
        assertEquals(localDateTime.getYear(), year);
        assertEquals(localDateTime.getMonth().getValue(), month);
        assertEquals(localDateTime.getDayOfMonth(), day);
        assertEquals(localDateTime.getHour(), hour);
        assertEquals(localDateTime.getMinute(), minute);
        assertEquals(localDateTime.getSecond(), second);

    }

    private void assertDate(LocalDate localDate) {
        assertEquals(localDate.getYear(), year);
        assertEquals(localDate.getMonth().getValue(), month);
        assertEquals(localDate.getDayOfMonth(), day);
    }

    private void assertDate(LocalTime localTime) {
        assertEquals(localTime.getHour(), hour);
        assertEquals(localTime.getMinute(), minute);
        assertEquals(localTime.getSecond(), second);
    }

    @Test
    public void formatDate() {
        assertEquals(Dates.formatDate(date), "2000-01-02");
        assertEquals(Dates.formatDateTime(date), "2000-01-02 03:04:05");
        assertEquals(Dates.formatTime(date), "03:04:05");

        assertEquals(Dates.format(date, "dd/MM yyyy"), "02/01 2000");
        assertEquals(Dates.format(date, "dd/MM/yyyy HH:mm:ss"), "02/01/2000 03:04:05");
    }

    @Test
    public void parseDate() {
        Date parsedDate = Dates.parseDate("2000-01-02");
        assertDate(Dates.toLocalDate(parsedDate));

        parsedDate = Dates.parseTime("03:04:05");
        assertDate(Dates.toLocalTime(parsedDate));

        parsedDate = Dates.parseDateTime("2000-01-02 03:04:05");
        assertDate(Dates.toLocalDateTime(parsedDate));

        parsedDate = Dates.parse("02/01/2000 03:04:05", "dd/MM/yyyy HH:mm:ss");
        assertDate(Dates.toLocalDateTime(parsedDate));

        parsedDate = Dates.parse("02/01 2000", "dd/MM yyyy");
        assertDate(Dates.toLocalDate(parsedDate));
    }

    @Test
    public void parseLocal() {
        assertDate(Dates.parseLocalDate("2000-01-02"));

        assertDate(Dates.parseLocalTime("03:04:05"));

        assertDate(Dates.parseLocalDateTime("2000-01-02 03:04:05"));
    }

    @Test
    public void formatLocalDateTime() {
        assertEquals(Dates.formatDate(localDateTime), "2000-01-02");
        assertEquals(Dates.formatDateTime(localDateTime), "2000-01-02 03:04:05");
        assertEquals(Dates.formatTime(localDateTime), "03:04:05");

        assertEquals(Dates.format(localDateTime, "dd/MM yyyy"), "02/01 2000");
        assertEquals(Dates.format(localDateTime, "dd/MM/yyyy HH:mm:ss"), "02/01/2000 03:04:05");
    }

    @Test
    public void formatLocalDate() {
        assertEquals(Dates.formatDate(localDate), "2000-01-02");

        assertEquals(Dates.format(localDate, "dd/MM yyyy"), "02/01 2000");
    }

    @Test
    public void formatLocalTime() {
        assertEquals(Dates.formatTime(localTime), "03:04:05");

        Locale.setDefault(Locale.ENGLISH);
        assertEquals(Dates.format(localTime, "HH:mm a"), "03:04 AM");

        Locale.setDefault(Locale.CHINESE);
        assertEquals(Dates.format(localTime, "HH:mm a"), "03:04 上午");
    }

    @Test
    public void formatDuration() {
        assertEquals(Dates.format(123), "123 milliseconds");
        assertEquals(Dates.format(1234567), "20 minutes 34 seconds 567 milliseconds");
        assertEquals(Dates.format(134, TimeUnit.SECONDS), "2 minutes 14 seconds");
        assertEquals(Dates.format(135, TimeUnit.MINUTES), "2 hours 15 minutes");
        assertEquals(Dates.format(58, TimeUnit.HOURS), "2 days 10 hours");
        assertEquals(Dates.format(78223, TimeUnit.MILLISECONDS), "1 minutes 18 seconds 223 milliseconds");
        assertEquals(Dates.format(78223, TimeUnit.MILLISECONDS, TimeUnit.SECONDS), "78 seconds 223 milliseconds");
        assertEquals(Dates.format(33222111, TimeUnit.NANOSECONDS), "33 milliseconds 222 microseconds 111 nanoseconds");
        //        System.err.println(Dates.format(123));
        //        System.err.println(Dates.format(1234567));
        //        System.err.println(Dates.format(134, TimeUnit.SECONDS));
        //        System.err.println(Dates.format(135, TimeUnit.MINUTES));
        //        System.err.println(Dates.format(58, TimeUnit.HOURS));
        //        System.err.println(Dates.format(78223, TimeUnit.MILLISECONDS));
        //        System.err.println(Dates.format(78223, TimeUnit.MILLISECONDS, TimeUnit.SECONDS));
        //        System.err.println(Dates.format(33222111, TimeUnit.NANOSECONDS));
    }

    @Test(expectedExceptions = UnsupportedException.class)
    public void getTimeMicroseconds() {
        System.out.println(Dates.getTime(new Date(), TimeUnit.MICROSECONDS));
    }

    @Test(expectedExceptions = UnsupportedException.class)
    public void getTimeNanoseconds() {
        System.out.println(Dates.getTime(new Date(), TimeUnit.NANOSECONDS));
    }

    //    @Test
    //    public void getTimeMICROSECONDS2() {
    //        System.out.println(Dates.getTime(new Date(), TimeUnit.MICROSECONDS));
    //    }
}