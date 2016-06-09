package com.funeral.kris.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funeral.kris.model.Email;

@Service
@Transactional
public class MailServiceImpl implements MailService{

	private static final String MAIL_FROM = "service@365niannian.com";

	private static final String USER_NAME = "service@365niannian.com";

	private static final String PWD = "20041632lxx!";
	

	public void send(Map<String, String> mailInfo) {
		System.out.println("开始发送邮件...");
		// 创建邮件对象
		Email mail = new Email();
		mail.setFrom(MAIL_FROM); // 发件人邮箱
		mail.addTo(mailInfo.get("to")); // 收件人邮箱
		//mail.addCc(mailInfo.get("cc"));
		//mail.addBcc(mailInfo.get("bcc"));
		mail.setSubject(mailInfo.get("subject")); // 邮件主题
		mail.setUser(USER_NAME); // 用户名
		mail.setPassword(PWD); // 密码

		// 邮件正文
		mail.addHtmlContent(mailInfo.get("content"));

		// mail.addAttachment(""); // 添加附件

		mail.send(); // 发送

		System.out.println("邮件已发送完毕！");
	}
}
