
package cn.featherfly.common.bean.javassist;

/**
 * <p>
 * 类的说明放这里
 * </p>
 * <p>
 * copyright featherfly 2010-2020, all rights reserved.
 * </p>
 * 
 * @author 钟冀
*
 */
public class Student extends Person{
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String say(String message) {
		System.out.println("i' am " + getName());
		System.out.println("i' am " + getAge() + " years old");
		System.out.println("i' am study at " + school + " school");
		System.out.println(message);
		return message;
	}
	
	private String school;

	/**
	 * 返回school
	 * @return school
	 */
	public String getSchool() {
		return school;
	}

	/**
	 * 设置school
	 * @param school school
	 */
	public void setSchool(String school) {
		this.school = school;
	}
}
