package com.dailycodelearner.controller;

import com.dailycodelearner.dto.requestdto.CityRequestDto;
import com.dailycodelearner.dto.requestdto.ZipcodeRequestDto;
import com.dailycodelearner.entity.City;
import com.dailycodelearner.entity.Zipcode;
import com.dailycodelearner.service.CityService;
import com.dailycodelearner.service.ZipcodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/zipcode")
public class ZipcodeController {

    @Autowired
    ZipcodeService zipcodeService;

    @PostMapping("/save")
    public ResponseEntity<Zipcode> addZipcode(@RequestBody ZipcodeRequestDto zipcodeRequestdto){
        Zipcode zipcode = zipcodeService.addZipcode(zipcodeRequestdto);
        return new ResponseEntity<>(zipcode, HttpStatus.CREATED);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Zipcode>> getAll(){
        List<Zipcode> zipcodes = zipcodeService.getAllZipcodes();
        return new ResponseEntity<>(zipcodes, HttpStatus.OK);

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Zipcode> getZipcode(@PathVariable Long id){
        Zipcode zipcode = zipcodeService.findZipcodeById(id);
        return new ResponseEntity<>(zipcode, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteZipcode(@PathVariable Long id){
        String str = zipcodeService.deleteZipcodeById(id);
        return new ResponseEntity<>(str, HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Zipcode> editZipcode(@PathVariable Long id,@RequestBody ZipcodeRequestDto zipcodeRequestdto){
        Zipcode zipcode = zipcodeService.editZipcode(id, zipcodeRequestdto);
        return new ResponseEntity<>(zipcode, HttpStatus.OK);
    }

    @PostMapping("/addcity/{cityid}/tozipcode/{zipcodeid}")
    public ResponseEntity<Zipcode> addCity(@PathVariable("cityid") Long cityid,@PathVariable("zipcodeid") Long zipcodeId){
        Zipcode zipcode=zipcodeService.addCityToZipcode(cityid,zipcodeId);
        return new ResponseEntity<>(zipcode, HttpStatus.OK);
    }

    @DeleteMapping("/deleteCity/{zipcodeid}")
    public ResponseEntity<Zipcode> deleteCityFromZipcode(@PathVariable("zipcodeid") Long zipcodeid){
        Zipcode zipcode = zipcodeService.removeCityFromZipcode(zipcodeid);
        return new ResponseEntity<>(zipcode, HttpStatus.OK);
    }
}
