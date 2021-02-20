
package cn.featherfly.common.db;

import java.io.File;
import java.io.IOException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.xml.DOMConfigurator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.db.dialect.Dialects;
import cn.featherfly.common.lang.ClassLoaderUtils;
import cn.featherfly.common.lang.UriUtils;

/**
 * <p>
 * JdbcTestBase
 * </p>
 *
 * @author zhongj
 */
public class JdbcTestBase {

    protected DataSource dataSource;

    protected Dialect dialect;

    protected SqlExecutor sqlExecutor;

    @BeforeClass
    @Parameters({ "dataBase" })
    public void initSetUp(@Optional("mysql") String dataBase) throws IOException {
        DOMConfigurator.configure(ClassLoaderUtils.getResource("log4j.xml", JdbcTestBase.class));
        initDataBase(dataBase);
        sqlExecutor = new SqlExecutor(dataSource);
        // 初始化数据库
        //        SqlExecutor sqlExecutor = new SqlExecutor(dataSource);
        //        sqlExecutor.execute(
        //                new File(ClassLoaderUtils.getResource("test." + dataBase + ".sql", JdbcTestBase.class).getFile()));
    }

    public void initDataBase(String dataBase) throws IOException {
        switch (dataBase) {
            case "mysql":
                initMysql();
                break;
            case "postgresql":
                initPostgresql();
                break;
            case "sqlite":
                initSQLite();
                break;
            default:
                initMysql();
                break;
        }
    }

    @BeforeGroups(groups = "mysql")
    public void initMysql() throws IOException {
        DOMConfigurator.configure(ClassLoaderUtils.getResource("log4j.xml").getFile());
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(
                "jdbc:mysql://127.0.0.1:3306/db_test?serverTimezone=UTC&characterEncoding=utf8&useUnicode=true&useSSL=false");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");

        this.dataSource = dataSource;
        dialect = Dialects.MYSQL;
    }

    @BeforeGroups(groups = "postgresql")
    public void initPostgresql() throws IOException {

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/db_test");
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUsername("postgres");
        dataSource.setPassword("123456");

        this.dataSource = dataSource;
        dialect = Dialects.POSTGRESQL;
    }

    @BeforeGroups(groups = "sqlite")
    public void initSQLite() throws IOException {
        String path = new File(UriUtils.linkUri(this.getClass().getResource("/").getFile(), "db_test.sqlite3.db"))
                .getPath();
        System.out.println(path);
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.sqlite.JDBC");
        dataSource.setUrl("jdbc:sqlite:" + path);
        //        dataSource.setUsername("root");
        //        dataSource.setPassword("123456");

        this.dataSource = dataSource;
        dialect = Dialects.SQLITE;
    }
}
