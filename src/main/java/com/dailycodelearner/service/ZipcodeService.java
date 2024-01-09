package com.dailycodelearner.service;

import com.dailycodelearner.dto.requestdto.ZipcodeRequestDto;
import com.dailycodelearner.entity.Zipcode;

import java.util.List;

public interface ZipcodeService {

    public Zipcode addZipcode(ZipcodeRequestDto zipcodeRequestDto);
    public List<Zipcode> getAllZipcodes();

    public Zipcode findZipcodeById(Long id);

    public Zipcode editZipcode(Long id,ZipcodeRequestDto zipcodeRequestDto);

    public String deleteZipcodeById(Long id);

    public Zipcode addCityToZipcode(Long cityId,Long zipcodeId);

    public Zipcode removeCityFromZipcode(Long zipcodeId);
}
