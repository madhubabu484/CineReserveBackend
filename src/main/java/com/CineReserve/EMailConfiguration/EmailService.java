package com.CineReserve.EMailConfiguration;

import org.springframework.stereotype.Service;

import com.CineReserve.Appuser.AppUser;
import com.CineReserve.Appuser.User;
import com.CineReserve.BookingCineReserve.BookMyShow;

@Service
public class EmailService {
	public void sendBookingConfirmation(AppUser user, BookMyShow booking) {
		String content = String.format("""
				    Hi %s,

				    Your Booking ID: %s
				    Movie: %s
				    Theatre: %s
				    Seat: %s

				    Enjoy your movie! 🍿
				""", user.getName(), booking.getBookingId(),
				booking.getMovie().getName(), booking.getTheatre().getTheatrename(),
				booking.getSeatNumber());

		sendEmail(user.getEmail(), "Ticket Confirmation", content);
	}

	private void sendEmail(String to, String subject, String body) {
		// your logic (JavaMailSender or any other)
	}

	public void sendCancellationEmail(String email, BookMyShow booking) {
		String content = String.format("""
				Hi %s,

				Your booking (%s) for movie '%s' at '%s' has been cancelled.
				Seat: %s
				Reason: %s

				We hope to serve you again soon!
				""",
				booking.getUser().getName(),
				booking.getBookingId(),
				booking.getMovie().getName(),
				booking.getTheatre().getTheatrename(),
				booking.getSeatNumber(),
				booking.getReason());

		sendEmail(email, "Ticket Cancellation Confirmation", content);
	}

}
