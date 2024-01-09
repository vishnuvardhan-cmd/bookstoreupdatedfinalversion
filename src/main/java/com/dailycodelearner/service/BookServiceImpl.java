package com.dailycodelearner.service;

import com.dailycodelearner.dto.mapper;
import com.dailycodelearner.dto.requestdto.BookRequestDto;
import com.dailycodelearner.dto.responsedto.BookResponseDto;
import com.dailycodelearner.entity.Author;
import com.dailycodelearner.entity.Book;
import com.dailycodelearner.entity.Category;
import com.dailycodelearner.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    private final ZipcodeService zipcodeService;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, CategoryService categoryService,ZipcodeService zipcodeService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.zipcodeService=zipcodeService;
    }

    @Transactional
    @Override
    public Book addBook(Book book) {
//        Book book=new Book();
//        book.setName(bookRequestDto.getName());
//        if(bookRequestDto.getAuthorIds().isEmpty()){
//            throw new IllegalArgumentException("book should have atleast one author");
//        }
//        else {
//            Set<Author> authors = new HashSet<>();
//            for (Long id : bookRequestDto.getAuthorIds()) {
//                authors.add(authorService.getAuthor(id));
//            }
//            book.setAuthors(authors);
//        }
//        if(bookRequestDto.getCategoryId()!=null){
//            Category id = categoryService.findById(bookRequestDto.getCategoryId());
//            book.setCategory(id);
//        }
//        else{
//            throw new IllegalArgumentException("Book should belong to a category");
//        }
//        Author author=new Author();
//        author.setZipcode(zipcodeService.findZipcodeById(6L));
//        author.setName("Sandeep Reddy Vanga");
//        Set<Author> authors=new HashSet<>();
//        authors.add(author);
//        book.setAuthors(authors);
//        book.setCategory(categoryService.findById(2L));
        Book save = bookRepository.save(book);

        return save;
    }

    @Override
    public List<BookResponseDto> getAllBooks() {
        Iterable<Book> books = bookRepository.findAll();
        List<Book> book= StreamSupport.stream(books.spliterator(),false).collect(Collectors.toList());
        return mapper.bookToBookResponseDtos(book);
    }

    @Override
    public BookResponseDto findBookById(Long id) {

        return mapper.bookToBookResponseDto(getBook(id));
    }

    @Override
    public Book getBook(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Book with this" +
                "id : "+id+" doesn't exsist"));
        return book;
    }

    @Transactional
    @Override
    public BookResponseDto editBookById(Long id, BookRequestDto bookRequestDto) {
        Book book=getBook(id);
        book.setName(bookRequestDto.getName());
        if(bookRequestDto.getAuthorIds()!=null){
            Set<Author> authors = new HashSet<>();
            for (Long bookId : bookRequestDto.getAuthorIds()) {
                authors.add(authorService.getAuthor(bookId));
            }
            book.setAuthors(authors);
        }
        if(bookRequestDto.getCategoryId()!=null){
            book.setCategory(categoryService.findById(bookRequestDto.getCategoryId()));
        }
        return mapper.bookToBookResponseDto(book);
    }

    @Transactional
    @Override
    public BookResponseDto deleteBookById(Long id) {
        Book book=getBook(id);
        bookRepository.delete(book);
        return mapper.bookToBookResponseDto(book);
    }

    @Transactional
    @Override
    public BookResponseDto addAuthorToBook(Long bookId, Long authorId) {
        Book book=getBook(bookId);
        Author author=authorService.getAuthor(authorId);
        book.addAuthor(author);
        Book save = bookRepository.save(book);
        return mapper.bookToBookResponseDto(save);
    }

    @Transactional
    @Override
    public BookResponseDto removeAuthorFromBook(Long bookId, Long authorId) {
        Book book=getBook(bookId);
        Author author=authorService.getAuthor(authorId);
        book.removeAuthor(author);
        Book save = bookRepository.save(book);
        return mapper.bookToBookResponseDto(save);
    }

    @Transactional
    @Override
    public BookResponseDto addCategoryToBook(Long bookId, Long categoryId) {
        Book book=getBook(bookId);
        Category id = categoryService.findById(categoryId);
        if(Objects.nonNull(book.getCategory())){
            throw new IllegalArgumentException("book has already a category");
        }
        book.setCategory(id);

        Book save = bookRepository.save(book);
        id.addBook(save);
        return mapper.bookToBookResponseDto(save);
    }

    @Transactional
    @Override
    public BookResponseDto removeCategoryFromBook(Long bookId, Long categoryId) {
        Book book=getBook(bookId);
        Category id = categoryService.findById(categoryId);
        if(!Objects.nonNull(book.getCategory())){
            throw new IllegalArgumentException("book was not allocated to any category");
        }
        book.setCategory(null);
        Book save = bookRepository.save(book);
        id.removeBook(save);
        return mapper.bookToBookResponseDto(save);
    }
}
