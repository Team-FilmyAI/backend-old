package com.filmyai.backend.dto.Genre;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GenreProjectCountDto {
    private String genreName;
    private Long projectCount;
}