package com.dailycodelearner.repository;

import com.dailycodelearner.entity.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category,Long> {
}
