package com.blogapplication.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.blogapplication.entities.User;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

@SpringBootTest
public class UserRepoTest {
	
	@Autowired
	private UserRepo userRepo;
	
	@Test
	void findByEmail() {
		User user = new User(1,"Abhishek","abhishek@gmail.com","Abc@231","This is testing account");
		userRepo.save(user);
		
		Optional<User> email = userRepo.findByEmail("abhishek@gmail.com");
		
		assertThat(email).isNotEqualTo(Optional.empty());
	}
	
}
