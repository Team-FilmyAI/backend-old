package com.filmyai.backend.service.Genre;

import com.filmyai.backend.dto.Genre.GenreProjectCountDto;
import com.filmyai.backend.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;

    public List<GenreProjectCountDto> getProjectCountByGenre() {
        return genreRepository.getProjectCountByGenre();
    }
}
