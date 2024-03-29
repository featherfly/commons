
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-03-29 14:32:29
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.db.builder.model;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import cn.featherfly.common.db.builder.model.UpdateColumnElement.SetType;
import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.db.dialect.Dialects;
import cn.featherfly.common.lang.ArrayUtils;
import cn.featherfly.common.repository.IgnoreStrategy;

/**
 * UpdateColumnElementTest.
 *
 * @author zhongj
 */
public class UpdateColumnElementTest {

    UpdateColumnElement u;

    String usernameColumn = "username";
    String ageColumn = "age";

    final String userTableAlias = "u";
    final String userTableAlias2 = "u2";
    final String username = "yufei";
    final Integer age = 18;

    @BeforeMethod
    void beforeMethod() {
    }

    void print(UpdateColumnElement c) {
        System.out.println(c);
        System.out.println(ArrayUtils.toString(c.getParam()));
    }

    @Test
    void mysql_sqlelement() {
        Dialect dialect = Dialects.MYSQL;
        ColumnElement ce = new ColumnElement(dialect, usernameColumn, userTableAlias2);

        u = new UpdateColumnElement(dialect, usernameColumn, ce, userTableAlias, IgnoreStrategy.NONE);
        print(u);
        assertEquals(u.toSql(), "u.`username` = u2.`username`");
        assertEquals(u.getParam(), ce);

        u = new UpdateColumnElement(dialect, usernameColumn, ce, userTableAlias, SetType.INCR, IgnoreStrategy.NONE);
        print(u);
        assertEquals(u.toSql(), "u.`username` = u.`username` + u2.`username`");
        assertEquals(u.getParam(), ce);

        // ----------------------------------------------------------------------------------------------------------------
        ce = new ColumnElement(dialect, "balance", "a2");
        u = new UpdateColumnElement(dialect, "balance", ce, "a", SetType.DECR, IgnoreStrategy.NONE);
        print(u);
        assertEquals(u.toSql(), "a.`balance` = a.`balance` - a2.`balance`");
        assertEquals(u.getParam(), ce);
    }

    @Test
    void postgresql_sqlelement() {
        Dialect dialect = Dialects.POSTGRESQL;
        ColumnElement ce = new ColumnElement(dialect, usernameColumn, userTableAlias2);

        u = new UpdateColumnElement(dialect, usernameColumn, ce, userTableAlias, IgnoreStrategy.NONE);
        print(u);
        assertEquals(u.toSql(), "u.\"username\" = u2.\"username\"");
        assertEquals(u.getParam(), ce);

        u = new UpdateColumnElement(dialect, usernameColumn, ce, userTableAlias, SetType.INCR, IgnoreStrategy.NONE);
        print(u);
        assertEquals(u.toSql(), "u.\"username\" = u.\"username\" + u2.\"username\"");
        assertEquals(u.getParam(), ce);

        // ----------------------------------------------------------------------------------------------------------------
        ce = new ColumnElement(dialect, "balance", "a2");
        u = new UpdateColumnElement(dialect, "balance", ce, "a", SetType.DECR, IgnoreStrategy.NONE);
        print(u);
        assertEquals(u.toSql(), "a.\"balance\" = a.\"balance\" - a2.\"balance\"");
        assertEquals(u.getParam(), ce);
    }

    @Test
    void sqllite_sqlelement() {
        // 同mysql
        Dialect dialect = Dialects.SQLITE;
        ColumnElement ce = new ColumnElement(dialect, usernameColumn, userTableAlias2);

        u = new UpdateColumnElement(dialect, usernameColumn, ce, userTableAlias, IgnoreStrategy.NONE);
        print(u);
        assertEquals(u.toSql(), "u.`username` = u2.`username`");
        assertEquals(u.getParam(), ce);

        u = new UpdateColumnElement(dialect, usernameColumn, ce, userTableAlias, SetType.INCR, IgnoreStrategy.NONE);
        print(u);
        assertEquals(u.toSql(), "u.`username` = u.`username` + u2.`username`");
        assertEquals(u.getParam(), ce);

        // ----------------------------------------------------------------------------------------------------------------
        ce = new ColumnElement(dialect, "balance", "a2");
        u = new UpdateColumnElement(dialect, "balance", ce, "a", SetType.DECR, IgnoreStrategy.NONE);
        print(u);
        assertEquals(u.toSql(), "a.`balance` = a.`balance` - a2.`balance`");
        assertEquals(u.getParam(), ce);
    }

    @Test
    void oracle_sqlelement() {
        Dialect dialect = Dialects.ORACLE;
        ColumnElement ce = new ColumnElement(dialect, usernameColumn, userTableAlias2);

        u = new UpdateColumnElement(dialect, usernameColumn, ce, userTableAlias, IgnoreStrategy.NONE);
        print(u);
        assertEquals(u.toSql(), "u.'username' = u2.'username'");
        assertEquals(u.getParam(), ce);

        u = new UpdateColumnElement(dialect, usernameColumn, ce, userTableAlias, SetType.INCR, IgnoreStrategy.NONE);
        print(u);
        assertEquals(u.toSql(), "u.'username' = u.'username' + u2.'username'");
        assertEquals(u.getParam(), ce);

        // ----------------------------------------------------------------------------------------------------------------
        ce = new ColumnElement(dialect, "balance", "a2");
        u = new UpdateColumnElement(dialect, "balance", ce, "a", SetType.DECR, IgnoreStrategy.NONE);
        print(u);
        assertEquals(u.toSql(), "a.'balance' = a.'balance' - a2.'balance'");
        assertEquals(u.getParam(), ce);
    }
}
