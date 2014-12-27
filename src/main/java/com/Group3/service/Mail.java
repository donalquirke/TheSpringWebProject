package com.Group3.service;

import org.springframework.stereotype.Service;
@Service
public interface Mail {
	public void sendMail(String to, String from, String subject, String message);
	public void sendPreconfiguredMail();
}