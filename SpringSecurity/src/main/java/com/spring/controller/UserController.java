package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.entity.User;
import com.spring.service.UserServiceImpl;

@RestController
public class UserController {

	
	@Autowired
	private UserServiceImpl service;
	
	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@RequestBody User user) throws Exception{
		String email = user.getEmail();
		if(email != null && !"".equals(email)) {
			User userObj = this.service.getUserByEmail(email);
			if(userObj != null) {
				throw new Exception("user with this "+ email +" is already Exist");
			}
		}
		User userObj = null;
		String password = user.getPassword();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		user.setPassword(hashedPassword);
		userObj = service.addUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(userObj);
	}
	
	@GetMapping("/allUser")
	public ResponseEntity<List<User>> getAllUser(){
		List<User> list = this.service.getAllUser();
		return ResponseEntity.ok(list);
	}
	
}
