package com.blogapplication.payloads;

import com.blogapplication.entities.Post;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {
	private int commentId;
	private String content;
	
	
}
