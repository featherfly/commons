
package cn.featherfly.common.db.mapping;

import static org.testng.Assert.assertEquals;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.speedment.common.tuple.Tuple2;

import cn.featherfly.common.bean.AsmPropertyAccessorFactory;
import cn.featherfly.common.db.JdbcTestBase;
import cn.featherfly.common.db.mapping.pojo.UserInfo;
import cn.featherfly.common.db.mapping.pojo.UserInfo2;
import cn.featherfly.common.db.mapping.pojo.UserRole2;
import cn.featherfly.common.db.metadata.DatabaseMetadataManager;

/**
 * ClassMappingUtilsTest.
 *
 * @author zhongj
 */
public class ClassMappingUtilsTest2 extends JdbcTestBase {

    JdbcMappingFactory factory;
    JdbcMappingFactory factory2;

    @BeforeClass
    public void setup() {
        factory = new CompatibleJdbcMappingFactory(DatabaseMetadataManager.getDefaultManager().create(dataSource),
            dialect, new AsmPropertyAccessorFactory(Thread.currentThread().getContextClassLoader()));
        factory2 = new StrictJdbcMappingFactory(DatabaseMetadataManager.getDefaultManager().create(dataSource), dialect,
            new AsmPropertyAccessorFactory(Thread.currentThread().getContextClassLoader()));
    }

    @Test
    void testDeleteMulitiPk() {
        Tuple2<String, Map<Integer, JdbcPropertyMapping>> t = null;

        t = ClassMappingUtils.getDeleteSqlAndParamPositions(factory.getClassMapping(UserRole2.class), dialect);
        System.out.println(t.get0());
        System.out.println(t.get1());
        assertEquals(t.get0(), "DELETE FROM `user_role` WHERE `user_id` = ? AND `role_id` = ?");
        assertEquals(t.get1().get(1).getPropertyFullName(), "user.id");
        assertEquals(t.get1().get(2).getPropertyFullName(), "role.id");
    }

    @Test
    void testSelectWithManyToOne() {
        String result = "`id` `id`, `user_id` `user.id`, `name` `name`";
        String selectSql = ClassMappingUtils.getSelectColumnsSql(factory.getClassMapping(UserInfo.class), null,
            dialect);
        System.out.println(selectSql);

        assertEquals(selectSql, result);

        String selectSql2 = ClassMappingUtils.getSelectColumnsSql(factory2.getClassMapping(UserInfo.class), null,
            dialect);
        System.out.println(selectSql2);

        String result2 = "`id` `id`, `name` `name`, `user_id` `user.id`";
        assertEquals(selectSql2, result2);
    }

    @Test
    void testSelectWithManyToOne2() {
        String selectSql = ClassMappingUtils.getSelectColumnsSql(factory.getClassMapping(UserInfo2.class), null,
            dialect);
        System.out.println(selectSql);

        assertEquals(selectSql, "`ID` `id`, `USER` `user.id`, `name` `name`");

        String selectSql2 = ClassMappingUtils.getSelectColumnsSql(factory2.getClassMapping(UserInfo2.class), null,
            dialect);
        System.out.println(selectSql2);

        assertEquals(selectSql2, "`id` `id`, `name` `name`, `user` `user.id`");

    }

}
