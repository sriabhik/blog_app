package com.blogapplication.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blogapplication.config.AppConstants;
import com.blogapplication.entities.Role;
import com.blogapplication.entities.User;
import com.blogapplication.exceptions.ResourceNotFoundException;
import com.blogapplication.payloads.UserDto;
import com.blogapplication.payloads.UserResponse;
import com.blogapplication.repositories.RoleRepo;
import com.blogapplication.repositories.UserRepo;
import com.blogapplication.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepo roleRepo;

//	this constructor for mocking
	public UserServiceImpl(UserRepo userRepo) {

		this.userRepo= userRepo;
	}

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		User savedUser = this.userRepo.save(user);
		logger.info("user created successfully");
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userId));

		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword((userDto.getPassword()));
		user.setAbout(userDto.getAbout());

		User updatedUser = this.userRepo.save(user);
		UserDto userDto1 = this.userToDto(updatedUser);
		logger.info("user update Implementation ");
		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userId));
		logger.info("Get User By Id implementation ");
		return this.userToDto(user);
	}

	@Override
	public UserResponse getAllUsers(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
		Sort sort = null;
		if(sortDir.equalsIgnoreCase("asc")) {
			sort = Sort.by(sortBy).ascending();
			
		}
		else {
			sort = Sort.by(sortBy).descending();
		}
		Pageable p1 =  PageRequest.of(pageNumber,pageSize,sort);
		Page<User> pageUser = this.userRepo.findAll(p1);
		List<User> user = pageUser.getContent();
	
		List<UserDto> userDtos = user.stream().map(u -> this.userToDto(u)).collect(Collectors.toList());
		UserResponse userResponse = new UserResponse();
		userResponse.setContent(userDtos);
		userResponse.setPageNumber(pageUser.getNumber());
		userResponse.setPageSize(pageUser.getSize());
		userResponse.setTotalElements(pageUser.getTotalElements());
		userResponse.setTotalPages(pageUser.getTotalPages());
		userResponse.setLastPage(pageUser.isLast());
		logger.info("Get all User implemetation ");
		return userResponse;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		logger.info("Delete User implemetation ");
		this.userRepo.delete(user);

	}

	public User dtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		return user;
	}

	public UserDto userToDto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		return userDto;
	}

	@Override
	public UserDto registerNewUser(UserDto userDto) {
	
		User user = this.modelMapper.map(userDto, User.class);

		// encoded the password
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));

		// roles

		Role role = this.roleRepo.findById(AppConstants.ADMIN_USER).get();

		user.getRoles().add(role);

		User newUser = this.userRepo.save(user);
		logger.info("Register new User Implementation ");
		
		return this.modelMapper.map(newUser, UserDto.class);
	}

}