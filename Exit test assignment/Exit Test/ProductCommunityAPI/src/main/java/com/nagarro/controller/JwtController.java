package com.nagarro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.helper.JwtUtil;
import com.nagarro.model.CustomerUserDetails;
import com.nagarro.model.JwtRequest;
import com.nagarro.model.JwtResponse;

import com.nagarro.service.CustomUserDetailsService;

@RestController
@CrossOrigin
public class JwtController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtil jwtTokenHelper;
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	
	@PostMapping("/authUser")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
		authenticate(jwtRequest.getEmail(), jwtRequest.getPassword());
		final CustomerUserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getEmail());
		final String token = jwtTokenHelper.generateToken(userDetails);
		System.out.println(token);
		return ResponseEntity.ok(new JwtResponse(token, userDetails.userName()));
	}

	
	private void authenticate(String email, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
		}catch(DisabledException e) {
			throw new Exception("User_Disabled", e);
		}catch(BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	
}
