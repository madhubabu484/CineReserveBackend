package com.CineReserve.GlobalException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.CineReserve.CustomException.BookingIdNotfoundException;
import com.CineReserve.CustomException.CancelBookingException;
import com.CineReserve.CustomException.MovieNotFoundException;
import com.CineReserve.CustomException.UserNotfoundException;

@RestControllerAdvice
public class GlobalException {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleSeatNotBokkedException(Exception ex) {
		return new ResponseEntity<>("Error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(MovieNotFoundException.class)
	public ResponseEntity<String> handleMovieNotFound(MovieNotFoundException ex) {
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body("Error: " + ex.getMessage());
	}

	@ExceptionHandler(BookingIdNotfoundException.class)
	public ResponseEntity<String> handleBookingIdNotFound(BookingIdNotfoundException ex)
	{
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body("Error :"+ ex.getMessage());
	}

	@ExceptionHandler(UserNotfoundException.class)
	public ResponseEntity<String> handleUserNotfoundException(UserNotfoundException ex)
	{
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body("Error :"+ex.getMessage());	
	}

	public ResponseEntity<String> handleCancelBookingException(CancelBookingException ex)
	{

		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body("Error : "+ex.getMessage());
	}
}