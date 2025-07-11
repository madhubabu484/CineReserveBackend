package com.CineReserve.Appuser;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Entity
@Table(name = "Person_Details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUser   {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;


	@Column(name = "email")
	private String email;
	
	@Size(min = 5 , max = 10, message = "password must berween 5 and 10 charactrisics")
	@Column(name = "Password")
	private String password;

	
	

	
}
