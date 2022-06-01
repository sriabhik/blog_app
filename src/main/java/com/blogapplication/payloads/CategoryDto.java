package com.blogapplication.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
	private Integer categoryId;
	@NotBlank
	@Size(min=4,message="size must be between 20 and more")
	private String categoryTitle;
	@NotBlank
	@Size(min=20,message="size must be between 20 and more")
	private String categoryDescription;
	
	
}
