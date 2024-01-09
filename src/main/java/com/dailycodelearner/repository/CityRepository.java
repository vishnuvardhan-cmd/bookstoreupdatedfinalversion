package com.dailycodelearner.repository;

import com.dailycodelearner.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CityRepository extends JpaRepository<City,Long> {
}
