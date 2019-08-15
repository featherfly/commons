package cn.featherfly.common.net.mail;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 邮件内容
 * </p>
 *
 * @author 钟冀
 */
public class MailBody {

	private String subject;
	private String content;
	private String contentHtml;
	private boolean mimeContent;
	private Date sentDate;
	private Date receivedDate;
	private String[] flags;

	private Map<String, String> properties = new HashMap<String, String>();

	private List<MailAttach> mailAttachs = new ArrayList<MailAttach>();

	/**
	 * @param subject 邮件主题
	 * @param content 邮件内容
	 */
	public MailBody(String subject, String content) {
		this(subject, content, false);
	}
	
	/**
	 * @param subject 邮件主题
	 * @param content 邮件内容
	 * @param mimeContent 是否是MIME类型
	 */
	public MailBody(String subject, String content, boolean mimeContent) {
	    this(subject, content, mimeContent, new MailAttach[]{});
	}

	/**
	 * @param subject 邮件主题
	 * @param content 邮件内容
	 * @param mimeContent 是否是MIME类型
	 * @param mailAttachs 附件数组
	 */
	public MailBody(String subject, String content, boolean mimeContent,
			MailAttach...mailAttachs) {
		this.subject = subject;
		this.content = content;
		this.mimeContent = mimeContent;
		addMailAttachs(mailAttachs);
	}

	/**
	 * <p>
	 * 添加邮件附件
	 * </p>
	 * @param mailAttach 邮件附件
	 */
	public void addMailAttach(MailAttach mailAttach) {
		if (mailAttach != null) {
			this.mailAttachs.add(mailAttach);
		}
	}
	/**
	 * <p>
	 * 添加邮件附件
	 * </p>
	 * @param mailAttachs 邮件附件
	 */
	public void addMailAttachs(MailAttach...mailAttachs) {
		if (mailAttachs != null) {
			for (MailAttach mailAttach : mailAttachs) {
				addMailAttach(mailAttach);
			}
		}
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the mimeContent
	 */
	public boolean isMimeContent() {
		return mimeContent;
	}

	/**
	 * @param mimeContent the mimeContent to set
	 */
	public void setMimeContent(boolean mimeContent) {
		this.mimeContent = mimeContent;
	}

	/**
	 * @return the affixFlag
	 */
	public boolean hasAttach() {
		return !mailAttachs.isEmpty();
	}

	/**
	 * 返回mailAttachs
	 * @return mailAttachs
	 */
	public List<MailAttach> getMailAttachs() {
		return mailAttachs;
	}

	/**
	 * 返回contentHtml
	 * @return contentHtml
	 */
	public String getContentHtml() {
		return contentHtml;
	}

	/**
	 * 设置contentHtml
	 * @param contentHtml contentHtml
	 */
	public void setContentHtml(String contentHtml) {
		this.contentHtml = contentHtml;
	}

	/**
	 * <p>
	 * 设置属性
	 * </p>
	 * @param name 属性名
	 * @param value 属性值
	 */
	public void setProperty(String name, String value) {
		properties.put(name, value);
	}
	/**
	 * <p>
	 * 获取属性
	 * </p>
	 * @param name 属性名
	 * @return 属性值
	 */
	public String getProperty(String name) {
		return properties.get(name);
	}

	/**
	 * <p>
	 * 获取所有属性
	 * </p>
	 * @return 所有属性的键值对
	 */
	public Map<String, String> getProperties() {
		return properties;
	}

	/**
	 * 返回sentDate
	 * @return sentDate
	 */
	public Date getSentDate() {
		return sentDate;
	}

	/**
	 * 设置sentDate
	 * @param sentDate sentDate
	 */
	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}

	/**
	 * 返回receivedDate
	 * @return receivedDate
	 */
	public Date getReceivedDate() {
		return receivedDate;
	}

	/**
	 * 设置receivedDate
	 * @param receivedDate receivedDate
	 */
	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}

	/**
	 * 返回flags
	 * @return flags
	 */
	public String[] getFlags() {
		return flags;
	}

	/**
	 * 设置flags
	 * @param flags flags
	 */
	public void setFlags(String[] flags) {
		this.flags = flags;
	}
}
