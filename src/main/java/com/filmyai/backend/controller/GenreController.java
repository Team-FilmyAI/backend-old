package com.filmyai.backend.controller;

import com.filmyai.backend.dto.Genre.GenreProjectCountDto;
import com.filmyai.backend.service.Genre.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/filmyai/genres")
public class GenreController {

    private final GenreService genreService;

    @GetMapping("/project_count")
    public ResponseEntity<List<GenreProjectCountDto>> getProjectCountByGenre() {
        return ResponseEntity.ok(genreService.getProjectCountByGenre());
    }
}
