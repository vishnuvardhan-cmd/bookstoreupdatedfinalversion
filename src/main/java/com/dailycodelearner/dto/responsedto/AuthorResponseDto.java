package com.dailycodelearner.dto.responsedto;

import lombok.Data;

import java.util.List;

@Data
public class AuthorResponseDto {

    private Long id;
    private String name;
    private List<String> bookNames;
    private String zipcodeName;
}
