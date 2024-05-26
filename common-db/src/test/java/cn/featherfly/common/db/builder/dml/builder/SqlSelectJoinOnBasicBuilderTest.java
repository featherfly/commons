
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-27 00:39:27
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.db.builder.dml.builder;

import static org.testng.Assert.assertNotEquals;

import org.testng.annotations.Test;

import cn.featherfly.common.db.builder.dml.basic.SqlSelectBasicBuilder;
import cn.featherfly.common.db.builder.dml.basic.SqlSelectJoinOnBasicBuilder;
import cn.featherfly.common.db.dialect.Dialects;

/**
 * SqlSelectJoinOnBasicBuilderTest.
 *
 * @author zhongj
 */
public class SqlSelectJoinOnBasicBuilderTest {

    @Test
    void test1() {
        SqlSelectBasicBuilder builder = null;

        builder = new SqlSelectBasicBuilder(Dialects.mysql(), "user", "u");
        SqlSelectJoinOnBasicBuilder join = null;

        join = builder.addColumn("name").addColumn("password", "pwd").join("user_info", "ui", "ui.user_id = u.id");
        join.addColumn("age", "age2");
        String s1 = join.build();
        System.out.println(s1);

        builder = new SqlSelectBasicBuilder(Dialects.mysql(), "user", "u");
        join = builder.addColumn("name").addColumn("password", "pwd").join("user_info", "ui", "ui.user_id = u.id");
        join.fetch("age", "age2");
        String s2 = join.build();
        System.out.println(s2);

        assertNotEquals(s1, s2);
    }
}
