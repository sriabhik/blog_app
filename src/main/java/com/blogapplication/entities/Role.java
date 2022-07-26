package com.blogapplication.entities;


import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

@NoArgsConstructor
@Data
public class Role {
	@Id
	private int id;
	private String name;
	
	
}
