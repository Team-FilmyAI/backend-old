package com.filmyai.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.filmyai.backend.model.City;


public interface CityRepository extends JpaRepository<City, Long> {
	
	
}
