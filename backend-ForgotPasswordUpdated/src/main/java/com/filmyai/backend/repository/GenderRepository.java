package com.filmyai.backend.repository;

import com.filmyai.backend.model.Genders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderRepository extends JpaRepository<Genders, Long> {
}