package com.CineReserve.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CineReserve.Appuser.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
	
	
	Optional<User>   findById(Long id);

}
