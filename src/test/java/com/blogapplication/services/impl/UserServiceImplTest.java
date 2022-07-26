package com.blogapplication.services.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.boot.test.context.SpringBootTest;

import com.blogapplication.entities.User;

import com.blogapplication.repositories.UserRepo;
import com.blogapplication.services.UserService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

	@Mock
	private UserService userService;
	
	@Mock
	private UserRepo userRepo;
	

	
	@BeforeEach
	void setUp() {
		userService = new UserServiceImpl(userRepo);
	}
	
	@Test

	void createUser() {
		User user = new User();
		user.setId(1);
		user.setName("Abhishek");
		user.setEmail("abhi@gmail.com");
		user.setPassword("abcdef12");
		user.setAbout("Testing ");
		userRepo.save(user);
		
		verify(userRepo,times(1)).save(user);
		
	}
	
	@Test 
	void getUserById() {
		

		User user = new User(1,"Abhishek","abhishek@gmail.com","Abc@231","This is testing account");
		
		userRepo.save(user);
		
		User exits = this.userRepo.findById(1).get();
		
		System.out.println(exits);
		
		assertThat(exits).isNotEqualTo(Optional.empty());
	}
	
}
