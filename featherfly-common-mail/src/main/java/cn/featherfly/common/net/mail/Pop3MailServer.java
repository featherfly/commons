
package cn.featherfly.common.net.mail;


/**
 * <p>
 * Pop3MailServer
 * </p>
 *
 * @author 钟冀
 */
public class Pop3MailServer extends MailServer{

	/**
	 */
	public Pop3MailServer(String host) {
		this(host, 110);
	}
	/**
	 */
	public Pop3MailServer(String host, int port) {
		super(host, port, "pop3");
	}
}
