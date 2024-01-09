package com.dailycodelearner.service;

import com.dailycodelearner.dto.mapper;
import com.dailycodelearner.dto.requestdto.CategoryRequestDto;
import com.dailycodelearner.dto.responsedto.CategoryResponseDto;
import com.dailycodelearner.entity.Category;
import com.dailycodelearner.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Category findById(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(()->new IllegalArgumentException("" +
                "Category couldn't found with this given id : "+categoryId));
    }

    @Transactional
    @Override
    public CategoryResponseDto addCategory(CategoryRequestDto categoryRequestDto) {
        Category category=new Category();
        category.setName(categoryRequestDto.getName());
        Category saved = categoryRepository.save(category);
        return mapper.categoryToCategoryResponseDto(saved);
    }

    @Override
    public List<CategoryResponseDto> getCategories() {
        List<Category> categoryList= (List<Category>) categoryRepository.findAll();
        return mapper.categoryToCategoryResponseDtos(categoryList);
    }

    @Override
    public CategoryResponseDto getCategoryById(Long id) {
        Category category = findById(id);
        CategoryResponseDto dto = mapper.categoryToCategoryResponseDto(category);
        return dto;
    }

    @Transactional
    @Override
    public CategoryResponseDto deleteCategory(Long id) {
        Category category=findById(id);
        categoryRepository.delete(category);
        return mapper.categoryToCategoryResponseDto(category);
    }

    @Transactional
    @Override
    public CategoryResponseDto editCategoryById(Long id,CategoryRequestDto categoryRequestDto) {
        Category category=findById(id);
        category.setName(categoryRequestDto.getName());
        return mapper.categoryToCategoryResponseDto(categoryRepository.save(category));
    }
}
