
/*
 * All rights Reserved, Designed By zhongj
 * @Title: SqlDeleteFromBasicBuilderTest.java
 * @Package cn.featherfly.common.db.builder.dml.builder
 * @Description: SqlDeleteFromBasicBuilderTest
 * @author: zhongj
 * @date: 2022-11-28 16:50:28
 * @Copyright: 2022 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.db.builder.dml.builder;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import cn.featherfly.common.db.builder.dml.basic.SqlDeleteFromBasicBuilder;
import cn.featherfly.common.db.dialect.Dialects;

/**
 * SqlDeleteFromBasicBuilderTest.
 *
 * @author zhongj
 */
public class SqlDeleteFromBasicBuilderTest {

    @Test
    void test() {
        SqlDeleteFromBasicBuilder builder = new SqlDeleteFromBasicBuilder(Dialects.MYSQL, "user");
        String sql = builder.build();
        System.out.println(sql);
        assertEquals(sql, "DELETE FROM `user`");

        builder.setTableAlias("u");
        sql = builder.build();
        System.out.println(sql);
        assertEquals(sql, "DELETE `u` FROM `user` `u`");
        sql = builder.build();
    }
}
