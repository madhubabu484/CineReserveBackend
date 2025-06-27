package com.CineReserve.BookingController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CineReserve.BookingCineReserve.BookMyShow;
import com.CineReserve.BookingDTO.BookingDTO;
import com.CineReserve.BookingService.BookingService;
import com.CineReserve.Canceldto.CancelDto;
import com.CineReserve.CustomException.BookingIdNotfoundException;
import com.CineReserve.CustomException.CancelBookingException;
import com.CineReserve.CustomException.UserNotfoundException;
import com.CineReserve.Movie.Movie;
import com.CineReserve.Theatre.Theatre;

@RestController
@RequestMapping("/book")
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@PostMapping("/create")
	public ResponseEntity<String> createBooking(@RequestBody BookingDTO dto) throws UserNotfoundException {

		// Step 1: Check if the seat is already booked
		if (bookingService.isSeatAlreadyBooked(dto)) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Seat already booked And Please Book Another Seat");
		}

		// Step 2: Save the booking
		bookingService.saveBooking(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body("Ticket booked successfully And Enjoy Your Movie");
	}



	@PutMapping("/cancel/{Bookingid}")
	public ResponseEntity<String> cancelBooking(@PathVariable String Bookingid , @RequestBody CancelDto dto) throws UserNotfoundException
	{

		try {
			String    status  =bookingService.CancelBooking(Bookingid, dto.getUserid(), dto.getReason());

			return ResponseEntity.ok(status);

		} catch (CancelBookingException  e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Error : " +  e.getMessage());
		}



	}
	@GetMapping("/findAllMovies")
	public List<Movie> FindAllMovies()
	{

		List<Movie> m1 = bookingService.getAllMovies();

		return m1;
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


	@GetMapping("/findAllBookings")
	public List<BookMyShow> getAllBookig()
	{

		List<BookMyShow> show =  bookingService.getAllBookings();

		return show;
	}


	@GetMapping("/getByBookingid/{bookingid}")
	public ResponseEntity<String> getByBookingid(@PathVariable("bookingid") String booking_id)
	{
		BookMyShow show = bookingService.getbybookingid(booking_id);
		{
			if(show!=null)
			{
				return ResponseEntity.ok("Bookingid Sucessfully Fetched by This :"+booking_id);
			}
			throw new BookingIdNotfoundException("Message : Booking is not found with the booking id");

		}
	}
}














