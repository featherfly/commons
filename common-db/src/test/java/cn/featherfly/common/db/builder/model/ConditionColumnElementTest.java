
package cn.featherfly.common.db.builder.model;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.db.dialect.Dialects;
import cn.featherfly.common.lang.ArrayUtils;
import cn.featherfly.common.operator.ComparisonOperator;
import cn.featherfly.common.repository.IgnoreStrategy;
import cn.featherfly.common.repository.Params.ParamType;

/**
 * ConditionColumnElementTest.
 *
 * @author zhongj
 */
public class ConditionColumnElementTest {

    ConditionColumnElement c;

    String userTableAlias = "u";
    String userTableAlias2 = "u2";
    String username = "yufei";
    Integer age = 18;

    String usernameColumn = "username";
    String ageColumn = "age";

    @BeforeMethod
    void beforeMethod() {
        c = null;
        userTableAlias = "u";
        userTableAlias2 = "u2";
        username = "yufei";
        age = 18;
    }

    void print(ConditionColumnElement c) {
        System.out.println(c);
        System.out.println(ArrayUtils.toString(c.getParam()));
    }

    @SuppressWarnings("unchecked")
    @Test
    void mysql() {
        Dialect dialect = Dialects.mysql();

        c = new ConditionColumnElement(dialect, "username", username, ComparisonOperator.EQ, IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "`username` = ?");
        assertEquals(c.getParam(), username);

        c = new ConditionColumnElement(dialect, "username", "yufei", ComparisonOperator.EQ, userTableAlias,
            IgnoreStrategy.EMPTY);
        assertEquals(c.toSql(), "u.`username` = ?");
        assertEquals(c.getParam(), username);
        print(c);

        c = new ConditionColumnElement(dialect, "age", age, ComparisonOperator.LT, IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "`age` < ?");
        assertEquals(c.getParam(), age);

        c = new ConditionColumnElement(dialect, "age", age, ComparisonOperator.LT, "u", IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "u.`age` < ?");
        assertEquals(c.getParam(), age);

        age = 19;
        c = new ConditionColumnElement(dialect, "age", age, ComparisonOperator.LE, IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "`age` <= ?");
        assertEquals(c.getParam(), age);

        c = new ConditionColumnElement(dialect, "age", age, ComparisonOperator.LE, "u", IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "u.`age` <= ?");
        assertEquals(c.getParam(), age);

        age = 17;
        c = new ConditionColumnElement(dialect, "age", age, ComparisonOperator.GE, IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "`age` >= ?");
        assertEquals(c.getParam(), age);
        c = new ConditionColumnElement(dialect, "age", age, ComparisonOperator.GE, "u", IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "u.`age` >= ?");
        assertEquals(c.getParam(), age);

        age = 16;
        c = new ConditionColumnElement(dialect, "age", age, ComparisonOperator.GT, IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "`age` > ?");
        assertEquals(c.getParam(), age);
        c = new ConditionColumnElement(dialect, "age", age, ComparisonOperator.GT, "u", IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "u.`age` > ?");
        assertEquals(c.getParam(), age);

        age = 15;
        c = new ConditionColumnElement(dialect, "age", age, ComparisonOperator.NE, IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "`age` != ?");
        assertEquals(c.getParam(), age);
        c = new ConditionColumnElement(dialect, "age", age, ComparisonOperator.NE, "u", IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "u.`age` != ?");
        assertEquals(c.getParam(), age);

        String name = "yi";
        c = new ConditionColumnElement(dialect, "name", name, ComparisonOperator.CO, IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "`name` LIKE ?");
        assertEquals(c.getParam(), "%" + name + "%");
        c = new ConditionColumnElement(dialect, "name", name, ComparisonOperator.CO, "u", IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "u.`name` LIKE ?");
        assertEquals(c.getParam(), "%" + name + "%");

        c = new ConditionColumnElement(dialect, "name", name, ComparisonOperator.NCO, IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "`name` NOT LIKE ?");
        assertEquals(c.getParam(), "%" + name + "%");
        c = new ConditionColumnElement(dialect, "name", name, ComparisonOperator.NCO, "u", IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "u.`name` NOT LIKE ?");
        assertEquals(c.getParam(), "%" + name + "%");

        name = "fea";
        c = new ConditionColumnElement(dialect, "name", name, ComparisonOperator.SW, IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "`name` LIKE ?");
        assertEquals(c.getParam(), name + "%");
        c = new ConditionColumnElement(dialect, "name", name, ComparisonOperator.SW, "u", IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "u.`name` LIKE ?");
        assertEquals(c.getParam(), name + "%");

        c = new ConditionColumnElement(dialect, "name", name, ComparisonOperator.NSW, IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "`name` NOT LIKE ?");
        assertEquals(c.getParam(), name + "%");
        c = new ConditionColumnElement(dialect, "name", name, ComparisonOperator.NSW, "u", IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "u.`name` NOT LIKE ?");
        assertEquals(c.getParam(), name + "%");

        name = "fly";
        c = new ConditionColumnElement(dialect, "name", name, ComparisonOperator.EW, IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "`name` LIKE ?");
        assertEquals(c.getParam(), "%" + name);
        c = new ConditionColumnElement(dialect, "name", name, ComparisonOperator.EW, "u", IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "u.`name` LIKE ?");
        assertEquals(c.getParam(), "%" + name);

        c = new ConditionColumnElement(dialect, "name", name, ComparisonOperator.NEW, IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "`name` NOT LIKE ?");
        assertEquals(c.getParam(), "%" + name);
        c = new ConditionColumnElement(dialect, "name", name, ComparisonOperator.NEW, "u", IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "u.`name` NOT LIKE ?");
        assertEquals(c.getParam(), "%" + name);

        Boolean value = true;
        c = new ConditionColumnElement(dialect, "mobile", value, ComparisonOperator.ISN, IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "`mobile` IS NULL");
        //        assertEquals(c.getParam(), null);
        assertEquals(c.getParam(), ParamType.NONE);
        c = new ConditionColumnElement(dialect, "mobile", value, ComparisonOperator.ISN, "u", IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "u.`mobile` IS NULL");
        //        assertEquals(c.getParam(), null);
        assertEquals(c.getParam(), ParamType.NONE);

        c = new ConditionColumnElement(dialect, "mobile", value, ComparisonOperator.INN, IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "`mobile` IS NOT NULL");
        //        assertEquals(c.getParam(), null);
        assertEquals(c.getParam(), ParamType.NONE);
        c = new ConditionColumnElement(dialect, "mobile", value, ComparisonOperator.INN, "u", IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "u.`mobile` IS NOT NULL");
        //        assertEquals(c.getParam(), null);
        assertEquals(c.getParam(), ParamType.NONE);

        value = false;
        c = new ConditionColumnElement(dialect, "mobile", value, ComparisonOperator.ISN, IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "`mobile` IS NOT NULL");
        assertEquals(c.getParam(), ParamType.NONE);
        c = new ConditionColumnElement(dialect, "mobile", value, ComparisonOperator.ISN, "u", IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "u.`mobile` IS NOT NULL");
        assertEquals(c.getParam(), ParamType.NONE);

        c = new ConditionColumnElement(dialect, "mobile", value, ComparisonOperator.INN, IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "`mobile` IS NULL");
        assertEquals(c.getParam(), ParamType.NONE);
        c = new ConditionColumnElement(dialect, "mobile", value, ComparisonOperator.INN, "u", IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "u.`mobile` IS NULL");
        assertEquals(c.getParam(), ParamType.NONE);

        value = null;
        c = new ConditionColumnElement(dialect, "mobile", value, ComparisonOperator.ISN, IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "");
        //        assertEquals(c.getParam(), value);
        assertEquals(c.getParam(), ParamType.NONE);
        c = new ConditionColumnElement(dialect, "mobile", value, ComparisonOperator.ISN, "u", IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "");
        //        assertEquals(c.getParam(), value);
        assertEquals(c.getParam(), ParamType.NONE);

        c = new ConditionColumnElement(dialect, "mobile", value, ComparisonOperator.INN, IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "");
        //        assertEquals(c.getParam(), value);
        assertEquals(c.getParam(), ParamType.NONE);
        c = new ConditionColumnElement(dialect, "mobile", value, ComparisonOperator.INN, "u", IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "");
        //        assertEquals(c.getParam(), value);
        assertEquals(c.getParam(), ParamType.NONE);

        Object[] tags = new Object[] { "a", "b" };
        c = new ConditionColumnElement(dialect, "tag", ArrayUtils.toList(tags), ComparisonOperator.IN,
            IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "`tag` IN (?,?)");
        assertEquals(((List<Object>) c.getParam()).toArray(), tags);
        c = new ConditionColumnElement(dialect, "tag", ArrayUtils.toList(tags), ComparisonOperator.IN, "u",
            IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "u.`tag` IN (?,?)");
        assertEquals(((List<Object>) c.getParam()).toArray(), tags);

        tags = new Object[] { "c", "d" };
        c = new ConditionColumnElement(dialect, "tag", ArrayUtils.toList(tags), ComparisonOperator.NI,
            IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "`tag` NOT IN (?,?)");
        assertEquals(((List<Object>) c.getParam()).toArray(), tags);
        c = new ConditionColumnElement(dialect, "tag", ArrayUtils.toList(tags), ComparisonOperator.NI, "u",
            IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "u.`tag` NOT IN (?,?)");
        assertEquals(((List<Object>) c.getParam()).toArray(), tags);

        name = "fea";
        c = new ConditionColumnElement(dialect, "name", name, ComparisonOperator.LK, IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "`name` LIKE ?");
        assertEquals(c.getParam(), name);
        c = new ConditionColumnElement(dialect, "name", name, ComparisonOperator.LK, "u", IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "u.`name` LIKE ?");
        assertEquals(c.getParam(), name);

        c = new ConditionColumnElement(dialect, "name", name, ComparisonOperator.NL, IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "`name` NOT LIKE ?");
        assertEquals(c.getParam(), name);
        c = new ConditionColumnElement(dialect, "name", name, ComparisonOperator.NL, "u", IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "u.`name` NOT LIKE ?");
        assertEquals(c.getParam(), name);

        // between and
        Object[] btParams = new Object[] { 18, 22 };
        c = new ConditionColumnElement(dialect, "age", new Object[] { 18, 22 }, ComparisonOperator.BA,
            IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "`age` BETWEEN ? AND ?");
        assertEquals(c.getParam(), btParams);

        c = new ConditionColumnElement(dialect, "age", name, ComparisonOperator.BA, "u", IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "u.`age` BETWEEN ? AND ?");
        assertEquals(c.getParam(), name);

        c = new ConditionColumnElement(dialect, "age", new Object[] { 18, 22 }, ComparisonOperator.NBA,
            IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "`age` NOT BETWEEN ? AND ?");
        assertEquals(c.getParam(), btParams);

        c = new ConditionColumnElement(dialect, "age", name, ComparisonOperator.NBA, "u", IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "u.`age` NOT BETWEEN ? AND ?");
        assertEquals(c.getParam(), name);
    }

    @Test
    void mysql_sqlelement() {
        Dialect dialect = Dialects.mysql();
        SqlElement se = new ColumnElement(dialect, usernameColumn, userTableAlias2);
        c = new ConditionColumnElement(dialect, usernameColumn, se, ComparisonOperator.EQ, userTableAlias,
            IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "u.`username` = u2.`username`");
        assertEquals(c.getParam(), se);

        se = () -> "(select name from `user_info` where id = 1)";
        c = new ConditionColumnElement(dialect, usernameColumn, se, ComparisonOperator.EQ, userTableAlias,
            IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "u.`username` = (select name from `user_info` where id = 1)");
        assertEquals(c.getParam(), se);
    }

    @SuppressWarnings("unchecked")
    @Test
    void postgresql() {
        Dialect dialect = Dialects.postgresql();
        ConditionColumnElement c = new ConditionColumnElement(dialect, "username", username, ComparisonOperator.EQ,
            IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "\"username\" = ?");
        assertEquals(c.getParam(), username);

        c = new ConditionColumnElement(dialect, "username", "yufei", ComparisonOperator.EQ, "u", IgnoreStrategy.EMPTY);
        assertEquals(c.toSql(), "u.\"username\" = ?");
        assertEquals(c.getParam(), username);
        print(c);

        c = new ConditionColumnElement(dialect, "age", age, ComparisonOperator.LT, IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "\"age\" < ?");
        assertEquals(c.getParam(), age);

        c = new ConditionColumnElement(dialect, "age", age, ComparisonOperator.LT, "u", IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "u.\"age\" < ?");
        assertEquals(c.getParam(), age);

        age = 19;
        c = new ConditionColumnElement(dialect, "age", age, ComparisonOperator.LE, IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "\"age\" <= ?");
        assertEquals(c.getParam(), age);

        c = new ConditionColumnElement(dialect, "age", age, ComparisonOperator.LE, "u", IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "u.\"age\" <= ?");
        assertEquals(c.getParam(), age);

        age = 17;
        c = new ConditionColumnElement(dialect, "age", age, ComparisonOperator.GE, IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "\"age\" >= ?");
        assertEquals(c.getParam(), age);
        c = new ConditionColumnElement(dialect, "age", age, ComparisonOperator.GE, "u", IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "u.\"age\" >= ?");
        assertEquals(c.getParam(), age);

        age = 16;
        c = new ConditionColumnElement(dialect, "age", age, ComparisonOperator.GT, IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "\"age\" > ?");
        assertEquals(c.getParam(), age);
        c = new ConditionColumnElement(dialect, "age", age, ComparisonOperator.GT, "u", IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "u.\"age\" > ?");
        assertEquals(c.getParam(), age);

        age = 15;
        c = new ConditionColumnElement(dialect, "age", age, ComparisonOperator.NE, IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "\"age\" != ?");
        assertEquals(c.getParam(), age);
        c = new ConditionColumnElement(dialect, "age", age, ComparisonOperator.NE, "u", IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "u.\"age\" != ?");
        assertEquals(c.getParam(), age);

        String name = "yi";
        c = new ConditionColumnElement(dialect, "name", name, ComparisonOperator.CO, IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "\"name\" LIKE ?");
        assertEquals(c.getParam(), "%" + name + "%");
        c = new ConditionColumnElement(dialect, "name", name, ComparisonOperator.CO, "u", IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "u.\"name\" LIKE ?");
        assertEquals(c.getParam(), "%" + name + "%");

        name = "fea";
        c = new ConditionColumnElement(dialect, "name", name, ComparisonOperator.SW, IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "\"name\" LIKE ?");
        assertEquals(c.getParam(), name + "%");
        c = new ConditionColumnElement(dialect, "name", name, ComparisonOperator.SW, "u", IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "u.\"name\" LIKE ?");
        assertEquals(c.getParam(), name + "%");

        name = "fly";
        c = new ConditionColumnElement(dialect, "name", name, ComparisonOperator.EW, IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "\"name\" LIKE ?");
        assertEquals(c.getParam(), "%" + name);
        c = new ConditionColumnElement(dialect, "name", name, ComparisonOperator.EW, "u", IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "u.\"name\" LIKE ?");
        assertEquals(c.getParam(), "%" + name);

        Boolean value = true;
        c = new ConditionColumnElement(dialect, "mobile", value, ComparisonOperator.ISN, IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "\"mobile\" IS NULL");
        assertEquals(c.getParam(), null);
        c = new ConditionColumnElement(dialect, "mobile", value, ComparisonOperator.ISN, "u", IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "u.\"mobile\" IS NULL");
        assertEquals(c.getParam(), null);

        c = new ConditionColumnElement(dialect, "mobile", value, ComparisonOperator.INN, IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "\"mobile\" IS NOT NULL");
        assertEquals(c.getParam(), null);
        c = new ConditionColumnElement(dialect, "mobile", value, ComparisonOperator.INN, "u", IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "u.\"mobile\" IS NOT NULL");
        assertEquals(c.getParam(), null);

        value = false;
        c = new ConditionColumnElement(dialect, "mobile", value, ComparisonOperator.ISN, IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "\"mobile\" IS NOT NULL");
        assertEquals(c.getParam(), null);
        c = new ConditionColumnElement(dialect, "mobile", value, ComparisonOperator.ISN, "u", IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "u.\"mobile\" IS NOT NULL");
        assertEquals(c.getParam(), null);

        c = new ConditionColumnElement(dialect, "mobile", value, ComparisonOperator.INN, IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "\"mobile\" IS NULL");
        assertEquals(c.getParam(), null);
        c = new ConditionColumnElement(dialect, "mobile", value, ComparisonOperator.INN, "u", IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "u.\"mobile\" IS NULL");
        assertEquals(c.getParam(), null);

        value = null;
        c = new ConditionColumnElement(dialect, "mobile", value, ComparisonOperator.ISN, IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "");
        assertEquals(c.getParam(), value);
        c = new ConditionColumnElement(dialect, "mobile", value, ComparisonOperator.ISN, "u", IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "");
        assertEquals(c.getParam(), value);

        c = new ConditionColumnElement(dialect, "mobile", value, ComparisonOperator.INN, IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "");
        assertEquals(c.getParam(), value);
        c = new ConditionColumnElement(dialect, "mobile", value, ComparisonOperator.INN, "u", IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "");
        assertEquals(c.getParam(), value);

        Object[] tags = new Object[] { "a", "b" };
        c = new ConditionColumnElement(dialect, "tag", ArrayUtils.toList(tags), ComparisonOperator.IN,
            IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "\"tag\" IN (?,?)");
        assertEquals(((List<Object>) c.getParam()).toArray(), tags);
        c = new ConditionColumnElement(dialect, "tag", ArrayUtils.toList(tags), ComparisonOperator.IN, "u",
            IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "u.\"tag\" IN (?,?)");
        assertEquals(((List<Object>) c.getParam()).toArray(), tags);

        tags = new Object[] { "c", "d" };
        c = new ConditionColumnElement(dialect, "tag", ArrayUtils.toList(tags), ComparisonOperator.NI,
            IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "\"tag\" NOT IN (?,?)");
        assertEquals(((List<Object>) c.getParam()).toArray(), tags);
        c = new ConditionColumnElement(dialect, "tag", ArrayUtils.toList(tags), ComparisonOperator.NI, "u",
            IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "u.\"tag\" NOT IN (?,?)");
        assertEquals(((List<Object>) c.getParam()).toArray(), tags);

        name = "fly";
        c = new ConditionColumnElement(dialect, "name", name, ComparisonOperator.LK, IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "\"name\" LIKE ?");
        assertEquals(c.getParam(), name);
        c = new ConditionColumnElement(dialect, "name", name, ComparisonOperator.LK, "u", IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "u.\"name\" LIKE ?");
        assertEquals(c.getParam(), name);
    }

    @SuppressWarnings("unchecked")
    @Test
    void oracle() {
        Dialect dialect = Dialects.oracle();
        String username = "yufei";
        Integer age = 18;
        ConditionColumnElement c = new ConditionColumnElement(dialect, "username", username, ComparisonOperator.EQ,
            IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "'username' = ?");
        assertEquals(c.getParam(), username);

        c = new ConditionColumnElement(dialect, "username", "yufei", ComparisonOperator.EQ, "u", IgnoreStrategy.EMPTY);
        assertEquals(c.toSql(), "u.'username' = ?");
        assertEquals(c.getParam(), username);
        print(c);

        c = new ConditionColumnElement(dialect, "age", age, ComparisonOperator.LT, IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "'age' < ?");
        assertEquals(c.getParam(), age);

        c = new ConditionColumnElement(dialect, "age", age, ComparisonOperator.LT, "u", IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "u.'age' < ?");
        assertEquals(c.getParam(), age);

        age = 19;
        c = new ConditionColumnElement(dialect, "age", age, ComparisonOperator.LE, IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "'age' <= ?");
        assertEquals(c.getParam(), age);

        c = new ConditionColumnElement(dialect, "age", age, ComparisonOperator.LE, "u", IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "u.'age' <= ?");
        assertEquals(c.getParam(), age);

        age = 17;
        c = new ConditionColumnElement(dialect, "age", age, ComparisonOperator.GE, IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "'age' >= ?");
        assertEquals(c.getParam(), age);
        c = new ConditionColumnElement(dialect, "age", age, ComparisonOperator.GE, "u", IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "u.'age' >= ?");
        assertEquals(c.getParam(), age);

        age = 16;
        c = new ConditionColumnElement(dialect, "age", age, ComparisonOperator.GT, IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "'age' > ?");
        assertEquals(c.getParam(), age);
        c = new ConditionColumnElement(dialect, "age", age, ComparisonOperator.GT, "u", IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "u.'age' > ?");
        assertEquals(c.getParam(), age);

        age = 15;
        c = new ConditionColumnElement(dialect, "age", age, ComparisonOperator.NE, IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "'age' != ?");
        assertEquals(c.getParam(), age);
        c = new ConditionColumnElement(dialect, "age", age, ComparisonOperator.NE, "u", IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "u.'age' != ?");
        assertEquals(c.getParam(), age);

        String name = "yi";
        c = new ConditionColumnElement(dialect, "name", name, ComparisonOperator.CO, IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "'name' LIKE ?");
        assertEquals(c.getParam(), "%" + name + "%");
        c = new ConditionColumnElement(dialect, "name", name, ComparisonOperator.CO, "u", IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "u.'name' LIKE ?");
        assertEquals(c.getParam(), "%" + name + "%");

        name = "fea";
        c = new ConditionColumnElement(dialect, "name", name, ComparisonOperator.SW, IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "'name' LIKE ?");
        assertEquals(c.getParam(), name + "%");
        c = new ConditionColumnElement(dialect, "name", name, ComparisonOperator.SW, "u", IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "u.'name' LIKE ?");
        assertEquals(c.getParam(), name + "%");

        name = "fly";
        c = new ConditionColumnElement(dialect, "name", name, ComparisonOperator.EW, IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "'name' LIKE ?");
        assertEquals(c.getParam(), "%" + name);
        c = new ConditionColumnElement(dialect, "name", name, ComparisonOperator.EW, "u", IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "u.'name' LIKE ?");
        assertEquals(c.getParam(), "%" + name);

        Boolean value = true;
        c = new ConditionColumnElement(dialect, "mobile", value, ComparisonOperator.ISN, IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "'mobile' IS NULL");
        assertEquals(c.getParam(), null);
        c = new ConditionColumnElement(dialect, "mobile", value, ComparisonOperator.ISN, "u", IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "u.'mobile' IS NULL");
        assertEquals(c.getParam(), null);

        c = new ConditionColumnElement(dialect, "mobile", value, ComparisonOperator.INN, IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "'mobile' IS NOT NULL");
        assertEquals(c.getParam(), null);
        c = new ConditionColumnElement(dialect, "mobile", value, ComparisonOperator.INN, "u", IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "u.'mobile' IS NOT NULL");
        assertEquals(c.getParam(), null);

        value = false;
        c = new ConditionColumnElement(dialect, "mobile", value, ComparisonOperator.ISN, IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "'mobile' IS NOT NULL");
        assertEquals(c.getParam(), null);
        c = new ConditionColumnElement(dialect, "mobile", value, ComparisonOperator.ISN, "u", IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "u.'mobile' IS NOT NULL");
        assertEquals(c.getParam(), null);

        c = new ConditionColumnElement(dialect, "mobile", value, ComparisonOperator.INN, IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "'mobile' IS NULL");
        assertEquals(c.getParam(), null);
        c = new ConditionColumnElement(dialect, "mobile", value, ComparisonOperator.INN, "u", IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "u.'mobile' IS NULL");
        assertEquals(c.getParam(), null);

        value = null;
        c = new ConditionColumnElement(dialect, "mobile", value, ComparisonOperator.ISN, IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "");
        assertEquals(c.getParam(), value);
        c = new ConditionColumnElement(dialect, "mobile", value, ComparisonOperator.ISN, "u", IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "");
        assertEquals(c.getParam(), value);

        c = new ConditionColumnElement(dialect, "mobile", value, ComparisonOperator.INN, IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "");
        assertEquals(c.getParam(), value);
        c = new ConditionColumnElement(dialect, "mobile", value, ComparisonOperator.INN, "u", IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "");
        assertEquals(c.getParam(), value);

        Object[] tags = new Object[] { "a", "b" };
        c = new ConditionColumnElement(dialect, "tag", ArrayUtils.toList(tags), ComparisonOperator.IN,
            IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "'tag' IN (?,?)");
        assertEquals(((List<Object>) c.getParam()).toArray(), tags);
        c = new ConditionColumnElement(dialect, "tag", ArrayUtils.toList(tags), ComparisonOperator.IN, "u",
            IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "u.'tag' IN (?,?)");
        assertEquals(((List<Object>) c.getParam()).toArray(), tags);

        tags = new Object[] { "c", "d" };
        c = new ConditionColumnElement(dialect, "tag", ArrayUtils.toList(tags), ComparisonOperator.NI,
            IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "'tag' NOT IN (?,?)");
        assertEquals(((List<Object>) c.getParam()).toArray(), tags);
        c = new ConditionColumnElement(dialect, "tag", ArrayUtils.toList(tags), ComparisonOperator.NI, "u",
            IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "u.'tag' NOT IN (?,?)");
        assertEquals(((List<Object>) c.getParam()).toArray(), tags);

        name = "fly";
        c = new ConditionColumnElement(dialect, "name", name, ComparisonOperator.LK, IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "'name' LIKE ?");
        assertEquals(c.getParam(), name);
        c = new ConditionColumnElement(dialect, "name", name, ComparisonOperator.LK, "u", IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "u.'name' LIKE ?");
        assertEquals(c.getParam(), name);
    }
}
