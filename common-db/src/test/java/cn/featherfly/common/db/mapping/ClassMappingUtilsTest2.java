
package cn.featherfly.common.db.mapping;

import static org.testng.Assert.assertEquals;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.speedment.common.tuple.Tuple2;

import cn.featherfly.common.db.JdbcTestBase;
import cn.featherfly.common.db.mapping.pojo.UserInfo;
import cn.featherfly.common.db.mapping.pojo.UserInfo2;
import cn.featherfly.common.db.mapping.pojo.UserRole2;
import cn.featherfly.common.db.metadata.DatabaseMetadataManager;

/**
 * <p>
 * ClassMappingUtilsTest
 * </p>
 *
 * @author zhongj
 */
public class ClassMappingUtilsTest2 extends JdbcTestBase {

    JdbcMappingFactory factory;
    JdbcMappingFactory factory2;

    @BeforeClass
    public void setup() {
        factory = new ObjectDbMixedMappingFactory(DatabaseMetadataManager.getDefaultManager().create(dataSource),
                dialect);
        factory2 = new ObjectToDbMappingFactory(DatabaseMetadataManager.getDefaultManager().create(dataSource),
                dialect);
    }

    @Test
    void testDeleteMulitiPk() {
        Tuple2<String, Map<Integer, String>> t = null;

        t = ClassMappingUtils.getDeleteSqlAndParamPositions(factory.getClassMapping(UserRole2.class), dialect);
        System.out.println(t.get0());
        System.out.println(t.get1());
        assertEquals(t.get0(), "DELETE FROM `user_role` WHERE `user_id` = ? AND `role_id` = ?");
        assertEquals(t.get1().get(1), "user.id");
        assertEquals(t.get1().get(2), "role.id");
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
