
package cn.featherfly.common.mail.client;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.UUIDGenerator;
import cn.featherfly.common.mail.MailAddress;
import cn.featherfly.common.mail.MailAttach;
import cn.featherfly.common.mail.MailBody;
import cn.featherfly.common.mail.MailException;
import cn.featherfly.common.mail.MailUser;
import cn.featherfly.common.mail.SmtpMailServer;

/**
 * <p>
 * 邮件发送客户端
 * </p>
 *
 * @author 钟冀
 */
public class MailSender extends AbstractMailClient {

    /**
     * @param mailUser   mailUser
     * @param smtpServer smtpServer
     */
    public MailSender(MailUser mailUser, SmtpMailServer smtpServer) {
        this(mailUser, smtpServer, null);
    }

    /**
     * @param mailUser   mailUser
     * @param smtpServer smtpServer
     * @param props      props
     */
    public MailSender(MailUser mailUser, SmtpMailServer smtpServer, Map<String, Object> props) {
        super(mailUser, smtpServer, null, props);
        AssertIllegalArgument.isNotNull(mailUser, "mailUser");
        AssertIllegalArgument.isNotNull(smtpServer, "smtpServer");
    }

    /**
     * 发送邮件
     *
     * @param address mail address
     * @param subject mail subject
     * @param content mail content
     */
    public void send(String address, String subject, String content) {
        send(new MailAddress(address), new MailBody(subject, content));
    }

    /**
     * 发送邮件
     *
     * @param mailAddress
     * @param mailBody
     */
    public void send(MailAddress mailAddress, MailBody mailBody) {
        setDebug();
        try (Transport transport = getSession().getTransport(getSmtpServer().getProtocol())) {
            Message message = createMessage(mailAddress, mailBody);
            // 连接服务器的邮箱
            transport.connect(getSmtpServer().getHost(), getSmtpServer().getPort(), getMailUser().getUsername(),
                    getMailUser().getPassword());
            // 发送邮件
            transport.sendMessage(message, message.getAllRecipients());
            //          transport.close();
        } catch (MessagingException e) {
            throw new MailException(e);
        }
    }

    // ********************************************************************
    //  private method
    // ********************************************************************

    // 构造邮件的内容
    private Message createMessage(MailAddress mailAddress, MailBody mailBody) throws MessagingException {
        AssertIllegalArgument.isNotNull(mailAddress, "mailAddress");
        AssertIllegalArgument.isNotEmpty(mailAddress.getTo(), "mailAddress.to");
        AssertIllegalArgument.isNotNull(mailBody, "mailBody");
        AssertIllegalArgument.isNotNull(mailBody.getSubject(), "mailBody.subject");
        //      AssertIllegalArgument.isNotNull(mailBody.getContent(), "mailBody.content");
        // 用session为参数定义消息对象
        Message message = new MimeMessage(getSession());
        // 加载发件人地址
        message.setFrom(new InternetAddress(getMailUser().getAddress()));
        if (mailBody.getSentDate() == null) {
            message.setSentDate(new Date());
        } else {
            message.setSentDate(mailBody.getSentDate());
        }
        // 加载收件人地址
        message.addRecipients(Message.RecipientType.TO, getAddress(mailAddress.getTo()));
        if (mailAddress.isHasCC()) {
            message.addRecipients(Message.RecipientType.CC, getAddress(mailAddress.getCc()));
        }
        // 加载标题
        message.setSubject(encode(mailBody.getSubject()));

        // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
        Multipart multipart = new MimeMultipart();

        // 设置邮件的文本内容
        MimeBodyPart contentPart = new MimeBodyPart();
        if (mailBody.isMimeContent()) {
            contentPart.setContent(mailBody.getContent(), "text/html;charset=" + getCharset());
        } else {
            contentPart.setText(mailBody.getContent(), getCharset());
        }
        multipart.addBodyPart(contentPart);

        // 设置邮件附件
        if (mailBody.hasAttach()) {
            // 添加附件
            for (MailAttach mailAttach : mailBody.getMailAttachs()) {
                BodyPart attachBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(mailAttach.getFile());
                // 添加附件的内容
                attachBodyPart.setDataHandler(new DataHandler(source));
                // 添加附件的标题这里很重要，通过下面的Base64编码的转换可以保证你的
                // 中文附件标题名在发送时不会变成乱码
                //                String fileName = null;
                //                try {
                ////                  fileName = "=?" + fileNameEncode + "?B?"
                ////                      + Base64.encode(mailAttach.getFileName().getBytes(fileNameEncode))
                ////                      + "?=";
                //                    fileName = MimeUtility.encodeText(mailAttach.getFileName(), getCharset(), getEncoding());
                //                } catch (Exception e) {
                //                    fileName = mailAttach.getFileName();
                //                    logger.debug("编码附件标题[{}]出错：{}", fileName, e.getMessage());
                //                }
                attachBodyPart.setFileName(encode(mailAttach.getFileName()));
                multipart.addBodyPart(attachBodyPart);
            }
        }
        // 设置Message-Id
        message.setHeader("Message-Id", UUIDGenerator.generateUUID());

        for (Entry<String, String> property : mailBody.getProperties().entrySet()) {

            if (property.getValue() != null) {
                try {
                    message.setHeader(PROPERTY_PREFIX + property.getKey(),
                            MimeUtility.encodeText(property.getValue(), getCharset(), getEncoding()));
                } catch (UnsupportedEncodingException e) {
                    logger.error("参数[{}]编码出错：{}", property.getValue(), e.getMessage());
                    message.setHeader(PROPERTY_PREFIX + property.getKey(), property.getValue());
                }
            }
        }

        // 将multipart对象放到message中
        message.setContent(multipart);
        // 保存邮件
        message.saveChanges();
        return message;
    }

    private String encode(String str) {
        try {
            return MimeUtility.encodeText(str, getCharset(), getEncoding());
        } catch (Exception e) {
            logger.error("编码字符串时[{}]出错：{}", str, e.getMessage());
        }
        return str;
    }
}