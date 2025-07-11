package com.CineReserve.Service;

import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.CineReserve.Appuser.AppUser;
import com.CineReserve.Repository.UserRepo;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepo userrepo;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		AppUser a1 = userrepo.findByEmail(email);
		return new User(a1.getEmail(), a1.getPassword(), Collections.emptyList()); // Here return new User it is a predifined method avalible in UserDetails Method.
	}

	public boolean saveuser(AppUser user)
	{
		String passwordencode =   encoder.encode(user.getPassword()); // Purpose of this method Before saving the record inside the  DataBase it is Encrtpt the our Password.
		user.setPassword(passwordencode);
		AppUser saveuser = userrepo.save(user);
		return saveuser.getId()!=null;
	}
}


