package com.blogapplication.payloads;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.blogapplication.entities.Role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
//Dto use for preventing direct impose of entities with apis
public class UserDto {
	
	private int id;
	@NotEmpty
	@Size(min = 4,message = "Username must be minimum of 4 Characters")
	private String name;
	@NotEmpty
	@Email(message ="Email address is not valid !!")
	private String email;
	@NotEmpty
	@Size(min = 5 ,max = 100 , message = "Password must be of length >=5 and <= 100")
	private String password;
	@NotEmpty
	private String about;
	private Set<RoleDto> roles = new HashSet<>();
}
