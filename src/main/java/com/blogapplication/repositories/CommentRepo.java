package com.blogapplication.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogapplication.entities.Comment;
import com.blogapplication.entities.Post;


public interface CommentRepo extends JpaRepository<Comment,Integer>{
	List<Comment> findByPost(Post post);
}
