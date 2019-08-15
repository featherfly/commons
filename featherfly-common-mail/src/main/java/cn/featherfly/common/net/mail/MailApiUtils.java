
package cn.featherfly.common.net.mail;

import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.Store;
import javax.mail.Transport;

import org.slf4j.Logger;

import cn.featherfly.common.lang.LogUtils;

/**
 * <p>
 * MailApiUtils
 * </p>
 * <p>
 * copyright featherfly 2010-2020, all rights reserved.
 * </p>
 *
 * @author 钟冀
 */
public class MailApiUtils {
	/**
	 * <p>
	 * 关闭
	 * </p>
	 * @param store store
	 * @param logger logger
	 */
	public static void close(Store store, Logger logger) {
		if (store != null && store.isConnected()) {
			try {
				store.close();
			} catch (MessagingException e) {
				LogUtils.debug(e, logger);
			}
		}
	}

	/**
	 * <p>
	 * 关闭.
	 * 关闭folder时传入false.
	 * </p>
	 * @param transport transport
	 * @param logger logger
	 */
	public static void close(Transport transport, Logger logger) {
		if (transport != null && transport.isConnected()) {
			try {
				transport.close();
			} catch (MessagingException e) {
				LogUtils.debug(e, logger);
			}
		}
	}
	/**
	 * <p>
	 * 关闭.
	 * 关闭folder时传入false.
	 * </p>
	 * @param folder folder
	 * @param logger logger
	 */
	public static void close(Folder folder, Logger logger) {
		close(folder, false, logger);
	}
	/**
	 * <p>
	 * 关闭.
	 * </p>
	 * @param folder folder
	 * @param expunge expunge
	 * @param logger logger
	 */
	public static void close(Folder folder, boolean expunge, Logger logger) {
		if (folder != null && folder.isOpen()) {
			try {
				folder.close(expunge);
			} catch (MessagingException e) {
				LogUtils.debug(e, logger);
			}
		}
	}
}
