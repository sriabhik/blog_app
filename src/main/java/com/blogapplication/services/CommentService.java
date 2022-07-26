package com.blogapplication.services;

import java.util.List;

import com.blogapplication.payloads.CommentDto;


public interface CommentService {

	CommentDto createComment(CommentDto commentDto,Integer postId);
	void deleteComment(Integer commentId);
	List<CommentDto> getCommentByPost(Integer postId);
}
