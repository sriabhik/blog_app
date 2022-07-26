package com.blogapplication.exceptions;

import lombok.Getter;
import lombok.Setter;
//creating custom exception

@Setter
@Getter
public class ResourceNotFoundException extends RuntimeException{
	final String resourceName;
	final String fieldName;
	final long fieldValue;
	public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
		super(String.format("%s not found with %s : %s", resourceName,fieldName,fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	
}
