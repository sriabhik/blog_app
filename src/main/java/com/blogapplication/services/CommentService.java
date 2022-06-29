package com.blogapplication.services;

import java.util.List;

import com.blogapplication.payloads.CommentDto;
import com.blogapplication.payloads.PostDto;

public interface CommentService {

	CommentDto createComment(CommentDto commentDto,Integer postId);
	void deleteComment(Integer commentId);
	List<CommentDto> getCommentByPost(Integer postId);
}
