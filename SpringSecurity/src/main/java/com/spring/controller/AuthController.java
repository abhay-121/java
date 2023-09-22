package com.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.model.CustomUserDetail;
import com.spring.model.CustomUserDetailService;
import com.spring.model.JwtHelper;
import com.spring.model.JwtRequest;
import com.spring.model.JwtResponse;

@RestController
@CrossOrigin
public class AuthController {
	
	@Autowired
	private JwtHelper jwtTokenHelper;
	
	@Autowired
	private CustomUserDetailService customUserDetailService;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/authuser")
	public ResponseEntity<JwtResponse> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
		authenticate(jwtRequest.getEmail(), jwtRequest.getPassword());
		CustomUserDetail userDetails = customUserDetailService.loadUserByUsername(jwtRequest.getEmail());
		String token = jwtTokenHelper.generateToken(userDetails);
		System.out.println(token);
		return new ResponseEntity<JwtResponse>(new JwtResponse(token, userDetails.getUsername()), HttpStatus.OK);
	}
	
	private void authenticate(String email, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
		}catch(DisabledException e) {
			throw new Exception("User Disabled");
		}catch(BadCredentialsException e) {
			throw new Exception("Invalid credentials");
		}
	}
}
