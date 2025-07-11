package com.CineReserve.UserDto;

import java.io.Serializable;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class UserDto implements Serializable{
	private Long user_id;
	private String name;
	private String password;
	private String email;
}
