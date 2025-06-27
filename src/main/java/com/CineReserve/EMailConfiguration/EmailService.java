package com.CineReserve.EMailConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender mailsender;
	
	
	public void BookingConformation(String to, String subject, String body)
	{
		
		   SimpleMailMessage message = new SimpleMailMessage(); 
		   
		   // 1) SimpleMailMessage is a class provided by the Spring(org.springframework.mail.SimpleMessage)
		   
		   //  2) It is used to give Plain-text-Mail
		   
		   // 3) And it is not support html or Attachements -> it is Support  only Basic text Content.
		   
		   
		      message.setFrom("madhubabusathivada@gmail.com");
		      message.setTo(to);
		      message.setSubject(subject);
		      message.setText(body);
		      
		      mailsender.send(message);
}
}