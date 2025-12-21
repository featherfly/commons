
package cn.featherfly.common.mail;

/**
 * Pop3MailServer.
 *
 * @author 钟冀
 */
public class Pop3MailServer extends MailServer {

    /**
     * Instantiates a new pop 3 mail server.
     *
     * @param host the host
     */
    public Pop3MailServer(String host) {
        this(host, 110);
    }

    /**
     * Instantiates a new pop 3 mail server.
     *
     * @param host the host
     * @param port the port
     */
    public Pop3MailServer(String host, int port) {
        super(host, port, "pop3");
    }
}
