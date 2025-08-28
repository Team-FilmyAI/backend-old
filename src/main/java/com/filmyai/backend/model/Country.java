package com.filmyai.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "countries")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id", updatable = false, nullable = false)
    private Long countryId;

    @Column(name = "country_name", length = 100, nullable = false)
    private String countryName;

}