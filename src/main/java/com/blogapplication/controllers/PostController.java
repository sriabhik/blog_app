package com.blogapplication.controllers;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.blogapplication.config.AppConstants;
import com.blogapplication.payloads.ApiResponse;
import com.blogapplication.payloads.PostDto;
import com.blogapplication.payloads.PostResponse;
import com.blogapplication.services.FileService;
import com.blogapplication.services.PostService;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class PostController {
	
	Logger logger = LoggerFactory.getLogger(CommentController.class);
	@Autowired 
	private PostService postService;
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,@PathVariable Integer userId,@PathVariable Integer categoryId){
		PostDto createPost = this.postService.createPost(postDto, userId, categoryId);
		logger.info("creating post");
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
	}
	
	//update post
	@PutMapping("/updatePost/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId){
		PostDto updatePost = this.postService.updatePost(postDto, postId);
		logger.info("Updating post");
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	}
	//delete post
	@DeleteMapping("/deletePost/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId){
		this.postService.deletePost(postId);
		logger.info("Deleting post");
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post deleted successfully!!",true),HttpStatus.OK);
	}
	//get by post id
	@GetMapping("/getPostById/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
		PostDto getPost = this.postService.getPostById(postId);
		logger.info("Getting post by Id");
		return new ResponseEntity<PostDto>(getPost,HttpStatus.OK);
	}
	//get all post
	@GetMapping("/getAllPost")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value="pageNumber",defaultValue=AppConstants.PAGE_NUMBER,required=false) Integer pageNumber,
			@RequestParam(value="pageSize",defaultValue=AppConstants.PAGE_SIZE,required=false) Integer pageSize,
			@RequestParam(value="sortBy",defaultValue=AppConstants.SORT_BY,required=false) String sortBy,
			@RequestParam(value="sortDir",defaultValue=AppConstants.SORT_DIR,required=false) String sortDir
			){
		logger.info("Getting all Post");
		return new ResponseEntity<PostResponse >(this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir),HttpStatus.OK);
	}
	//get all post by user
	@GetMapping("/getPostByUser/{userId}")
	public ResponseEntity<List<PostDto> >getPostByUser(@PathVariable Integer userId){
		List<PostDto> postDto = this.postService.getPostByUser(userId);
		logger.info("Get Post By User");
		return new ResponseEntity<List<PostDto> >(postDto,HttpStatus.OK);
	}
	//get all post by category
	@GetMapping("/getPostByCategory/{categoryId}")
	public ResponseEntity<List<PostDto> >getPostByCategory(@PathVariable Integer categoryId){
		logger.info("Get Post By Category");
		return new ResponseEntity<List<PostDto> >(this.postService.getPostByCategory(categoryId),HttpStatus.OK);
	}

}
