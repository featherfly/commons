
package cn.featherfly.common.db.mapping;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import cn.featherfly.common.bean.AsmPropertyAccessorFactory;
import cn.featherfly.common.db.JdbcTestBase;
import cn.featherfly.common.db.mapping.pojo.Entity;
import cn.featherfly.common.db.mapping.pojo.Role;
import cn.featherfly.common.db.mapping.pojo.User2;
import cn.featherfly.common.db.metadata.DatabaseMetadata;
import cn.featherfly.common.db.metadata.DatabaseMetadataManager;
import cn.featherfly.common.repository.mapping.MappingException;

/**
 * <p>
 * JdbcMappingFactoryTest
 * </p>
 *
 * @author zhongj
 */
@Test(groups = "postgresql")
public class ObjectToDbMappingFactoryPostgresqlTest extends JdbcTestBase {

    private StrictJdbcMappingFactory factory;

    private SqlTypeMappingManager sqlTypeMappingManager = new SqlTypeMappingManager();

    @BeforeClass
    public void init() {
        DatabaseMetadata metadata = DatabaseMetadataManager.getDefaultManager().create(dataSource);
        factory = new StrictJdbcMappingFactory(metadata, dialect, sqlTypeMappingManager,
            new AsmPropertyAccessorFactory(Thread.currentThread().getContextClassLoader()));
    }

    @Test
    public void test1() {
        JdbcClassMapping<Role> mapping = factory.getClassMapping(Role.class);
        System.out.println(ClassMappingUtils.getCreateTableSql(mapping, dialect, sqlTypeMappingManager));
    }

    @Test(expectedExceptions = MappingException.class)
    public void test2() {
        JdbcClassMapping<User2> mapping = factory.getClassMapping(User2.class);
        System.out.println(ClassMappingUtils.getCreateTableSql(mapping, dialect, sqlTypeMappingManager));
    }

    @Test
    public void test3() {
        factory.setCheckMapping(false);
        JdbcClassMapping<Entity> mapping = factory.getClassMapping(Entity.class);
        System.out.println(ClassMappingUtils.getCreateTableSql(mapping, dialect, sqlTypeMappingManager));

        sqlTypeMappingManager.setEnumWithOrdinal(true);

        System.out.println(ClassMappingUtils.getCreateTableSql(mapping, dialect, sqlTypeMappingManager));
    }
}
