package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.entity.User;
import com.spring.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public List<User> getAllUser() {
		return this.userRepo.findAll(); 
	}

	@Override
	public User addUser(User user) {
		return this.userRepo.save(user);
	
	}

	@Override
	public User getUserByEmail(String email) {
		return this.userRepo.findByEmail(email);	
	}

	@Override
	public User getUserByEmailAndPassword(String email, String password) {
		return this.userRepo.findByEmailAndPassword(email, password);
	}

	@Override
	public Long countUser() {
		return this.userRepo.count();
	}
}
