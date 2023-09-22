package com.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.entity.User;

public interface UserRepo extends JpaRepository<User, String> {
	public User findByEmail(String email);
	public User findByEmailAndPassword(String email, String password);

}
