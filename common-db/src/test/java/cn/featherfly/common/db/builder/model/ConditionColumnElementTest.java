
package cn.featherfly.common.db.builder.model;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.testng.annotations.Test;

import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.db.dialect.Dialects;
import cn.featherfly.common.lang.ArrayUtils;
import cn.featherfly.common.operator.ComparisonOperator;
import cn.featherfly.common.repository.IgnoreStrategy;
import cn.featherfly.common.repository.Params;

/**
 * <p>
 * ConditionColumnElementTest
 * </p>
 *
 * @author zhongj
 */
public class ConditionColumnElementTest {

    void print(ConditionColumnElement c) {
        System.out.println(c);
        System.out.println(ArrayUtils.toString(c.getParam()));
    }

    @SuppressWarnings("unchecked")
    @Test
    void testMySql() {
        Dialect dialect = Dialects.MYSQL;
        String username = "yufei";
        Integer age = 18;
        ConditionColumnElement c = new ConditionColumnElement(dialect, "username", username, ComparisonOperator.EQ,
                IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "`username` = ?");
        assertEquals(c.getParam(), username);

        c = new ConditionColumnElement(dialect, "username", "yufei", ComparisonOperator.EQ, "u", IgnoreStrategy.EMPTY);
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
        assertEquals(c.getParam(), Params.NONE);
        c = new ConditionColumnElement(dialect, "mobile", value, ComparisonOperator.ISN, "u", IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "u.`mobile` IS NULL");
        //        assertEquals(c.getParam(), null);
        assertEquals(c.getParam(), Params.NONE);

        c = new ConditionColumnElement(dialect, "mobile", value, ComparisonOperator.INN, IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "`mobile` IS NOT NULL");
        //        assertEquals(c.getParam(), null);
        assertEquals(c.getParam(), Params.NONE);
        c = new ConditionColumnElement(dialect, "mobile", value, ComparisonOperator.INN, "u", IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "u.`mobile` IS NOT NULL");
        //        assertEquals(c.getParam(), null);
        assertEquals(c.getParam(), Params.NONE);

        value = false;
        c = new ConditionColumnElement(dialect, "mobile", value, ComparisonOperator.ISN, IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "`mobile` IS NOT NULL");
        assertEquals(c.getParam(), Params.NONE);
        c = new ConditionColumnElement(dialect, "mobile", value, ComparisonOperator.ISN, "u", IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "u.`mobile` IS NOT NULL");
        assertEquals(c.getParam(), Params.NONE);

        c = new ConditionColumnElement(dialect, "mobile", value, ComparisonOperator.INN, IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "`mobile` IS NULL");
        assertEquals(c.getParam(), Params.NONE);
        c = new ConditionColumnElement(dialect, "mobile", value, ComparisonOperator.INN, "u", IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "u.`mobile` IS NULL");
        assertEquals(c.getParam(), Params.NONE);

        value = null;
        c = new ConditionColumnElement(dialect, "mobile", value, ComparisonOperator.ISN, IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "");
        //        assertEquals(c.getParam(), value);
        assertEquals(c.getParam(), Params.NONE);
        c = new ConditionColumnElement(dialect, "mobile", value, ComparisonOperator.ISN, "u", IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "");
        //        assertEquals(c.getParam(), value);
        assertEquals(c.getParam(), Params.NONE);

        c = new ConditionColumnElement(dialect, "mobile", value, ComparisonOperator.INN, IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "");
        //        assertEquals(c.getParam(), value);
        assertEquals(c.getParam(), Params.NONE);
        c = new ConditionColumnElement(dialect, "mobile", value, ComparisonOperator.INN, "u", IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "");
        //        assertEquals(c.getParam(), value);
        assertEquals(c.getParam(), Params.NONE);

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
        c = new ConditionColumnElement(dialect, "age", new Object[] { 18, 22 }, ComparisonOperator.BT,
                IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "`age` BETWEEN ? AND ?");
        assertEquals(c.getParam(), btParams);

        c = new ConditionColumnElement(dialect, "age", name, ComparisonOperator.BT, "u", IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "u.`age` BETWEEN ? AND ?");
        assertEquals(c.getParam(), name);

        c = new ConditionColumnElement(dialect, "age", new Object[] { 18, 22 }, ComparisonOperator.NBT,
                IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "`age` NOT BETWEEN ? AND ?");
        assertEquals(c.getParam(), btParams);

        c = new ConditionColumnElement(dialect, "age", name, ComparisonOperator.NBT, "u", IgnoreStrategy.EMPTY);
        print(c);
        assertEquals(c.toSql(), "u.`age` NOT BETWEEN ? AND ?");
        assertEquals(c.getParam(), name);

    }

    @SuppressWarnings("unchecked")
    @Test
    void testPostgresql() {
        Dialect dialect = Dialects.POSTGRESQL;
        String username = "yufei";
        Integer age = 18;
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
    void testOracle() {
        Dialect dialect = Dialects.ORACLE;
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
