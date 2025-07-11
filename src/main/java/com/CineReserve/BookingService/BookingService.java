package com.CineReserve.BookingService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CineReserve.Appuser.AppUser;
import com.CineReserve.Appuser.User;
import com.CineReserve.BookingCineReserve.BookMyShow;
import com.CineReserve.BookingDTO.BookingDTO;
import com.CineReserve.BookingDTO.BookingResponseDTO;
import com.CineReserve.BookingRepository.BookingRepo;
import com.CineReserve.CustomException.BookingIdNotfoundException;
import com.CineReserve.CustomException.CancelBookingException;
import com.CineReserve.CustomException.MovieNotFoundException;
import com.CineReserve.CustomException.SeatAlreadyBookedException;
import com.CineReserve.CustomException.TheatreNotfoundException;
import com.CineReserve.CustomException.UserNotfoundException;
import com.CineReserve.CustomGenaratorid.Customgenaratorid;
import com.CineReserve.EMailConfiguration.EmailService;
import com.CineReserve.Enum.BookingStatus;
import com.CineReserve.Movie.Movie;
import com.CineReserve.MovieDto.MovieResponseDTO;
import com.CineReserve.MovieRepository.MovieRepo;
import com.CineReserve.Repository.UserRepo;
import com.CineReserve.Theatre.Theatre;
import com.CineReserve.TheatreRepo.TheatreRepo;
import com.CineReserve.mappers.BookingMapper;

import jakarta.transaction.Transactional;

@Service
public class BookingService {

	@Autowired
	private BookingRepo bookingRepository;

	@Autowired
	private MovieRepo movieRepo;

	@Autowired
	private TheatreRepo theatreRepo;

	@Autowired
	private UserRepo userRepository;

	@Autowired
	private EmailService emailService;

	@Autowired
	private BookingMapper bookingMapper;

	public boolean isSeatAlreadyBooked(BookingDTO dto) {
	    Long movieId = dto.getMovieId();
	    Long theatreId = dto.getTheatreId();
	    String seatNumber = dto.getSeatNumber();

	    if (movieId == null || theatreId == null || seatNumber == null || seatNumber.isBlank()) {
	        throw new IllegalArgumentException("Movie, Theatre, and Seat Number must be provided.");
	    }

	    return bookingRepository.existsByTheatreIdAndMovieIdAndSeatNumber(theatreId, movieId, seatNumber);
	}


	@Transactional
	public BookingResponseDTO bookTicket(BookingDTO dto) 
	{		 
		// 1. Fetch Required Entities
		AppUser user = userRepository.findById(dto.getUserId())
				.orElseThrow(() -> new UserNotfoundException("User not found"));
		Movie movie = movieRepo.findById(dto.getMovieId())
				.orElseThrow(() -> new MovieNotFoundException("Movie not found"));
		Theatre theatre = theatreRepo.findById(dto.getTheatreId())
				.orElseThrow(() -> new TheatreNotfoundException("Theatre not found"));

		// 2. Check if seat is already booked
		if (bookingRepository.existsByTheatreIdAndMovieIdAndSeatNumber(
				dto.getTheatreId(), dto.getMovieId(), dto.getSeatNumber())) {
			throw new SeatAlreadyBookedException("Seat already booked");
		}

		// 3. Create Booking
		BookMyShow booking = BookMyShow.builder()
				.bookingId(Customgenaratorid.generateNextId())
				.seatNumber(dto.getSeatNumber())
				.theatre(theatre)
				.movie(movie)
				.user(user)
				.bookingstatus(BookingStatus.Booked)
				.build();

		bookingRepository.save(booking);

		// 4. Send confirmation email
		emailService.sendBookingConfirmation(user, booking);

		// 5. Convert to response DTO
		return bookingMapper.toResponseDTO(booking);

	}

	@Transactional
	public String cancelBooking(String bookingId, Long userId, String reason) {

		// Step 1: Validate user
		AppUser user = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotfoundException("User not found with ID: " + userId));

		// Step 2: Validate booking exists for this user
		BookMyShow booking = bookingRepository.findByBookingIdAndUser_Id(bookingId, userId)
				.orElseThrow(() -> new CancelBookingException("Booking not found for ID: " + bookingId + " and user ID: " + userId));

		// Step 3: Check if already cancelled
		if (booking.getBookingstatus() == BookingStatus.Cancelled) {
			throw new CancelBookingException("Booking is already cancelled.");
		}

		// Step 4: Cancel booking
		booking.setBookingstatus(BookingStatus.Cancelled);
		booking.setReason(reason);
		bookingRepository.save(booking);

		// Step 5: Send cancellation email
		emailService.sendCancellationEmail(user.getEmail(), booking);

		return "✅ Ticket has been successfully cancelled. Confirmation sent to your email.";
	}

	public List<MovieResponseDTO> getAllMovies() {
		List<Movie> movies = movieRepo.findAll();
		return movies.stream()
		        .map((Movie movie) -> MovieResponseDTO.builder()
		            .id(movie.getId())
		            .name(movie.getName())
		            .genre(movie.getGenore())
		            .heroName(movie.getHeroname())         
		            .heroineName(movie.getHeroniename())   
		            .build())
		        .collect(Collectors.toList());
	}



	// Next API Find the All Theatres
	public List<Theatre> getAlltheatres()
	{
		List<Theatre> t1 = theatreRepo.findAll();
		return t1;
	}

	// FindBySeatNumber
	public BookMyShow findbyseatnumber(String seatNumber)
	{
		BookMyShow seat = bookingRepository.findByseatNumber(seatNumber)
				.orElseThrow(()-> new RuntimeException("SeatNumber is Not Found with the id :"+seatNumber)) ;

		return seat;
	}


	public List<BookingResponseDTO> getAllBookings() {
	    List<BookMyShow> bookings = bookingRepository.findAll();

	    return bookings.stream()
	            .map(bookingMapper::toResponseDTO)
	            .collect(Collectors.toList());
	}


	public BookingResponseDTO getByBookingId(String bookingId) {
	    BookMyShow booking = bookingRepository.findByBookingId(bookingId)
	            .orElseThrow(() ->
	                    new BookingIdNotfoundException("Booking not found for ID: " + bookingId));

	    return bookingMapper.toResponseDTO(booking);
	}

}