
package cn.featherfly.common.db.migration;

import java.util.HashSet;
import java.util.Set;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import cn.featherfly.common.db.JdbcTestBase;
import cn.featherfly.common.db.mapping.ObjectToDbMappingFactory;
import cn.featherfly.common.db.mapping.SqlTypeMappingManager;
import cn.featherfly.common.db.mapping.pojo.Entity;
import cn.featherfly.common.db.mapping.pojo.Entity2;
import cn.featherfly.common.db.metadata.DatabaseMetadata;
import cn.featherfly.common.db.metadata.DatabaseMetadataManager;
import cn.featherfly.common.db.migration.Migrator.ModifyType;
import cn.featherfly.common.repository.mapping.ClassMapping;

/**
 * <p>
 * MigratorTest
 * </p>
 *
 * @author zhongj
 */
public class MigratorTest extends JdbcTestBase {

    private Migrator migrator;

    private ObjectToDbMappingFactory factory;

    private SqlTypeMappingManager sqlTypeMappingManager;

    @BeforeClass
    public void init() {
        sqlTypeMappingManager = new SqlTypeMappingManager();
        DatabaseMetadata metadata = DatabaseMetadataManager.getDefaultManager().create(dataSource);
        factory = new ObjectToDbMappingFactory(metadata, dialect, sqlTypeMappingManager);
        migrator = new Migrator(dataSource, dialect, sqlTypeMappingManager);
        factory.setCheckMapping(false);
        sqlTypeMappingManager.setEnumWithOrdinal(true);
    }

    @Test
    public void testInitSql() {
        System.out.println(migrator.initSql(classMappings()));
    }

    @Test
    public void testInitCreate() {
        migrator.create(classMappings());
    }

    @Test
    public void testUpdateSql2() {
        Set<ClassMapping<?>> mappings = classMappings2();
        sqlTypeMappingManager.setEnumWithOrdinal(false);
        System.out.println("migrator.updateSql(classMappings(), ModifyType.MODIFY, true, ModifyType.MODIFY, true)");
        System.err.println(migrator.updateSql(mappings, ModifyType.MODIFY, true, ModifyType.MODIFY, true));
        System.out.println("******************************************");
        System.out.println("migrator.updateSql(classMappings(), ModifyType.MODIFY, false, ModifyType.MODIFY, false)");
        System.out.println("******************************************");
        System.err.println(migrator.updateSql(mappings, ModifyType.MODIFY, false, ModifyType.MODIFY, false));
        System.out.println("******************************************");
        System.out.println(
                "migrator.updateSql(classMappings(), ModifyType.DROP_AND_CREATE, true,ModifyType.DROP_AND_CREATE, true)");
        System.out.println("******************************************");
        System.err.println(
                migrator.updateSql(mappings, ModifyType.DROP_AND_CREATE, true, ModifyType.DROP_AND_CREATE, true));
        System.out.println("******************************************");
        System.out.println(
                "migrator.updateSql(classMappings(), ModifyType.DROP_AND_CREATE, false, ModifyType.DROP_AND_CREATE, false)");
        System.out.println("******************************************");
        System.err.println(
                migrator.updateSql(mappings, ModifyType.DROP_AND_CREATE, false, ModifyType.DROP_AND_CREATE, false));
    }

    private Set<ClassMapping<?>> classMappings() {
        Set<ClassMapping<?>> set = new HashSet<>();
        set.add(factory.getClassMapping(Entity.class));
        //        set.add(factory.getClassMapping(User.class));
        //        set.add(factory.getClassMapping(UserRole.class));
        return set;
    }

    private Set<ClassMapping<?>> classMappings2() {
        Set<ClassMapping<?>> set = new HashSet<>();
        set.add(factory.getClassMapping(Entity2.class));
        return set;
    }
}
