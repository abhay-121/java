package com.nagarro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nagarro.entity.User;
import com.nagarro.model.CustomerUserDetails;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserServiceImpl userService;

	@Override
	public CustomerUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		final User user = this.userService.getUserByEmail(email);
		if(user == null) {
			return null;
		}
		
		return new CustomerUserDetails(user);
	}

}
