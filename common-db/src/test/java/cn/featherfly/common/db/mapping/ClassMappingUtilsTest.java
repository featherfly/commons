
package cn.featherfly.common.db.mapping;

import org.testng.annotations.Test;

import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.db.dialect.Dialects;
import cn.featherfly.common.repository.mapping.ClassMapping;
import cn.featherfly.common.repository.mapping.PropertyMapping;

/**
 * <p>
 * ClassMappingUtilsTest
 * </p>
 *
 * @author zhongj
 */
public class ClassMappingUtilsTest {

    Dialect dialect = Dialects.MYSQL;

    @Test
    void test() {
        ClassMapping<User> mapping = new ClassMapping<>(User.class, "user");
        PropertyMapping pm = new PropertyMapping();
        pm.setPrimaryKey(true);
        pm.setRepositoryFieldName("id");
        pm.setPropertyName("id");
        pm.setPropertyType(Long.class);

        PropertyMapping pm2 = new PropertyMapping();
        pm2.setPrimaryKey(false);
        pm2.setRepositoryFieldName("name");
        pm2.setPropertyName("name");
        pm2.setPropertyType(String.class);

        mapping.addPropertyMapping(pm);
        mapping.addPropertyMapping(pm2);

        String sql = ClassMappingUtils.getSelectSql(mapping, dialect);

        System.out.println(sql);
    }
}
