
package cn.featherfly.common.db.builder.dml.builder;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import cn.featherfly.common.db.ClassMappingSupport;
import cn.featherfly.common.db.builder.dml.basic.SqlDeleteFromBasicBuilder;
import cn.featherfly.common.db.builder.dml.basic.SqlJoinOnBasicBuilder;
import cn.featherfly.common.db.builder.dml.basic.SqlOrderByBasicBuilder;
import cn.featherfly.common.db.builder.dml.basic.SqlSelectBasicBuilder;
import cn.featherfly.common.db.builder.dml.basic.SqlSelectColumnsBasicBuilder;
import cn.featherfly.common.db.builder.dml.basic.SqlSelectColumnsClassMappingBuilder;
import cn.featherfly.common.db.builder.dml.basic.SqlSelectJoinOnBasicBuilder;
import cn.featherfly.common.db.builder.dml.basic.SqlUpdateSetBasicBuilder;
import cn.featherfly.common.db.dialect.Dialects;
import cn.featherfly.common.db.dialect.Join;
import cn.featherfly.common.db.mapping.JdbcClassMapping;
import cn.featherfly.common.db.mapping.pojo.User;
import cn.featherfly.common.db.mapping.pojo.UserRole;
import cn.featherfly.common.operator.AggregateFunction;
import cn.featherfly.common.repository.IgnorePolicy;
import cn.featherfly.common.structure.ChainMapImpl;

/**
 * BasicBuilderTest.
 *
 * @author zhongj
 */
@Test(groups = { "dml-test" })
public class BasicBuilderTest implements ClassMappingSupport {

    String name = "featherfly";
    String pwd = "2222";
    String sex = "male";
    Integer age = 18;

    String sql = null;
    JdbcClassMapping<User> cm = getUserClassMapping();
    JdbcClassMapping<UserRole> urm = getUserRoleClassMapping();
    String userAlias = "u";
    String userRoleAlias = "ur";
    String userRoleAlias2 = "ur2";

    @Test
    void testSqlSelectColumnsBasicBuilder() {
        SqlSelectColumnsBasicBuilder builder = new SqlSelectColumnsBasicBuilder(Dialects.MYSQL, "u");
        builder.addColumns("username", "mobile", "age", "sex");
        System.out.println(builder.build());
        assertEquals("u.`username`, u.`mobile`, u.`age`, u.`sex`", builder.build());

        builder = new SqlSelectColumnsBasicBuilder(Dialects.MYSQL);
        builder.addColumns("username", "mobile", "age", "sex");
        System.out.println(builder.build());
        assertEquals("`username`, `mobile`, `age`, `sex`", builder.build());
    }

    @Test
    void testSqlSelectColumnsClassMappingBuilder() {
        JdbcClassMapping<User> cm = getUserClassMapping();
        String sql = null;

        SqlSelectColumnsClassMappingBuilder builder = new SqlSelectColumnsClassMappingBuilder(Dialects.MYSQL, cm);
        sql = builder.build();
        System.out.println(sql);
        assertEquals(sql, "`id` `id`, `name` `name`, `descp` `descp`, `password` `pwd`");

        builder = new SqlSelectColumnsClassMappingBuilder(Dialects.MYSQL, cm, "u");
        sql = builder.build();
        System.out.println(sql);
        assertEquals(sql, "u.`id` `id`, u.`name` `name`, u.`descp` `descp`, u.`password` `pwd`");

        builder = new SqlSelectColumnsClassMappingBuilder(Dialects.MYSQL, cm, "u");
        builder.addColumn("id", "id").addColumn("name", "name").addColumn("password", "pwd");
        sql = builder.build();
        System.out.println(sql);
        assertEquals(sql, "u.`id` `id`, u.`name` `name`, u.`password` `pwd`");
    }

    @Test
    void testSqlSelectBasicBuilder() {
        SqlSelectBasicBuilder builder = new SqlSelectBasicBuilder(Dialects.MYSQL, "user", "u");
        builder.addColumns("username", "mobile", "age", "sex");
        System.out.println(builder.build());
        assertEquals("SELECT u.`username`, u.`mobile`, u.`age`, u.`sex` FROM `user` u", builder.build());

        builder = new SqlSelectBasicBuilder(Dialects.MYSQL, "user");
        builder.addColumns("username", "mobile", "age", "sex");
        System.out.println(builder.build());
        assertEquals("SELECT `username`, `mobile`, `age`, `sex` FROM `user`", builder.build());

        builder = new SqlSelectBasicBuilder(Dialects.MYSQL, "user", "u");
        System.out.println(builder.build());
        assertEquals("SELECT u.* FROM `user` u", builder.build());

        builder = new SqlSelectBasicBuilder(Dialects.MYSQL, "user");
        System.out.println(builder.build());
        assertEquals("SELECT * FROM `user`", builder.build());

        builder = new SqlSelectBasicBuilder(Dialects.MYSQL, "user", "u");
        builder.addColumn(AggregateFunction.COUNT, "user_id");
        System.out.println(builder.build());
        assertEquals("SELECT COUNT(u.`user_id`) FROM `user` u", builder.build());

        builder = new SqlSelectBasicBuilder(Dialects.MYSQL, "user");
        builder.addColumn(AggregateFunction.COUNT, "user_id");
        System.out.println(builder.build());
        assertEquals("SELECT COUNT(`user_id`) FROM `user`", builder.build());
    }

    @Test
    void testSqlSelectBasicBuilderJoin() {
        String sql = null;
        SqlSelectBasicBuilder builder = new SqlSelectBasicBuilder(Dialects.MYSQL, "user", "u");
        builder.addColumns("username", "mobile", "age", "sex").join("id", "user_info", "ui", "user_id");
        System.out.println(builder.build());
        assertEquals(
                "SELECT u.`username`, u.`mobile`, u.`age`, u.`sex` FROM `user` u JOIN `user_info` ui ON ui.`user_id` = u.`id`",
                builder.build());

        builder = new SqlSelectBasicBuilder(Dialects.MYSQL, "user", "u");
        builder.addColumns("username", "mobile", "age", "sex").join("id", "user_info", "ui", "user_id")
                .addColumns("name", "passport");
        System.out.println(builder.build());
        assertEquals(
                "SELECT u.`username`, u.`mobile`, u.`age`, u.`sex`, ui.`name`, ui.`passport` FROM `user` u JOIN `user_info` ui ON ui.`user_id` = u.`id`",
                builder.build());

        builder = new SqlSelectBasicBuilder(Dialects.MYSQL, "user", "u");
        sql = builder.addColumns("username", "mobile", "age", "sex").join("id", "user_info", "ui", "user_id").fetch()
                .build();
        System.out.println(sql);
        assertEquals(
                "SELECT u.`username`, u.`mobile`, u.`age`, u.`sex`, ui.* FROM `user` u JOIN `user_info` ui ON ui.`user_id` = u.`id`",
                sql);

        builder = new SqlSelectBasicBuilder(Dialects.MYSQL, "user", "u");
        sql = builder.addColumns("username", "mobile", "age", "sex").join("id", "user_info", "ui", "user_id")
                .addColumns("name", "passport").endJoin().join("id", "user_role", "ur", "user_id").endJoin()
                .join("ur", "role_id", "role", "r", "id").build();
        System.out.println(sql);

    }

    @Test
    void testSqlSelectBasicBuilderMulityTables() {
        String sql = null;
        Map<String, String> tables = new ChainMapImpl<String, String>(new LinkedHashMap<>()).putChain("u", "user")
                .putChain("r", "role");

        SqlSelectBasicBuilder builder = new SqlSelectBasicBuilder(Dialects.MYSQL, tables);
        sql = builder.addColumns("username", "mobile", "age", "sex").table("role", b -> {
            b.addColumns("id", "name");
        }).build();
        System.out.println(sql);
        assertEquals("SELECT u.`username`, u.`mobile`, u.`age`, u.`sex`, r.`id`, r.`name` FROM `user` u, `role` r",
                sql);

        builder = new SqlSelectBasicBuilder(Dialects.MYSQL, tables);
        sql = builder.addColumns("username", "mobile", "age", "sex").table(1, b -> {
            b.addColumns("id", "name");
        }).build();
        System.out.println(sql);
        assertEquals("SELECT u.`username`, u.`mobile`, u.`age`, u.`sex`, r.`id`, r.`name` FROM `user` u, `role` r",
                sql);

        builder = new SqlSelectBasicBuilder(Dialects.MYSQL, tables);
        sql = builder.addColumns("username", "mobile", "age", "sex").table("role", b -> {
            b.addColumns("id", "name");
        }).join("id", "user_info", "ui", "user_id").build();
        System.out.println(sql);
        assertEquals(
                "SELECT u.`username`, u.`mobile`, u.`age`, u.`sex`, r.`id`, r.`name` FROM `user` u, `role` r JOIN `user_info` ui ON ui.`user_id` = u.`id`",
                sql);

        builder = new SqlSelectBasicBuilder(Dialects.MYSQL, tables);
        sql = builder.addColumns("username", "mobile", "age", "sex").table(1, b -> {
            b.addColumns("id", "name");
        }).join("id", "user_info", "ui", "user_id").build();
        System.out.println(sql);
        assertEquals(
                "SELECT u.`username`, u.`mobile`, u.`age`, u.`sex`, r.`id`, r.`name` FROM `user` u, `role` r JOIN `user_info` ui ON ui.`user_id` = u.`id`",
                sql);
    }

    @Test
    void testSqlSelectBasicBuilderClassMapping() {
        SqlSelectBasicBuilder builder = new SqlSelectBasicBuilder(Dialects.MYSQL, cm, userAlias);
        sql = builder.build();
        System.out.println(sql);
        assertEquals(sql, "SELECT u.`id` `id`, u.`name` `name`, u.`descp` `descp`, u.`password` `pwd` FROM `user` u");

        builder.setBuildWithFrom(false);
        sql = builder.build();
        System.out.println(sql);
        assertEquals(sql, "SELECT u.`id` `id`, u.`name` `name`, u.`descp` `descp`, u.`password` `pwd`");

        builder = new SqlSelectBasicBuilder(Dialects.MYSQL, cm, userAlias);
        builder.addColumn("id", "id").addColumn("name", "name").addColumn("password", "pwd");
        sql = builder.build();
        System.out.println(sql);
        assertEquals(sql, "SELECT u.`id` `id`, u.`name` `name`, u.`password` `pwd` FROM `user` u");

        builder.join(userAlias, "id", urm, userRoleAlias, "role_id");
        sql = builder.build();
        System.out.println(sql);

        builder = new SqlSelectBasicBuilder(Dialects.MYSQL, cm, userAlias);
        builder.addColumn("id", "id").addColumn("name", "name").addColumn("password", "pwd");
        sql = builder.build();
        System.out.println(sql);
        assertEquals(sql, "SELECT u.`id` `id`, u.`name` `name`, u.`password` `pwd` FROM `user` u");

        SqlSelectJoinOnBasicBuilder joinBuilder = builder.join(userAlias, "id", urm, userRoleAlias, "role_id");
        sql = builder.build();
        System.out.println(sql);
        System.out.println(joinBuilder.build());

        //        builder.join(userAlias, "id", urm, userRoleAlias, "role_id");
        //        sql = builder.build();
        //        System.out.println(sql);
    }

    @Test
    void testSqlSelectBasicBuilderClassMappingJoin() {
        SqlSelectBasicBuilder builder = new SqlSelectBasicBuilder(Dialects.MYSQL, cm, userAlias);
        builder.addColumn("id", "id").addColumn("name", "name").addColumn("password", "pwd");

        SqlSelectJoinOnBasicBuilder joinBuilder = builder.join(userAlias, "id", urm, userRoleAlias, "role_id")
                .addColumn("role_id", "role_id");
        sql = joinBuilder.build();
        System.out.println(sql);
        assertEquals(sql,
                "SELECT u.`id` `id`, u.`name` `name`, u.`password` `pwd`, ur.`role_id` `role_id` FROM `user` u JOIN `user_role` ur ON ur.`role_id` = u.`id`");

        builder = joinBuilder.endJoin();
        joinBuilder = builder.join(urm, userRoleAlias2, "role_id");
        joinBuilder.addColumn("role_id", "role_id2");
        sql = joinBuilder.build();
        System.out.println(sql);
        assertEquals(sql,
                "SELECT u.`id` `id`, u.`name` `name`, u.`password` `pwd`, ur.`role_id` `role_id`, ur2.`role_id` `role_id2` FROM `user` u JOIN `user_role` ur ON ur.`role_id` = u.`id` JOIN `role_id` ur2 ON ur2.`role_id` = u.`id`");
    }

    @Test
    void testSqlOrderByBasicBuilder() {
        SqlOrderByBasicBuilder builder = new SqlOrderByBasicBuilder(Dialects.MYSQL);
        builder.addAsc("name").addAsc("age").addDesc("sex");
        System.out.println(builder.build());
        assertEquals(" ORDER BY `name` ASC, `age` ASC, `sex` DESC", builder.build());

        builder = new SqlOrderByBasicBuilder(Dialects.MYSQL);
        builder.addAsc("name").addAsc("age").addDesc("sex");
        System.out.println(builder.build());
        assertEquals(" ORDER BY `name` ASC, `age` ASC, `sex` DESC", builder.build());

        builder = new SqlOrderByBasicBuilder(Dialects.MYSQL);
        builder.addAsc("name").addDesc("sex").addDesc("mobile").addAsc("age").addAsc("no");
        System.out.println(builder.build());
        assertEquals(" ORDER BY `name` ASC, `sex` DESC, `mobile` DESC, `age` ASC, `no` ASC", builder.build());

        String alias = "u";
        String alias2 = "p";
        builder = new SqlOrderByBasicBuilder(Dialects.MYSQL);
        builder.addAsc("name", alias).addDesc("sex", alias).addDesc("mobile", alias2).addAsc("age", alias2).addAsc("no",
                alias);
        System.out.println(builder.build());
        assertEquals(" ORDER BY u.`name` ASC, u.`sex` DESC, p.`mobile` DESC, p.`age` ASC, u.`no` ASC", builder.build());
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
