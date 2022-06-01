package com.blogapplication.services;

import java.util.List;

import com.blogapplication.payloads.CategoryDto;

public interface CategoryService {

	 CategoryDto createCateogry(CategoryDto categoryDto);
	 
	 CategoryDto updateCateogry(CategoryDto categoryDto,Integer categoryId);
	 
	 void deleteCategory(Integer categoryId);
	 
	 CategoryDto getCategory(Integer categoryId);
	 
	 List<CategoryDto> getAllCategory();
	
	
	
}
