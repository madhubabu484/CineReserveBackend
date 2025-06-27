package com.CineReserve.EMailCancelConformation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.CineReserve.BookingCineReserve.BookMyShow;

@Service
public class EmailServiceCancel {
	
	       @Autowired
	      private   JavaMailSender mailsender;
	
	  public void TicketCancellation(BookMyShow booking , String to, String subject , String body)
	  {
		  
		     SimpleMailMessage message  = new SimpleMailMessage();
		     
		     
		     message.setTo(booking.getUser().getEmail()); // assumes Booking has a User with email
		        message.setSubject("Booking Cancellation Confirmation");
		        message.setText("Dear " + booking.getUser().getName() + ",\n\n"
		                + "Your ticket for movie: " + booking.getMovie().getName() + " has been successfully cancelled.\n"
		                + "Booking ID: " + booking.getBookingId() + "\n"
		                + "Thank you.");

		     
		     mailsender.send(message);
		     
		  
		  
	  }

	public void TicketCancellation(String email, BookMyShow booking) {
		
	}

}
