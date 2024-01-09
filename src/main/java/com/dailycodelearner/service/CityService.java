package com.dailycodelearner.service;

import com.dailycodelearner.dto.requestdto.CityRequestDto;
import com.dailycodelearner.entity.City;

import java.util.List;

public interface CityService {

    public City addCity(CityRequestDto cityRequestDto);
    public List<City> getCities();

    public City getCity(Long id);

    public City editCity(Long id,CityRequestDto cityRequestDto);

    public City deleteCity(Long id);
}
