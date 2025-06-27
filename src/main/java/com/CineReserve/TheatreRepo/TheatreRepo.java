package com.CineReserve.TheatreRepo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CineReserve.Theatre.Theatre;

@Repository
public interface TheatreRepo  extends JpaRepository<Theatre, Long>{
	
	
	
	      Optional<Theatre> findBytheatrenameIgnoreCase(String name);

}
