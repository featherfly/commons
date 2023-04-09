
package cn.featherfly.common.db.metadata;

import java.sql.Types;

import org.apache.commons.dbcp2.BasicDataSource;

import cn.featherfly.common.db.Table;

/**
 *
 */
public class MetadataTestOracle {
    public static void main(String[] args) {
        BasicDataSource dataSource = new BasicDataSource();
        //		dataSource.setUrl("jdbc:oracle:thin:@192.168.1.99:1521:orcl10g");
        //		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        //		dataSource.setUsername("scbmptest");
        //		dataSource.setPassword("scbmptest");
        dataSource.setUrl("jdbc:oracle:thin:@192.168.1.99:1521:ORCL10G");
        dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        dataSource.setUsername("armybms");
        dataSource.setPassword("armybms");
        DatabaseMetadata meta = DatabaseMetadataManager.getDefaultManager().create(dataSource, "db_test");

        System.out.println(meta.getName());

        for (Table td : meta.getTables()) {
            System.out.println("\t" + td.getName());
        }

        System.out.println(meta.getTable("SYS_USER").getColumn("CREATE_TIME").getType());
        System.out.println(Types.DATE);
    }
}
