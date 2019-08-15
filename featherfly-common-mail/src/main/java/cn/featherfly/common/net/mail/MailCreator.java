
package cn.featherfly.common.net.mail;

import javax.mail.Message;

/**
 * <p>
 * MailCreator
 * </p>
 *
 * @author 钟冀
 */
public interface MailCreator<E> {
	/**
	 * <p>
	 * 从Message创建mail对象
	 * </p>
	 * @param message message
	 * @return mail
	 */
	E create(Message message);
}
