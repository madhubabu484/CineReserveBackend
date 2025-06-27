package com.CineReserve.BookingService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CineReserve.Appuser.User;
import com.CineReserve.BookingCineReserve.BookMyShow;
import com.CineReserve.BookingDTO.BookingDTO;
import com.CineReserve.BookingRepository.BookingRepo;
import com.CineReserve.CustomException.BookingIdNotfoundException;
import com.CineReserve.CustomException.MovieNotFoundException;
import com.CineReserve.CustomException.TheatreNotfoundException;
import com.CineReserve.CustomException.UserNotfoundException;
import com.CineReserve.CustomGenaratorid.Customgenaratorid;
import com.CineReserve.EMailCancelConformation.EmailServiceCancel;
import com.CineReserve.EMailConfiguration.EmailService;
import com.CineReserve.Enum.BookingStatus;
import com.CineReserve.Movie.Movie;
import com.CineReserve.MovieRepository.MovieRepo;
import com.CineReserve.Repository.UserRepo;
import com.CineReserve.Theatre.Theatre;
import com.CineReserve.TheatreRepo.TheatreRepo;

@Service
public class BookingService {
	
	    @Autowired
	    private BookingRepo repo;

	    @Autowired
	    private MovieRepo movieRepo;

	    @Autowired
	    private TheatreRepo theatreRepo;
	    
	    @Autowired
	    private UserRepo userrepo; // ✅ Correct type

	    @Autowired
	    private EmailService emailservice;
	    
	    @Autowired
	    private EmailServiceCancel servicecancel;
	   
	    public boolean isSeatAlreadyBooked(BookingDTO dto) {
	        Movie movie = movieRepo.findById(dto.getMovie()).orElseThrow();
	        Theatre theatre = theatreRepo.findById(dto.getTheatre()).orElseThrow();
	        
	        User user = userrepo.findById(dto.getUserid()).orElseThrow();

	        return repo.existsByMovieAndTheatreAndSeatNumber(movie, theatre, dto.getSeatNumber());
	    }

	    public BookMyShow saveBooking(BookingDTO dto) throws UserNotfoundException {

	        // 🔍 Debug prints to verify incoming values
	        System.out.println(">>> Movie ID: " + dto.getMovie());
	        System.out.println(">>> Theatre ID: " + dto.getTheatre());
	        System.out.println(">>> Seat Number: " + dto.getSeatNumber());
	        System.out.println(">>>>>User ID  :"+dto.getUserid());
	       

	        if (dto.getMovie() == null || dto.getTheatre() == null) {
	            throw new IllegalArgumentException("Movie ID or Theatre ID is null");
	        }

	        Movie movie = movieRepo.findById(dto.getMovie())
	            .orElseThrow(() -> new MovieNotFoundException("Movie not found"));

	        Theatre theatre = theatreRepo.findById(dto.getTheatre())
	            .orElseThrow(() ->  new TheatreNotfoundException("Theatre Not Found"));
	        

	        User u1 = userrepo.findById(dto.getUserid())
	            .orElseThrow(() ->  new UserNotfoundException("User  Not Found"));
	        
	        
	        
	        
	        BookMyShow show = new BookMyShow();
	        show.setBookingId(Customgenaratorid.generateNextId());
	         show.setSeatNumber(dto.getSeatNumber());
	         show.setTheatre(theatre);
	         show.setMovie(movie);
	         show.setBookingstatus(BookingStatus.pending);
	         show.setUser(u1);
	       

	        

	        repo.save(show);
	        
	        emailservice.BookingConformation(
	        		u1.getEmail(),
	        	    "CineReserve Ticket Confirmation",
	        	    "Hi " + u1.getName() + ",\n\nYour Booking ID is: " + show.getBookingId() +
	        	    "\nMovie: " + movie.getName() +
	        	    "\nTheatre: " + theatre.getTheatrename() +
	        	    "\nYourSeat Number is : " + dto.getSeatNumber() +
	        	    "\n\nEnjoy the show! 🍿"
	            );
	        
	       

	            return show;
	        }
	        
	        
	    
	    
	    
	    public String  CancelBooking(String bookingid , Long id , String reason) throws UserNotfoundException
	    {
	    	
	    	 //STEP 1 : Fetch the User Based on the User 
	    	 
	    	   User u1 = userrepo.findById(id)
	    			             .orElseThrow(()-> new UserNotfoundException("user is not found with that id : "+id));
	    			           
            //STEP 2 : fetch Booking id And User id for Booking Cancellation
	    			             
	    			     BookMyShow booking = repo.findByBookingIdAndUser_id(bookingid,id)
	    			    		                  .orElseThrow(()-> new UserNotfoundException("Booking id and user id is not found Here"));
	    			    		                 
	    			//Update the Status    		                 
	    			 booking.setBookingstatus(BookingStatus.Cancelled);
	    			 
	    			 booking.setReason(reason);
	    			 
	    			  //Step 4 : save the Booking
	    			 repo.save(booking);
	    			 
	    			 
	    			 
	    			 // Now we are call the TicketConformation method inside the Booking Service
	    			 
	    			 servicecancel .TicketCancellation(booking.getUser().getEmail(), booking);

	    			
	    			 return " Your Ticket   is Sucessfully Cancelled and also conformation sent your Email";
	    			 
	    			
	    			
	    			 
	    			    		                 
	    			    		                 
	    }
	    
	   
	    
	   //Next Api find the  All Movies 
	    public List<Movie> getAllMovies()
	    {
	    	
	    	
	    	    List<Movie> m1 = movieRepo.findAll();
				return m1;
	    	
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
	    	
	    	
	    	    BookMyShow seat = repo.findByseatNumber(seatNumber)
	    	    		                       .orElseThrow(()-> new RuntimeException("SeatNumber is Not Found with the id :"+seatNumber)) ;
	    	    
	    	    return seat;
	    }
	    
	    
	    
	    public List<BookMyShow> getAllBookings()
	    {
	    	
	    	 List<BookMyShow> book = repo.findAll();
	    	      return book;
	    }
	    
	    
	    public BookMyShow getbybookingid(String booking_id)
	    {
	    	
	    	   
	    	           BookMyShow show = repo.findByBookingId(booking_id)
	    	        		                 .orElseThrow(()-> new BookingIdNotfoundException("Bookinf id is Not Found with the id : "+booking_id));	    	             
	    	        		                		 return show;
	    }
	    
	    
	    
	  
	    
	    
	    
}
	    			    		                 
	    			    		                 
	    			    		                 
	    			    		                 
	    			            		                 



