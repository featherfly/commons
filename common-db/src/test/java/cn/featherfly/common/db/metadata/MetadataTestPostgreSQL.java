
package cn.featherfly.common.db.metadata;

import org.apache.commons.dbcp.BasicDataSource;

import cn.featherfly.common.db.Table;

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

        for (Table td : meta.getTables()) {
            System.out.println("\t" + td.getName());
            td.getColumns().forEach(c -> {
                System.out.println("\t\t" + c.getName() + " " + c.getTypeName() + "(" + c.getType() + ")" + " "
                        + c.getDefaultValue());
            });
        }
        for (Table td : meta.getTables()) {
            System.out.println("\t" + td.toString());
        }

        //        String sql = Dialects.POSTGRESQL.buildDropTableDDL("db_test", "user", true);
        //        System.out.println(sql);
        //        try (Connection conn = dataSource.getConnection(); Statement statement = conn.createStatement();) {
        //            statement.execute(sql);
        //        }
    }
}