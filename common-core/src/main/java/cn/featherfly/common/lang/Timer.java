
/*
 * All rights Reserved, Designed By zhongj
 * @Title: Timer.java
 * @Description: Timer
 * @author: zhongj
 * @Copyright: 2023 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.lang;

/**
 * Timer.
 *
 * @author zhongj
 */
public class Timer {

    private long startTime = -1;

    private Timer(long startTime) {
        this.startTime = startTime;
    }

    public static Timer start() {
        return start(System.currentTimeMillis());
    }

    public static Timer start(long startTime) {
        return new Timer(startTime);
    }

    public long stop() {
        return stop(System.currentTimeMillis());
    }

    public long stop(long stopTime) {
        return stopTime - startTime;
    }
}
