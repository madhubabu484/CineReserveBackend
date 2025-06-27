package com.CineReserve.Theatre;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Theatre")
public class Theatre {
	
	@Id
	@Column(name = "id")
	private Long id;
	@Column(name = "theatrename")
    private String theatrename;
	@Column(name = "loc")
	private String loc;
	 
	

}
