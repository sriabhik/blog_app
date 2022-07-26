package com.blogapplication;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.blogapplication.config.AppConstants;
import com.blogapplication.entities.Role;
import com.blogapplication.repositories.RoleRepo;
@SpringBootApplication

public class BlogApplication implements CommandLineRunner {
	@Autowired
	private RoleRepo roleRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}
	//spring automatically create object and it make enable we ca use anywhere
    @Bean 
    public ModelMapper modelMapper() {
    	return new ModelMapper();
    }
    @Override
    public void run(String... args) throws Exception {
//    	System.out.println(this.passwordEncoder.encode("sriabhik"))
    	try {
    		Role role = new Role();
    		role.setId(AppConstants.ADMIN_USER);
    		role.setName("ADMIN");   		
    		Role role1 = new Role();
    		role1.setId(AppConstants.NORMAL_USER);
    		role1.setName("NORMAL");
    		List<Role> roles = List.of(role,role1);
    		this.roleRepo.saveAll(roles);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
}
