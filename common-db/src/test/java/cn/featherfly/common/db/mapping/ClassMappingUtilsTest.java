
package cn.featherfly.common.db.mapping;

import static org.testng.Assert.assertEquals;

import java.util.Map;

import org.testng.annotations.Test;

import com.speedment.common.tuple.Tuple2;
import com.speedment.common.tuple.Tuple3;

import cn.featherfly.common.db.ClassMappingSupport;
import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.db.dialect.Dialects;
import cn.featherfly.common.db.mapping.pojo.User;
import cn.featherfly.common.db.mapping.pojo.UserRole;

/**
 * <p>
 * ClassMappingUtilsTest
 * </p>
 *
 * @author zhongj
 */
public class ClassMappingUtilsTest implements ClassMappingSupport {

    Dialect dialect = Dialects.MYSQL;

    @Test
    void testSelectSql() {

        String sql = ClassMappingUtils.getSelectSql(getUserClassMapping(), dialect);

        System.out.println(sql);

        assertEquals(sql, "SELECT `id` `id`, `name` `name`, `descp` `descp`, `password` `pwd` FROM `user`");
    }

    @Test
    void testSelectByIdSql() {
        String sql = ClassMappingUtils.getSelectByPkSql(getUserClassMapping(), dialect);

        System.out.println(sql);
        assertEquals(sql,
                "SELECT `id` `id`, `name` `name`, `descp` `descp`, `password` `pwd` FROM `user` WHERE `id` = ?");

        sql = ClassMappingUtils.getSelectByPkSql(getUserRoleClassMapping(), dialect);

        System.out.println(sql);
        assertEquals(sql,
                "SELECT `user_id` `userId`, `role_id` `roleId`, `descp` `descp`, `descp2` `descp2` FROM `user_role` WHERE `user_id` = ? AND `role_id` = ?");

    }

    @Test
    void testInsert() {
        Tuple2<String, Map<Integer, String>> t = ClassMappingUtils.getInsertSqlAndParamPositions(getUserClassMapping(),
                dialect);
        System.out.println(t.get0());
        System.out.println(t.get1());

        assertEquals(t.get0(), "INSERT INTO `user` (`id`,`name`,`descp`,`password`) VALUES (?,?,?,?)");
    }

    @Test
    void testUpdate() {
        Tuple2<String, Map<Integer, String>> t = ClassMappingUtils.getUpdateSqlAndParamPositions(getUserClassMapping(),
                dialect);
        System.out.println(t.get0());
        System.out.println(t.get1());

        assertEquals(t.get0(), "UPDATE `user` SET `name` = ?, `descp` = ?, `password` = ? WHERE `id` = ?");

        t = ClassMappingUtils.getUpdateSqlAndParamPositions(getUserRoleClassMapping(), dialect);
        System.out.println(t.get0());
        System.out.println(t.get1());
        assertEquals(t.get0(),
                "UPDATE `user_role` SET `descp` = ?, `descp2` = ? WHERE `user_id` = ? AND `role_id` = ?");
    }

    @Test
    void testDelete() {
        Tuple2<String, Map<Integer, String>> t = ClassMappingUtils.getDeleteSqlAndParamPositions(getUserClassMapping(),
                dialect);
        System.out.println(t.get0());
        System.out.println(t.get1());

        assertEquals(t.get0(), "DELETE FROM `user` WHERE `id` = ?");
        assertEquals(t.get1().get(1), "id");

        t = ClassMappingUtils.getDeleteSqlAndParamPositions(getUserRoleClassMapping(), dialect);
        System.out.println(t.get0());
        System.out.println(t.get1());
        assertEquals(t.get0(), "DELETE FROM `user_role` WHERE `user_id` = ? AND `role_id` = ?");
        assertEquals(t.get1().get(1), "userId");
        assertEquals(t.get1().get(2), "roleId");
    }

    @Test
    void testDeleteBatch() {
        Tuple2<String, Map<Integer, String>> t = null;
        t = ClassMappingUtils.getDeleteSqlAndParamPositions(3, getUserClassMapping(), dialect);
        System.out.println(t.get0());
        System.out.println(t.get1());

        assertEquals(t.get0(), "DELETE FROM `user` WHERE `id` IN (?,?,?)");

        t = ClassMappingUtils.getDeleteSqlAndParamPositions(2, getUserRoleClassMapping(), dialect);
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
        Tuple3<String, Map<Integer, String>, Integer> t = ClassMappingUtils.getMergeSqlAndParamPositions(user,
                getUserClassMapping(), false, dialect);
        System.out.println(t.get0());
        System.out.println(t.get1());

        assertEquals(t.get0(), "UPDATE `user` SET `name` = ? WHERE `id` = ?");

        user.setDescp("descp");
        t = ClassMappingUtils.getMergeSqlAndParamPositions(user, getUserClassMapping(), true, dialect);
        System.out.println(t.get0());
        System.out.println(t.get1());

        assertEquals(t.get0(), "UPDATE `user` SET `name` = ?, `descp` = ? WHERE `id` = ?");
        UserRole ur = new UserRole();
        ur.setUserId(1);
        ur.setRoleId(1);
        ur.setDescp("d");
        t = ClassMappingUtils.getMergeSqlAndParamPositions(ur, getUserRoleClassMapping(), true, dialect);
        System.out.println(t.get0());
        System.out.println(t.get1());

        assertEquals(t.get0(), "UPDATE `user_role` SET `descp` = ? WHERE `user_id` = ? AND `role_id` = ?");

        ur.setDescp(null);
        ur.setDescp2("d2");
        t = ClassMappingUtils.getMergeSqlAndParamPositions(ur, getUserRoleClassMapping(), true, dialect);
        System.out.println(t.get0());
        System.out.println(t.get1());

        assertEquals(t.get0(), "UPDATE `user_role` SET `descp2` = ? WHERE `user_id` = ? AND `role_id` = ?");

        ur.setDescp("d");
        ur.setDescp2("d2");
        t = ClassMappingUtils.getMergeSqlAndParamPositions(ur, getUserRoleClassMapping(), true, dialect);
        System.out.println(t.get0());
        System.out.println(t.get1());

        assertEquals(t.get0(),
                "UPDATE `user_role` SET `descp` = ?, `descp2` = ? WHERE `user_id` = ? AND `role_id` = ?");
    }

    @Test
    void testMergeNoColumnChange() {
        User user = new User();
        user.setId(12L);
        Tuple3<String, Map<Integer, String>, Integer> t = ClassMappingUtils.getMergeSqlAndParamPositions(user,
                getUserClassMapping(), false, dialect);
        System.out.println(t.get0());
        System.out.println(t.get1());

        assertEquals(t.get2(), new Integer(0));
        assertEquals(t.get0(), "UPDATE `user` WHERE `id` = ?");
    }

    @Test
    void testInsertBatchMysql() {
        Tuple2<String, Map<Integer, String>> t = ClassMappingUtils.getInsertBatchSqlAndParamPositions(5,
                getUserClassMapping(), dialect);

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
        assertEquals(t.get1().get(1), "id");
        assertEquals(t.get1().get(2), "name");
        assertEquals(t.get1().get(3), "descp");
    }

    @Test
    void testInsertBatchMysql2() {
        Tuple2<String, Map<Integer, String>> t = ClassMappingUtils.getInsertBatchSqlAndParamPositions(5,
                getUserRoleClassMapping(), dialect);

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
        assertEquals(t.get1().get(1), "userId");
        assertEquals(t.get1().get(2), "roleId");
        assertEquals(t.get1().get(3), "descp");
        assertEquals(t.get1().get(4), "descp2");
    }

    @Test
    void testInsertBatchSqlite() {
        int size = 5;
        Tuple2<String, Map<Integer, String>> t = ClassMappingUtils.getInsertBatchSqlAndParamPositions(size,
                getUserClassMapping(), Dialects.SQLITE);

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
                "INSERT INTO `user` VALUES (`id`,`name`,`descp`,`password`) SELECT ?, ?, ?, ? UNION SELECT ?, ?, ?, ? UNION SELECT ?, ?, ?, ? UNION SELECT ?, ?, ?, ? UNION SELECT ?, ?, ?, ?");
        assertEquals(t.get1().get(1), "id");
        assertEquals(t.get1().get(2), "name");
        assertEquals(t.get1().get(3), "descp");
    }

    @Test
    void testUpsertMysql() {
        Tuple2<String, Map<Integer, String>> t = ClassMappingUtils.getUpsertSqlAndParamPositions(getUserClassMapping(),
                Dialects.MYSQL);
        System.out.println(t.get0());
        System.out.println(t.get1());

        assertEquals(t.get0(),
                "INSERT INTO `user` (`id`, `name`, `descp`, `password`) VALUES (?, ?, ?, ?) ON DUPLICATE KEY UPDATE `name`=values(`name`), `descp`=values(`descp`), `password`=values(`password`)");
    }

    @Test
    void testUpsertPostgresql() {
        Tuple2<String, Map<Integer, String>> t = ClassMappingUtils.getUpsertSqlAndParamPositions(getUserClassMapping(),
                Dialects.POSTGRESQL);
        System.out.println(t.get0());
        System.out.println(t.get1());

        assertEquals(t.get0(),
                "INSERT INTO \"user\" (\"id\", \"name\", \"descp\", \"password\") VALUES (?, ?, ?, ?) ON CONFLICT (id) DO UPDATE SET \"name\"=EXCLUDED.\"name\", \"descp\"=EXCLUDED.\"descp\", \"password\"=EXCLUDED.\"password\"");
    }

    @Test
    void testUpsertSqlite() {
        Tuple2<String, Map<Integer, String>> t = ClassMappingUtils.getUpsertSqlAndParamPositions(getUserClassMapping(),
                Dialects.SQLITE);
        System.out.println(t.get0());
        System.out.println(t.get1());

        assertEquals(t.get0(),
                "INSERT INTO `user` (`id`, `name`, `descp`, `password`) VALUES (?, ?, ?, ?) ON CONFLICT (id) DO UPDATE SET `name`=EXCLUDED.`name`, `descp`=EXCLUDED.`descp`, `password`=EXCLUDED.`password`");
    }

    @Test
    void testUpsertBatchMysql() {
        Tuple2<String, Map<Integer, String>> t = ClassMappingUtils.getUpsertBatchSqlAndParamPositions(5,
                getUserClassMapping(), dialect);
        assertEquals(t.get0(),
                "INSERT INTO `user` (`id`, `name`, `descp`, `password`) VALUES (?, ?, ?, ?),(?, ?, ?, ?),(?, ?, ?, ?),(?, ?, ?, ?),(?, ?, ?, ?) ON DUPLICATE KEY UPDATE `name`=values(`name`), `descp`=values(`descp`), `password`=values(`password`)");
        assertEquals(t.get1().get(1), "id");
        assertEquals(t.get1().get(2), "name");
        assertEquals(t.get1().get(3), "descp");
    }

    @Test
    void testUpsertBatchPostgresql() {
        Tuple2<String, Map<Integer, String>> t = ClassMappingUtils.getUpsertBatchSqlAndParamPositions(5,
                getUserClassMapping(), Dialects.POSTGRESQL);
        assertEquals(t.get0(),
                "INSERT INTO \"user\" (\"id\", \"name\", \"descp\", \"password\") VALUES (?, ?, ?, ?),(?, ?, ?, ?),(?, ?, ?, ?),(?, ?, ?, ?),(?, ?, ?, ?) ON CONFLICT (id) DO UPDATE SET \"name\"=EXCLUDED.\"name\", \"descp\"=EXCLUDED.\"descp\", \"password\"=EXCLUDED.\"password\"");
        assertEquals(t.get1().get(1), "id");
        assertEquals(t.get1().get(2), "name");
        assertEquals(t.get1().get(3), "descp");
    }

    @Test
    void testUpsertBatchSqlite() {
        Tuple2<String, Map<Integer, String>> t = ClassMappingUtils.getUpsertBatchSqlAndParamPositions(5,
                getUserClassMapping(), Dialects.SQLITE);
        assertEquals(t.get0(),
                "INSERT INTO `user` VALUES (`id`,`name`,`descp`,`password`) SELECT ?, ?, ?, ? UNION SELECT ?, ?, ?, ? UNION SELECT ?, ?, ?, ? UNION SELECT ?, ?, ?, ? UNION SELECT ?, ?, ?, ? ON CONFLICT (id) DO UPDATE SET `name`=EXCLUDED.`name`, `descp`=EXCLUDED.`descp`, `password`=EXCLUDED.`password`");
        assertEquals(t.get1().get(1), "id");
        assertEquals(t.get1().get(2), "name");
        assertEquals(t.get1().get(3), "descp");
    }
}
