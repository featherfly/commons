
package cn.featherfly.common.db.metadata;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.dbcp2.BasicDataSource;

/**
 * <p>
 * Test
 * </p>
 *
 * @author zhongj
 */
public class Test2 {
    public static void main(String[] args) throws SQLException {
        try (BasicDataSource dataSource = new BasicDataSource()) {
            dataSource.setUrl(
                "jdbc:mysql://127.0.0.1:3306/db_test?serverTimezone=UTC&characterEncoding=utf8&useUnicode=true&useSSL=false");
            dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
            dataSource.setUsername("root");
            dataSource.setPassword("123456");
            try (Connection connection = dataSource.getConnection()) {
                String catalog = connection.getCatalog();
                System.out.println("catalog: " + catalog);
                //            System.out.println("schema: " + connection.getSchema());

                DatabaseMetaData metaData = connection.getMetaData();
                ResultSet rs = null;
                System.err.println("url: " + metaData.getURL());

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
                    System.out.println("\tschema: " + rs.getString("TABLE_SCHEM"));
                    System.out.println("\tREMARKS: " + rs.getString(remarks));

                    ResultSet ri = metaData.getIndexInfo(catalog, null, tableName, false, false);
                    while (ri.next()) {
                        System.out.println("\tINDEX_NAME: " + ri.getString("INDEX_NAME"));
                        System.out.println("\t\tCOLUMN_NAME: " + ri.getString("COLUMN_NAME"));
                        System.out.println("\t\tNON_UNIQUE: " + ri.getBoolean("NON_UNIQUE"));
                        System.out.println("\t\tINDEX_QUALIFIER: " + ri.getString("INDEX_QUALIFIER"));
                        System.out.println("\t\tIASC_OR_DESC: " + ri.getString("ASC_OR_DESC"));
                        System.out.println("\t\tTYPE: " + ri.getString("TYPE"));
                        System.out.println("\t\tTABLE_CAT: " + ri.getString("TABLE_CAT"));
                        System.out.println("\t\tTABLE_SCHEM: " + ri.getString("TABLE_SCHEM"));
                    }
                    System.err.println("-------------------------------");
                    ri = metaData.getIndexInfo(catalog, null, tableName, true, false);
                    while (ri.next()) {
                        System.out.println("\tINDEX_NAME: " + ri.getString("INDEX_NAME"));
                        System.out.println("\t\tCOLUMN_NAME: " + ri.getString("COLUMN_NAME"));
                        System.out.println("\t\tNON_UNIQUE: " + ri.getBoolean("NON_UNIQUE"));
                        System.out.println("\t\tINDEX_QUALIFIER: " + ri.getString("INDEX_QUALIFIER"));
                        System.out.println("\t\tIASC_OR_DESC: " + ri.getString("ASC_OR_DESC"));
                        System.out.println("\t\tTYPE: " + ri.getString("TYPE"));
                        System.out.println("\t\tTABLE_CAT: " + ri.getString("TABLE_CAT"));
                        System.out.println("\t\tTABLE_SCHEM: " + ri.getString("TABLE_SCHEM"));
                    }

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
                System.out.println(hasDatabase);
            }
        }
    }
}
