package com.CineReserve.mappers;

import org.springframework.stereotype.Component;

import com.CineReserve.BookingCineReserve.BookMyShow;
import com.CineReserve.BookingDTO.BookingResponseDTO;

@Component
public class BookingMapper {
	public BookingResponseDTO toResponseDTO(BookMyShow booking) {
		return BookingResponseDTO.builder()
				.bookingId(booking.getBookingId())
				.movieName(booking.getMovie().getName())
				.theatreName(booking.getTheatre().getTheatrename())
				.seatNumber(booking.getSeatNumber())
				.userEmail(booking.getUser().getEmail())
				.status(booking.getBookingstatus().name())
				.build();
	}
}

