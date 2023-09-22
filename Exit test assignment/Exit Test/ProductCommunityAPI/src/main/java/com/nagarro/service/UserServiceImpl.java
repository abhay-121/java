package com.nagarro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.dbdao.UserDao;
import com.nagarro.entity.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userdao;
	
	@Override
	public List<User> getAllUser() {
		return this.userdao.findAll();
	}

	@Override
	public User addUser(User user) {
		return this.userdao.save(user);
	}

	@Override
	public User getUserByEmailAndPassword(String email, String password) {
		return this.userdao.findByEmailAndPassword(email, password);
	}

	@Override
	public User getUserByEmail(String email) {
		return this.userdao.findByEmail(email);
	}

	@Override
	public Long countAllUser() {
		return this.userdao.count();
	}

	

}
