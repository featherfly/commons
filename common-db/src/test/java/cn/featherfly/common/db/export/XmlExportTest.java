//
//package cn.featherfly.common.db.export;
//
//import java.io.FileOutputStream;
//import java.io.FileReader;
//import java.io.OutputStreamWriter;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.zip.GZIPOutputStream;
//
//import org.apache.commons.dbcp.BasicDataSource;
//
//import cn.featherfly.common.db.data.xml.XmlExportor;
//
///**
// * <p>
// * 类的说明放这里
// * </p>
// * <p>
// * copyright cdthgk 2010-2020, all rights reserved.
// * </p>
// *
// * @author 钟冀
// */
//public class XmlExportTest {
//	public static void main(String[] args) throws Exception {
////		String database = "yufei";
////		BasicDataSource dataSource = new BasicDataSource();
////		dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/" + database);
////		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
////		dataSource.setUsername("root");
////		dataSource.setPassword("yi");
//
//		String database = "bmp_cd2";
//		BasicDataSource dataSource = new BasicDataSource();
//		dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/yufei");
////		dataSource.setUrl("jdbc:mysql://192.168.1.32:3306/" + database);
//		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//		dataSource.setUsername("root");
//		dataSource.setPassword("yi");
//		XmlExportor exportor = new XmlExportor();
//		exportor.setDataSource(dataSource);
//
//		long start = System.currentTimeMillis();
//
//		exportor.exportDatabase(new OutputStreamWriter(
//				new FileOutputStream("c:/yufei.xml"), "UTF-8"));
////		exportor.exportDatabase(new OutputStreamWriter(
////				new FileOutputStream("c:/bmp_cd2.xml"), "UTF-8"));
//
////		exportor.exportData("select * from CORE_TPL_SOLUTION", new OutputStreamWriter(
////				new FileOutputStream("c:/cts.xml"), "UTF-8"));
////		exportor.exportData("select * from sys_department where organ_id = '1'", new OutputStreamWriter(
////				new FileOutputStream("c:/sys_de.xml"), "UTF-8"));
//
////		exportor.exportDatabase(new OutputStreamWriter(
////				new GZIPOutputStream(new FileOutputStream("c:/yufei_big.xml")), "UTF-8"));
//		long end = System.currentTimeMillis();
//
//		System.out.println("start : " + start);
//		System.out.println("end : " + end);
//		System.out.println("use : " + (end - start));
//		System.out.println("use : " + (end - start) / 1000);
//
////		Collection<String> tables = new ArrayList<String>();
////		tables.add("sys_organization");
////		tables.add("sys_department");
////		exportor.exportTable(tables, new OutputStreamWriter(
////				new FileOutputStream("c:/org_dep.xml"), "UTF-8"));
//
////		exportor.exportData(
////				"select * from PERSON where ID = '1'",
////				"PERSON",
////				new OutputStreamWriter(System.out, "UTF-8"));
//	}
//}
