package com.filmyai.backend.repository;

import com.filmyai.backend.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}