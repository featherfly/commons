
package cn.featherfly.common.db.metadata;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.dbcp.BasicDataSource;

/**
 * <p>
 * Test
 * </p>
 *
 * @author zhongj
 */
public class Test {
    public static void main(String[] args) throws SQLException {

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/db_test");
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUsername("postgres");
        dataSource.setPassword("123456");

        try (Connection connection = dataSource.getConnection()) {
            String catalog = connection.getCatalog();
            System.out.println("catalog: " + catalog);
            //            System.out.println("schema: " + connection.getSchema());

            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet rs = null;

            rs = metaData.getCatalogs();
            System.out.println("catalogs:");
            while (rs.next()) {
                System.out.println("\t" + rs.getString("TABLE_CAT"));
            }
            rs = metaData.getSchemas();
            System.out.println("schemas:");
            while (rs.next()) {
                System.out.println("\tTABLE_SCHEM: " + rs.getString("TABLE_SCHEM"));
                System.out.println("\tTABLE_CATALOG: " + rs.getString("TABLE_CATALOG"));
            }
            rs = metaData.getTableTypes();
            rs = metaData.getTables(catalog, null, null, new String[] { TableType.TABLE.toString() });
            //            rs = metaData.getTables(catalog, catalog, null, new String[] { TableType.TABLE.toString() });
            //            rs = metaData.getTables("", "", null, new String[] { TableType.TABLE.toString() });
            final String remarks = "REMARKS";
            final String columnName = "COLUMN_NAME";
            boolean hasDatabase = false;
            while (rs.next()) {
                hasDatabase = true;
                // 表名
                String tableName = rs.getString("TABLE_NAME");
                System.out.println("table:");
                System.out.println("\tname: " + tableName);
                System.out.println("\ttype: " + rs.getString("TABLE_TYPE"));
                System.out.println("\tcat: " + rs.getString("TABLE_CAT"));
                System.out.println("\tschem: " + rs.getString("TABLE_SCHEM"));
                System.out.println("\tREMARKS: " + rs.getString(remarks));
                // 得到主键信息
                ResultSet rp = metaData.getPrimaryKeys(catalog, null, tableName);
                Set<String> pkColumnNames = new HashSet<>();
                while (rp.next()) {
                    // 主键列名称
                    pkColumnNames.add(rp.getString(columnName));
                }
                rp.close();
                // 得到表信息
                //                ResultSet rc = metaData.getColumns(catalog, null, tableName, null);
                //                while (rc.next()) {
                //                    // 列名
                //                    columnMetadata.setName(rc.getString(columnName));
                //                    // 列类型
                //                    columnMetadata.setType(rc.getInt("DATA_TYPE"));
                //                    // 类型名称
                //                    columnMetadata.setTypeName(rc.getString("TYPE_NAME"));
                //                    // 注释
                //                    columnMetadata.setRemark(rc.getString(remarks));
                //                    // 长度
                //                    columnMetadata.setSize(rc.getInt("COLUMN_SIZE"));
                //                    // 小数位数
                //                    columnMetadata.setDecimalDigits(rc.getInt("DECIMAL_DIGITS"));
                //                    // 默认值
                //                    columnMetadata.setDefaultValue(rc.getString("COLUMN_DEF"));
                //                    // 是否空
                //                    int nullable = rc.getInt("NULLABLE");
                //                    if (DatabaseMetaData.columnNullable == nullable) {
                //                        columnMetadata.setNullable(true);
                //                    } else {
                //                        columnMetadata.setNullable(false);
                //                    }
                //                    // 是否空
                //                    String isAutoincrement = rc.getString("IS_AUTOINCREMENT");
                //                    if ("YES".equals(isAutoincrement)) {
                //                        columnMetadata.setAutoincrement(true);
                //                    } else {
                //                        columnMetadata.setAutoincrement(false);
                //                    }
                //                    // 列的位置
                //                    columnMetadata.setColumnIndex(rc.getInt("ORDINAL_POSITION"));
                //                    // 设置是否主键
                //                    if (pkColumnNames.contains(columnMetadata.getName())) {
                //                        columnMetadata.setPrimaryKey(true);
                //                    }
                //
                //                    tableMetadata.addColumn(columnMetadata);
                //                }
                //                rc.close();
            }
            rs.close();
        }
    }
}
