
/*
 * All rights Reserved, Designed By zhongj
 * @Title: Timer.java
 * @Description: Timer
 * @author: zhongj
 * @Copyright: 2023 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.lang;

import java.util.concurrent.TimeUnit;

/**
 * Timer.
 *
 * @author zhongj
 */
public class Timer {

    private long startTime = -1;

    private TimeUnit unit;

    private Timer(long startTime, TimeUnit unit) {
        this.startTime = startTime;
        this.unit = unit;
    }

    public static Timer start() {
        return start(TimeUnit.MILLISECONDS);
    }

    public static Timer start(TimeUnit timeUnit) {
        switch (timeUnit) {
            case MILLISECONDS:
                return start(System.currentTimeMillis(), timeUnit);
            case MICROSECONDS:
                return start(TimeUnit.NANOSECONDS.toMicros(System.nanoTime()), timeUnit);
            case NANOSECONDS:
                return start(System.nanoTime(), timeUnit);
            default:
                throw new IllegalArgumentException("only support MILLISECONDS, MICROSECONDS, NANOSECONDS");
        }
    }

    private static Timer start(long startTime, TimeUnit unit) {
        return new Timer(startTime, unit);
    }

    public long stop() {
        switch (unit) {
            case MILLISECONDS:
                return stop(System.currentTimeMillis());
            case MICROSECONDS:
                return stop(TimeUnit.NANOSECONDS.toMicros(System.nanoTime()));
            case NANOSECONDS:
                return stop(System.nanoTime());
            default:
                throw new IllegalArgumentException("only support MILLISECONDS, MICROSECONDS, NANOSECONDS");
        }
    }

    private long stop(long stopTime) {
        return stopTime - startTime;
    }
}
