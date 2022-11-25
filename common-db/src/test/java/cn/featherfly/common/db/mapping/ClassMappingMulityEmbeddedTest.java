package cn.featherfly.common.db.mapping;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import cn.featherfly.common.db.JdbcTestBase;
import cn.featherfly.common.db.dialect.Dialects;
import cn.featherfly.common.db.mapping.pojo.order.Order;
import cn.featherfly.common.db.metadata.DatabaseMetadata;
import cn.featherfly.common.db.metadata.DatabaseMetadataManager;

/**
 * ClassMappingMulityEmbeddedTest.
 *
 * @author zhongj
 */
public class ClassMappingMulityEmbeddedTest extends JdbcTestBase {

    private CompatibleJdbcMappingFactory factory;

    private SqlTypeMappingManager sqlTypeMappingManager = new SqlTypeMappingManager();

    @BeforeClass
    public void init() {
        DatabaseMetadata metadata = DatabaseMetadataManager.getDefaultManager().create(dataSource);
        factory = new CompatibleJdbcMappingFactory(metadata, dialect, sqlTypeMappingManager);
    }

    @Test(expectedExceptions = JdbcMappingException.class)
    public void testMulityEmbedded() {
        JdbcClassMapping<Order> mapping = factory.getClassMapping(Order.class);

        //        String insertSql = "INSERT INTO `order` (`wx_package`,`wx_package_expire_time`,`app_id`,`alipay_trade_no`,`id`,`no`,`app_key`) VALUES (?,?,?,?,?,?,?)";

        String result = ClassMappingUtils.getInsertSqlAndParamPositions(mapping, Dialects.MYSQL).get0();

        System.out.println(result);

        //        assertEquals(result, insertSql);

        //        System.out.println(ClassMappingUtils.getCreateTableSql(mapping, dialect, sqlTypeMappingManager));
        //
        //        sqlTypeMappingManager.setEnumWithOrdinal(true);
        //
        //        System.out.println(ClassMappingUtils.getCreateTableSql(mapping, dialect, sqlTypeMappingManager));
    }
}
