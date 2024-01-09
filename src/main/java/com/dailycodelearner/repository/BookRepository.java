package com.dailycodelearner.repository;

import com.dailycodelearner.entity.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book,Long> {
}
