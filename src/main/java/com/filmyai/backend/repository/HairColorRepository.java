package com.filmyai.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.filmyai.backend.model.HairColor;

public interface HairColorRepository extends JpaRepository<HairColor, Long> {

}
