package com.blogapplication.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogapplication.entities.Comment;
import com.blogapplication.entities.Post;
import com.blogapplication.entities.User;
import com.blogapplication.exceptions.ResourceNotFoundException;
import com.blogapplication.payloads.CommentDto;
import com.blogapplication.payloads.PostDto;
import com.blogapplication.repositories.CommentRepo;
import com.blogapplication.repositories.PostRepo;
import com.blogapplication.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepo commentRepo;
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","PostId",postId));
		Comment comment = this.modelMapper.map(commentDto,Comment.class);
		comment.setPost(post);
		Comment createComment =this.commentRepo.save(comment);
		return this.modelMapper.map(createComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		// TODO Auto-generated method stub
		 Comment comment = this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("comment","commentId",commentId));
		 this.commentRepo.delete(comment);

	}

	@Override
	public List<CommentDto> getCommentByPost(Integer postId) {
		// TODO Auto-generated method stub
		Post  post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post","postId",postId));
		List<Comment> comments =  this.commentRepo.findByPost(post);
		List<CommentDto> commentDtos = comments.stream().map((p)-> this.modelMapper.map(p, CommentDto.class)).collect(Collectors.toList());
		return commentDtos;
	
	}

}
