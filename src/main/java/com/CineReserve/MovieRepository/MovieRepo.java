package com.CineReserve.MovieRepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CineReserve.Movie.Movie;
import com.CineReserve.MovieDto.MovieDto;

@Repository
public interface MovieRepo extends JpaRepository<Movie, Long> 
{
	Optional<Movie> findById(Long id);

	Movie save(MovieDto dto);

	Optional<Movie> findByNameIgnoreCase(String name);

	Optional<Movie> findBygenoreIgnoreCase(String genore );

}
