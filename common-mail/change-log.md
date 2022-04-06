# 0.4.4 2022-04-06
1. 修复common-core不兼容升级

# 0.4.3 2020-12-18
1. 修复i18n资源文件目录位置不对的问题

# 0.4.2 2020-11-12
1. MailReceiver加入receive(MailHandler<Mail> mailHandler)和receive(SearchTerm searchTerm, MailHandler<Mail> mailHandler)方法
2. common-core升级，替换所有使用了废弃API的地方

# 0.4.1 2020-4-11
1. 移除MailReceive.setHandler，MailReceiver.handle方法
2. 加入MailReceive.receive方法

# 0.4.0 2020-3-10
1. 修改包名为cn.featherfly.common.mail

# 0.3.1 2019-12-03
1. 修改group名称

# 0.3.0 2019-8-15
1. 移除mail以外的功能单独发布

# 0.2.12 2014-06-17 
1. 移植到cn.featherfly
	
# 0.2.11 2013-06-05
1. MailClient加入allowCacheSize属性（可以使用set,get方法访问，默认值-1,表示不检查）
2. MailClient加入handle方法（多个参数重载）
	// 示例
	client.setAllowCacheSize(450 * 1024 * 1024);
	client.setMailCreator(new JamesMailCreator());
	client.setMailHandler(new new MailHandler<Mail>() {
		@Override
		public void handle(Mail mail) {
			System.out.println("mail ***************************************************");
			System.out.println("mail.id " + mail.getId());
			System.out.println("mail.subject " + mail.getMailBody().getSubject());
		}
	});
	client.handle(searchTerm);

# 0.2.10 2013-05-03
1. Mail对象删除setSentDate和setReceivedDate
2. MailBody对象加入setSentDate和setReceivedDate

# 0.2.9 2013-05-03
1. Mail对象修正setSentDate和setReceivedDate

# 0.2.8 2013-02-16
1. MailClient加入setCharset方法

# 0.2.7 2013-02-16
1. 修复发送邮件附加属性中文乱码问题

# 0.2.6 2013-02-16
1. HttpRequest 加入文件上传的功能
2. JamesMailCreator 加入protected File storeAttach(InputStream is)，可以由子类自定义附件存储策略
		JamesMailCreator 修正附件读取
3. 修复发送邮件附件名称中文乱码问题

# 0.2.5 2013-02-06
1. MailClient 收取的时候将cc设置进to里的BUG
2. MailClient 加入扩展信息

# 0.2.4 2013-02-06
1. MailUser修改，加入address 和 domain， 区分username,address
2. MailClient 发送的时候，自动设置MessageId为uuid

# 0.2.3 2013-02-05
1. MailClient  receive方法改为getAll，加入get(messageId)，search(SearcherTerm)方法
2. MailClient 加入delete方法

# 0.2.2 2013-01-14
1. MailClient 加入 receive方法

# 0.2.1 2013-01-14
1. ftp加入downloadAsStream方法
2. 修复邮件发送API BUG

# 0.2.0 
1. 加入http支持

# 0.1.1 2011-09-28
1. 依赖common-1.0.9和alorithm-1.0.0包

# 0.1.0 2011-09-28
1. 从common包独立出来单独发布