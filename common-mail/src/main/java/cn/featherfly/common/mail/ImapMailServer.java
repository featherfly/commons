
package cn.featherfly.common.mail;


/**
 * <p>
 * StmpMailServer
 * </p>
 *
 * @author 钟冀
 */
public class ImapMailServer extends MailServer{

	/**
	 */
	public ImapMailServer(String host) {
		this(host, 143);
	}
	/**
	 */
	public ImapMailServer(String host, int port) {
		super(host, port, "imap");
	}
}
