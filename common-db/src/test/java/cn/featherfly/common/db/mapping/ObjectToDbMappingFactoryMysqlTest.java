
package cn.featherfly.common.db.mapping;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import cn.featherfly.common.db.JdbcTestBase;
import cn.featherfly.common.db.mapping.pojo.Entity;
import cn.featherfly.common.db.mapping.pojo.Role;
import cn.featherfly.common.db.mapping.pojo.User2;
import cn.featherfly.common.db.metadata.DatabaseMetadata;
import cn.featherfly.common.db.metadata.DatabaseMetadataManager;
import cn.featherfly.common.repository.mapping.ClassMapping;

/**
 * <p>
 * JdbcMappingFactoryTest
 * </p>
 *
 * @author zhongj
 */
//@Test(groups = "mysql")
public class ObjectToDbMappingFactoryMysqlTest extends JdbcTestBase {

    private ObjectToDbMappingFactory factory;

    private SqlTypeMappingManager sqlTypeMappingManager = new SqlTypeMappingManager();

    @BeforeClass
    public void init() {
        DatabaseMetadata metadata = DatabaseMetadataManager.getDefaultManager().create(dataSource);
        factory = new ObjectToDbMappingFactory(metadata, dialect, sqlTypeMappingManager);
    }

    @Test
    public void test1() {
        ClassMapping<Role> mapping = factory.getClassMapping(Role.class);
        System.out.println(ClassMappingUtils.getCreateTableSql(mapping, dialect, sqlTypeMappingManager));
    }

    @Test(expectedExceptions = JdbcMappingException.class)
    public void test2() {
        ClassMapping<User2> mapping = factory.getClassMapping(User2.class);
        System.out.println(ClassMappingUtils.getCreateTableSql(mapping, dialect, sqlTypeMappingManager));
    }

    @Test
    public void test3() {
        factory.setCheckMapping(false);
        ClassMapping<Entity> mapping = factory.getClassMapping(Entity.class);
        System.out.println(ClassMappingUtils.getCreateTableSql(mapping, dialect, sqlTypeMappingManager));

        sqlTypeMappingManager.setEnumWithOrdinal(true);

        System.out.println(ClassMappingUtils.getCreateTableSql(mapping, dialect, sqlTypeMappingManager));
    }
}
