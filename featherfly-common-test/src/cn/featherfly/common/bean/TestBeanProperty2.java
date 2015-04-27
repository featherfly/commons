package cn.featherfly.common.bean;

import java.util.Collection;

import javax.annotation.Resource;

class TestBeanProperty2 {

	@Resource(name = "name field")
	private String name;
	
	private Integer age;
	
	/**
	 * 返回name
	 * @return name
	 */
	@Resource(name = "name getName")
	public String getName() {
		return name;
	}

	/**
	 * 设置name
	 * @param name name
	 */
	@Resource(name = "name setName")
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 返回age
	 * @return age
	 */
	@Resource(name = "age getAge")
	public Integer getAge() {
		return age;
	}

	/**
	 * 设置age
	 * @param age age
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/**
	 * 返回sex
	 * @return sex
	 */
	@Resource(name = "sex getSex")
	public Integer getSex() {
		return null;
	}

	/**
	 * 设置sex
	 * @param sex sex
	 */
	@Resource(name = "sex setSex")
	public void setSex(Integer sex) {
		
	}
	
	public static void main(String[] args) {
		TestBeanProperty2 t = new TestBeanProperty2();
		BeanDescriptor<TestBeanProperty2> bd = BeanDescriptor.getBeanDescriptor(TestBeanProperty2.class);
		Collection<BeanProperty> bps = bd.getBeanProperties();
		for (BeanProperty bp : bps) {
			System.out.println(bp.getAnnotation(Resource.class));
		}
	}
}