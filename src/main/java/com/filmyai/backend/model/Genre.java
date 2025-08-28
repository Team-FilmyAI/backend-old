package com.filmyai.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "genres")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id", updatable = false, nullable = false)
    private Long genreId;

    @Column(name = "genre_name", length = 100, nullable = false)
    private String genreName;

}