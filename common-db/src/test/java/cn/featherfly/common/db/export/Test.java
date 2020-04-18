
package cn.featherfly.common.db.export;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import org.apache.commons.dbcp.BasicDataSource;

/**
 * <p>
 * 类的说明放这里
 * </p>
 * <p>
 * copyright cdthgk 2010-2020, all rights reserved.
 * </p>
 *
 * @author 钟冀
 */
public class Test {
    public static void main(String[] args) throws Exception {
        String database = "juorm_jdbc";
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/" + database);
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");

        Connection conn = dataSource.getConnection();

        PreparedStatement prep = conn.prepareStatement("select * from user");
        ResultSet res = prep.executeQuery();
        ResultSetMetaData rsmd = res.getMetaData();

        DatabaseMetaData dbmd = conn.getMetaData();

        //		System.out.println(rsmd.getTableName(1));
        //		System.out.println(rsmd.getColumnTypeName(1));

        System.out.println("supportsBatchUpdates：" + dbmd.supportsBatchUpdates());
        System.out.println("supportsSavepoints：" + dbmd.supportsSavepoints());
        System.out.println("supportsNamedParameters：" + dbmd.supportsNamedParameters());
        System.out.println("supportsGetGeneratedKeys：" + dbmd.supportsGetGeneratedKeys());
        System.out.println("getDatabaseMajorVersion：" + dbmd.getDatabaseMajorVersion());
        System.out.println("getDatabaseMinorVersion：" + dbmd.getDatabaseMinorVersion());
        System.out.println("getDatabaseProductName：" + dbmd.getDatabaseProductName());
        System.out.println("getDatabaseProductVersion：" + dbmd.getDatabaseProductVersion());
        System.out.println("getJDBCMajorVersion：" + dbmd.getJDBCMajorVersion());
        System.out.println("getJDBCMinorVersion：" + dbmd.getJDBCMinorVersion());
        System.out.println("getDriverName：" + dbmd.getDriverName());
        System.out.println("getDriverVersion：" + dbmd.getDriverVersion());
        System.out.println("getDriverMajorVersion：" + dbmd.getDriverMajorVersion());
        System.out.println("getDriverMinorVersion：" + dbmd.getDriverMinorVersion());

        ResultSet cr = dbmd.getTables(database, null, "person", null);
        while (cr.next()) {
            System.out.print(cr.getString("TABLE_CAT"));
            System.out.print("\t");
            System.out.print(cr.getString("TABLE_SCHEM"));
            System.out.print("\t");
            System.out.print(cr.getString("TABLE_NAME"));
            System.out.print("\t");
            System.out.print(cr.getString("TABLE_TYPE"));
            System.out.print("\t");
            System.out.print(cr.getString("REMARKS"));
            System.out.print("\n");
        }
        /*
         * 每个表描述都有以下列： TABLE_CAT String => 表类别（可为 null） TABLE_SCHEM String =>
         * 表模式（可为 null） TABLE_NAME String => 表名称 TABLE_TYPE String => 表类型。典型的类型是
         * "TABLE"、"VIEW"、"SYSTEM TABLE"、"GLOBAL TEMPORARY"、"LOCAL TEMPORARY"
         * 、"ALIAS" 和 "SYNONYM"。 REMARKS String => 表的解释性注释 TYPE_CAT String =>
         * 类型的类别（可为 null） TYPE_SCHEM String => 类型模式（可为 null） TYPE_NAME String =>
         * 类型名称（可为 null） SELF_REFERENCING_COL_NAME String => 有类型表的指定
         * "identifier" 列的名称（可为 null） REF_GENERATION String => 指定在
         * SELF_REFERENCING_COL_NAME 中创建值的方式。这些值为 "SYSTEM"、"USER" 和
         * "DERIVED"。（可能为 null） 注： 有些数据库可能不返回用于所有表的信息。
         */

        cr = dbmd.getColumns(database, null, "person", null);
        while (cr.next()) {
            System.out.print(cr.getString("COLUMN_NAME"));
            System.out.print("\t");
            System.out.print(cr.getString("TYPE_NAME"));
            System.out.print("\t");
            System.out.print(cr.getInt("COLUMN_SIZE"));
            System.out.print("\t");
            System.out.print(cr.getString("IS_NULLABLE"));
            System.out.print("\t");
            System.out.print(cr.getString("COLUMN_DEF"));
            System.out.print("\t");
            System.out.print(cr.getString("REMARKS"));
            System.out.print("\t");
            System.out.print(cr.getString("IS_AUTOINCREMENT"));
            System.out.print("\n");
        }
        /*
         * TABLE_CAT String => 表类别（可为 null） TABLE_SCHEM String => 表模式（可为 null）
         * TABLE_NAME String => 表名称 COLUMN_NAME String => 列名称 DATA_TYPE int =>
         * 来自 java.sql.Types 的 SQL 类型 TYPE_NAME String => 数据源依赖的类型名称，对于
         * UDT，该类型名称是完全限定的 COLUMN_SIZE int => 列的大小。 BUFFER_LENGTH 未被使用。
         * DECIMAL_DIGITS int => 小数部分的位数。对于 DECIMAL_DIGITS 不适用的数据类型，则返回 Null。
         * NUM_PREC_RADIX int => 基数（通常为 10 或 2） NULLABLE int => 是否允许使用 NULL。
         * columnNoNulls - 可能不允许使用 NULL 值 columnNullable - 明确允许使用 NULL 值
         * columnNullableUnknown - 不知道是否可使用 null REMARKS String => 描述列的注释（可为
         * null） COLUMN_DEF String => 该列的默认值，当值在单引号内时应被解释为一个字符串（可为 null）
         * SQL_DATA_TYPE int => 未使用 SQL_DATETIME_SUB int => 未使用
         * CHAR_OCTET_LENGTH int => 对于 char 类型，该长度是列中的最大字节数 ORDINAL_POSITION int
         * => 表中的列的索引（从 1 开始） IS_NULLABLE String => ISO 规则用于确定列是否包括 null。 YES
         * --- 如果参数可以包括 NULL NO --- 如果参数不可以包括 NULL 空字符串 --- 如果不知道参数是否可以包括 null
         * SCOPE_CATLOG String => 表的类别，它是引用属性的作用域（如果 DATA_TYPE 不是 REF，则为 null）
         * SCOPE_SCHEMA String => 表的模式，它是引用属性的作用域（如果 DATA_TYPE 不是 REF，则为 null）
         * SCOPE_TABLE String => 表名称，它是引用属性的作用域（如果 DATA_TYPE 不是 REF，则为 null）
         * SOURCE_DATA_TYPE short => 不同类型或用户生成 Ref 类型、来自 java.sql.Types 的 SQL
         * 类型的源类型（如果 DATA_TYPE 不是 DISTINCT 或用户生成的 REF，则为 null） IS_AUTOINCREMENT
         * String => 指示此列是否自动增加 YES --- 如果该列自动增加 NO --- 如果该列不自动增加 空字符串 ---
         * 如果不能确定该列是否是自动增加参数 COLUMN_SIZE
         * 列表示给定列的指定列大小。对于数值数据，这是最大精度。对于字符数据，这是字符长度。对于日期时间数据类型，这是 String
         * 表示形式的字符长度（假定允许的最大小数秒组件的精度）。对于二进制数据，这是字节长度。对于 ROWID
         * 数据类型，这是字节长度。对于列大小不适用的数据类型，则返回 Null。
         */
    }
}
