
package cn.featherfly.common.db.builder.model;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.testng.annotations.Test;

import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.db.dialect.Dialects;
import cn.featherfly.common.lang.ArrayUtils;
import cn.featherfly.common.repository.operate.QueryOperator;

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
        System.out.println(c.getParam());
    }

    @SuppressWarnings("unchecked")
    @Test
    void testMySql() {
        Dialect dialect = Dialects.MYSQL;
        String username = "yufei";
        Integer age = 18;
        ConditionColumnElement c = new ConditionColumnElement(dialect, "username", username, QueryOperator.EQ);
        print(c);
        assertEquals(c.toSql(), "`username` = ?");
        assertEquals(c.getParam(), username);

        c = new ConditionColumnElement(dialect, "username", "yufei", QueryOperator.EQ, "u");
        assertEquals(c.toSql(), "u.`username` = ?");
        assertEquals(c.getParam(), username);
        print(c);

        c = new ConditionColumnElement(dialect, "age", age, QueryOperator.LT);
        print(c);
        assertEquals(c.toSql(), "`age` < ?");
        assertEquals(c.getParam(), age);

        c = new ConditionColumnElement(dialect, "age", age, QueryOperator.LT, "u");
        print(c);
        assertEquals(c.toSql(), "u.`age` < ?");
        assertEquals(c.getParam(), age);

        age = 19;
        c = new ConditionColumnElement(dialect, "age", age, QueryOperator.LE);
        print(c);
        assertEquals(c.toSql(), "`age` <= ?");
        assertEquals(c.getParam(), age);

        c = new ConditionColumnElement(dialect, "age", age, QueryOperator.LE, "u");
        print(c);
        assertEquals(c.toSql(), "u.`age` <= ?");
        assertEquals(c.getParam(), age);

        age = 17;
        c = new ConditionColumnElement(dialect, "age", age, QueryOperator.GE);
        print(c);
        assertEquals(c.toSql(), "`age` >= ?");
        assertEquals(c.getParam(), age);
        c = new ConditionColumnElement(dialect, "age", age, QueryOperator.GE, "u");
        print(c);
        assertEquals(c.toSql(), "u.`age` >= ?");
        assertEquals(c.getParam(), age);

        age = 16;
        c = new ConditionColumnElement(dialect, "age", age, QueryOperator.GT);
        print(c);
        assertEquals(c.toSql(), "`age` > ?");
        assertEquals(c.getParam(), age);
        c = new ConditionColumnElement(dialect, "age", age, QueryOperator.GT, "u");
        print(c);
        assertEquals(c.toSql(), "u.`age` > ?");
        assertEquals(c.getParam(), age);

        age = 15;
        c = new ConditionColumnElement(dialect, "age", age, QueryOperator.NE);
        print(c);
        assertEquals(c.toSql(), "`age` != ?");
        assertEquals(c.getParam(), age);
        c = new ConditionColumnElement(dialect, "age", age, QueryOperator.NE, "u");
        print(c);
        assertEquals(c.toSql(), "u.`age` != ?");
        assertEquals(c.getParam(), age);

        String name = "yi";
        c = new ConditionColumnElement(dialect, "name", name, QueryOperator.CO);
        print(c);
        assertEquals(c.toSql(), "`name` LIKE ?");
        assertEquals(c.getParam(), "%" + name + "%");
        c = new ConditionColumnElement(dialect, "name", name, QueryOperator.CO, "u");
        print(c);
        assertEquals(c.toSql(), "u.`name` LIKE ?");
        assertEquals(c.getParam(), "%" + name + "%");

        name = "fea";
        c = new ConditionColumnElement(dialect, "name", name, QueryOperator.SW);
        print(c);
        assertEquals(c.toSql(), "`name` LIKE ?");
        assertEquals(c.getParam(), name + "%");
        c = new ConditionColumnElement(dialect, "name", name, QueryOperator.SW, "u");
        print(c);
        assertEquals(c.toSql(), "u.`name` LIKE ?");
        assertEquals(c.getParam(), name + "%");

        name = "fly";
        c = new ConditionColumnElement(dialect, "name", name, QueryOperator.EW);
        print(c);
        assertEquals(c.toSql(), "`name` LIKE ?");
        assertEquals(c.getParam(), "%" + name);
        c = new ConditionColumnElement(dialect, "name", name, QueryOperator.EW, "u");
        print(c);
        assertEquals(c.toSql(), "u.`name` LIKE ?");
        assertEquals(c.getParam(), "%" + name);

        Boolean value = true;
        c = new ConditionColumnElement(dialect, "mobile", value, QueryOperator.ISN);
        print(c);
        assertEquals(c.toSql(), "`mobile` IS NULL");
        assertEquals(c.getParam(), value);
        c = new ConditionColumnElement(dialect, "mobile", value, QueryOperator.ISN, "u");
        print(c);
        assertEquals(c.toSql(), "u.`mobile` IS NULL");
        assertEquals(c.getParam(), value);

        c = new ConditionColumnElement(dialect, "mobile", value, QueryOperator.INN);
        print(c);
        assertEquals(c.toSql(), "`mobile` IS NOT NULL");
        assertEquals(c.getParam(), value);
        c = new ConditionColumnElement(dialect, "mobile", value, QueryOperator.INN, "u");
        print(c);
        assertEquals(c.toSql(), "u.`mobile` IS NOT NULL");
        assertEquals(c.getParam(), value);

        value = false;
        c = new ConditionColumnElement(dialect, "mobile", value, QueryOperator.ISN);
        print(c);
        assertEquals(c.toSql(), "`mobile` IS NOT NULL");
        assertEquals(c.getParam(), value);
        c = new ConditionColumnElement(dialect, "mobile", value, QueryOperator.ISN, "u");
        print(c);
        assertEquals(c.toSql(), "u.`mobile` IS NOT NULL");
        assertEquals(c.getParam(), value);

        c = new ConditionColumnElement(dialect, "mobile", value, QueryOperator.INN);
        print(c);
        assertEquals(c.toSql(), "`mobile` IS NULL");
        assertEquals(c.getParam(), value);
        c = new ConditionColumnElement(dialect, "mobile", value, QueryOperator.INN, "u");
        print(c);
        assertEquals(c.toSql(), "u.`mobile` IS NULL");
        assertEquals(c.getParam(), value);

        value = null;
        c = new ConditionColumnElement(dialect, "mobile", value, QueryOperator.ISN);
        print(c);
        assertEquals(c.toSql(), "");
        assertEquals(c.getParam(), value);
        c = new ConditionColumnElement(dialect, "mobile", value, QueryOperator.ISN, "u");
        print(c);
        assertEquals(c.toSql(), "");
        assertEquals(c.getParam(), value);

        c = new ConditionColumnElement(dialect, "mobile", value, QueryOperator.INN);
        print(c);
        assertEquals(c.toSql(), "");
        assertEquals(c.getParam(), value);
        c = new ConditionColumnElement(dialect, "mobile", value, QueryOperator.INN, "u");
        print(c);
        assertEquals(c.toSql(), "");
        assertEquals(c.getParam(), value);

        Object[] tags = new Object[] { "a", "b" };
        c = new ConditionColumnElement(dialect, "tag", ArrayUtils.toList(tags), QueryOperator.IN);
        print(c);
        assertEquals(c.toSql(), "`tag` IN (?,?)");
        assertEquals(((List<Object>) c.getParam()).toArray(), tags);
        c = new ConditionColumnElement(dialect, "tag", ArrayUtils.toList(tags), QueryOperator.IN, "u");
        print(c);
        assertEquals(c.toSql(), "u.`tag` IN (?,?)");
        assertEquals(((List<Object>) c.getParam()).toArray(), tags);

        tags = new Object[] { "c", "d" };
        c = new ConditionColumnElement(dialect, "tag", ArrayUtils.toList(tags), QueryOperator.NIN);
        print(c);
        assertEquals(c.toSql(), "`tag` NOT IN (?,?)");
        assertEquals(((List<Object>) c.getParam()).toArray(), tags);
        c = new ConditionColumnElement(dialect, "tag", ArrayUtils.toList(tags), QueryOperator.NIN, "u");
        print(c);
        assertEquals(c.toSql(), "u.`tag` NOT IN (?,?)");
        assertEquals(((List<Object>) c.getParam()).toArray(), tags);

        name = "fea";
        c = new ConditionColumnElement(dialect, "name", name, QueryOperator.LK);
        print(c);
        assertEquals(c.toSql(), "`name` LIKE ?");
        assertEquals(c.getParam(), name);
        c = new ConditionColumnElement(dialect, "name", name, QueryOperator.LK, "u");
        print(c);
        assertEquals(c.toSql(), "u.`name` LIKE ?");
        assertEquals(c.getParam(), name);
    }

    @SuppressWarnings("unchecked")
    @Test
    void testPostgresql() {
        Dialect dialect = Dialects.POSTGRESQL;
        String username = "yufei";
        Integer age = 18;
        ConditionColumnElement c = new ConditionColumnElement(dialect, "username", username, QueryOperator.EQ);
        print(c);
        assertEquals(c.toSql(), "\"username\" = ?");
        assertEquals(c.getParam(), username);

        c = new ConditionColumnElement(dialect, "username", "yufei", QueryOperator.EQ, "u");
        assertEquals(c.toSql(), "u.\"username\" = ?");
        assertEquals(c.getParam(), username);
        print(c);

        c = new ConditionColumnElement(dialect, "age", age, QueryOperator.LT);
        print(c);
        assertEquals(c.toSql(), "\"age\" < ?");
        assertEquals(c.getParam(), age);

        c = new ConditionColumnElement(dialect, "age", age, QueryOperator.LT, "u");
        print(c);
        assertEquals(c.toSql(), "u.\"age\" < ?");
        assertEquals(c.getParam(), age);

        age = 19;
        c = new ConditionColumnElement(dialect, "age", age, QueryOperator.LE);
        print(c);
        assertEquals(c.toSql(), "\"age\" <= ?");
        assertEquals(c.getParam(), age);

        c = new ConditionColumnElement(dialect, "age", age, QueryOperator.LE, "u");
        print(c);
        assertEquals(c.toSql(), "u.\"age\" <= ?");
        assertEquals(c.getParam(), age);

        age = 17;
        c = new ConditionColumnElement(dialect, "age", age, QueryOperator.GE);
        print(c);
        assertEquals(c.toSql(), "\"age\" >= ?");
        assertEquals(c.getParam(), age);
        c = new ConditionColumnElement(dialect, "age", age, QueryOperator.GE, "u");
        print(c);
        assertEquals(c.toSql(), "u.\"age\" >= ?");
        assertEquals(c.getParam(), age);

        age = 16;
        c = new ConditionColumnElement(dialect, "age", age, QueryOperator.GT);
        print(c);
        assertEquals(c.toSql(), "\"age\" > ?");
        assertEquals(c.getParam(), age);
        c = new ConditionColumnElement(dialect, "age", age, QueryOperator.GT, "u");
        print(c);
        assertEquals(c.toSql(), "u.\"age\" > ?");
        assertEquals(c.getParam(), age);

        age = 15;
        c = new ConditionColumnElement(dialect, "age", age, QueryOperator.NE);
        print(c);
        assertEquals(c.toSql(), "\"age\" != ?");
        assertEquals(c.getParam(), age);
        c = new ConditionColumnElement(dialect, "age", age, QueryOperator.NE, "u");
        print(c);
        assertEquals(c.toSql(), "u.\"age\" != ?");
        assertEquals(c.getParam(), age);

        String name = "yi";
        c = new ConditionColumnElement(dialect, "name", name, QueryOperator.CO);
        print(c);
        assertEquals(c.toSql(), "\"name\" LIKE ?");
        assertEquals(c.getParam(), "%" + name + "%");
        c = new ConditionColumnElement(dialect, "name", name, QueryOperator.CO, "u");
        print(c);
        assertEquals(c.toSql(), "u.\"name\" LIKE ?");
        assertEquals(c.getParam(), "%" + name + "%");

        name = "fea";
        c = new ConditionColumnElement(dialect, "name", name, QueryOperator.SW);
        print(c);
        assertEquals(c.toSql(), "\"name\" LIKE ?");
        assertEquals(c.getParam(), name + "%");
        c = new ConditionColumnElement(dialect, "name", name, QueryOperator.SW, "u");
        print(c);
        assertEquals(c.toSql(), "u.\"name\" LIKE ?");
        assertEquals(c.getParam(), name + "%");

        name = "fly";
        c = new ConditionColumnElement(dialect, "name", name, QueryOperator.EW);
        print(c);
        assertEquals(c.toSql(), "\"name\" LIKE ?");
        assertEquals(c.getParam(), "%" + name);
        c = new ConditionColumnElement(dialect, "name", name, QueryOperator.EW, "u");
        print(c);
        assertEquals(c.toSql(), "u.\"name\" LIKE ?");
        assertEquals(c.getParam(), "%" + name);

        Boolean value = true;
        c = new ConditionColumnElement(dialect, "mobile", value, QueryOperator.ISN);
        print(c);
        assertEquals(c.toSql(), "\"mobile\" IS NULL");
        assertEquals(c.getParam(), value);
        c = new ConditionColumnElement(dialect, "mobile", value, QueryOperator.ISN, "u");
        print(c);
        assertEquals(c.toSql(), "u.\"mobile\" IS NULL");
        assertEquals(c.getParam(), value);

        c = new ConditionColumnElement(dialect, "mobile", value, QueryOperator.INN);
        print(c);
        assertEquals(c.toSql(), "\"mobile\" IS NOT NULL");
        assertEquals(c.getParam(), value);
        c = new ConditionColumnElement(dialect, "mobile", value, QueryOperator.INN, "u");
        print(c);
        assertEquals(c.toSql(), "u.\"mobile\" IS NOT NULL");
        assertEquals(c.getParam(), value);

        value = false;
        c = new ConditionColumnElement(dialect, "mobile", value, QueryOperator.ISN);
        print(c);
        assertEquals(c.toSql(), "\"mobile\" IS NOT NULL");
        assertEquals(c.getParam(), value);
        c = new ConditionColumnElement(dialect, "mobile", value, QueryOperator.ISN, "u");
        print(c);
        assertEquals(c.toSql(), "u.\"mobile\" IS NOT NULL");
        assertEquals(c.getParam(), value);

        c = new ConditionColumnElement(dialect, "mobile", value, QueryOperator.INN);
        print(c);
        assertEquals(c.toSql(), "\"mobile\" IS NULL");
        assertEquals(c.getParam(), value);
        c = new ConditionColumnElement(dialect, "mobile", value, QueryOperator.INN, "u");
        print(c);
        assertEquals(c.toSql(), "u.\"mobile\" IS NULL");
        assertEquals(c.getParam(), value);

        value = null;
        c = new ConditionColumnElement(dialect, "mobile", value, QueryOperator.ISN);
        print(c);
        assertEquals(c.toSql(), "");
        assertEquals(c.getParam(), value);
        c = new ConditionColumnElement(dialect, "mobile", value, QueryOperator.ISN, "u");
        print(c);
        assertEquals(c.toSql(), "");
        assertEquals(c.getParam(), value);

        c = new ConditionColumnElement(dialect, "mobile", value, QueryOperator.INN);
        print(c);
        assertEquals(c.toSql(), "");
        assertEquals(c.getParam(), value);
        c = new ConditionColumnElement(dialect, "mobile", value, QueryOperator.INN, "u");
        print(c);
        assertEquals(c.toSql(), "");
        assertEquals(c.getParam(), value);

        Object[] tags = new Object[] { "a", "b" };
        c = new ConditionColumnElement(dialect, "tag", ArrayUtils.toList(tags), QueryOperator.IN);
        print(c);
        assertEquals(c.toSql(), "\"tag\" IN (?,?)");
        assertEquals(((List<Object>) c.getParam()).toArray(), tags);
        c = new ConditionColumnElement(dialect, "tag", ArrayUtils.toList(tags), QueryOperator.IN, "u");
        print(c);
        assertEquals(c.toSql(), "u.\"tag\" IN (?,?)");
        assertEquals(((List<Object>) c.getParam()).toArray(), tags);

        tags = new Object[] { "c", "d" };
        c = new ConditionColumnElement(dialect, "tag", ArrayUtils.toList(tags), QueryOperator.NIN);
        print(c);
        assertEquals(c.toSql(), "\"tag\" NOT IN (?,?)");
        assertEquals(((List<Object>) c.getParam()).toArray(), tags);
        c = new ConditionColumnElement(dialect, "tag", ArrayUtils.toList(tags), QueryOperator.NIN, "u");
        print(c);
        assertEquals(c.toSql(), "u.\"tag\" NOT IN (?,?)");
        assertEquals(((List<Object>) c.getParam()).toArray(), tags);

        name = "fly";
        c = new ConditionColumnElement(dialect, "name", name, QueryOperator.LK);
        print(c);
        assertEquals(c.toSql(), "\"name\" LIKE ?");
        assertEquals(c.getParam(), name);
        c = new ConditionColumnElement(dialect, "name", name, QueryOperator.LK, "u");
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
        ConditionColumnElement c = new ConditionColumnElement(dialect, "username", username, QueryOperator.EQ);
        print(c);
        assertEquals(c.toSql(), "'username' = ?");
        assertEquals(c.getParam(), username);

        c = new ConditionColumnElement(dialect, "username", "yufei", QueryOperator.EQ, "u");
        assertEquals(c.toSql(), "u.'username' = ?");
        assertEquals(c.getParam(), username);
        print(c);

        c = new ConditionColumnElement(dialect, "age", age, QueryOperator.LT);
        print(c);
        assertEquals(c.toSql(), "'age' < ?");
        assertEquals(c.getParam(), age);

        c = new ConditionColumnElement(dialect, "age", age, QueryOperator.LT, "u");
        print(c);
        assertEquals(c.toSql(), "u.'age' < ?");
        assertEquals(c.getParam(), age);

        age = 19;
        c = new ConditionColumnElement(dialect, "age", age, QueryOperator.LE);
        print(c);
        assertEquals(c.toSql(), "'age' <= ?");
        assertEquals(c.getParam(), age);

        c = new ConditionColumnElement(dialect, "age", age, QueryOperator.LE, "u");
        print(c);
        assertEquals(c.toSql(), "u.'age' <= ?");
        assertEquals(c.getParam(), age);

        age = 17;
        c = new ConditionColumnElement(dialect, "age", age, QueryOperator.GE);
        print(c);
        assertEquals(c.toSql(), "'age' >= ?");
        assertEquals(c.getParam(), age);
        c = new ConditionColumnElement(dialect, "age", age, QueryOperator.GE, "u");
        print(c);
        assertEquals(c.toSql(), "u.'age' >= ?");
        assertEquals(c.getParam(), age);

        age = 16;
        c = new ConditionColumnElement(dialect, "age", age, QueryOperator.GT);
        print(c);
        assertEquals(c.toSql(), "'age' > ?");
        assertEquals(c.getParam(), age);
        c = new ConditionColumnElement(dialect, "age", age, QueryOperator.GT, "u");
        print(c);
        assertEquals(c.toSql(), "u.'age' > ?");
        assertEquals(c.getParam(), age);

        age = 15;
        c = new ConditionColumnElement(dialect, "age", age, QueryOperator.NE);
        print(c);
        assertEquals(c.toSql(), "'age' != ?");
        assertEquals(c.getParam(), age);
        c = new ConditionColumnElement(dialect, "age", age, QueryOperator.NE, "u");
        print(c);
        assertEquals(c.toSql(), "u.'age' != ?");
        assertEquals(c.getParam(), age);

        String name = "yi";
        c = new ConditionColumnElement(dialect, "name", name, QueryOperator.CO);
        print(c);
        assertEquals(c.toSql(), "'name' LIKE ?");
        assertEquals(c.getParam(), "%" + name + "%");
        c = new ConditionColumnElement(dialect, "name", name, QueryOperator.CO, "u");
        print(c);
        assertEquals(c.toSql(), "u.'name' LIKE ?");
        assertEquals(c.getParam(), "%" + name + "%");

        name = "fea";
        c = new ConditionColumnElement(dialect, "name", name, QueryOperator.SW);
        print(c);
        assertEquals(c.toSql(), "'name' LIKE ?");
        assertEquals(c.getParam(), name + "%");
        c = new ConditionColumnElement(dialect, "name", name, QueryOperator.SW, "u");
        print(c);
        assertEquals(c.toSql(), "u.'name' LIKE ?");
        assertEquals(c.getParam(), name + "%");

        name = "fly";
        c = new ConditionColumnElement(dialect, "name", name, QueryOperator.EW);
        print(c);
        assertEquals(c.toSql(), "'name' LIKE ?");
        assertEquals(c.getParam(), "%" + name);
        c = new ConditionColumnElement(dialect, "name", name, QueryOperator.EW, "u");
        print(c);
        assertEquals(c.toSql(), "u.'name' LIKE ?");
        assertEquals(c.getParam(), "%" + name);

        Boolean value = true;
        c = new ConditionColumnElement(dialect, "mobile", value, QueryOperator.ISN);
        print(c);
        assertEquals(c.toSql(), "'mobile' IS NULL");
        assertEquals(c.getParam(), value);
        c = new ConditionColumnElement(dialect, "mobile", value, QueryOperator.ISN, "u");
        print(c);
        assertEquals(c.toSql(), "u.'mobile' IS NULL");
        assertEquals(c.getParam(), value);

        c = new ConditionColumnElement(dialect, "mobile", value, QueryOperator.INN);
        print(c);
        assertEquals(c.toSql(), "'mobile' IS NOT NULL");
        assertEquals(c.getParam(), value);
        c = new ConditionColumnElement(dialect, "mobile", value, QueryOperator.INN, "u");
        print(c);
        assertEquals(c.toSql(), "u.'mobile' IS NOT NULL");
        assertEquals(c.getParam(), value);

        value = false;
        c = new ConditionColumnElement(dialect, "mobile", value, QueryOperator.ISN);
        print(c);
        assertEquals(c.toSql(), "'mobile' IS NOT NULL");
        assertEquals(c.getParam(), value);
        c = new ConditionColumnElement(dialect, "mobile", value, QueryOperator.ISN, "u");
        print(c);
        assertEquals(c.toSql(), "u.'mobile' IS NOT NULL");
        assertEquals(c.getParam(), value);

        c = new ConditionColumnElement(dialect, "mobile", value, QueryOperator.INN);
        print(c);
        assertEquals(c.toSql(), "'mobile' IS NULL");
        assertEquals(c.getParam(), value);
        c = new ConditionColumnElement(dialect, "mobile", value, QueryOperator.INN, "u");
        print(c);
        assertEquals(c.toSql(), "u.'mobile' IS NULL");
        assertEquals(c.getParam(), value);

        value = null;
        c = new ConditionColumnElement(dialect, "mobile", value, QueryOperator.ISN);
        print(c);
        assertEquals(c.toSql(), "");
        assertEquals(c.getParam(), value);
        c = new ConditionColumnElement(dialect, "mobile", value, QueryOperator.ISN, "u");
        print(c);
        assertEquals(c.toSql(), "");
        assertEquals(c.getParam(), value);

        c = new ConditionColumnElement(dialect, "mobile", value, QueryOperator.INN);
        print(c);
        assertEquals(c.toSql(), "");
        assertEquals(c.getParam(), value);
        c = new ConditionColumnElement(dialect, "mobile", value, QueryOperator.INN, "u");
        print(c);
        assertEquals(c.toSql(), "");
        assertEquals(c.getParam(), value);

        Object[] tags = new Object[] { "a", "b" };
        c = new ConditionColumnElement(dialect, "tag", ArrayUtils.toList(tags), QueryOperator.IN);
        print(c);
        assertEquals(c.toSql(), "'tag' IN (?,?)");
        assertEquals(((List<Object>) c.getParam()).toArray(), tags);
        c = new ConditionColumnElement(dialect, "tag", ArrayUtils.toList(tags), QueryOperator.IN, "u");
        print(c);
        assertEquals(c.toSql(), "u.'tag' IN (?,?)");
        assertEquals(((List<Object>) c.getParam()).toArray(), tags);

        tags = new Object[] { "c", "d" };
        c = new ConditionColumnElement(dialect, "tag", ArrayUtils.toList(tags), QueryOperator.NIN);
        print(c);
        assertEquals(c.toSql(), "'tag' NOT IN (?,?)");
        assertEquals(((List<Object>) c.getParam()).toArray(), tags);
        c = new ConditionColumnElement(dialect, "tag", ArrayUtils.toList(tags), QueryOperator.NIN, "u");
        print(c);
        assertEquals(c.toSql(), "u.'tag' NOT IN (?,?)");
        assertEquals(((List<Object>) c.getParam()).toArray(), tags);

        name = "fly";
        c = new ConditionColumnElement(dialect, "name", name, QueryOperator.LK);
        print(c);
        assertEquals(c.toSql(), "'name' LIKE ?");
        assertEquals(c.getParam(), name);
        c = new ConditionColumnElement(dialect, "name", name, QueryOperator.LK, "u");
        print(c);
        assertEquals(c.toSql(), "u.'name' LIKE ?");
        assertEquals(c.getParam(), name);
    }
}
