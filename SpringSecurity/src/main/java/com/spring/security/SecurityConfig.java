package com.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	 @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	      	http.cors();
	      	http.csrf()
	      		.disable()
	      		.authorizeRequests()
	      		.antMatchers("/authuser").permitAll()
	      		.antMatchers("/register").permitAll()
	      		.anyRequest().authenticated()
	      		.and()
	      		.exceptionHandling().authenticationEntryPoint(this.jwtAuthenticationEntryPoint)
	      		.and()
	      		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	      	http.headers()
	      		.addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Origin", "*"));
	      	http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	      	return http.build();
	      		
	    }
	 
	 @Bean
	 public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
	     return authenticationConfiguration.getAuthenticationManager();
	 }
	
	 @Bean
	    public PasswordEncoder encoder() {
	        return new BCryptPasswordEncoder();
	    }
	 
	 
}
