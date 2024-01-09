package com.dailycodelearner.dto;

import com.dailycodelearner.dto.responsedto.AuthorResponseDto;
import com.dailycodelearner.dto.responsedto.BookResponseDto;
import com.dailycodelearner.dto.responsedto.CategoryResponseDto;
import com.dailycodelearner.entity.Author;
import com.dailycodelearner.entity.Book;
import com.dailycodelearner.entity.Category;

import java.util.ArrayList;
import java.util.List;

public class mapper {

    public static BookResponseDto bookToBookResponseDto(Book book) {

        BookResponseDto bookResponseDto = new BookResponseDto();
        bookResponseDto.setId(book.getId());
        bookResponseDto.setName(book.getName());
        bookResponseDto.setCategoryName(book.getCategory().getName());
        List<String> names = new ArrayList<>();
        for (Author author : book.getAuthors()) {
            names.add(author.getName());
        }
        bookResponseDto.setAuthorNames(names);

        return bookResponseDto;
    }

    public static List<BookResponseDto> bookToBookResponseDtos(List<Book> books) {
        List<BookResponseDto> bookResponseDtos = new ArrayList<>();
        for (Book book : books) {
            bookResponseDtos.add(bookToBookResponseDto(book));
        }
        return bookResponseDtos;
    }

    public static AuthorResponseDto authorToAuthorResponseDto(Author author) {
        AuthorResponseDto authorResponseDto = new AuthorResponseDto();
        authorResponseDto.setId(author.getId());
        authorResponseDto.setName(author.getName());
        authorResponseDto.setZipcodeName(author.getZipcode().getName());
        List<String> names = new ArrayList<>();
        for (Book book : author.getBooks()) {
            names.add(book.getName());
        }
        authorResponseDto.setBookNames(names);
        return authorResponseDto;
    }

    public static List<AuthorResponseDto> authorToAuthorResponseDtos(List<Author> authors) {
        List<AuthorResponseDto> authorResponseDtos = new ArrayList<>();
        for (Author author : authors) {
            authorResponseDtos.add(authorToAuthorResponseDto(author));
        }
        return authorResponseDtos;
    }

    public static CategoryResponseDto categoryToCategoryResponseDto(Category category) {
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setId(category.getId());
        categoryResponseDto.setName(category.getName());
        List<String> names = new ArrayList<>();
        if (category.getBooks()!=null) {
            for (Book book : category.getBooks()) {
                names.add(book.getName());
            }
            categoryResponseDto.setBookNames(names);
        }
        return categoryResponseDto;
    }

    public static List<CategoryResponseDto> categoryToCategoryResponseDtos(List<Category> categorys) {
        List<CategoryResponseDto> categoryResponseDtos = new ArrayList<>();
        for (Category category : categorys) {
            categoryResponseDtos.add(categoryToCategoryResponseDto(category));
        }
        return categoryResponseDtos;
    }
}
