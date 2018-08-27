package com.ksh.common;

import java.io.Serializable;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.ksh.core.exception.KSHException;
import com.ksh.core.util.Util;

public class Mail implements Serializable {

	private static final long serialVersionUID = 1L;

	private String from;
	private String to;
	private String subject;
	private String content;

	public Mail() {
	}

	public Mail(String from, String to, String subject, String content) {
		this.setFrom(from);
		this.setTo(to);
		this.setSubject(subject);
		this.setContent(content);
	}

	public void sendEmail() {
		try {

			Properties props = new Properties();

			props.put("mail.transport.protocol", Constant.PROTOCOL);
			props.put("mail.smtps.host", Constant.SMTP_HOST_NAME);
			props.put("mail.smtps.auth", Constant.AUTH);

			Session mailSession = Session.getDefaultInstance(props);
			mailSession.setDebug(true);
			Transport transport;
			transport = mailSession.getTransport();

			MimeMessage message = new MimeMessage(mailSession);
			message.setSubject(this.subject);
			message.setText(this.content);

			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					to));

			transport.connect(Constant.SMTP_HOST_NAME, Constant.SMTP_HOST_PORT,
					Constant.SMTP_AUTH_USER, Constant.SMTP_AUTH_PWD);
			transport.sendMessage(message,
					message.getRecipients(Message.RecipientType.TO));
			transport.close();
		} catch (NoSuchProviderException e2) {
			e2.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getFrom() {
		return from;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getTo() {
		return to;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSubject() {
		return subject;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

}
