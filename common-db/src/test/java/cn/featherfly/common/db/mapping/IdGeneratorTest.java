
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-31 16:10:31
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.db.mapping;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import cn.featherfly.common.bean.AsmPropertyAccessorFactory;
import cn.featherfly.common.db.JdbcTestBase;
import cn.featherfly.common.db.mapping.pojo.UuidTable;
import cn.featherfly.common.db.metadata.DatabaseMetadata;
import cn.featherfly.common.db.metadata.DatabaseMetadataManager;
import cn.featherfly.common.repository.id.IdGeneratorManager;
import cn.featherfly.common.repository.id.UUIDGenerator;

/**
 * CompatibleJdbcMappingFactoryTest.
 *
 * @author zhongj
 */
public class IdGeneratorTest extends JdbcTestBase {

    DatabaseMetadata metadata;

    private CompatibleJdbcMappingFactory factory;

    private SqlTypeMappingManager sqlTypeMappingManager = new SqlTypeMappingManager();

    @BeforeClass
    public void beforeClass() {
        IdGeneratorManager idGeneratorManager = new IdGeneratorManager();
        idGeneratorManager.add("uuid", new UUIDGenerator());
        metadata = DatabaseMetadataManager.getDefaultManager().create(dataSource);
        factory = new CompatibleJdbcMappingFactory(metadata, dialect, sqlTypeMappingManager, idGeneratorManager,
            new AsmPropertyAccessorFactory(Thread.currentThread().getContextClassLoader()));
    }

    @Test
    public void uuid() {
        JdbcClassMapping<UuidTable> mapping = factory.getClassMapping(UuidTable.class);
        assertEquals(mapping.getPropertyMappingLeafNodes().size(), 2);
        assertEquals(mapping.getPrimaryKeyPropertyMappings().get(0).getPrimaryKey().getIdGenerator().getClass(),
            UUIDGenerator.class);
    }

    @Test(expectedExceptions = JdbcMappingException.class)
    public void uuidNotFound() {
        factory = new CompatibleJdbcMappingFactory(metadata, dialect, sqlTypeMappingManager, new IdGeneratorManager(),
            new AsmPropertyAccessorFactory(Thread.currentThread().getContextClassLoader()));

        JdbcClassMapping<UuidTable> mapping = factory.getClassMapping(UuidTable.class);
        assertEquals(mapping.getPropertyMappingLeafNodes().size(), 2);
        assertEquals(mapping.getPrimaryKeyPropertyMappings().get(0).getPrimaryKey().getIdGenerator().getClass(),
            UUIDGenerator.class);
    }
}
