package com.blogapplication.controllers;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blogapplication.config.AppConstants;
import com.blogapplication.entities.User;
import com.blogapplication.payloads.ApiResponse;
import com.blogapplication.payloads.UserDto;
import com.blogapplication.payloads.UserResponse;
import com.blogapplication.services.UserService;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("*")
public class UserController {
	
	@Autowired
	private UserService userService;
	
//	//post create - user create
//	@PostMapping("/")
//	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
//		UserDto createdUserDto = this.userService.createUser(userDto);
//		return new ResponseEntity<>(createdUserDto,HttpStatus.CREATED);
//	}
	
	//put update user
	@PutMapping("/updateUser/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") Integer userId){
		UserDto updatedUser = this.userService.updateUser(userDto, userId);
		return new ResponseEntity<>(updatedUser,HttpStatus.OK);
	}
	//deleteMapping
	//ApiReponse in payload package is used to give message as response and used as global ,we can use again
	
	@DeleteMapping("/deleteUser/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer userId){
	
	  this.userService.deleteUser(userId);
	  return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted successfully",true),HttpStatus.OK);
	  
	}
	
	
	//get user all user
	@GetMapping("/getAllUser")
	public ResponseEntity<UserResponse>  getAllUsers(
			@RequestParam(value="pageNumber",defaultValue=AppConstants.PAGE_NUMBER,required=false) Integer pageNumber,
			@RequestParam(value="pageSize",defaultValue=AppConstants.PAGE_SIZE,required=false) Integer pageSize,
			@RequestParam(value="sortBy",defaultValue=AppConstants.SORT_BY,required=false) String sortBy,
			@RequestParam(value="sortDir",defaultValue=AppConstants.SORT_DIR,required=false) String sortDir
			){
		return new ResponseEntity<UserResponse>(this.userService.getAllUsers(pageNumber,pageSize,sortBy,sortDir),HttpStatus.OK);
	}
	//get user -by id get user
	@GetMapping("/getUser/{userId}")
	public UserDto getSingleUser(@PathVariable Integer userId){
		UserDto userDto = this.userService.getUserById(userId);
		return userDto;
	}
}
