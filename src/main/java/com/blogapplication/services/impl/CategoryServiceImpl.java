package com.blogapplication.services.impl;

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
import com.blogapplication.exceptions.ResourceNotFoundException;
import com.blogapplication.payloads.CategoryDto;
import com.blogapplication.payloads.CategoryResponse;
import com.blogapplication.repositories.CategoryRepo;
import com.blogapplication.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public CategoryDto createCateogry(CategoryDto categoryDto) {

//		convert categorydto to category
		Category cat = this.modelMapper.map(categoryDto, Category.class);
		Category createdCat = this.categoryRepo.save(cat);
		logger.info("create category Implementation");
		return this.modelMapper.map(createdCat,CategoryDto.class);
	}

	@Override
	public CategoryDto updateCateogry(CategoryDto categoryDto, Integer categoryId) {

		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category ","Category ID",categoryId));
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		Category updatedCat = this.categoryRepo.save(cat);
		logger.info("Update category Implementation");
		return this.modelMapper.map(updatedCat,CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {

		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category " ,"Category ID",categoryId));
		logger.info("delete category Implementation");
		this.categoryRepo.delete(cat);
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Category Id",categoryId));
		logger.info("Get category By Id Implementation ");
		return this.modelMapper.map(cat,CategoryDto.class);
	}

	@Override
	public CategoryResponse getAllCategory(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
		logger.info("Get All category Implementation");
		Sort sort = null;
		if(sortDir.equalsIgnoreCase("asc")) {
			sort = Sort.by(sortBy).ascending();
			
		}
		else {
			sort = Sort.by(sortBy).descending();
		}
		Pageable p1 =  PageRequest.of(pageNumber,pageSize,sort);
		Page<Category> pageCategory = this.categoryRepo.findAll(p1);
		List<Category> category = pageCategory.getContent();
		List<CategoryDto> categoryDtos = category.stream().map(p->this.modelMapper.map(p, CategoryDto.class)).collect(Collectors.toList());
		CategoryResponse categoryResponse= new CategoryResponse();
		categoryResponse.setContent(categoryDtos);
		categoryResponse.setPageNumber(pageCategory.getNumber());
		categoryResponse.setPageSize(pageCategory.getSize());
		categoryResponse.setTotalElements(pageCategory.getTotalElements());
		categoryResponse.setTotalPages(pageCategory.getTotalPages());
		categoryResponse.setLastPage(pageCategory.isLast());
		return categoryResponse;
		
	}

}
