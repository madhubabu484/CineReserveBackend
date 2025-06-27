package com.CineReserve.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CineReserve.Appuser.User;
import com.CineReserve.Repository.UserRepo;

@Service
public class UserService {
	
	@Autowired
	private UserRepo userrepo;
	
	
	public User saveuser(User user)
	{
		
		     User saveuser = userrepo.save(user);
		     
		     return saveuser;
		     
		     
		     
	}

}
