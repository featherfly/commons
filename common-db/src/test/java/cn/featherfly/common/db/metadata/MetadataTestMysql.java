package cn.featherfly.common.db.metadata;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import cn.featherfly.common.db.Table;

public class MetadataTestMysql {

    BasicDataSource dataSource;

    @BeforeClass
    void setup() {
        dataSource = new BasicDataSource();
        //      dataSource.setUrl("jdbc:mysql://192.168.1.99:3306/mysql");
        //        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/db_test");
        dataSource.setUrl(
                "jdbc:mysql://127.0.0.1:3306/db_test?serverTimezone=UTC&characterEncoding=utf8&useUnicode=true&useSSL=false");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
    }

    @Test
    void test() throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            DatabaseMetaData metaData = conn.getMetaData();

            System.out.println("connection.getCatalog() = " + conn.getCatalog());
            System.out.println("connection.getSchema() = " + conn.getSchema());

            System.out.println(
                    "metaData.supportsCatalogsInDataManipulation() = " + metaData.supportsCatalogsInDataManipulation());
            System.out.println(
                    "metaData.supportsCatalogsInTableDefinitions() = " + metaData.supportsCatalogsInTableDefinitions());
            System.out.println(
                    "metaData.supportsSchemasInDataManipulation() = " + metaData.supportsSchemasInDataManipulation());
            System.out.println(
                    "metaData.supportsSchemasInTableDefinitions() = " + metaData.supportsSchemasInTableDefinitions());

            ResultSet rs = metaData.getCatalogs();
            System.out.println("catalogs: ");
            while (rs.next()) {
                String catalog = rs.getString("TABLE_CAT");
                System.out.println("  catalog = " + catalog);
            }
            rs.close();
            System.out.println("schemas: ");
            rs = metaData.getSchemas();
            while (rs.next()) {
                String schema = rs.getString("TABLE_SCHEM");
                String catalog = rs.getString("TABLE_CATALOG");
                System.out.println("  schema = " + schema);
                System.out.println("  catalog = " + catalog);
            }
            rs.close();
        }
    }

    public static void main(String[] args) {
        BasicDataSource dataSource = new BasicDataSource();
        //		dataSource.setUrl("jdbc:mysql://192.168.1.99:3306/mysql");
        //        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/db_test");
        dataSource.setUrl(
                "jdbc:mysql://127.0.0.1:3306/db_test?serverTimezone=UTC&characterEncoding=utf8&useUnicode=true&useSSL=false");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        DatabaseMetadata meta = DatabaseMetadataManager.getDefaultManager().create(dataSource);
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
    }
}