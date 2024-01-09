package com.dailycodelearner.service;

import com.dailycodelearner.dto.requestdto.CityRequestDto;
import com.dailycodelearner.entity.City;
import com.dailycodelearner.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    private CityRepository cityRepository;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public City addCity(CityRequestDto cityRequestDto) {
        City city = new City();
        city.setCityName(cityRequestDto.getName());
        return cityRepository.save(city);
    }

    @Override
    public List<City> getCities() {
        return cityRepository.findAll();
    }

    @Override
    public City getCity(Long id) {

        return cityRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("City doesn't" +
                "exsist with the given id: " + id));
    }

    @Override
    public City editCity(Long id, CityRequestDto cityRequestDto) {
        City city=getCity(id);
        city.setCityName(cityRequestDto.getName());
        return cityRepository.save(city);
    }

    @Override
    public City deleteCity(Long id) {
        City city=getCity(id);
        cityRepository.delete(city);
        return city;
    }
}
