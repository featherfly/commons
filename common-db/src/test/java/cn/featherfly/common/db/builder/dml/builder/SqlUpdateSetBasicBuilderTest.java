
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-04-18 17:25:18
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.db.builder.dml.builder;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import cn.featherfly.common.db.builder.dml.basic.SqlJoinOnBasicBuilder;
import cn.featherfly.common.db.builder.dml.basic.SqlUpdateSetBasicBuilder;
import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.db.dialect.Dialects;
import cn.featherfly.common.db.dialect.Join;
import cn.featherfly.common.repository.IgnoreStrategy;

/**
 * SqlUpdateSetBasicBuilderTest.
 *
 * @author zhongj
 */
public class SqlUpdateSetBasicBuilderTest {
    @Test
    void test() {
        SqlUpdateSetBasicBuilder builder = new SqlUpdateSetBasicBuilder(Dialects.MYSQL, "user", IgnoreStrategy.NONE);
        builder.setValue("name", "yufei");
        String sql = builder.build();

        System.out.println(sql);
        assertEquals(sql, "UPDATE `user` SET `name` = ?");

        builder.setAlias("_user0");
        sql = builder.build();
        System.out.println(sql);
        assertEquals(sql, "UPDATE `user` `_user0` SET _user0.`name` = ?");
        //UPDATE `user` `_user0` SET _user0.`name` = ?
        //UPDATE `user` `_user0` SET `name` = ?
        sql = builder.build();
    }

    @Test
    void join() {
        Dialect dialect = Dialects.MYSQL;
        SqlUpdateSetBasicBuilder builder = new SqlUpdateSetBasicBuilder(dialect, "user", "_user0", IgnoreStrategy.NONE);
        builder.join(
            new SqlJoinOnBasicBuilder(dialect, Join.INNER_JOIN, "user_info", "_user_info0", "user_id", "_user0", "id"))
            .setValue("name", "yufei");
        String sql = builder.build();
        System.out.println(sql);
        assertEquals(sql,
            "UPDATE `user` `_user0` JOIN `user_info` `_user_info0` ON _user_info0.`user_id` = _user0.`id` SET _user0.`name` = ?");
    }
}
