package com.spring.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.entity.User;
import com.spring.service.UserServiceImpl;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserServiceImpl service;

	@Override
	public CustomUserDetail loadUserByUsername(String email) throws UsernameNotFoundException {
		// Loading user from database by userName
		final User user = this.service.getUserByEmail(email);
		if (user == null) {
			return null;
		}
		return new CustomUserDetail(user);
	}

}
