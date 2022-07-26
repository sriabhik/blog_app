package com.blogapplication.controllers;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.blogapplication.entities.User;
import com.blogapplication.exceptions.InvalidUser;
import com.blogapplication.payloads.JwtAuthRequest;
import com.blogapplication.payloads.JwtAuthResponse;
import com.blogapplication.payloads.UserDto;
import com.blogapplication.security.JwtTokenHelper;
import com.blogapplication.services.UserService;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserService userService;
	@Autowired
	private ModelMapper modelMapper;
	//register new user
	
	Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	@PostMapping("/registerUser")
	public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto){
		
		UserDto registeredUser = this.userService.registerNewUser(userDto);
		logger.info(userDto.getEmail());
		return new ResponseEntity<UserDto>(registeredUser, HttpStatus.CREATED);
		
	}
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws BadCredentialsException {
	
		try {
			this.authenticate(request.getUsername(),request.getPassword());
		} catch (BadCredentialsException e) {
			logger.warn("credential Invalid");
			throw new InvalidUser("username","password:");
		}
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
		String token = this.jwtTokenHelper.generateToken(userDetails);
		JwtAuthResponse j = new JwtAuthResponse();
		j.setToken(token);
		return new ResponseEntity<JwtAuthResponse>(j,HttpStatus.OK);
	}

	private void authenticate(String username, String password){
		try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        }
        catch (BadCredentialsException e){
            throw new InvalidUser("username","password:");
        }
	}
	
	@GetMapping("/current-user")
    public UserDto getCurrentUser(Principal principal){
        User user = (User)this.userDetailsService.loadUserByUsername(principal.getName());
        logger.info("Accessing current user");
        return this.modelMapper.map(user, UserDto.class);
    }
}
