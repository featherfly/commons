
package cn.featherfly.common.bean.vo;

public class Address {
	private String name;
	
	private int num;

	private Zipcode zipcode;
	
	public Zipcode getZipcode() {
		return zipcode;
	}

	public void setZipcode(Zipcode zipcode) {
		this.zipcode = zipcode;
	}

	/**
	 * @return 返回name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name 设置name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return 返回num
	 */
	public int getNum() {
		return num;
	}

	/**
	 * @param num 设置num
	 */
	public void setNum(int num) {
		this.num = num;
	}
}
