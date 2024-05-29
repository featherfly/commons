
/*
 * All rights Reserved, Designed By zhongj
 * @Title: JdbcPerformanceTest.java
 * @Description: JdbcPerformanceTest
 * @author: zhongj
 * @date: 2023-08-22 15:49:22
 * @Copyright: 2023 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.db.pt;

import org.testng.annotations.Test;

import cn.featherfly.common.db.JdbcTestBase;
import cn.featherfly.common.db.JdbcUtils;
import cn.featherfly.common.db.dialect.Dialects;
import cn.featherfly.common.db.wrapper.ConnectionWrapper;
import cn.featherfly.common.db.wrapper.PreparedStatementWrapper;
import cn.featherfly.common.lang.ArrayUtils;
import cn.featherfly.common.lang.Strings;
import cn.featherfly.common.lang.Timer;

/**
 * JdbcPerformanceTest.
 *
 * @author zhongj
 */
public class JdbcPerformanceTest extends JdbcTestBase {

    private int batchSize = 10000;

    private int batchTimes = 100;

    @Test
    public void testInsertBatch() {
        String insertSql = "insert into `user_info` (`id`, `user_id`, `name`, `descp`, `province`, `city`, `district`) values(?,?,?,?,?,?,?)";
        Timer timer = Timer.start();
        ConnectionWrapper conn = JdbcUtils.getConnectionWrapper(dataSource);
        int index = 0;
        for (int i = 0; i < batchTimes; i++) {
            PreparedStatementWrapper prep = conn.prepareStatement(insertSql);
            for (int j = 0; j < batchSize; j++) {
                prep.setObject(1, null);
                prep.setInt(2, 1);
                prep.setString(3, "yufei_" + index);
                prep.setString(4, "yufei_descp_" + index);
                prep.setString(5, "省_" + index);
                prep.setString(6, "市_" + index);
                prep.setString(7, "区_" + index);
                prep.addBatch();
                index++;
            }
            int[] res = prep.executeBatch();
            System.out.println("res: " + res.length);
            System.out.println(ArrayUtils.toString(res));
            prep.close();
        }
        conn.close();
        System.out.println(Strings.format("testInsertBatch use {0} time with insertBatch[{1}] times {2}", timer.stop(),
            batchSize, batchTimes));
    }

    @Test
    public void testInsertBatch2() {
        String insertSql = Dialects.mysql().dml().insertBatch("user_info",
            new String[] { "id", "user_id", "name", "descp", "province", "city", "district" }, batchSize, true);
        Timer timer = Timer.start();
        ConnectionWrapper conn = JdbcUtils.getConnectionWrapper(dataSource);
        //        int total = 0;
        for (int i = 0; i < batchTimes; i++) {
            int paramIndex = 0;
            PreparedStatementWrapper prep = conn.prepareStatement(insertSql);
            for (int j = 0; j < batchSize; j++) {
                prep.setObject(++paramIndex, null);
                prep.setInt(++paramIndex, 1);
                prep.setString(++paramIndex, "yufei2_" + paramIndex);
                prep.setString(++paramIndex, "yufei2_descp_" + paramIndex);
                prep.setString(++paramIndex, "省2_" + paramIndex);
                prep.setString(++paramIndex, "市2_" + paramIndex);
                prep.setString(++paramIndex, "区2_" + paramIndex);
                //                paramIndex++;
            }
            int res = prep.executeUpdate();
            System.out.println("res: " + res);
            prep.close();
        }
        conn.close();
        System.out.println(Strings.format("testInsertBatch2 use {0} time with insertBatch[{1}] times {2}", timer.stop(),
            batchSize, batchTimes));
    }
}
