
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-09 16:45:09
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.db;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.Test;

import cn.featherfly.common.lang.Str;

/**
 * JdbcUtilsTest.
 *
 * @author zhongj
 */
public class JdbcUtilsTest extends JdbcTestBase {

    @Test
    public void getBigInteger() throws SQLException {
        BigInteger age = new BigInteger("18446744073709551615");
        System.err.println("set age " + age.toString());
        System.err.println("mysql bigint unsign 18446744073709551615");

        String updateAge = Str.format("update {0} set age = ? where id = 1", dialect.wrapName("user"));
        String selectAge = Str.format("select age from {0} where id = 1", dialect.wrapName("user"));

        try (Connection conn = dataSource.getConnection();) {
            try (PreparedStatement prep = conn.prepareStatement(updateAge)) {
                JdbcUtils.setParameter(prep, 1, age);
                prep.execute();
            }
            try (Statement stat = conn.createStatement(); ResultSet resultSet = stat.executeQuery(selectAge);) {
                if (resultSet.next()) {
                    BigInteger bi = JdbcUtils.getBigInteger(resultSet, 1);
                    System.err.println("get age = " + bi.toString());
                }
            }
        }
    }

    @Test
    public void setParameters() throws SQLException {
        BigInteger age = new BigInteger("18446744073709551615");
        System.err.println("set age " + age.toString());
        System.err.println("mysql bigint unsign 18446744073709551615");

        String updateAge = Str.format("update {0} set age = ? where id = 1", dialect.wrapName("user"));
        String selectAge = Str.format("select age from {0} where id = 1", dialect.wrapName("user"));

        try (Connection conn = dataSource.getConnection();) {
            try (PreparedStatement prep = conn.prepareStatement(updateAge)) {
                JdbcUtils.setParameters(prep, age);
                prep.execute();
            }
            try (Statement stat = conn.createStatement(); ResultSet resultSet = stat.executeQuery(selectAge);) {
                if (resultSet.next()) {
                    BigInteger bi = JdbcUtils.getBigInteger(resultSet, 1);
                    System.err.println("get age = " + bi.toString());
                }
            }
        }
    }
}
