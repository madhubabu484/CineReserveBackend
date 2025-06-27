package com.CineReserve.BookingDTO;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {
	
	
	
	private Long userid;
	
	private String bookingid;
	
	private Long movie;
	
	private Long theatre;
	
	private String seatNumber;
	
	private String status;
	
	private String reason;
	
	private LocalDate creat;
	
	
	
	
	

}
