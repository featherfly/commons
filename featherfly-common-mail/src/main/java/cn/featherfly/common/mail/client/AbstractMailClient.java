package cn.featherfly.common.mail.client;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.StringUtils;
import cn.featherfly.common.mail.ImapMailServer;
import cn.featherfly.common.mail.MailException;
import cn.featherfly.common.mail.MailServer;
import cn.featherfly.common.mail.MailUser;
import cn.featherfly.common.mail.Pop3MailServer;
import cn.featherfly.common.mail.SmtpMailServer;

/**
 * <p>
 * 抽象邮件发送客户端
 * </p>
 *
 * @author 钟冀
 */
public abstract class AbstractMailClient {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected static final String MAIL_SMTP_AUTH = "mail.smtp.auth";

    protected static final String MAIL_SMTP_HOST = "mail.smtp.host";

    /**
     * 扩展属性前置
     */
    public static final String PROPERTY_PREFIX = "prop-";

    /**
     * @param mailUser
     * @param smtpMailServer
     */
    public AbstractMailClient(MailUser mailUser, SmtpMailServer smtpMailServer) {
        this(mailUser, smtpMailServer, null);
    }

    /**
     * @param mailUser       mailUser
     * @param pop3MailServer pop3MailServer
     */
    public AbstractMailClient(MailUser mailUser, Pop3MailServer pop3MailServer) {
        this(mailUser, null, pop3MailServer);
    }

    /**
     * @param mailUser
     * @param imapMailServer
     */
    public AbstractMailClient(MailUser mailUser, ImapMailServer imapMailServer) {
        this(mailUser, null, imapMailServer);
    }

    /**
     * @param mailUser
     * @param smtpServer
     * @param storeServer
     */
    public AbstractMailClient(MailUser mailUser, MailServer smtpServer, MailServer storeServer) {
        this(mailUser, smtpServer, storeServer, null);
    }

    /**
     * @param mailUser
     * @param smtpServer
     * @param storeServer
     * @param props
     */
    public AbstractMailClient(MailUser mailUser, MailServer smtpServer, MailServer storeServer,
            Map<String, Object> props) {
        try {
            init(mailUser, smtpServer, storeServer, props);
        } catch (Exception e) {
            throw new MailException(e);
        }
    }

    /**
     * @param mailClient
     */
    public AbstractMailClient(AbstractMailClient mailClient) {
        this(mailClient.mailUser, mailClient.smtpServer, mailClient.storeServer);
        setCharset(mailClient.charset);
        setDebug(mailClient.debug);
        setEncoding(mailClient.encoding);
        setMailLogFile(mailClient.mailLogFile);
        setMailSmtpAuth(mailClient.mailSmtpAuth);
        setMailSmtpHost(mailClient.mailSmtpHost);
    }

    // ********************************************************************
    //	private method
    // ********************************************************************

    // 初始化<code> Session, Transport </code>
    protected void init(final MailUser mailUser, MailServer smtpServer, MailServer storeServer,
            Map<String, Object> props) throws MessagingException {
        Properties properties = new Properties();

        AssertIllegalArgument.isNotNull(mailUser, "mailUser");
        this.mailUser = mailUser;
        this.smtpServer = smtpServer;
        this.storeServer = storeServer;

        if (smtpServer != null) {
            // 设置发送邮件的邮件服务器的属性
            properties.put(getMailSmtpHost(), smtpServer.getHost());
            // 需要经过授权，也就是有户名和密码的校验，这样才能通过验证（一定要有这一条）
            properties.put(getMailSmtpAuth(), "true");
            // 设置邮件服务器的端口
            properties.put("mail.smtp.port", smtpServer.getPort());
        }

        if (storeServer != null) {
            // 设置收取邮件的邮件服务器的属性
            properties.put("mail.store.protocol", storeServer.getProtocol());
            properties.put("mail." + storeServer.getProtocol() + ".host", storeServer.getHost());
        }

        //	TODO 以后让props可以调用者设置
        /*
         * if (propsMap != null) { for (Entry<String, Object> prop :
         * propsMap.entrySet()) { if (LangUtils.isNotEmpty(prop.getKey()) &&
         * prop.getValue() != null) { props.put(prop.getKey(), prop.getValue());
         * } } } Security.addProvider(new
         * com.sun.net.ssl.internal.ssl.Provider());
         * props.put("mail.smtp.socketFactory.class",
         * "javax.net.ssl.SSLSocketFactory");
         * props.put("mail.smtp.socketFactory.fallback", "false");
         * props.put("mail.smtp.socketFactory.port", "25");
         */

        // 用刚刚设置好的props对象构建一个session
        session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailUser.getAddress(), mailUser.getPassword());
            }
        });
    }

    // 获取所有的邮件地址
    protected Address[] getAddress(String[] address) throws AddressException {
        Address[] addrs = new InternetAddress[address.length];
        for (int i = 0; i < address.length; i++) {
            addrs[i] = new InternetAddress(address[i]);
        }
        return addrs;
    }

    protected void setDebug() {
        // 有了这句便可以在发送邮件的过程中在console处显示过程信息，供调试使用（你可以在控制台（console)上看到发送邮件的过程）
        if (debug) {
            if (StringUtils.isNotEmpty(mailLogFile)) {
                try {
                    session.setDebugOut(new PrintStream(new FileOutputStream(mailLogFile, true)));
                } catch (FileNotFoundException e) {
                    logger.warn("邮件发送日志文件创建失败：{}", e.getMessage());
                }
            } else {
                session.setDebug(debug);
            }
        }
    }

    // ********************************************************************
    //	field
    // ********************************************************************

    // 会话
    private Session session;
    // 发送邮件
    //	private Transport transport;
    // 邮件相关的帐户信息
    private MailUser mailUser;
    //	// 收件人地址
    //	private MailAddress mailAddress;
    //	// 邮件内容
    //	private MailBody mailBody;

    /**
     * 返回session
     *
     * @return session
     */
    protected Session getSession() {
        return session;
    }

    /**
     * 返回smtpServer
     *
     * @return smtpServer
     */
    protected MailServer getSmtpServer() {
        return smtpServer;
    }

    /**
     * 返回storeServer
     *
     * @return storeServer
     */
    protected MailServer getStoreServer() {
        return storeServer;
    }

    // ********************************************************************
    //	property
    // ********************************************************************

    private MailServer smtpServer;

    private MailServer storeServer;

    private Map<String, Object> props = new HashMap<>();

    // 文件名称编码（用于解决中文乱码）
    private String charset = "GBK";

    private String encoding = "B";

    private String mailSmtpHost;

    private String mailSmtpAuth;

    private boolean debug;

    private String mailLogFile;

    /**
     * 返回debug
     *
     * @return debug
     */
    public boolean isDebug() {
        return debug;
    }

    /**
     * 设置debug
     *
     * @param debug debug
     */
    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    /**
     * @return the mailSmtpHost
     */
    public String getMailSmtpHost() {
        if (StringUtils.isBlank(mailSmtpHost)) {
            mailSmtpHost = MAIL_SMTP_HOST;
        }
        return mailSmtpHost;
    }

    /**
     * @param mailSmtpHost the mailSmtpHost to set
     */
    public void setMailSmtpHost(String mailSmtpHost) {
        this.mailSmtpHost = mailSmtpHost;
    }

    /**
     * @return the mailSmtpAuth
     */
    public String getMailSmtpAuth() {
        if (StringUtils.isBlank(mailSmtpAuth)) {
            mailSmtpAuth = MAIL_SMTP_AUTH;
        }
        return mailSmtpAuth;
    }

    /**
     * @param mailSmtpAuth the mailSmtpAuth to set
     */
    public void setMailSmtpAuth(String mailSmtpAuth) {
        this.mailSmtpAuth = mailSmtpAuth;
    }

    /**
     * 返回mailLogFile
     *
     * @return mailLogFile
     */
    public String getMailLogFile() {
        return mailLogFile;
    }

    /**
     * 设置mailLogFile
     *
     * @param mailLogFile mailLogFile
     */
    public void setMailLogFile(String mailLogFile) {
        this.mailLogFile = mailLogFile;
    }

    /**
     * 返回charset
     *
     * @return charset
     */
    public String getCharset() {
        return charset;
    }

    /**
     * 设置charset
     *
     * @param charset charset
     */
    public void setCharset(String charset) {
        this.charset = charset;
    }

    /**
     * 返回encoding
     *
     * @return encoding
     */
    public String getEncoding() {
        return encoding;
    }

    /**
     * 设置encoding
     *
     * @param encoding encoding
     */
    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    /**
     * 返回props
     *
     * @return props
     */
    public Map<String, Object> getProps() {
        return props;
    }

    /**
     * 返回mailUser
     *
     * @return mailUser
     */
    public MailUser getMailUser() {
        return mailUser;
    }
}