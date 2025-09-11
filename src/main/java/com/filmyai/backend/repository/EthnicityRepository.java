package com.filmyai.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.filmyai.backend.model.Ethnicity;

public interface EthnicityRepository extends JpaRepository<Ethnicity, Long> {

}
