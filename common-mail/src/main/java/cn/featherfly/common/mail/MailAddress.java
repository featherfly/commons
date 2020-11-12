package cn.featherfly.common.mail;

import cn.featherfly.common.lang.Lang;

/**
 * <p>
 * 邮件地址
 * </p>
 *
 * @author 钟冀
 */
public class MailAddress {
    //接收邮件地址
    private String[] to;
    private String[] cc;

    /**
     * @param to 收件人地址
     */
    public MailAddress(String... to) {
        this.to = to;
    }

    /**
     * 是否包含抄送
     *
     * @return boolean 是否包含抄送
     */
    public boolean isHasCC() {
        return Lang.isNotEmpty(cc);
    }

    /**
     * 设置抄送地址
     *
     * @param cc 抄送地址
     */
    public void setCc(String... cc) {
        this.cc = cc;
    }

    /**
     * @return the to
     */
    public String[] getTo() {
        return to;
    }

    /**
     * 设置收件人地址
     *
     * @param to the to to set
     */
    public void setTo(String... to) {
        this.to = to;
    }

    /**
     * @return the cc
     */
    public String[] getCc() {
        return cc;
    }
}
