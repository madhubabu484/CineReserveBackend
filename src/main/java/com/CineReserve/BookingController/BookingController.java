package com.CineReserve.BookingController;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CineReserve.BookingCineReserve.BookMyShow;
import com.CineReserve.BookingDTO.BookingDTO;
import com.CineReserve.BookingDTO.BookingResponseDTO;
import com.CineReserve.BookingService.BookingService;
import com.CineReserve.Canceldto.CancelBookingDTO;
import com.CineReserve.CustomException.CancelBookingException;
import com.CineReserve.CustomException.UserNotfoundException;
import com.CineReserve.MovieDto.MovieResponseDTO;
import com.CineReserve.Theatre.Theatre;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/book")
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@PostMapping("/create")
	public ResponseEntity<?> createBooking(@Valid @RequestBody BookingDTO dto, BindingResult result) {
		if (result.hasErrors()) {
			String errorMsg = result.getAllErrors().stream()
					.map(error -> error.getDefaultMessage())
					.collect(Collectors.joining(", "));
			return ResponseEntity.badRequest().body(errorMsg);
		}

		BookingResponseDTO response = bookingService.bookTicket(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PutMapping("/cancel/{bookingId}")
	public ResponseEntity<String> cancelBooking(@PathVariable String bookingId,
			@RequestBody CancelBookingDTO dto) {
		try {
			String message = bookingService.cancelBooking(bookingId, dto.getUserId(), dto.getReason());
			return ResponseEntity.ok(message);
		} catch (CancelBookingException | UserNotfoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(e.getMessage());
		}
	}

	@GetMapping("/movies")
	public ResponseEntity<List<MovieResponseDTO>> findAllMovies() {
		List<MovieResponseDTO> movies = bookingService.getAllMovies();

		if (movies.isEmpty()) {
			return ResponseEntity.noContent().build(); // HTTP 204
		}

		return ResponseEntity.ok(movies); // HTTP 200 with body
	}


	@GetMapping("/FindAllTheatres")
	public List<Theatre> FindAllTheatre()
	{
		List<Theatre> t2 = bookingService.getAlltheatres();

		return t2;
	}

	@GetMapping("/getByseatid/{id}")
	public ResponseEntity<String> getbyseatNumber(@PathVariable ("id") String seatNumber)
	{
		BookMyShow s1 = bookingService.findbyseatnumber(seatNumber);
		if(s1!=null)
		{
			return ResponseEntity.ok("SeatNumber is Sucressfully Featched By the id "+seatNumber);
		}
		else {
			return  ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("SeatNumber is Not found with that the id : "+seatNumber);
		}

	}


	@GetMapping("/bookings")
	public ResponseEntity<List<BookingResponseDTO>> getAllBookings() {
		List<BookingResponseDTO> bookings = bookingService.getAllBookings();
		if (bookings.isEmpty()) {
			return ResponseEntity.noContent().build(); // HTTP 204
		}
		return ResponseEntity.ok(bookings); // HTTP 200
	}


	@PostMapping("/check-seat")
	public ResponseEntity<String> checkSeatAvailability(@RequestBody BookingDTO dto) {
		boolean isBooked = bookingService.isSeatAlreadyBooked(dto);
		if (isBooked) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body("Seat already booked. Please choose another one.");
		} else {
			return ResponseEntity.ok(" Seat is available.");
		}
	}

	@GetMapping("/getByBookingId/{bookingId}")
	public ResponseEntity<BookingResponseDTO> getByBookingId(@PathVariable String bookingId) {
		BookingResponseDTO response = bookingService.getByBookingId(bookingId);
		return ResponseEntity.ok(response);
	}
}














