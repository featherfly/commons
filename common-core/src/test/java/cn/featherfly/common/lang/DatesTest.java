
/*
 * Thgk-Commons
 * copyright featherfly 2010-2020, all rights reserved.
 * created on May 21, 2010 3:46:26 PM
 */
package cn.featherfly.common.lang;

import static org.testng.Assert.assertEquals;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import cn.featherfly.common.exception.UnsupportedException;

public class DatesTest {

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

    @Test
    public void getTimeMICROSECONDS2() {
        System.out.println(Dates.getTime(new Date(), TimeUnit.MICROSECONDS));
    }
}