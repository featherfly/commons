
package cn.featherfly.common.db;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.xml.DOMConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import cn.featherfly.common.lang.ClassLoaderUtils;

/**
 * <p>
 * SqlExecutorTest
 * </p>
 * <p>
 * 2019-08-16
 * </p>
 *
 * @author zhongj
 */
public class SqlExecutorTest {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    SqlExecutor sqlExecutor;

    @BeforeClass
    public void setup() {
        DOMConfigurator.configure(ClassLoaderUtils.getResource("log4j.xml", SqlExecutorTest.class));
        BasicDataSource dataSource = new BasicDataSource();
        //      dataSource.setUrl("jdbc:mysql://192.168.1.99:3306/mysql");
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/db_test");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");

        sqlExecutor = new SqlExecutor(dataSource);
    }

    @Test
    public void test() throws IOException {
        URL resource = ClassLoaderUtils.getResource("executor.sql", SqlExecutorTest.class);
        System.out.println(resource.getFile());
        File file = new File(resource.getFile());
        System.out.println(file.exists());
        sqlExecutor.execute(file);
    }
}
