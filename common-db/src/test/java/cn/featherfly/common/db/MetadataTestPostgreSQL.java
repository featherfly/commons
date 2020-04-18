
package cn.featherfly.common.db;

import org.apache.commons.dbcp.BasicDataSource;

import cn.featherfly.common.db.metadata.DatabaseMetadata;
import cn.featherfly.common.db.metadata.DatabaseMetadataManager;
import cn.featherfly.common.db.metadata.TableMetadata;

/**
 *
 */
public class MetadataTestPostgreSQL {
    public static void main(String[] args) {
        BasicDataSource dataSource = new BasicDataSource();
        //		dataSource.setUrl("jdbc:oracle:thin:@192.168.1.99:1521:orcl10g");
        //		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        //		dataSource.setUsername("scbmptest");
        //		dataSource.setPassword("scbmptest");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/db_test");
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUsername("postgres");
        dataSource.setPassword("123456");
        DatabaseMetadata meta = DatabaseMetadataManager.getDefaultManager().create(dataSource);
        //        DatabaseMetadata meta = DatabaseMetadataManager.getDefaultManager().create(dataSource, "juorm_jdbc");

        System.out.println(meta.getName());

        for (TableMetadata td : meta.getTables()) {
            System.out.println("\t" + td.getName());
            td.getColumns().forEach(c -> {
                System.out.println("\t\t" + c.getName() + " " + c.getTypeName() + "(" + c.getType() + ")" + " "
                        + c.getDefaultValue());
            });
        }
    }
}