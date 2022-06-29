package com.blogapplication.payloads;

import java.util.List;

import lombok.Data;

@Data
public class CategoryResponse {
	private List<CategoryDto> content;
	private int pageNumber;
	private int pageSize;
	private long totalElements;
	private int totalPages;
	private boolean lastPage ;

}
