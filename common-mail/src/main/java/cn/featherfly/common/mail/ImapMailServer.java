
package cn.featherfly.common.mail;

/**
 * StmpMailServer.
 *
 * @author 钟冀
 */
public class ImapMailServer extends MailServer {

    /**
     * Instantiates a new imap mail server.
     *
     * @param host the host
     */
    public ImapMailServer(String host) {
        this(host, 143);
    }

    /**
     * Instantiates a new imap mail server.
     *
     * @param host the host
     * @param port the port
     */
    public ImapMailServer(String host, int port) {
        super(host, port, "imap");
    }
}
