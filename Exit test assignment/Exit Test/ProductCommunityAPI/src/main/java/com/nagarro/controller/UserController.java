package com.nagarro.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.entity.User;
import com.nagarro.service.UserServiceImpl;

@RestController
@CrossOrigin
public class UserController {

	private final UserServiceImpl service;
	
	public UserController(UserServiceImpl service) {
		this.service = service;
	}
	
	@PostMapping("/registerUser")
	public User registerUser(@RequestBody User user) throws Exception{
		String tempEmailId = user.getEmail();
		if(tempEmailId != null && !"".equals(tempEmailId)) {
			User userobj = service.getUserByEmail(tempEmailId);
			if(userobj != null) {
				throw new Exception("User with this " + tempEmailId +" is alrady Exist");
			}
		}
		User userObj = null;
		String password = user.getPassword();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		user.setPassword(hashedPassword);
		userObj = service.addUser(user);
		return userObj;
	}
	
	
}
