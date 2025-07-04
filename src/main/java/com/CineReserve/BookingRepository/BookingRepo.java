package com.CineReserve.BookingRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CineReserve.BookingCineReserve.BookMyShow;

@Repository
public interface BookingRepo extends JpaRepository<BookMyShow , String> {
    
     Optional<BookMyShow> findByBookingIdAndUser_id(String bookingid , Long userid);
     
     boolean existsByTheatreIdAndMovieIdAndSeatNumber(Long theatreId, Long movieId, String seatNumber);  
     
     Optional<BookMyShow> findByseatNumber(String seatNumber);
     
     List<BookMyShow>  findAll(); 
     
     Optional<BookMyShow> findByBookingId(String booking_id);
   
     Optional<BookMyShow> findByBookingIdAndUser_Id(String bookingId, Long userId);
    
    
}
