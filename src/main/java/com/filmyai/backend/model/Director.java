package com.filmyai.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "directors")
public class Director {

    @Id
    @Column(name = "user_id", updatable = true, nullable = false)
    private Long userId;

    @Column(name = "portfolio_link")
    private String portfolioLink;

    @Column(name = "notable_films")
    private String notableFilms;

    @Column(name = "awards")
    private String awards;

    @Column(name = "experience_years")
    private Integer experienceYears;

    @Column(name = "preferred_genre", length = 100)
    private String preferredGenre;

    @Column(name = "availability", length = 50)
    private String availability;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_directors_user"))
    private User user;

}