package com.blogapplication.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogapplication.entities.Category;
import com.blogapplication.entities.Post;
import com.blogapplication.entities.User;

public interface PostRepo extends JpaRepository<Post,Integer>{

//	creating custom method to get all post of a user
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);

//  For Searching
	//if need name then user findByNameContaining
	List<Post> findByTitleContaining(String title);
}
