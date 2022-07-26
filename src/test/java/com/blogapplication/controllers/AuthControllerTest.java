package com.blogapplication.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.blogapplication.entities.User;
import com.blogapplication.payloads.UserDto;
import com.blogapplication.services.UserService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {
	
	@InjectMocks
	private UserController userController;
	
	@Mock
	private UserService userService;
	
//	@Test
//	public void testRegisterNewUser() {
//		MockHttpServletRequest request = new MockHttpServletRequest();
//        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
//        when(userService.registerNewUser(any(UserDto.class))).thenReturn(true);
//	}
}
