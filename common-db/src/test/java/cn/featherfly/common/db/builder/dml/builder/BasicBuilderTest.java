
package cn.featherfly.common.db.builder.dml.builder;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import cn.featherfly.common.db.builder.dml.basic.SqlDeleteFromBasicBuilder;
import cn.featherfly.common.db.builder.dml.basic.SqlJoinOnBasicBuilder;
import cn.featherfly.common.db.builder.dml.basic.SqlOrderByBasicBuilder;
import cn.featherfly.common.db.builder.dml.basic.SqlSelectBasicBuilder;
import cn.featherfly.common.db.builder.dml.basic.SqlSelectColumnsBasicBuilder;
import cn.featherfly.common.db.builder.dml.basic.SqlUpdateSetBasicBuilder;
import cn.featherfly.common.db.dialect.Dialects;
import cn.featherfly.common.db.dialect.Join;
import cn.featherfly.common.repository.IgnorePolicy;
import cn.featherfly.common.repository.operate.AggregateFunction;

/**
 * <p>
 * BasicBuilderTest
 * </p>
 *
 * @author zhongj
 */
@Test(groups = { "dml-test" })
public class BasicBuilderTest {

    String name = "featherfly";
    String pwd = "2222";
    String sex = "male";
    Integer age = 18;

    @Test
    void testSqlSelectColumnsBasicBuilder() {
        SqlSelectColumnsBasicBuilder builder = new SqlSelectColumnsBasicBuilder(Dialects.MYSQL, "u");
        builder.addSelectColumns("username", "mobile", "age", "sex");
        System.out.println(builder.build());
        assertEquals("u.`username`, u.`mobile`, u.`age`, u.`sex`", builder.build());

        builder = new SqlSelectColumnsBasicBuilder(Dialects.MYSQL);
        builder.addSelectColumns("username", "mobile", "age", "sex");
        System.out.println(builder.build());
        assertEquals("`username`, `mobile`, `age`, `sex`", builder.build());
    }

    @Test
    void testSqlSelectBasicBuilder() {
        SqlSelectBasicBuilder builder = new SqlSelectBasicBuilder(Dialects.MYSQL, "user", "u");
        builder.addSelectColumns("username", "mobile", "age", "sex");
        System.out.println(builder.build());
        assertEquals("SELECT u.`username`, u.`mobile`, u.`age`, u.`sex` FROM `user` u", builder.build());

        builder = new SqlSelectBasicBuilder(Dialects.MYSQL, "user");
        builder.addSelectColumns("username", "mobile", "age", "sex");
        System.out.println(builder.build());
        assertEquals("SELECT `username`, `mobile`, `age`, `sex` FROM `user`", builder.build());

        builder = new SqlSelectBasicBuilder(Dialects.MYSQL, "user", "u");
        System.out.println(builder.build());
        assertEquals("SELECT u.* FROM `user` u", builder.build());

        builder = new SqlSelectBasicBuilder(Dialects.MYSQL, "user");
        System.out.println(builder.build());
        assertEquals("SELECT * FROM `user`", builder.build());

        builder = new SqlSelectBasicBuilder(Dialects.MYSQL, "user", "u");
        builder.addSelectColumn("user_id", AggregateFunction.COUNT);
        System.out.println(builder.build());
        assertEquals("SELECT COUNT(u.`user_id`) FROM `user` u", builder.build());

        builder = new SqlSelectBasicBuilder(Dialects.MYSQL, "user");
        builder.addSelectColumn("user_id", AggregateFunction.COUNT);
        System.out.println(builder.build());
        assertEquals("SELECT COUNT(`user_id`) FROM `user`", builder.build());
    }

    @Test
    void testSqlSelectBasicBuilder2() {
        String sql = null;
        SqlSelectBasicBuilder builder = new SqlSelectBasicBuilder(Dialects.MYSQL, "user", "u");
        builder.addSelectColumns("username", "mobile", "age", "sex").join("id", "user_info", "ui", "user_id");
        System.out.println(builder.build());
        assertEquals(
                "SELECT u.`username`, u.`mobile`, u.`age`, u.`sex` FROM `user` u JOIN `user_info` ui ON ui.`user_id` = u.`id`",
                builder.build());

        builder = new SqlSelectBasicBuilder(Dialects.MYSQL, "user", "u");
        builder.addSelectColumns("username", "mobile", "age", "sex").join("id", "user_info", "ui", "user_id")
                .addSelectColumns("name", "passport");
        System.out.println(builder.build());
        assertEquals(
                "SELECT u.`username`, u.`mobile`, u.`age`, u.`sex`, ui.`name`, ui.`passport` FROM `user` u JOIN `user_info` ui ON ui.`user_id` = u.`id`",
                builder.build());

        builder = new SqlSelectBasicBuilder(Dialects.MYSQL, "user", "u");
        sql = builder.addSelectColumns("username", "mobile", "age", "sex").join("id", "user_info", "ui", "user_id")
                .fetch().build();
        System.out.println(sql);
        assertEquals(
                "SELECT u.`username`, u.`mobile`, u.`age`, u.`sex`, ui.* FROM `user` u JOIN `user_info` ui ON ui.`user_id` = u.`id`",
                sql);

        builder = new SqlSelectBasicBuilder(Dialects.MYSQL, "user", "u");
        sql = builder.addSelectColumns("username", "mobile", "age", "sex").join("id", "user_info", "ui", "user_id")
                .addSelectColumns("name", "passport").endJoin().join("id", "user_role", "ur", "user_id").endJoin()
                .join("ur", "role_id", "role", "r", "id").build();
        System.out.println(sql);

    }

    @Test
    void testSqlOrderByBasicBuilder() {
        SqlOrderByBasicBuilder builder = new SqlOrderByBasicBuilder(Dialects.MYSQL);
        builder.addAsc("name").addAsc("age").addDesc("sex");
        System.out.println(builder.build());
        assertEquals(" ORDER BY `name`, `age` ASC, `sex` DESC", builder.build());

        builder = new SqlOrderByBasicBuilder(Dialects.MYSQL);
        builder.addAsc("name").addAsc("age").addDesc("sex");
        System.out.println(builder.build());
        assertEquals(" ORDER BY `name`, `age` ASC, `sex` DESC", builder.build());

        builder = new SqlOrderByBasicBuilder(Dialects.MYSQL);
        builder.addAsc("name").addDesc("sex").addDesc("mobile").addAsc("age").addAsc("no");
        System.out.println(builder.build());
        assertEquals(" ORDER BY `name` ASC, `sex`, `mobile` DESC, `age`, `no` ASC", builder.build());

        String alias = "u";
        String alias2 = "p";
        builder = new SqlOrderByBasicBuilder(Dialects.MYSQL);
        builder.addAsc("name", alias).addDesc("sex", alias).addDesc("mobile", alias2).addAsc("age", alias2).addAsc("no",
                alias);
        System.out.println(builder.build());
        assertEquals(" ORDER BY u.`name` ASC, u.`sex`, p.`mobile` DESC, p.`age`, u.`no` ASC", builder.build());
    }

    @Test
    void testSqlUpdateSetBasicBuilder() {
        List<Object> params = new ArrayList<>();
        params.add(name);
        params.add(age);
        params.add(sex);
        params.add(pwd);

        SqlUpdateSetBasicBuilder builder = new SqlUpdateSetBasicBuilder(Dialects.MYSQL, "user", IgnorePolicy.EMPTY);
        builder.setValue("name", name).setValue("age", age).setValue("sex", sex).setValue("pwd", pwd);
        System.out.println(builder.build());
        System.out.println(builder.getParams());
        assertEquals("UPDATE `user` SET `name` = ?, `age` = ?, `sex` = ?, `pwd` = ?", builder.build());
        assertEquals(params, builder.getParams());

        builder = new SqlUpdateSetBasicBuilder(Dialects.MYSQL, "user", "u", IgnorePolicy.EMPTY);
        builder.setValue("name", name).setValue("age", age).setValue("sex", sex).setValue("pwd", pwd);
        System.out.println(builder.build());
        System.out.println(builder.getParams());
        assertEquals("UPDATE `user` u SET u.`name` = ?, u.`age` = ?, u.`sex` = ?, u.`pwd` = ?", builder.build());
        assertEquals(params, builder.getParams());
    }

    @Test
    void testSqlJoinBasicBuilder() {
        SqlJoinOnBasicBuilder builder = new SqlJoinOnBasicBuilder(Dialects.MYSQL, "user", "uid", "user_id");
        System.out.println(builder.build());
        assertEquals(builder.build(), "JOIN `user` ON `uid` = `user_id`");

        builder = new SqlJoinOnBasicBuilder(Dialects.MYSQL, Join.LEFT_JOIN, "user", "uid", "user_id");
        System.out.println(builder.build());
        assertEquals(builder.build(), "LEFT JOIN `user` ON `uid` = `user_id`");

        builder = new SqlJoinOnBasicBuilder(Dialects.MYSQL, Join.RIGHT_JOIN, "user", "uid", "user_id");
        System.out.println(builder.build());
        assertEquals(builder.build(), "RIGHT JOIN `user` ON `uid` = `user_id`");

        builder = new SqlJoinOnBasicBuilder(Dialects.MYSQL, Join.FULL_JOIN, "user", "uid", "user_id");
        System.out.println(builder.build());
        assertEquals(builder.build(), "FULL JOIN `user` ON `uid` = `user_id`");

        builder = new SqlJoinOnBasicBuilder(Dialects.MYSQL, "user", "u", "uid", "r", "user_id");
        System.out.println(builder.build());
        assertEquals(builder.build(), "JOIN `user` u ON u.`uid` = r.`user_id`");

        builder = new SqlJoinOnBasicBuilder(Dialects.MYSQL, Join.LEFT_JOIN, "user", "u", "uid", "r", "user_id");
        System.out.println(builder.build());
        assertEquals(builder.build(), "LEFT JOIN `user` u ON u.`uid` = r.`user_id`");

    }

    @Test
    void testSqlDeleteFromBasicBuilder() {
        SqlDeleteFromBasicBuilder builder = new SqlDeleteFromBasicBuilder(Dialects.MYSQL, "user");
        System.out.println(builder.build());
        assertEquals(builder.build(), "DELETE FROM `user`");

    }

    // @Test
    // void testAndGroup() {
    // SqlQueryBuilder builder1 = new SqlQueryBuilder();
    // SqlQueryBuilder builder2 = new SqlQueryBuilder();
    // builder1.from("user").eq("name", "yufei").and().group().eq("age",
    // 18).or().eq("sex", "male").parent().or()
    // .eq("city", "cd");
    // builder2.from("user").eq("name", "yufei").andGroup().eq("age",
    // 18).or().eq("sex", "male").parent().or()
    // .eq("city", "cd");
    // assertEquals(builder1.build(), builder2.build());
    // }
}
