
package cn.featherfly.common.mail;


/**
 * <p>
 * SmtpMailServer
 * </p>
 *
 * @author 钟冀
 */
public class SmtpMailServer extends MailServer{

	/**
	 */
	public SmtpMailServer(String host) {
		this(host, 25);
	}
	/**
	 */
	public SmtpMailServer(String host, int port) {
		super(host, port, "smtp");
	}
}
