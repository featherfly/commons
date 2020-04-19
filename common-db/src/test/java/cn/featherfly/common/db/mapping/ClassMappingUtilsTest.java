
package cn.featherfly.common.db.mapping;

import static org.testng.Assert.assertEquals;

import java.util.Map;
import java.util.Map.Entry;

import org.testng.annotations.Test;

import com.speedment.common.tuple.Tuple2;

import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.db.dialect.Dialects;
import cn.featherfly.common.repository.mapping.ClassMapping;
import cn.featherfly.common.repository.mapping.PropertyMapping;

/**
 * <p>
 * ClassMappingUtilsTest
 * </p>
 *
 * @author zhongj
 */
public class ClassMappingUtilsTest {

    Dialect dialect = Dialects.MYSQL;

    @Test
    void testSelectSql() {

        String sql = ClassMappingUtils.getSelectSql(getUserClassMapping(), dialect);

        System.out.println(sql);

        assertEquals(sql, "SELECT `name` `name`, `id` `id`, `descp` `descp` FROM `user`");
    }

    @Test
    void testSelectByIdSql() {
        String sql = ClassMappingUtils.getSelectByIdSql(getUserClassMapping(), dialect);

        System.out.println(sql);
        assertEquals(sql, "SELECT `name` `name`, `id` `id`, `descp` `descp` FROM `user` WHERE `id` = ?");

        sql = ClassMappingUtils.getSelectByIdSql(getUserRoleClassMapping(), dialect);

        System.out.println(sql);
        assertEquals(sql,
                "SELECT `descp2` `descp2`, `user_id` `userId`, `role_id` `roleId`, `descp` `descp` FROM `user_role` WHERE `user_id` = ? AND `role_id` = ?");
    }

    @Test
    void testInsert() {
        Tuple2<String, Map<Integer, String>> t = ClassMappingUtils.getInsertSqlAndParamPositions(getUserClassMapping(),
                dialect);
        System.out.println(t.get0());
        System.out.println(t.get1());

        assertEquals(t.get0(), "INSERT INTO `user` (`name`,`id`,`descp`) VALUES (?,?,?)");
    }

    @Test
    void testUpdate() {
        Tuple2<String, Map<Integer, String>> t = ClassMappingUtils.getUpdateSqlAndParamPositions(getUserClassMapping(),
                dialect);
        System.out.println(t.get0());
        System.out.println(t.get1());

        assertEquals(t.get0(), "UPDATE `user` SET `name` = ?, `descp` = ? WHERE `id` = ?");

        t = ClassMappingUtils.getUpdateSqlAndParamPositions(getUserRoleClassMapping(), dialect);
        System.out.println(t.get0());
        System.out.println(t.get1());
        assertEquals(t.get0(),
                "UPDATE `user_role` SET `descp2` = ?, `descp` = ? WHERE `user_id` = ? AND `role_id` = ?");

    }

    @Test
    void testDelete() {
        Tuple2<String, Map<Integer, String>> t = ClassMappingUtils.getDeleteSqlAndParamPositions(getUserClassMapping(),
                dialect);
        System.out.println(t.get0());
        System.out.println(t.get1());

        assertEquals(t.get0(), "DELETE FROM `user` WHERE `id` = ?");

        t = ClassMappingUtils.getDeleteSqlAndParamPositions(getUserRoleClassMapping(), dialect);
        System.out.println(t.get0());
        System.out.println(t.get1());
        assertEquals(t.get0(), "DELETE FROM `user_role` WHERE `user_id` = ? AND `role_id` = ?");
    }

    @Test
    void testMerge() {
        User user = new User();
        user.setId(12L);
        user.setName("yufei");
        Tuple2<String, Map<Integer, String>> t = ClassMappingUtils.getMergeSqlAndParamPositions(user,
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
                "UPDATE `user_role` SET `descp2` = ?, `descp` = ? WHERE `user_id` = ? AND `role_id` = ?");

    }

    @Test
    void testInsertBatchMysql() {
        Tuple2<String, Map<Integer, String>> t = ClassMappingUtils.getInsertBatchSqlAndParamPositions(5,
                getUserClassMapping(), dialect);

        System.out.println(t.get0());
        System.out.println(t.get1());

        for (int i = 0; i < 5; i++) {
            int len = t.get1().size();
            System.out.println("第" + i + "对象");
            for (Entry<Integer, String> e : t.get1().entrySet()) {
                int position = e.getKey() + i * len;
                System.out.println(e.getValue());
                System.out.println(position);
            }
        }
    }

    @Test
    void testInsertBatchSqlite() {
        int size = 5;
        Tuple2<String, Map<Integer, String>> t = ClassMappingUtils.getInsertBatchSqlAndParamPositions(size,
                getUserClassMapping(), Dialects.SQLITE);

        System.out.println(t.get0());
        System.out.println(t.get1());

        for (int i = 0; i < 5; i++) {
            int len = t.get1().size();
            System.out.println("第" + i + "对象");
            for (Entry<Integer, String> e : t.get1().entrySet()) {
                int position = e.getKey() + i * len;
                System.out.println(e.getValue());
                System.out.println(position);
            }
        }

    }

    ClassMapping<User> getUserClassMapping() {
        ClassMapping<User> mapping = new ClassMapping<>(User.class, "user");
        PropertyMapping pm = new PropertyMapping();
        pm.setPrimaryKey(true);
        pm.setRepositoryFieldName("id");
        pm.setPropertyName("id");
        pm.setPropertyType(Long.class);

        PropertyMapping pm2 = new PropertyMapping();
        pm2.setPrimaryKey(false);
        pm2.setRepositoryFieldName("name");
        pm2.setPropertyName("name");
        pm2.setPropertyType(String.class);

        PropertyMapping pm3 = new PropertyMapping();
        pm3.setPrimaryKey(false);
        pm3.setRepositoryFieldName("descp");
        pm3.setPropertyName("descp");
        pm3.setPropertyType(String.class);

        mapping.addPropertyMapping(pm);
        mapping.addPropertyMapping(pm2);
        mapping.addPropertyMapping(pm3);

        return mapping;
    }

    ClassMapping<UserRole> getUserRoleClassMapping() {
        ClassMapping<UserRole> mapping = new ClassMapping<>(UserRole.class, "user_role");
        PropertyMapping pm = new PropertyMapping();
        pm.setPrimaryKey(true);
        pm.setRepositoryFieldName("user_id");
        pm.setPropertyName("userId");
        pm.setPropertyType(Long.class);

        PropertyMapping pm2 = new PropertyMapping();
        pm2.setPrimaryKey(true);
        pm2.setRepositoryFieldName("role_id");
        pm2.setPropertyName("roleId");
        pm2.setPropertyType(Long.class);

        PropertyMapping pm3 = new PropertyMapping();
        pm3.setPrimaryKey(false);
        pm3.setRepositoryFieldName("descp");
        pm3.setPropertyName("descp");
        pm3.setPropertyType(String.class);

        PropertyMapping pm4 = new PropertyMapping();
        pm4.setPrimaryKey(false);
        pm4.setRepositoryFieldName("descp2");
        pm4.setPropertyName("descp2");
        pm4.setPropertyType(String.class);

        mapping.addPropertyMapping(pm);
        mapping.addPropertyMapping(pm2);
        mapping.addPropertyMapping(pm3);
        mapping.addPropertyMapping(pm4);

        return mapping;
    }
}
