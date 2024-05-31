
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
import cn.featherfly.common.db.mapping.pojo.UserInfo3;
import cn.featherfly.common.db.mapping.pojo.UserInfo32;
import cn.featherfly.common.db.metadata.DatabaseMetadata;
import cn.featherfly.common.db.metadata.DatabaseMetadataManager;

/**
 * CompatibleJdbcMappingFactoryTest.
 *
 * @author zhongj
 */
public class CompatibleJdbcMappingFactoryTest extends JdbcTestBase {

    private CompatibleJdbcMappingFactory factory;

    private SqlTypeMappingManager sqlTypeMappingManager = new SqlTypeMappingManager();

    @BeforeClass
    public void beforeClass() {
        DatabaseMetadata metadata = DatabaseMetadataManager.getDefaultManager().create(dataSource);
        factory = new CompatibleJdbcMappingFactory(metadata, dialect, sqlTypeMappingManager,
            new AsmPropertyAccessorFactory(Thread.currentThread().getContextClassLoader()));
    }

    @Test
    public void mappingTableNameWithUnderscoreAndNumber() {
        JdbcClassMapping<UserInfo3> mapping = factory.getClassMapping(UserInfo3.class);
        assertEquals(mapping.getPropertyMappingLeafNodes().size(), 3);
        assertEquals(mapping.getRepositoryName(), "user_info_3");
        assertEquals(mapping.getPropertyMapping("userName3").getRepositoryFieldName(), "user_name_3");
    }

    @Test
    public void mappingColumnNameWithUnderscoreAndNumber() {
        JdbcClassMapping<UserInfo32> mapping = factory.getClassMapping(UserInfo32.class);
        assertEquals(mapping.getPropertyMappingLeafNodes().size(), 3);
        assertEquals(mapping.getRepositoryName(), "user_info_3");
        assertEquals(mapping.getPropertyMapping("userName3").getRepositoryFieldName(), "user_name_3");
    }
}
