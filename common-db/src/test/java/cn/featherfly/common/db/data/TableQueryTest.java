
/*
 * All rights Reserved, Designed By zhongj
 * @Title: TableQueryTest.java
 * @Package cn.featherfly.common.db.data
 * @Description: TODO (用一句话描述该文件做什么)
 * @author: zhongj
 * @date: 2021-12-19 19:13:19
 * @Copyright: 2021 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.db.data;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import cn.featherfly.common.db.builder.dml.SqlConditionGroup;
import cn.featherfly.common.db.builder.dml.SqlSortBuilder;
import cn.featherfly.common.db.data.query.TableQuery;
import cn.featherfly.common.db.dialect.Dialects;
import cn.featherfly.common.repository.IgnorePolicy;
import cn.featherfly.common.repository.builder.dml.ConditionBuilder;

/**
 * TableQueryTest.
 *
 * @author zhongj
 */
public class TableQueryTest {

    @Test
    public void test() {
        ConditionBuilder builder = new SqlConditionGroup(Dialects.MYSQL, IgnorePolicy.EMPTY,
                new SqlSortBuilder(Dialects.MYSQL));
        builder.eq("name", "yufei").and().gt("age", 18);
        TableQuery q = new TableQuery(Dialects.MYSQL, "user", builder);
        System.out.println(q.getSql());
        assertEquals(q.getSql(), "select * from `user` WHERE `name` = ? AND `age` > ?");
    }
}
