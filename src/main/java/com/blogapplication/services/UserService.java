package com.blogapplication.services;

import java.util.List;

import org.springframework.stereotype.Service;

//import com.blogapplication.entities.User;
import com.blogapplication.payloads.UserDto;


public interface UserService {
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user,Integer userId);
	UserDto getUserById(Integer userId);
	List<UserDto> getAllUsers();
	void deleteUser(Integer userId);
	
}
