package cn.featherfly.common.net.mail;


/**
 * <p>
 * 邮件实体
 * </p>
 *
 * @author 钟冀
 */
public class Mail {

	/**
	 *
	 */
	public Mail() {

	}

	private String id;

	private MailUser mailUser;

	private MailAddress mailAddress;

	private MailBody mailBody;

	/**
	 * 返回id
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置id
	 * @param id id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 返回mailUser
	 * @return mailUser
	 */
	public MailUser getMailUser() {
		return mailUser;
	}

	/**
	 * 设置mailUser
	 * @param mailUser mailUser
	 */
	public void setMailUser(MailUser mailUser) {
		this.mailUser = mailUser;
	}

	/**
	 * 返回mailAddress
	 * @return mailAddress
	 */
	public MailAddress getMailAddress() {
		return mailAddress;
	}

	/**
	 * 设置mailAddress
	 * @param mailAddress mailAddress
	 */
	public void setMailAddress(MailAddress mailAddress) {
		this.mailAddress = mailAddress;
	}

	/**
	 * 返回mailBody
	 * @return mailBody
	 */
	public MailBody getMailBody() {
		return mailBody;
	}

	/**
	 * 设置mailBody
	 * @param mailBody mailBody
	 */
	public void setMailBody(MailBody mailBody) {
		this.mailBody = mailBody;
	}
}
