
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
import cn.featherfly.common.repository.Params.ParamType;

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
        Dialect dialect = Dialects.mysql();
        ColumnElement ce = new ColumnElement(dialect, userTableAlias2, usernameColumn);

        u = new UpdateColumnElement(dialect, userTableAlias, usernameColumn, ce, IgnoreStrategy.NONE);
        print(u);
        assertEquals(u.toSql(), "u.`username` = u2.`username`");
        assertEquals(u.getParam(), ParamType.NONE);

        u = new UpdateColumnElement(dialect, userTableAlias, usernameColumn, ce, SetType.INCR, IgnoreStrategy.NONE);
        print(u);
        assertEquals(u.toSql(), "u.`username` = u.`username` + u2.`username`");
        assertEquals(u.getParam(), ParamType.NONE);

        // ----------------------------------------------------------------------------------------------------------------
        ce = new ColumnElement(dialect, "a2", "balance");
        u = new UpdateColumnElement(dialect, "a", "balance", ce, SetType.DECR, IgnoreStrategy.NONE);
        print(u);
        assertEquals(u.toSql(), "a.`balance` = a.`balance` - a2.`balance`");
        assertEquals(u.getParam(), ParamType.NONE);
    }

    @Test
    void postgresql_sqlelement() {
        Dialect dialect = Dialects.postgresql();
        ColumnElement ce = new ColumnElement(dialect, userTableAlias2, usernameColumn);

        u = new UpdateColumnElement(dialect, userTableAlias, usernameColumn, ce, IgnoreStrategy.NONE);
        print(u);
        assertEquals(u.toSql(), "u.\"username\" = u2.\"username\"");
        assertEquals(u.getParam(), ParamType.NONE);

        u = new UpdateColumnElement(dialect, userTableAlias, usernameColumn, ce, SetType.INCR, IgnoreStrategy.NONE);
        print(u);
        assertEquals(u.toSql(), "u.\"username\" = u.\"username\" + u2.\"username\"");
        assertEquals(u.getParam(), ParamType.NONE);

        // ----------------------------------------------------------------------------------------------------------------
        ce = new ColumnElement(dialect, "a2", "balance");
        u = new UpdateColumnElement(dialect, "a", "balance", ce, SetType.DECR, IgnoreStrategy.NONE);
        print(u);
        assertEquals(u.toSql(), "a.\"balance\" = a.\"balance\" - a2.\"balance\"");
        assertEquals(u.getParam(), ParamType.NONE);
    }

    @Test
    void sqllite_sqlelement() {
        // 同mysql
        Dialect dialect = Dialects.sqlite();
        ColumnElement ce = new ColumnElement(dialect, userTableAlias2, usernameColumn);

        u = new UpdateColumnElement(dialect, userTableAlias, usernameColumn, ce, IgnoreStrategy.NONE);
        print(u);
        assertEquals(u.toSql(), "u.`username` = u2.`username`");
        assertEquals(u.getParam(), ParamType.NONE);

        u = new UpdateColumnElement(dialect, userTableAlias, usernameColumn, ce, SetType.INCR, IgnoreStrategy.NONE);
        print(u);
        assertEquals(u.toSql(), "u.`username` = u.`username` + u2.`username`");
        assertEquals(u.getParam(), ParamType.NONE);

        // ----------------------------------------------------------------------------------------------------------------
        ce = new ColumnElement(dialect, "a2", "balance");
        u = new UpdateColumnElement(dialect, "a", "balance", ce, SetType.DECR, IgnoreStrategy.NONE);
        print(u);
        assertEquals(u.toSql(), "a.`balance` = a.`balance` - a2.`balance`");
        assertEquals(u.getParam(), ParamType.NONE);
    }

    @Test
    void oracle_sqlelement() {
        Dialect dialect = Dialects.oracle();
        ColumnElement ce = new ColumnElement(dialect, userTableAlias2, usernameColumn);

        u = new UpdateColumnElement(dialect, userTableAlias, usernameColumn, ce, IgnoreStrategy.NONE);
        print(u);
        assertEquals(u.toSql(), "u.'username' = u2.'username'");
        assertEquals(u.getParam(), ParamType.NONE);

        u = new UpdateColumnElement(dialect, userTableAlias, usernameColumn, ce, SetType.INCR, IgnoreStrategy.NONE);
        print(u);
        assertEquals(u.toSql(), "u.'username' = u.'username' + u2.'username'");
        assertEquals(u.getParam(), ParamType.NONE);

        // ----------------------------------------------------------------------------------------------------------------
        ce = new ColumnElement(dialect, "a2", "balance");
        u = new UpdateColumnElement(dialect, "a", "balance", ce, SetType.DECR, IgnoreStrategy.NONE);
        print(u);
        assertEquals(u.toSql(), "a.'balance' = a.'balance' - a2.'balance'");
        assertEquals(u.getParam(), ParamType.NONE);
    }
}
