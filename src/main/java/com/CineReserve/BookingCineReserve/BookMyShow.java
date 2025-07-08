package com.CineReserve.BookingCineReserve;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.CineReserve.Appuser.AppUser;
import com.CineReserve.Enum.BookingStatus;
import com.CineReserve.Movie.Movie;
import com.CineReserve.Theatre.Theatre;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Your_Ticket")
public class BookMyShow {

	@Id
	@Column(name = "booking_id")
	private String bookingId;
	@Column(name = "SeatNumber")
	private String seatNumber; 

	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createdAt;

	@Enumerated(EnumType.STRING)
	@Column(name = "Booking_Status")
	private BookingStatus bookingstatus;

	@Column(name = "reason")
	private String reason;

	@ManyToOne
	private Movie movie;

	@ManyToOne
	private Theatre theatre;

	@ManyToOne
	private AppUser user;

}
