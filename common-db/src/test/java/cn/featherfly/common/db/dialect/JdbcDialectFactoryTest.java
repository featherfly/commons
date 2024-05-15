
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2023-10-10 16:31:10
 * @Copyright: 2023 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.db.dialect;

import static org.testng.Assert.assertEquals;

import org.apache.commons.dbcp2.BasicDataSource;
import org.testng.annotations.Test;

/**
 * JdbcDialectFactoryTest.
 *
 * @author zhongj
 */
public class JdbcDialectFactoryTest {

    DialectFactory factory = new JdbcDialectFactory();

    @Test
    void testMysql() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(
                "jdbc:mysql://127.0.0.1:3306/hammer_jdbc?characterEncoding=utf8&useUnicode=true&useSSL=false&allowPublicKeyRetrieval=true ");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");

        Dialect dialect = factory.create(dataSource);
        assertEquals(dialect.getClass(), MySQLDialect.class);
    }
}
