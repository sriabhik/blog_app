package com.blogapplication.services;

import com.blogapplication.payloads.CategoryDto;
import com.blogapplication.payloads.CategoryResponse;

public interface CategoryService {

	 CategoryDto createCateogry(CategoryDto categoryDto);
	 
	 CategoryDto updateCateogry(CategoryDto categoryDto,Integer categoryId);
	 
	 void deleteCategory(Integer categoryId);
	 
	 CategoryDto getCategory(Integer categoryId);
	 
	 CategoryResponse getAllCategory(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	
	
	
}
