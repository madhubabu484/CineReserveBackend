package com.CineReserve.Controller;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.CineReserve.Appuser.AppUser;
import com.CineReserve.CustomException.UserNameAndPasswordNotValidException;
import com.CineReserve.Service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService servive;


	@Autowired 
	private AuthenticationManager authManager;

	@PostMapping("/adduser")
	public boolean adduser(@RequestBody AppUser user)
	{
		boolean  saveuser = servive.saveuser(user);

		return saveuser;

	}


	@PostMapping("/login")
	public ResponseEntity<String> UserLogin(@RequestBody AppUser user) {

	    try {
	        // it is used to set the login credentials for Validation
	        UsernamePasswordAuthenticationToken token =
	                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());

	        // AuthManager is valid our Login Credentials.
	        Authentication authenticate = authManager.authenticate(token);

	        if (authenticate.isAuthenticated()) {
	            return ResponseEntity.ok("User successfully logged in with credentials: " + user.getEmail());
	        } else {
	            throw new UserNameAndPasswordNotValidException("Invalid username or password.");
	        }

	    } catch (Exception ex) {
	        throw new UserNameAndPasswordNotValidException("Invalid username or password. Details: " + ex.getMessage());
	    }
	}

	@PostMapping("/register")
	public ResponseEntity<String> userRegister(@RequestBody AppUser user)
	{
		boolean   status = servive.saveuser(user);
		{
			if(status)
			{
				return ResponseEntity.ok("User Sucessfully Registred");
			}
			else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("User is Not Registred sucessfully Please try Again Some time : "+user);
			}
		}
	}

}
