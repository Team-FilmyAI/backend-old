package com.filmyai.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.filmyai.backend.model.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {
	
	
}
