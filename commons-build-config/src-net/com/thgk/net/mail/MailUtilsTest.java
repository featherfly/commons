
package com.thgk.common.net.mail;

import javax.mail.MessagingException;

import cn.featherfly.common.net.mail.client.MailUtils;



public class MailUtilsTest {
	public static void main(String[] args) throws MessagingException {
		MailUser mailUser = new MailUser("smtp.163.com", "xiaoyi84527@163.com", "yufei101");
		MailAddress mailAddress = new MailAddress("featherfly101@hotmail.com");
//		MailUser mailUser = new MailUser("smtp.163.com", "xiaoyi84527@163.com", "yufei101");
//		MailAddress mailAddress = new MailAddress("featherfly@21cn.com");
//		MailUser mailUser = new MailUser("smtp.21cn.com", "featherfly@21cn.com", "yufei101");
//		MailAddress mailAddress = new MailAddress("xiaoyi84527@163.com");
		MailBody mailBody = new MailBody("测试中文附件14", "测试中文附件14", true,
				null, null);
		mailBody.setAffix("c:\\t.html");
		mailBody.setAffixName("中文附件.htm");
		mailBody.setAffixFlag(true);
		mailBody.setMimeContent(true);
		mailBody.setContentFlag(true);
		MailUtils.sendMail(mailUser, mailAddress, mailBody);
		mailAddress = new MailAddress("featherfly@21.com");
		MailUtils.sendMail(mailUser, mailAddress, mailBody);
		mailAddress = new MailAddress("zhongj@featherfly.com");
		MailUtils.sendMail(mailUser, mailAddress, mailBody);
	}
}
