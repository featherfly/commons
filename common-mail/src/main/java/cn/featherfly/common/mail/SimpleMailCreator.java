
package cn.featherfly.common.mail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Enumeration;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Flags.Flag;
import javax.mail.Header;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Part;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.lang.UUIDGenerator;
import cn.featherfly.common.mail.client.AbstractMailClient;

/**
 * SimpleMailCreator.
 *
 * @author 钟冀
 */
public class SimpleMailCreator implements MailCreator<Mail> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleMailCreator.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public Mail create(Message message) {
        try {
            return createMail(message);
        } catch (Exception e) {
            throw new MailException(e);
        }
    }

    private Mail createMail(Message message) throws MessagingException, IOException {
        Mail mail = new Mail();
        mail.setMailAddress(new MailAddress());
        mail.setMailUser(new MailUser("", ""));
        mail.setMailBody(new MailBody(null, null, true));

        // 设置ID
        String[] headerId = message.getHeader("Message-ID");
        if (Lang.isNotEmpty(headerId)) {
            mail.setId(headerId[0]);
        } else {
            LOGGER.warn("没有找到Message-ID");
        }
        LOGGER.trace("Message-ID : {}", mail.getId());

        // 设置日期
        //		mail.setDate(message.getSentDate());
        mail.getMailBody().setSentDate(message.getSentDate());
        mail.getMailBody().setReceivedDate(message.getReceivedDate());
        LOGGER.trace("SentDate : {}", message.getSentDate());
        LOGGER.trace("ReceivedDate : {}", message.getReceivedDate());

        // 设置地址
        Address[] toAddresses = message.getRecipients(RecipientType.TO);
        if (toAddresses != null) {
            String[] to = new String[toAddresses.length];
            for (int i = 0; i < toAddresses.length; i++) {
                if (toAddresses[i] instanceof InternetAddress) {
                    InternetAddress toAddress = (InternetAddress) toAddresses[i];
                    to[i] = toAddress.getAddress();
                } else {
                    to[i] = toAddresses[i].toString();
                }
            }
            mail.getMailAddress().setTo(to);
        }
        LOGGER.trace("toAddresses : {}", Arrays.toString(mail.getMailAddress().getTo()));

        Address[] ccAddresses = message.getRecipients(RecipientType.CC);
        if (ccAddresses != null) {
            String[] cc = new String[ccAddresses.length];
            for (int i = 0; i < ccAddresses.length; i++) {
                if (ccAddresses[i] instanceof InternetAddress) {
                    InternetAddress ccAddress = (InternetAddress) ccAddresses[i];
                    cc[i] = ccAddress.getAddress();
                } else {
                    cc[i] = ccAddresses[i].toString();
                }
            }
            mail.getMailAddress().setCc(cc);
        }
        LOGGER.trace("ccAddresses : {}", Arrays.toString(mail.getMailAddress().getCc()));

        // 设置发件人
        if (message.getFrom()[0] instanceof InternetAddress) {
            InternetAddress address = (InternetAddress) message.getFrom()[0];
            mail.getMailUser().setAddress(address.getAddress());
        } else {
            mail.getMailUser().setAddress(message.getFrom()[0].toString());
        }
        LOGGER.trace("from : {}", mail.getMailUser().getAddress());

        // 设置邮件标题
        mail.getMailBody().setSubject(message.getSubject());
        LOGGER.trace("subject : {}", mail.getMailBody().getSubject());

        String flags = "";
        for (Flag f : message.getFlags().getSystemFlags()) {
            if (f == Flag.ANSWERED) {
                flags += "ANSWERED" + ",";
            } else if (f == Flag.DELETED) {
                flags += "DELETED" + ",";
            } else if (f == Flag.DRAFT) {
                flags += "DRAFT" + ",";
            } else if (f == Flag.FLAGGED) {
                flags += "FLAGGED" + ",";
            } else if (f == Flag.RECENT) {
                flags += "RECENT" + ",";
            } else if (f == Flag.SEEN) {
                flags += "SEEN" + ",";
            } else if (f == Flag.USER) {
                flags += "USER" + ",";
            }
        }
        LOGGER.trace("flags : {}", flags);
        LOGGER.trace("userFlags : {}", Arrays.toString(message.getFlags().getUserFlags()));

        //		ArrayList<String> flags = new ArrayList<String>();

        Enumeration<Header> enums = message.getAllHeaders();
        while (enums.hasMoreElements()) {
            Header header = enums.nextElement();
            if (header.getName().contains(AbstractMailClient.PROPERTY_PREFIX)) {
                String name = header.getName().substring(AbstractMailClient.PROPERTY_PREFIX.length());
                if (header.getValue() != null) {
                    mail.getMailBody().setProperty(name, MimeUtility.decodeText(header.getValue()));
                }
            }
        }
        // 设置邮件内容
        setContent(message, mail);

        LOGGER.trace("content : {}", mail.getMailBody().getContent());
        LOGGER.trace("attach_size : {}", mail.getMailBody().getMailAttachs().size());

        return mail;
    }

    private void setContent(Message message, Mail mail) throws IOException, MessagingException {
        if (message.getContent() instanceof MimeMultipart) {
            setContent((MimeMultipart) message.getContent(), mail);
        } else if (message.getContent() instanceof String) {
            if (message.getContentType().contains("text/html")) {
                mail.getMailBody().setContentHtml(message.getContent().toString());
            }
            mail.getMailBody().setContent(message.getContent().toString());
        } else {
            throw new MailException("邮件正文不是MimeMultipart和String类型，类型" + message.getContent().getClass().getName());
        }
    }

    private void setContent(MimeMultipart part, Mail mail)
        throws FileNotFoundException, IOException, MessagingException {
        for (int i = 0; i < part.getCount(); i++) {
            BodyPart bodyPart = part.getBodyPart(i);
            String disposition = bodyPart.getDisposition();
            if (disposition != null && (disposition.equals(Part.ATTACHMENT) || disposition.equals(Part.INLINE))) {
                mail.getMailBody().addMailAttach(new MailAttach(storeAttach(bodyPart.getInputStream()),
                    MimeUtility.decodeText(bodyPart.getFileName())));
            } else {
                Object content = bodyPart.getContent();
                if (content instanceof MimeMultipart) {
                    //					System.out.println("MimeMultipart : " + bodyPart.getContentType());
                    MimeMultipart subpart = (MimeMultipart) content;
                    setContent(subpart, mail);
                } else if (content instanceof InputStream) {
                    //					System.out.println("InputStream : " + bodyPart.getContentType());
                    InputStream is = (InputStream) part.getBodyPart(i).getContent();
                    //					mail.getMailBody().addMailAttach(new MailAttach(storeAttach(is), bodyPart.getFileName()));
                    mail.getMailBody()
                        .addMailAttach(new MailAttach(storeAttach(is), MimeUtility.decodeText(bodyPart.getFileName())));
                } else if (content instanceof String) {
                    // contentType text/html;charset="gb2312";
                    //					System.out.println("String : " + bodyPart.getContentType());
                    if (bodyPart.getContentType().contains("text/html")) {
                        mail.getMailBody().setContentHtml(content.toString());
                    }
                    mail.getMailBody().setContent(content.toString());
                } else {
                    throw new MailException("不支持邮件正文类型：" + content.getClass().getName());
                }
            }
        }
    }

    /**
     * 存储文件. 可以由子类覆盖.
     *
     * @param is 附件输入流
     * @return 存储的文件
     * @throws IOException Signals that an I/O exception has occurred.
     */
    protected File storeAttach(InputStream is) throws IOException {
        File file = new File(UUIDGenerator.generateUUID());
        IOUtils.copy(is, new FileOutputStream(file));
        return file;
    }

    // ********************************************************************
    //	property
    // ********************************************************************

}
