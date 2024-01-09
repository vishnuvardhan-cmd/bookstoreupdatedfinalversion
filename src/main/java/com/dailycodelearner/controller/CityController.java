package com.dailycodelearner.controller;

import com.dailycodelearner.dto.requestdto.CityRequestDto;
import com.dailycodelearner.entity.City;
import com.dailycodelearner.service.CityService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    CityService cityService;

    @PostMapping("/save")
    public ResponseEntity<City> addCity(@RequestBody CityRequestDto cityRequestdto){
        City city = cityService.addCity(cityRequestdto);
        return new ResponseEntity<>(city, HttpStatus.CREATED);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<City>> getAll(){
        List<City> cities = cityService.getCities();
        return new ResponseEntity<>(cities, HttpStatus.OK);

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<City> getCity(@PathVariable Long id){
        City city = cityService.getCity(id);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<City> deleteCity(@PathVariable Long id){
        City city = cityService.deleteCity(id);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<City> editCity(@PathVariable Long id,@RequestBody CityRequestDto cityRequestdto){
        City city = cityService.editCity(id, cityRequestdto);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }
}
