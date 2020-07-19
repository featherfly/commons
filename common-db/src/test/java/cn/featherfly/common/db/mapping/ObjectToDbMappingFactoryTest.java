
package cn.featherfly.common.db.mapping;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import cn.featherfly.common.db.JdbcTestBase;
import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.db.dialect.Dialects;
import cn.featherfly.common.db.metadata.DatabaseMetadata;
import cn.featherfly.common.db.metadata.DatabaseMetadataManager;
import cn.featherfly.common.repository.mapping.ClassMapping;
import cn.featherfly.common.repository.mapping.MappingException;

/**
 * <p>
 * JdbcMappingFactoryTest
 * </p>
 *
 * @author zhongj
 */
public class ObjectToDbMappingFactoryTest extends JdbcTestBase {

    private ObjectToDbMappingFactory factory;

    private Dialect dialect = Dialects.MYSQL;

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

    @Test(expectedExceptions = MappingException.class)
    public void test2() {
        ClassMapping<User> mapping = factory.getClassMapping(User.class);
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
