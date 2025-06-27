package com.CineReserve.BookingRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CineReserve.Appuser.User;
import com.CineReserve.BookingCineReserve.BookMyShow;
import com.CineReserve.BookingDTO.BookingDTO;
import com.CineReserve.Movie.Movie;
import com.CineReserve.Theatre.Theatre;

@Repository
public interface BookingRepo extends JpaRepository<BookMyShow , String> {
    boolean existsByMovieAndTheatreAndSeatNumber(Movie movieid, Theatre theatreid,String seatNumber);
    
     Optional<BookMyShow> findByBookingIdAndUser_id(String bookingid , Long userid);
     
     
     Optional<BookMyShow> findByseatNumber(String seatNumber);
     
     List<BookMyShow>  findAll(); 
     
     Optional<BookMyShow> findByBookingId(String booking_id);
   
    
    
}
