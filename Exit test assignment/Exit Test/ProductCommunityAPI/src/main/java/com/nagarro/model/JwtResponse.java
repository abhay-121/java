package com.nagarro.model;

import java.io.Serializable;

public class JwtResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	private final String jwttoken;
	private final String userName;
	public JwtResponse(String jwttoken, String userName) {
		super();
		this.jwttoken = jwttoken;
		this.userName = userName;
	}
	public String getJwttoken() {
		return jwttoken;
	}
	public String getUserName() {
		return userName;
	}
	


	

}
