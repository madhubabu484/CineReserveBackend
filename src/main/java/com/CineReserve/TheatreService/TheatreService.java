package com.CineReserve.TheatreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CineReserve.Theatre.Theatre;
import com.CineReserve.TheatreRepo.TheatreRepo;

@Service
public class TheatreService {
	

	@Autowired
	private TheatreRepo repo;
	
	public Theatre addTheatre(Theatre theatre) {
	  Theatre t1 = repo.save(theatre);
	  return t1;
	}

   
	

    public String FindByTheatreName(String theatrename)
    {
    	
    	
    	         Theatre t1 = repo.findBytheatrenameIgnoreCase(theatrename)
    	        		          .orElseThrow(() -> new RuntimeException("Theatre is Not Found with you pass the TheatreName : "+theatrename));
    	         
    	         return "Theatre";
    }



}


