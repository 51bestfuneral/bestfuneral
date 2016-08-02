package com.funeral.kris.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funeral.kris.init.ENV;
import com.funeral.kris.model.Email;

@Service
@Transactional
public class MailServiceImpl implements MailService{
//配置在application.properties
	private static final String MAIL_FROM = "MAIL_FROM";

	private static final String USER_NAME = "USER_NAME";

	private static final String PWD = "PWD";
	

	public void send(Map<String, String> mailInfo) {
		System.out.println("开始发送邮件...");
		
		String mailFrom=ENV.getValue(MAIL_FROM);
		String userName=ENV.getValue(USER_NAME);
		String pwd=ENV.getValue(PWD);

		// 创建邮件对象
		Email mail = new Email();
		mail.setFrom(mailFrom); // 发件人邮箱
		mail.addTo(mailInfo.get("to")); // 收件人邮箱
		//mail.addCc(mailInfo.get("cc"));
		//mail.addBcc(mailInfo.get("bcc"));
		mail.setContent("text/html");
		mail.setSubject(mailInfo.get("subject")); // 邮件主题
		mail.setUser(userName); // 用户名
		mail.setPassword(pwd); // 密码

		// 邮件正文
		mail.addHtmlContent(mailInfo.get("content"));

		// mail.addAttachment(""); // 添加附件

		mail.send(); // 发送

		System.out.println("邮件已发送完毕！");
	}
}
