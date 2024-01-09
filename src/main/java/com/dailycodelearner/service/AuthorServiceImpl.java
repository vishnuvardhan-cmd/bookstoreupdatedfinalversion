package com.dailycodelearner.service;

import com.dailycodelearner.dto.mapper;
import com.dailycodelearner.dto.requestdto.AuthorRequestDto;
import com.dailycodelearner.dto.responsedto.AuthorResponseDto;
import com.dailycodelearner.entity.Author;
import com.dailycodelearner.entity.Zipcode;
import com.dailycodelearner.repository.AuthorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final ZipcodeService zipcodeService;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, ZipcodeService zipcodeService) {
        this.authorRepository = authorRepository;
        this.zipcodeService = zipcodeService;
    }

    @Transactional
    @Override
    public AuthorResponseDto addAuthor(AuthorRequestDto authorRequestDto) {
        Author author =new Author();
        author.setName(authorRequestDto.getName());
        if(authorRequestDto.getZipcodeId()==null){
            throw new IllegalArgumentException("author needs a zipcode");
        }
        Zipcode zipcode=zipcodeService.findZipcodeById(authorRequestDto.getZipcodeId());
        author.setZipcode(zipcode);
        Author saveAuthor=authorRepository.save(author);
        return mapper.authorToAuthorResponseDto(saveAuthor);
    }

    @Override
    public List<AuthorResponseDto> getAllAuthors() {
        List<Author> authors= StreamSupport.stream(authorRepository.findAll().spliterator(),false)
                .toList();
        return mapper.authorToAuthorResponseDtos(authors);
    }

    @Override
    public AuthorResponseDto getAuthorById(Long id) {
        return mapper.authorToAuthorResponseDto(getAuthor(id));
    }

    @Override
    public Author getAuthor(Long id) {
        return authorRepository.findById(id).orElseThrow(()->new IllegalArgumentException(
                "author doesn't exsist with given id : "+id
        ));
    }

    @Transactional
    @Override
    public AuthorResponseDto editAuthorById(Long id, AuthorRequestDto authorRequestDto) {
        Author author=getAuthor(id);
        author.setName(authorRequestDto.getName());
        if(authorRequestDto.getZipcodeId()!=null){
            Zipcode zipcode=zipcodeService.findZipcodeById(authorRequestDto.getZipcodeId());
            author.setZipcode(zipcode);
        }
        Author saveAuthor=authorRepository.save(author);
        return mapper.authorToAuthorResponseDto(saveAuthor);
    }

    @Transactional
    @Override
    public AuthorResponseDto deleteAuthorById(Long id) {
        Author author=getAuthor(id);
        authorRepository.delete(author);
        return mapper.authorToAuthorResponseDto(author);
    }

    @Transactional
    @Override
    public AuthorResponseDto addZipcodeToAuthor(Long id, Long zipcodeId) {
        Author author=getAuthor(id);
        Zipcode zipcode=zipcodeService.findZipcodeById(zipcodeId);
        if(Objects.nonNull(author.getZipcode())){
            throw new IllegalArgumentException("Author has already a Zipcode");
        }
        author.setZipcode(zipcode);
        Author saved = authorRepository.save(author);
        return mapper.authorToAuthorResponseDto(saved);
    }

    @Transactional
    @Override
    public AuthorResponseDto deleteZipcodeFromAuthor(Long id) {
        Author author=getAuthor(id);
        if(!Objects.nonNull(author.getZipcode())){
            throw new IllegalArgumentException("Author doesn't have a zipcode to delete");
        }
        author.setZipcode(null);
        Author saved = authorRepository.save(author);
        return mapper.authorToAuthorResponseDto(saved);
    }
}
