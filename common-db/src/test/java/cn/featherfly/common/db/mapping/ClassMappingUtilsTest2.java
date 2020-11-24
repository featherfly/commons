
package cn.featherfly.common.db.mapping;

import static org.testng.Assert.assertEquals;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.speedment.common.tuple.Tuple2;

import cn.featherfly.common.db.JdbcTestBase;
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

    @BeforeClass
    public void setup() {
        factory = new ObjectDbMixedMappingFactory(DatabaseMetadataManager.getDefaultManager().create(dataSource),
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

}
