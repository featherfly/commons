
package cn.featherfly.common.db.export;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

import cn.featherfly.common.db.data.DataExportorImpl;
import cn.featherfly.common.db.data.JsonImportor;
import cn.featherfly.common.db.data.format.JsonDataFormatFactory;
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
public class ExportImplTest {

    static String database = "meihuo";

    public static DataSource getDatasource(String database) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/" + database);
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        return dataSource;
    }

    public static void main(String[] args) throws Exception {
        // 这里有问题，SqlDataFormat创建需要参数
        //		DataExportorImpl2 exportor = new DataExportorImpl2(SqlDataFormat.class);

        DataSource dataSource = getDatasource(database);

        DataExportorImpl exportor = new DataExportorImpl(new SqlDataFormatFactory());
        exportor.setDialect(Dialects.MYSQL);
        exportor.setDataSource(dataSource);
        exportor.exportDatabase(new OutputStreamWriter(new FileOutputStream("C:/" + database + ".sql"), "UTF-8"));

        exportor = new DataExportorImpl(new JsonDataFormatFactory());
        exportor.setDialect(Dialects.MYSQL);
        exportor.setDataSource(dataSource);
        exportor.exportDatabase(new OutputStreamWriter(new FileOutputStream("C:/" + database + ".json"), "UTF-8"));
    }
}

class ImpTest {
    public static void main(String[] args) throws UnsupportedEncodingException, FileNotFoundException {
        String database = "meihuo_imp";

        JsonImportor imp = new JsonImportor();
        imp.setDialect(Dialects.MYSQL);
        imp.setDataSource(ExportImplTest.getDatasource(database));
        imp.setFkCheck(false);
        imp.imp(new InputStreamReader(new FileInputStream("C:/" + ExportImplTest.database + ".json"), "UTF-8"));

    }
}
