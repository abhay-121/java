package com.nagarro.service;

import java.util.List;

import com.nagarro.entity.User;

public interface UserService {
	public List<User> getAllUser();
	public User addUser(User user);
	public User getUserByEmail(String email);
	public User getUserByEmailAndPassword(String email, String password);
	public Long countAllUser();
	
}
