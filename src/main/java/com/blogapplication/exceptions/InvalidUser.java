package com.blogapplication.exceptions;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InvalidUser extends RuntimeException{
	final String UserName;
	final String password;
	
	public InvalidUser(String UserName, String password) {
		super(String.format("Wrong Credentials %s %s",UserName,password));
		this.UserName = UserName;
		this.password = password;
		
	}
}
