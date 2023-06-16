
package cn.featherfly.common.db;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import cn.featherfly.common.lang.ArrayUtils;
import cn.featherfly.common.repository.Execution;

/**
 * <p>
 * 类的说明放这里
 * </p>
 * <p>
 * copyright cdthgk 2010-2020, all rights reserved.
 * </p>
 *
 * @author 钟冀
 */
public class SqlUtilsTest {
    public static void main(String[] args) {
        String[] sqls = new String[] { "select u.username from user u where u.age > 0",
                "select u.username FROM user u inner join class c on c.id =u.class_id where u.age > 0",
                "select (select p.name from project p where p.id = 1) from user u inner join class c on c.id =u.class_id where u.age > 0",
                "select u.username,(select p.name from project p where p.id = 1) from user u inner join class c on c.id =u.class_id where u.age > 0",
                "select distinct u.username,u.id from user u inner join class c on c.id =u.class_id where u.age > 0" };

        for (int i = 0; i < sqls.length; i++) {
            System.out.println("sqls[" + i + "]\n\t" + sqls[i] + "\n\t" + SqlUtils.convertSelectToCount(sqls[i]));
        }
    }

    @Test
    void testConvertSelectToCount() {
        String sql = null;
        sql = "select u.username from user u where u.age > 0";
        assertEquals(SqlUtils.convertSelectToCount(sql), "SELECT COUNT(*) from user u where u.age > 0");

        sql = "select u.username FROM user u inner join class c on c.id =u.class_id where u.age > 0";
        assertEquals(SqlUtils.convertSelectToCount(sql),
                "SELECT COUNT(*) FROM user u inner join class c on c.id =u.class_id where u.age > 0");

        sql = "select (select p.name from project p where p.id = 1) from user u inner join class c on c.id =u.class_id where u.age > 0";
        assertEquals(SqlUtils.convertSelectToCount(sql),
                "SELECT COUNT(*) from user u inner join class c on c.id =u.class_id where u.age > 0");

        sql = "select u.username,(select p.name from project p where p.id = 1) from user u inner join class c on c.id =u.class_id where u.age > 0";
        assertEquals(SqlUtils.convertSelectToCount(sql),
                "SELECT COUNT(*) from user u inner join class c on c.id =u.class_id where u.age > 0");

        sql = "select distinct u.username,u.id from user u inner join class c on c.id =u.class_id where u.age > 0";
        assertEquals(SqlUtils.convertSelectToCount(sql),
                "SELECT COUNT(distinct u.username) from user u inner join class c on c.id =u.class_id where u.age > 0");
    }

    @Test
    void testConvertSelectToCountMulitiLine() {
        String sql = "select a.project_code,u.* from user_project_auth a join user u on a.user_id = u.id\n"
                + "order by id desc";
        //        System.out.println(SqlUtils.convertSelectToCount(sql));
        String count = "SELECT COUNT(*) from user_project_auth a join user u on a.user_id = u.id\n"
                + "order by id desc";
        assertEquals(SqlUtils.convertSelectToCount(sql), count);

        sql = "select a.project_code,u.* from user_project_auth a join user u on a.user_id = u.id\r\n"
                + "order by id desc";
        count = "SELECT COUNT(*) from user_project_auth a join user u on a.user_id = u.id\r\n" + "order by id desc";
        assertEquals(SqlUtils.convertSelectToCount(sql), count);

        sql = "select a.project_code,u.* from user_project_auth a join user u on a.user_id = u.id\r"
                + "order by id desc";
        count = "SELECT COUNT(*) from user_project_auth a join user u on a.user_id = u.id\r" + "order by id desc";
        assertEquals(SqlUtils.convertSelectToCount(sql), count);
    }

    @Test
    void testConvertNamedParamSql() {
        String sql = "select * from user where name like ? and age = ?\nand gender = ?";
        Execution execution = null;
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("name", "yufei");
        params.put("age", 18);
        params.put("gender", 0);

        execution = SqlUtils.convertNamedParamSql(
                "select * from user where name like :name and age = :age\nand gender = :gender", params);
        assertEquals(execution.getExecution(), sql);
        assertEquals(execution.getParams(), params.values().toArray());

        execution = SqlUtils.convertNamedParamSql(
                "select * from user where name like {name} and age = {age}\nand gender = {gender}", params, '{', '}');
        assertEquals(execution.getExecution(), sql);
        assertEquals(execution.getParams(), params.values().toArray());

        execution = SqlUtils.convertNamedParamSql(
                "select * from user where name like @name and age = @age\nand gender = @gender", params, '@');
        assertEquals(execution.getExecution(), sql);
        assertEquals(execution.getParams(), params.values().toArray());

        execution = SqlUtils.convertNamedParamSql(
                "select * from user where name like $name and age = $age\nand gender = $gender", params, '$');
        assertEquals(execution.getExecution(), sql);
        assertEquals(execution.getParams(), params.values().toArray());
    }

    @Test
    void testConvertNamedParamSql2() {
        String sql = "insert into role(name, descp) values(?, ?)";
        Execution execution = null;
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("name", "yufei");
        params.put("descp", "descp11");

        execution = SqlUtils.convertNamedParamSql("insert into role(name, descp) values(:name, :descp)", params);
        assertEquals(execution.getExecution(), sql);
        assertEquals(execution.getParams(), params.values().toArray());

        execution = SqlUtils.convertNamedParamSql("insert into role(name, descp) values({name}, {descp})", params, '{',
                '}');
        assertEquals(execution.getExecution(), sql);
        assertEquals(execution.getParams(), params.values().toArray());

        execution = SqlUtils.convertNamedParamSql("insert into role(name, descp) values(@name, @descp)", params, '@');
        assertEquals(execution.getExecution(), sql);
        assertEquals(execution.getParams(), params.values().toArray());
    }

    @Test
    void testConvertNamedParamSqlInParams() {
        String namedSql = "select * from user where id in :ids and gender = :gender";
        String sql = "select * from user where id in (?) and gender = ?";
        String sql2 = "select * from user where id in (?,?) and gender = ?";
        String sql3 = "select * from user where id in (?,?,?) and gender = ?";
        Execution execution = null;

        Map<String, Object> params = new LinkedHashMap<>();
        params.put("gender", "male");
        List<Integer> idList = new ArrayList<>();
        params.put("ids", idList);

        idList.add(1);
        execution = SqlUtils.convertNamedParamSql(namedSql, params);
        System.out.println(execution.getExecution());
        assertEquals(execution.getExecution(), sql);
        System.out.println(ArrayUtils.toString(execution.getParams()));

        idList.add(2);
        execution = SqlUtils.convertNamedParamSql(namedSql, params);
        System.out.println(execution.getExecution());
        assertEquals(execution.getExecution(), sql2);
        System.out.println(ArrayUtils.toString(execution.getParams()));

        idList.add(3);
        execution = SqlUtils.convertNamedParamSql(namedSql, params);
        assertEquals(execution.getExecution(), sql3);

        System.out.println(execution.getExecution());
        System.out.println(ArrayUtils.toString(execution.getParams()));

        // array

        Integer[] idArray = { 1 };
        params.put("ids", idArray);
        execution = SqlUtils.convertNamedParamSql(namedSql, params);
        assertEquals(execution.getExecution(), sql);

        System.out.println(execution.getExecution());
        System.out.println(ArrayUtils.toString(execution.getParams()));

        Integer[] idArray2 = { 1, 2 };
        params.put("ids", idArray2);
        execution = SqlUtils.convertNamedParamSql(namedSql, params);
        assertEquals(execution.getExecution(), sql2);

        System.out.println(execution.getExecution());
        System.out.println(ArrayUtils.toString(execution.getParams()));

        Integer[] idArray3 = { 1, 2, 3 };
        params.put("ids", idArray3);
        execution = SqlUtils.convertNamedParamSql(namedSql, params);
        assertEquals(execution.getExecution(), sql3);

        System.out.println(execution.getExecution());
        System.out.println(ArrayUtils.toString(execution.getParams()));
    }

}
