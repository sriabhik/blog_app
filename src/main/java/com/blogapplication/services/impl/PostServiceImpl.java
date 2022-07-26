package com.blogapplication.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blogapplication.entities.Category;
import com.blogapplication.entities.Post;
import com.blogapplication.entities.User;
import com.blogapplication.exceptions.ResourceNotFoundException;
import com.blogapplication.payloads.PostDto;
import com.blogapplication.payloads.PostResponse;
import com.blogapplication.repositories.CategoryRepo;
import com.blogapplication.repositories.PostRepo;
import com.blogapplication.repositories.UserRepo;
import com.blogapplication.services.PostService;

@Service
public class PostServiceImpl implements PostService {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
		
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","UserId",userId));
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","CategoryId",categoryId));
		
		Post post = this.modelMapper.map(postDto, Post.class);

		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		
		Post createdpost = this.postRepo.save(post);
		logger.info("create post Implemenation");
		return this.modelMapper.map(createdpost, PostDto.class);
		
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post  = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","postID",postId));
		
	
		
	
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		Post updatepost = this.postRepo.save(post);
		logger.info("Update post Implemenation");
		return this.modelMapper.map(updatepost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post","postId",postId));
		logger.info("delete post Implemenation");
		this.postRepo.delete(post);

	}

	@Override
	public PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
		logger.info("getAll post Implemenation");
		Sort sort = null;
		if(sortDir.equalsIgnoreCase("asc")) {
			sort = Sort.by(sortBy).ascending();
			
		}
		else {
			sort = Sort.by(sortBy).descending();
		}
		Pageable p1 =  PageRequest.of(pageNumber,pageSize,sort);
		Page<Post> pagePost = this.postRepo.findAll(p1);
		List<Post> post = pagePost.getContent();
		List<PostDto> postDtos = post.stream().map(p->this.modelMapper.map(p, PostDto.class)).collect(Collectors.toList());
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		return postResponse;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		logger.info("Get post By Id Implemenation");
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post","postId",postId));
		
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		logger.info("Get post By Category Implementation");
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","CategoryId",categoryId));
		List<Post> posts = this.postRepo.findByCategory(category);
		List<PostDto> postDtos = posts.stream().map(p->this.modelMapper.map(p, PostDto.class)).collect(Collectors.toList());
		return postDtos;
		
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		logger.info("Get post By User Implementation");
		User  user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user","userId",userId));
		List<Post> posts =  this.postRepo.findByUser(user);
		List<PostDto> postDtos = posts.stream().map(p-> this.modelMapper.map(p, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {

		List<Post> posts = this.postRepo.findByTitleContaining(keyword);
		List<PostDto> postDtos = posts.stream().map((p)->this.modelMapper.map(p, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

}
