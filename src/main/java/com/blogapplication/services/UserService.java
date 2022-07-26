package com.blogapplication.services;


import com.blogapplication.payloads.UserDto;
import com.blogapplication.payloads.UserResponse;


public interface UserService {
	
	UserDto registerNewUser(UserDto user);
	
	
	UserDto createUser(UserDto user);

	UserDto updateUser(UserDto user, Integer userId);

	UserDto getUserById(Integer userId);

	void deleteUser(Integer userId);


	UserResponse getAllUsers(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
	
}
