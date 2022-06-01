package com.blogapplication.payloads;

import com.blogapplication.entities.Category;
import com.blogapplication.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {
	private Integer postId;
	private String title;
	private String content;
	private String imageName;
	private String addedDate;
	private CategoryDto category;
	private UserDto user;
}
