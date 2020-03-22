
package cn.featherfly.common.mail;


/**
 * <p>
 * 邮件处理器
 * </p>
 *
 * @author 钟冀
 */
public interface MailHandler<E> {
	/**
	 * <p>
	 * 处理传入对象
	 * </p>
	 * @param e e
	 */
	void handle(E e);
}
