package com.filmyai.backend.repository;

import com.filmyai.backend.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}
