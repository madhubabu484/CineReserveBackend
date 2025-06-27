package com.CineReserve.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.CineReserve.Appuser.User;
import com.CineReserve.Service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService servive;
	
	@PostMapping("/adduser")
	public User adduser(@RequestBody User user)
	{
		   User  saveuser = servive.saveuser(user);
		   
		     return saveuser;
		   
	}
	

}
