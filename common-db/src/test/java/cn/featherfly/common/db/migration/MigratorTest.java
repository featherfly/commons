
package cn.featherfly.common.db.migration;

import java.util.HashSet;
import java.util.Set;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import cn.featherfly.common.db.JdbcTestBase;
import cn.featherfly.common.db.mapping.StrictJdbcMappingFactory;
import cn.featherfly.common.db.mapping.SqlTypeMappingManager;
import cn.featherfly.common.db.mapping.pojo.Article3;
import cn.featherfly.common.db.mapping.pojo.Entity;
import cn.featherfly.common.db.mapping.pojo.Entity2;
import cn.featherfly.common.db.mapping.pojo.Entity3;
import cn.featherfly.common.db.mapping.pojo.UserRole2;
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

    private StrictJdbcMappingFactory factory;

    private SqlTypeMappingManager sqlTypeMappingManager;

    @BeforeClass
    public void init() {
        sqlTypeMappingManager = new SqlTypeMappingManager();
        DatabaseMetadata metadata = DatabaseMetadataManager.getDefaultManager().create(dataSource);
        factory = new StrictJdbcMappingFactory(metadata, dialect, sqlTypeMappingManager);
        migrator = new Migrator(dataSource, dialect, sqlTypeMappingManager);
        factory.setCheckMapping(false);
        sqlTypeMappingManager.setEnumWithOrdinal(true);
    }

    @Test
    public void testInitSql2() {
        Set<ClassMapping<?>> mappings = new HashSet<>();
        mappings.add(factory.getClassMapping(Entity3.class));
        System.err.println(migrator.initSql(mappings));
    }

    @Test
    public void testInitSql() {
        System.out.println(migrator.initSql(classMappings()));
    }

    @Test
    public void testInitSqlUserRole2() {
        System.out.println(migrator.initSql(classMappingsUserRole()));
    }

    @Test
    public void testInitSqlArticle2() {
        System.out.println(migrator.initSql(classMappingsArticle2()));
    }

    @Test
    public void testInitSqlArticle3() {
        System.out.println(migrator.initSql(classMappingsArticle3()));
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
        System.err.println(migrator.updateSql(mappings, ModifyType.MODIFY, true, ModifyType.MODIFY, true, true));
        System.out.println("******************************************");
        System.out.println("migrator.updateSql(classMappings(), ModifyType.MODIFY, false, ModifyType.MODIFY, false)");
        System.out.println("******************************************");
        System.err.println(migrator.updateSql(mappings, ModifyType.MODIFY, false, ModifyType.MODIFY, false, false));
        System.out.println("******************************************");
        System.out.println(
                "migrator.updateSql(classMappings(), ModifyType.DROP_AND_CREATE, true,ModifyType.DROP_AND_CREATE, true)");
        System.out.println("******************************************");
        System.err.println(
                migrator.updateSql(mappings, ModifyType.DROP_AND_CREATE, true, ModifyType.DROP_AND_CREATE, true, true));
        System.out.println("******************************************");
        System.out.println(
                "migrator.updateSql(classMappings(), ModifyType.DROP_AND_CREATE, false, ModifyType.DROP_AND_CREATE, false)");
        System.out.println("******************************************");
        System.err.println(migrator.updateSql(mappings, ModifyType.DROP_AND_CREATE, false, ModifyType.DROP_AND_CREATE,
                false, false));
    }

    @Test
    public void testUpdateSqlIndex() {
        Set<ClassMapping<?>> mappings = classMappings2();
        sqlTypeMappingManager.setEnumWithOrdinal(false);
        System.out.println("migrator.updateSql(classMappings(), ModifyType.MODIFY, true, ModifyType.MODIFY, true)");
        System.err.println(migrator.updateSql(mappings, ModifyType.MODIFY, true, ModifyType.MODIFY, true, true));

    }

    private Set<ClassMapping<?>> classMappingsArticle2() {
        Set<ClassMapping<?>> set = new HashSet<>();
        set.add(factory.getClassMapping(Article3.class));
        return set;
    }

    private Set<ClassMapping<?>> classMappingsArticle3() {
        Set<ClassMapping<?>> set = new HashSet<>();
        set.add(factory.getClassMapping(Article3.class));
        return set;
    }

    private Set<ClassMapping<?>> classMappingsUserRole() {
        Set<ClassMapping<?>> set = new HashSet<>();
        set.add(factory.getClassMapping(UserRole2.class));
        return set;
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
        set.add(factory.getClassMapping(Entity3.class));
        return set;
    }
}
