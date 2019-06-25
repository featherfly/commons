
package cn.featherfly.common.bean.javassist;

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
public class Person {
	
	public String say(String message) {
		System.out.println("i' am " + name);
		System.out.println("i' am " + age + " years old");
		System.out.println(message);
		return message;
	}
	
	private String name;
	private Integer age;
	private String idCard = "21212121";
	private String sex;
	/**
	 * 设置sex
	 * @param sex sex
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
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
	/**
	 * 返回age
	 * @return age
	 */
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
	 * 返回idCard
	 * @return idCard
	 */
	public String getIdCard() {
		return idCard;
	}
}
