package com.dailycodelearner.repository;

import com.dailycodelearner.entity.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author,Long> {
}
