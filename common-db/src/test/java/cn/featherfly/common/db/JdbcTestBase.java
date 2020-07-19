
package cn.featherfly.common.db;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.xml.DOMConfigurator;
import org.testng.annotations.BeforeSuite;

import cn.featherfly.common.lang.ClassLoaderUtils;

/**
 * <p>
 * JdbcTestBase
 * </p>
 *
 * @author zhongj
 */
public class JdbcTestBase {

    protected DataSource dataSource;

    @BeforeSuite
    public void setUp() {
        DOMConfigurator.configure(ClassLoaderUtils.getResource("log4j.xml").getFile());
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(
                "jdbc:mysql://127.0.0.1:3306/db_test?serverTimezone=UTC&characterEncoding=utf8&useUnicode=true&useSSL=false");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        this.dataSource = dataSource;
    }
}
