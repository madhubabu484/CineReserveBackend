package com.CineReserve.CustomException;

public class UserNameAndPasswordNotValidException  extends RuntimeException{
	
	
	public UserNameAndPasswordNotValidException(String Message)
	{
		 super(Message);
	}

}
