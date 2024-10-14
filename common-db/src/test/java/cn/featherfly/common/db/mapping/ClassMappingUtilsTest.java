
package cn.featherfly.common.db.mapping;

import static org.testng.Assert.assertEquals;

import java.util.Map;

import org.testng.annotations.Test;

import cn.featherfly.common.db.ClassMappingSupport;
import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.db.dialect.Dialects;
import cn.featherfly.common.db.mapping.pojo.User;
import cn.featherfly.common.db.mapping.pojo.UserRole;
import cn.featherfly.common.lang.ArrayUtils;
import cn.featherfly.common.tuple.Tuple2;
import cn.featherfly.common.tuple.Tuple3;

/**
 * ClassMappingUtilsTest.
 *
 * @author zhongj
 */
public class ClassMappingUtilsTest implements ClassMappingSupport {

    Dialect mysqlDialect = Dialects.mysql();
    Dialect postgresqlDialect = Dialects.postgresql();
    Dialect sqliteDialect = Dialects.sqlite();
    Dialect oracleDialect = Dialects.oracle();

    @Test
    void testSelectSql() {

        String sql = ClassMappingUtils.getSelectSql(getUserClassMapping(mysqlDialect), mysqlDialect);

        System.out.println(sql);

        assertEquals(sql, "SELECT `id` `id`, `name` `name`, `descp` `descp`, `password` `pwd` FROM `user`");
    }

    @Test
    void testSelectSql2() {

        String sql = ClassMappingUtils.getSelectSql(getUserRoleClassMapping2(), mysqlDialect);

        System.out.println(sql);

        assertEquals(sql,
            "SELECT `user_id` `user.id`, `role_id` `role.id`, `descp` `descp`, `descp2` `descp2` FROM `user_role`");
    }

    @Test
    void testSelectByIdSql() {
        String sql = ClassMappingUtils.getSelectByPkSql(getUserClassMapping(mysqlDialect), mysqlDialect);

        System.out.println(sql);
        assertEquals(sql,
            "SELECT `id` `id`, `name` `name`, `descp` `descp`, `password` `pwd` FROM `user` WHERE `id` = ?");

        sql = ClassMappingUtils.getSelectByPkSql(getUserRoleClassMapping(), mysqlDialect);

        System.out.println(sql);
        assertEquals(sql,
            "SELECT `user_id` `userId`, `role_id` `roleId`, `descp` `descp`, `descp2` `descp2` FROM `user_role` WHERE `user_id` = ? AND `role_id` = ?");

    }

    @Test
    void testSelectByIdSql2() {
        String sql = ClassMappingUtils.getSelectByPkSql(getUserRoleClassMapping2(), mysqlDialect);

        System.out.println(sql);
        assertEquals(sql,
            "SELECT `user_id` `user.id`, `role_id` `role.id`, `descp` `descp`, `descp2` `descp2` FROM `user_role` WHERE `user_id` = ? AND `role_id` = ?");
    }

    @Test
    void testInsert() {
        Tuple2<String, Map<Integer, JdbcPropertyMapping>> t = ClassMappingUtils
            .getInsertSqlAndParamPositions(getUserClassMapping(mysqlDialect), mysqlDialect);
        System.out.println(t.get0());
        System.out.println(t.get1());
        assertEquals(t.get1().get(1).getPropertyName(), getUserProperty1());
        assertEquals(t.get1().get(2).getPropertyName(), getUserProperty2());
        assertEquals(t.get1().get(3).getPropertyName(), getUserProperty3());
        assertEquals(t.get1().get(4).getPropertyName(), getUserProperty4());

        assertEquals(t.get0(), "INSERT INTO `user` (`id`, `name`, `descp`, `password`) VALUES (?, ?, ?, ?)");
    }

    @Test
    void testInsert2() {
        Tuple2<String, JdbcPropertyMapping[]> t = ClassMappingUtils
            .getInsertSqlAndMappings(getUserClassMapping(mysqlDialect), mysqlDialect);
        System.out.println(t.get0());
        System.out.println(ArrayUtils.toString(t.get1()));
        assertEquals(t.get1()[0].getPropertyName(), getUserProperty1());
        assertEquals(t.get1()[1].getPropertyName(), getUserProperty2());
        assertEquals(t.get1()[2].getPropertyName(), getUserProperty3());
        assertEquals(t.get1()[3].getPropertyName(), getUserProperty4());

        assertEquals(t.get0(), "INSERT INTO `user` (`id`, `name`, `descp`, `password`) VALUES (?, ?, ?, ?)");
    }

    @Test
    void testUpdate() {
        Tuple2<String, Map<Integer, JdbcPropertyMapping>> t = ClassMappingUtils
            .getUpdateSqlAndParamPositions(getUserClassMapping(mysqlDialect), mysqlDialect);
        System.out.println(t.get0());
        System.out.println(t.get1());

        assertEquals(t.get0(), "UPDATE `user` SET `name` = ?, `descp` = ?, `password` = ? WHERE `id` = ?");

        t = ClassMappingUtils.getUpdateSqlAndParamPositions(getUserRoleClassMapping(), mysqlDialect);
        System.out.println(t.get0());
        System.out.println(t.get1());
        assertEquals(t.get0(),
            "UPDATE `user_role` SET `descp` = ?, `descp2` = ? WHERE `user_id` = ? AND `role_id` = ?");
    }

    @Test
    void testUpdate2() {
        Tuple2<String, JdbcPropertyMapping[]> t = ClassMappingUtils
            .getUpdateSqlAndMappings(getUserClassMapping(mysqlDialect), mysqlDialect);
        System.out.println(t.get0());
        System.out.println(t.get1());

        assertEquals(t.get1()[0].getRepositoryFieldName(), "name");

        assertEquals(t.get0(), "UPDATE `user` SET `name` = ?, `descp` = ?, `password` = ? WHERE `id` = ?");

        t = ClassMappingUtils.getUpdateSqlAndMappings(getUserRoleClassMapping(), mysqlDialect);
        System.out.println(t.get0());
        System.out.println(t.get1());
        assertEquals(t.get1()[0].getRepositoryFieldName(), "descp");
        assertEquals(t.get0(),
            "UPDATE `user_role` SET `descp` = ?, `descp2` = ? WHERE `user_id` = ? AND `role_id` = ?");
    }

    @Test
    void testDelete() {
        Tuple2<String, Map<Integer, JdbcPropertyMapping>> t = ClassMappingUtils
            .getDeleteSqlAndParamPositions(getUserClassMapping(mysqlDialect), mysqlDialect);
        System.out.println(t.get0());
        System.out.println(t.get1());

        assertEquals(t.get0(), "DELETE FROM `user` WHERE `id` = ?");
        assertEquals(t.get1().get(1).getPropertyFullName(), "id");

        t = ClassMappingUtils.getDeleteSqlAndParamPositions(getUserRoleClassMapping(), mysqlDialect);
        System.out.println(t.get0());
        System.out.println(t.get1());
        assertEquals(t.get0(), "DELETE FROM `user_role` WHERE `user_id` = ? AND `role_id` = ?");
        assertEquals(t.get1().get(1).getPropertyFullName(), "userId");
        assertEquals(t.get1().get(2).getPropertyFullName(), "roleId");
    }

    @Test
    void testDelete2() {
        Tuple2<String, JdbcPropertyMapping[]> t = ClassMappingUtils
            .getDeleteSqlAndMappings(getUserClassMapping(mysqlDialect), mysqlDialect);
        System.out.println(t.get0());
        System.out.println(t.get1());

        assertEquals(t.get0(), "DELETE FROM `user` WHERE `id` = ?");
        assertEquals(t.get1()[0].getPropertyFullName(), "id");

        t = ClassMappingUtils.getDeleteSqlAndMappings(getUserRoleClassMapping(), mysqlDialect);
        System.out.println(t.get0());
        System.out.println(t.get1());

        assertEquals(t.get0(), "DELETE FROM `user_role` WHERE `user_id` = ? AND `role_id` = ?");
        assertEquals(t.get1()[0].getPropertyFullName(), "userId");
        assertEquals(t.get1()[1].getPropertyFullName(), "roleId");
    }

    @Test
    void testDeleteBatch() {
        Tuple2<String, Map<Integer, JdbcPropertyMapping>> t = null;
        t = ClassMappingUtils.getDeleteSqlAndParamPositions(3, getUserClassMapping(mysqlDialect), mysqlDialect);
        System.out.println(t.get0());
        System.out.println(t.get1());

        assertEquals(t.get0(), "DELETE FROM `user` WHERE `id` IN (?,?,?)");

        t = ClassMappingUtils.getDeleteSqlAndParamPositions(2, getUserRoleClassMapping(), mysqlDialect);
        System.out.println(t.get0());
        System.out.println(t.get1());
        assertEquals(t.get0(),
            "DELETE FROM `user_role` WHERE  (`user_id` = ? AND `role_id` = ?) OR  (`user_id` = ? AND `role_id` = ?)");
    }

    @Test
    void testMerge() {
        User user = new User();
        user.setId(12L);
        user.setName("yufei");
        Tuple3<String, Map<Integer, JdbcPropertyMapping>, Integer> t = ClassMappingUtils
            .getMergeSqlAndParamPositions(user, getUserClassMapping(mysqlDialect), false, mysqlDialect);
        System.out.println(t.get0());
        System.out.println(t.get1());

        assertEquals(t.get0(), "UPDATE `user` SET `name` = ? WHERE `id` = ?");

        user.setDescp("descp");
        t = ClassMappingUtils.getMergeSqlAndParamPositions(user, getUserClassMapping(mysqlDialect), true, mysqlDialect);
        System.out.println(t.get0());
        System.out.println(t.get1());

        assertEquals(t.get0(), "UPDATE `user` SET `name` = ?, `descp` = ? WHERE `id` = ?");
        UserRole ur = new UserRole();
        ur.setUserId(1);
        ur.setRoleId(1);
        ur.setDescp("d");
        t = ClassMappingUtils.getMergeSqlAndParamPositions(ur, getUserRoleClassMapping(), true, mysqlDialect);
        System.out.println(t.get0());
        System.out.println(t.get1());

        assertEquals(t.get0(), "UPDATE `user_role` SET `descp` = ? WHERE `user_id` = ? AND `role_id` = ?");

        ur.setDescp(null);
        ur.setDescp2("d2");
        t = ClassMappingUtils.getMergeSqlAndParamPositions(ur, getUserRoleClassMapping(), true, mysqlDialect);
        System.out.println(t.get0());
        System.out.println(t.get1());

        assertEquals(t.get0(), "UPDATE `user_role` SET `descp2` = ? WHERE `user_id` = ? AND `role_id` = ?");

        ur.setDescp("d");
        ur.setDescp2("d2");
        t = ClassMappingUtils.getMergeSqlAndParamPositions(ur, getUserRoleClassMapping(), true, mysqlDialect);
        System.out.println(t.get0());
        System.out.println(t.get1());

        assertEquals(t.get0(),
            "UPDATE `user_role` SET `descp` = ?, `descp2` = ? WHERE `user_id` = ? AND `role_id` = ?");
    }

    @Test
    void testMerge2() {
        User user = new User();
        user.setId(12L);
        user.setName("yufei");
        Tuple3<String, JdbcPropertyMapping[], Integer> t = ClassMappingUtils.getMergeSqlAndMappings(user,
            getUserClassMapping(mysqlDialect), false, mysqlDialect);
        System.out.println(t.get0());
        System.out.println(t.get1());

        assertEquals(t.get0(), "UPDATE `user` SET `name` = ? WHERE `id` = ?");

        user.setDescp("descp");
        t = ClassMappingUtils.getMergeSqlAndMappings(user, getUserClassMapping(mysqlDialect), true, mysqlDialect);
        System.out.println(t.get0());
        System.out.println(t.get1());

        assertEquals(t.get0(), "UPDATE `user` SET `name` = ?, `descp` = ? WHERE `id` = ?");
        UserRole ur = new UserRole();
        ur.setUserId(1);
        ur.setRoleId(1);
        ur.setDescp("d");
        t = ClassMappingUtils.getMergeSqlAndMappings(ur, getUserRoleClassMapping(), true, mysqlDialect);
        System.out.println(t.get0());
        System.out.println(t.get1());

        assertEquals(t.get0(), "UPDATE `user_role` SET `descp` = ? WHERE `user_id` = ? AND `role_id` = ?");

        ur.setDescp(null);
        ur.setDescp2("d2");
        t = ClassMappingUtils.getMergeSqlAndMappings(ur, getUserRoleClassMapping(), true, mysqlDialect);
        System.out.println(t.get0());
        System.out.println(t.get1());

        assertEquals(t.get0(), "UPDATE `user_role` SET `descp2` = ? WHERE `user_id` = ? AND `role_id` = ?");

        ur.setDescp("d");
        ur.setDescp2("d2");
        t = ClassMappingUtils.getMergeSqlAndMappings(ur, getUserRoleClassMapping(), true, mysqlDialect);
        System.out.println(t.get0());
        System.out.println(t.get1());

        assertEquals(t.get0(),
            "UPDATE `user_role` SET `descp` = ?, `descp2` = ? WHERE `user_id` = ? AND `role_id` = ?");
    }

    @Test
    void testMergeNoColumnChange() {
        User user = new User();
        user.setId(12L);
        Tuple3<String, Map<Integer, JdbcPropertyMapping>, Integer> t = ClassMappingUtils
            .getMergeSqlAndParamPositions(user, getUserClassMapping(mysqlDialect), false, mysqlDialect);
        System.out.println(t.get0());
        System.out.println(t.get1());

        assertEquals(t.get2(), new Integer(0));
        assertEquals(t.get0(), "UPDATE `user` WHERE `id` = ?");
    }

    @Test
    void testMergeNoColumnChange2() {
        User user = new User();
        user.setId(12L);
        Tuple3<String, JdbcPropertyMapping[], Integer> t = ClassMappingUtils.getMergeSqlAndMappings(user,
            getUserClassMapping(mysqlDialect), false, mysqlDialect);
        System.out.println(t.get0());
        System.out.println(t.get1());

        assertEquals(t.get2(), new Integer(0));
        assertEquals(t.get0(), "UPDATE `user` WHERE `id` = ?");
    }

    @Test
    void testInsertBatchMysql() {
        Tuple2<String, Map<Integer, JdbcPropertyMapping>> t = ClassMappingUtils.getInsertBatchSqlAndParamPositions(5,
            getUserClassMapping(mysqlDialect), mysqlDialect);

        //        System.out.println(t.get0());
        //        System.out.println(t.get1());
        //
        //        for (int i = 0; i < 5; i++) {
        //            int len = t.get1().size();
        //            System.out.println("第" + i + "对象");
        //            for (Entry<Integer, String> e : t.get1().entrySet()) {
        //                int position = e.getKey() + i * len;
        //                System.out.println(e.getValue());
        //                System.out.println(position);
        //            }
        //        }

        assertEquals(t.get0(),
            "INSERT INTO `user` (`id`, `name`, `descp`, `password`) VALUES (?, ?, ?, ?),(?, ?, ?, ?),(?, ?, ?, ?),(?, ?, ?, ?),(?, ?, ?, ?)");
        assertEquals(t.get1().get(1).getPropertyName(), "id");
        assertEquals(t.get1().get(2).getPropertyName(), "name");
        assertEquals(t.get1().get(3).getPropertyName(), "descp");
        //        expected [id] but found [PropertyMapping [propertyName=name, repositoryFieldName=name, propertyType=class java.lang.String, primaryKey=false, defaultValue=null, mode=SINGLE, propertyMappings={}, parent=]]
    }

    @Test
    void testInsertBatchMysql2() {
        Tuple2<String, Map<Integer, JdbcPropertyMapping>> t = ClassMappingUtils.getInsertBatchSqlAndParamPositions(5,
            getUserRoleClassMapping(), mysqlDialect);

        //        System.out.println(t.get0());
        //        System.out.println(t.get1());
        //
        //        for (int i = 0; i < 5; i++) {
        //            int len = t.get1().size();
        //            System.out.println("第" + i + "对象");
        //            for (Entry<Integer, String> e : t.get1().entrySet()) {
        //                int position = e.getKey() + i * len;
        //                System.out.println(e.getValue());
        //                System.out.println(position);
        //            }
        //        }

        assertEquals(t.get0(),
            "INSERT INTO `user_role` (`user_id`, `role_id`, `descp`, `descp2`) VALUES (?, ?, ?, ?),(?, ?, ?, ?),(?, ?, ?, ?),(?, ?, ?, ?),(?, ?, ?, ?)");
        assertEquals(t.get1().get(1).getPropertyName(), "userId");
        assertEquals(t.get1().get(2).getPropertyName(), "roleId");
        assertEquals(t.get1().get(3).getPropertyName(), "descp");
        assertEquals(t.get1().get(4).getPropertyName(), "descp2");
    }

    @Test
    void testInsertBatchSqlite() {
        int size = 5;
        Tuple2<String, Map<Integer, JdbcPropertyMapping>> t = ClassMappingUtils.getInsertBatchSqlAndParamPositions(size,
            getUserClassMapping(sqliteDialect), sqliteDialect);

        //        System.out.println(t.get0());
        //        System.out.println(t.get1());
        //
        //        for (int i = 0; i < 5; i++) {
        //            int len = t.get1().size();
        //            System.out.println("第" + i + "对象");
        //            for (Entry<Integer, String> e : t.get1().entrySet()) {
        //                int position = e.getKey() + i * len;
        //                System.out.println(e.getValue());
        //                System.out.println(position);
        //            }
        //        }

        assertEquals(t.get0(),
            "INSERT INTO `user` SELECT ? AS `id`, ? AS `name`, ? AS `descp`, ? AS `password` UNION SELECT ?, ?, ?, ? UNION SELECT ?, ?, ?, ? UNION SELECT ?, ?, ?, ? UNION SELECT ?, ?, ?, ?");
        assertEquals(t.get1().get(1).getPropertyName(), "id");
        assertEquals(t.get1().get(2).getPropertyName(), "name");
        assertEquals(t.get1().get(3).getPropertyName(), "descp");
    }

    @Test
    void testUpsertMysql() {
        Tuple2<String, Map<Integer, JdbcPropertyMapping>> t = ClassMappingUtils
            .getUpsertSqlAndParamPositions(getUserClassMapping(mysqlDialect), mysqlDialect);
        System.out.println(t.get0());
        System.out.println(t.get1());

        assertEquals(t.get0(),
            "INSERT INTO `user` (`id`, `name`, `descp`, `password`) VALUES (?, ?, ?, ?) ON DUPLICATE KEY UPDATE `name`=values(`name`), `descp`=values(`descp`), `password`=values(`password`)");
    }

    @Test
    void testUpsertMysql2() {
        Tuple2<String, JdbcPropertyMapping[]> t = ClassMappingUtils
            .getUpsertSqlAndMappings(getUserClassMapping(mysqlDialect), mysqlDialect);
        System.out.println(t.get0());
        System.out.println(t.get1());

        assertEquals(t.get1()[0].getRepositoryFieldName(), "id");

        assertEquals(t.get0(),
            "INSERT INTO `user` (`id`, `name`, `descp`, `password`) VALUES (?, ?, ?, ?) ON DUPLICATE KEY UPDATE `name`=values(`name`), `descp`=values(`descp`), `password`=values(`password`)");
    }

    @Test
    void testUpsertPostgresql() {
        Tuple2<String, Map<Integer, JdbcPropertyMapping>> t = ClassMappingUtils
            .getUpsertSqlAndParamPositions(getUserClassMapping(postgresqlDialect), postgresqlDialect);
        System.out.println(t.get0());
        System.out.println(t.get1());

        assertEquals(t.get0(),
            "INSERT INTO \"user\" (\"id\", \"name\", \"descp\", \"password\") VALUES (?, ?, ?, ?) ON CONFLICT (id) DO UPDATE SET \"name\"=EXCLUDED.\"name\", \"descp\"=EXCLUDED.\"descp\", \"password\"=EXCLUDED.\"password\"");
    }

    @Test
    void testUpsertPostgresql2() {
        Tuple2<String, JdbcPropertyMapping[]> t = ClassMappingUtils
            .getUpsertSqlAndMappings(getUserClassMapping(postgresqlDialect), postgresqlDialect);
        System.out.println(t.get0());
        System.out.println(t.get1());

        assertEquals(t.get1()[0].getRepositoryFieldName(), "id");

        assertEquals(t.get0(),
            "INSERT INTO \"user\" (\"id\", \"name\", \"descp\", \"password\") VALUES (?, ?, ?, ?) ON CONFLICT (id) DO UPDATE SET \"name\"=EXCLUDED.\"name\", \"descp\"=EXCLUDED.\"descp\", \"password\"=EXCLUDED.\"password\"");
    }

    @Test
    void testUpsertSqlite() {
        Tuple2<String, Map<Integer, JdbcPropertyMapping>> t = ClassMappingUtils
            .getUpsertSqlAndParamPositions(getUserClassMapping(sqliteDialect), sqliteDialect);
        System.out.println(t.get0());
        System.out.println(t.get1());

        assertEquals(t.get0(),
            "INSERT INTO `user` (`id`, `name`, `descp`, `password`) VALUES (?, ?, ?, ?) ON CONFLICT (id) DO UPDATE SET `name`=EXCLUDED.`name`, `descp`=EXCLUDED.`descp`, `password`=EXCLUDED.`password`");
    }

    @Test
    void testUpsertSqlite2() {
        Tuple2<String, JdbcPropertyMapping[]> t = ClassMappingUtils
            .getUpsertSqlAndMappings(getUserClassMapping(sqliteDialect), sqliteDialect);
        System.out.println(t.get0());
        System.out.println(t.get1());

        assertEquals(t.get1()[0].getRepositoryFieldName(), "id");

        assertEquals(t.get0(),
            "INSERT INTO `user` (`id`, `name`, `descp`, `password`) VALUES (?, ?, ?, ?) ON CONFLICT (id) DO UPDATE SET `name`=EXCLUDED.`name`, `descp`=EXCLUDED.`descp`, `password`=EXCLUDED.`password`");
    }

    @Test
    void testUpsertBatchMysql() {
        Tuple2<String, Map<Integer, JdbcPropertyMapping>> t = ClassMappingUtils.getUpsertBatchSqlAndParamPositions(5,
            getUserClassMapping(mysqlDialect), mysqlDialect);
        assertEquals(t.get0(),
            "INSERT INTO `user` (`id`, `name`, `descp`, `password`) VALUES (?, ?, ?, ?),(?, ?, ?, ?),(?, ?, ?, ?),(?, ?, ?, ?),(?, ?, ?, ?) ON DUPLICATE KEY UPDATE `name`=values(`name`), `descp`=values(`descp`), `password`=values(`password`)");
        assertEquals(t.get1().get(1).getPropertyFullName(), "id");
        assertEquals(t.get1().get(2).getPropertyFullName(), "name");
        assertEquals(t.get1().get(3).getPropertyFullName(), "descp");
    }

    @Test
    void testUpsertBatchPostgresql() {
        Tuple2<String, Map<Integer, JdbcPropertyMapping>> t = ClassMappingUtils.getUpsertBatchSqlAndParamPositions(5,
            getUserClassMapping(postgresqlDialect), postgresqlDialect);
        assertEquals(t.get0(),
            "INSERT INTO \"user\" (\"id\", \"name\", \"descp\", \"password\") VALUES (?, ?, ?, ?),(?, ?, ?, ?),(?, ?, ?, ?),(?, ?, ?, ?),(?, ?, ?, ?) ON CONFLICT (id) DO UPDATE SET \"name\"=EXCLUDED.\"name\", \"descp\"=EXCLUDED.\"descp\", \"password\"=EXCLUDED.\"password\"");
        assertEquals(t.get1().get(1).getPropertyFullName(), "id");
        assertEquals(t.get1().get(2).getPropertyFullName(), "name");
        assertEquals(t.get1().get(3).getPropertyFullName(), "descp");
    }

    @Test
    void testUpsertBatchSqlite() {
        Tuple2<String, Map<Integer, JdbcPropertyMapping>> t = ClassMappingUtils.getUpsertBatchSqlAndParamPositions(5,
            getUserClassMapping(sqliteDialect), sqliteDialect);
        //        assertEquals(t.get0(),
        //                "INSERT INTO `user` VALUES (`id`, `name`, `descp`, `password`) SELECT ?, ?, ?, ? UNION SELECT ?, ?, ?, ? UNION SELECT ?, ?, ?, ? UNION SELECT ?, ?, ?, ? UNION SELECT ?, ?, ?, ? ON CONFLICT (id) DO UPDATE SET `name`=EXCLUDED.`name`, `descp`=EXCLUDED.`descp`, `password`=EXCLUDED.`password`");
        assertEquals(t.get0(),
            "INSERT INTO `user` SELECT ? AS `id`, ? AS `name`, ? AS `descp`, ? AS `password` UNION SELECT ?, ?, ?, ? UNION SELECT ?, ?, ?, ? UNION SELECT ?, ?, ?, ? UNION SELECT ?, ?, ?, ? ON CONFLICT (id) DO UPDATE SET `name`=EXCLUDED.`name`, `descp`=EXCLUDED.`descp`, `password`=EXCLUDED.`password`");
        assertEquals(t.get1().get(1).getPropertyFullName(), "id");
        assertEquals(t.get1().get(2).getPropertyFullName(), "name");
        assertEquals(t.get1().get(3).getPropertyFullName(), "descp");
    }
}
