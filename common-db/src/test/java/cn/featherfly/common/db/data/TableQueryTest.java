package cn.featherfly.common.db.data;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import cn.featherfly.common.db.builder.dml.SqlConditionGroup;
import cn.featherfly.common.db.builder.dml.SqlSortBuilder;
import cn.featherfly.common.db.data.query.TableQuery;
import cn.featherfly.common.db.dialect.Dialects;
import cn.featherfly.common.repository.IgnoreStrategy;
import cn.featherfly.common.repository.builder.dml.ConditionBuilder;

/**
 * TableQueryTest.
 *
 * @author zhongj
 */
public class TableQueryTest {

    @Test
    public void test() {
        ConditionBuilder builder = new SqlConditionGroup(Dialects.mysql(), IgnoreStrategy.EMPTY,
                new SqlSortBuilder(Dialects.mysql()));
        builder.eq("name", "yufei").and().gt("age", 18);
        TableQuery q = new TableQuery(Dialects.mysql(), "user", builder);
        System.out.println(q.getSql());
        assertEquals(q.getSql(), "select * from `user` WHERE `name` = ? AND `age` > ?");
    }
}
