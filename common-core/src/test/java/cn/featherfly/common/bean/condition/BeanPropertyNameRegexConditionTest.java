
package cn.featherfly.common.bean.condition;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

import org.testng.annotations.Test;

import cn.featherfly.common.bean.BeanDescriptor;
import cn.featherfly.common.bean.matcher.BeanPropertyNameRegexMatcher;
import cn.featherfly.common.bean.vo.Person;


/**
 */
public class BeanPropertyNameRegexConditionTest {

	@Test
	public void test() {
		BeanDescriptor<Person> bd = BeanDescriptor.getBeanDescriptor(Person.class);
		assertNotNull(bd.findBeanProperty(new BeanPropertyNameRegexMatcher("name")));
		assertNotNull(bd.findBeanProperty(new BeanPropertyNameRegexMatcher("idCard")));
		assertNotNull(bd.findBeanProperty(new BeanPropertyNameRegexMatcher("availa\\w+")));
		assertNull(bd.findBeanProperty(new BeanPropertyNameRegexMatcher("fffff")));
	}
}
