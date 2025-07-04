package com.CineReserve.Canceldto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CancelBookingDTO 
{	
	private String reason;	
	private Long userId;
}
