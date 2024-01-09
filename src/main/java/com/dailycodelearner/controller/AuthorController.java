package com.dailycodelearner.controller;

import com.dailycodelearner.dto.requestdto.AuthorRequestDto;
import com.dailycodelearner.dto.responsedto.AuthorResponseDto;
import com.dailycodelearner.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {
    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/save")
    public ResponseEntity<AuthorResponseDto> addAuthor(@RequestBody AuthorRequestDto authorRequestDto){
        AuthorResponseDto authorResponseDto = authorService.addAuthor(authorRequestDto);
        return new ResponseEntity<>(authorResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<AuthorResponseDto>> getAllAuthors(){
        List<AuthorResponseDto> allAuthors = authorService.getAllAuthors();
        return new ResponseEntity<>(allAuthors, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<AuthorResponseDto> getAuthorById(@PathVariable Long id){
        AuthorResponseDto authorById = authorService.getAuthorById(id);
        return new ResponseEntity<>(authorById, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<AuthorResponseDto> deleteAuthorById(@PathVariable Long id){
        AuthorResponseDto authorResponseDto = authorService.deleteAuthorById(id);
        return new ResponseEntity<>(authorResponseDto, HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<AuthorResponseDto> editAuthorById(@PathVariable Long id,
                                                            @RequestBody AuthorRequestDto authorRequestDto){
        AuthorResponseDto authorResponseDto = authorService.editAuthorById(id, authorRequestDto);
        return new ResponseEntity<>(authorResponseDto, HttpStatus.OK);
    }

    @PostMapping("/addzipcode/{zipcodeid}/toauthor/{authorid}")
    public ResponseEntity<AuthorResponseDto> addZipcodeToAuthor(@PathVariable Long zipcodeId
    ,@PathVariable Long authorId){
        AuthorResponseDto authorResponseDto = authorService.addZipcodeToAuthor(authorId, zipcodeId);
        return new ResponseEntity<>(authorResponseDto, HttpStatus.OK);
    }

    @PostMapping("/deletezipcode/{zipcode}/fromauthor")
    public ResponseEntity<AuthorResponseDto> deleteZipcodeFromAuthor(@PathVariable Long id){
        AuthorResponseDto authorResponseDto = authorService.deleteZipcodeFromAuthor(id);
        return new ResponseEntity<>(authorResponseDto, HttpStatus.OK);
    }
}
