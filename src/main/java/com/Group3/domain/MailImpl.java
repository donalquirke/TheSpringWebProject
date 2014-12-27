package com.Group3.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.Group3.service.Mail;
@Component
public class MailImpl implements Mail {

	@Autowired
	private JavaMailSender mailSender;	
	@Autowired
	private SimpleMailMessage customMailMessage;	
	
	public void sendMail(String from, String to, String subject, String msg) { 
		
		SimpleMailMessage message = new SimpleMailMessage(); 
		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(msg);
		mailSender.send(message);	
	}

	@Override
	public void sendPreconfiguredMail() {
		mailSender.send(customMailMessage);
	}

}