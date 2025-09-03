package com.filmyai.backend.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.filmyai.backend.model.City;

public interface CityRepository extends JpaRepository<City, Long> {
	List<City> findByState_StateId(Long stateId);

}
