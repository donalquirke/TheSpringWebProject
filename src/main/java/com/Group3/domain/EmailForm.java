package com.Group3.domain;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Email;

public class EmailForm{

@NotEmpty(message="To address needs to be specified.")
@Email
private String to;
@NotEmpty(message="From address needs to be specified.")
@Email
private String from;	
private String subject;
@Size(min=1, message="The message must have at least one character.")
private String message;

public String getTo() {
	return to;
}
public void setTo(String to) {
	this.to = to;
}
public String getFrom() {
	return from;
}
public void setFrom(String from) {
	this.from = from;
}
public String getSubject() {
	return subject;
}
public void setSubject(String subject) {
	this.subject = subject;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
}