package com.filmyai.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.filmyai.backend.model.State;

public interface StateRepository extends JpaRepository<State, Long> {
	List<State> findByCountry_CountryId(Long countryId);

}
