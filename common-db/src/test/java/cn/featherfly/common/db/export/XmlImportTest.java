//
//package cn.featherfly.common.db.export;
//
//import java.io.FileReader;
//
//import org.apache.commons.dbcp.BasicDataSource;
//import org.apache.log4j.xml.DOMConfigurator;
//
//import cn.featherfly.common.db.data.DataImportor.ExistPolicy;
//import cn.featherfly.common.db.data.xml.XmlImportorMysqlImpl;
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
//public class XmlImportTest {
//	public static void main(String[] args) throws Exception {
//		String database = "bmp_cd2_i";
//		BasicDataSource dataSource = new BasicDataSource();
//		dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/yufei1");
////		dataSource.setUrl("jdbc:mysql://192.168.1.32:3306/" + database);
//		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//		dataSource.setUsername("root");
//		dataSource.setPassword("yi");
//
//		DOMConfigurator.configure(XmlImportTest.class.getResource("/log4j.xml"));
//
//		XmlImportorMysqlImpl importor = new XmlImportorMysqlImpl();
//		importor.setDataSource(dataSource);
//
//		importor.setFkCheck(false);
//		importor.setExistPolicy(ExistPolicy.update);
//
//		long start = System.currentTimeMillis();
////		importor.imp(new FileReader("c:/zx_sdo_1.xml"));
////		importor.imp(new FileReader("c:/zx_sdo.xml"));
////		importor.imp(new FileReader("c:/bmp_cd2.xml"));
////		importor.imp(new FileReader("c:/cts.xml"));
//		importor.imp(new FileReader("c:/yufei.xml"));
////		importor.imp(new FileReader(file));
////		importor.imp(new InputStreamReader(new GZIPInputStream(new FileInputStream(file)), "UTF-8"));
//		long end = System.currentTimeMillis();
//
//		System.out.println("start : " + start);
//		System.out.println("end : " + end);
//		System.out.println("use : " + (end - start));
//		System.out.println("use : " + (end - start) / 1000);
//
////		exportor.exportData(
////				"select * from PERSON where ID = '1'",
////				"PERSON",
////				new OutputStreamWriter(System.out, "UTF-8"));
//	}
//}
