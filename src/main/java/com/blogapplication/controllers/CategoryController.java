package com.blogapplication.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogapplication.entities.Category;
import com.blogapplication.payloads.ApiResponse;
import com.blogapplication.payloads.CategoryDto;
import com.blogapplication.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	//create
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		CategoryDto createdCat  = this.categoryService.createCateogry(categoryDto);
		return new ResponseEntity<CategoryDto>(createdCat,HttpStatus.CREATED);
	}
	//update
	@PutMapping("/updateCategory/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable Integer categoryId){
		CategoryDto updateCat  = this.categoryService.updateCateogry(categoryDto,categoryId);
		return new ResponseEntity<CategoryDto>(updateCat,HttpStatus.OK);
	}
	
	//	delete
	@DeleteMapping("/deleteCategory/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId){
		this.categoryService.deleteCategory(categoryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted successfully !!",true),HttpStatus.OK);
	}
	
	//get
	@GetMapping("/getCategory/{categoryId}")
	public ResponseEntity<CategoryDto> getUser(@PathVariable Integer categoryId) {
		return new ResponseEntity<CategoryDto>(this.categoryService.getCategory(categoryId),HttpStatus.OK);
	}
	
	//getall
	@GetMapping("/getAllCategory")
	public ResponseEntity<List<CategoryDto>> getAllUser() {
		return new ResponseEntity<List<CategoryDto>>(this.categoryService.getAllCategory(),HttpStatus.OK);
	}
}
