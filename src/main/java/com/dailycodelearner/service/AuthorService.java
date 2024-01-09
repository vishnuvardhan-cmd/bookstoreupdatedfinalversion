package com.dailycodelearner.service;

import com.dailycodelearner.dto.requestdto.AuthorRequestDto;
import com.dailycodelearner.dto.responsedto.AuthorResponseDto;
import com.dailycodelearner.entity.Author;

import java.util.List;

public interface AuthorService {

    public AuthorResponseDto addAuthor(AuthorRequestDto authorRequestDto);
    public List<AuthorResponseDto> getAllAuthors();

    public AuthorResponseDto getAuthorById(Long id);
    public Author getAuthor(Long id);

    public AuthorResponseDto editAuthorById(Long id,AuthorRequestDto authorRequestDto);

    public AuthorResponseDto deleteAuthorById(Long id);

    public AuthorResponseDto addZipcodeToAuthor(Long id,Long zipcodeId);

    public AuthorResponseDto deleteZipcodeFromAuthor(Long id);
}
