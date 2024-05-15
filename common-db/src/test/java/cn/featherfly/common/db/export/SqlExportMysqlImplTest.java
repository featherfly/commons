
package cn.featherfly.common.db.export;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.dbcp2.BasicDataSource;

import cn.featherfly.common.db.data.DataExportorImpl;
import cn.featherfly.common.db.data.format.SqlDataFormatFactory;
import cn.featherfly.common.db.dialect.Dialects;

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
public class SqlExportMysqlImplTest {
    public static void main(String[] args) throws Exception {
        String database = "db_test";
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/" + database
                + "?serverTimezone=UTC&characterEncoding=utf8&useUnicode=true&useSSL=false");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");

        //        FileWriter fw = null;
        //		SqlDataFormat sqlDataFormat = (SqlDataFormat) new SqlDataFormatFactory().createDataFormat(fw, new MySqlDialect());

        DataExportorImpl exportor = new DataExportorImpl(Dialects.mysql(), new SqlDataFormatFactory());
        //		SqlExportorMysqlImpl exportor = new SqlExportorMysqlImpl();
        exportor.setDataSource(dataSource);
        exportor.exportDatabase(new OutputStreamWriter(new FileOutputStream("tmp/" + database + ".sql"), "UTF-8"));

        exportor.exportData("select * from user_info where ID = '1'",
                new OutputStreamWriter(new FileOutputStream("tmp/user_info_1.sql"), "UTF-8"));

        Collection<String> tables = new ArrayList<>();
        tables.add("user_info");
        tables.add("role");
        tables.add("user_role");
        exportor.exportTable(new OutputStreamWriter(new FileOutputStream("tmp/tables.sql"), "UTF-8"), tables);
        Collection<String> sqls = new ArrayList<>();
        sqls.add("select * from cms_article where ID = '1'");
        sqls.add("select * from user_info where ID = '1'");
        exportor.exportData(sqls, new OutputStreamWriter(new FileOutputStream("tmp/sqls.sql"), "UTF-8"));
    }
}
