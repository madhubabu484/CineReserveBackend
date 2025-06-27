package com.CineReserve.CustomException;

public class CancelBookingException extends RuntimeException {
	
	
	public CancelBookingException(String Message)
	{
		  super(Message);
	}

}
