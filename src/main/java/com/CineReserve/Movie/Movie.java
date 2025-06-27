package com.CineReserve.Movie;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movie")
public class Movie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	@Column(name = "name")
	private String name;
	@Column(name = "Gonere")
    private String gonere;
	@Column(name = "heroname")
    private String heroname;
	@Column(name = "heroniename")
    private String heroniename;
	
	

}
