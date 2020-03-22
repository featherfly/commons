
package cn.featherfly.common.bean;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import org.testng.annotations.Test;

import cn.featherfly.common.bean.vo.Address;
import cn.featherfly.common.bean.vo.User;
import cn.featherfly.common.bean.vo.Zipcode;

import cn.featherfly.common.bean.BeanDescriptor;
import cn.featherfly.common.bean.BeanUtils;
import cn.featherfly.common.bean.rule.CopyRuleEnum;


public class BeanUtilsTestPerformence {

	User user = new User();

	Date start;

	Date end;

	long time;

//	int max = 2000000;
	int max = 2000000;

	@Test
	public void test1() throws Exception{

		System.out.println("直接方法调用：");
		start = new Date();

		for (int i = 0; i < max; i++) {
			if(user.getAddress()==null){
				Address address = new Address();
				if(address.getZipcode()==null){
					address.setZipcode(new Zipcode());
				}
				user.setAddress(address);
			}
			user.getAddress().getZipcode().setCode("610000");
		}
		end  = new Date();
		time = end.getTime() - start.getTime();
		System.out.println("set:"+time);
		start = new Date();
		for (int i = 0; i < max; i++) {
			user.getAddress().getZipcode().getCode();
		}
		end  = new Date();
		time = end.getTime() - start.getTime();
		System.out.println("get:"+time);

		// ********************************************************************
		//
		// ********************************************************************

		System.out.println(BeanUtils.class);
		start = new Date();
		for (int i = 0; i < max; i++) {
			BeanUtils.setProperty(user, "address.zipcode.code", "610000");
		}
		end  = new Date();
		time = end.getTime() - start.getTime();
		System.out.println("setProperty:"+time);
		start = new Date();
		for (int i = 0; i < max; i++) {
			BeanUtils.getProperty(user, "address.zipcode.code");
		}
		end  = new Date();
		time = end.getTime() - start.getTime();
		System.out.println("getProperty:"+time);
		
		System.out.println(BeanUtils.class  + "第二轮，初始化过后");
        start = new Date();
        for (int i = 0; i < max; i++) {
            BeanUtils.setProperty(user, "address.zipcode.code", "610000");
        }
        end  = new Date();
        time = end.getTime() - start.getTime();
        System.out.println("setProperty:"+time);
        start = new Date();
        for (int i = 0; i < max; i++) {
            BeanUtils.getProperty(user, "address.zipcode.code");
        }
        end  = new Date();
        time = end.getTime() - start.getTime();
        System.out.println("getProperty:"+time);

		// ********************************************************************
		//	apache
		// ********************************************************************
		System.out.println(org.apache.commons.beanutils.BeanUtils.class);
		start = new Date();
		for (int i = 0; i < max; i++) {
			org.apache.commons.beanutils.BeanUtils.setProperty(user, "address.zipcode.code", "610000");
		}
		end  = new Date();
		time = end.getTime() - start.getTime();
		System.out.println("setProperty:"+time);
		start = new Date();
		for (int i = 0; i < max; i++) {
			org.apache.commons.beanutils.BeanUtils.getProperty(user, "address.zipcode.code");
		}
		end  = new Date();
		time = end.getTime() - start.getTime();
		System.out.println("getProperty:"+time);
		
		System.out.println(org.apache.commons.beanutils.BeanUtils.class + "第二轮，初始化过后");
        start = new Date();
        for (int i = 0; i < max; i++) {
            org.apache.commons.beanutils.BeanUtils.setProperty(user, "address.zipcode.code", "610000");
        }
        end  = new Date();
        time = end.getTime() - start.getTime();
        System.out.println("setProperty:"+time);
        start = new Date();
        for (int i = 0; i < max; i++) {
            org.apache.commons.beanutils.BeanUtils.getProperty(user, "address.zipcode.code");
        }
        end  = new Date();
        time = end.getTime() - start.getTime();
        System.out.println("getProperty:"+time);
	}

	//@Test
	public void testCopy() throws IllegalAccessException, InvocationTargetException{
		int ua = 18;
		String un = "yi";
		User u = new User();
		int num = 100;
		u.setAge(ua);
		u.setName(un);
		Address address = new Address();
		address.setNum(num);
		u.setAddress(address);

		int usera = 22;
		String usern = "yufei";
		user.setAge(usera);
		user.setName(usern);

		start = new Date();
		for (int i = 0; i < max; i++) {
			BeanUtils.copyProperties(u, user, CopyRuleEnum.ignoreCaseNull);
		}
		end  = new Date();
		System.out.println("BeanUtils.copyProperties CopyRuleEnum.ignoreCaseNull:"+(end.getTime() - start.getTime()));

		start = new Date();
		for (int i = 0; i < max; i++) {
			BeanUtils.copyProperties(u, user);
		}
		end  = new Date();
		System.out.println("BeanUtils.copyProperties:"+(end.getTime() - start.getTime()));

		start = new Date();
		for (int i = 0; i < max; i++) {
			org.apache.commons.beanutils.BeanUtils.copyProperties(u, user);
		}
		end  = new Date();
		System.out.println("org.apache.commons.beanutils.copyProperties:"+(end.getTime() - start.getTime()));
	}
}