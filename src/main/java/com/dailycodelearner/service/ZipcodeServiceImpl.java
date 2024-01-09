package com.dailycodelearner.service;

import com.dailycodelearner.dto.requestdto.ZipcodeRequestDto;
import com.dailycodelearner.entity.City;
import com.dailycodelearner.entity.Zipcode;
import com.dailycodelearner.repository.ZipcodeRepository;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class ZipcodeServiceImpl implements ZipcodeService {

    @Resource
    ZipcodeRepository zipcodeRepository;

    @Autowired
    CityService cityService;

    @Transactional
    @Override
    public Zipcode addZipcode(ZipcodeRequestDto zipcodeRequestDto) {
        Zipcode zipcode=new Zipcode();
        zipcode.setName(zipcodeRequestDto.getName());
        if(zipcodeRequestDto.getCityId()==null){
            return zipcodeRepository.save(zipcode);
        }
        City city=cityService.getCity(zipcodeRequestDto.getCityId());
        zipcode.setCity(city);

        return zipcodeRepository.save(zipcode);
    }


    @Override
    public List<Zipcode> getAllZipcodes() {
        return StreamSupport.stream(zipcodeRepository.findAll().spliterator(),false).toList();
    }

    @Override
    public Zipcode findZipcodeById(Long id) {
        Optional<Zipcode> zipcode = zipcodeRepository.findById(id);
        return zipcode.orElseThrow(()->new IllegalArgumentException("Zipcode doesn't exsist with given id : "+id));
    }

    @Transactional
    @Override
    public Zipcode editZipcode(Long id, ZipcodeRequestDto zipcodeRequestDto) {
        Zipcode zipcode=findZipcodeById(id);
        zipcode.setName(zipcodeRequestDto.getName());
        if(zipcodeRequestDto.getCityId()!=null) {
            zipcode.setCity(cityService.getCity(zipcodeRequestDto.getCityId()));
        }
        return zipcode;
    }

    @Transactional
    @Override
    public String deleteZipcodeById(Long id) {
        Zipcode zipcode=findZipcodeById(id);
        zipcodeRepository.delete(zipcode);
        return "zipcode removed successfullly";
    }

    @Transactional
    @Override
    public Zipcode addCityToZipcode(Long cityId, Long zipcodeId) {
        Zipcode zipcode=findZipcodeById(zipcodeId);
        City city=cityService.getCity(cityId);
        if(Objects.nonNull(zipcode.getCity())){
            throw new IllegalArgumentException("Zipcode has already a city");
        }
        zipcode.setCity(city);
        return zipcodeRepository.save(zipcode);

    }

    @Transactional
    @Override
    public Zipcode removeCityFromZipcode(  Long zipcodeId) {
        Zipcode zipcode=findZipcodeById(zipcodeId);
        if(!Objects.nonNull(zipcode.getCity())){
          throw new IllegalArgumentException("zipcode doesn't have a city to remove");
        }
        zipcode.setCity(null);

        return zipcodeRepository.save(zipcode);
    }
}
