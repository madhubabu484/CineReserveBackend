package com.CineReserve.SecurityConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.web.SecurityFilterChain;

import com.CineReserve.Service.UserService;

import lombok.SneakyThrows;

import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity // Enables Spring Security configuration
public class SecurityConfiguration {
	
	
	
	  @Autowired
	  private UserService service;

     //Bean to encode passwords using BCrypt
    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    @SuppressWarnings("deprecation")
	public DaoAuthenticationProvider authprovider()
    {
    	
    	DaoAuthenticationProvider authprovider = new DaoAuthenticationProvider();
    	
    	  authprovider.setPasswordEncoder(encoder());
    	
    	  authprovider.setUserDetailsService(service);
    	return authprovider;
    }
    
    
    @Bean
    public AuthenticationManager authmaManager(AuthenticationConfiguration config) throws Exception
    {
    	
    	 return config.getAuthenticationManager();
    }
    
    
      

     //Defines the security filter chain (what is protected and how)
    @Bean
    @SneakyThrows
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	
    	
    	    http
    	        .csrf().disable()
    	        .authorizeHttpRequests(auth -> auth
    	            .requestMatchers("/login", "/register", "/adduser","/book/create","/book/cancel/YOURBOOKINGID-E21005FF").permitAll()
    	            .anyRequest().authenticated()
    	        )
    	        .httpBasic(Customizer.withDefaults())  
    	        .logout(Customizer.withDefaults());
    	    return http.build();
    	}
}