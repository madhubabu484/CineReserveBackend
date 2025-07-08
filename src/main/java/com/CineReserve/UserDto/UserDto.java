package com.CineReserve.UserDto;

import lombok.Data;

@Data
public class UserDto {
	
	private Long user_id;
	private String name;
	private String password;
	private String email;

}
