
package cn.featherfly.common.bean;

import static org.testng.Assert.*;

import org.apache.commons.collections.Bag;
import org.apache.commons.lang.StringUtils;
import org.testng.annotations.Test;

import cn.featherfly.common.bean.BeanUtils;


/**
 * <p>
 * BeanUtilsTest
 * 类的说明放这里
 * </p>
 *
 * @author 钟冀
 */
public class BeanUtilsTest {

	@Test
	public void testC2P() {
		P p = new P();
		C c = new C();		
		c.setName("c->name");
		c.setN("c->n");
		BeanUtils.copyProperties(p, c);
		System.out.println(p.getName());
		assertEquals(p.getName(), c.getName());
	}
	@Test
	public void testP2C() {
		P p = new P();
		C c = new C();

		p.setName("p->name");

		BeanUtils.copyProperties(c, p);
		System.out.println(c.getName());
		assertEquals(p.getName(), c.getName());
	}
}

class P {

	private String name;

	/**
	 * 返回name
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置name
	 * @param name name
	 */
	public void setName(String name) {
		this.name = name;
	}


}

class C extends P{
	private String n;

	/**
	 * 返回n
	 * @return n
	 */
	public String getN() {
		return n;
	}

	/**
	 * 设置n
	 * @param n n
	 */
	public void setN(String n) {
		this.n = n;
	}
}