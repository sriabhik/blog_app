package com.blogapplication.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.blogapplication.payloads.ApiResponse;
import com.blogapplication.payloads.CommentDto;
import com.blogapplication.services.CommentService;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class CommentController {

	Logger logger = LoggerFactory.getLogger(CommentController.class);
	@Autowired
	private CommentService commentService ;
	//create
	
	@PostMapping("/createComment/{postId}")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,@PathVariable Integer postId){
		CommentDto createComment = this.commentService.createComment(commentDto, postId);
		logger.info("Comment created");
		return new ResponseEntity<CommentDto>(createComment,HttpStatus.OK); 
	}
	//delete
	@DeleteMapping("/deleteComment/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
		this.commentService.deleteComment(commentId);
		logger.info("Comment deleted");
		return new ResponseEntity<ApiResponse>(new ApiResponse("Comment deleted successfully!!",true),HttpStatus.OK);
	}
	
	//get comment py post
	@GetMapping("/getCommentByPost/{postId}")
	public ResponseEntity<List<CommentDto> > getComment(@PathVariable Integer postId){
		List<CommentDto> commentDto = this.commentService.getCommentByPost(postId);
		logger.info("get Comment by Id");
		return new ResponseEntity<List<CommentDto> >(commentDto,HttpStatus.OK);
	}
}
