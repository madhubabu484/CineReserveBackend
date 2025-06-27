package com.CineReserve.MovieController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.CineReserve.CustomException.MovieNotFoundException;
import com.CineReserve.Movie.Movie;
import com.CineReserve.MovieDto.MovieDto;
import com.CineReserve.MovieService.MovieService;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/movie")
public class MovieController {
	
	@Autowired
	private MovieService service;
	
	
    @PostMapping("/addmovie")
	public Movie addmovie(@RequestBody Movie movie)
	{
		Movie m1 = service.addmovie(movie);
		 return m1;
	}
    
    
   @PatchMapping("updatemovie/{id}")
    public ResponseEntity<String> updateMovie(@PathVariable Long id, @RequestBody MovieDto dto) {
        try {
            Movie updatedMovie = service.UpdateBymovie(id, dto);
            return ResponseEntity.ok("Movie successfully updated with ID: " + updatedMovie.getId());
        } catch (MovieNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            		             .body("Error : "+ex.getMessage());
        
        }
   }
   
   

  @GetMapping("getbymovie/{id}")
   public ResponseEntity<String> getByMovie(  @PathVariable  Long  id)
	  {
		   
		       Movie movie = service.getByMovie(id);
		       
		       if(movie!=null)
		       {
		    	  return  ResponseEntity.ok("Movie sucessfully Fetch By the Given id : "+id);
		       }
		       
		       else {
		    	   
		    	             throw new  MovieNotFoundException("Movie is Not Found with the based on the id");
		       }
	  }
  
  
           @GetMapping("/SerachMovieName")
          public ResponseEntity<String> SearchByMovieName( @RequestParam  String name)
          {
        	  
        	    // Call service method, which returns Optional<Movie>

        	     String    movie = service.findByMovieName(name);
        	        
        	     // Check if movie is present in the Optional

        	        if(movie.isEmpty())
        	        {
        	            // If not found, return 404 Not Found status

        	        	  return ResponseEntity.status(HttpStatus.NOT_FOUND)
        	        			               .body("Movie is Not found with the Name "+name);
        	        }
        	        
        	        else
        	        {
        	        	
        	        	return ResponseEntity.ok("Movie  is Sucessfulluy Fetched with the Name");
        	        }
        	        
          }
           
           
                   @GetMapping("/SearchByName/{gonere}")
                   public ResponseEntity<String> searchbygonire(@PathVariable("gonere")  String gonere)
                   {
                	   
                	   
                	 String     m1 = service.findByGonere(gonere);
                	    
                	    if(m1.isEmpty())
                	    {
                	    	
                	    	return ResponseEntity.status(HttpStatus.NOT_FOUND)
                	    			             .body("Not found the Goere name : "+gonere);
                	    }
                	    
                	    else {
                	    	
                	    	return ResponseEntity.ok("Sucessfully Fetched the Gonere name : "+gonere);
                	    	
                	    }
                	    
                   }
                   
                   @GetMapping("/getAll")
                   public List<Movie> getAllMovies()
                   {
                	   List<Movie> m2 = service.findAllgonere();
                	   
                	   return m2;
                   }
                   
                   

@PatchMapping("/updatemoviegonire/{id}")
public ResponseEntity<String> UpdateGonere(@PathVariable ("id") Long id, @RequestBody MovieDto dto )
{
	   
	   
	        Movie movie = service.UpdateBygonere(id, dto);
	        		
	        		if(movie!=null)
	        		{
	        			
	        			return ResponseEntity.ok("Update the gonere with the");
	        		}
	        
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	        		             .body("Un sucessfully Update the gonere with the id : "+id);

}
}
                	       