
package cn.featherfly.common.db.mapping;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import org.testng.annotations.Test;

import cn.featherfly.common.bean.BeanDescriptor;
import cn.featherfly.common.bean.BeanProperty;
import cn.featherfly.common.db.JdbcException;
import cn.featherfly.common.db.JdbcTestBase;
import cn.featherfly.common.db.JdbcUtils;
import cn.featherfly.common.db.mapping.mappers.ObjectToJsonMapper;
import cn.featherfly.common.db.mapping.pojo.Article;
import cn.featherfly.common.db.mapping.pojo.Content;
import cn.featherfly.common.db.wrapper.ConnectionWrapper;
import cn.featherfly.common.db.wrapper.PreparedStatementWrapper;
import cn.featherfly.common.db.wrapper.ResultSetWrapper;
import cn.featherfly.common.lang.ArrayUtils;
import cn.featherfly.common.lang.GenericType;
import cn.featherfly.common.lang.Randoms;
import cn.featherfly.common.lang.reflect.GenericClass;

/**
 * <p>
 * SqlTypeMappingManagerTest
 * </p>
 *
 * @author zhongj
 */
public class SqlTypeMappingManagerTest extends JdbcTestBase {

    @Test
    public void testDefaultJavaToSql() {
        SqlTypeMappingManager manager = new SqlTypeMappingManager();
        assertEquals(manager.getSqlType(Boolean.class), JDBCType.BOOLEAN);
        assertEquals(manager.getSqlType(Boolean.TYPE), JDBCType.BOOLEAN);
        assertEquals(manager.getSqlType(Character.TYPE), JDBCType.CHAR);
        assertEquals(manager.getSqlType(Character.class), JDBCType.CHAR);
        assertEquals(manager.getSqlType(Byte.TYPE), JDBCType.TINYINT);
        assertEquals(manager.getSqlType(Byte.class), JDBCType.TINYINT);
        assertEquals(manager.getSqlType(Short.TYPE), JDBCType.SMALLINT);
        assertEquals(manager.getSqlType(Short.class), JDBCType.SMALLINT);
        assertEquals(manager.getSqlType(Integer.TYPE), JDBCType.INTEGER);
        assertEquals(manager.getSqlType(Integer.class), JDBCType.INTEGER);
        assertEquals(manager.getSqlType(Long.TYPE), JDBCType.BIGINT);
        assertEquals(manager.getSqlType(Long.class), JDBCType.BIGINT);
        assertEquals(manager.getSqlType(Float.TYPE), JDBCType.DECIMAL);
        assertEquals(manager.getSqlType(Float.class), JDBCType.DECIMAL);
        assertEquals(manager.getSqlType(Double.TYPE), JDBCType.DECIMAL);
        assertEquals(manager.getSqlType(Double.class), JDBCType.DECIMAL);
        assertEquals(manager.getSqlType(BigInteger.class), JDBCType.BIGINT);
        assertEquals(manager.getSqlType(BigDecimal.class), JDBCType.DECIMAL);
        assertEquals(manager.getSqlType(String.class), JDBCType.VARCHAR);
        assertEquals(manager.getSqlType(Date.class), JDBCType.TIMESTAMP);
        assertEquals(manager.getSqlType(Timestamp.class), JDBCType.TIMESTAMP);
        assertEquals(manager.getSqlType(LocalDateTime.class), JDBCType.TIMESTAMP);
        assertEquals(manager.getSqlType(java.sql.Date.class), JDBCType.DATE);
        assertEquals(manager.getSqlType(LocalDate.class), JDBCType.DATE);
        assertEquals(manager.getSqlType(java.sql.Time.class), JDBCType.TIME);
        assertEquals(manager.getSqlType(LocalTime.class), JDBCType.TIME);

    }

    @Test
    public void testDefaultSqlToJava() {
        SqlTypeMappingManager manager = new SqlTypeMappingManager();
        assertEquals(manager.getJavaType(JDBCType.BOOLEAN), Boolean.TYPE);
        //  str types
        assertEquals(manager.getJavaType(JDBCType.CHAR), String.class);
        assertEquals(manager.getJavaType(JDBCType.NCHAR), String.class);
        assertEquals(manager.getJavaType(JDBCType.VARCHAR), String.class);
        assertEquals(manager.getJavaType(JDBCType.NVARCHAR), String.class);
        assertEquals(manager.getJavaType(JDBCType.LONGVARCHAR), String.class);
        assertEquals(manager.getJavaType(JDBCType.LONGNVARCHAR), String.class);
        assertEquals(manager.getJavaType(JDBCType.CLOB), String.class);
        assertEquals(manager.getJavaType(JDBCType.NCLOB), String.class);
        //  number types
        assertEquals(manager.getJavaType(JDBCType.TINYINT), Integer.class);
        assertEquals(manager.getJavaType(JDBCType.SMALLINT), Integer.class);
        assertEquals(manager.getJavaType(JDBCType.INTEGER), Integer.class);
        assertEquals(manager.getJavaType(JDBCType.BIGINT), Long.class);
        assertEquals(manager.getJavaType(JDBCType.FLOAT), BigDecimal.class);
        assertEquals(manager.getJavaType(JDBCType.DOUBLE), BigDecimal.class);
        assertEquals(manager.getJavaType(JDBCType.REAL), BigDecimal.class);
        assertEquals(manager.getJavaType(JDBCType.NUMERIC), BigDecimal.class);
        assertEquals(manager.getJavaType(JDBCType.DECIMAL), BigDecimal.class);
        // date types
        assertEquals(manager.getJavaType(JDBCType.DATE), Date.class);
        assertEquals(manager.getJavaType(JDBCType.TIME), Date.class);
        assertEquals(manager.getJavaType(JDBCType.TIMESTAMP), Date.class);
        assertEquals(manager.getJavaType(JDBCType.TIME_WITH_TIMEZONE), LocalTime.class);
        assertEquals(manager.getJavaType(JDBCType.TIMESTAMP_WITH_TIMEZONE), LocalDateTime.class);
        // data binary
        assertEquals(manager.getJavaType(JDBCType.BLOB), byte[].class);
        assertEquals(manager.getJavaType(JDBCType.BINARY), byte[].class);
        assertEquals(manager.getJavaType(JDBCType.LONGVARBINARY), byte[].class);
        assertEquals(manager.getJavaType(JDBCType.VARBINARY), byte[].class);

        assertEquals(manager.getJavaType(JDBCType.BIT), Boolean.class);
    }

    @Test
    public void testRegister() {
        SqlTypeMappingManager manager = new SqlTypeMappingManager();
        assertNull(manager.getSqlType(Long[].class));
        manager.regist(new JavaToSqlTypeRegister<Long[]>() {
            @Override
            public Class<Long[]> getJavaType() {
                return Long[].class;
            }

            @Override
            public SQLType getSqlType() {
                return JDBCType.VARCHAR;
            }
        });

        assertEquals(manager.getSqlType(Long[].class), JDBCType.VARCHAR);

        assertEquals(manager.getJavaType(JDBCType.DOUBLE), BigDecimal.class);
        manager.regist(new SqlTypeToJavaRegister<Double>() {
            @Override
            public Class<Double> getJavaType() {
                return Double.class;
            }

            @Override
            public SQLType getSqlType() {
                return JDBCType.DOUBLE;
            }
        });
        assertEquals(manager.getJavaType(JDBCType.DOUBLE), Double.class);
    }

    @Test
    public void testMapper() {
        SqlTypeMappingManager manager = new SqlTypeMappingManager();
        assertNull(manager.getSqlType(Long[].class));

        sqlExecutor.execute("delete from cms_article");

        manager.regist(new JavaSqlTypeMapper<Long[]>() {

            //            private BeanProperty<Long[]> bp = BeanDescriptor.getBeanDescriptor(Article.class)
            //                    .getBeanProperty("content");

            protected boolean support(SQLType sqlType) {
                return JDBCType.VARCHAR.equals(sqlType);
            }

            @Override
            public boolean support(GenericType<Long[]> type) {
                return type.getType().equals(Long[].class);
            }

            @Override
            public Class<Long[]> getJavaType(SQLType sqlType) {
                return Long[].class;
                //                if (JDBCType.VARCHAR.equals(sqlType)) {
                //                    return Long[].class;
                //                } else {
                //                    return null;
                //                }
            }

            @Override
            public SQLType getSqlType(GenericType<Long[]> javaType) {
                return JDBCType.VARCHAR;
                //                if (javaType.getType().equals(Long[].class)) {
                //                    return JDBCType.VARCHAR;
                //                } else {
                //                    return null;
                //                }
            }

            @Override
            public void set(PreparedStatement prep, int columnIndex, Long[] value) {
                System.out.println(ArrayUtils.toString(value, ','));
                JdbcUtils.setParameter(prep, columnIndex, ArrayUtils.toString(value, ','));
            }

            @Override
            public Long[] get(ResultSet rs, int columnIndex) {
                try {
                    return ArrayUtils.toNumbers(Long.class, rs.getString(columnIndex).split(","));
                } catch (SQLException e) {
                    throw new JdbcException(e);
                }
            }

            @Override
            public boolean support(SQLType sqlType, String tableName, String columnName) {
                return support(sqlType);
            }
        });

        String insert = "INSERT INTO `db_test`.`cms_article` (`ID`, `title`, `content`) VALUES (null, ?, ?)";
        Long[] contentArray = new Long[] { 1L, 2L, 3L };
        try (ConnectionWrapper connection = JdbcUtils.getConnectionWrapper(dataSource);
                PreparedStatementWrapper prep = connection.prepareStatement(insert)) {
            manager.set(prep.getPreparedStatement(), 1, Randoms.getString(6));
            manager.set(prep.getPreparedStatement(), 2, contentArray);
            boolean res = prep.execute();
            System.out.println(res);
        }

        String select = "select * from cms_article";
        try (ConnectionWrapper connection = JdbcUtils.getConnectionWrapper(dataSource);
                PreparedStatementWrapper prep = connection.prepareStatement(select)) {
            ResultSetWrapper rs = prep.executeQuery();
            GenericType<Long[]> gc = new GenericClass<>(Long[].class);
            GenericType<String> gts = new GenericClass<>(String.class);
            while (rs.next()) {
                String title = manager.get(rs.getResultSet(), 2, gts);
                Long[] content = manager.get(rs.getResultSet(), 3, gc);
                System.out.println(title + " -> " + ArrayUtils.toString(content));
                assertEquals(content, contentArray);
            }
        }
    }

    @Test
    public void testObjectToJsonMapper() {
        // database varchar type
        SqlTypeMappingManager manager = new SqlTypeMappingManager();
        assertNull(manager.getSqlType(Content.class));

        sqlExecutor.execute("delete from cms_article");

        BeanDescriptor<Article> bd = BeanDescriptor.getBeanDescriptor(Article.class);
        BeanProperty<Content> contentProperty = bd.getBeanProperty("content2");

        manager.regist(contentProperty, new ObjectToJsonMapper<>(contentProperty));
        //        manager.regist(contentProperty, new ObjectToJsonMapper<>(Content.class));

        String insert = "INSERT INTO `db_test`.`cms_article` (`ID`, `title`, `content2`) VALUES (null, ?, ?)";
        Content content = new Content();
        content.setDescp("c_descp");
        content.setImg("c_img");

        try (ConnectionWrapper connection = JdbcUtils.getConnectionWrapper(dataSource);
                PreparedStatementWrapper prep = connection.prepareStatement(insert)) {
            manager.set(prep.getPreparedStatement(), 1, Randoms.getString(6));
            //            manager.set(prep.getPreparedStatement(), 2, content);
            manager.set(prep.getPreparedStatement(), 2, content, contentProperty);

            boolean res = prep.execute();
            System.out.println(res);
        }

        String select = "select * from cms_article";
        try (ConnectionWrapper connection = JdbcUtils.getConnectionWrapper(dataSource);
                PreparedStatementWrapper prep = connection.prepareStatement(select)) {
            ResultSetWrapper rs = prep.executeQuery();
            GenericType<String> gts = new GenericClass<>(String.class);
            while (rs.next()) {
                String title = manager.get(rs.getResultSet(), 2, gts);
                Content contentResult = manager.get(rs.getResultSet(), 4, contentProperty);
                System.out.println(title + " -> " + contentResult);
                assertEquals(content, contentResult);
            }
        }
    }

    @Test
    public void testObjectToJsonMapper2() {
        // database json type
        SqlTypeMappingManager manager = new SqlTypeMappingManager();
        assertNull(manager.getSqlType(Content.class));

        sqlExecutor.execute("delete from cms_article");

        BeanDescriptor<Article> bd = BeanDescriptor.getBeanDescriptor(Article.class);
        BeanProperty<Content> contentProperty = bd.getBeanProperty("content3");

        manager.regist(contentProperty, new ObjectToJsonMapper<>(contentProperty));
        //        manager.regist(contentProperty, new ObjectToJsonMapper<>(Content.class));

        String insert = "INSERT INTO `db_test`.`cms_article` (`ID`, `title`, `content3`) VALUES (null, ?, ?)";
        Content content = new Content();
        content.setDescp("c_descp");
        content.setImg("c_img");

        try (ConnectionWrapper connection = JdbcUtils.getConnectionWrapper(dataSource);
                PreparedStatementWrapper prep = connection.prepareStatement(insert)) {
            manager.set(prep.getPreparedStatement(), 1, Randoms.getString(6));
            //            manager.set(prep.getPreparedStatement(), 2, content);
            manager.set(prep.getPreparedStatement(), 2, content, contentProperty);

            boolean res = prep.execute();
            System.out.println(res);
        }

        String select = "select * from cms_article";
        try (ConnectionWrapper connection = JdbcUtils.getConnectionWrapper(dataSource);
                PreparedStatementWrapper prep = connection.prepareStatement(select)) {
            ResultSetWrapper rs = prep.executeQuery();
            GenericType<String> gts = new GenericClass<>(String.class);
            while (rs.next()) {
                String title = manager.get(rs.getResultSet(), 2, gts);
                Content contentResult = manager.get(rs.getResultSet(), 5, contentProperty);
                System.out.println(title + " -> " + contentResult);
                assertEquals(content, contentResult);
            }
        }
    }

    @Test
    public void testObjectToJsonMapper3() {
        // database json type
        SqlTypeMappingManager manager = new SqlTypeMappingManager();
        assertNull(manager.getSqlType(Content.class));

        sqlExecutor.execute("delete from cms_article");

        GenericType<Content> type = new GenericClass<>(Content.class);

        manager.regist(
                new ObjectToJsonMapper<>(type).addAllow("cms_article", "content2").addAllow("cms_article", "content3"));

        String insert = "INSERT INTO `db_test`.`cms_article` (`ID`, `title`, `content2`, `content3`) VALUES (null, ?, ?, ?)";
        Content content3 = new Content();
        content3.setDescp("c_descp");
        content3.setImg("c_img");
        Content content2 = new Content();
        content2.setDescp("c2_descp");
        content2.setImg("c2_img");

        try (ConnectionWrapper connection = JdbcUtils.getConnectionWrapper(dataSource);
                PreparedStatementWrapper prep = connection.prepareStatement(insert)) {
            manager.set(prep.getPreparedStatement(), 1, Randoms.getString(6));
            manager.set(prep.getPreparedStatement(), 2, content2, type);
            manager.set(prep.getPreparedStatement(), 3, content3, type);

            boolean res = prep.execute();
            System.out.println(res);
        }

        String select = "select * from cms_article";
        try (ConnectionWrapper connection = JdbcUtils.getConnectionWrapper(dataSource);
                PreparedStatementWrapper prep = connection.prepareStatement(select)) {
            ResultSetWrapper rs = prep.executeQuery();
            GenericType<String> gts = new GenericClass<>(String.class);
            while (rs.next()) {
                String title = manager.get(rs.getResultSet(), 2, gts);

                Content content3Result = manager.get(rs.getResultSet(), 5, type);
                System.out.println(title + " -> " + content3Result);
                assertEquals(content3, content3Result);

                Content content2Result = manager.get(rs.getResultSet(), 4, type);
                System.out.println(title + " -> " + content2Result);
                assertEquals(content2, content2Result);
            }
        }
    }

    @Test(expectedExceptions = ClassCastException.class)
    public void testObjectToJsonMapper4() {
        // database json type
        SqlTypeMappingManager manager = new SqlTypeMappingManager();
        assertNull(manager.getSqlType(Content.class));

        sqlExecutor.execute("delete from cms_article");

        GenericType<Content> type = new GenericClass<>(Content.class);

        manager.regist(new ObjectToJsonMapper<>(type).addAllow("cms_article", "content3"));
        // 因为添加了许可条件，则会进行表名，列名的筛选
        // 只添加了表名的content3的映射，下面使用Content对象对content2获取时就会出现错误

        String insert = "INSERT INTO `db_test`.`cms_article` (`ID`, `title`, `content2`, `content3`) VALUES (null, ?, ?, ?)";
        Content content3 = new Content();
        content3.setDescp("c_descp");
        content3.setImg("c_img");
        Content content2 = new Content();
        content2.setDescp("c2_descp");
        content2.setImg("c2_img");

        try (ConnectionWrapper connection = JdbcUtils.getConnectionWrapper(dataSource);
                PreparedStatementWrapper prep = connection.prepareStatement(insert)) {
            manager.set(prep.getPreparedStatement(), 1, Randoms.getString(6));
            manager.set(prep.getPreparedStatement(), 2, content2, type);
            manager.set(prep.getPreparedStatement(), 3, content3, type);

            boolean res = prep.execute();
            System.out.println(res);
        }

        String select = "select * from cms_article";
        try (ConnectionWrapper connection = JdbcUtils.getConnectionWrapper(dataSource);
                PreparedStatementWrapper prep = connection.prepareStatement(select)) {
            ResultSetWrapper rs = prep.executeQuery();
            GenericType<String> gts = new GenericClass<>(String.class);
            while (rs.next()) {
                String title = manager.get(rs.getResultSet(), 2, gts);

                Content content3Result = manager.get(rs.getResultSet(), 5, type);
                System.out.println(title + " -> " + content3Result);
                assertEquals(content3, content3Result);

                // 因为添加了许可条件，则会进行表名，列名的筛选，而又没有添加content2列进入映射
                Content content2Result = manager.get(rs.getResultSet(), 4, type);
                System.out.println(title + " -> " + content2Result);
                assertEquals(content2, content2Result);
            }
        }
    }
}
