
/*
 * All rights Reserved, Designed By zhongj
 * @Title: DatabaseOperatingPerformance.java
 * @Description: DatabaseOperatingPerformance
 * @author: zhongj
 * @date: 2023-08-22 15:52:22
 * @Copyright: 2023 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.db.pt;

/**
 * DatabaseOperatingPerformance.
 *
 * @author zhongj
 */
public interface DatabaseOperatingPerformance {

    void testInsert();

    void testInsertBatch();
}
