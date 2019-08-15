package cn.featherfly.common.net.mail;


/**
 * <p>
 * 发送邮件的账户
 * </p>
 *
 * @author 钟冀
 */
public class MailUser {

	// 发送邮件用户名称
	private String username;

	// 发件人邮箱host
	private String domain;

	// 发件人邮件地址
	private String address;

	// 发送邮件用户密码
	private String password;

	/**
	 * @param address 邮件地址
	 * @param password 密码
	 */
	public MailUser(String address, String password) {
		setPassword(password);
		setAddress(address);
	}
	/**
	 * @param username 用户名
	 * @param domain 邮件服务器domain
	 * @param password 密码
	 */
	public MailUser(String username, String domain, String password) {
		this.address = username + "@" + domain;
		this.username = username;
		this.domain = domain;
		this.password = password;
	}

	/**
	 * 返回address
	 * @return address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 设置address
	 * @param address address
	 */
	public void setAddress(String address) {
		this.address = address;
		if (address != null && address.contains("@")) {
			String[] s = address.split("@");
			username = s[0];
			domain = s[1];
		}
	}

	/**
	 * 返回password
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 设置password
	 * @param password password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 返回username
	 * @return username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 返回domain
	 * @return domain
	 */
	public String getDomain() {
		return domain;
	}
}
