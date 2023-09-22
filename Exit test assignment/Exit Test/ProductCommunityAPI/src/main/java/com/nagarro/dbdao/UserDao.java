package com.nagarro.dbdao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.entity.User;

public interface UserDao extends JpaRepository<User, String> {
	public User findByEmail(String email);
	public User findByEmailAndPassword(String email, String password);

}
