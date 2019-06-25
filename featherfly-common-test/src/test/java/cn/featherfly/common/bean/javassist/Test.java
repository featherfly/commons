
package cn.featherfly.common.bean.javassist;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.Modifier;

import cn.featherfly.common.bean.BeanDescriptor;
import cn.featherfly.common.bean.BeanProperty;
import cn.featherfly.common.lang.WordUtils;

/**
 * <p>
 * 类的说明放这里
 * </p>
 * <p>
 * copyright featherfly 2010-2020, all rights reserved.
 * </p>
 * 
 * @author zhongj
*
 */
public class Test {
	
	private int max = 10000000;
	
	private String name = "name";
	
	public void testInvokeMethod() {
		System.out.println("testInvokeMethod: max = "+max);		
		Person p = new Person();
		Date start = new Date();
		for (int i = 0; i < max; i++) {
			p.setName("name_"+i);
		}
		Date end = new Date();
		System.out.println("set time :"+ (end.getTime() - start.getTime()));
		start = new Date();
		for (int i = 0; i < max; i++) {
			p.getName();
		}
		end = new Date();
		System.out.println("get time :"+ (end.getTime() - start.getTime()));
	}
	
	public void testBeanProperty() {
		System.out.println("testBeanProperty: max = "+max);
		BeanDescriptor<Person> bd = BeanDescriptor.getBeanDescriptor(Person.class);
		BeanProperty beanProperty = bd.getBeanProperty(name);
		Person p = new Person();
		Date start = new Date();
		for (int i = 0; i < max; i++) {
			beanProperty.setValue(p, "name_"+i);;
		}
		Date end = new Date();
		System.out.println("set time :"+ (end.getTime() - start.getTime()));
		start = new Date();
		for (int i = 0; i < max; i++) {
			beanProperty.getValue(p);
		}
		end = new Date();
		System.out.println("get time :"+ (end.getTime() - start.getTime()));
	}
	
	public void testBeanPropertyWithJassit() throws Exception {
		System.out.println("testBeanPropertyWithJassit: max = "+max);
		Person p = new Person();
		BeanProperty beanProperty = create(Person.class, String.class, Person.class.getDeclaredField("name") 
				, Person.class.getDeclaredMethod("setName", String.class)
				, Person.class.getDeclaredMethod("getName", new Class[]{})
				, name);
		Date start = new Date();
		for (int i = 0; i < max; i++) {
			beanProperty.setValue(p, "name_"+i);
		}
		Date end = new Date();
		System.out.println("set time :"+ (end.getTime() - start.getTime()));
		start = new Date();
		for (int i = 0; i < max; i++) {
			beanProperty.getValue(p);
		}
		end = new Date();
		System.out.println("get time :"+ (end.getTime() - start.getTime()));
	}
	
	public static <T extends Object, V extends Object> BeanProperty create(Class<T> type,Class<V> property, Field field, Method setMethod, Method getMethod, String name) throws Exception {
		ClassPool pool = ClassPool.getDefault();
		
		String upperCaseFirstName = WordUtils.upperCaseFirst(name);
		String propertyClassName = type.getPackage()+"._"+type.getSimpleName()+upperCaseFirstName+"PropertyTemp";
		CtClass beanPropertyClass = pool.makeClass(propertyClassName);
		beanPropertyClass.setSuperclass(pool.getCtClass(BeanProperty.class.getName()));
		
//		beanPropertyClass.addInterface(pool.getCtClass(BeanPropertyAccess.class.getName()));
		
		
		CtConstructor constraConstructor = new CtConstructor(
				new CtClass[]{
					pool.getCtClass(Field.class.getName()),
					pool.getCtClass(Method.class.getName()),
					pool.getCtClass(Method.class.getName())
				}, beanPropertyClass);
		constraConstructor.setModifiers(Modifier.PUBLIC);
		constraConstructor.setBody("super(" + type.getName() + ".class, $1, $2, $3);");
		beanPropertyClass.addConstructor(constraConstructor);
		
//		CtClass propertyType = pool.get(property.getName());
//		CtClass classType = pool.get(type.getName());
		CtClass objectType = pool.get(Object.class.getName());
		
//		String setterName = "set"+upperCaseFirstName;
		String setterName = setMethod.getName();
		CtMethod setter = new CtMethod(CtClass.voidType, "setValue",
				new CtClass[] {objectType, objectType}, beanPropertyClass);
		String setterBody = String.format("{" 
				+ "%1$s value = (%1$s) $2;"
				+ "%2$s obj = (%2$s)$1;" 
				+ "obj.%3$s(value);" 
				+ "}", field.getType().getName(), type.getName(), setterName);
		System.out.println(setterBody);
		setter.setBody(setterBody);
		setter.setModifiers(Modifier.PUBLIC);
		beanPropertyClass.addMethod(setter);
		
		String getterName = "get"+upperCaseFirstName;
		CtMethod getter = new CtMethod(objectType, "getValue",
				new CtClass[] {objectType}, beanPropertyClass);
		String getterBody = String.format("{"
				+ "%1$s obj = (%1$s)$1;" 
				+ "return obj.%2$s();"
				+ "}", type.getName(), getterName);
		getter.setBody(getterBody);
		getter.setModifiers(Modifier.PUBLIC);
		beanPropertyClass.addMethod(getter);
		
//		CtMethod[] ms = beanPropertyClass.getDeclaredMethods();
//		for (CtMethod ctMethod : ms) {
//			System.out.println(ctMethod.getLongName());
//			System.out.println(ctMethod.getReturnType().getName());
//		}
		
		
		beanPropertyClass.toClass();
		beanPropertyClass.detach();
		
		return (BeanProperty) Class.forName(propertyClassName).getConstructor(Field.class, Method.class, Method.class)
			.newInstance(field, setMethod, getMethod);
	}
	
	public static void main(String[] args) throws Exception {
		Test t = new Test();
		t.max = 3000000;
		t.testInvokeMethod();
		t.testBeanProperty();
		t.testBeanPropertyWithJassit();
		
		
//		BeanProperty beanProperty = create(Person.class, Integer.class, Person.class.getDeclaredField("age") 
//				, Person.class.getDeclaredMethod("setAge", Integer.class)
//				, Person.class.getDeclaredMethod("getAge", new Class[]{})
//				, "age");
//		Person p = new Person();
//		
//		beanProperty.setValue(p, 18);		
//		System.out.println(beanProperty.getValue(p));
//		beanProperty.setValue(p, 25);		
//		System.out.println(beanProperty.getValue(p));
	}
}
