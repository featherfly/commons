
package mail;

import cn.featherfly.common.net.mail.MailAddress;
import cn.featherfly.common.net.mail.MailBody;
import cn.featherfly.common.net.mail.MailUser;
import cn.featherfly.common.net.mail.SmtpMailServer;
import cn.featherfly.common.net.mail.client.MailSender;

/**
 * <p>
 * MailSenderTest 类的说明放这里
 * </p>
 *
 * @author 钟冀
 */
public class MailSenderTest {
    public static void main(String[] args) {
        MailSender client = new MailSender(new MailUser("robot", "cdmhzx.com", "q123456"),
                new SmtpMailServer("smtp.exmail.qq.com"));
        client.send(new MailAddress("admin@cdmhzx.com"), new MailBody("中文", "中文"));
    }
}
