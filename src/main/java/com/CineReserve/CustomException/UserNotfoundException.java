package com.CineReserve.CustomException;

@SuppressWarnings("serial")
public class UserNotfoundException extends RuntimeException 
{	
	public UserNotfoundException(String message)
	{
		super(message);
	}
}
