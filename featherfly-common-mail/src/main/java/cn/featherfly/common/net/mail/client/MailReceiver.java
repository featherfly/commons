package cn.featherfly.common.net.mail.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.mail.Flags;
import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Store;
import javax.mail.search.AndTerm;
import javax.mail.search.IntegerComparisonTerm;
import javax.mail.search.MessageIDTerm;
import javax.mail.search.SearchTerm;

import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.net.MailException;
import cn.featherfly.common.net.mail.ImapMailServer;
import cn.featherfly.common.net.mail.Mail;
import cn.featherfly.common.net.mail.MailApiUtils;
import cn.featherfly.common.net.mail.MailCreator;
import cn.featherfly.common.net.mail.MailHandler;
import cn.featherfly.common.net.mail.MailUser;
import cn.featherfly.common.net.mail.Pop3MailServer;

/**
 * <p>
 * 邮件接收客户端
 * </p>
 *
 * @author 钟冀
 */
public class MailReceiver extends AbstractMailClient {

    /**
     * @param mailUser       mailUser
     * @param pop3MailServer pop3MailServer
     */
    public MailReceiver(MailUser mailUser, Pop3MailServer pop3MailServer) {
        this(mailUser, pop3MailServer, null);
    }

    /**
     * @param mailUser       mailUser
     * @param imapMailServer imapMailServer
     */
    public MailReceiver(MailUser mailUser, ImapMailServer imapMailServer) {
        this(mailUser, imapMailServer, null);
    }

    /**
     * @throws MessagingException
     */
    public MailReceiver(MailUser mailUser, Pop3MailServer pop3MailServer, Map<String, Object> props) {
        super(mailUser, null, pop3MailServer, props);
        AssertIllegalArgument.isNotNull(mailUser, "mailUser");
        AssertIllegalArgument.isNotNull(pop3MailServer, "smtpServer");
    }

    /**
     * @throws MessagingException
     */
    public MailReceiver(MailUser mailUser, ImapMailServer imapMailServer, Map<String, Object> props) {
        super(mailUser, null, imapMailServer, props);
        AssertIllegalArgument.isNotNull(mailUser, "mailUser");
        AssertIllegalArgument.isNotNull(imapMailServer, "smtpServer");
    }

    /**
     * <p>
     * 获取指定folder指定messageId的邮件.
     * </p>
     *
     * @param messageId messageId
     * @return 邮件
     */
    public Mail get(String messageId) {
        return get(messageId, mailCreator);
    }

    /**
     * <p>
     * 获取指定folder指定messageId的邮件.
     * </p>
     *
     * @param messageId   messageId
     * @param mailCreator 邮件创建器
     * @return 邮件
     */
    public <E> E get(String messageId, MailCreator<E> mailCreator) {
        AssertIllegalArgument.isNotEmpty(messageId, "messageId");
        List<E> list = search(new MessageIDTerm(messageId), mailCreator);
        if (list.size() > 1) {
            logger.warn("get messageId -> {} size > 0 , size -> {}", messageId, list.size());
        }
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    /*
     * <p> 查询指定folder的邮件. </p>
     * @param searchTerm 查询条件，如果为空则返回所有
     * @param mailCreator 邮件创建器
     * @return 邮件
     */
    private <E> List<E> search(SearchTerm searchTerm, MailCreator<E> mailCreator) {
        AssertIllegalArgument.isNotNull(mailCreator, "mailCreator");
        return search(searchTerm, 0, mailCreator, null);
    }

    /**
     * <p>
     * 处理从指定folder的所有邮件.
     * </p>
     */
    public void handle() {
        handle(null);
    }

    /**
     * <p>
     * 处理从指定folder查询出的邮件.
     * </p>
     *
     * @param searchTerm 查询条件，如果为空则返回所有
     */
    public void handle(SearchTerm searchTerm) {
        handle(searchTerm, mailCreator, mailHandler);
    }

    /**
     * <p>
     * 处理从指定folder查询出的邮件.
     * </p>
     *
     * @param searchTerm  查询条件，如果为空则返回所有
     * @param mailCreator 邮件创建器
     * @param mailHandler 处理对象
     */
    public <E> void handle(SearchTerm searchTerm, MailCreator<E> mailCreator, MailHandler<E> mailHandler) {
        AssertIllegalArgument.isNotNull(mailCreator, "mailCreator");
        AssertIllegalArgument.isNotNull(mailHandler, "mailHandler");
        search(searchTerm, 0, mailCreator, mailHandler);
    }

    /**
     * <p>
     * 查询指定folder的邮件.
     * </p>
     *
     * @param searchTerm  查询条件，如果为空则返回所有
     * @param mailCreator 邮件创建器
     * @return 邮件
     */
    private <E> List<E> search(SearchTerm searchTerm, int start, MailCreator<E> mailCreator, MailHandler<E> handler) {
        AssertIllegalArgument.isNotNull(mailCreator, "mailCreator");
        Store store = null;
        try {
            store = getStore();
            return searchInStore(searchTerm, start, store, mailCreator, handler);
        } catch (Exception e) {
            throw new MailException(e);
        } finally {
            MailApiUtils.close(store, logger);
        }
    }

    /**
     * <p>
     * 查询指定folder的邮件.
     * </p>
     *
     * @param searchTerm  查询条件，如果为空则返回所有
     * @param mailCreator 邮件创建器
     * @return 邮件
     */
    private <E> List<E> searchInStore(SearchTerm searchTerm, int start, Store store, MailCreator<E> mailCreator,
            MailHandler<E> handler) throws Exception {
        AssertIllegalArgument.isNotNull(mailCreator, "mailCreator");
        Folder folder = null;
        int cacheSize = 0;
        try {
            List<E> mails = new ArrayList<>();
            // 获取指定文件箱
            folder = getFolder(store);
            folder.open(Folder.READ_ONLY);
            Message[] messages = null;
            if (searchTerm == null) {
                messages = searchMessages(folder, new MessageNumberGeTerm(start));
            } else {
                messages = searchMessages(folder, new AndTerm(searchTerm, new MessageNumberGeTerm(start)));
            }
            for (int i = 0, n = messages.length; i < n; i++) {
                Message message = messages[i];
                int messageSize = message.getSize();
                cacheSize += messageSize;
                if (allowCacheSize > 0 && messageSize > allowCacheSize) {
                    throw new MailException("#mail.size.gt.cache", new Object[] { messageSize, allowCacheSize });
                }
                if (allowCacheSize > 0 && cacheSize > allowCacheSize) {
                    // 超过允许的缓存大小
                    logger.debug("从第[{}]封邮件开始，在第{}封邮件时累计缓存[{}]超过允许的缓存[{}]大小，重新连接",
                            new Object[] { start, i, cacheSize, allowCacheSize });
                    MailApiUtils.close(folder, false, logger);
                    mails.addAll(searchInStore(searchTerm, start + i, store, mailCreator, handler));
                    return mails;
                }

                if (message != null) {
                    E mail = mailCreator.create(message);
                    // 处理者没有的时候才加入集合返回
                    if (handler != null) {
                        handler.handle(mail);
                    } else {
                        mails.add(mail);
                    }
                }
            }
            return mails;
        } finally {
            MailApiUtils.close(folder, false, logger);
        }
    }

    /**
     * <p>
     * 删除
     * </p>
     *
     * @param messageId
     */
    public void delete(String messageId) {
        flag(messageId, new Flags(Flag.DELETED));
    }

    /**
     * <p>
     * 标记已读
     * </p>
     *
     * @param messageId
     */
    public void seen(String messageId) {
        flag(messageId, new Flags(Flag.SEEN));
    }

    /**
     * <p>
     * 标记已回复
     * </p>
     *
     * @param messageId
     */
    public void answer(String messageId) {
        flag(messageId, new Flags(Flag.ANSWERED));
    }

    /**
     * <p>
     * 标记已回复
     * </p>
     *
     * @param messageId
     */
    public void recent(String messageId) {
        flag(messageId, new Flags(Flag.RECENT));
    }

    /**
     * <p>
     * 标记
     * </p>
     *
     * @param messageId
     */
    public void flag(String messageId, String flag) {
        flag(messageId, new Flags(flag));
    }

    /*
     * <p> 标记 </p>
     * @param messageId messageId
     * @param flag 标记
     */
    private void flag(String messageId, Flags flags) {
        Store store = null;
        Folder folder = null;
        try {
            SearchTerm searchTerm = new MessageIDTerm(messageId);
            // 获取邮箱的存储
            store = getStore();
            // 获取指定文件箱
            folder = getFolder(store);
            folder.open(Folder.READ_WRITE);
            Message[] messages = searchMessages(folder, searchTerm);
            for (int i = 0, n = messages.length; i < n; i++) {
                Message message = messages[i];
                message.setFlags(flags, true);
                //				message.saveChanges();
            }
            //			folder.close(true);
            //			store.close();
        } catch (Exception e) {
            throw new MailException(e);
        } finally {
            MailApiUtils.close(folder, false, logger);
            MailApiUtils.close(store, logger);
        }
    }

    // ********************************************************************
    //	private method
    // ********************************************************************

    private Message[] searchMessages(Folder folder, SearchTerm searchTerm) throws MessagingException {
        // 获取邮件消息
        Message[] messages = null;
        if (searchTerm == null) {
            messages = folder.getMessages();
        } else {
            messages = folder.search(searchTerm);
        }
        return messages;
    }

    private Store getStore() throws MessagingException {
        Store store = getSession().getStore(getStoreServer().getProtocol());
        store.connect(getStoreServer().getHost(), getStoreServer().getPort(), getMailUser().getUsername(),
                getMailUser().getPassword());
        return store;
    }

    private Folder getFolder(Store store) throws MessagingException {
        AssertIllegalArgument.isNotEmpty(folder, "folder");
        Folder folder = store.getFolder(this.folder);
        return folder;
    }

    // ********************************************************************
    //	field
    // ********************************************************************

    private MailCreator<Mail> mailCreator;

    private MailHandler<Mail> mailHandler;

    private int allowCacheSize = -1;

    private String folder = "INBOX";

    /**
     * 返回folder
     *
     * @return folder
     */
    public String getFolder() {
        return folder;
    }

    /**
     * 设置folder
     *
     * @param folder folder
     */
    public void setFolder(String folder) {
        this.folder = folder;
    }

    /**
     * 返回mailCreator
     *
     * @return mailCreator
     */
    public MailCreator<Mail> getMailCreator() {
        return mailCreator;
    }

    /**
     * 设置mailCreator
     *
     * @param mailCreator mailCreator
     */
    public void setMailCreator(MailCreator<Mail> mailCreator) {
        this.mailCreator = mailCreator;
    }

    /**
     * 返回mailHandler
     *
     * @return mailHandler
     */
    public MailHandler<Mail> getMailHandler() {
        return mailHandler;
    }

    /**
     * 设置mailHandler
     *
     * @param mailHandler mailHandler
     */
    public void setMailHandler(MailHandler<Mail> mailHandler) {
        this.mailHandler = mailHandler;
    }

    /**
     * 返回allowCacheSize
     *
     * @return allowCacheSize
     */
    public int getAllowCacheSize() {
        return allowCacheSize;
    }

    /**
     * 设置allowCacheSize
     *
     * @param allowCacheSize allowCacheSize
     */
    public void setAllowCacheSize(int allowCacheSize) {
        this.allowCacheSize = allowCacheSize;
    }
}

class MessageNumberGeTerm extends IntegerComparisonTerm {

    private static final long serialVersionUID = -5379625829658623812L;

    /**
     * Constructor.
     *
     * @param number the Message number
     */
    public MessageNumberGeTerm(int number) {
        super(GE, number);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean match(Message msg) {
        int msgno;
        try {
            msgno = msg.getMessageNumber();
        } catch (Exception e) {
            return false;
        }
        return super.match(msgno);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MessageNumberGeTerm)) {
            return false;
        }
        return super.equals(obj);
    }
}

class MessageNumberLtTerm extends IntegerComparisonTerm {

    private static final long serialVersionUID = -5379625829658623812L;

    /**
     * Constructor.
     *
     * @param number the Message number
     */
    public MessageNumberLtTerm(int number) {
        super(LT, number);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean match(Message msg) {
        int msgno;
        try {
            msgno = msg.getMessageNumber();
        } catch (Exception e) {
            return false;
        }
        return super.match(msgno);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MessageNumberGeTerm)) {
            return false;
        }
        return super.equals(obj);
    }
}