
package cn.featherfly.common.db.builder.dml.builder;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import cn.featherfly.common.db.builder.dml.SqlConditionGroup;
import cn.featherfly.common.db.builder.dml.SqlFindBuilder;
import cn.featherfly.common.db.builder.dml.SqlQueryBuilder;
import cn.featherfly.common.db.builder.dml.SqlSortBuilder;
import cn.featherfly.common.db.dialect.Dialects;
import cn.featherfly.common.repository.IgnoreStrategy;
import cn.featherfly.common.repository.builder.dml.ConditionBuilder;
import cn.featherfly.common.repository.builder.dml.QueryBuilder;
import cn.featherfly.common.repository.builder.dml.SortBuilder;

/**
 * ConditionBuilderTest
 *
 * @author 钟冀
 */
@Test(groups = { "dml-test" })
public class ConditionBuilderTest {

    QueryBuilder builder = null;
    QueryBuilder sub = null;
    List<Object> params = new ArrayList<>();

    String name = "yufei";
    String pwd = "123";
    String sex = "m";
    Integer age = 18;

    @BeforeClass
    public void init() {
        params.add(name);
        params.add(pwd);
        params.add(sex);
        params.add(age);
    }

    @Test
    public void testSqlQueryBuilderFind() {
        builder = new SqlQueryBuilder(Dialects.MYSQL, IgnoreStrategy.EMPTY);
        builder.find("user", "u").where().eq("name", name).and().eq("pwd", pwd).and().group().eq("sex", sex).or()
                .gt("age", age).sort().asc("age", "sex").desc("name");
        builder = new SqlQueryBuilder(Dialects.MYSQL, IgnoreStrategy.EMPTY);
        builder.find("user", "u").property("name", "pwd", "age", "sex").where().eq("name", name).and().eq("pwd", pwd)
                .and().group().eq("sex", sex).or().gt("age", age).sort().asc("age", "sex").desc("name");

        System.out.println(builder.build());
        System.out.println(((SqlQueryBuilder) builder).getParams());
        assertEquals(
                "SELECT u.`name`, u.`pwd`, u.`age`, u.`sex` FROM `user` u WHERE u.`name` = ? AND u.`pwd` = ? AND ( u.`sex` = ? OR u.`age` > ? ) ORDER BY u.`age` ASC, u.`sex` ASC, u.`name` DESC",
                builder.build());
        assertEquals(params, ((SqlQueryBuilder) builder).getParams());
    }

    @Test
    void testSqlQueryBuilderSelect() {
        // FIXME 这个测试未通过SelectBuilder有BUG
        SqlQueryBuilder builder2 = new SqlQueryBuilder(Dialects.MYSQL, IgnoreStrategy.EMPTY);
        builder2.from("user", "u").where().eq("name", name).and().eq("pwd", pwd).and().group().eq("sex", sex).or()
                .gt("age", 18).sort().asc("age", "sex").desc("name");
        System.out.println(builder2.build());
        System.out.println(builder2.getParams());
        assertEquals(
                "SELECT u.* FROM `user` u WHERE u.`name` = ? AND u.`pwd` = ? AND ( u.`sex` = ? OR u.`age` > ? ) ORDER BY u.`age` ASC, u.`sex` ASC, u.`name` DESC",
                builder2.build());
        assertEquals(params, builder2.getParams());

        builder2 = new SqlQueryBuilder(Dialects.MYSQL, IgnoreStrategy.EMPTY);
        builder2.select(new String[] { "name", "pwd", "age", "sex" }).from("user", "u2").where().eq("name", name).and()
                .eq("pwd", pwd).and().group().eq("sex", sex).or().gt("age", age).sort().asc("age", "sex").desc("name");
        System.out.println(builder2.build());
        System.out.println(builder2.getParams());
        assertEquals(
                "SELECT u2.`name`, u2.`pwd`, u2.`age`, u2.`sex` FROM `user` u2 WHERE u2.`name` = ? AND u2.`pwd` = ? AND ( u2.`sex` = ? OR u2.`age` > ? ) ORDER BY u2.`age` ASC, u2.`sex` ASC, u2.`name` DESC",
                builder2.build());
        assertEquals(params, builder2.getParams());

    }

    @Test
    void testConditionBuilder() {
        ConditionBuilder cb = new SqlConditionGroup(Dialects.MYSQL, IgnoreStrategy.EMPTY, null);
        cb.eq("name", name).and().eq("pwd", pwd).and().group().eq("sex", sex).or().gt("age", age);
        System.out.println(cb.build());
        System.out.println(((SqlConditionGroup) cb).getParamValues());
        assertEquals("`name` = ? AND `pwd` = ? AND ( `sex` = ? OR `age` > ? )", cb.build());
        assertEquals(params, cb.getParamValue());

    }

    @Test
    void testSortBuilder() {
        //
        SortBuilder sortBuilder = new SqlSortBuilder(Dialects.MYSQL);
        sortBuilder.desc("dddesc").asc("zzz", "xxx");
        System.out.println(sortBuilder.build());
        assertEquals(" ORDER BY `dddesc` DESC, `zzz` ASC, `xxx` ASC", sortBuilder.build());

    }

    @Test
    void testSqlFindBuilder() {
        SqlFindBuilder findBuilder = new SqlFindBuilder(Dialects.MYSQL, "user", null);
        System.out.println(findBuilder.build());
        //        assertEquals(findBuilder.build(), "SELECT * FROM `user`");
        assertEquals(findBuilder.build(), "SELECT _user0.* FROM `user` _user0");

        findBuilder = new SqlFindBuilder(Dialects.MYSQL, "user", "u3", null);
        System.out.println(findBuilder.build());
        assertEquals("SELECT u3.* FROM `user` u3", findBuilder.build());
    }

}
