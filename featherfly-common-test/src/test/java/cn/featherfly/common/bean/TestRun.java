
package cn.featherfly.common.bean;

import java.util.Iterator;

import cn.featherfly.common.bean.vo.Person;

import cn.featherfly.common.bean.BeanDescriptor;
import cn.featherfly.common.bean.BeanProperty;


/**
 * <p>
 * 类的说明放这里
 * </p>
 * <p>
 * 创建时间 2011-1-24 - 下午02:40:58
 * </p>
 * <blockquote>
 * <h4>历史修改记录</h4>
 * <ul>
 * <li>修改人 修改时间 修改描述
 * </ul>
 * </blockquote>
 * <p>
 * copyright featherfly 2010-2020, all rights reserved.
 * </p>
 *
 * @author zhongj
 * @author cdpws r&d
 * @since 1.0
 * @version 1.0
 */
public class TestRun {
	public static void main(String[] args) {
		BeanDescriptor<Person> bd = BeanDescriptor.getBeanDescriptor(Person.class);
		Iterator<BeanProperty<?>> iter = bd.getBeanProperties().iterator();
		Person person = new Person();
		while (iter.hasNext()) {
			BeanProperty<?> p = iter.next();
//			System.out.println(p + " --- " + p.getExtendsFrom().getName());
		}
		bd.getBeanProperty("name").setValue(person, "yufei");
		System.out.println(person.getName());
	}
}
