
package cn.featherfly.common.bean;

import static org.testng.Assert.assertEquals;

import java.util.Iterator;

import org.testng.annotations.Test;

import cn.featherfly.common.bean.vo.Address;
import cn.featherfly.common.bean.vo.Person;
import cn.featherfly.common.bean.vo.User;

import cn.featherfly.common.bean.BeanDescriptor;
import cn.featherfly.common.bean.BeanProperty;


public class BeanDescriptorTest {
	@Test
	public void test1(){
		User user = new User();
		String name = "yufei";
		int age = 20;
		boolean available = false;
		BeanDescriptor<User> bd = BeanDescriptor.getBeanDescriptor(User.class);
		BeanProperty pName = bd.getBeanProperty("name");
		BeanProperty pAge = bd.getBeanProperty("age");
		BeanProperty pAva = bd.getBeanProperty("available");
		pAge.setValue(user, age);
		pName.setValue(user, name);
		pAva.setValue(user, available);
		assertEquals(name, pName.getValue(user));
		assertEquals(age, pAge.getValue(user));
//		assertEquals(available, pAva.getValue(user));

		name = "featherfly";
		bd.setProperty(user, "name", name);
		assertEquals(name, bd.getProperty(user, "name"));
	}

	@Test
	public void test2(){
		User user = new User();
		String name = "yufei";

		BeanDescriptor<User> bd = BeanDescriptor.getBeanDescriptor(User.class);
		BeanProperty pName = bd.getBeanProperty("name");
		pName.setValue(user, name);
		assertEquals(name, pName.getValue(user));

		name = "featherfly";
		bd.setProperty(user, "name", name);
		assertEquals(name, bd.getProperty(user, "name"));
	}

	@Test
	public void test3(){
		String c = "610000";

		User user = new User();
		BeanDescriptor<User> bdUser = BeanDescriptor.getBeanDescriptor(User.class);
		assertEquals(bdUser.getProperty(user, "address.zipcode.code"),null);
		bdUser.setProperty(user, "address.zipcode.code", c);
		assertEquals(bdUser.getProperty(user, "address.zipcode.code"),c);
	}

	@Test
	public void test4(){
		String name = "yufei";
		String idCard = "1984";

		Person p = new Person();
		p.setName(name);
		p.setIdCard(idCard);

		BeanDescriptor<Person> bdPerson = BeanDescriptor.getBeanDescriptor(Person.class);
		assertEquals(bdPerson.getProperty(p, "idCard"), idCard);

		assertEquals(bdPerson.getProperty(p, "name"), name);

		Iterator<BeanProperty<?>> iter = bdPerson.getBeanProperties().iterator();
		while(iter.hasNext()){
			BeanProperty bp = iter.next();
			System.out.println(bp.getName());
		}
	}

	@Test
	public void test5(){
		User user = new User();
		BeanDescriptor<User> bd = BeanDescriptor.getBeanDescriptor(User.class);
		bd.addProperty(user, "names", "yufei");
		bd.addProperty(user, "names", "yi");
		assertEquals(user.getNames().size() , 2);
		System.out.println(user.getNames());

		bd.addProperty(user, "addresses", new Address());
		bd.addProperty(user, "addresses", new Address());

		assertEquals(user.getAddresses().size() , 2);
		System.out.println(user.getAddresses());
	}

	public static void main(String[] args) {
		User user = new User();
		BeanDescriptor<User> bd = BeanDescriptor.getBeanDescriptor(User.class);

		bd.setProperty(user, "addresses.name", "成都市");

		System.out.println(user.getAddresses());
	}

	@Test
	public void test6(){
		User user = new User();
		BeanDescriptor<User> bd = BeanDescriptor.getBeanDescriptor(User.class);

		bd.setProperty(user, "addresses.name", "成都市");

		System.out.println(user.getAddresses());
	}

	@Test
	public void test(){
		java.beans.BeanDescriptor bd = new java.beans.BeanDescriptor(User.class);
		String addName = "人民南路";
		// 只是在放在hashtable中而已，并没有和具体的对象绑定
		bd.setValue("address.name", addName);
		assertEquals(addName, bd.getValue("address.name"));
	}
}
