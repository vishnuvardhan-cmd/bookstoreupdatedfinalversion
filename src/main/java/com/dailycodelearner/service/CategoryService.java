package com.dailycodelearner.service;

import com.dailycodelearner.dto.requestdto.CategoryRequestDto;
import com.dailycodelearner.dto.responsedto.CategoryResponseDto;
import com.dailycodelearner.entity.Category;

import java.util.List;

public interface CategoryService {
    public Category findById(Long categoryId);
    public CategoryResponseDto addCategory(CategoryRequestDto categoryRequestDto);
    public List<CategoryResponseDto> getCategories();

    public CategoryResponseDto getCategoryById(Long id);

    public CategoryResponseDto deleteCategory(Long id);

    public CategoryResponseDto editCategoryById(Long id,CategoryRequestDto categoryRequestDto);
}
