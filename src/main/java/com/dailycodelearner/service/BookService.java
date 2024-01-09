package com.dailycodelearner.service;

import com.dailycodelearner.dto.requestdto.BookRequestDto;
import com.dailycodelearner.dto.responsedto.BookResponseDto;
import com.dailycodelearner.entity.Book;

import java.util.List;

public interface BookService {

    public Book addBook(Book book);
    public List<BookResponseDto> getAllBooks();

    public BookResponseDto findBookById(Long id);
    public Book getBook(Long id);
    public BookResponseDto editBookById(Long id,BookRequestDto bookRequestDto);

    public BookResponseDto deleteBookById(Long id);

    public BookResponseDto addAuthorToBook(Long bookId,Long authorId);

    public BookResponseDto removeAuthorFromBook(Long bookId,Long authorId);

    public BookResponseDto addCategoryToBook(Long bookId,Long categoryId);

    public BookResponseDto removeCategoryFromBook(Long bookId,Long categoryId);
}
