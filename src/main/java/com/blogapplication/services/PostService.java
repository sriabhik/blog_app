package com.blogapplication.services;

import java.util.List;

import com.blogapplication.entities.Post;
import com.blogapplication.payloads.PostDto;
import com.blogapplication.payloads.PostResponse;

public interface PostService {

	//create
	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
//	update
	PostDto updatePost(PostDto postDto,Integer postId);
//	delete
	void deletePost(Integer postId);
	
	PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	
	PostDto getPostById(Integer postId);
	
	//get all post by category
	List<PostDto> getPostByCategory(Integer categoryId);
	
	//get all post by User
	List<PostDto> getPostByUser(Integer userId);
	
	//Search Posts
	List<PostDto> searchPosts(String keyword);
	
	
}
