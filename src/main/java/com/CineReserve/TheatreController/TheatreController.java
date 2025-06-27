package com.CineReserve.TheatreController;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.CineReserve.Theatre.Theatre;
import com.CineReserve.TheatreRepo.TheatreRepo;
import com.CineReserve.TheatreService.TheatreService;

@RestController
public class TheatreController {
	
	
	@Autowired
	private TheatreService service;
	
    @PostMapping("/addtheatre")
	public Theatre addtheatre(@RequestBody  Theatre theatre)
	{
		Theatre t1 = service.addTheatre(theatre);
		 return t1;
	}
    
    
      @GetMapping("/SearchByTheatre/{theatrename}")
       public ResponseEntity<String> SearchByTheatreName(@PathVariable ("theatrename")String theatrename)
       {
    	  
    	  
    	  
           theatrename = service.FindByTheatreName(theatrename);
    	    
    	    if(theatrename.isEmpty())
    	    {
    	    	
    	    	 return ResponseEntity.status(HttpStatus.NOT_FOUND)
    	    			               .body("Theatre is not found with the Name : "+theatrename);
    	    }
    	    
    	    else {
    	    	
    	    	  return ResponseEntity.ok("Theatre is Avalible with the name : "+theatrename);
    	    }
			
    	  
       }
	

}




