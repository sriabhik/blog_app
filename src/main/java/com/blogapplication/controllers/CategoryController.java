package com.blogapplication.controllers;

import javax.validation.Valid;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.blogapplication.config.AppConstants;
import com.blogapplication.payloads.ApiResponse;
import com.blogapplication.payloads.CategoryDto;
import com.blogapplication.payloads.CategoryResponse;
import com.blogapplication.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {

	Logger logger = LoggerFactory.getLogger(CategoryController.class);
	@Autowired
	private CategoryService categoryService;
	//create
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){

		CategoryDto createdCat  = this.categoryService.createCateogry(categoryDto);
		logger.info("Creating categeory with name : {}", categoryDto.getCategoryTitle());
		return new ResponseEntity<CategoryDto>(createdCat,HttpStatus.CREATED);
	}
	//update
	@PutMapping("/updateCategory/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable Integer categoryId){
		CategoryDto updateCat  = this.categoryService.updateCateogry(categoryDto,categoryId);
		logger.info("Updating categeory with name : {}", categoryDto.getCategoryTitle());
		return new ResponseEntity<CategoryDto>(updateCat,HttpStatus.OK);
	}
	
	//	delete
	@DeleteMapping("/deleteCategory/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId){
		this.categoryService.deleteCategory(categoryId);
		logger.info("Deleting categeory with category Id: {}", categoryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted successfully !!",true),HttpStatus.OK);
	}
	
	//get
	@GetMapping("/getCategory/{categoryId}")
	public ResponseEntity<CategoryDto> getUser(@PathVariable Integer categoryId) {
		logger.info("Getting categeory with category Id: {}", categoryId);
		return new ResponseEntity<CategoryDto>(this.categoryService.getCategory(categoryId),HttpStatus.OK);
	}
	
	//getall
	@GetMapping("/getAllCategory")
	public ResponseEntity<CategoryResponse> getAllUser(
			@RequestParam(value="pageNumber",defaultValue=AppConstants.PAGE_NUMBER,required=false) Integer pageNumber,
			@RequestParam(value="pageSize",defaultValue=AppConstants.PAGE_SIZE,required=false) Integer pageSize,
			@RequestParam(value="sortBy",defaultValue=AppConstants.SORT_BY,required=false) String sortBy,
			@RequestParam(value="sortDir",defaultValue=AppConstants.SORT_DIR,required=false) String sortDir
			) {
		logger.info("Get all categeory : ");
		return new ResponseEntity<CategoryResponse>(this.categoryService.getAllCategory(pageNumber,pageSize,sortBy,sortDir),HttpStatus.OK);
	}
}
