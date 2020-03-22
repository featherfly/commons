
package cn.featherfly.common.bean.vo;

/**
 * <p>
 * 类的说明放这里
 * </p>
 * <p>
 * 创建时间 2010-11-26 - 上午10:39:40
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
public class Zipcode {

	public Zipcode() {
		
	}
	public Zipcode(String code) {
		this.code = code;
	}
	
	private String code;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
