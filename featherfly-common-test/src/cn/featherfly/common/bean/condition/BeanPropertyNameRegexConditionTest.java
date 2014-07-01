
package cn.featherfly.common.bean.condition;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

import org.testng.annotations.Test;

import cn.featherfly.common.bean.vo.Person;

import cn.featherfly.common.bean.BeanDescriptor;
import cn.featherfly.common.bean.condition.BeanPropertyNameRegexCondition;


/**
 */
public class BeanPropertyNameRegexConditionTest {

	@Test
	public void test() {
		BeanDescriptor<Person> bd = BeanDescriptor.getBeanDescriptor(Person.class);
		assertNotNull(bd.findBeanProperty(new BeanPropertyNameRegexCondition("name")));
		assertNotNull(bd.findBeanProperty(new BeanPropertyNameRegexCondition("idCard")));
		assertNotNull(bd.findBeanProperty(new BeanPropertyNameRegexCondition("availa\\w+")));
		assertNull(bd.findBeanProperty(new BeanPropertyNameRegexCondition("fffff")));
	}
}
