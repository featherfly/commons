//
//package cn.featherfly.common.bean.javassist;
//
//import java.util.Date;
//
//import cn.featherfly.common.bean.BeanProperty;
//import cn.featherfly.common.bean.BeanPropertyFactory;
//
///**
// * <p>
// * 类的说明放这里
// * </p>
// * <p>
// * copyright featherfly 2010-2020, all rights reserved.
// * </p>
// * 
// * @author 钟冀
//*
// */
//public class TestBeanPropertyFactory {
//	
//	int max = 100000;
//	
//	BeanProperty beanProperty;
//	
//	public void test(String name) {
//		System.out.println("TestBeanPropertyFactory: max = "+max);
//		Person p = new Person();
//		if (beanProperty == null) {
//			beanProperty = BeanPropertyFactory.create(Person.class, name);
//		}
//		Date start = new Date();
//		for (int i = 0; i < max; i++) {
//			beanProperty.setValue(p, "name_"+i);
//		}
//		Date end = new Date();
//		System.out.println("set time :"+ (end.getTime() - start.getTime()));
//		start = new Date();
//		for (int i = 0; i < max; i++) {
//			beanProperty.getValue(p);
//		}
//		end = new Date();
//		System.out.println("get time :"+ (end.getTime() - start.getTime()));
//	}
//	
//	public static void main(String[] args) {
////		TestBeanPropertyFactory t = new TestBeanPropertyFactory();
////		t.max = 3000000;
////		t.test("name");
//		Person p = new Person();
//		BeanProperty sex = BeanPropertyFactory.create(Person.class, "sex");
//		sex.getValue(p);
//
//		BeanProperty id = BeanPropertyFactory.create(Person.class, "idCard");
//		id.setValue(p, "1212121");
//	}
//}
