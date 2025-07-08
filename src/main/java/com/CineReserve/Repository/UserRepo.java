package com.CineReserve.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CineReserve.Appuser.AppUser;

@Repository
public interface UserRepo extends JpaRepository<AppUser, Long> {
	
	
	Optional<AppUser>   findById(Long id);
	
	
	public AppUser findByEmail(String email); // purpose of this method is whenever user is trying to login then we are verfrify the email avalible or not in the our DataBase.


	 

}
