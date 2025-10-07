package com.filmyai.backend.repository;

import com.filmyai.backend.dto.Genre.GenreProjectCountDto;
import com.filmyai.backend.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    @Query("SELECT new com.filmyai.backend.dto.Genre.GenreProjectCountDto(g.genreName, COUNT(p)) " +
            "FROM Project p JOIN p.genres g " +
            "GROUP BY g.genreName")
    List<GenreProjectCountDto> getProjectCountByGenre();
}