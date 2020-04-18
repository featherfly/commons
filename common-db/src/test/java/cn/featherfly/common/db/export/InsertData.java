
package cn.featherfly.common.db.export;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sql.DataSource;

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
public class InsertData {

	public static void insertDep(DataSource dataSource, int num) throws SQLException {
		Connection conn = dataSource.getConnection();
		Statement sta = conn.createStatement();

		sta.execute("delete from sys_department");
		long start = System.currentTimeMillis();
		for (int i = 0; i < num; i++) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String sql = String.format("insert into sys_department (DEPARTMENT_ID , DEPARTMENT_NAME, create_time) values (null, '%s' , '%s')",
					"name_insert_" + i, format.format(new Date()));
			sta.addBatch(sql);
		}
		sta.executeBatch();
		long end = System.currentTimeMillis();

		System.out.println("start : " + start);
		System.out.println("end : " + end);
		System.out.println("use : " + (end - start));
		System.out.println("use : " + (end - start) / 1000);
	}
	public static void insertOtop(DataSource dataSource, int num) throws SQLException {
		Connection conn = dataSource.getConnection();
		Statement sta = conn.createStatement();

		sta.execute("delete from oto_p2");
		long start = System.currentTimeMillis();
		for (int i = 0; i < num; i++) {
			String sql = String.format("insert into oto_p2 (id , name) values (null, '%s')",
					"name_insert_" + i);
			sta.addBatch(sql);
		}
		sta.executeBatch();
		long end = System.currentTimeMillis();

		System.out.println("start : " + start);
		System.out.println("end : " + end);
		System.out.println("use : " + (end - start));
		System.out.println("use : " + (end - start) / 1000);
	}
	public static void insertUser(DataSource dataSource, int num) throws SQLException {
		Connection conn = dataSource.getConnection();
		Statement sta = conn.createStatement();

		sta.execute("delete from user");
		long start = System.currentTimeMillis();
		for (int i = 0; i < num; i++) {
			String sql = String.format("insert into user values (null, '%s', %s)",
					"name_insert_" + i, i);
			sta.addBatch(sql);
		}
		sta.executeBatch();
		long end = System.currentTimeMillis();

		System.out.println("start : " + start);
		System.out.println("end : " + end);
		System.out.println("use : " + (end - start));
		System.out.println("use : " + (end - start) / 1000);
	}
	public static void insertPerson(DataSource dataSource, int num, boolean withDate) throws Exception {
		Connection conn = dataSource.getConnection();
		Statement sta = conn.createStatement();

		sta.execute("delete from person");
		long start = System.currentTimeMillis();
		if (withDate) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (int i = 0; i < num; i++) {
				String sql = String.format("insert into person values (null, '%s', '%s')",
						"p_name_insert_" + i, format.format(new Date()));
				sta.addBatch(sql);
			}
		} else {
			for (int i = 0; i < num; i++) {
				String sql = String.format("insert into person values (null, '%s', null)",
						"p_name_insert_" + i);
				sta.addBatch(sql);
			}
		}
		sta.executeBatch();
		long end = System.currentTimeMillis();

		System.out.println("start : " + start);
		System.out.println("end : " + end);
		System.out.println("use : " + (end - start));
		System.out.println("use : " + (end - start) / 1000);
	}

	public static void main(String[] args) throws Exception {
		String database = "yufei";
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:mysql://localhost:3306/" + database);
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUsername("root");
		dataSource.setPassword("yi");

//		insertPerson(dataSource, 20000, false);

		insertDep(dataSource, 10000);

//		insertUser(dataSource, 40000);
//		insertOtop(dataSource, 40000);
	}
}
