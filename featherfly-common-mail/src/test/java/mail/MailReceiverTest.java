
package mail;

import cn.featherfly.common.mail.ImapMailServer;
import cn.featherfly.common.mail.MailUser;
import cn.featherfly.common.mail.SimpleMailCreator;
import cn.featherfly.common.mail.client.MailReceiver;

/**
 * <p>
 * MailReceiverTest 类的说明放这里
 * </p>
 *
 * @author 钟冀
 */
public class MailReceiverTest {
    public static void main(String[] args) {
        MailReceiver client = new MailReceiver(new MailUser("robot", "cdmhzx.com", "q123456"),
                new ImapMailServer("imap.exmail.qq.com"));
        client.setMailCreator(new SimpleMailCreator());
        client.setMailHandler(e -> System.out.println(e.getMailBody().getSubject()));
        client.handle();
    }
}
