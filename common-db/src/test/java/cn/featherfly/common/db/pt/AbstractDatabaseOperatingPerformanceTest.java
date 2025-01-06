
/*
 * All rights Reserved, Designed By zhongj
 * @Title: JdbcPerformanceTest.java
 * @Description: JdbcPerformanceTest
 * @author: zhongj
 * @date: 2023-08-22 15:49:22
 * @Copyright: 2023 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.db.pt;

import cn.featherfly.common.db.JdbcTestBase;
import cn.featherfly.common.lang.Str;
import cn.featherfly.common.lang.Timer;

/**
 * JdbcPerformanceTest.
 *
 * @author zhongj
 */
public abstract class AbstractDatabaseOperatingPerformanceTest extends JdbcTestBase
    implements DatabaseOperatingPerformance {

    @Override
    public void testInsert() {
        Timer timer = Timer.start();
        for (int i = 0; i < times; i++) {

        }
        System.out.println(Str.format("testInsert use {0} time with insert times {1}", timer.stop(), times));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void testInsertBatch() {
        Timer timer = Timer.start();
        for (int i = 0; i < batchTime; i++) {

        }
        System.out.println(Str.format("testInsertBatch use {0} time with insertBatch[{1}] times {2}", timer.stop(),
            batchSize, batchTime));
    }

    private long times = 1000;

    private long batchSize = 1000;

    private long batchTime = 1;

    /**
     * get times value
     *
     * @return times
     */
    public long getTimes() {
        return times;
    }

    /**
     * set times value
     *
     * @param times times
     */
    public void setTimes(long times) {
        this.times = times;
    }

    /**
     * get batchSize value
     *
     * @return batchSize
     */
    public long getBatchSize() {
        return batchSize;
    }

    /**
     * set batchSize value
     *
     * @param batchSize batchSize
     */
    public void setBatchSize(long batchSize) {
        this.batchSize = batchSize;
    }

    /**
     * get batchTime value
     *
     * @return batchTime
     */
    public long getBatchTime() {
        return batchTime;
    }

    /**
     * set batchTime value
     *
     * @param batchTime batchTime
     */
    public void setBatchTime(long batchTime) {
        this.batchTime = batchTime;
    }
}
