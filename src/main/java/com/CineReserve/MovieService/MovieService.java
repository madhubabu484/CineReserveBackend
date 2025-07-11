package com.CineReserve.MovieService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CineReserve.CustomException.MovieNotFoundException;
import com.CineReserve.Movie.Movie;
import com.CineReserve.MovieDto.MovieDto;
import com.CineReserve.MovieRepository.MovieRepo;

@Service
public class MovieService {

	@Autowired
	private MovieRepo repo;	

	public Movie addmovie(Movie movie)
	{
		Movie m1 = repo.save(movie);
		return m1;
	}


	public Movie UpdateBymovie( Long id , MovieDto dto)
	{
		Movie m1 = repo.findById(id)
				.orElseThrow(()-> new MovieNotFoundException("Movie Not found with that Id :"+id));
		if(m1!=null)
		{
			m1.setHeroname(dto.getHeroname());
			//Next we will Save the UpdateMovie
			return  repo.save(m1);
		}else{
			// if Movie not foundwith that Id we pass the RunTimeException
			throw new MovieNotFoundException("Movie Not found with the Id :"+id);
		}
	}

	// Fetch By the Movie Based on the Id
	public Movie   getByMovie(Long id)
	{
		Movie m1 = repo.findById(id)
				.orElseThrow(()-> new MovieNotFoundException("Movie Not found with the id  : "+id));
		return m1;
	}

	//Search By MovieName Based on the it's Name
	public String  findByMovieName(String name)
	{
		repo.findByNameIgnoreCase(name)
		.orElseThrow(()->  new RuntimeException("Movie name is Not found with that the name :"+name));
		return "movie";            	     
	}

	public String findByGonere(String gonere)
	{
		repo.findBygenoreIgnoreCase(gonere).orElseThrow(() -> new RuntimeException("Gonre is Not found with the name  : "+gonere));
		return "Movie";
	}

	public List<Movie> findAllgonere()
	{

		List<Movie> m1 = repo.findAll();

		return m1;
	}



	public Movie UpdateBygonere(Long id , MovieDto dto)
	{
		Movie m2 =  repo.findById(id)
				.orElseThrow(()-> new RuntimeException("Gonere is Not found with the id : "+id));

		if(m2!=null)
		{
			m2.setGenore(null); 
			//Next we will Save the UpdateMovie
			repo.save(m2);
		}else {
			throw new RuntimeException("Gonere is not found with the id : "+id);
		}
		return m2;
	}

}





